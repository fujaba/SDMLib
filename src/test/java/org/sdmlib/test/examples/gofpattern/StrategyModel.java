package org.sdmlib.test.examples.gofpattern;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Modifier;
import de.uniks.networkparser.graph.Parameter;

public class StrategyModel
{
     /**
    * 
    * @see <a href='../../../../../../../../doc/GofStrategyModel.html'>GofStrategyModel.html</a>
*/
   @Test
   public void GofStrategyModel() 
   {
      Storyboard story = new Storyboard();
      
      ClassModel cm = new ClassModel("org.sdmlib.test.examples.gofpattern.strategy");

      Clazz bPlayer = cm
            .createClazz("BombermanPlayer")
            .withAttribute("xPosition", DataType.INT)
            .withAttribute("yPosition", DataType.INT)
            .withAttribute("numberOfBombs", DataType.INT)
            .withAttribute("lastKey", DataType.CHAR)
            .withAttribute("shortTest", DataType.create("short"))
            .withMethod("keyPress", DataType.VOID,
               new Parameter(DataType.STRING).with("key"));

      Clazz bStrategy = cm.createClazz("BombermanStrategy")
            .with(Modifier.ABSTRACT).withMethod("handleMove", DataType.VOID);

      cm.createClazz("MoveUp").withSuperClazz(bStrategy);
      cm.createClazz("MoveDown").withSuperClazz(bStrategy);
      cm.createClazz("MoveLeft").withSuperClazz(bStrategy);
      cm.createClazz("MoveRight").withSuperClazz(bStrategy);
      cm.createClazz("Blast").withSuperClazz(bStrategy);
      cm.createClazz("Stay").withSuperClazz(bStrategy);

      bStrategy.withUniDirectional(bStrategy, "successor", Cardinality.ONE);
      // bStrategy.withBidirectional(bStrategy, "suc", Cardinality.ONE, "pre", Cardinality.ONE);

      // cm.removeAllGeneratedCode("src/test/java");
      cm.withAuthorName("zuendorf");
      cm.generate("src/test/java");
      
      story.addClassDiagram(cm);
      
      story.dumpHTML();
   }


}
