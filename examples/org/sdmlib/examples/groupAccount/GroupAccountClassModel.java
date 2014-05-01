package org.sdmlib.examples.groupAccount;


import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Parameter;
import org.sdmlib.models.classes.SDMLibConfig;
import org.sdmlib.storyboards.Storyboard;

public class GroupAccountClassModel
{
   @Test
   public void testGroupAccountCodegen()
   {
      Storyboard storyboard = new Storyboard("examples");
      
      storyboard.setSprint("Sprint.002.Examples"); 
      
      storyboard.add("Start situation: Nothing here yet. Generate classes",
         SDMLibConfig.DONE, "zuendorf", "04.04.2012 00:11:32", 1, 0);
      
      ClassModel model = new ClassModel("org.sdmlib.examples.groupAccount.model");
      
      Clazz groupAccountClass = model.createClazz("GroupAccount");
            
      groupAccountClass.createMethod("getTaskNames")
            .withParameter(new Parameter(DataType.DOUBLE))
            .withParameter(new Parameter(DataType.STRING))
            .withReturnType(DataType.DOUBLE);
      
      groupAccountClass.createMethod("updateBalances");
      
      //      groupAccountClass.withRunningConstants("RED", "YELLOW", "GREEN");
      //      groupAccountClass.withConstant("NORTH", "north");
      //      groupAccountClass.withConstant("CARD", 3);
      
      Clazz personClass = model.createClazz("Person")
            .withAttribute("name", DataType.STRING)
            .withAttribute("balance", DataType.DOUBLE);

      
      groupAccountClass.withAssoc(personClass, "persons", Card.MANY, "parent", Card.ONE);
      
      Clazz itemClass = model.createClazz("Item") 
            .withAttribute("description", DataType.STRING)
            .withAttribute("value", DataType.DOUBLE);
      
      groupAccountClass.withAssoc(itemClass, "items", Card.MANY, "parent", Card.ONE);
      
      new Association()
      .withSource("buyer", personClass, Card.ONE)
      .withTarget("items", itemClass, Card.MANY);

      // model.updateFromCode("examples", "examples", "org.sdmlib.examples.groupAccount");
      
      // model.insertModelCreationCodeHere("examples");
      
      // model.removeAllGeneratedCode("examples", "examples", "examples");
      
      model.generate("examples");
      
      storyboard.addClassDiagram(model);
      
      storyboard.dumpHTML();
   }
}
