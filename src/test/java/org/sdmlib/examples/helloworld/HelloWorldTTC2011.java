package org.sdmlib.examples.helloworld;

import java.util.Arrays;

import org.junit.Test;
import org.sdmlib.CGUtil;
import org.sdmlib.examples.helloworld.model.Edge;
import org.sdmlib.examples.helloworld.model.Graph;
import org.sdmlib.examples.helloworld.model.GraphComponent;
import org.sdmlib.examples.helloworld.model.Node;
import org.sdmlib.examples.helloworld.model.util.EdgePO;
import org.sdmlib.examples.helloworld.model.util.EdgeSet;
import org.sdmlib.examples.helloworld.model.util.GraphComponentSet;
import org.sdmlib.examples.helloworld.model.util.GraphCreator;
import org.sdmlib.examples.helloworld.model.util.GraphPO;
import org.sdmlib.examples.helloworld.model.util.NodePO;
import org.sdmlib.examples.helloworld.model.util.NodeSet;
import org.sdmlib.examples.helloworld.util.GreetingMessagePO;
import org.sdmlib.examples.helloworld.util.GreetingPO;
import org.sdmlib.examples.helloworld.util.PersonPO;
import org.sdmlib.models.classes.Association;
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
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonIdMap;
import de.uniks.networkparser.json.JsonObject;

public class HelloWorldTTC2011
{
   private String systemout;
   private Node n1;
   private Node n6;
   private Node n7;
   private Node n8;
   private Node n3;
   private GraphPO graphPO;
   private GraphPO srcGraphPO;
   private EdgePO edgesPO;
   private NodePO copySrcNodePO;
   private NodePO copyTgtNodePO;


   @Test
   public void testConstantTransformation1()
   {
      Storyboard storyboard = new Storyboard("examples", "TTC2011HelloWorldConstantTransformation1")
      .with("A constant transformation that creates a Greeting object");


      //==========================================================================

      storyboard.add("Create class model and generate implementation:");

      ClassModel model = new ClassModel();

      Clazz greetClass = model.createClazz("org.sdmlib.examples.helloworld.Greeting")
            .withAttribute("text", DataType.STRING);

      greetClass.withUniDirectionalAssoc(greetClass, "tgt", Card.ONE);
      // model.removeAllGeneratedCode("examples", "examples", "examples");

      model.generate("src/test/java");

      storyboard.addClassDiagram(model, "examples");


      //==========================================================================

      storyboard.add("The code that builds and runs the transformation / pattern looks like: ");
      storyboard.markCodeStart();

      GreetingPO greetingPO = (GreetingPO) new Pattern<Pattern>()
            .startCreate()
            .has(new GreetingPO());

      greetingPO.hasText("Hello World");

      storyboard.addCode("examples");

      storyboard.addPattern(greetingPO, false);

      storyboard.assertTrue("Constant transformation has match", greetingPO.getPattern().getHasMatch());

      storyboard.add("At runtime the object structure for the pattern and for the hostgraph looks like: ");

      storyboard.addPattern(greetingPO, true);


      //==========================================================================

      storyboard.add("For completeness just the host graph:");

      storyboard.addObjectDiagram(greetingPO.getCurrentMatch());

      int noOfMatches = greetingPO.getPattern().allMatches();

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

      Clazz greetingClazz = model.createClazz("org.sdmlib.examples.helloworld.Greeting");

      Clazz greetingMessageClazz = model.createClazz("org.sdmlib.examples.helloworld.GreetingMessage")
            .withAttribute("text", DataType.STRING);

      Clazz personClazz = model.createClazz("org.sdmlib.examples.helloworld.Person")
            .withAttribute("name", DataType.STRING);

      greetingClazz.withAssoc(greetingMessageClazz, "greetingMessage", Card.ONE, "greeting", Card.ONE);

      greetingClazz.withAssoc(personClazz, "person", Card.ONE, "greeting", Card.ONE);

      // model.removeAllGeneratedCode("examples", "examples", "examples");

      model.generate("src/test/java");

      storyboard.addClassDiagram(model);


      //==========================================================================

      storyboard.add("The code that builds and runs the transformation / pattern looks like: ");
      storyboard.markCodeStart();
      GreetingPO greetingPO = (GreetingPO) new Pattern<Pattern>()
            .startCreate()
            .has(new GreetingPO());

      GreetingMessagePO greetingMessagePO = greetingPO.hasGreetingMessage() 
            .hasText("Hello");

      PersonPO personPO = greetingPO.hasPerson()
            .hasName("TTC Participants");
      storyboard.addCode("examples");

      storyboard.addPattern(greetingPO, false);

      storyboard.assertTrue("Constant transformation has match", greetingPO.getPattern().getHasMatch());

      storyboard.add("At runtime the object structure for the pattern and for the hostgraph looks like: ");

      storyboard.addPattern(greetingPO, true);


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

      GreetingPO greetingPO = (GreetingPO) new Pattern<Pattern>()
            .startCreate()
            .has(new GreetingPO());

      GreetingMessagePO greetingMessagePO = greetingPO.hasGreetingMessage() 
            .hasText("Hello");

      PersonPO personPO = greetingPO.hasPerson()
            .hasName("TTC Participants");
      storyboard.addCode("examples");

      storyboard.add("The created object model looks like: ");

      storyboard.addObjectDiagram(greetingPO.getCurrentMatch());

      storyboard.add("The model to text transfromation template mechanism is used like this: ");

      storyboard.markCodeStart();
      systemout = CGUtil.replaceAll("message name", 
         "message", greetingMessagePO.getText(),
         "name", personPO.getName());
      storyboard.addCode("examples");

      storyboard.add("systemout: \n" + systemout);

      String newText = "Hi dudes";

      storyboard.add("Assume we change the text manually to: " + newText);

      storyboard.markCodeStart();
      CGUtil.find("Hi dudes ", 0, "message name ", 
         "message", greetingMessagePO, GreetingMessage.PROPERTY_TEXT,
         "name", personPO, Person.PROPERTY_NAME
            );
      storyboard.addCode("examples");

      storyboard.addObjectDiagram(greetingPO.getCurrentMatch());

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

      ClassModel model = new ClassModel("org.sdmlib.examples.helloworld.model");

      Clazz graphClazz = model.createClazz("Graph");

      Clazz edgeClazz = model.createClazz("Edge")
            .withAttribute("name", DataType.STRING);

      Clazz nodeClazz = model.createClazz("Node")
            .withAttribute("name", DataType.STRING);

      new Association()
      .withTarget(nodeClazz, "nodes", Card.MANY)
      .withSource(graphClazz, "graph", Card.ONE);

      new Association()
      .withTarget(edgeClazz, "edges", Card.MANY)
      .withSource(graphClazz, "graph", Card.ONE);

      new Association()
      .withTarget(nodeClazz, "src", Card.ONE)
      .withSource(edgeClazz, "outEdges", Card.MANY);

      new Association()
      .withTarget(nodeClazz, "tgt", Card.ONE)
      .withSource(edgeClazz, "inEdges", Card.MANY);

      // model.removeAllGeneratedCode("examples", "examples", "examples");

      model.generate("src/test/java");

      storyboard.addClassDiagram(model, "examples");


      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("create example graph: ");

      Graph graph = createExampleGraph();

      storyboard.addObjectDiagram(GraphCreator.createIdMap("hg"), graph, true);

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

      storyboard.addPattern(graphPO, false);

      storyboard.addPattern(graphPO, true);

      storyboard.add("systemout: " + systemout);


      //==========================================================================

      storyboard.add("Retrieve set of matching nodes per pattern and count its elements: ");

      countNodesPerNodeSet(graph);

      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "countNodesPerNodeSet(Graph)"));

      storyboard.addPattern(graphPO, false);

      storyboard.addPattern(graphPO, true);

      storyboard.add("systemout: " + systemout);


      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("<h2>Retrieve set of looping edges per pattern and count its elements: </h2>");

      countLoopingEdgesPerPattern(graph);

      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "countLoopingEdgesPerPattern(Graph)"));

      storyboard.addPattern(edgesPO, false);

      storyboard.addPattern(edgesPO, true);

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

      storyboard.addPattern(graphPO, false);

      storyboard.addPattern(graphPO, true);

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

      storyboard.addPattern(graphPO, false);

      storyboard.addPattern(graphPO, true);

      storyboard.add("systemout: " + systemout);

      storyboard.add("If you want to print the matched nodes for each circle, you need to iterate through the matches: ");

      countCirclesOfThreeNodesPerPatternReportMatches(graph);

      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "countCirclesOfThreeNodesPerPatternReportMatches(Graph)"));

      storyboard.addPattern(graphPO, false);

      storyboard.addPattern(graphPO, true);

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

      storyboard.addPattern(edgesPO, false);

      storyboard.addPattern(edgesPO, true);

      storyboard.add("systemout: " + systemout);

      storyboard.add("Count dangling edges in Java: ");

      countDanglingEdgesInJava(graph);

      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "countDanglingEdgesInJava(Graph)"));

      storyboard.add("systemout: " + systemout);


      storyboard.dumpHTML();
   }


   //   private void sleep1000()
   //   {
   //      try
   //      {
   //         Thread.sleep(1000);
   //      }
   //      catch (InterruptedException e)
   //      {
   //         // TODO Auto-generated catch block
   //         e.printStackTrace();
   //      }
   //   }


   //==========================================================================
   @Test
   public void testTTC2011HelloWorldReverseEdges()
   {  
      Storyboard storyboard = new Storyboard("examples");

      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("create example graph: ");

      Graph graph = createExampleGraph();

      storyboard.addObjectDiagram(graph);

      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("<h2>Reverse Edges per pattern: </h2>");

      reverseEdgesPerPattern(graph);

      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "reverseEdgesPerPattern(Graph)"));

      storyboard.addPattern(edgesPO, false);

      storyboard.addPattern(edgesPO, true);

      storyboard.add("Result graph: ");

      storyboard.addObjectDiagram(graph);

      storyboard.add(systemout);

      //==========================================================================

      storyboard.add("<h2>Reverse Edges (back) in Java: </h2>: ");

      reverseEdgesInJava(graph);

      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "reverseEdgesInJava(Graph)"));

      storyboard.add("Result graph: ");

      storyboard.addObjectDiagram(graph);

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

      ClassModel model = new ClassModel("org.sdmlib.examples.helloworld.model");

      Clazz graphClazz = model.createClazz("Graph");

      Clazz edgeClazz = model.createClazz("Edge")
            .withAttribute("name", DataType.STRING);

      Clazz nodeClazz = model.createClazz("Node")
            .withAttribute("name", DataType.STRING);

      new Association()
      .withTarget(nodeClazz, "nodes", Card.MANY)
      .withSource(graphClazz, "graph", Card.ONE);

      new Association()
      .withTarget(edgeClazz, "edges", Card.MANY)
      .withSource(graphClazz, "graph", Card.ONE);

      new Association()
      .withTarget(nodeClazz, "src", Card.ONE)
      .withSource(edgeClazz, "outEdges", Card.MANY);

      new Association()
      .withTarget(nodeClazz, "tgt", Card.ONE)
      .withSource(edgeClazz, "inEdges", Card.MANY);

      // model.removeAllGeneratedCode("examples", "examples", "examples");

      model.generate("src/test/java");

      storyboard.addClassDiagram(model);


      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("Target model:");

      model = new ClassModel("org.sdmlib.examples.helloworld.model");

      graphClazz = model.createClazz("Graph");

      Clazz graphComponentClazz = model.createClazz("GraphComponent")
            .withAttribute("text", DataType.STRING);

      edgeClazz = model.createClazz("Edge")
            .withSuperClazz(graphComponentClazz);

      nodeClazz = model.createClazz("Node")
            .withSuperClazz(graphComponentClazz);

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

      model.generate("src/test/java");

      storyboard.addClassDiagram(model);


      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("Migration extension:");

      model = new ClassModel("org.sdmlib.examples.helloworld.model");

      nodeClazz = model.createClazz("Node");

      new Association()
      .withTarget(nodeClazz, "copy", Card.ONE)
      .withSource(nodeClazz, "orig", Card.ONE);

      model.generate("src/test/java");

      storyboard.addClassDiagram(model);


      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("Create example source graph: ");

      Graph graph = createExampleGraph();

      storyboard.withJsonIdMap(GraphCreator.createIdMap("hg"));

      storyboard.addObjectDiagram(graph);

      // storyboard.dumpHTML();

      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("<h2>Migrate per pattern: </h2>");

      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "simpleMigrationPerPattern(Graph,Storyboard)"));

      Graph targetGraph = simpleMigrationPerPattern(graph, storyboard);

      storyboard.add("Result graph: ");

      storyboard.addObjectDiagramWith(targetGraph, targetGraph.getGcs());

      storyboard.add(systemout);


      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("<h2>Migrate in Java: </h2>");

      graph = createExampleGraph();

      graph = simpleMigrationInJava(graph);

      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "simpleMigrationInJava(Graph)"));

      storyboard.add("Result graph: ");

      storyboard.addObjectDiagramWith(graph, graph.getGcs());


      storyboard.add(systemout);


      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("<h2>Migrate using Serialisation / Clone : </h2>");

      graph = createExampleGraph();

      graph = simpleMigrationByCloning(graph);

      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "simpleMigrationByCloning(Graph)"));

      storyboard.add("Result graph: ");

      storyboard.addObjectDiagramWith(graph, graph.getGcs());


      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("<h2>Migrate using JSON Array representation : </h2>");

      graph = createExampleGraph();

      graph = simpleMigrationByJsonArray(graph);

      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "simpleMigrationByJsonArray(Graph)"));

      storyboard.add("Result graph: ");

      storyboard.addObjectDiagramWith(graph, graph.getGcs());


      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("<h2>Migrate using Generic Graph representation : </h2>");

      graph = createExampleGraph();

      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "simpleMigrationByGenericGraph(Graph,Storyboard)"));

      Graph tgtGraph = simpleMigrationByGenericGraph(graph, storyboard);

      storyboard.add("Result graph: ");

      storyboard.addObjectDiagramWith(tgtGraph, tgtGraph.getGcs());


      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("<h2>Even more evolved graph model : </h2>");

      model = new ClassModel("org.sdmlib.examples.helloworld.model");

      graphClazz = model.createClazz("Graph");

      nodeClazz = model.createClazz("Node")
            .withAttribute("text", DataType.STRING);

      graphClazz.withAssoc(nodeClazz, "nodes", Card.MANY, "graph", Card.ONE);

      nodeClazz.withAssoc(nodeClazz, "linksTo", Card.MANY, "linksFrom", Card.MANY);

      model.generate("src/test/java");

      storyboard.addClassDiagram(model);

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

      storyboard.add("<hr/>");
      storyboard.add("Source model:");

      ClassModel model = new ClassModel("org.sdmlib.examples.helloworld.model");

      Clazz graphClazz = model.createClazz("Graph");

      Clazz edgeClazz = model.createClazz("Edge")
            .withAttribute("name", DataType.STRING);

      Clazz nodeClazz = model.createClazz("Node")
            .withAttribute("name", DataType.STRING);

      new Association()
      .withTarget(nodeClazz, "nodes", Card.MANY)
      .withSource(graphClazz, "graph", Card.ONE);

      new Association()
      .withTarget(edgeClazz, "edges", Card.MANY)
      .withSource(graphClazz, "graph", Card.ONE);

      new Association()
      .withTarget(nodeClazz, "src", Card.ONE)
      .withSource(edgeClazz, "outEdges", Card.MANY);

      new Association()
      .withTarget(nodeClazz, "tgt", Card.ONE)
      .withSource(edgeClazz, "inEdges", Card.MANY);

      // model.removeAllGeneratedCode("examples", "examples", "examples");

      model.generate("src/test/java");

      storyboard.addClassDiagram(model);


      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("Target model:");

      model = new ClassModel("org.sdmlib.examples.helloworld.model");

      graphClazz = model.createClazz("Graph");

      Clazz graphComponentClazz = model.createClazz("GraphComponent")
            .withAttribute("text", DataType.STRING);

      edgeClazz = model.createClazz("Edge")
            .withSuperClazz(graphComponentClazz);

      nodeClazz = model.createClazz("Node")
            .withSuperClazz(graphComponentClazz);

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

      model.generate("src/test/java");

      storyboard.addClassDiagram(model);


      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("Create example source graph: ");

      Graph graph = createExampleGraph();

      storyboard.addObjectDiagramWith(graph.getNodes(), graph.getEdges());;

      // storyboard.dumpHTML();

      //==========================================================================

      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("<h2>Migrate using Generic Graph representation : </h2>");

      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "simpleMigrationByGenericGraph(Graph,Storyboard)"));

      Graph tgtGraph = simpleMigrationByGenericGraph(graph, storyboard);

      storyboard.add("Result graph: ");

      storyboard.addObjectDiagramWith(tgtGraph, tgtGraph.getGcs());


      //==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("<h2>Even more evolved graph model : </h2>");

      model = new ClassModel("org.sdmlib.examples.helloworld.model");

      graphClazz = model.createClazz("Graph");

      nodeClazz = model.createClazz("Node")
            .withAttribute("text", DataType.STRING);

      graphClazz.withAssoc(nodeClazz, "nodes", Card.MANY, "graph", Card.ONE);

      nodeClazz.withAssoc(nodeClazz, "linksTo", Card.MANY, "linksFrom", Card.MANY);

      model.generate("src/test/java");

      storyboard.addClassDiagram(model);

      graph = createExampleGraph();

      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "simpleMigrationToEvenMoreEvolvedGraphByGenericGraph(Graph,Storyboard)"));

      tgtGraph = simpleMigrationToEvenMoreEvolvedGraphByGenericGraph(graph, storyboard);

      storyboard.add("Result graph: ");

      storyboard.addObjectDiagram(tgtGraph);


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

      storyboard.addObjectDiagram(graph);

      storyboard.add("Transformation:");

      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "deleteNodeWithNameN1(Graph)"));

      NodePO nodeN1PO = deleteNodeWithNameN1(graph);

      storyboard.addPattern(nodeN1PO, false);

      storyboard.add("Result graph:");

      storyboard.addObjectDiagram(graph);

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

      storyboard.addObjectDiagram(graph);

      int noOfMatches = insertTransitiveEdges(graph);

      storyboard.add("Transformation:");

      storyboard.add(storyboard.getMethodText("examples", this.getClass().getName(), "insertTransitiveEdges(Graph)"));

      storyboard.addPattern(graphPO, false);

      storyboard.add("Transformation with matches:");

      storyboard.addPattern(graphPO, true);

      storyboard.add("We have inserted " + noOfMatches + " new transitive edges. Result graph:");

      storyboard.addObjectDiagram(graph);

      storyboard.dumpHTML();
   }


   private int insertTransitiveEdges(Graph graph)
   {

      graphPO = new GraphPO(graph).withPatternObjectName("graphPO2");

      EdgePO firstEdgePO = graphPO.hasEdges();

      EdgePO secondEdgePO = graphPO.hasEdges();

      NodePO joinNodePO = firstEdgePO.hasTgt();

      secondEdgePO.hasSrc(joinNodePO);

      NodePO startNodePO = firstEdgePO.hasSrc();

      NodePO endNodePO = secondEdgePO.hasTgt();

      startNodePO.startNAC().hasOutEdges().hasTgt(endNodePO).endNAC();

      startNodePO.startCreate().hasOutEdges().hasTgt(endNodePO);

      return graphPO.getPattern().allMatches();
   }

   
   private NodePO deleteNodeWithNameN1(Graph graph)
   {
      // find node
      NodePO nodeN1PO = new GraphPO(graph)
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

      return nodeN1PO;
   }


   private Graph simpleMigrationToEvenMoreEvolvedGraphByGenericGraph(Graph origGraph, Storyboard storyboard)
   {
      GenericGraph genGraph = new Specific2Generic()
      .convert(GraphCreator.createIdMap("g1"), origGraph);

      // rename name to text attributes
      GenericGraphPO graphPO = new GenericGraphPO(genGraph);
      graphPO
      .hasObjects()
      .hasAttrs()
      .hasName(Node.PROPERTY_NAME)
      .startCreate()
      .hasName(Node.PROPERTY_TEXT)
      .allMatches();

      storyboard.addPattern(graphPO, false);

      // replace n.l.e.l.n by n.l.n
      GenericObjectPO edgePO = new GenericGraphPO(genGraph)
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

      storyboard.addPattern(edgePO, false);

      // destroy dangling edges
      GenericObjectPO danglingPO = new GenericGraphPO(genGraph)
      .hasObjects()
      .hasType(Edge.class.getName());

      danglingPO.destroy();

      danglingPO.allMatches();

      storyboard.addPattern(danglingPO, false);

      Graph tgtGraph = (Graph) new Generic2Specific().convert(GraphCreator.createIdMap("tg"), null, genGraph);

      return tgtGraph;
   }


   private Graph simpleMigrationByGenericGraph(Graph origGraph, Storyboard storyboard)
   {
      GenericGraph genGraph = new Specific2Generic()
      .convert(GraphCreator.createIdMap("g1"), origGraph);

      storyboard.addObjectDiagramWith(genGraph.getObjects(), genGraph.getLinks(), genGraph.getObjects().getAttrs());

      // rename name to text attributes
      GenericGraphPO graphPO = new GenericGraphPO(genGraph);
      graphPO.hasObjects()
      .hasAttrs()
      .hasName(Node.PROPERTY_NAME)
      .startCreate()
      .hasName(Node.PROPERTY_TEXT)
      .allMatches();

      storyboard.addPattern(graphPO, false);

      // storyboard.add("<hr/>");

      // rename graph--nodes links to parent--gcs links
      graphPO = new GenericGraphPO(genGraph);
      graphPO
      .hasLinks()
      .hasTgtLabel(Node.PROPERTY_GRAPH)
      .startCreate()
      .hasTgtLabel(Node.PROPERTY_PARENT)
      .hasSrcLabel(Graph.PROPERTY_GCS)
      .allMatches();

      storyboard.addPattern(graphPO, false);

      // storyboard.add("<hr/>");

      // rename graph--edges links to parent--gcs links
      graphPO = new GenericGraphPO(genGraph);
      graphPO
      .hasLinks()
      .hasSrcLabel(Node.PROPERTY_GRAPH)
      .startCreate()
      .hasSrcLabel(Node.PROPERTY_PARENT)
      .hasTgtLabel(Graph.PROPERTY_GCS)
      .allMatches();

      storyboard.addPattern(graphPO, false);

      storyboard.addObjectDiagramWith(genGraph.getObjects(), genGraph.getLinks(), genGraph.getObjects().getAttrs());

      Graph tgtGraph = (Graph) new Generic2Specific().convert(GraphCreator.createIdMap("tg"), null, genGraph);

      return tgtGraph;
   }


   private Graph simpleMigrationByJsonArray(Graph graph)
   {
      JsonIdMap idMap = GraphCreator.createIdMap("hg");

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

      Graph copyGraph = (Graph) GraphCreator.createIdMap("hg").decode(jsonArray);

      return copyGraph;
   }


   private Graph simpleMigrationByCloning(Graph graph)
   {
      JsonArray jsonArray = GraphCreator.createIdMap("hg").toJsonArray(graph);

      Graph copyGraph = (Graph) GraphCreator.createIdMap("hg").decode(jsonArray);

      GraphComponentSet gcs = new GraphComponentSet();
      gcs.addAll(copyGraph.getNodes());
      gcs.addAll(copyGraph.getEdges());

      for (GraphComponent comp : gcs)
      {
         comp.setParent(copyGraph);
         comp.setGraph(null);

         comp.setText(comp.getName());
         comp.setName(null);
      }

      return copyGraph;
   }


   private Graph simpleMigrationInJava(Graph origGraph)
   {
      int noOfMatches = 0;

      Graph copyGraph = new Graph();

      if (origGraph != null)
      {
         // migrate nodes
         for (Node origNode : origGraph.getNodes())
         {
            Node copyNode = (Node) copyGraph.createGcsNode()
                  .withText(origNode.getName());

            copyNode.withOrig(origNode);

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

      return copyGraph;
   }


   private Graph simpleMigrationPerPattern(Graph graph, Storyboard storyboard)
   {
      //==========================================================================
      // create target graph
      srcGraphPO = new GraphPO(graph);

      GraphPO tgtGraphPO = (GraphPO) new GraphPO().withPattern(srcGraphPO.getPattern()).withModifier(Pattern.CREATE);
      tgtGraphPO.findNextMatch();

      Graph result = tgtGraphPO.getCurrentMatch();

      storyboard.addPattern(srcGraphPO, false);

      //==========================================================================
      // copy nodes
      int noOfMatches = 0;

      srcGraphPO = new GraphPO(graph);

      NodePO srcNodePO = srcGraphPO.hasNodes();

      tgtGraphPO = (GraphPO) new GraphPO(tgtGraphPO.getCurrentMatch())
      .withPattern(srcGraphPO.getPattern());

      srcGraphPO.startCreate();

      NodePO tgtNodePO = tgtGraphPO.hasGcsNode();

      tgtNodePO.hasOrig(srcNodePO);

      while (srcGraphPO.getPattern().getHasMatch())
      {
         tgtNodePO.withText(srcNodePO.getName());

         noOfMatches++;

         srcGraphPO.getPattern().findNextMatch();
      }

      systemout = "Number of migrated nodes: " + noOfMatches;

      storyboard.addPattern(srcGraphPO, false);

      //==========================================================================
      noOfMatches = 0;

      srcGraphPO = new GraphPO(graph);

      EdgePO srcEdgePO = srcGraphPO.hasEdges();

      tgtGraphPO = new GraphPO(tgtGraphPO.getCurrentMatch()).withPattern(srcGraphPO.getPattern());

      tgtGraphPO.startCreate();

      EdgePO tgtEdgePO = tgtGraphPO.hasGcsEdge();

      boolean done = false;

      while (tgtEdgePO.getPattern().getHasMatch())
      {
         tgtEdgePO.withText(srcEdgePO.getName());

         copySrcNodePO = new EdgePO(srcEdgePO.getCurrentMatch())
         .hasSrc()
         .hasCopy();

         EdgePO copyEdgePO = new EdgePO(tgtEdgePO.getCurrentMatch()).withPattern(copySrcNodePO.getPattern());

         copySrcNodePO.startCreate();

         copyEdgePO.hasSrc(copySrcNodePO);


         copyTgtNodePO = new EdgePO(srcEdgePO.getCurrentMatch())
         .hasTgt()
         .hasCopy();

         EdgePO copyEdgePO2 = new EdgePO(tgtEdgePO.getCurrentMatch()).withPattern(copyTgtNodePO.getPattern());

         copyTgtNodePO.startCreate();

         copyEdgePO2.hasTgt(copyTgtNodePO);

         noOfMatches++;

         tgtEdgePO.getPattern().findNextMatch();
      }

      systemout += "\nNumber of migrated Edges: " + noOfMatches;

      storyboard.addPattern(tgtEdgePO, false);
      storyboard.addPattern(copySrcNodePO, false);
      storyboard.addPattern(copyTgtNodePO, false);

      return result;
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
      graphPO = new GraphPO(graph);

      NodePO nodePO = graphPO.hasNodes();

      int noOfNodes = graphPO.getPattern().allMatches();

      systemout = "Number of nodes: " + noOfNodes;
   }


   public void countNodesPerNodeSet(Graph graph)
   {
      graphPO = new GraphPO(graph);

      NodeSet allMatches = graphPO
            .hasNodes()
            .allMatches();

      systemout = "Nodes: " + allMatches.getName().concat(", ") + " Number of nodes: " + allMatches.size();      
   }


   public void countLoopingEdgesPerPattern(Graph graph)
   {
      edgesPO = new GraphPO(graph)
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


   public void countIsolatedNodesPerPattern(Graph graph)
   {
      graphPO = new GraphPO(graph);

      NodePO nodePO = graphPO.hasNodes();

      nodePO.startNAC().hasOutEdges().endNAC();

      nodePO.startNAC().hasInEdges().endNAC();

      NodeSet isolatedNodes = nodePO.allMatches();

      systemout = "Isolated nodes: " + isolatedNodes.getName().concat(", ") + " Number of isolated nodes: " + isolatedNodes.size();
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


   public void countCirclesOfThreeNodesPerPattern(Graph graph)
   {
      graphPO = new GraphPO(graph);

      NodePO firstCircleNodePO = graphPO.hasNodes();

      NodePO secondCircleNodePO = firstCircleNodePO.hasOutEdges().hasTgt();

      NodePO thirdCircleNodePO = secondCircleNodePO.hasOutEdges().hasTgt();

      thirdCircleNodePO.hasOutEdges().hasTgt(firstCircleNodePO);

      graphPO.getPattern().matchIsomorphic();

      int noOfCircles = graphPO.getPattern().allMatches();

      systemout = "Circles found: " + noOfCircles;
   }


   public void countCirclesOfThreeNodesPerPatternReportMatches(Graph graph)
   {
      graphPO = new GraphPO(graph);

      NodePO firstCircleNodePO = graphPO.hasNodes();

      NodePO secondCircleNodePO = firstCircleNodePO.hasOutEdges().hasTgt();

      NodePO thirdCircleNodePO = secondCircleNodePO.hasOutEdges().hasTgt();

      thirdCircleNodePO.hasOutEdges().hasTgt(firstCircleNodePO);

      graphPO.getPattern().matchIsomorphic();

      systemout = "Circles found: \n";
      int noOfCircles = 0;

      while (graphPO.getPattern().getHasMatch())
      {
         systemout += firstCircleNodePO.getName() + " --> "
               + secondCircleNodePO.getName() + " --> " 
               + thirdCircleNodePO.getName() + " --> \n";

         noOfCircles++; 

         graphPO.getPattern().findNextMatch();
      }

      systemout += "" + noOfCircles + " circles found";
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


   public void countDanglingEdgesPerPattern(Graph graph)
   {
      edgesPO = new GraphPO(graph).hasEdges();

      edgesPO.startNAC().hasTgt();

      edgesPO.hasSrc().endNAC();

      EdgeSet allMatches = edgesPO.allMatches();

      systemout = "Dangling edges " + allMatches.getName().concat(", ") + " number: " + allMatches.size();
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
      edgesPO = new GraphPO(graph).hasEdges();

      NodePO srcPO = edgesPO.hasSrc();

      NodePO tgtPO = edgesPO.hasTgt();

      edgesPO.startCreate();

      edgesPO.hasSrc(tgtPO);

      edgesPO.hasTgt(srcPO);

      systemout = "Number of reversed edges: " + edgesPO.getPattern().allMatches();
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
