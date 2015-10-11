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
     /**
    * 
    * @see <a href='../../../../../../../../doc/GroupAccountCodegen.html'>GroupAccountCodegen.html</a>
*/
   @Test
   public void testGroupAccountCodegen()
   {
      Storyboard storyboard = new Storyboard();
      
      storyboard.add("Start situation: Nothing here yet. Generate classes");
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.groupaccount.model");
      
      Clazz groupAccountClass = model.createClazz("GroupAccount")
            .withAttribute("task", DataType.STRING);
            
      groupAccountClass.createMethod("getTaskNames")
            .with(new Parameter(DataType.DOUBLE))
            .with(new Parameter(DataType.STRING))
            .withReturnType(DataType.DOUBLE);
      
      groupAccountClass.createMethod("updateBalances");
      
      Clazz personClass = model.createClazz("Person")
            .withAttribute("name", DataType.STRING)
            .withAttribute("balance", DataType.DOUBLE);

      
      groupAccountClass.withAssoc(personClass, "persons", Card.MANY, "parent", Card.ONE);
      
      Clazz itemClass = model.createClazz("Item") 
            .withAttribute("description", DataType.STRING)
            .withAttribute("value", DataType.DOUBLE);
      
      personClass.withAssoc(itemClass, "item", Card.MANY, "buyer", Card.ONE);

      // model.updateFromCode("examples", "examples", "org.sdmlib.test.examples.groupAccount");
      
      // model.insertModelCreationCodeHere("examples");
      
      // model.removeAllGeneratedCode("examples");
      
      model.generate("src/test/java");
      
      storyboard.addClassDiagram(model);
      
      storyboard.dumpHTML();
   }
}
