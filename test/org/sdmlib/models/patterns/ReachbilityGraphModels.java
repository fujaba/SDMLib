package org.sdmlib.models.patterns;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.storyboards.Storyboard;

public class ReachbilityGraphModels
{
   @Test
   public void SimpleReachabilityGraphModel()
   {
      Storyboard storyboard = new Storyboard("test");

      ClassModel model = new ClassModel("org.sdmlib.models.patterns.example");

      Clazz state = model.createClazz("SimpleState");

      Clazz node = state.createClassAndAssoc("Node", "nodes", R.MANY, "graph",
         R.ONE).withAttributes("num", R.INT);

      node.withAssoc(node, "next", R.MANY, "prev", R.MANY);

      storyboard.addSVGImage(model.dumpClassDiagram("simpleReachableState"));

      model.generate("test");

      storyboard.dumpHTML();
   }

   @Test
   public void FerryMansProblemModel()
   {
      Storyboard storyboard = new Storyboard("test");

      ClassModel model = new ClassModel(
            "org.sdmlib.models.patterns.example.ferrmansproblem");

      Clazz river = model.createClazz("River");

      Clazz boat = river.createClassAndAssoc("Boat", "boat", R.ONE, "river",
         R.ONE);

      Clazz bank = boat.createClassAndAssoc("Bank", "bank", R.ONE, "boat",
         R.ONE).withAttributes("name", R.STRING, "age", "int");

      river.withAssoc(bank, "banks", R.MANY, "river", R.ONE);

      Clazz cargo = bank.createClassAndAssoc("Cargo", "cargos", R.MANY, "bank",
         R.ONE).withAttribute("name", R.STRING);

      // cargo.withAttribute("name", R.STRING);

      cargo.withAssoc(boat, "boat", R.ONE, "cargo", R.ONE);

      storyboard.addSVGImage(model.dumpClassDiagram("FerryMansProblemModel"));

      model.generate("test");

      storyboard.dumpHTML();
   }
}
