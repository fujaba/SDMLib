package org.sdmlib.examples.helloworld;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.codegen.CGUtil;
import org.sdmlib.examples.helloworld.creators.CreatorCreator;
import org.sdmlib.examples.helloworld.creators.GreetingMessagePO;
import org.sdmlib.examples.helloworld.creators.GreetingPO;
import org.sdmlib.examples.helloworld.creators.ModelPattern;
import org.sdmlib.examples.helloworld.creators.PersonPO;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.scenarios.Scenario;

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
      
      PatternObject greetingPO = p.hasElementGreetingMessagePO()
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
}