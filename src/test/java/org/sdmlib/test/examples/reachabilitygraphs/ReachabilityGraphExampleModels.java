package org.sdmlib.test.examples.reachabilitygraphs;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.storyboards.Storyboard;

public class ReachabilityGraphExampleModels
{
   @Test
   public void SimpleReachabilityGraphModel()
   {
      Storyboard storyboard = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.reachabilitygraphs.simplestates");
      
      Clazz state = model.createClazz("SimpleState");
      
      Clazz node = model.createClazz("Node")
            .withAttribute("num", DataType.INT);
            
      state.withAssoc(node, "nodes", Card.MANY, "graph", Card.ONE);
      
      node.withAssoc(node, "next", Card.MANY, "prev", Card.MANY);
      
      storyboard.addClassDiagram(model);

      model.generate("src/test/java");
      
      storyboard.dumpHTML();
   }
   
   @Test
   public void FerryMansProblemModel()
   {
      Storyboard storyboard = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem");
      
      Clazz river = model.createClazz("River");
      
      Clazz boat = model.createClazz("Boat");
            
      river.withAssoc(boat, "boat", Card.ONE, "river", Card.ONE);
      
      Clazz bank = model.createClazz("Bank")
            .withAttribute("name", DataType.STRING)
            .withAttribute("age", DataType.INT);
            
      boat.withAssoc(bank, "bank", Card.ONE, "boat", Card.ONE);
      
      river.withAssoc(bank, "banks", Card.MANY, "river", Card.ONE);
      
      Clazz cargo = model.createClazz("Cargo")
            .withAttribute("name", DataType.STRING); 
            
      bank.withAssoc(cargo, "cargos", Card.MANY, "bank", Card.ONE);
      
      // cargo.withAttribute("name", Card.STRING);
      
      cargo.withAssoc(boat, "boat", Card.ONE, "cargo", Card.ONE);
      
      storyboard.addClassDiagram(model);
      
      model.generate("src/test/java");
      
      storyboard.dumpHTML();
   }
}
