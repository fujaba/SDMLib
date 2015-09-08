package org.sdmlib.test.examples.gofpattern;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Modifier;
import org.sdmlib.models.classes.Parameter;
import org.sdmlib.storyboards.Storyboard;

public class StrategyModel
{
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
            .withAttribute("shortTest", DataType.ref("short"))
            .withMethod("keyPress", DataType.VOID,
               new Parameter("key", DataType.STRING));

      Clazz bStrategy = cm.createClazz("BombermanStrategy")
            .withModifier(Modifier.ABSTRACT).withMethod("handleMove", DataType.VOID);

      bStrategy.createKidClazz("MoveUp");
      bStrategy.createKidClazz("MoveDown");
      bStrategy.createKidClazz("MoveLeft");
      bStrategy.createKidClazz("MoveRight");
      bStrategy.createKidClazz("Blast");
      bStrategy.createKidClazz("Stay");

      bStrategy.withAssoc(bStrategy, "successor", Card.ONE);
      // bStrategy.withAssoc(bStrategy, "suc", Card.ONE, "pre", Card.ONE);

      // cm.removeAllGeneratedCode("src/test/java");
      cm.withAuthorName("zuendorf");
      cm.generate("src/test/java");
      
      story.addClassDiagram(cm);
      
      story.dumpHTML();
   }


}
