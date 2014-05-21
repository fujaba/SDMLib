package org.sdmlib.examples.m2m;

import javax.management.relation.Relation;

import org.junit.Test;
import org.sdmlib.examples.groupAccount.model.Person;
import org.sdmlib.examples.groupAccount.model.util.PersonCreator;
import org.sdmlib.examples.m2m.model.Graph;
import org.sdmlib.examples.m2m.model.util.GraphCreator;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.objects.Generic2Specific;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.Specific2Generic;
import org.sdmlib.models.objects.util.GenericLinkPO;
import org.sdmlib.models.objects.util.GenericLinkSet;
import org.sdmlib.models.objects.util.GenericObjectPO;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonIdMap;

public class BanfM2MTransformations
{
   private Clazz edgeClazz;
   private Clazz nodeClazz;
   private Clazz graphClazz;

   
   private ClassModel genModel(){
      ClassModel model = new ClassModel("org.sdmlib.examples.m2m.model");
      
      graphClazz = new Clazz("Graph").withClassModel(model);
      
      nodeClazz = new Clazz("Person")
         .withAttribute("firstName", DataType.STRING);
      
      edgeClazz = new Clazz("Relation").withAttribute("kind", DataType.STRING );

      new Association()
      .withTarget(nodeClazz, "persons", Card.MANY)
      .withSource(graphClazz, "graph", Card.ONE);
      
      new Association()
      .withTarget(edgeClazz, "relations", Card.MANY)
      .withSource(graphClazz, "graph", Card.ONE);
      
      new Association()
      .withTarget(nodeClazz, "src", Card.ONE)
      .withSource(edgeClazz, "outEdges", Card.MANY);

      new Association()
      .withTarget(nodeClazz, "tgt", Card.ONE)
      .withSource(edgeClazz, "inEdges", Card.MANY);
      
      model.generate("examples");
      return model;
   }
   
   @Test
   public void testBanGenModel(){
      genModel();
   }
   
   
   @Test
   public void testBanfM2MTransformation()
   {  
      Storyboard storyboard = new Storyboard();
      
      storyboard.add("Class diagram for source model:");
      ClassModel model = genModel();
      
      
      storyboard.addClassDiagram(model);
      
      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("Class diagram for target model:");
      
      model = new ClassModel("org.sdmlib.examples.m2m");
      
      graphClazz = new Clazz("Graph");
      
      Clazz graphComponentClazz = new Clazz("GraphComponent")
      .withAttribute("text", DataType.STRING);
      
      nodeClazz = new Clazz("Person")
      .withSuperClass(graphComponentClazz);
      
      edgeClazz = new Clazz("Relation")
      .withSuperClass(graphComponentClazz);

      new Association()
      .withTarget(graphComponentClazz, "gcs", Card.MANY)
      .withSource(graphClazz, "parent", Card.ONE);
      
      new Association()
      .withTarget(nodeClazz, "src", Card.ONE)
      .withSource(edgeClazz, "outEdges", Card.MANY);

      new Association()
      .withTarget(nodeClazz, "tgt", Card.ONE)
      .withSource(edgeClazz, "inEdges", Card.MANY);
      
      // model.removeAllGeneratedCode("examples", "examples", "examples");
      
      model.generate("examples");
      
      storyboard.addClassDiagram(model);
      
      
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

      model = new ClassModel("org.sdmlib.examples.m2m");
      
      graphClazz = new Clazz("Graph");

      nodeClazz = new Clazz("Person")
      .withAttribute("text", DataType.STRING);

      new Association()
      .withTarget(nodeClazz, "persons", Card.MANY)
      .withSource(graphClazz, "graph", Card.ONE);
      
      new Association()
      .withTarget(nodeClazz, "knows", Card.MANY)
      .withSource(nodeClazz, "knows", Card.MANY);
      
      model.generate("examples");
      
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
      
      storyboard.addLogEntry(R.DONE, "zuendorf", "10.10.2013 10:10:42", 20, 0, "should be published in paper");
      
      storyboard.dumpHTML();
   }
   

   private Graph simpleReverseMigration(Graph tgtGraph, Storyboard storyboard)
   {
      // make the graph generic
      GenericGraph genGraph = new Specific2Generic().convert(GraphComponentCreator.createIdMap("s"), tgtGraph);
      
      // revert the transformations
      storyboard.add("_____ forward ______________ backward ___________");
      
      
      renameFirstNameAttrRule = new org.sdmlib.models.objects.creators.ModelPattern();
      renameFirstNameAttrRule
      .hasElementGenericGraphPO(null)
      .hasObjects()
      .hasType(Person.class.getName())
      .hasAttrs()
      .hasName(Person.PROPERTY_FIRSTNAME)
      .startCreate()
      .hasName(GraphComponent.PROPERTY_TEXT)
      .allMatches();
      
      storyboard.markCodeStart();
      Pattern reverseRenameFirstNameAttrRule = revertRule(renameFirstNameAttrRule);
      storyboard.addCode();
      
      storyboard.add(renameFirstNameAttrRule.dumpDiagram("banfSimpleMigrationRenameFirstNameAttrRule2", false));
      
      storyboard.add(reverseRenameFirstNameAttrRule.dumpDiagram("banfSimpleMigrationReverseRenameFirstNameAttrRule", false));
      
      storyboard.add("<hr/>");
      
      
      renameKindAttrRule = new org.sdmlib.models.objects.creators.ModelPattern();
      renameKindAttrRule
      .hasElementGenericGraphPO(genGraph)
      .hasObjects()
      .hasType(Relation.class.getName())
      .hasAttrs()
      .hasName(Relation.PROPERTY_KIND)
      .startCreate()
      .hasName(GraphComponent.PROPERTY_TEXT)
      .allMatches();
      
      storyboard.markCodeStart();
      Pattern reverseRenameKindAttrRule = revertRule(renameKindAttrRule);
      storyboard.addCode();
      
      storyboard.add(renameKindAttrRule.dumpDiagram("banfSimpleMigrationRenameKindAttrRule2", false));
      
      storyboard.add(reverseRenameKindAttrRule.dumpDiagram("banfSimpleMigrationReverseRenameKindAttrRule", false));
      
      storyboard.add("<hr/>");
      
      // rename graph--nodes links to parent--gcs links
      renamePersonsLinkRule = new org.sdmlib.models.objects.creators.ModelPattern();
      renamePersonsLinkRule
      .hasElementGenericGraphPO(null)
      .hasLinks()
      .hasTgtLabel(Person.PROPERTY_GRAPH)
      .hasSrcLabel(Graph.PROPERTY_PERSONS)
      .startCreate()
      .hasTgtLabel(GraphComponent.PROPERTY_PARENT)
      .hasSrcLabel(Graph.PROPERTY_GCS)
      .allMatches();
      
      storyboard.markCodeStart();
      Pattern reverseRenamePersonsLinkRule = revertRule(renamePersonsLinkRule);
      storyboard.addCode();
      
      
      storyboard.add(renamePersonsLinkRule.dumpDiagram("banfSimpleMigrationRenamePersonsLinkRule2", false));
      
      storyboard.add(reverseRenamePersonsLinkRule.dumpDiagram("banfSimpleMigrationReverseRenamePersonsLinkRule", false));
      
      storyboard.add("<hr/>");
      
      // rename graph--edges links to parent--gcs links
      renameRelationsLinkRule = new org.sdmlib.models.objects.creators.ModelPattern();
      renameRelationsLinkRule
      .hasElementGenericGraphPO(null)
      .hasLinks()
      .hasSrcLabel(Relation.PROPERTY_GRAPH)
      .hasTgtLabel(Graph.PROPERTY_RELATIONS)
      .startCreate()
      .hasSrcLabel(GraphComponent.PROPERTY_PARENT)
      .hasTgtLabel(Graph.PROPERTY_GCS)
      .allMatches();
      
      storyboard.markCodeStart();
      Pattern reverseRenameRelationsLinkRule = revertRule(renameRelationsLinkRule);
      storyboard.addCode();
      
      
      storyboard.add(renameRelationsLinkRule.dumpDiagram("banfSimpleMigrationRenameRelationsLinkRule2", false));
      
      storyboard.add(reverseRenameRelationsLinkRule.dumpDiagram("banfSimpleMigrationReverseRenameRelationsLinkRule", false));
      
      storyboard.add("<hr/>");
      
      // apply the transformations
      PatternObject boundPO = (PatternObject) reverseRenameRelationsLinkRule.getElements().first();
      reverseRenameRelationsLinkRule.rebind(boundPO, genGraph);
      reverseRenameRelationsLinkRule.allMatches();
      
      boundPO = (PatternObject) reverseRenamePersonsLinkRule.getElements().first();
      reverseRenamePersonsLinkRule.rebind(boundPO, genGraph);
      reverseRenamePersonsLinkRule.allMatches();
      
      boundPO = (PatternObject) reverseRenameKindAttrRule.getElements().first();
      reverseRenameKindAttrRule.rebind(boundPO, genGraph);
      reverseRenameKindAttrRule.allMatches();
      
      boundPO = (PatternObject) reverseRenameFirstNameAttrRule.getElements().first();
      reverseRenameFirstNameAttrRule.rebind(boundPO, genGraph);
      reverseRenameFirstNameAttrRule.allMatches();
      
      // storyboard.addObjectDiagramWith(genGraph.getObjects(), genGraph.getLinks(), genGraph.getObjects().getAttrs());
      
      // make the graph specific again
      Graph srcGraph = (Graph) new Generic2Specific().convert(GraphCreator.createIdMap("s"), null, genGraph);
      
      return srcGraph;
   }


   private Pattern revertRule(Pattern forwardRule)
   {
      JsonIdMap origMap = forwardRule.getJsonIdMap();
      // origMap.withCreator(new ModelPatternCreator());
      
      JsonIdMap fwdMap = (JsonIdMap) new JsonIdMap().withCreator(origMap.getCreators());

      JsonArray jsonArray = fwdMap.toJsonArray(forwardRule);
      
      JsonIdMap bwdMap = (JsonIdMap) new JsonIdMap().withCreator(origMap.getCreators());
      
      Pattern backwardRule = (Pattern) bwdMap.decode(jsonArray);
      
      // look for attribute constraint with modifer create
      for (PatternElement<Object> elem : backwardRule.getElements())
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
      new org.sdmlib.models.objects.creators.ModelPattern()
      .hasElementGenericGraphPO(genGraph)
      .hasObjects()
      .hasAttrs()
      .hasName(Person.PROPERTY_FIRSTNAME)
      .startCreate()
      .hasName(Person.PROPERTY_TEXT)
      .allMatches();
      
      storyboard.add(org.sdmlib.models.objects.creators.ModelPattern.lastPattern.dumpDiagram("banfsimpleMigrationToEvenMoreEvolvedGraphByGenericGraph_renameAttr", false));
      
      // replace n.l.e.l.n by n.l.n
      GenericObjectPO edgePO = new org.sdmlib.models.objects.creators.ModelPattern()
      .hasElementGenericGraphPO(genGraph)
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
      
      storyboard.add(org.sdmlib.models.objects.creators.ModelPattern.lastPattern.dumpDiagram("banfsimpleMigrationToEvenMoreEvolvedGraphByGenericGraph_replaceEdges", false));
      
      // destroy dangling edges
      GenericObjectPO danglingPO = new org.sdmlib.models.objects.creators.ModelPattern()
      .hasElementGenericGraphPO(genGraph)
      .hasObjects()
      .hasType(Relation.class.getName());
      
      danglingPO.destroy();
      
      danglingPO.allMatches();
      
      storyboard.add(org.sdmlib.models.objects.creators.ModelPattern.lastPattern.dumpDiagram("banfsimpleMigrationToEvenMoreEvolvedGraphByGenericGraph_removeDanglingEdges", false));
      
      Graph tgtGraph = (Graph) new Generic2Specific().convert(PersonCreator.createIdMap("tg"), null, genGraph);
      
      return tgtGraph;

   }

   private Graph simpleMigrationByGenericGraph(Graph origGraph, Storyboard storyboard)
   {
      GenericGraph genGraph = new Specific2Generic()
      .convert(GraphCreator.createIdMap("g"), origGraph);
      
      storyboard.addObjectDiagramWith(genGraph.getObjects(), genGraph.getLinks(), genGraph.getObjects().getAttrs());
      
      renameFirstNameAttrRule = new org.sdmlib.models.objects.creators.ModelPattern();
      renameFirstNameAttrRule
      .hasElementGenericGraphPO(genGraph)
      .hasObjects()
      .hasAttrs()
      .hasName(Person.PROPERTY_FIRSTNAME)
      .startCreate()
      .hasName(GraphComponent.PROPERTY_TEXT)
      .allMatches();
      
      storyboard.add(renameFirstNameAttrRule.dumpDiagram("banfsimpleMigrationByGenericGraph_renameAttr", false));
      
      
      // rename name to text attributes
      renameKindAttrRule = new org.sdmlib.models.objects.creators.ModelPattern();
      renameKindAttrRule
      .hasElementGenericGraphPO(genGraph)
      .hasObjects()
      .hasAttrs()
      .hasName(Relation.PROPERTY_KIND)
      .startCreate()
      .hasName(GraphComponent.PROPERTY_TEXT)
      .allMatches();
      
      storyboard.add(org.sdmlib.models.objects.creators.ModelPattern.lastPattern.dumpDiagram("banfsimpleMigrationByGenericGraph_renameAttr2", false));
      
      
      // rename graph--nodes links to parent--gcs links
      new org.sdmlib.models.objects.creators.ModelPattern()
      .hasElementGenericGraphPO(genGraph)
      .hasLinks()
      .hasTgtLabel(Person.PROPERTY_GRAPH)
      .startCreate()
      .hasTgtLabel(GraphComponent.PROPERTY_PARENT)
      .hasSrcLabel(Graph.PROPERTY_GCS)
      .allMatches();
      
      storyboard.add(org.sdmlib.models.objects.creators.ModelPattern.lastPattern.dumpDiagram("banfsimpleMigrationByGenericGraph_renameLink1", false));

      // storyboard.add("<hr/>");
      
      // rename graph--edges links to parent--gcs links
      new org.sdmlib.models.objects.creators.ModelPattern()
      .hasElementGenericGraphPO(genGraph)
      .hasLinks()
      .hasSrcLabel(Relation.PROPERTY_GRAPH)
      .startCreate()
      .hasSrcLabel(GraphComponent.PROPERTY_PARENT)
      .hasTgtLabel(Graph.PROPERTY_GCS)
      .allMatches();
      
      storyboard.add(org.sdmlib.models.objects.creators.ModelPattern.lastPattern.dumpDiagram("banfsimpleMigrationByGenericGraph_renameLink2", false));

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
