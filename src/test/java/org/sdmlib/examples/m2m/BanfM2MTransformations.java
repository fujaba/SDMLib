package org.sdmlib.examples.m2m;

import org.junit.Test;
import org.sdmlib.examples.m2m.model.Graph;
import org.sdmlib.examples.m2m.model.GraphComponent;
import org.sdmlib.examples.m2m.model.Person;
import org.sdmlib.examples.m2m.model.Relation;
import org.sdmlib.examples.m2m.model.util.GraphComponentCreator;
import org.sdmlib.examples.m2m.model.util.GraphCreator;
import org.sdmlib.examples.m2m.model.util.PersonCreator;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.objects.Generic2Specific;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.Specific2Generic;
import org.sdmlib.models.objects.util.GenericGraphPO;
import org.sdmlib.models.objects.util.GenericLinkPO;
import org.sdmlib.models.objects.util.GenericLinkSet;
import org.sdmlib.models.objects.util.GenericObjectPO;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.util.PatternCreator;
import org.sdmlib.storyboards.Kanban;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonIdMap;

public class BanfM2MTransformations
{
   private Clazz edgeClazz;
   private Clazz nodeClazz;
   private Clazz graphClazz;
   private GenericGraphPO renameKindAttrGraphPO;
   private GenericGraphPO renamePersonsLinkGraphPO;
   private GenericGraphPO renameRelationsLinkGraphPO;
   private GenericGraphPO renameFirstNameAttrGraphPO;

      
   @Test
   public void testBanfM2MTransformation()
   {  
      Storyboard storyboard = new Storyboard();
      
      storyboard.add("Class diagram for source model:");
   
      ClassModel model = BanfM2MModelGen.genModel();
      
      
      storyboard.addClassDiagram(model);
      
      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("Class diagram for target model:");
      
      model = new ClassModel("org.sdmlib.examples.m2m.model");
      
      graphClazz = model.createClazz("Graph");
      
      Clazz graphComponentClazz = model.createClazz("GraphComponent")
      .withAttribute("text", DataType.STRING);
      
      Clazz nodeClazz = model.createClazz("Person")
      .withSuperClazz(graphComponentClazz);
      
      Clazz edgeClazz = model.createClazz("Relation")
      .withSuperClazz(graphComponentClazz);

      graphClazz.withAssoc(graphComponentClazz, "gcs", Card.MANY, "parent", Card.ONE);
      
      edgeClazz.withAssoc(nodeClazz, "src", Card.ONE, "outEdges", Card.MANY);

      edgeClazz.withAssoc(nodeClazz, "tgt", Card.ONE, "inEdges", Card.MANY);
      
      // model.removeAllGeneratedCode("examples", "examples", "examples");
      
      model.generate("src/test/java");
      
      storyboard.addClassDiagram(model);
      
      storyboard.dumpHTML();
      
      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("Create example source graph: ");
      
      Graph graph = createExampleGraph();
      
      storyboard.addObjectDiagramWith(graph.getPersons(), graph.getRelations());

      
      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("<h2>Migrate using Generic Graph representation : </h2>");

      graph = createExampleGraph();

      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "simpleMigrationByGenericGraph(Graph,Storyboard)"));
      
      Graph tgtGraph = simpleMigrationByGenericGraph(graph, storyboard);

      storyboard.add("Result graph: ");

      storyboard.addObjectDiagramWith(tgtGraph.getGcs());


      //==========================================================================
      storyboard.add("<hr/>");
      storyboard.add("<h2>Even more evolved class diagram : </h2>");

      model = new ClassModel("org.sdmlib.examples.m2m.model");
      
      graphClazz = model.createClazz("Graph");

      nodeClazz = model.createClazz("Person")
      .withAttribute("text", DataType.STRING);

      graphClazz.withAssoc(nodeClazz, "persons", Card.MANY, "graph", Card.ONE);
      
      nodeClazz.withAssoc(nodeClazz, "knows", Card.MANY, "knows", Card.MANY);
      
      model.generate("src/test/java");
      
      storyboard.addClassDiagram(model);
      
      storyboard.add("<hr/>");
      
      storyboard.add("Again the input graph:");
      
      graph = createExampleGraph();
      
      storyboard.addObjectDiagramWith(graph.getPersons(), graph.getRelations());
      
      storyboard.add("The transformation code:");
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "simpleMigrationToEvenMoreEvolvedGraphByGenericGraph(Graph,Storyboard)"));
      
      Graph tgtGraph2 = simpleMigrationToEvenMoreEvolvedGraphByGenericGraph(graph, storyboard);

      storyboard.add("Result graph: ");

      storyboard.addObjectDiagramWith(tgtGraph2.getPersons());
      
      
      //===============================================================================
      storyboard.add("<hr/>");
      storyboard.add("Let us derive the reverse transformation for the first model evolution:");
      
      Graph sourceGraphRevers = simpleReverseMigration(tgtGraph, storyboard);
      
      storyboard.addObjectDiagramWith(sourceGraphRevers.getPersons(), sourceGraphRevers.getRelations());
      
      storyboard.addLogEntry(Kanban.DONE, "zuendorf", "10.10.2013 10:10:42", 20, 0, "should be published in paper");
      
      storyboard.dumpHTML();
   }
   

   private Graph simpleReverseMigration(Graph tgtGraph, Storyboard storyboard)
   {
      // make the graph generic
      GenericGraph genGraph = new Specific2Generic().convert(GraphComponentCreator.createIdMap("s"), tgtGraph);
      
      // revert the transformations
      storyboard.add("_____ forward ______________ backward ___________");
      
      
      GenericGraphPO genericGraphPO = new GenericGraphPO().withModifier(Pattern.BOUND);
      genericGraphPO
      .hasObjects()
      .hasType(Person.class.getName())
      .hasAttrs()
      .hasName(Person.PROPERTY_FIRSTNAME)
      .startCreate()
      .hasName(GraphComponent.PROPERTY_TEXT)
      .allMatches();
      
      storyboard.markCodeStart();
      Pattern reverseRenameFirstNameAttrRule = revertRule(genericGraphPO.getPattern());
      storyboard.addCode();
      
      storyboard.addPattern(genericGraphPO, false);
      
      storyboard.addPattern(reverseRenameFirstNameAttrRule, false);
      
      storyboard.add("<hr/>");
      
      
      renameKindAttrGraphPO = new GenericGraphPO().withModifier(Pattern.BOUND);
      renameKindAttrGraphPO
      .hasObjects()
      .hasType(Relation.class.getName())
      .hasAttrs()
      .hasName(Relation.PROPERTY_KIND)
      .startCreate()
      .hasName(GraphComponent.PROPERTY_TEXT)
      .allMatches();
      
      storyboard.markCodeStart();
      Pattern reverseRenameKindAttrRule = revertRule(renameKindAttrGraphPO.getPattern());
      storyboard.addCode();
      
      storyboard.addPattern(renameKindAttrGraphPO, false);
      
      storyboard.addPattern(reverseRenameKindAttrRule, false);
      
      storyboard.add("<hr/>");
      
      // rename graph--nodes links to parent--gcs links
      renamePersonsLinkGraphPO = new GenericGraphPO().withModifier(Pattern.BOUND);
      renamePersonsLinkGraphPO
      .hasLinks()
      .hasTgtLabel(Person.PROPERTY_GRAPH)
      .hasSrcLabel(Graph.PROPERTY_PERSONS)
      .startCreate()
      .hasTgtLabel(GraphComponent.PROPERTY_PARENT)
      .hasSrcLabel(Graph.PROPERTY_GCS)
      .allMatches();
      
      storyboard.markCodeStart();
      Pattern reverseRenamePersonsLinkRule = revertRule(renamePersonsLinkGraphPO.getPattern());
      storyboard.addCode();
      
      
      storyboard.addPattern(renamePersonsLinkGraphPO, false);
      
      storyboard.addPattern(reverseRenamePersonsLinkRule, false);
      
      storyboard.add("<hr/>");
      
      // rename graph--edges links to parent--gcs links
      renameRelationsLinkGraphPO = new GenericGraphPO().withModifier(Pattern.BOUND);
      renameRelationsLinkGraphPO
      .hasLinks()
      .hasSrcLabel(Relation.PROPERTY_GRAPH)
      .hasTgtLabel(Graph.PROPERTY_RELATIONS)
      .startCreate()
      .hasSrcLabel(GraphComponent.PROPERTY_PARENT)
      .hasTgtLabel(Graph.PROPERTY_GCS)
      .allMatches();
      
      storyboard.markCodeStart();
      Pattern reverseRenameRelationsLinkRule = revertRule(renameRelationsLinkGraphPO.getPattern());
      storyboard.addCode();
      
      
      storyboard.addPattern(renameRelationsLinkGraphPO, false);
      
      storyboard.addPattern(reverseRenameRelationsLinkRule, false);
      
      storyboard.add("<hr/>");
      
      storyboard.addObjectDiagramWith(genGraph.getObjects(), genGraph.getLinks(), genGraph.getObjects().getAttrs());
      
      storyboard.add("<hr/>");
      
      // apply the transformations
      PatternObject<?,?> boundPO = (PatternObject<?,?>) reverseRenameRelationsLinkRule.getElements().first();
      reverseRenameRelationsLinkRule.rebind(boundPO, genGraph);
      reverseRenameRelationsLinkRule.allMatches();
      
      boundPO = (PatternObject<?,?>) reverseRenamePersonsLinkRule.getElements().first();
      reverseRenamePersonsLinkRule.rebind(boundPO, genGraph);
      reverseRenamePersonsLinkRule.allMatches();
      
      boundPO = (PatternObject<?,?>) reverseRenameKindAttrRule.getElements().first();
      reverseRenameKindAttrRule.rebind(boundPO, genGraph);
      reverseRenameKindAttrRule.allMatches();
      

      boundPO = (PatternObject<?,?>) reverseRenameFirstNameAttrRule.getElements().first();
      reverseRenameFirstNameAttrRule.rebind(boundPO, genGraph);
      reverseRenameFirstNameAttrRule.allMatches();
      
      storyboard.addObjectDiagramWith(genGraph.getObjects(), genGraph.getLinks(), genGraph.getObjects().getAttrs());
      
      
      storyboard.add("<hr/>");

      // make the graph specific again
      Graph srcGraph = (Graph) new Generic2Specific().convert(GraphCreator.createIdMap("s"), null, genGraph);
      
      return srcGraph;
   }


   private Pattern<?> revertRule(Pattern<?> forwardRule)
   {
      JsonIdMap origMap = forwardRule.getJsonIdMap();
      origMap.withCreator(PatternCreator.createIdMap("x").getCreators());
      
      JsonIdMap fwdMap = (JsonIdMap) new JsonIdMap().withCreator(origMap.getCreators());

      JsonArray jsonArray = fwdMap.toJsonArray(forwardRule);
      Object firstObject = jsonArray.get(0);
      jsonArray.remove(0);
      jsonArray.add(firstObject);
      
      JsonIdMap bwdMap = (JsonIdMap) new JsonIdMap().withCreator(origMap.getCreators());
      
      PatternElement<?> decode = (PatternElement<?>) bwdMap.decode(jsonArray);
      Pattern<?> backwardRule = (Pattern<?>) decode.getPattern();
      backwardRule.setJsonIdMap(origMap);
      
      // look for attribute constraint with modifer create
      for (PatternElement<?> elem : backwardRule.getElements())
      {
         if (elem instanceof AttributeConstraint)
         {
            AttributeConstraint attrConstraint = (AttributeConstraint) elem;
            
            if (Pattern.CREATE.equals(attrConstraint.getModifier()))
            {
               // find the corresponding search constraint
               AttributeConstraint otherConstraint = 
                     attrConstraint.getSrc().getAttrConstraints().hasAttrName(attrConstraint.getAttrName()).first();
               
               // exchange values
               Object attrConstraintValue = attrConstraint.getTgtValue();
               attrConstraint.setTgtValue(otherConstraint.getTgtValue());
               otherConstraint.setTgtValue(attrConstraintValue);
            }
         }
      }
      
      return backwardRule;
   }


   private Graph simpleMigrationToEvenMoreEvolvedGraphByGenericGraph(Graph origGraph, Storyboard storyboard)
   {
      GenericGraph genGraph = new Specific2Generic()
      .convert(PersonCreator.createIdMap("g1"), origGraph);
      
      // rename name to text attributes
      GenericGraphPO po = new GenericGraphPO();
      po.withCandidates(genGraph);
      po.hasObjects()
      .hasAttrs()
      .hasName(Person.PROPERTY_FIRSTNAME)
      .startCreate()
      .hasName(Person.PROPERTY_TEXT)
      .allMatches();
      
      storyboard.addPattern(po, false);
      
      // replace n.l.e.l.n by n.l.n
      GenericObjectPO edgePO = new GenericGraphPO(genGraph) 
      .hasObjects()
      .hasType(Relation.class.getName());
      
      GenericLinkPO srcLinkPO = edgePO.hasOutgoingLinks().hasTgtLabel(Relation.PROPERTY_SRC);
      
      GenericLinkPO tgtLinkPO = edgePO.hasOutgoingLinks().hasTgtLabel(Relation.PROPERTY_TGT);
      
      GenericObjectPO srcNodePO = srcLinkPO.hasTgt();
      
      GenericObjectPO tgtNodePO = tgtLinkPO.hasTgt();
      
      edgePO.destroy(); 
      
      srcLinkPO.destroy();
      
      GenericLinkSet allMatches = tgtLinkPO.startCreate()
      .hasSrc(srcNodePO)
      .hasSrcLabel(Person.PROPERTY_KNOWS)
      .hasTgtLabel(Person.PROPERTY_KNOWS)
      .allMatches();
      
      storyboard.addPattern(edgePO, false);
      
      // destroy dangling edges
      GenericObjectPO danglingPO = new GenericGraphPO(genGraph)
      .hasObjects()
      .hasType(Relation.class.getName());
      
      danglingPO.destroy();
      
      danglingPO.allMatches();
      
      storyboard.addPattern(danglingPO, false);
      
      Graph tgtGraph = (Graph) new Generic2Specific().convert(PersonCreator.createIdMap("tg"), null, genGraph);
      
      return tgtGraph;

   }

   private Graph simpleMigrationByGenericGraph(Graph origGraph, Storyboard storyboard)
   {
      GenericGraph genGraph = new Specific2Generic()
      .convert(GraphCreator.createIdMap("g"), origGraph);
      
      storyboard.addObjectDiagramWith(genGraph.getObjects(), genGraph.getLinks(), genGraph.getObjects().getAttrs());
      
      renameFirstNameAttrGraphPO = new GenericGraphPO(genGraph);
      renameFirstNameAttrGraphPO
      .hasObjects()
      .hasAttrs()
      .hasName(Person.PROPERTY_FIRSTNAME)
      .startCreate()
      .hasName(GraphComponent.PROPERTY_TEXT)
      .allMatches();
      
      storyboard.addPattern(renameFirstNameAttrGraphPO, false);
      
      
      // rename name to text attributes
      renameKindAttrGraphPO = new GenericGraphPO(genGraph);
      renameKindAttrGraphPO
      .hasObjects()
      .hasAttrs()
      .hasName(Relation.PROPERTY_KIND)
      .startCreate()
      .hasName(GraphComponent.PROPERTY_TEXT)
      .allMatches();
      
      storyboard.addPattern(renameKindAttrGraphPO, false);
      
      
      // rename graph--nodes links to parent--gcs links
      GenericGraphPO renameNodesEdgeGraphPO = new GenericGraphPO(genGraph);
      renameNodesEdgeGraphPO
      .hasLinks()
      .hasTgtLabel(Person.PROPERTY_GRAPH)
      .startCreate()
      .hasTgtLabel(GraphComponent.PROPERTY_PARENT)
      .hasSrcLabel(Graph.PROPERTY_GCS)
      .allMatches();
      
      storyboard.addPattern(renameNodesEdgeGraphPO, false);

      // storyboard.add("<hr/>");
      
      // rename graph--edges links to parent--gcs links
      GenericGraphPO genericGraphPO = new GenericGraphPO(genGraph);
      genericGraphPO
      .hasLinks()
      .hasSrcLabel(Relation.PROPERTY_GRAPH)
      .startCreate()
      .hasSrcLabel(GraphComponent.PROPERTY_PARENT)
      .hasTgtLabel(Graph.PROPERTY_GCS)
      .allMatches();
      
      storyboard.addPattern(genericGraphPO, false);

      storyboard.addObjectDiagramWith(genGraph.getObjects(), genGraph.getLinks(), genGraph.getObjects().getAttrs());
      
      Graph tgtGraph = (Graph) new Generic2Specific().convert(GraphComponentCreator.createIdMap("tg"), null, genGraph);
      
      return tgtGraph;
   }

   private Graph createExampleGraph()
   {
      Graph graph = new Graph();
      
      Person albert = graph.createPersons().withFirstName("Albert");
      Person jens = graph.createPersons().withFirstName("Jens");
      Person gabi = graph.createPersons().withFirstName("Gabi");
      Person nina = graph.createPersons().withFirstName("Nina");
      Person nerdi = graph.createPersons().withFirstName("Nerdi");
      
      graph.createRelations().withKind("knows").withSrc(albert).withTgt(gabi);
      graph.createRelations().withKind("knows").withSrc(albert).withTgt(nina);
      graph.createRelations().withKind("knows").withSrc(albert).withTgt(jens);
      graph.createRelations().withKind("knows").withSrc(jens).withTgt(gabi);
      graph.createRelations().withKind("knows").withSrc(nerdi).withTgt(nerdi);

      return graph;
   }
   
}
