package org.sdmlib.test.examples.gofpattern;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.StoryPage;

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
      StoryPage story = new StoryPage();
      
      ClassModel cm = new ClassModel("org.sdmlib.test.examples.gofpattern.strategy");

      Clazz bPlayer = cm
            .createClazz("BombermanPlayer")
            .withAttribute("xPosition", DataType.INT)
            .withAttribute("yPosition", DataType.INT)
            .withAttribute("numberOfBombs", DataType.INT)
            .withAttribute("lastKey", DataType.CHAR)
            .withAttribute("shortTest", DataType.ref("short"))
            .withMethod("keyPress", DataType.VOID,
               new Parameter(DataType.STRING).with("key"));

      Clazz bStrategy = cm.createClazz("BombermanStrategy")
            .with(Modifier.ABSTRACT).withMethod("handleMove", DataType.VOID);

      bStrategy.createKidClazz("MoveUp");
      bStrategy.createKidClazz("MoveDown");
      bStrategy.createKidClazz("MoveLeft");
      bStrategy.createKidClazz("MoveRight");
      bStrategy.createKidClazz("Blast");
      bStrategy.createKidClazz("Stay");

      bStrategy.withUniDirectional(bStrategy, "successor", Cardinality.ONE);
      // bStrategy.withBidirectional(bStrategy, "suc", Cardinality.ONE, "pre", Cardinality.ONE);

      // cm.removeAllGeneratedCode("src/test/java");
      cm.withAuthorName("zuendorf");
      cm.generate("src/test/java");
      
      story.addClassDiagram(cm);
      
      story.dumpHTML();
   }


}
