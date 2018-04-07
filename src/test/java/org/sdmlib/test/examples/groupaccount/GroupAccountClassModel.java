package org.sdmlib.test.examples.groupaccount;


import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class GroupAccountClassModel
{
     /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountClassModel.java' type='text/x-java'>GroupAccountCodegen</a></p>
    * <p>Start situation: Nothing here yet. Generate classes</p>
    * <img src="doc-files/GroupAccountCodegenStep0.png"></img>
    * @see <a href='../../../../../../../../doc/GroupAccountCodegen.html'>GroupAccountCodegen.html</a>
*/
   @Test
   public void testGroupAccountCodegen()
   {
      Storyboard storyboard = new Storyboard();
      
      storyboard.add("Start situation: Nothing here yet. Generate classes");
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.groupaccount.model");

      Clazz partyClass = model.createClazz("Party")
            .withAttribute("partyName", DataType.STRING)
            .withAttribute("total", DataType.DOUBLE)
            .withAttribute("share", DataType.DOUBLE)
            ;

      Clazz personClass = model.createClazz("Person")
            .withAttribute("name", DataType.STRING)
            .withAttribute("total", DataType.DOUBLE)
            .withAttribute("saldo", DataType.DOUBLE)
            ;

      partyClass.createBidirectional(personClass, "guests", Cardinality.MANY, 
         "party", Cardinality.ONE);

      Clazz itemClass = model.createClazz("Item")
            .withAttribute("description", DataType.STRING)
            .withAttribute("price", DataType.DOUBLE);

      personClass.createBidirectional(itemClass, "items", Cardinality.MANY, 
         "person", Cardinality.ONE);

      model.generate("src/test/java");
      
      storyboard.addClassDiagramAsImage(model, 400, 350);
      
      storyboard.dumpHTML();
   }
}
