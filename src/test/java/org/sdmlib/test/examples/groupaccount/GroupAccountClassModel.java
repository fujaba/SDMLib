package org.sdmlib.test.examples.groupaccount;


import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.StoryPage;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class GroupAccountClassModel
{
     /**
    * 
    * @see <a href='../../../../../../../../doc/GroupAccountCodegen.html'>GroupAccountCodegen.html</a>
*/
   @Test
   public void testGroupAccountCodegen()
   {
      StoryPage storyboard = new StoryPage();
      
      storyboard.add("Start situation: Nothing here yet. Generate classes");
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.groupaccount.model");
      
      Clazz groupAccountClass = model.createClazz("GroupAccount")
            .withAttribute("task", DataType.STRING);
            
      groupAccountClass.createMethod("updateBalances");
      
      Clazz personClass = model.createClazz("Person")
            .withAttribute("name", DataType.STRING)
            .withAttribute("balance", DataType.DOUBLE);

      
      groupAccountClass.withBidirectional(personClass, "persons", Cardinality.MANY, "parent", Cardinality.ONE);
      
      Clazz itemClass = model.createClazz("Item") 
            .withAttribute("description", DataType.STRING)
            .withAttribute("value", DataType.DOUBLE);
      
      personClass.withBidirectional(itemClass, "item", Cardinality.MANY, "buyer", Cardinality.ONE);

      // model.updateFromCode("examples", "examples", "org.sdmlib.test.examples.groupAccount");
      
      // model.insertModelCreationCodeHere("examples");
      
      // model.removeAllGeneratedCode("examples");
      
      model.generate("src/test/java");
      
      storyboard.addClassDiagram(model);
      
      storyboard.dumpHTML();
   }
}
