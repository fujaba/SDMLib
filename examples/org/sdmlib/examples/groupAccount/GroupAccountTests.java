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
   
package org.sdmlib.examples.groupAccount;
   
import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import static org.sdmlib.models.classes.Role.R.*;
import org.sdmlib.models.transformations.TransformOp;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.scenarios.ScenarioManager;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.classes.Method;
   
public class GroupAccountTests 
{
   @Test
   public void testGroupAccountRuleRecognition()
   {
      Scenario scenario = new Scenario("examples");
      
      scenario.add("Start situation: classes have been generated. Now create some example object structure",
         MODELING, "zuendorf", "04.04.2012 00:26:59", 0, 0);
      
      GroupAccount groupAccount = new GroupAccount();
      
      Person albert = groupAccount.createPersons()
      .withName("Albert");
      
      Person nina = groupAccount.createPersons()
      .withName("Nina");
      
      Person artjom = new Person()
      .withName("Artjom")
      .withParent(groupAccount);

      Item beer = new Item()
      .withBuyer(albert)
      .withDescription("Beer")
      .withValue(12)
      .withParent(groupAccount);
      
      Item bread = new Item()
      .withBuyer(nina)
      .withDescription("Bread")
      .withValue(3)
      .withParent(groupAccount);
      
      Item meat = new Item()
      .withBuyer(artjom)
      .withDescription("Meat")
      .withValue(6)
      .withParent(groupAccount);

      // JsonIdMap createIdMap = org.sdmlib.examples.groupAccount.creators.GroupAccountCreator.createIdMap("az42");
      scenario.addObjectDiag(groupAccount);
      
      scenario.add("We will call updateBalances() to compute the correct balances.\n",
         MODELING, "zuendorf", "15.04.2012 17:18:42", 8, 2);
      
      scenario.markCodeStart();
      groupAccount.updateBalances();
      scenario.addCode("examples");
      
      scenario.add("Now see updateBalances() first as code and then as a graphical model transformation operation diagram.");
      
      String methodText = scenario.getMethodText("examples", "org.sdmlib.examples.groupAccount.GroupAccount", "updateBalances()");
      
      scenario.add(methodText);
      
      TransformOp transformOp = new TransformOp()
      .withName("org.sdmlib.examples.groupAccount.GroupAccount.updateBalances()");
      
      transformOp.updateFromSourceCode("examples", "org.sdmlib.examples.groupAccount");
      
      scenario.addImage(transformOp.dumpTransformOpDiagram("GroupAccount.UpdateBalances02"));
      
      scenario.add("The effects of executing updateBalances() is shown in: ");
      
      scenario.addObjectDiag(groupAccount);

      scenario.assertEquals("Balance for Albert is now ", 5, albert.getBalance(), 0.0001);

      scenario.assertEquals("The sum of all balances is ", 0, groupAccount.getPersons().getBalance().sum(), 0.0001);
      
      scenario.dumpHTML();
   }

   @Test
   public void testGroupAccountCodegen()
   {
      Scenario scenario = new Scenario("examples");
      
      scenario.add("Start situation: Nothing here yet. Generated classes",
         DONE, "zuendorf", "04.04.2012 00:11:32", 1, 0);
      
      ClassModel model = new ClassModel("org.sdmlib.examples.groupAccount");
      
      Clazz groupAccountClass = new Clazz("GroupAccount")
      .withMethods("initAccounts(double,String)", DOUBLE)
      .withMethods("updateBalances()", "void");
      
      //      groupAccountClass.withRunningConstants("RED", "YELLOW", "GREEN");
      //      groupAccountClass.withConstant("NORTH", "north");
      //      groupAccountClass.withConstant("CARD", 3);
      
      Clazz personClass = new Clazz(
         "Person",
         "name", STRING,
         "balance", DOUBLE);
      
      groupAccountClass.withAssoc(personClass, "persons", MANY, "parent", ONE);
      
      Clazz itemClass = new Clazz("Item", 
         "description", STRING, 
         "value", DOUBLE);
      
      groupAccountClass.withAssoc(itemClass, "items", MANY, "parent", ONE);
      
      new Association()
      .withSource("buyer", personClass, ONE)
      .withTarget("items", itemClass, MANY);

      // model.updateFromCode("examples", "examples", "org.sdmlib.examples.groupAccount");
      
      // model.insertModelCreationCodeHere("examples");
      
      // model.removeAllGeneratedCode("examples", "examples", "examples");
      
      model.generate("examples", "examplehelpers");
      
      scenario.addImage(model.dumpClassDiag("examples", "GroupAccountClassDiag01"));

      scenario.add("Resolved Bug: creatorcreator class is no longer growing on each run. ",
         DONE, "zuendorf", "24.05.2012 00:16:18", 1, 0);
      
      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();
   }

   private static final String MODELING = "modeling";
   private static final String ACTIVE = "active";
   private static final String DONE = "done";
   private static final String IMPLEMENTATION = "implementation";
   private static final String BACKLOG = "backlog";
}

