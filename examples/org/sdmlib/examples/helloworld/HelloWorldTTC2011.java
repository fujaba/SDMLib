package org.sdmlib.examples.helloworld;

import java.util.Arrays;

import org.junit.Test;
import org.sdmlib.CGUtil;
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
import org.sdmlib.examples.helloworld.model.Node;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.models.objects.Generic2Specific;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.Specific2Generic;
import org.sdmlib.models.objects.creators.GenericAttributeSet;
import org.sdmlib.models.objects.creators.GenericLinkPO;
import org.sdmlib.models.objects.creators.GenericLinkSet;
import org.sdmlib.models.objects.creators.GenericObjectPO;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.JsonObject;
import org.sdmlib.storyboards.Storyboard;

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
      Storyboard storyboard = new Storyboard("examples", "TTC2011HelloWorldConstantTransformation1")
      .with("A constant transformation that creates a Greeting object");
      
      
      //==========================================================================
      
      storyboard.add("Create class model and generate implementation:");
      
      ClassModel model = new ClassModel();
      
      Clazz greetingClazz = new Clazz("org.sdmlib.examples.helloworld.Greeting")
      .withAttribute("text", "String");
      
      // model.removeAllGeneratedCode("examples", "examples", "examples");
      
      model.generate("examples", "examples");
      
      storyboard.addSVGImage(model.dumpClassDiagram("examples", "TTC2011HelloWorldConstantTransformation1ClassDiag"));
      
      
      //==========================================================================
      
      storyboard.add("The code that builds and runs the transformation / pattern looks like: ");
      storyboard.markCodeStart();
      ModelPattern p = new ModelPattern().startCreate();
      
      PatternObject greetingPO = p.hasElementGreetingPO()
                                  .hasText("Hello World");
      storyboard.addCode("examples");
      
      storyboard.add(p.dumpDiagram("TTC2011HelloWorldConstantTransformation1", false));
      
      storyboard.assertTrue("Constant transformation has match", p.getHasMatch());
      
      storyboard.add("At runtime the object structure for the pattern and for the hostgraph looks like: ");
      
      storyboard.add(p.dumpDiagram("TTC2011HelloWorldConstantTransformation1AfterTransformation"));
      
      
      //==========================================================================
      
      storyboard.add("For completeness just the host graph:");

      storyboard.addObjectDiagram(greetingPO.getCurrentMatch());
      
      int noOfMatches = p.allMatches();
      
      storyboard.add("TTC2011HelloWorldConstantTransformation1 number of matches is " + noOfMatches);
      
      
      //==========================================================================
      
      storyboard.add("For fairness, the java code that does this transformation looks like: ");
      storyboard.markCodeStart();
      Greeting greeting = new Greeting()
      .withText("Hello World");
      storyboard.addCode("examples");
      
      
      storyboard.dumpHTML();
   }


   //==========================================================================
   @Test
   public void testConstantTransformation2()
   {
      Storyboard storyboard = new Storyboard("examples", "TTC2011HelloWorldConstantTransformation2WithReferences")
      .with("A constant transformation that creates a Greeting object structure with references");
      
      
      //==========================================================================
      
      storyboard.add("Create class model and generate implementation:");
      
      ClassModel model = new ClassModel();
      
      Clazz greetingClazz = new Clazz("org.sdmlib.examples.helloworld.Greeting");
      
      Clazz greetingMessageClazz = new Clazz("org.sdmlib.examples.helloworld.GreetingMessage")
      .withAttribute("text", "String");
      
      Clazz personClazz = new Clazz("org.sdmlib.examples.helloworld.Person")
      .withAttribute("name", "String");
      
      new Association()
      .withTarget(greetingMessageClazz, "greetingMessage", R.ONE)
      .withSource(greetingClazz, "greeting", R.ONE);
      
      new Association()
      .withTarget(personClazz, "person", R.ONE)
      .withSource(greetingClazz, "greeting", R.ONE);
      
      // model.removeAllGeneratedCode("examples", "examples", "examples");
      
      model.generate("examples", "examples");
      
      storyboard.addSVGImage(model.dumpClassDiagram("examples", "TTC2011HelloWorldConstantTransformation2ClassDiag"));
      
      
      //==========================================================================
      
      storyboard.add("The code that builds and runs the transformation / pattern looks like: ");
      storyboard.markCodeStart();
      ModelPattern p = new ModelPattern().startCreate();
      
      GreetingPO greetingPO = p.hasElementGreetingPO();
      
      GreetingMessagePO greetingMessagePO = greetingPO.hasGreetingMessage() 
      .hasText("Hello");
      
      PersonPO personPO = greetingPO.hasPerson()
      .hasName("TTC Participants");
      storyboard.addCode("examples");
      
      storyboard.add(p.dumpDiagram("TTC2011HelloWorldConstantTransformation2", false));
      
      storyboard.assertTrue("Constant transformation has match", p.getHasMatch());
      
      storyboard.add("At runtime the object structure for the pattern and for the hostgraph looks like: ");
      
      // storyboard.addObjectDiag(p.getJsonIdMap(), greetingPO);
      
      storyboard.add(p.dumpDiagram("TTC2011HelloWorldConstantTransformation2AfterTransformation"));
      
      
      //==========================================================================
      
      storyboard.add("For completeness just the host graph:");

      storyboard.addObjectDiagram(greetingPO.getCurrentMatch());
      
      //==========================================================================
      
      storyboard.add("For fairness, the java code that does this transformation looks like: ");
      
      storyboard.markCodeStart();
      Greeting greeting = new Greeting();
      
      GreetingMessage greetingMessage = greeting.createGreetingMessage()
      .withText("Hello");
      
      Person person = greeting.createPerson()
      .withName("TTC Participants");
      storyboard.addCode("examples");
      
      storyboard.dumpHTML();
   }
      
      
   //==========================================================================
   @Test
   public void testModelToTextTransformation()
   {  
      Storyboard storyboard = new Storyboard("examples", "TTC2011HelloWorldModelToText")
      .with("For model to text transformation we provide a simple template mechanism. ");
      
      storyboard.add("The model transformation that builds our object model looks like: ");
      storyboard.markCodeStart();
      ModelPattern p = new ModelPattern().startCreate();
      
      GreetingPO greetingPO = p.hasElementGreetingPO();
      
      GreetingMessagePO greetingMessagePO = greetingPO.hasGreetingMessage() 
      .hasText("Hello");
      
      PersonPO personPO = greetingPO.hasPerson()
      .hasName("TTC Participants");
      storyboard.addCode("examples");
      
      storyboard.add("The created object model looks like: ");
      
      storyboard.addObjectDiagram(p.getJsonIdMap(), greetingPO.getCurrentMatch());
      
      storyboard.add("The model to text transfromation template mechanism is used like this: ");
      
      storyboard.markCodeStart();
      StringBuilder text = new StringBuilder("message name");
      
      CGUtil.replaceAll(text, 
         "message", greetingMessagePO.getText(),
         "name", personPO.getName());
      
      systemout = text.toString();
      storyboard.addCode("examples");
      
      storyboard.add("systemout: \n" + systemout);
      
      String newText = "Hi dudes";
      
      storyboard.add("Assume we change the text manually to: " + newText);
      
      storyboard.markCodeStart();
      CGUtil.find("Hi dudes", 0, "message name", 
         "message", greetingMessagePO, GreetingMessage.PROPERTY_TEXT,
         "name", personPO, Person.PROPERTY_NAME
         );
      storyboard.addCode("examples");
      
      storyboard.addObjectDiagram(p.getJsonIdMap(), greetingPO.getCurrentMatch());
      
      //==========================================================================
      
      storyboard.add("Alternatively, plain java code that does the model to text transformation looks like: ");
      
      storyboard.markCodeStart();
      systemout = greetingMessagePO.getText() + " " + personPO.getName();
      storyboard.addCode("examples");
      
      storyboard.dumpHTML();  
   }
   
   
   //==========================================================================
   @Test
   public void testTTC2011HelloWorldCountNumberOfNodes()
   {  
      Storyboard storyboard = new Storyboard("examples");
      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("Simple Graph application classes: ");
      
      ClassModel model = new ClassModel("org.sdmlib.examples.helloworld");
      
      Clazz graphClazz = new Clazz("Graph");
      
      Clazz edgeClazz = new Clazz("Edge")
      .withAttribute("name", "String");

      Clazz nodeClazz = new Clazz("Node")
      .withAttribute("name", "String");

      new Association()
      .withTarget(nodeClazz, "nodes", R.MANY)
      .withSource(graphClazz, "graph", R.ONE);
      
      new Association()
      .withTarget(edgeClazz, "edges", R.MANY)
      .withSource(graphClazz, "graph", R.ONE);
      
      new Association()
      .withTarget(nodeClazz, "src", R.ONE)
      .withSource(edgeClazz, "outEdges", R.MANY);

      new Association()
      .withTarget(nodeClazz, "tgt", R.ONE)
      .withSource(edgeClazz, "inEdges", R.MANY);

      // // model.removeAllGeneratedCode("examples", "examples", "examples");
      
      model.generate("examples", "examples");
      
      storyboard.addSVGImage(model.dumpClassDiagram("TTC2011HelloWorldSimpleGraphClassDiag"));
      
      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("create example graph: ");
      
      Graph graph = createExampleGraph();
      
      storyboard.addObjectDiagram(CreatorCreator.createIdMap("hg"), graph, true);
      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("<h2>Count nodes in graph direct: </h2>");
      
      countNodesInJava(graph);
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "countNodesInJava(Graph)"));
      
      storyboard.add("systemout: " + systemout);
      
      
      //==========================================================================
      
      storyboard.add("Count nodes in graph per pattern: ");
      
      countNodesPerPattern(graph);
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "countNodesPerPattern(Graph)"));
      
      // storyboard.addObjectDiag(p.getJsonIdMap(), p.getElements().iterator().next());
      
      storyboard.add(ModelPattern.lastPattern.dumpDiagram("countNodesPerPatternRule", false));

      storyboard.add(ModelPattern.lastPattern.dumpDiagram("countNodesPerPatternRuleWithMatch"));
      
      storyboard.add("systemout: " + systemout);
      
      
      //==========================================================================
      
      storyboard.add("Retrieve set of matching nodes per pattern and count its elements: ");
      
      countNodesPerNodeSet(graph);
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "countNodesPerNodeSet(Graph)"));

      storyboard.add(ModelPattern.lastPattern.dumpDiagram("countNodesPerNodeSet", false));
      
      storyboard.add(ModelPattern.lastPattern.dumpDiagram("countNodesPerNodeSetWithMatch"));
      
      storyboard.add("systemout: " + systemout);
      
      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("<h2>Retrieve set of looping edges per pattern and count its elements: </h2>");
      
      countLoopingEdgesPerPattern(graph);
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "countLoopingEdgesPerPattern(Graph)"));
      
      storyboard.add(ModelPattern.lastPattern.dumpDiagram("countLoopingEdgesPerPattern", false));
      
      storyboard.add(ModelPattern.lastPattern.dumpDiagram("countLoopingEdgesPerPatternWithMatch"));
      
      storyboard.add("systemout: " + systemout);
      
      storyboard.add("For comparison a Java solution: ");
      
      countLoopingEdgesInJava(graph);
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "countLoopingEdgesInJava(Graph)"));
      
      storyboard.add("systemout: " + systemout);
      
      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("<h2>Retrieve isolated nodes and count them: </h2>");
      
      countIsolatedNodesPerPattern(graph);
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "countIsolatedNodesPerPattern(Graph)"));
      
      storyboard.add(ModelPattern.lastPattern.dumpDiagram("countIsolatedNodesPerPattern", false));

      storyboard.add(ModelPattern.lastPattern.dumpDiagram("countIsolatedNodesPerPatternWithMatch", true));

      storyboard.add("systemout: " + systemout);
      
      storyboard.add("For comparison a Java solution: ");
      
      countIsolatedNodesInJava(graph);
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "countIsolatedNodesInJava(Graph)"));
      
      storyboard.add("systemout: " + systemout);
      
      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("<h2>Count circles of three nodes: </h2>");
      
      countCirclesOfThreeNodesPerPattern(graph);
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "countCirclesOfThreeNodesPerPattern(Graph)"));
      
      storyboard.add(ModelPattern.lastPattern.dumpDiagram("countCirclesOfThreeNodesPerPattern", false));

      storyboard.add(ModelPattern.lastPattern.dumpDiagram("countCirclesOfThreeNodesPerPatternWithMatch", true));

      storyboard.add("systemout: " + systemout);
      
      storyboard.add("If you want to print the matched nodes for each circle, you need to iterate through the matches: ");
      
      countCirclesOfThreeNodesPerPatternReportMatches(graph);
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "countCirclesOfThreeNodesPerPatternReportMatches(Graph)"));
      
      storyboard.add(ModelPattern.lastPattern.dumpDiagram("countCirclesOfThreeNodesPerPatternReportMatches", false));

      storyboard.add(ModelPattern.lastPattern.dumpDiagram("countCirclesOfThreeNodesPerPatternReportMatchesWithMatch", true));

      storyboard.add("systemout: " + systemout);
      
      storyboard.add("Just for comparison, a plain java implementation: ");
      
      countCirclesOfThreeNodesInJava(graph);
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "countCirclesOfThreeNodesInJava(Graph)"));
      
      storyboard.add("systemout: " + systemout);
      
      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("<h2>Count dangling edges per pattern: </h2>");
      
      countDanglingEdgesPerPattern(graph);
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "countDanglingEdgesPerPattern(Graph)"));
      
      storyboard.add(ModelPattern.lastPattern.dumpDiagram("countDanglingEdgesPerPattern", false));

      storyboard.add(ModelPattern.lastPattern.dumpDiagram("countDanglingEdgesPerPatternWithMatch", true));

      storyboard.add("systemout: " + systemout);
      
      storyboard.add("Count dangling edges in Java: ");
      
      countDanglingEdgesInJava(graph);
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "countDanglingEdgesInJava(Graph)"));
      
      storyboard.add("systemout: " + systemout);
      
      
      
      
      storyboard.dumpHTML();
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
      Storyboard storyboard = new Storyboard("examples");
      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("create example graph: ");
      
      Graph graph = createExampleGraph();
      
      storyboard.addObjectDiagram(CreatorCreator.createIdMap("hg"), graph);
      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("<h2>Reverse Edges per pattern: </h2>");
      
      reverseEdgesPerPattern(graph);
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "reverseEdgesPerPattern(Graph)"));
      
      storyboard.add(ModelPattern.lastPattern.dumpDiagram("reverseEdgesPerPattern", false));

      storyboard.add(ModelPattern.lastPattern.dumpDiagram("reverseEdgesPerPatternWithMatch", true));

      
      storyboard.add("Result graph: ");
      
      storyboard.addObjectDiagram(CreatorCreator.createIdMap("hg"), graph);
      
      storyboard.add(systemout);
      
      //==========================================================================
      
      storyboard.add("<h2>Reverse Edges (back) in Java: </h2>: ");
    
      reverseEdgesInJava(graph);
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "reverseEdgesInJava(Graph)"));
      
      storyboard.add("Result graph: ");
      
      storyboard.addObjectDiagram(CreatorCreator.createIdMap("hg"), graph);
      
      storyboard.add(systemout);
      

      storyboard.dumpHTML();
   }


   
   //==========================================================================
   @Test
   public void testTTC2011SimpleMigration()
   {  
      Storyboard storyboard = new Storyboard("examples");
      
      storyboard.add("<hr/>");
      storyboard.add("Source model:");
      
      ClassModel model = new ClassModel();
      
      Clazz graphClazz = new Clazz("org.sdmlib.examples.helloworld.Graph");
      
      Clazz edgeClazz = new Clazz("org.sdmlib.examples.helloworld.Edge")
      .withAttribute("name", "String");

      Clazz nodeClazz = new Clazz("org.sdmlib.examples.helloworld.Node")
      .withAttribute("name", "String");

      new Association()
      .withTarget(nodeClazz, "nodes", R.MANY)
      .withSource(graphClazz, "graph", R.ONE);
      
      new Association()
      .withTarget(edgeClazz, "edges", R.MANY)
      .withSource(graphClazz, "graph", R.ONE);
      
      new Association()
      .withTarget(nodeClazz, "src", R.ONE)
      .withSource(edgeClazz, "outEdges", R.MANY);

      new Association()
      .withTarget(nodeClazz, "tgt", R.ONE)
      .withSource(edgeClazz, "inEdges", R.MANY);
      
      // model.removeAllGeneratedCode("examples", "examples", "examples");
      
      model.generate("examples", "examples");
      
      storyboard.addSVGImage(model.dumpClassDiagram("examples", "TTC2011HelloWorldSimpleMigrationSourceClassDiag"));
      
      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("Target model:");
      
      model = new ClassModel();
      
      graphClazz = new Clazz("org.sdmlib.examples.helloworld.Graph");
      
      Clazz graphComponentClazz = new Clazz("org.sdmlib.examples.helloworld.GraphComponent")
      .withAttribute("text", "String");
      
      edgeClazz = new Clazz("org.sdmlib.examples.helloworld.Edge")
      .withSuperClass(graphComponentClazz);

      nodeClazz = new Clazz("org.sdmlib.examples.helloworld.Node")
      .withSuperClass(graphComponentClazz);

      new Association()
      .withTarget(graphComponentClazz, "gcs", R.MANY)
      .withSource(graphClazz, "parent", R.ONE);
      
      new Association()
      .withTarget(nodeClazz, "src", R.ONE)
      .withSource(edgeClazz, "outEdges", R.MANY);

      new Association()
      .withTarget(nodeClazz, "tgt", R.ONE)
      .withSource(edgeClazz, "inEdges", R.MANY);
      
      // model.removeAllGeneratedCode("examples", "examples", "examples");
      
      model.generate("examples", "examples");
      
      storyboard.addClassDiagram(model);
      
      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("Migration extension:");
      
      model = new ClassModel();
      
      nodeClazz = new Clazz("org.sdmlib.examples.helloworld.Node");

      new Association()
      .withTarget(nodeClazz, "copy", R.ONE)
      .withSource(nodeClazz, "orig", R.ONE);
      
      model.generate("examples");
      
      storyboard.addClassDiagram(model);
      

      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("Create example source graph: ");
      
      Graph graph = createExampleGraph();
      
      storyboard.addObjectDiagram(graph);
      
      storyboard.dumpHTML();

      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("<h2>Migrate per pattern: </h2>");
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "simpleMigrationPerPattern(Graph,Storyboard)"));
      
      simpleMigrationPerPattern(graph, storyboard);
      
      storyboard.add("Result graph: ");
      
      storyboard.addObjectDiagram(graph);
      
      storyboard.add(systemout);
      
      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("<h2>Migrate in Java: </h2>");
      
      graph = createExampleGraph();
      
      simpleMigrationInJava(graph);
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "simpleMigrationInJava(Graph)"));
      
      storyboard.add("Result graph: ");
      
      storyboard.addObjectDiagram(graph);
      
      
      storyboard.add(systemout);

      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("<h2>Migrate using Serialisation / Clone : </h2>");
      
      graph = createExampleGraph();
      
      graph = simpleMigrationByCloning(graph);
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "simpleMigrationByCloning(Graph)"));
      
      storyboard.add("Result graph: ");
      
      storyboard.addObjectDiagram(graph);
      
      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("<h2>Migrate using JSON Array repr�sentation : </h2>");
      
      graph = createExampleGraph();
      
      graph = simpleMigrationByJsonArray(graph);
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "simpleMigrationByJsonArray(Graph)"));
      
      storyboard.add("Result graph: ");
      
      storyboard.addObjectDiagram(graph);
      
      
      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("<h2>Migrate using Generic Graph representation : </h2>");

      graph = createExampleGraph();

      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "simpleMigrationByGenericGraph(Graph,Storyboard)"));
      
      Graph tgtGraph = simpleMigrationByGenericGraph(graph, storyboard);

      storyboard.add("Result graph: ");

      storyboard.addObjectDiagram(tgtGraph);

   
      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("<h2>Even more evolved graph model : </h2>");

      model = new ClassModel();
      
      graphClazz = new Clazz("org.sdmlib.examples.helloworld.Graph");

      nodeClazz = new Clazz("org.sdmlib.examples.helloworld.Node")
      .withAttribute("text", "String");

      new Association()
      .withTarget(nodeClazz, "nodes", R.MANY)
      .withSource(graphClazz, "graph", R.ONE);
      
      new Association()
      .withTarget(nodeClazz, "linksTo", R.MANY)
      .withSource(nodeClazz, "linksFrom", R.MANY);
      
      model.generate("examples", "examples");
      
      storyboard.addSVGImage(model.dumpClassDiagram("examples", "TTC2011HelloWorldSimpleMigrationEvenMoreEvolvedDiag"));
      
      graph = createExampleGraph();
      
      storyboard.addObjectDiagram(graph);
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "simpleMigrationToEvenMoreEvolvedGraphByGenericGraph(Graph,Storyboard)"));
      
      tgtGraph = simpleMigrationToEvenMoreEvolvedGraphByGenericGraph(graph, storyboard);

      storyboard.add("Result graph: ");

      storyboard.addObjectDiagram(tgtGraph);

      
      storyboard.dumpHTML();
   }
   
   
   //==========================================================================
   @Test
   public void testTTC2011SimpleMigrationViaGenericGraphs()
   {  
      Storyboard storyboard = new Storyboard();
      
      storyboard.add("Class diagram for source model:");
      
      ClassModel model = new ClassModel();
      
      Clazz graphClazz = new Clazz("org.sdmlib.examples.helloworld.Graph");
      
      Clazz edgeClazz = new Clazz("org.sdmlib.examples.helloworld.Edge")
      .withAttribute("name", "String");

      Clazz nodeClazz = new Clazz("org.sdmlib.examples.helloworld.Node")
      .withAttribute("name", "String");

      new Association()
      .withTarget(nodeClazz, "nodes", R.MANY)
      .withSource(graphClazz, "graph", R.ONE);
      
      new Association()
      .withTarget(edgeClazz, "edges", R.MANY)
      .withSource(graphClazz, "graph", R.ONE);
      
      new Association()
      .withTarget(nodeClazz, "src", R.ONE)
      .withSource(edgeClazz, "outEdges", R.MANY);

      new Association()
      .withTarget(nodeClazz, "tgt", R.ONE)
      .withSource(edgeClazz, "inEdges", R.MANY);
      
      model.generate("examples", "examples");
      
      storyboard.addClassDiagram(model);
      
      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("Class diagram for target model:");
      
      model = new ClassModel();
      
      graphClazz = new Clazz("org.sdmlib.examples.helloworld.Graph");
      
      Clazz graphComponentClazz = new Clazz("org.sdmlib.examples.helloworld.GraphComponent")
      .withAttribute("text", "String");
      
      edgeClazz = new Clazz("org.sdmlib.examples.helloworld.Edge")
      .withSuperClass(graphComponentClazz);

      nodeClazz = new Clazz("org.sdmlib.examples.helloworld.Node")
      .withSuperClass(graphComponentClazz);

      new Association()
      .withTarget(graphComponentClazz, "gcs", R.MANY)
      .withSource(graphClazz, "parent", R.ONE);
      
      new Association()
      .withTarget(nodeClazz, "src", R.ONE)
      .withSource(edgeClazz, "outEdges", R.MANY);

      new Association()
      .withTarget(nodeClazz, "tgt", R.ONE)
      .withSource(edgeClazz, "inEdges", R.MANY);
      
      // model.removeAllGeneratedCode("examples", "examples", "examples");
      
      model.generate("examples", "examples");
      
      storyboard.addClassDiagram(model);
      
      
      //==========================================================================
      
      storyboard.add("<hr/>");
      storyboard.add("Create example source graph: ");
      
      Graph graph = createExampleGraph();
      
      storyboard.addObjectDiagramWith(graph.getNodes(), graph.getEdges());;
      
      storyboard.dumpHTML();

      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("<h2>Migrate using Generic Graph representation : </h2>");

      graph = createExampleGraph();

      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "simpleMigrationByGenericGraph(Graph,Storyboard)"));
      
      Graph tgtGraph = simpleMigrationByGenericGraph(graph, storyboard);

      storyboard.add("Result graph: ");

      storyboard.addObjectDiagramWith(tgtGraph.getNodes(), tgtGraph.getGcs());

   
      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("<h2>Even more evolved class diagram : </h2>");

      model = new ClassModel();
      
      graphClazz = new Clazz("org.sdmlib.examples.helloworld.Graph");

      nodeClazz = new Clazz("org.sdmlib.examples.helloworld.Node")
      .withAttribute("text", "String");

      new Association()
      .withTarget(nodeClazz, "nodes", R.MANY)
      .withSource(graphClazz, "graph", R.ONE);
      
      new Association()
      .withTarget(nodeClazz, "linksTo", R.MANY)
      .withSource(nodeClazz, "linksFrom", R.MANY);
      
      model.generate("examples", "examples");
      
      storyboard.addClassDiagram(model);
      
      storyboard.add("<hr/>");
      
      storyboard.add("Again the input graph:");
      
      graph = createExampleGraph();
      
      storyboard.addObjectDiagramWith(graph.getNodes(), graph.getEdges());
      
      storyboard.add("The transformation code:");
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "simpleMigrationToEvenMoreEvolvedGraphByGenericGraph(Graph,Storyboard)"));
      
      tgtGraph = simpleMigrationToEvenMoreEvolvedGraphByGenericGraph(graph, storyboard);

      storyboard.add("Result graph: ");

      storyboard.addObjectDiagramWith(tgtGraph.getNodes(), tgtGraph.getEdges());

      
      storyboard.dumpHTML();
   }
   
   
   //==========================================================================
   @Test
   public void testTTC2011DeleteNodeWithSpecificName()
   {  
      Storyboard storyboard = new Storyboard("examples");
      
      storyboard.add("<hr/>");
      storyboard.add("Delete node with name n1 and its incidemnt edges.");
      
      storyboard.add("Start graph:");
      
      Graph graph = createExampleGraph();
      
      // rename node n3 to make case more interesting
      n3.withName("n1");
      
      JsonIdMap createIdMap = CreatorCreator.createIdMap("sg");
      
      storyboard.addObjectDiagram(createIdMap, graph);
      
      storyboard.add("Transformation:");
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "deleteNodeWithNameN1(Graph)"));

      deleteNodeWithNameN1(graph);
      
      storyboard.add(ModelPattern.lastPattern.dumpDiagram("deleteNodeWithNameN1_nodeN1", false));
      
      storyboard.add("Result graph:");
      
      storyboard.addObjectDiagram(createIdMap, graph);
      
      
      storyboard.dumpHTML();
   }


 //==========================================================================
   @Test
   public void testTTC2011InsertTransitiveEdges()
   {  
      Storyboard storyboard = new Storyboard("examples");
      
      storyboard.add("<hr/>");
      storyboard.add("Insert transitive edges.");
      
      storyboard.add("Start graph:");
      
      Graph graph = createExampleGraph();
      
      JsonIdMap createIdMap = CreatorCreator.createIdMap("sg");
      
      storyboard.addObjectDiagram(createIdMap, graph);
      
      int noOfMatches = insertTransitiveEdges(graph);

      storyboard.add("Transformation:");
      
      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "insertTransitiveEdges(Graph)"));

      storyboard.add(ModelPattern.lastPattern.dumpDiagram("insertTransitiveEdges", false));
      
      storyboard.add("Transformation with matches:");
      
      storyboard.add(ModelPattern.lastPattern.dumpDiagram("insertTransitiveEdgesWithMatch", true));
      
      storyboard.add("We have inserted " + noOfMatches + " new transitive edges. Result graph:");
      
      storyboard.addObjectDiagram(createIdMap, graph);
      
      storyboard.add("For comparison a Java solution: ");
      
      
      
      storyboard.dumpHTML();
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


   private Graph simpleMigrationToEvenMoreEvolvedGraphByGenericGraph(Graph origGraph, Storyboard storyboard)
   {
      GenericGraph genGraph = new Specific2Generic()
      .convert(CreatorCreator.createIdMap("g1"), origGraph);
      
      // rename name to text attributes
      new org.sdmlib.models.objects.creators.ModelPattern()
      .hasElementGenericGraphPO(genGraph)
      .hasObjects()
      .hasAttrs()
      .hasName(Node.PROPERTY_NAME)
      .startCreate()
      .hasName(Node.PROPERTY_TEXT)
      .allMatches();
      
      storyboard.add(org.sdmlib.models.objects.creators.ModelPattern.lastPattern.dumpDiagram("simpleMigrationToEvenMoreEvolvedGraphByGenericGraph_renameAttr", false));
      
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
      
      storyboard.add(org.sdmlib.models.objects.creators.ModelPattern.lastPattern.dumpDiagram("simpleMigrationToEvenMoreEvolvedGraphByGenericGraph_replaceEdges", false));
      
      // destroy dangling edges
      GenericObjectPO danglingPO = new org.sdmlib.models.objects.creators.ModelPattern()
      .hasElementGenericGraphPO(genGraph)
      .hasObjects()
      .hasType(Edge.class.getName());
      
      danglingPO.destroy();
      
      danglingPO.allMatches();
      
      storyboard.add(org.sdmlib.models.objects.creators.ModelPattern.lastPattern.dumpDiagram("simpleMigrationToEvenMoreEvolvedGraphByGenericGraph_removeDanglingEdges", false));
      
      Graph tgtGraph = (Graph) new Generic2Specific().convert(CreatorCreator.createIdMap("tg"), null, genGraph);
      
      return tgtGraph;
   }
   
   
   private Graph simpleMigrationByGenericGraph(Graph origGraph, Storyboard storyboard)
   {
      GenericGraph genGraph = new Specific2Generic()
      .convert(CreatorCreator.createIdMap("g1"), origGraph);
      
      storyboard.addObjectDiagramWith(genGraph.getObjects(), genGraph.getLinks(), genGraph.getObjects().getAttrs());
      
      // rename name to text attributes
      new org.sdmlib.models.objects.creators.ModelPattern()
      .hasElementGenericGraphPO(genGraph)
      .hasObjects()
      .hasAttrs()
      .hasName(Node.PROPERTY_NAME)
      .startCreate()
      .hasName(Node.PROPERTY_TEXT)
      .allMatches();
      
      storyboard.add(org.sdmlib.models.objects.creators.ModelPattern.lastPattern.dumpDiagram("simpleMigrationByGenericGraph_renameAttr", false));
      
      // storyboard.add("<hr/>");
      
      // rename graph--nodes links to parent--gcs links
      new org.sdmlib.models.objects.creators.ModelPattern()
      .hasElementGenericGraphPO(genGraph)
      .hasLinks()
      .hasTgtLabel(Node.PROPERTY_GRAPH)
      .startCreate()
      .hasTgtLabel(Node.PROPERTY_PARENT)
      .hasSrcLabel(Graph.PROPERTY_GCS)
      .allMatches();
      
      storyboard.add(org.sdmlib.models.objects.creators.ModelPattern.lastPattern.dumpDiagram("simpleMigrationByGenericGraph_renameLink1", false));

      // storyboard.add("<hr/>");
      
      // rename graph--edges links to parent--gcs links
      new org.sdmlib.models.objects.creators.ModelPattern()
      .hasElementGenericGraphPO(genGraph)
      .hasLinks()
      .hasSrcLabel(Node.PROPERTY_GRAPH)
      .startCreate()
      .hasSrcLabel(Node.PROPERTY_PARENT)
      .hasTgtLabel(Graph.PROPERTY_GCS)
      .allMatches();
      
      storyboard.add(org.sdmlib.models.objects.creators.ModelPattern.lastPattern.dumpDiagram("simpleMigrationByGenericGraph_renameLink2", false));

      storyboard.addObjectDiagramWith(genGraph.getObjects(), genGraph.getLinks(), genGraph.getObjects().getAttrs());
      
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
      
      Graph copyGraph = (Graph) CreatorCreator.createIdMap("hg").decode(jsonArray);
      
      return copyGraph;
   }


   private Graph simpleMigrationByCloning(Graph graph)
   {
      JsonArray jsonArray = CreatorCreator.createIdMap("hg").toJsonArray(graph);
      
      Graph copyGraph = (Graph) CreatorCreator.createIdMap("hg").decode(jsonArray);
      
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


   private void simpleMigrationPerPattern(Graph graph, Storyboard storyboard)
   {
      //==========================================================================
      ModelPattern migrateGraph = new ModelPattern();
      
      GraphPO srcGraphPO = migrateGraph.hasElementGraphPO(graph);
      
      migrateGraph.startCreate();
      
      GraphPO tgtGraphPO = migrateGraph.hasElementGraphPO();
      
      storyboard.add(migrateGraph.dumpDiagram("simpleMigrationPerPattern_migrateGraph", false));
      
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
      
      storyboard.add(migrateNodesPattern.dumpDiagram("simpleMigrationPerPattern_migrateNodesPattern", false));
      
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
      
      storyboard.add(migrateEdgesPattern.dumpDiagram("simpleMigrationPerPattern_migrateEdgesPattern", false));
      
      storyboard.add(migrateSrcHalfLinkPattern.dumpDiagram("simpleMigrationPerPattern_migrateSrcHalfLinkPattern", false));
      
      storyboard.add(migrateTgtHalfLinkPattern.dumpDiagram("simpleMigrationPerPattern_migrateTgtHalfLinkPattern", false));
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
