package org.sdmlib.examples.helloworld;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.codegen.CGUtil;
import org.sdmlib.examples.helloworld.creators.CreatorCreator;
import org.sdmlib.examples.helloworld.creators.EdgePO;
import org.sdmlib.examples.helloworld.creators.EdgeSet;
import org.sdmlib.examples.helloworld.creators.GraphComponentSet;
import org.sdmlib.examples.helloworld.creators.GraphPO;
import org.sdmlib.examples.helloworld.creators.GreetingMessagePO;
import org.sdmlib.examples.helloworld.creators.GreetingPO;
import org.sdmlib.examples.helloworld.creators.ModelPattern;
import org.sdmlib.examples.helloworld.creators.NodePO;
import org.sdmlib.examples.helloworld.creators.NodeSet;
import org.sdmlib.examples.helloworld.creators.PersonPO;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.objects.Generic2Specific;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.Specific2Generic;
import org.sdmlib.models.objects.creators.GenericAttributePO;
import org.sdmlib.models.objects.creators.GenericLinkPO;
import org.sdmlib.models.objects.creators.GenericLinkSet;
import org.sdmlib.models.objects.creators.GenericObjectPO;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.serialization.json.JsonFilter;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.JsonObject;

public class HelloWorldTTC2011
{
   private String systemout;
   private Node n1;
   private Node n6;
   private Node n7;
   private Node n8;
   private Node n3;


   @Test
   public void testConstantTransformation1()
   {
      Scenario scenario = new Scenario("examples", "TTC2011HelloWorldConstantTransformation1")
      .with("A constant transformation that creates a Greeting object");
      
      
      //==========================================================================
      
      scenario.add("Create class model and generate implementation:");
      
      ClassModel model = new ClassModel();
      
      Clazz greetingClazz = new Clazz("org.sdmlib.examples.helloworld.Greeting")
      .withAttribute("text", "String");
      
      // model.removeAllGeneratedCode("examples", "examples", "examples");
      
      model.generate("examples", "examples");
      
      scenario.addImage(model.dumpClassDiag("TTC2011HelloWorldConstantTransformation1ClassDiag"));
      
      
      //==========================================================================
      
      scenario.add("The code that builds and runs the transformation / pattern looks like: ");
      scenario.markCodeStart();
      ModelPattern p = new ModelPattern().startCreate();
      
      PatternObject greetingPO = p.hasElementGreetingPO()
                                  .hasText("Hello World");
      scenario.addCode("examples");
      
      scenario.add(p.dumpDiagram("TTC2011HelloWorldConstantTransformation1", false));
      
      Assert.assertTrue("Constant transformation has no match", p.getHasMatch());
      
      scenario.add("At runtime the object structure for the pattern and for the hostgraph looks like: ");
      
      scenario.add(p.dumpDiagram("TTC2011HelloWorldConstantTransformation1AfterTransformation"));
      
      
      //==========================================================================
      
      scenario.add("For completeness just the host graph:");

      scenario.addObjectDiag(p.getJsonIdMap(), greetingPO.getCurrentMatch());
      
      int noOfMatches = p.allMatches();
      
      System.out.println("TTC2011HelloWorldConstantTransformation1 number of matches is " + noOfMatches);
      
      
      //==========================================================================
      
      scenario.add("For fairness, the java code that does this transformation looks like: ");
      scenario.markCodeStart();
      Greeting greeting = new Greeting()
      .withText("Hello World");
      scenario.addCode("examples");
      
      
      scenario.dumpHTML();
   }


   //==========================================================================
   @Test
   public void testConstantTransformation2()
   {
      Scenario scenario = new Scenario("examples", "TTC2011HelloWorldConstantTransformation2WithReferences")
      .with("A constant transformation that creates a Greeting object structure with references");
      
      
      //==========================================================================
      
      scenario.add("Create class model and generate implementation:");
      
      ClassModel model = new ClassModel();
      
      Clazz greetingClazz = new Clazz("org.sdmlib.examples.helloworld.Greeting");
      
      Clazz greetingMessageClazz = new Clazz("org.sdmlib.examples.helloworld.GreetingMessage")
      .withAttribute("text", "String");
      
      Clazz personClazz = new Clazz("org.sdmlib.examples.helloworld.Person")
      .withAttribute("name", "String");
      
      new Association()
      .withTarget(greetingMessageClazz, "greetingMessage", Role.ONE)
      .withSource(greetingClazz, "greeting", Role.ONE);
      
      new Association()
      .withTarget(personClazz, "person", Role.ONE)
      .withSource(greetingClazz, "greeting", Role.ONE);
      
      // model.removeAllGeneratedCode("examples", "examples", "examples");
      
      model.generate("examples", "examples");
      
      scenario.addImage(model.dumpClassDiag("TTC2011HelloWorldConstantTransformation2ClassDiag"));
      
      
      //==========================================================================
      
      scenario.add("The code that builds and runs the transformation / pattern looks like: ");
      scenario.markCodeStart();
      ModelPattern p = new ModelPattern().startCreate();
      
      GreetingPO greetingPO = p.hasElementGreetingPO();
      
      GreetingMessagePO greetingMessagePO = greetingPO.hasGreetingMessage() 
      .hasText("Hello");
      
      PersonPO personPO = greetingPO.hasPerson()
      .hasName("TTC Participants");
      scenario.addCode("examples");
      
      scenario.add(p.dumpDiagram("TTC2011HelloWorldConstantTransformation2", false));
      
      Assert.assertTrue("Constant transformation has no match", p.getHasMatch());
      
      scenario.add("At runtime the object structure for the pattern and for the hostgraph looks like: ");
      
      // scenario.addObjectDiag(p.getJsonIdMap(), greetingPO);
      
      scenario.add(p.dumpDiagram("TTC2011HelloWorldConstantTransformation2AfterTransformation"));
      
      
      //==========================================================================
      
      scenario.add("For completeness just the host graph:");

      scenario.addObjectDiag(p.getJsonIdMap(), greetingPO.getCurrentMatch());
      
      //==========================================================================
      
      scenario.add("For fairness, the java code that does this transformation looks like: ");
      
      scenario.markCodeStart();
      Greeting greeting = new Greeting();
      
      GreetingMessage greetingMessage = greeting.createGreetingMessage()
      .withText("Hello");
      
      Person person = greeting.createPerson()
      .withName("TTC Participants");
      scenario.addCode("examples");
      
      scenario.dumpHTML();
   }
      
      
   //==========================================================================
   @Test
   public void testModelToTextTransformation()
   {  
      Scenario scenario = new Scenario("examples", "TTC2011HelloWorldModelToText")
      .with("For model to text transformation we provide a simple template mechanism. ");
      
      scenario.add("The model transformation that builds our object model looks like: ");
      scenario.markCodeStart();
      ModelPattern p = new ModelPattern().startCreate();
      
      GreetingPO greetingPO = p.hasElementGreetingPO();
      
      GreetingMessagePO greetingMessagePO = greetingPO.hasGreetingMessage() 
      .hasText("Hello");
      
      PersonPO personPO = greetingPO.hasPerson()
      .hasName("TTC Participants");
      scenario.addCode("examples");
      
      scenario.add("The created object model looks like: ");
      
      scenario.addObjectDiag(p.getJsonIdMap(), greetingPO.getCurrentMatch());
      
      scenario.add("The model to text transfromation template mechanism is used like this: ");
      
      scenario.markCodeStart();
      StringBuilder text = new StringBuilder("message name");
      
      CGUtil.replaceAll(text, 
         "message", greetingMessagePO.getText(),
         "name", personPO.getName());
      
      systemout = text.toString();
      scenario.addCode("examples");
      
      scenario.add("systemout: \n" + systemout);
      
      //==========================================================================
      
      scenario.add("Alternatively, plain java code that does this model to text transformation looks like: ");
      
      scenario.markCodeStart();
      systemout = greetingMessagePO.getText() + " " + personPO.getName();
      scenario.addCode("examples");
      
      scenario.dumpHTML();  
   }
   
   
   //==========================================================================
   @Test
   public void testTTC2011HelloWorldCountNumberOfNodes()
   {  
      Scenario scenario = new Scenario("examples");
      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("Simple Graph application classes: ");
      
      ClassModel model = new ClassModel();
      
      Clazz graphClazz = new Clazz("org.sdmlib.examples.helloworld.Graph");
      
      Clazz edgeClazz = new Clazz("org.sdmlib.examples.helloworld.Edge")
      .withAttribute("name", "String");

      Clazz nodeClazz = new Clazz("org.sdmlib.examples.helloworld.Node")
      .withAttribute("name", "String");

      new Association()
      .withTarget(nodeClazz, "nodes", Role.MANY)
      .withSource(graphClazz, "graph", Role.ONE);
      
      new Association()
      .withTarget(edgeClazz, "edges", Role.MANY)
      .withSource(graphClazz, "graph", Role.ONE);
      
      new Association()
      .withTarget(nodeClazz, "src", Role.ONE)
      .withSource(edgeClazz, "outEdges", Role.MANY);

      new Association()
      .withTarget(nodeClazz, "tgt", Role.ONE)
      .withSource(edgeClazz, "inEdges", Role.MANY);

      // // model.removeAllGeneratedCode("examples", "examples", "examples");
      
      model.generate("examples", "examples");
      
      scenario.addImage(model.dumpClassDiag("TTC2011HelloWorldSimpleGraphClassDiag"));
      
      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("create example graph: ");
      
      Graph graph = createExampleGraph();
      
      scenario.addObjectDiag(CreatorCreator.createIdMap("hg"), graph);
      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("<h2>Count nodes in graph direct: </h2>");
      
      countNodesInJava(graph);
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "countNodesInJava(Graph)"));
      
      scenario.add("systemout: " + systemout);
      
      
      //==========================================================================
      
      scenario.add("Count nodes in graph per pattern: ");
      
      countNodesPerPattern(graph);
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "countNodesPerPattern(Graph)"));
      
      // scenario.addObjectDiag(p.getJsonIdMap(), p.getElements().iterator().next());
      
      scenario.add(ModelPattern.lastPattern.dumpDiagram("countNodesPerPatternRule", false));

      scenario.add(ModelPattern.lastPattern.dumpDiagram("countNodesPerPatternRuleWithMatch"));
      
      scenario.add("systemout: " + systemout);
      
      
      //==========================================================================
      
      scenario.add("Retrieve set of matching nodes per pattern and count its elements: ");
      
      countNodesPerNodeSet(graph);
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "countNodesPerNodeSet(Graph)"));

      scenario.add(ModelPattern.lastPattern.dumpDiagram("countNodesPerNodeSet", false));
      
      scenario.add(ModelPattern.lastPattern.dumpDiagram("countNodesPerNodeSetWithMatch"));
      
      scenario.add("systemout: " + systemout);
      
      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("<h2>Retrieve set of looping edges per pattern and count its elements: </h2>");
      
      countLoopingEdgesPerPattern(graph);
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "countLoopingEdgesPerPattern(Graph)"));
      
      scenario.add(ModelPattern.lastPattern.dumpDiagram("countLoopingEdgesPerPattern", false));
      
      scenario.add(ModelPattern.lastPattern.dumpDiagram("countLoopingEdgesPerPatternWithMatch"));
      
      scenario.add("systemout: " + systemout);
      
      scenario.add("For comparison a Java solution: ");
      
      countLoopingEdgesInJava(graph);
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "countLoopingEdgesInJava(Graph)"));
      
      scenario.add("systemout: " + systemout);
      
      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("<h2>Retrieve isolated nodes and count them: </h2>");
      
      countIsolatedNodesPerPattern(graph);
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "countIsolatedNodesPerPattern(Graph)"));
      
      scenario.add(ModelPattern.lastPattern.dumpDiagram("countIsolatedNodesPerPattern", false));

      scenario.add(ModelPattern.lastPattern.dumpDiagram("countIsolatedNodesPerPatternWithMatch", true));

      scenario.add("systemout: " + systemout);
      
      scenario.add("For comparison a Java solution: ");
      
      countIsolatedNodesInJava(graph);
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "countIsolatedNodesInJava(Graph)"));
      
      scenario.add("systemout: " + systemout);
      
      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("<h2>Count circles of three nodes: </h2>");
      
      countCirclesOfThreeNodesPerPattern(graph);
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "countCirclesOfThreeNodesPerPattern(Graph)"));
      
      scenario.add(ModelPattern.lastPattern.dumpDiagram("countCirclesOfThreeNodesPerPattern", false));

      scenario.add(ModelPattern.lastPattern.dumpDiagram("countCirclesOfThreeNodesPerPatternWithMatch", true));

      scenario.add("systemout: " + systemout);
      
      scenario.add("If you want to print the matched nodes for each circle, you need to iterate through the matches: ");
      
      countCirclesOfThreeNodesPerPatternReportMatches(graph);
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "countCirclesOfThreeNodesPerPatternReportMatches(Graph)"));
      
      scenario.add(ModelPattern.lastPattern.dumpDiagram("countCirclesOfThreeNodesPerPatternReportMatches", false));

      scenario.add(ModelPattern.lastPattern.dumpDiagram("countCirclesOfThreeNodesPerPatternReportMatchesWithMatch", true));

      scenario.add("systemout: " + systemout);
      
      scenario.add("Just for comparison, a plain java implementation: ");
      
      countCirclesOfThreeNodesInJava(graph);
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "countCirclesOfThreeNodesInJava(Graph)"));
      
      scenario.add("systemout: " + systemout);
      
      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("<h2>Count dangling edges per pattern: </h2>");
      
      countDanglingEdgesPerPattern(graph);
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "countDanglingEdgesPerPattern(Graph)"));
      
      scenario.add(ModelPattern.lastPattern.dumpDiagram("countDanglingEdgesPerPattern", false));

      scenario.add(ModelPattern.lastPattern.dumpDiagram("countDanglingEdgesPerPatternWithMatch", true));

      scenario.add("systemout: " + systemout);
      
      scenario.add("Count dangling edges in Java: ");
      
      countDanglingEdgesInJava(graph);
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "countDanglingEdgesInJava(Graph)"));
      
      scenario.add("systemout: " + systemout);
      
      
      
      
      scenario.dumpHTML();
   }


   private void sleep1000()
   {
      try
      {
         Thread.sleep(1000);
      }
      catch (InterruptedException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   
   //==========================================================================
   @Test
   public void testTTC2011HelloWorldReverseEdges()
   {  
      Scenario scenario = new Scenario("examples");
      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("create example graph: ");
      
      Graph graph = createExampleGraph();
      
      scenario.addObjectDiag(CreatorCreator.createIdMap("hg"), graph);
      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("<h2>Reverse Edges per pattern: </h2>");
      
      reverseEdgesPerPattern(graph);
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "reverseEdgesPerPattern(Graph)"));
      
      scenario.add(ModelPattern.lastPattern.dumpDiagram("reverseEdgesPerPattern", false));

      scenario.add(ModelPattern.lastPattern.dumpDiagram("reverseEdgesPerPatternWithMatch", true));

      
      scenario.add("Result graph: ");
      
      scenario.addObjectDiag(CreatorCreator.createIdMap("hg"), graph);
      
      scenario.add(systemout);
      
      //==========================================================================
      
      scenario.add("<h2>Reverse Edges (back) in Java: </h2>: ");
    
      reverseEdgesInJava(graph);
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "reverseEdgesInJava(Graph)"));
      
      scenario.add("Result graph: ");
      
      scenario.addObjectDiag(CreatorCreator.createIdMap("hg"), graph);
      
      scenario.add(systemout);
      

      scenario.dumpHTML();
   }


   
   //==========================================================================
   @Test
   public void testTTC2011SimpleMigration()
   {  
      Scenario scenario = new Scenario("examples");
      
      scenario.add("<hr/>");
      scenario.add("Source model:");
      
      ClassModel model = new ClassModel();
      
      Clazz graphClazz = new Clazz("org.sdmlib.examples.helloworld.Graph");
      
      Clazz edgeClazz = new Clazz("org.sdmlib.examples.helloworld.Edge")
      .withAttribute("name", "String");

      Clazz nodeClazz = new Clazz("org.sdmlib.examples.helloworld.Node")
      .withAttribute("name", "String");

      new Association()
      .withTarget(nodeClazz, "nodes", Role.MANY)
      .withSource(graphClazz, "graph", Role.ONE);
      
      new Association()
      .withTarget(edgeClazz, "edges", Role.MANY)
      .withSource(graphClazz, "graph", Role.ONE);
      
      new Association()
      .withTarget(nodeClazz, "src", Role.ONE)
      .withSource(edgeClazz, "outEdges", Role.MANY);

      new Association()
      .withTarget(nodeClazz, "tgt", Role.ONE)
      .withSource(edgeClazz, "inEdges", Role.MANY);
      
      // model.removeAllGeneratedCode("examples", "examples", "examples");
      
      model.generate("examples", "examples");
      
      scenario.addImage(model.dumpClassDiag("TTC2011HelloWorldSimpleMigrationSourceClassDiag"));
      
      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("Target model:");
      
      model = new ClassModel();
      
      graphClazz = new Clazz("org.sdmlib.examples.helloworld.Graph");
      
      Clazz graphComponentClazz = new Clazz("org.sdmlib.examples.helloworld.GraphComponent")
      .withAttribute("text", "String");
      
      edgeClazz = new Clazz("org.sdmlib.examples.helloworld.Edge")
      .withSuperClass(graphComponentClazz);

      nodeClazz = new Clazz("org.sdmlib.examples.helloworld.Node")
      .withSuperClass(graphComponentClazz);

      new Association()
      .withTarget(graphComponentClazz, "gcs", Role.MANY)
      .withSource(graphClazz, "parent", Role.ONE);
      
      new Association()
      .withTarget(nodeClazz, "src", Role.ONE)
      .withSource(edgeClazz, "outEdges", Role.MANY);

      new Association()
      .withTarget(nodeClazz, "tgt", Role.ONE)
      .withSource(edgeClazz, "inEdges", Role.MANY);
      
      // model.removeAllGeneratedCode("examples", "examples", "examples");
      
      model.generate("examples", "examples");
      
      scenario.addImage(model.dumpClassDiag("TTC2011HelloWorldSimpleMigrationTargetClassDiag"));
      
      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("Migration extension:");
      
      model = new ClassModel();
      
      nodeClazz = new Clazz("org.sdmlib.examples.helloworld.Node");

      new Association()
      .withTarget(nodeClazz, "copy", Role.ONE)
      .withSource(nodeClazz, "orig", Role.ONE);
      
      model.generate("examples", "examples");
      
      scenario.addImage(model.dumpClassDiag("TTC2011HelloWorldSimpleMigrationExtensionClassDiag"));
      

      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("Create example source graph: ");
      
      Graph graph = createExampleGraph();
      
      scenario.addObjectDiag(CreatorCreator.createIdMap("hg"), graph);
      
      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("<h2>Migrate per pattern: </h2>");
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "simpleMigrationPerPattern(Graph,Scenario)"));
      
      simpleMigrationPerPattern(graph, scenario);
      
      scenario.add("Result graph: ");
      
      scenario.addObjectDiag(CreatorCreator.createIdMap("hg"), graph);
      
      scenario.add(systemout);
      
      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("<h2>Migrate in Java: </h2>");
      
      graph = createExampleGraph();
      
      simpleMigrationInJava(graph);
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "simpleMigrationInJava(Graph)"));
      
      scenario.add("Result graph: ");
      
      scenario.addObjectDiag(CreatorCreator.createIdMap("hg"), graph);
      
      
      scenario.add(systemout);

      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("<h2>Migrate using Serialisation / Clone : </h2>");
      
      graph = createExampleGraph();
      
      graph = simpleMigrationByCloning(graph);
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "simpleMigrationByCloning(Graph)"));
      
      scenario.add("Result graph: ");
      
      scenario.addObjectDiag(CreatorCreator.createIdMap("hg"), graph);
      
      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("<h2>Migrate using JSON Array repräsentation : </h2>");
      
      graph = createExampleGraph();
      
      graph = simpleMigrationByJsonArray(graph);
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "simpleMigrationByJsonArray(Graph)"));
      
      scenario.add("Result graph: ");
      
      scenario.addObjectDiag(CreatorCreator.createIdMap("hg"), graph);
      
      
      //==========================================================================

      scenario.add("<hr/>");
      scenario.add("<h2>Migrate using Generic Graph representation : </h2>");

      graph = createExampleGraph();

      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "simpleMigrationByGenericGraph(Graph,Scenario)"));
      
      Graph tgtGraph = simpleMigrationByGenericGraph(graph, scenario);

      scenario.add("Result graph: ");

      scenario.addObjectDiag(CreatorCreator.createIdMap("tg"), tgtGraph);

   
      //==========================================================================

      scenario.add("<hr/>");
      scenario.add("<h2>Even more evolved graph model : </h2>");

      model = new ClassModel();
      
      graphClazz = new Clazz("org.sdmlib.examples.helloworld.Graph");

      nodeClazz = new Clazz("org.sdmlib.examples.helloworld.Node")
      .withAttribute("text", "String");

      new Association()
      .withTarget(nodeClazz, "nodes", Role.MANY)
      .withSource(graphClazz, "graph", Role.ONE);
      
      new Association()
      .withTarget(nodeClazz, "linksTo", Role.MANY)
      .withSource(nodeClazz, "linksFrom", Role.MANY);
      
      model.generate("examples", "examples");
      
      scenario.addImage(model.dumpClassDiag("TTC2011HelloWorldSimpleMigrationEvenMoreEvolvedDiag"));
      
      graph = createExampleGraph();
      
      JsonIdMap createIdMap = CreatorCreator.createIdMap("eg");
      
      scenario.addObjectDiag(createIdMap, graph);
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "simpleMigrationToEvenMoreEvolvedGraphByGenericGraph(Graph,Scenario)"));
      
      tgtGraph = simpleMigrationToEvenMoreEvolvedGraphByGenericGraph(graph, scenario);

      scenario.add("Result graph: ");

      scenario.addObjectDiag(CreatorCreator.createIdMap("tg"), tgtGraph);

      
      scenario.dumpHTML();
   }
   
   
   //==========================================================================
   @Test
   public void testTTC2011DeleteNodeWithSpecificName()
   {  
      Scenario scenario = new Scenario("examples");
      
      scenario.add("<hr/>");
      scenario.add("Delete node with name n1 and its incidemnt edges.");
      
      scenario.add("Start graph:");
      
      Graph graph = createExampleGraph();
      
      // rename node n3 to make case more interesting
      n3.withName("n1");
      
      JsonIdMap createIdMap = CreatorCreator.createIdMap("sg");
      
      scenario.addObjectDiag(createIdMap, graph);
      
      scenario.add("Transformation:");
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "deleteNodeWithNameN1(Graph)"));

      deleteNodeWithNameN1(graph);
      
      scenario.add(ModelPattern.lastPattern.dumpDiagram("deleteNodeWithNameN1_nodeN1", false));
      
      scenario.add("Result graph:");
      
      scenario.addObjectDiag(createIdMap, graph);
      
      
      scenario.dumpHTML();
   }


 //==========================================================================
   @Test
   public void testTTC2011InsertTransitiveEdges()
   {  
      Scenario scenario = new Scenario("examples");
      
      scenario.add("<hr/>");
      scenario.add("Insert transitive edges.");
      
      scenario.add("Start graph:");
      
      Graph graph = createExampleGraph();
      
      JsonIdMap createIdMap = CreatorCreator.createIdMap("sg");
      
      scenario.addObjectDiag(createIdMap, graph);
      
      int noOfMatches = insertTransitiveEdges(graph);

      scenario.add("Transformation:");
      
      scenario.add(scenario.getMethodText("examples", this.getClass().getName(), "insertTransitiveEdges(Graph)"));

      scenario.add(ModelPattern.lastPattern.dumpDiagram("insertTransitiveEdges", false));
      
      scenario.add("Transformation with matches:");
      
      scenario.add(ModelPattern.lastPattern.dumpDiagram("insertTransitiveEdgesWithMatch", true));
      
      scenario.add("We have inserted " + noOfMatches + " new transitive edges. Result graph:");
      
      scenario.addObjectDiag(createIdMap, graph);
      
      scenario.add("For comparison a Java solution: ");
      
      
      
      scenario.dumpHTML();
   }

   
   private int insertTransitiveEdges(Graph graph)
   {
      ModelPattern p = new ModelPattern();
      
      GraphPO graphPO = p.hasElementGraphPO(graph).withPatternObjectName("graphPO2");
      
      EdgePO firstEdgePO = graphPO.hasEdges();

      EdgePO secondEdgePO = graphPO.hasEdges();
      
      NodePO joinNodePO = firstEdgePO.hasTgt();
      
      secondEdgePO.hasSrc(joinNodePO);
      
      NodePO startNodePO = firstEdgePO.hasSrc();
      
      NodePO endNodePO = secondEdgePO.hasTgt();
      
      startNodePO.startNAC().hasOutEdges().hasTgt(endNodePO).endNAC();
      
      p.startCreate();
      
      startNodePO.hasOutEdges().hasTgt(endNodePO);
      
      return p.allMatches();
}

   private void deleteNodeWithNameN1(Graph graph)
   {
      // find node
      NodePO nodeN1PO = new ModelPattern()
      .hasElementGraphPO(graph)
      .hasNodes()
      .hasName("n1");
      
      // destroy all leaving edges
      nodeN1PO.startSubPattern()
      .hasOutEdges()
      .destroy()
      .endSubPattern()
      .allMatches();

      // destroy all incoming edges
      nodeN1PO.startSubPattern()
      .hasInEdges()
      .destroy()
      .endSubPattern()
      .allMatches();
      
      // destroy the node
      nodeN1PO.destroy().allMatches();
   }


   private Graph simpleMigrationToEvenMoreEvolvedGraphByGenericGraph(Graph origGraph, Scenario scenario)
   {
      GenericGraph genGraph = new Specific2Generic().convert(CreatorCreator.createIdMap("g1"), origGraph);
      
      // rename name to text attributes
      new org.sdmlib.models.objects.creators.ModelPattern()
      .hasElementGenericGraphPO(genGraph)
      .hasObjects()
      .hasAttrs()
      .hasName(Node.PROPERTY_NAME)
      .startCreate()
      .hasName(Node.PROPERTY_TEXT)
      .allMatches();
      
      scenario.add(org.sdmlib.models.objects.creators.ModelPattern.lastPattern.dumpDiagram("simpleMigrationToEvenMoreEvolvedGraphByGenericGraph_renameAttr", false));
      
      // replace n.l.e.l.n by n.l.n
      GenericObjectPO edgePO = new org.sdmlib.models.objects.creators.ModelPattern()
      .hasElementGenericGraphPO(genGraph)
      .hasObjects()
      .hasType(Edge.class.getName());
      
      GenericLinkPO srcLinkPO = edgePO.hasOutgoingLinks().hasTgtLabel(Edge.PROPERTY_SRC);
      
      GenericLinkPO tgtLinkPO = edgePO.hasOutgoingLinks().hasTgtLabel(Edge.PROPERTY_TGT);
      
      GenericObjectPO srcNodePO = srcLinkPO.hasTgt();
      
      GenericObjectPO tgtNodePO = tgtLinkPO.hasTgt();
      
      edgePO.destroy(); 
      
      srcLinkPO.destroy();
      
      GenericLinkSet allMatches = tgtLinkPO.startCreate()
      .hasSrc(srcNodePO)
      .hasSrcLabel(Node.PROPERTY_LINKSFROM)
      .hasTgtLabel(Node.PROPERTY_LINKSTO)
      .allMatches();
      
      scenario.add(org.sdmlib.models.objects.creators.ModelPattern.lastPattern.dumpDiagram("simpleMigrationToEvenMoreEvolvedGraphByGenericGraph_replaceEdges", false));
      
      // destroy dangling edges
      GenericObjectPO danglingPO = new org.sdmlib.models.objects.creators.ModelPattern()
      .hasElementGenericGraphPO(genGraph)
      .hasObjects()
      .hasType(Edge.class.getName());
      
      danglingPO.destroy();
      
      danglingPO.allMatches();
      
      scenario.add(org.sdmlib.models.objects.creators.ModelPattern.lastPattern.dumpDiagram("simpleMigrationToEvenMoreEvolvedGraphByGenericGraph_removeDanglingEdges", false));
      
      Graph tgtGraph = (Graph) new Generic2Specific().convert(CreatorCreator.createIdMap("tg"), null, genGraph);
      
      return tgtGraph;
   }
   
   
   private Graph simpleMigrationByGenericGraph(Graph origGraph, Scenario scenario)
   {
      GenericGraph genGraph = new Specific2Generic().convert(CreatorCreator.createIdMap("g1"), origGraph);
      
      scenario.addObjectDiag(org.sdmlib.models.objects.creators.CreatorCreator.createIdMap("gg"), genGraph);
      
      // rename name to text attributes
      new org.sdmlib.models.objects.creators.ModelPattern()
      .hasElementGenericGraphPO(genGraph)
      .hasObjects()
      .hasAttrs()
      .hasName(Node.PROPERTY_NAME)
      .startCreate()
      .hasName(Node.PROPERTY_TEXT)
      .allMatches();
      
      scenario.add(org.sdmlib.models.objects.creators.ModelPattern.lastPattern.dumpDiagram("simpleMigrationByGenericGraph_renameAttr", false));
      
      // rename graph--nodes links to parent--gcs links
      new org.sdmlib.models.objects.creators.ModelPattern()
      .hasElementGenericGraphPO(genGraph)
      .hasLinks()
      .hasTgtLabel(Node.PROPERTY_GRAPH)
      .startCreate()
      .hasTgtLabel(Node.PROPERTY_PARENT)
      .hasSrcLabel(Graph.PROPERTY_GCS)
      .allMatches();
      
      scenario.add(org.sdmlib.models.objects.creators.ModelPattern.lastPattern.dumpDiagram("simpleMigrationByGenericGraph_renameLink", false));

      scenario.addObjectDiag(org.sdmlib.models.objects.creators.CreatorCreator.createIdMap("gg"), genGraph);
      
      Graph tgtGraph = (Graph) new Generic2Specific().convert(CreatorCreator.createIdMap("tg"), null, genGraph);
      
      return tgtGraph;
   }
   
   
   private Graph simpleMigrationByJsonArray(Graph graph)
   {
      JsonIdMap idMap = CreatorCreator.createIdMap("hg");
      
      JsonArray jsonArray = idMap.toJsonArray(graph);
      
      for (int i = 0; i < jsonArray.size(); i++)
      {
         JsonObject jsonObject = jsonArray.getJSONObject(i);
         
         String className = jsonObject.getString(JsonIdMap.CLASS);
         
         if (Graph.class.getName().equals(className))
         {
            JsonObject jsonProps = jsonObject.getJsonObject(JsonIdMap.JSON_PROPS);
            
            // migrate nodes and edges to gcs
            JsonArray gcsArray = jsonProps.getJsonArray(Graph.PROPERTY_NODES);
            
            gcsArray.addAll(Arrays.asList(jsonProps.getJsonArray(Graph.PROPERTY_EDGES).toArray()));
            
            jsonProps.put(Graph.PROPERTY_GCS, gcsArray);
            
            jsonProps.remove(Graph.PROPERTY_NODES);
            
            jsonProps.remove(Graph.PROPERTY_EDGES);
         }
         else if (Node.class.getName().equals(className))
         {
            JsonObject jsonProps = jsonObject.getJsonObject(JsonIdMap.JSON_PROPS);
            
            // migrate name to text
            jsonProps.put(Node.PROPERTY_TEXT, jsonProps.get(Node.PROPERTY_NAME));
            
            jsonProps.remove(Node.PROPERTY_NAME);
            
            // migrate graph to parent
            jsonProps.put(Node.PROPERTY_PARENT, jsonProps.get(Node.PROPERTY_GRAPH));
            
            jsonProps.remove(Node.PROPERTY_GRAPH);
         }
         else if (Edge.class.getName().equals(className))
         {
            JsonObject jsonProps = jsonObject.getJsonObject(JsonIdMap.JSON_PROPS);

            // migrate name to text
            jsonProps.put(Node.PROPERTY_TEXT, jsonProps.get(Node.PROPERTY_NAME));

            jsonProps.remove(Node.PROPERTY_NAME);

            // migrate graph to parent
            jsonProps.put(Node.PROPERTY_PARENT, jsonProps.get(Node.PROPERTY_GRAPH));

            jsonProps.remove(Node.PROPERTY_GRAPH);
         }
         
      }
      
      Graph copyGraph = (Graph) CreatorCreator.createIdMap("hg").readJson(jsonArray);
      
      return copyGraph;
   }


   private Graph simpleMigrationByCloning(Graph graph)
   {
      JsonArray jsonArray = CreatorCreator.createIdMap("hg").toJsonArray(graph);
      
      Graph copyGraph = (Graph) CreatorCreator.createIdMap("hg").readJson(jsonArray);
      
      GraphComponentSet gcs = new GraphComponentSet();
      gcs.addAll(copyGraph.getNodes());
      gcs.addAll(copyGraph.getEdges());
      
      for (GraphComponent comp : gcs)
      {
         comp.setParent(comp.getGraph());
         comp.setGraph(null);
         
         comp.setText(comp.getName());
         comp.setName(null);
      }

      return copyGraph;
   }


   private void simpleMigrationInJava(Graph origGraph)
   {
      int noOfMatches = 0;
      
      if (origGraph != null)
      {
         Graph copyGraph = new Graph();
         
         // migrate nodes
         for (Node origNode : origGraph.getNodes())
         {
            Node copyNode = (Node) copyGraph.createGcsNode()
                  .withOrig(origNode)
                  .withText(origNode.getName());
            
            noOfMatches++;
         }

         systemout = "Number of migrated nodes: " + noOfMatches;
         
         // migrate edges
         for (Edge origEdge : origGraph.getEdges())
         {
            Edge copyEdge = (Edge) copyGraph.createGcsEdge()
                  .withText(origEdge.getName());
            
            Node origSrcNode = origEdge.getSrc();
            
            if (origSrcNode != null)
            {
               copyEdge.setSrc(origSrcNode.getCopy());
            }

            Node origTgtNode = origEdge.getTgt();
            
            if (origTgtNode != null)
            {
               copyEdge.setTgt(origTgtNode.getCopy());
            }
         }
      }
      
   }


   private void simpleMigrationPerPattern(Graph graph, Scenario scenario)
   {
      //==========================================================================
      ModelPattern migrateGraph = new ModelPattern();
      
      GraphPO srcGraphPO = migrateGraph.hasElementGraphPO(graph);
      
      migrateGraph.startCreate();
      
      GraphPO tgtGraphPO = migrateGraph.hasElementGraphPO();
      
      scenario.add(migrateGraph.dumpDiagram("simpleMigrationPerPattern_migrateGraph", false));
      
      //==========================================================================
      int noOfMatches = 0;
      
      ModelPattern migrateNodesPattern = new ModelPattern();
      
      srcGraphPO = migrateNodesPattern.hasElementGraphPO(graph);
      
      NodePO srcNodePO = srcGraphPO.hasNodes();
      
      tgtGraphPO = migrateNodesPattern.hasElementGraphPO(tgtGraphPO.getCurrentMatch());
      
      migrateNodesPattern.startCreate();
      
      NodePO tgtNodePO = tgtGraphPO.hasGcsNode();
      
      tgtNodePO.hasOrig(srcNodePO);
      
      while (migrateNodesPattern.getHasMatch())
      {
         tgtNodePO.withText(srcNodePO.getName());
         
         noOfMatches++;
         
         migrateNodesPattern.findNextMatch();
      }
      
      systemout = "Number of migrated nodes: " + noOfMatches;
      
      scenario.add(migrateNodesPattern.dumpDiagram("simpleMigrationPerPattern_migrateNodesPattern", false));
      
      //==========================================================================
      noOfMatches = 0;
      
      ModelPattern migrateEdgesPattern = new ModelPattern();
      
      srcGraphPO = migrateEdgesPattern.hasElementGraphPO(graph);
      
      EdgePO srcEdgePO = srcGraphPO.hasEdges();
      
      tgtGraphPO = migrateEdgesPattern.hasElementGraphPO(tgtGraphPO.getCurrentMatch());
      
      migrateEdgesPattern.startCreate();
      
      EdgePO tgtEdgePO = tgtGraphPO.hasGcsEdge();
      
      ModelPattern migrateSrcHalfLinkPattern = null;
      ModelPattern migrateTgtHalfLinkPattern = null;
      
      while (migrateEdgesPattern.getHasMatch())
      {
         tgtEdgePO.withText(srcEdgePO.getName());
      
         migrateSrcHalfLinkPattern = new ModelPattern();
         
         NodePO copySrcNodePO = migrateSrcHalfLinkPattern.hasElementEdgePO(srcEdgePO.getCurrentMatch())
         .hasSrc()
         .hasCopy();
         
         EdgePO copyEdgePO = migrateSrcHalfLinkPattern.hasElementEdgePO(tgtEdgePO.getCurrentMatch());
         
         migrateSrcHalfLinkPattern.startCreate();
         
         copyEdgePO.hasSrc(copySrcNodePO);
         

         migrateTgtHalfLinkPattern = new ModelPattern();
         
         NodePO copyTgtNodePO = migrateTgtHalfLinkPattern.hasElementEdgePO(srcEdgePO.getCurrentMatch())
         .hasTgt()
         .hasCopy();
         
         EdgePO copyEdgePO2 = migrateTgtHalfLinkPattern.hasElementEdgePO(tgtEdgePO.getCurrentMatch());
         
         migrateTgtHalfLinkPattern.startCreate();
         
         copyEdgePO2.hasTgt(copyTgtNodePO);
         
         noOfMatches++;
         
         migrateEdgesPattern.findNextMatch();
      }

      systemout += "\nNumber of migrated Edges: " + noOfMatches;
      
      scenario.add(migrateEdgesPattern.dumpDiagram("simpleMigrationPerPattern_migrateEdgesPattern", false));
      
      scenario.add(migrateSrcHalfLinkPattern.dumpDiagram("simpleMigrationPerPattern_migrateSrcHalfLinkPattern", false));
      
      scenario.add(migrateTgtHalfLinkPattern.dumpDiagram("simpleMigrationPerPattern_migrateTgtHalfLinkPattern", false));
   }


   private Graph createExampleGraph()
   {
      Graph graph = new Graph();
      
      n1 = graph.createNodes().withName("n1");
      Node n2 = graph.createNodes().withName("n2");
      n3 = graph.createNodes().withName("n3");
      Node n4 = graph.createNodes().withName("n4");
      Node n5 = graph.createNodes().withName("n5");
      n6 = graph.createNodes().withName("n6");
      n7 = graph.createNodes().withName("n7");
      n8 = graph.createNodes().withName("n8");
      
      graph.createEdges().withSrc(n5).withName("e18");
      graph.createEdges().withTgt(n5).withName("e19");

      graph.createEdges().withSrc(n1).withTgt(n2).withName("e10");
      graph.createEdges().withSrc(n2).withTgt(n3).withName("e11");
      graph.createEdges().withSrc(n3).withTgt(n1).withName("e12");
      graph.createEdges().withSrc(n3).withTgt(n4).withName("e13");
      graph.createEdges().withSrc(n4).withTgt(n5).withName("e14");
      graph.createEdges().withSrc(n5).withTgt(n3).withName("e15");
      graph.createEdges().withSrc(n8).withTgt(n8).withName("e16");
      graph.createEdges().withSrc(n8).withTgt(n8).withName("e17");

      return graph;
   }


   public void countNodesInJava(Graph graph)
   {
      int noOfNodes = graph.getNodes().size();
      
      systemout = "Number of nodes: " + noOfNodes;
   }
   
   
   public void countNodesPerPattern(Graph graph)
   {
      ModelPattern p = new ModelPattern();
      
      GraphPO graphPO = p.hasElementGraphPO(graph);
      
      NodePO nodePO = graphPO.hasNodes();
      
      int noOfNodes = p.allMatches();
      
      systemout = "Number of nodes: " + noOfNodes;
   }
   

   public void countNodesPerNodeSet(Graph graph)
   {
      NodeSet allMatches = new ModelPattern()
      .hasElementGraphPO(graph)
      .hasNodes()
      .allMatches();
      
      systemout = "Nodes: " + allMatches.getName().concat(", ") + " Number of nodes: " + allMatches.size();      
   }
   

   public void countLoopingEdgesPerPattern(Graph graph)
   {
      EdgePO edgesPO = new ModelPattern()
      .hasElementGraphPO(graph)
      .hasEdges();
      
      NodePO srcPO = edgesPO.hasSrc();
      
      edgesPO.hasTgt(srcPO);
      
      EdgeSet loopingEdges = edgesPO.allMatches();
      
      systemout = "Looping Edges: " + loopingEdges.getName().concat(", ") + " Number of looping edges: " + loopingEdges.size();
   }
   

   public void countLoopingEdgesInJava(Graph graph)
   {
      String sysout = "Looping Edges: ";
      
      int noOfLoopingEdges = 0;
      
      for (Edge edge : graph.getEdges())
      {
         if (edge.getSrc() != null && edge.getSrc() == edge.getTgt())
         {
            noOfLoopingEdges++;
            
            sysout += edge.getName() + ", ";
         }
      }
      
      systemout = sysout.substring(0, sysout.length() - 2) + " Number of looping edges: " + noOfLoopingEdges;
   }


   public ModelPattern countIsolatedNodesPerPattern(Graph graph)
   {
      ModelPattern p = new ModelPattern();
      
      GraphPO graphPO = p.hasElementGraphPO(graph);
      
      NodePO nodePO = graphPO.hasNodes();
      
      nodePO.startNAC().hasOutEdges().endNAC();
      
      nodePO.startNAC().hasInEdges().endNAC();

      NodeSet isolatedNodes = nodePO.allMatches();
      
      systemout = "Isolated nodes: " + isolatedNodes.getName().concat(", ") + " Number of isolated nodes: " + isolatedNodes.size();
      
      return p;
   }


   public void countIsolatedNodesInJava(Graph graph)
   {
      systemout = "Isolated nodes: ";
      
      int noOfIsolatedNodes = 0;
      
      for (Node isoNode : graph.getNodes())
      {
         if (isoNode.getOutEdges().size() == 0 
               && isoNode.getInEdges().size() == 0)
         {
            noOfIsolatedNodes++;
            
            systemout += isoNode.getName() + ", ";
         }
      }
      
      systemout = systemout.substring(0, systemout.length() - 2) + " Number of isolated nodes: " + noOfIsolatedNodes;
   }


   public ModelPattern countCirclesOfThreeNodesPerPattern(Graph graph)
   {
      ModelPattern p = new ModelPattern();
      
      GraphPO graphPO = p.hasElementGraphPO(graph);
      
      NodePO firstCircleNodePO = graphPO.hasNodes();
      
      NodePO secondCircleNodePO = firstCircleNodePO.hasOutEdges().hasTgt();
      
      NodePO thirdCircleNodePO = secondCircleNodePO.hasOutEdges().hasTgt();
      
      thirdCircleNodePO.hasOutEdges().hasTgt(firstCircleNodePO);
      
      p.matchIsomorphic();
      
      int noOfCircles = p.allMatches();
      
      systemout = "Circles found: " + noOfCircles;
      
      return p;
   }


   public ModelPattern countCirclesOfThreeNodesPerPatternReportMatches(Graph graph)
   {
      ModelPattern p = new ModelPattern();
      
      GraphPO graphPO = p.hasElementGraphPO(graph);
      
      NodePO firstCircleNodePO = graphPO.hasNodes();
      
      NodePO secondCircleNodePO = firstCircleNodePO.hasOutEdges().hasTgt();
      
      NodePO thirdCircleNodePO = secondCircleNodePO.hasOutEdges().hasTgt();
      
      thirdCircleNodePO.hasOutEdges().hasTgt(firstCircleNodePO);
      
      p.matchIsomorphic();
      
      systemout = "Circles found: \n";
      int noOfCircles = 0;
      
      while (p.getHasMatch())
      {
         systemout += firstCircleNodePO.getName() + " --> "
               + secondCircleNodePO.getName() + " --> " 
               + thirdCircleNodePO.getName() + " --> \n";
         
         noOfCircles++; 
         
         p.findNextMatch();
      }
      
      systemout += "" + noOfCircles + " circles found";
      
      return p;
   }


   public void countCirclesOfThreeNodesInJava(Graph graph)
   {
      int noOfCircles = 0;
      
      systemout = "Circles found: \n";
      
      for (Node firstNode : graph.getNodes())
      {
         for (Edge firstEdge : firstNode.getOutEdges())
         {
            Node secondNode = firstEdge.getTgt();
            
            if (secondNode != null && secondNode != firstNode)
            {
               for (Edge secondEdge : secondNode.getOutEdges())
               {
                  Node thirdNode = secondEdge.getTgt();
            
                  if (thirdNode != null && thirdNode != secondNode && thirdNode != firstNode)
                  {
                     for (Edge thirdEdge : thirdNode.getOutEdges())
                     {
                        if (thirdEdge.getTgt() == firstNode)
                        {
                           // found match
                           systemout += firstNode.getName() + " --> "
                                 + secondNode.getName() + " --> " 
                                 + thirdNode.getName() + " --> \n";
                           
                           noOfCircles++;
                        }
                     }
                  }
               }
            }
         }
      }
      
      systemout += "" + noOfCircles + " circles found";
   }


   public ModelPattern countDanglingEdgesPerPattern(Graph graph)
   {
      ModelPattern p = new ModelPattern();
      
      EdgePO edgePO = p.hasElementGraphPO(graph).hasEdges();
      
      edgePO.startNAC().hasTgt();
      
      edgePO.hasSrc().endNAC();
      
      EdgeSet allMatches = edgePO.allMatches();
      
      systemout = "Dangling edges " + allMatches.getName().concat(", ") + " number: " + allMatches.size();
      
      return p;
   }


   public void countDanglingEdgesInJava(Graph graph)
   {
      systemout = "Dangling edges "; 
      
      int noOfMatches = 0;
      
      for (Edge edge : graph.getEdges())
      {
         if (edge.getSrc() == null
               || edge.getTgt() == null)
         {
            systemout += edge.getName() + ", ";
            
            noOfMatches++;
         }
      }
      
      systemout = systemout.substring(0, systemout.length() - 2) + " number: " + noOfMatches;
   }


   public void reverseEdgesPerPattern(Graph graph)
   {
      ModelPattern p = new ModelPattern();
      
      EdgePO edgesPO = p.hasElementGraphPO(graph).hasEdges();
      
      NodePO srcPO = edgesPO.hasSrc();
      
      NodePO tgtPO = edgesPO.hasTgt();
      
      p.startCreate();
      
      edgesPO.hasSrc(tgtPO);
      
      edgesPO.hasTgt(srcPO);
      
      systemout = "Number of reversed edges: " + p.allMatches();
   }


   public void reverseEdgesInJava(Graph graph)
   {
      int noOfMatches = 0;
      
      for (Edge edge : graph.getEdges())
      {
         Node src = edge.getSrc();
         
         Node tgt = edge.getTgt();
         
         edge.setSrc(tgt);
         
         edge.setTgt(src);
         
         noOfMatches++;
      }
      
      systemout = "Number of reversed edges: " + noOfMatches;
   }


   

}
