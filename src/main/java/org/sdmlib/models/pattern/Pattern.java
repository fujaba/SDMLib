/*
   Copyright (c) 2012 zuendorf 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package org.sdmlib.models.pattern;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.doc.GraphFactory;
import org.sdmlib.doc.interfaze.Adapter.GuiAdapter;
import org.sdmlib.models.pattern.util.PatternElementSet;
import org.sdmlib.models.pattern.util.PatternSet;
import org.sdmlib.models.tables.Cell;
import org.sdmlib.models.tables.Column;
import org.sdmlib.models.tables.Row;
import org.sdmlib.models.tables.Table;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.Kanban;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonObject;
import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.MatchIsomorphicConstraint;
import org.sdmlib.models.pattern.CloneOp;
import org.sdmlib.models.pattern.UnifyGraphsOp;
import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.pattern.MatchOtherThen;
import org.sdmlib.models.pattern.GenericConstraint;
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.OptionalSubPattern;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.ReachabilityGraph;

/**
 * 
 * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/PatternModelCodeGen.java'>PatternModelCodeGen.java</a>
 */
public class Pattern<MP> extends PatternElement<MP>implements PropertyChangeInterface, Iterable<Match>
{
   public static final String CREATE = "create";

   public static final String DESTROY = "destroy";

   public static final String BOUND = "bound";

   private IdMap idMap;

   private GuiAdapter adapter;

   public boolean addToElementsInCool(PatternElement value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.elements == null)
         {
            this.elements = new PatternElementSet();
         }

         if (!this.elements.contains(value))
         {
            changed = this.elements.add(value);

            value.withPattern((Pattern<PatternElement<?>>) this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ELEMENTS, null, value);

         }
      }

      return changed;
   }



   public GuiAdapter getAdapter()
   {
      if (adapter == null)
      {
         adapter = GraphFactory.getAdapter();
         // adapter = new GraphViz().withDrawer(new org.sdmlib.doc.GraphViz());
      }
      return adapter;
   }


   public IdMap getIdMap()
   {
      return idMap;
   }


   public void setIdMap(IdMap idMap)
   {
      this.idMap = idMap;
      // lazyCloneOp = new LazyCloneOp().setMap(idMap);
   }

   public void clone(ReachabilityGraph rgraph)
   {
      CloneOp cloneOp = new CloneOp();

      this.withElements(cloneOp);

      this.findMatch();
   }

   public void unify(ReachabilityGraph rgraph)
   {
      UnifyGraphsOp unifyGraphsOp = new UnifyGraphsOp();

      this.withElements(unifyGraphsOp);

      this.findMatch();
   }


   // ==========================================================================

   public Pattern(IdMap createIdMap)
   {
      idMap = createIdMap;
      setHasMatch(true);
   }


   public Pattern()
   {
      hasMatch = true;
   }


   public MP startCreate()
   {
      this.setModifier(Pattern.CREATE);
      return (MP) this;
   }


   public MP endCreate()
   {
      this.setModifier(null);
      return (MP) this;
   }


   public MP startNAC()
   {
      NegativeApplicationCondition nac = new NegativeApplicationCondition();

      this.addToElements(nac);

      if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
      {
         nac.setPatternObjectName("n" + getTopPattern().getPatternObjectCount());

      }
      return (MP) this;
   }


   public MP startDestroy()
   {
      this.setModifier(Pattern.DESTROY);
      return (MP) this;
   }


   public MP endDestroy()
   {
      this.setModifier(null);
      return (MP) this;
   }


   public MP matchIsomorphic()
   {
      MatchIsomorphicConstraint isoConstraint = new MatchIsomorphicConstraint();

      this.addToElements(isoConstraint);

      this.findMatch();

      return (MP) this;
   }


   // ==========================================================================
   public int allMatches()
   {
      this.setDoAllMatches(true);

      int result = 0;

      while (getHasMatch())
      {
         result++;
         findMatch();
      }

      return result;
   }


   public Table createResultTable()
   {
      Table result = new Table();

      SimpleSet<PatternObject> patternObjects = new SimpleSet<PatternObject>();

      // add columns for pattern objects
      for (PatternElement elem : this.getElements())
      {
         if (elem instanceof PatternObject)
         {
            Column newCol = result.createColumns();
            patternObjects.add((PatternObject) elem);
         }
      }

      while (this.getHasMatch())
      {
         Row newRow = result.createRows();

         Iterator<Column> colIter = result.getColumns().iterator();
         for (PatternObject po : patternObjects)
         {
            Column col = colIter.next();
            Cell newCell = newRow.createCells()
               .withColumn(col)
               .withValue(po.getCurrentMatch());

         }

         this.findNextMatch();
      }

      return result;
   }


   public boolean rebind(PatternObject boundObject, Object value)
   {
      // set Modifier
      boundObject.setModifier(BOUND);

      boundObject.setCurrentMatch(value);
      this.resetSearch();
      return this.findMatch();
   }


   public boolean findMatch()
   {
      if (!this.getHasMatch())
      {
         return false;
      }

      boolean done = false;
      
      if (this.lazyCloneOp != null)
      {
         this.lazyCloneOp.clear();
      }

      // start with the last element and go backward until a new choice is made,
      // then go forward to propagate the new choice
      int i = this.getElements().size() - 1;

      if (restartSearchAtIndex0)
      {
         restartSearchAtIndex0 = false;

         i = 0;

         if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
         {
            getTopPattern().addLogMsg("\n     Restart pattern: ");
         }
      }

      PatternElement currentPE = null;
      while (i >= 0 && i < this.getElements().size())
      {
         currentPE = this.getElements().get(i);

         boolean hasNextMatch = currentPE.findNextMatch();

         if (hasNextMatch)
         {
            i++;
         }
         else
         {
            i--;
         }
      }

      setHasMatch(i >= this.getElements().size());

      return getHasMatch();
   }


   public boolean findNextMatch()
   {
      return findMatch();
   }

   private boolean restartSearchAtIndex0 = false;


   public void resetSearch()
   {
      restartSearchAtIndex0 = true;

      setHasMatch(true);

      for (PatternElement pe : this.getElements())
      {
         pe.resetSearch();
      }
      
      if (this.lazyCloneOp != null)
      {
         this.lazyCloneOp.clear();
      }
   }


   // ==========================================================================

   public void removeYou()
   {
      removeAllFromElements();
      setPattern(null);
      withoutElements(this.getElements().toArray(new PatternElement[this.getElements().size()]));
      setCurrentSubPattern(null);
      setRgraph(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * Pattern ----------------------------------- PatternElement
    *              pattern                   elements
    * </pre>
    */

   public static final String PROPERTY_ELEMENTS = "elements";

   private PatternElementSet elements = null;

   private int objNo;


   public PatternElementSet getElements()
   {
      if (this.elements == null)
      {
         return PatternElement.EMPTY_SET;
      }

      return this.elements;
   }


   public boolean addToElements(PatternElement value)
   {
      boolean changed = false;

      if (currentSubPattern != null)
      {
         // add element to nac
         changed = currentSubPattern.addToElements(value);
      }
      else
      {
         if (value != null)
         {
            if (this.elements == null)
            {
               this.elements = new PatternElementSet();
            }

            if (!this.elements.contains(value))
            {
               changed = this.elements.add(value);

               value.withPattern((Pattern<PatternElement<?>>) this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ELEMENTS, null, value);

               if (value instanceof PatternObject || value instanceof Pattern)
               {
                  getTopPattern().incrementPatternObjectCount();
               }
            }

            if (value instanceof Pattern)
            {
               this.setCurrentSubPattern((Pattern) value);
               ((Pattern) value).setIdMap(this.getIdMap());
            }
         }
      }

      return changed;
   }


   public boolean removeFromElements(PatternElement value)
   {
      boolean changed = false;

      if ((this.elements != null) && (value != null))
      {
         changed = this.elements.remove(value);

         if (changed)
         {
            value.setPattern(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ELEMENTS, value, null);
         }
      }

      return changed;
   }


   public MP withElements(PatternElement value)
   {
      addToElements(value);
      return (MP) this;
   }


   public MP withoutElements(PatternElement value)
   {
      removeFromElements(value);
      return (MP) this;
   }


   public void removeAllFromElements()
   {
      LinkedHashSet<PatternElement> tmpSet = new LinkedHashSet<PatternElement>(this.getElements());

      for (PatternElement value : tmpSet)
      {
         this.removeFromElements(value);
      }
   }


   public String getPOClassName(String modelClassName)
   {
      int pos = modelClassName.lastIndexOf('.');
      return modelClassName.substring(0, pos + 1) + "creators."
         + modelClassName.substring(pos + 1, modelClassName.length()) + "PO";
   }


   public PatternObject bind(Object hostGraphObject)
   {
      SendableEntityCreator creatorClass = getIdMap().getCreator(
         getPOClassName(hostGraphObject.getClass().getName()), true, null);

      PatternObject po = (PatternObject) creatorClass.getSendableInstance(false);

      this.addToElements(po);

      po.setCurrentMatch(hostGraphObject);

      return po;
   }


   public String dumpDiagram(String diagramName)
   {
      return dumpDiagram(diagramName, true);
   }


   public String dumpDiagram(String diagramName, boolean showMatch)
   {
      JsonObject result = new JsonObject();

      result.put("type", "object");

      JsonArray nodes = new JsonArray();

      result.put("nodes", nodes);

      JsonArray edges = new JsonArray();

      result.put("edges", edges);

      LinkedHashMap<PatternObject, String> nameMap = new LinkedHashMap<PatternObject, String>();

      addNodesToDiagram(this.elements, nodes, nameMap);

      addEdgesToDiagram(this.elements, edges, nameMap);

      String text = "<script>\n" +
         "   var json = " +
         result.toString(3) +
         "   ;\n" +
         "   json[\"options\"]={\"canvasid\":\"canvas" + diagramName + "\", "
         + "\"display\":\"html\", "
         + "\"fontsize\":10,"
         + "\"bar\":true};" +
         "   var g = new Graph(json);\n" +
         "   g.layout(100,100);\n" +
         "</script>\n";
      return text;
   }


   private void addEdgesToDiagram(PatternElementSet elements, JsonArray edges, LinkedHashMap<PatternObject, String> nameMap)
   {
      for (PatternElement elem : elements)
      {
         if (elem instanceof PatternLink)
         {
            PatternLink link = (PatternLink) elem;

            PatternObject src = link.getSrc();
            PatternObject tgt = link.getTgt();

            JsonObject edge = new JsonObject();
            edges.add(edge);

            edge.put("typ", "EDGE");

            JsonObject role = new JsonObject();
            edge.put("source", role);

            // role.put("cardinality", "one");
            role.put("property", " ");
            role.put("id", nameMap.get(src));

            role = new JsonObject();
            edge.put("target", role);

            String modifier = link.getModifier();
            if (Pattern.CREATE.equals(modifier))
            {
               edge.put("style", "create");

            }
            else if (Pattern.DESTROY.equals(modifier))
            {
               edge.put("style", Pattern.DESTROY);

            }

            // role.put("cardinality", "one");
            role.put("property", link.getTgtRoleName());
            role.put("id", nameMap.get(tgt));

         }
         else if (elem instanceof Pattern)
         {
            Pattern subPattern = (Pattern) elem;
            addEdgesToDiagram(subPattern.getElements(), edges, nameMap);
         }

      }
   }


   private void addNodesToDiagram(PatternElementSet elements, JsonArray nodes, LinkedHashMap<PatternObject, String> nameMap)
   {
      for (PatternElement elem : elements)
      {
         if (elem instanceof PatternObject)
         {
            PatternObject po = (PatternObject) elem;

            JsonObject node = new JsonObject();
            node.put("type", "patternObject");

            String modifier = po.getModifier();
            if (Pattern.CREATE.equals(modifier))
            {
               node.put("style", Pattern.CREATE);

            }
            else if (Pattern.DESTROY.equals(modifier))
            {
               node.put("style", Pattern.DESTROY);

            }

            String shortClassName = CGUtil.shortClassName(po.getClass().getName());
            String firstChar = shortClassName.substring(0, 1).toLowerCase();

            int num = nameMap.size() + 1;

            String jsonId = firstChar + num + " : " + shortClassName;

            node.put("id", jsonId);

            nameMap.put(po, jsonId);

            JsonArray attrs = new JsonArray();

            if (po.getModifier() != null)
            {
               attrs.add("<< " + po.getModifier() + ">>");
            }

            for (AttributeConstraint attr : po.getAttrConstraints())
            {
               if (attr.getUpperTgtValue() != null)
               {
                  attrs.add("" + attr.getAttrName() + " in [" + attr.getTgtValue() + ".." + attr.getUpperTgtValue() + "]");
               }
               else
               {
                  attrs.add("" + attr.getAttrName() + " == " + attr.getTgtValue());
               }
            }

            node.put("attributes", attrs);

            nodes.add(node);
         }
         else if (elem instanceof Pattern)
         {
            Pattern subPattern = (Pattern) elem;

            // add subgraph
            JsonObject node = new JsonObject();
            node.put("type", "objectdiagram");
            node.put("style", "nac");
            node.put("info", CGUtil.shortClassName(subPattern.getClass().getName()));
            nodes.add(node);

            JsonArray subNodes = new JsonArray();

            node.put("nodes", subNodes);

            // JsonArray edges = new JsonArray();
            // node.put("edges", edges);

            addNodesToDiagram(subPattern.getElements(), subNodes, nameMap);

         }

      }
   }


   public String dumpDiagramOld(String diagramName, boolean showMatch)
   {
      objNo = 0;

      LinkedHashSet<Object> matchedObjects = new LinkedHashSet<Object>();

      String imgLink = "<embed type=\"image/svg+xml\"src='<imagename>'>\n"
         .replaceFirst("<imagename>", diagramName + ".svg");

      // generate dot file
      String fileText = "graph ObjectDiagram {\n" +
         "   node [shape = none, fontsize = 10, fontname = \"Arial\"];\n" +
         "   edge [fontsize = 10, fontname = \"Arial\"];\n\n" +
         "<nodes>\n" +
         "<edges>" +
         "}\n";

      // nodes
      StringBuilder nodeBuilder = new StringBuilder();
      StringBuilder edgeBuilder = new StringBuilder();

      dumpPatternObjects(nodeBuilder, edgeBuilder, showMatch, matchedObjects);

      if (getDoAllMatches())
      {
         String allMatchesLine = "allMatches;\n";
         nodeBuilder.append(allMatchesLine);
      }

      // edges
      for (PatternElement patElem : this.getElementsTransitive(null))
      {
         if (patElem instanceof PatternLink)
         {
            PatternLink patLink = (PatternLink) patElem;

            String color = "black";

            if (Pattern.CREATE.equals(patLink.getModifier()))
            {
               color = "green";
            }
            else if (Pattern.DESTROY.equals(patLink.getModifier()))
            {
               color = "red";
            }

            StringBuilder oneEdgeLine = new StringBuilder(
                  "<srcId> -- <tgtId> [headlabel = \"headText\" taillabel = \"tailText\" color=\"black\" fontcolor=\"black\"];\n");

            String tgtRoleName = patLink.getTgtRoleName();
            if (tgtRoleName == null)
            {
               tgtRoleName = "";
            }

            String srcRoleName = patLink.getSrcRoleName();
            if (srcRoleName == null)
            {
               srcRoleName = "";
            }

            CGUtil.replaceAll(oneEdgeLine,
               "<srcId>", nameForPatElem(patLink.getSrc()),
               "<tgtId>", nameForPatElem(patLink.getTgt()),
               "headText", tgtRoleName,
               "tailText", srcRoleName,
               "black", color);

            edgeBuilder.append(oneEdgeLine.toString());
         }
      }

      // hostgraph
      if (showMatch && !matchedObjects.isEmpty())
      {
         JsonArray jsonArray = getIdMap().toJsonArray(matchedObjects.iterator().next());

         getAdapter().fillNodeAndEdgeBuilders(diagramName, jsonArray, nodeBuilder, edgeBuilder, false);
      }

      fileText = fileText.replaceFirst("<nodes>", nodeBuilder.toString());

      fileText = fileText.replaceFirst("<edges>", edgeBuilder.toString());

      dumpDiagram(diagramName, fileText);

      return imgLink;
   }


   public PatternElementSet getElementsTransitive(PatternElementSet result)
   {
      if (result == null)
      {
         result = new PatternElementSet();
      }

      for (PatternElement patternElement : this.getElements())
      {
         boolean isNewElem = result.add(patternElement);

         if (isNewElem && patternElement instanceof Pattern)
         {
            result = ((Pattern) patternElement).getElementsTransitive(result);
         }
      }

      return result;
   }

   private GenericConstraint previousConstraint = null;


   public void dumpPatternObjects(StringBuilder nodeBuilder,
         StringBuilder edgeBuilder, boolean showMatch,
         LinkedHashSet<Object> matchedObjects)
   {
      previousConstraint = null;

      for (PatternElement patElem : this.getElements())
      {
         if (patElem instanceof PatternObject)
         {
            PatternObject patObject = (PatternObject) patElem;

            StringBuilder nodeLine = new StringBuilder(
                  "<id> [label=<<table border='0' cellborder='1' cellspacing='0' color='black' bgcolor='deepskyblue'> "
                     +
                     "<modifier> <tr> <td align='center'> <font color='black'> <id> :<classname> </font></td></tr> "
                     +
                     "<tr> <td align='left'> <table border='0' cellborder='0' cellspacing='0' color='black'> <tr> <td>  </td></tr></table></td></tr></table>>];\n");

            String id = nameForPatElem(patObject);

            String color = "black";
            String modifier = "";

            if (Pattern.CREATE.equals(patObject.getModifier()))
            {
               color = "green";
               modifier = "<tr> <td border='0' align='right'><font color='green'>&#171;create&#187;</font></td></tr>";
            }
            else if (Pattern.BOUND.equals(patObject.getModifier()))
            {
               modifier = "<tr> <td border='0' align='right'><font color='black'>&#171;bound&#187;</font></td></tr>";
            }

            CGUtil.replaceAll(nodeLine,
               "<id>", id,
               "<classname>", CGUtil.shortClassName(patElem.getClass().getName()),
               "black", color,
               "<modifier>", modifier);

            if (!patObject.getAttrConstraints().isEmpty())
            {
               StringBuilder allAttrLines = new StringBuilder();

               // add attribute constraints
               for (AttributeConstraint attrConstr : patObject.getAttrConstraints())
               {
                  StringBuilder oneAttrLine = new StringBuilder(
                        "<tr><td><font color='black'> attrName Op value </font></td></tr>");

                  String op = "==";
                  color = "black";

                  if (Pattern.CREATE.equals(attrConstr.getModifier()))
                  {
                     op = ":=";
                     color = "green";
                  }

                  if (attrConstr.getCmpOp() != null)
                  {
                     op = attrConstr.getCmpOp();
                  }

                  Object tgtValue = attrConstr.getTgtValue();
                  String value = "" + (tgtValue instanceof String ? "\"" + tgtValue + "\"" : tgtValue);

                  if (attrConstr.getUpperTgtValue() != null)
                  {
                     op = "in";
                     value = "" + tgtValue + ".." + attrConstr.getUpperTgtValue();
                  }

                  CGUtil.replaceAll(oneAttrLine,
                     "attrName", attrConstr.getAttrName(),
                     "black", color,
                     "Op", op,
                     "value", value);

                  allAttrLines.append(oneAttrLine.toString());
               }

               CGUtil.replaceAll(nodeLine, "<tr> <td>  </td></tr>", allAttrLines.toString());
            }

            nodeBuilder.append(nodeLine.toString());

            if (patObject.getDoAllMatches())
            {
               // add an "allMatches" node and a link to the current patObject
               StringBuilder allMatchesBuilder = new StringBuilder(
                     "allMatches_patElemId [label=allMatches];\n");

               CGUtil.replaceAll(allMatchesBuilder,
                  "patElemId", id);

               nodeBuilder.append(allMatchesBuilder.toString());

               edgeBuilder.append("" + id + " -- allMatches_" + id + " [style=\"dotted\"];\n");
            }

            if (showMatch)
            {
               if (patObject.getCurrentMatch() != null)
               {
                  matchedObjects.add(patObject.getCurrentMatch());
                  StringBuilder matchEdge = new StringBuilder(
                        "<srcId> -- <tgtId> [headlabel = \\\"match\\\" style=\"dotted\" color=\"black\" fontcolor=\"black\"];\n");

                  CGUtil.replaceAll(matchEdge,
                     "<srcId>", id,
                     "<tgtId>", nameForPatElem(patObject.getCurrentMatch()),
                     "black", color);

                  edgeBuilder.append(matchEdge.toString());
               }

               if (patObject.getCandidates() != null)
               {
                  if (patObject.getCandidates() instanceof Collection)
                  {
                     for (Object candidate : (Collection<?>) patObject.getCandidates())
                     {
                        matchedObjects.add(candidate);
                        StringBuilder matchEdge = new StringBuilder(
                              "<srcId> -- <tgtId> [headlabel = \\\"candidate\\\" style=\"dotted\" color=\"black\" fontcolor=\"black\"];\n");

                        CGUtil.replaceAll(matchEdge,
                           "<srcId>", id,
                           "<tgtId>", nameForPatElem(candidate),
                           "black", color);

                        edgeBuilder.append(matchEdge.toString());
                     }
                  }
                  else
                  // single candidate
                  {
                     matchedObjects.add(patObject.getCandidates());
                     StringBuilder matchEdge = new StringBuilder(
                           "<srcId> -- <tgtId> [headlabel = \\\"candidate\\\" style=\"dotted\" color=\"black\" fontcolor=\"black\"];\n");

                     CGUtil.replaceAll(matchEdge,
                        "<srcId>", id,
                        "<tgtId>", nameForPatElem(patObject.getCandidates()),
                        "black", color);

                     edgeBuilder.append(matchEdge.toString());
                  }
               }
            }

         }
         else if (patElem instanceof MatchIsomorphicConstraint)
         {
            nodeBuilder.append("matchIsomorphic;\n");
         }
         else if (patElem instanceof DestroyObjectElem)
         {
            DestroyObjectElem destroyElem = (DestroyObjectElem) patElem;

            StringBuilder destroyBuilder = new StringBuilder(
                  "id [label=\"destroy\" fontcolor=\"red\"]\n");

            CGUtil.replaceAll(destroyBuilder,
               "id", nameForPatElem(destroyElem));

            nodeBuilder.append(destroyBuilder.toString());

            StringBuilder destroyEdgeBuilder = new StringBuilder(
                  "<srcId> -- <tgtId> [style=\"dotted\" color=\"red\" fontcolor=\"red\"];\n");

            CGUtil.replaceAll(destroyEdgeBuilder,
               "<srcId>", nameForPatElem(destroyElem.getPatternObject()),
               "<tgtId>", nameForPatElem(destroyElem));

            edgeBuilder.append(destroyEdgeBuilder.toString());
         }
         else if (patElem instanceof CardinalityConstraint)
         {
            CardinalityConstraint cardConstr = (CardinalityConstraint) patElem;

            StringBuilder cardConstrBuilder = new StringBuilder(
                  "id [label=\"{mincard <= tgtRole.size <= maxcard}\"]\n");

            CGUtil.replaceAll(cardConstrBuilder,
               "id", nameForPatElem(cardConstr),
               "mincard", "" + cardConstr.getMinCard(),
               "maxcard", "" + cardConstr.getMaxCard(),
               "tgtRole", cardConstr.getTgtRoleName());

            nodeBuilder.append(cardConstrBuilder.toString());

            StringBuilder dottedEdgeBuilder = new StringBuilder(
                  "<srcId> -- <tgtId> [style=\"dotted\"];\n");

            CGUtil.replaceAll(dottedEdgeBuilder,
               "<srcId>", nameForPatElem(cardConstr.getSrc()),
               "<tgtId>", nameForPatElem(cardConstr));

            edgeBuilder.append(dottedEdgeBuilder.toString());
         }
         else if (patElem instanceof GenericConstraint)
         {
            GenericConstraint genericConstraint = (GenericConstraint) patElem;

            StringBuilder constrBuilder = new StringBuilder(
                  "id [label=\"text\"]\n");

            CGUtil.replaceAll(constrBuilder,
               "id", nameForPatElem(genericConstraint),
               "text", "" + genericConstraint.getText());

            nodeBuilder.append(constrBuilder.toString());

            // connect to previous constraint for better layout
            if (previousConstraint != null)
            {
               StringBuilder dottedEdgeBuilder = new StringBuilder(
                     "<srcId> -- <tgtId> [style=\"dotted\"];\n");

               CGUtil.replaceAll(dottedEdgeBuilder,
                  "<srcId>", nameForPatElem(previousConstraint),
                  "<tgtId>", nameForPatElem(genericConstraint));

               edgeBuilder.append(dottedEdgeBuilder.toString());

            }

            previousConstraint = genericConstraint;
         }
         else if (patElem instanceof MatchOtherThen)
         {
            MatchOtherThen matchOther = (MatchOtherThen) patElem;

            StringBuilder cardConstrBuilder = new StringBuilder(
                  "id [label=\"{nodeX != nodeY}\"]\n");

            CGUtil.replaceAll(cardConstrBuilder,
               "id", nameForPatElem(matchOther),
               "nodeX", nameForPatElem(matchOther.getSrc()),
               "nodeY", nameForPatElem(matchOther.getForbidden()));

            nodeBuilder.append(cardConstrBuilder.toString());

            StringBuilder destroyEdgeBuilder = new StringBuilder(
                  "<srcId> -- <tgtId> [style=\"dotted\"];\n");

            CGUtil.replaceAll(destroyEdgeBuilder,
               "<srcId>", nameForPatElem(matchOther.getSrc()),
               "<tgtId>", nameForPatElem(matchOther));

            edgeBuilder.append(destroyEdgeBuilder.toString());
         }
         else if (patElem instanceof NegativeApplicationCondition)
         {
            NegativeApplicationCondition nac = (NegativeApplicationCondition) patElem;

            // create nested NAC subgraph
            StringBuilder nacBuilder = new StringBuilder(
                  "subgraph cluster_nacId \n"
                     +
                     "{\n"
                     +
                     "   label=<<table border='0' cellborder='0'><tr><td><img src='../../SDMLib.net/doc/forbiddensign.svg'/></td><td>NAC nacId</td></tr></table>>;\n"
                     +
                     "   color=grey;\n" +
                     "\n");

            CGUtil.replaceAll(nacBuilder,
               "nacId", nameForPatElem(nac));

            nodeBuilder.append(nacBuilder.toString());

            // dump contained pattern objects
            nac.dumpPatternObjects(nodeBuilder, edgeBuilder, showMatch, matchedObjects);

            nodeBuilder.append("}\n\n");
         }
         else if (patElem instanceof OptionalSubPattern)
         {
            OptionalSubPattern optSubPattern = (OptionalSubPattern) patElem;

            // create nested subgraph
            StringBuilder optSubBuilder = new StringBuilder(
                  "subgraph cluster_nacId \n" +
                     "{\n" +
                     "   label=<<table border='0' cellborder='0'><tr><td>optional nacId</td></tr></table>>;\n" +
                     "   color=grey;\n" +
                     "\n");

            CGUtil.replaceAll(optSubBuilder,
               "nacId", nameForPatElem(optSubPattern));

            nodeBuilder.append(optSubBuilder.toString());

            // dump contained pattern objects
            optSubPattern.dumpPatternObjects(nodeBuilder, edgeBuilder, showMatch, matchedObjects);

            nodeBuilder.append("}\n\n");
         }
      }
   }


   private String nameForPatElem(Object patElem)
   {
      if (patElem instanceof PatternObject)
      {
         PatternObject patObj = (PatternObject) patElem;

         if (patObj.getPatternObjectName() != null)
         {
            return patObj.getPatternObjectName();
         }
      }
      return getIdMap().getId(patElem).split("\\.")[1].toLowerCase();
   }


   private void dumpDiagram(String diagramName, String fileText)
   {
      File docDir = new File("doc");

      docDir.mkdir();

      getAdapter().dumpDiagram(diagramName, fileText);

   }

   // ==========================================================================

   public static final String PROPERTY_CURRENTSUBPATTERN = "currentSubPattern";

   private Pattern currentSubPattern;


   public Pattern getCurrentSubPattern()
   {
      return this.currentSubPattern;
   }


   public Pattern getOnDutyPattern()
   {
      Pattern result = this;
      while (result.getCurrentSubPattern() != null)
      {
         result = result.getCurrentSubPattern();
      }
      return result;
   }

   public void setCurrentSubPattern(Pattern value)
   {
      if (this.currentSubPattern != value)
      {
         Pattern oldValue = this.currentSubPattern;
         this.currentSubPattern = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CURRENTSUBPATTERN, oldValue, value);
      }
   }


   public MP withCurrentSubPattern(Pattern value)
   {
      setCurrentSubPattern(value);
      return (MP) this;
   }

   // ==========================================================================

   public static final String PROPERTY_DEBUGMODE = "debugMode";

   private int debugMode;


   public int getDebugMode()
   {
      return this.debugMode;
   }


   public void setDebugMode(int value)
   {
      if (this.debugMode != value)
      {
         int oldValue = this.debugMode;
         this.debugMode = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_DEBUGMODE, oldValue, value);

         if (value >= Kanban.DEBUG_ON)
         {
            setTrace(new StringBuilder());
         }
      }
   }


   public MP withDebugMode(int value)
   {
      setDebugMode(value);
      return (MP) this;
   }


   public String toString()
   {
      StringBuilder s = new StringBuilder();

      s.append(" ").append("Pattern");
      s.append(" ").append(this.getDebugMode());
      s.append(" ").append(this.getModifier());
      s.append(" ").append(this.getPatternObjectName());
      s.append(" ").append(this.getName());
      return s.substring(1);
   }

   // ==========================================================================
   private int patternObjectCount = 0;


   public int getPatternObjectCount()
   {
      return patternObjectCount;
   }


   public void incrementPatternObjectCount()
   {
      patternObjectCount++;
   }

   private LinkedHashSet<String> variablesAlreadyInTrace;


   public LinkedHashSet<String> getVariablesAlreadyInTrace()
   {
      if (variablesAlreadyInTrace == null)
      {
         variablesAlreadyInTrace = new LinkedHashSet<String>();
      }
      return variablesAlreadyInTrace;
   }

   public static final String PROPERTY_TRACE = "trace";

   private StringBuilder trace;


   public StringBuilder getTrace()
   {
      return this.trace;
   }


   public void setTrace(StringBuilder value)
   {
      if (this.trace != value)
      {
         StringBuilder oldValue = this.trace;
         this.trace = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TRACE, oldValue, value);
      }
   }


   public MP withTrace(StringBuilder value)
   {
      setTrace(value);
      return (MP) this;
   }

   public static int traceLength = 0;


   public MP addLogMsg(String msg)
   {
      if (debugMode >= Kanban.DEBUG_ON)
      {
         if (trace == null)
         {
            trace = new StringBuilder();
            traceLength = 0;
         }

         traceLength++;
         String line = "" + traceLength + ": " + msg + "\n";
         trace.append(line);

         if (debugMode >= Kanban.TRACE_ON)
         {
            System.out.print(line);
         }

         if (traceLength >= 9)
         {
            traceLength = traceLength + 0; // break here to stop on trace step i
         }
      }

      return (MP) this;
   }

   public static final PatternSet EMPTY_SET = new PatternSet();

   // ==========================================================================

   public static final String PROPERTY_NAME = "name";

   private String name;

   private boolean riskConcurrentModification;


   public boolean getRiskConcurrentModification()
   {
      return this.riskConcurrentModification;
   }


   public String getName()
   {
      return this.name;
   }


   public void setName(String value)
   {
      if (!StrUtil.stringEquals(this.name, value))
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }


   public Pattern withName(String value)
   {
      setName(value);
      return this;
   }


   @Override
   public Iterator<Match> iterator()
   {
      // TODO Auto-generated method stub
      return new Iterator<Match>()
      {
         private boolean needsNewMatch = false;

         int i = 0;


         @Override
         public boolean hasNext()
         {
            if (needsNewMatch)
            {
               findNextMatch();
               needsNewMatch = false;
            }

            return getHasMatch();
         }


         @Override
         public Match next()
         {
            needsNewMatch = true;
            i++;
            return new Match().withNumber(i);
         }


         @Override
         public void remove()
         {
            // blank
         }

      };
   }


   public Pattern withElements(PatternElement... value)
   {
      if (value == null)
      {
         return this;
      }
      for (PatternElement item : value)
      {
         addToElements(item);
      }
      return this;
   }


   public Pattern withoutElements(PatternElement... value)
   {
      for (PatternElement item : value)
      {
         removeFromElements(item);
      }
      return this;
   }


   public PatternElement createElements()
   {
      PatternElement value = new PatternElement();
      withElements(value);
      return value;
   }


   public MP withRiskConcurrentModification(boolean riskConcurrentModification)
   {
      this.riskConcurrentModification = riskConcurrentModification;
      return (MP) this;
   }


   public PatternObject has(PatternObject po)
   {
      po.withModifier(this.getModifier());
      this.setIdMap(po.getPattern().getIdMap());
      this.addToElements(po);
      this.findMatch();
      return (PatternObject) po;
   }


   public PatternElement createElementsPattern()
   {
      PatternElement value = new Pattern();
      withElements(value);
      return value;
   }


   public PatternElement createElementsPatternObject()
   {
      PatternElement value = new PatternObject();
      withElements(value);
      return value;
   }


   public PatternElement createElementsPatternLink()
   {
      PatternElement value = new PatternLink();
      withElements(value);
      return value;
   }


   public PatternElement createElementsAttributeConstraint()
   {
      PatternElement value = new AttributeConstraint();
      withElements(value);
      return value;
   }


   public PatternElement createElementsMatchIsomorphicConstraint()
   {
      PatternElement value = new MatchIsomorphicConstraint();
      withElements(value);
      return value;
   }


   public PatternElement createElementsCloneOp()
   {
      PatternElement value = new CloneOp();
      withElements(value);
      return value;
   }


   public PatternElement createElementsUnifyGraphsOp()
   {
      PatternElement value = new UnifyGraphsOp();
      withElements(value);
      return value;
   }


   public PatternElement createElementsDestroyObjectElem()
   {
      PatternElement value = new DestroyObjectElem();
      withElements(value);
      return value;
   }


   public PatternElement createElementsCardinalityConstraint()
   {
      PatternElement value = new CardinalityConstraint();
      withElements(value);
      return value;
   }


   public PatternElement createElementsMatchOtherThen()
   {
      PatternElement value = new MatchOtherThen();
      withElements(value);
      return value;
   }


   public PatternElement createElementsGenericConstraint()
   {
      PatternElement value = new GenericConstraint();
      withElements(value);
      return value;
   }


   public PatternElement createElementsNegativeApplicationCondition()
   {
      PatternElement value = new NegativeApplicationCondition();
      withElements(value);
      return value;
   }


   public PatternElement createElementsOptionalSubPattern()
   {
      PatternElement value = new OptionalSubPattern();
      withElements(value);
      return value;
   }


   public PatternElement createElementsLinkConstraint()
   {
      PatternElement value = new LinkConstraint();
      withElements(value);
      return value;
   }


   public Pattern createPattern()
   {
      Pattern value = new Pattern();
      withElements(value);
      return value;
   }


   public PatternObject createPatternObject()
   {
      PatternObject value = new PatternObject();
      withElements(value);
      return value;
   }


   public PatternLink createPatternLink()
   {
      PatternLink value = new PatternLink();
      withElements(value);
      return value;
   }


   public AttributeConstraint createAttributeConstraint()
   {
      AttributeConstraint value = new AttributeConstraint();
      withElements(value);
      return value;
   }


   public MatchIsomorphicConstraint createMatchIsomorphicConstraint()
   {
      MatchIsomorphicConstraint value = new MatchIsomorphicConstraint();
      withElements(value);
      return value;
   }


   public CloneOp createCloneOp()
   {
      CloneOp value = new CloneOp();
      withElements(value);
      this.findMatch();
      return value;
   }


   public UnifyGraphsOp createUnifyGraphsOp()
   {
      UnifyGraphsOp value = new UnifyGraphsOp();
      withElements(value);
      return value;
   }


   public DestroyObjectElem createDestroyObjectElem()
   {
      DestroyObjectElem value = new DestroyObjectElem();
      withElements(value);
      return value;
   }


   public CardinalityConstraint createCardinalityConstraint()
   {
      CardinalityConstraint value = new CardinalityConstraint();
      withElements(value);
      return value;
   }


   public MatchOtherThen createMatchOtherThen()
   {
      MatchOtherThen value = new MatchOtherThen();
      withElements(value);
      return value;
   }


   public GenericConstraint createGenericConstraint()
   {
      GenericConstraint value = new GenericConstraint();
      withElements(value);
      return value;
   }


   public NegativeApplicationCondition createNegativeApplicationCondition()
   {
      NegativeApplicationCondition value = new NegativeApplicationCondition();
      withElements(value);
      return value;
   }


   public OptionalSubPattern createOptionalSubPattern()
   {
      OptionalSubPattern value = new OptionalSubPattern();
      withElements(value);
      return value;
   }


   public LinkConstraint createLinkConstraint()
   {
      LinkConstraint value = new LinkConstraint();
      withElements(value);
      return value;
   }
   public PatternSet getCurrentSubPatternTransitive()
   {
      PatternSet result = new PatternSet().with(this);
      return result.getCurrentSubPatternTransitive();
   }


   public Pattern createCurrentSubPattern()
   {
      Pattern value = new Pattern();
      withCurrentSubPattern(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Pattern ----------------------------------- ReachabilityGraph
    *              rules                   rgraph
    * </pre>
    */
   
   public static final String PROPERTY_RGRAPH = "rgraph";

   private ReachabilityGraph rgraph = null;

   public ReachabilityGraph getRgraph()
   {
      return this.rgraph;
   }

   public boolean setRgraph(ReachabilityGraph value)
   {
      boolean changed = false;
      
      if (this.rgraph != value)
      {
         ReachabilityGraph oldValue = this.rgraph;
         
         if (this.rgraph != null)
         {
            this.rgraph = null;
            oldValue.withoutRules(this);
         }
         
         this.rgraph = value;
         
         if (value != null)
         {
            value.withRules(this);
         }
         
         firePropertyChange(PROPERTY_RGRAPH, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Pattern withRgraph(ReachabilityGraph value)
   {
      setRgraph(value);
      return this;
   } 

   public ReachabilityGraph createRgraph()
   {
      ReachabilityGraph value = new ReachabilityGraph();
      withRgraph(value);
      return value;
   }

   private LazyCloneOp lazyCloneOp = null;

   public LazyCloneOp getLazyCloneOp()
   {
      return lazyCloneOp;
   }



   public Pattern<MP> setLazyCloneOp(LazyCloneOp lazyCloneOp2)
   {
      this.lazyCloneOp = lazyCloneOp2;
      return this;
   }

   public void lazyClone(Object srcObj)
   {
      if (this.lazyCloneOp == null)
      {
         return; // no lazy cloning
      }
      
      if (this.lazyCloneOp.getCloneToOrigMap().get(srcObj) != null)
      {
         // srcObj is already a clone
         return;
      }
      
      // ensure root has already been / is cloned
      if (this.lazyCloneOp.getOrigToCloneMap().isEmpty())
      {
         // get graph root and lazy clone it
         PatternObject firstPO = (PatternObject) this.getElements().first();
         Object root = firstPO.getCurrentMatch();
         this.lazyCloneOp.clone(root);
      }
      
      // does srcObj already have a clone?
      if (this.lazyCloneOp.getOrigToCloneMap().get(srcObj) == null)
      {
         // no, do it
         this.lazyCloneOp.clone(srcObj);
      }
   } 
}
