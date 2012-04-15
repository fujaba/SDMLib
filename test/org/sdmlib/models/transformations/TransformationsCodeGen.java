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
   
package org.sdmlib.models.transformations;
   
import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.scenarios.LogEntry;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.scenarios.ScenarioManager;
   
public class TransformationsCodeGen 
{
   @Test
   public void testTransformationsCodegen()
   {
      Scenario scenario = new Scenario("TransformationsCodegen");
      
      scenario.add("classes for model transformations:",
         MODELING, "zuendorf", "05.04.2012 16:24:42", 2, 0);
      
      ClassModel model = new ClassModel();
      
      Clazz transformOpClass = new Clazz("org.sdmlib.models.transformations.TransformOp")
      .withAttribute("name", "String");
      
      Clazz operationObjectClass = new Clazz("org.sdmlib.models.transformations.OperationObject")
      .withAttribute("name", "String")
      .withAttribute("type", "String")
      .withAttribute("set", "boolean");
      
      new Association()
      .withSource("transformOp", transformOpClass, Role.ONE)
      .withTarget("opObjects", operationObjectClass, Role.MANY);
      
      Clazz attributeOpClass = new Clazz("org.sdmlib.models.transformations.AttributeOp")
      .withAttribute("text", "String");
      
      new Association()
      .withSource("operationObject", operationObjectClass, Role.ONE)
      .withTarget("attributeOps", attributeOpClass, Role.MANY);

      Clazz linkOpClass = new Clazz("org.sdmlib.models.transformations.LinkOp")
      .withAttribute("srcText", "String")
      .withAttribute("tgtText", "String");

      new Association()
      .withSource("src", operationObjectClass, Role.ONE)
      .withTarget("outgoings", linkOpClass, Role.MANY);
      
      new Association()
      .withSource("incomings", linkOpClass, Role.MANY)
      .withTarget("tgt", operationObjectClass, Role.ONE);
      
      new Association()
      .withSource("transformOp", transformOpClass, Role.ONE)
      .withTarget("linkOps", linkOpClass, Role.MANY);
      
      
      Clazz statementClass = new Clazz("org.sdmlib.models.transformations.Statement")
      .withAttribute("text", "String");

      new Association()
      .withSource("prev", statementClass, Role.ONE)
      .withTarget("next", statementClass, Role.ONE);
      
      new Association()
      .withSource("statements", statementClass, Role.MANY)
      .withTarget("operationObjects", operationObjectClass, Role.MANY);

      new Association()
      .withSource("transformOp", transformOpClass, Role.ONE)
      .withTarget("statements", statementClass, Role.MANY);
      
      
      scenario.addImage(model.dumpClassDiag("TransformationClasses01"));
      
      
      model.generate("src", "srchelpers");
      
      scenario.add("create some example transformOp:",
         MODELING, "zuendorf", "05.04.2012 16:31:42", 1, 0);
      
      TransformOp transformOp = new TransformOp();
      
      OperationObject thisOp = new OperationObject()
      .withName("this")
      .withTransformOp(transformOp);
      
      OperationObject itemsOp = new OperationObject()
      .withName("items")
      .withSet(true)
      .withTransformOp(transformOp);
      
      AttributeOp valueOp = new AttributeOp()
      .withText("value")
      .withOperationObject(itemsOp);
      
      LinkOp thisToItems = new LinkOp()
      .withSrc(thisOp)
      .withTgt(itemsOp)
      .withTgtText("get")
      .withTransformOp(transformOp);

      Statement sumStat = new Statement()
      .withText("total = sum()")
      .withOperationObjects(itemsOp)
      .withTransformOp(transformOp);
      
      OperationObject personsOp = new OperationObject()
      .withName("persons")
      .withSet(true)
      .withTransformOp(transformOp);

      LinkOp thisToPersons = new LinkOp()
      .withSrc(thisOp)
      .withTgt(personsOp)
      .withTgtText("get")
      .withTransformOp(transformOp);

      Statement shareStat = new Statement()
      .withText("share = total / size()")
      .withOperationObjects(personsOp)
      .withPrev(sumStat)
      .withTransformOp(transformOp);
      
      OperationObject personOp = new OperationObject()
      .withName("person")
      .withTransformOp(transformOp);

      LinkOp personsToPerson = new LinkOp()
      .withSrc(personsOp)
      .withTgt(personOp)
      .withTgtText("foreach")
      .withTransformOp(transformOp);

      OperationObject personItemsOp = new OperationObject()
      .withName("personItems")
      .withSet(true)
      .withTransformOp(transformOp);
      
      AttributeOp myValueOp = new AttributeOp()
      .withText("value")
      .withOperationObject(personItemsOp);
      

      LinkOp personToItems = new LinkOp()
      .withSrc(personOp)
      .withTgt(personItemsOp)
      .withTgtText("get")
      .withTransformOp(transformOp);

      Statement myCostsStat = new Statement()
      .withText("myCosts = sum()")
      .withOperationObjects(personItemsOp)
      .withPrev(shareStat)
      .withTransformOp(transformOp);
      
      Statement assingStat = new Statement()
      .withText("setBalance(myCosts - share)")
      .withOperationObjects(personOp)
      .withPrev(myCostsStat)
      .withTransformOp(transformOp);
      
      scenario.add("dump an image from the transformOp:",
         MODELING, "zuendorf", "06.04.2012 15:19:42", 4, 0);
      
      scenario.addImage(transformOp.dumpTransformOpDiagram("updateBalanceTrafoOpDiag01"));
      
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


