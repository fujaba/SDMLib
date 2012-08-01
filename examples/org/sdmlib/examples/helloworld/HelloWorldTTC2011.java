package org.sdmlib.examples.helloworld;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.codegen.CGUtil;
import org.sdmlib.examples.helloworld.creators.CreatorCreator;
import org.sdmlib.examples.helloworld.creators.EdgePO;
import org.sdmlib.examples.helloworld.creators.EdgeSet;
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
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.serialization.json.JsonFilter;
import org.sdmlib.serialization.json.JsonIdMap;

public class HelloWorldTTC2011
{
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
      
      Assert.assertTrue("Constant transformation has no match", p.getHasMatch());
      
      scenario.add("At runtime the object structure for the pattern and for the hostgraph looks like: ");
      
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
      
      scenario.recordSystemOut();
      
      scenario.markCodeStart();
      StringBuilder text = new StringBuilder("message name");
      
      CGUtil.replaceAll(text, 
         "message", greetingMessagePO.getText(),
         "name", personPO.getName());
      
      System.out.println(text.toString());
      scenario.addCode("examples");
      
      scenario.add("System.out: \n" + scenario.getSystemOut());
      
      //==========================================================================
      
      scenario.add("Alternatively, plain java code that does this model to text transformation looks like: ");
      
      scenario.markCodeStart();
      String result = greetingMessagePO.getText() + " " + personPO.getName();
      
      System.out.println(result);
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
      
      Graph graph = new Graph();
      
      Node n1 = graph.createNodes().withName("n1");
      Node n2 = graph.createNodes().withName("n2");
      Node n3 = graph.createNodes().withName("n3");
      Node n4 = graph.createNodes().withName("n4");
      Node n5 = graph.createNodes().withName("n5");
      Node n6 = graph.createNodes().withName("n6");
      Node n7 = graph.createNodes().withName("n7");
      Node n8 = graph.createNodes().withName("n8");
      graph.createEdges().withSrc(n1).withTgt(n2).withName("e10");
      graph.createEdges().withSrc(n2).withTgt(n3).withName("e11");
      graph.createEdges().withSrc(n3).withTgt(n1).withName("e12");
      graph.createEdges().withSrc(n3).withTgt(n4).withName("e13");
      graph.createEdges().withSrc(n4).withTgt(n5).withName("e14");
      graph.createEdges().withSrc(n5).withTgt(n3).withName("e15");
      graph.createEdges().withSrc(n8).withTgt(n8).withName("e16");
      graph.createEdges().withSrc(n8).withTgt(n8).withName("e17");

      JsonIdMap idMap = CreatorCreator.createIdMap("hg");
      scenario.addObjectDiag(idMap, n1, new JsonFilter(Node.PROPERTY_GRAPH));
      scenario.addObjectDiag(idMap, n6, new JsonFilter(Node.PROPERTY_GRAPH));
      scenario.addObjectDiag(idMap, n7, new JsonFilter(Node.PROPERTY_GRAPH));
      scenario.addObjectDiag(idMap, n8, new JsonFilter(Node.PROPERTY_GRAPH));
      
      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("<h2>Count nodes in graph direct: </h2>");
      
      scenario.markCodeStart();
      int noOfNodes = graph.getNodes().size();
      
      String systemout = "Number of nodes: " + noOfNodes;
      scenario.addCode("examples");
      
      scenario.add("systemout: " + systemout);
      
      
      //==========================================================================
      
      scenario.add("Count nodes in graph per pattern: ");
      
      scenario.markCodeStart();
      ModelPattern p = new ModelPattern();
      
      GraphPO graphPO = p.hasElementGraphPO(graph);
      
      NodePO nodePO = graphPO.hasNodes();
      
      noOfNodes = p.allMatches();
      
      systemout = "Number of nodes: " + noOfNodes;
      scenario.addCode("examples");
      
      scenario.add("systemout: " + systemout);
      
      
      //==========================================================================
      
      scenario.add("Retrieve set of matching nodes per pattern and count its elements: ");
      
      scenario.markCodeStart();
      p = new ModelPattern();
      
      graphPO = p.hasElementGraphPO(graph);
      
      nodePO = graphPO.hasNodes();
      
      NodeSet allMatches = nodePO.allMatches();
      
      systemout = "Nodes: " + allMatches.getName().concat(", ") + " Number of nodes: " + allMatches.size();
      scenario.addCode("examples");
      
      scenario.add("systemout: " + systemout);
      
      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("<h2>Retrieve set of looping edges per pattern and count its elements: </h2>");
      
      scenario.markCodeStart();
      p = new ModelPattern();
      
      graphPO = p.hasElementGraphPO(graph);
      
      EdgePO edgesPO = graphPO.hasEdges();
      
      NodePO srcPO = edgesPO.hasSrc();
      
      edgesPO.hasTgt(srcPO);
      
      EdgeSet loopingEdges = edgesPO.allMatches();
      
      systemout = "Looping Edges: " + loopingEdges.getName().concat(", ") + " Number of looping edges: " + loopingEdges.size();
      scenario.addCode("examples");
      
      scenario.add("systemout: " + systemout);
      
      scenario.add("For comparison a Java solution: ");
      
      scenario.markCodeStart();
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
      
      sysout = sysout.substring(0, sysout.length() - 2) + " Number of looping edges: " + noOfLoopingEdges;
      scenario.addCode("examples");
      
      scenario.add("sysout: " + sysout);
      
      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("<h2>Retrieve isolated nodes and count them: </h2>");
      
      scenario.markCodeStart();
      p = new ModelPattern();
      
      graphPO = p.hasElementGraphPO(graph);
      
      nodePO = graphPO.hasNodes();
      
      nodePO.startNAC().hasOutEdges().endNAC();
      
      nodePO.startNAC().hasInEdges().endNAC();

      NodeSet isolatedNodes = nodePO.allMatches();
      
      systemout = "Isolated nodes: " + isolatedNodes.getName().concat(", ") + " Number of isolated nodes: " + isolatedNodes.size();
      scenario.addCode("examples");
      
      scenario.add("systemout: " + systemout);
      
      
      scenario.add("For comparison a Java solution: ");
      
      scenario.markCodeStart();
      sysout = "Isolated nodes: ";
      
      int noOfIsolatedNodes = 0;
      
      for (Node isoNode : graph.getNodes())
      {
         if (isoNode.getOutEdges().size() == 0 
               && isoNode.getInEdges().size() == 0)
         {
            noOfIsolatedNodes++;
            
            sysout += isoNode.getName() + ", ";
         }
      }
      
      sysout = sysout.substring(0, sysout.length() - 2) + " Number of isolated nodes: " + noOfIsolatedNodes;
      scenario.addCode("examples");
      
      scenario.add("sysout: " + sysout);
      
      
      //==========================================================================
      
      scenario.add("<hr/>");
      scenario.add("<h2>Count circles of three nodes: </h2>");
      
      scenario.markCodeStart();
      p = new ModelPattern();
      
      graphPO = p.hasElementGraphPO(graph);
      
      NodePO firstCircleNodePO = graphPO.hasNodes();
      
      NodePO secondCircleNodePO = firstCircleNodePO.hasOutEdges().hasTgt();
      
      NodePO thirdCircleNodePO = secondCircleNodePO.hasOutEdges().hasTgt();
      
      thirdCircleNodePO.hasOutEdges().hasTgt(firstCircleNodePO);
      
      p.matchIsomorphic();
      
      int noOfCircles = p.allMatches();
      
      systemout = "Circles found: " + noOfCircles;
      scenario.addCode("examples");
      
      scenario.add("systemout: " + systemout);
      
      scenario.add("If you want to print the matched nodes for each circle, you need to iterate through the matches: ");
      
      scenario.markCodeStart();
      p = new ModelPattern();
      
      graphPO = p.hasElementGraphPO(graph);
      
      firstCircleNodePO = graphPO.hasNodes();
      
      secondCircleNodePO = firstCircleNodePO.hasOutEdges().hasTgt();
      
      thirdCircleNodePO = secondCircleNodePO.hasOutEdges().hasTgt();
      
      thirdCircleNodePO.hasOutEdges().hasTgt(firstCircleNodePO);
      
      p.matchIsomorphic();
      
      systemout = "Circles found: \n";
      noOfCircles = 0;
      
      while (p.getHasMatch())
      {
         systemout += firstCircleNodePO.getName() + " --> "
               + secondCircleNodePO.getName() + " --> " 
               + thirdCircleNodePO.getName() + " --> \n";
         
         noOfCircles++; 
         
         p.findNextMatch();
      }
      
      systemout += "" + noOfCircles + " circles found";
      scenario.addCode("examples");
      
      scenario.add("systemout: " + systemout);
      
      scenario.add("Just for comparison, a plain java implementation: ");
      
      scenario.markCodeStart();
      noOfCircles = 0;
      
      sysout = "Circles found: \n";
      
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
                           sysout += firstNode.getName() + " --> "
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
      
      sysout += "" + noOfCircles + " circles found";
      scenario.addCode("examples");
      
      scenario.add("sysout: " + sysout);
      
      scenario.dumpHTML();
   }


}
