package org.sdmlib.test.examples.groupaccount;


import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Parameter;
import org.sdmlib.storyboards.Kanban;
import org.sdmlib.storyboards.Storyboard;

public class GroupAccountClassModel
{
   @Test
   public void testGroupAccountCodegen()
   {
      Storyboard storyboard = new Storyboard();
      
      storyboard.setSprint("Sprint.002.Examples"); 
      
      storyboard.add("Start situation: Nothing here yet. Generate classes",
         Kanban.DONE, "zuendorf", "04.04.2012 00:11:32", 1, 0);
      
      ClassModel model = new ClassModel("org.sdmlib.examples.groupAccount.model");
      
      Clazz groupAccountClass = model.createClazz("GroupAccount");
            
      groupAccountClass.createMethod("getTaskNames")
            .with(new Parameter(DataType.DOUBLE))
            .with(new Parameter(DataType.STRING))
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
      
      groupAccountClass.withAssoc(itemClass, "item", Card.MANY, "parent", Card.ONE);
      
      personClass.withAssoc(itemClass, "item", Card.MANY, "buyer", Card.ONE);

      // model.updateFromCode("examples", "examples", "org.sdmlib.examples.groupAccount");
      
      // model.insertModelCreationCodeHere("examples");
      
      // model.removeAllGeneratedCode("examples");
      
      model.generate("src/test/java");
      
      storyboard.addClassDiagram(model);
      
      storyboard.dumpHTML();
   }
}
