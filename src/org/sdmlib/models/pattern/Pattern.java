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

import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.doc.GuiAdapter;
import org.sdmlib.doc.GraphViz.JsonToGraphViz;
import org.sdmlib.models.classes.SDMLibConfig;
import org.sdmlib.models.pattern.util.PatternElementSet;
import org.sdmlib.models.pattern.util.PatternSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonIdMap;

public class Pattern<MP> extends PatternElement<MP> implements PropertyChangeInterface, Iterable<Match>
{
   public static final String CREATE = "create";
   public static final String DESTROY = "destroy";
   public static final String BOUND = "bound";
   
   private JsonIdMap jsonIdMap;
	private GuiAdapter adapter;

	public GuiAdapter getAdapter() {
		if (adapter == null) {
			adapter = new JsonToGraphViz();
		}
		return adapter;
	}
   
   public JsonIdMap getJsonIdMap()
   {
      return jsonIdMap;
   }

   public void setJsonIdMap(JsonIdMap jsonIdMap)
   {
      this.jsonIdMap = jsonIdMap;
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


   
   //==========================================================================
   
   public Pattern(JsonIdMap createIdMap)
   {
      jsonIdMap = createIdMap;
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

      if (getTopPattern().getDebugMode() >= SDMLibConfig.DEBUG_ON)
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
   
   //==========================================================================
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
   
   public boolean rebind(PatternObject boundObject, Object value)
   {
      boundObject.setCurrentMatch(value);
      this.resetSearch();
      return this.findMatch();
   }
   
   public boolean findMatch()
   {
      if ( ! this.getHasMatch())
      {
         return false;
      }
      
      boolean done = false; 
      
      // start with the last element and go backward until a new choice is made, then go forward to propagate the new choice
      int i = this.getElements().size() - 1;
      
      if (restartSearchAtIndex0)
      {
         restartSearchAtIndex0 = false;
         
         i = 0;
         
         if (getTopPattern().getDebugMode() >= SDMLibConfig.DEBUG_ON)
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
      
      setHasMatch( i >= this.getElements().size());
      
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
   }

   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_ELEMENTS.equalsIgnoreCase(attrName))
      {
         return getElements();
      }

      if (PROPERTY_HASMATCH.equalsIgnoreCase(attrName))
      {
         return getHasMatch();
      }

      if (PROPERTY_MODIFIER.equalsIgnoreCase(attribute))
      {
         return getModifier();
      }

      if (PROPERTY_DOALLMATCHES.equalsIgnoreCase(attribute))
      {
         return getDoAllMatches();
      }

      if (PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attribute))
      {
         return getPatternObjectName();
      }

      if (PROPERTY_CURRENTSUBPATTERN.equalsIgnoreCase(attribute))
      {
         return getCurrentSubPattern();
      }

      if (PROPERTY_DEBUGMODE.equalsIgnoreCase(attrName))
      {
         return getDebugMode();
      }

      if (PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         return getPattern();
      }

      if (PROPERTY_TRACE.equalsIgnoreCase(attrName))
      {
         return getTrace();
      }

      if (PROPERTY_RGRAPH.equalsIgnoreCase(attrName))
      {
         return getRgraph();
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_ELEMENTS.equalsIgnoreCase(attrName))
      {
         addToElements((PatternElement) value);
         return true;
      }
      
      if ((PROPERTY_ELEMENTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromElements((PatternElement) value);
         return true;
      }

      if (PROPERTY_HASMATCH.equalsIgnoreCase(attrName))
      {
         setHasMatch((Boolean) value);
         return true;
      }

      if (PROPERTY_MODIFIER.equalsIgnoreCase(attrName))
      {
         setModifier((String) value);
         return true;
      }

      if (PROPERTY_DOALLMATCHES.equalsIgnoreCase(attrName))
      {
         setDoAllMatches((Boolean) value);
         return true;
      }

      if (PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attrName))
      {
         setPatternObjectName((String) value);
         return true;
      }

      if (PROPERTY_CURRENTSUBPATTERN.equalsIgnoreCase(attrName))
      {
         setCurrentSubPattern((Pattern) value);
         return true;
      }

      if (PROPERTY_DEBUGMODE.equalsIgnoreCase(attrName))
      {
         setDebugMode(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         setPattern((Pattern) value);
         return true;
      }

      if (PROPERTY_TRACE.equalsIgnoreCase(attrName))
      {
         setTrace((StringBuilder) value);
         return true;
      }

      if (PROPERTY_RGRAPH.equalsIgnoreCase(attrName))
      {
         setRgraph((ReachabilityGraph) value);
         return true;
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      return false;
   }

   
  //==========================================================================
   
   public void removeYou()
   {
      removeAllFromElements();
      setPattern(null);
      setRgraph(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
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

            if ( ! this.elements.contains(value))
            {
               changed = this.elements.add (value);

               value.withPattern(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ELEMENTS, null, value);
               
               if (value instanceof PatternObject || value instanceof Pattern)
               {
                  getTopPattern().incrementPatternObjectCount();
               }
            }

            if (value instanceof Pattern)
            {
               this.setCurrentSubPattern((Pattern) value);
               ((Pattern) value).setJsonIdMap(this.getJsonIdMap());
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
         changed = this.elements.remove (value);
         
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
      return modelClassName.substring(0, pos+1) + "creators." + modelClassName.substring(pos+1, modelClassName.length()) + "PO";
   }

   public PatternObject bind(Object hostGraphObject)
   {
      SendableEntityCreator creatorClass = getJsonIdMap().getCreator(getPOClassName(hostGraphObject.getClass().getName()), true);
      
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
               "<srcId> -- <tgtId> [headlabel = \"headText\" taillabel = \"tailText\" color=\"black\" fontcolor=\"black\"];\n"
                  );
            
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
               "black", color
               );
            
            edgeBuilder.append(oneEdgeLine.toString());
         }
      }
      
      // hostgraph
      if ( showMatch && ! matchedObjects.isEmpty())
      {
         JsonArray jsonArray = getJsonIdMap().toJsonArray(matchedObjects.iterator().next()); 
      
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
            result = ((Pattern)patternElement).getElementsTransitive(result);
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
            
            StringBuilder nodeLine = new StringBuilder 
                  ("<id> [label=<<table border='0' cellborder='1' cellspacing='0' color='black' bgcolor='deepskyblue'> " +
                  		"<modifier> <tr> <td align='center'> <font color='black'> <id> :<classname> </font></td></tr> " +
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
               "<modifier>", modifier
                  );
            
            if ( ! patObject.getAttrConstraints().isEmpty())
            {
               StringBuilder allAttrLines = new StringBuilder();

               // add attribute constraints
               for (AttributeConstraint attrConstr : patObject.getAttrConstraints())
               {
                  StringBuilder oneAttrLine = new StringBuilder(
                     "<tr><td><font color='black'> attrName Op value </font></td></tr>"
                        );
                  
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
                  String value = "" + (tgtValue instanceof String ?
                                 "\"" + tgtValue + "\"" :
                                 tgtValue);

                  if (attrConstr.getUpperTgtValue() != null)
                  {
                     op = "in";
                     value = "" + tgtValue + ".." + attrConstr.getUpperTgtValue();
                  }
                  
                  CGUtil.replaceAll(oneAttrLine, 
                		  "attrName", attrConstr.getAttrName(),
                		  "black", color, 
                		  "Op", op,
                		  "value", value
                		  );

                  allAttrLines.append(oneAttrLine.toString());
               }
               
               CGUtil.replaceAll(nodeLine, "<tr> <td>  </td></tr>", allAttrLines.toString());
            }
            
            nodeBuilder.append(nodeLine.toString());

            if (patObject.getDoAllMatches())
            {
               // add an "allMatches" node and a link to the current patObject
               StringBuilder allMatchesBuilder = new StringBuilder(
                  "allMatches_patElemId [label=allMatches];\n"
                  );
               
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
                     for (Object candidate : (Collection) patObject.getCandidates())
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
                  else // single candidate
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
               "subgraph cluster_nacId \n" + 
               "{\n" + 
               "   label=<<table border='0' cellborder='0'><tr><td><img src='../../SDMLib.net/doc/forbiddensign.svg'/></td><td>NAC nacId</td></tr></table>>;\n" + 
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
      return getJsonIdMap().getId(patElem).split("\\.")[1].toLowerCase();
   }

   private void dumpDiagram(String diagramName, String fileText)
   {
      File docDir = new File("doc");
      
      docDir.mkdir();
      
      getAdapter().dumpDiagram(diagramName, fileText);
      
   }
   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public static final String PROPERTY_CURRENTSUBPATTERN = "currentSubPattern";
      
   private Pattern currentSubPattern;

   public Pattern getCurrentSubPattern()
   {
      return this.currentSubPattern;
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

   
   //==========================================================================
   
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
         
         if (value >= SDMLibConfig.DEBUG_ON)
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
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getDebugMode());
      _.append(" ").append(this.getModifier());
      _.append(" ").append(this.getPatternObjectName());
      _.append(" ").append(this.getName());
      return _.substring(1);
   }


   
   //==========================================================================
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
      if (debugMode >= SDMLibConfig.DEBUG_ON)
      {
         if (trace == null)
         {
            trace = new StringBuilder();
            traceLength = 0;
         }
         
         traceLength++;
         String line = ""  + traceLength + ": " + msg + "\n";
         trace.append(line);
      
         if (debugMode >= SDMLibConfig.TRACE_ON)
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
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_RGRAPH, oldValue, value);
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

   
   //==========================================================================
   
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
      if ( ! StrUtil.stringEquals(this.name, value))
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


}

