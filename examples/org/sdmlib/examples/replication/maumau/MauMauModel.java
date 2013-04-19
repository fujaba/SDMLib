package org.sdmlib.examples.replication.maumau;

import static org.sdmlib.models.classes.Role.R.*;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.scenarios.Scenario;

public class MauMauModel
{
   private static final String STRING = "String";
   private static final String INT = "int";

   @Test
   public void testMauMauModel()
   {
      Scenario scenario = new Scenario("examples");

      ClassModel model = new ClassModel("org.sdmlib.examples.replication.maumau");

      Clazz mauMauClass = model.createClazz("MauMau");

      Clazz cardClass = mauMauClass.createClassAndAssoc("Card", "cards", MANY, "game", ONE)
            .withAttributes(
               "suit", Suit.class.getSimpleName(),
               "value", Value.class.getName()
                  );
      
      Clazz holderClass = mauMauClass.createClassAndAssoc("Holder", "deck", R.ONE, "deckOwner", R.ONE)
            .withAssoc(cardClass, "cards", R.MANY, "holder", R.ONE);
      
      mauMauClass.withAssoc(holderClass, "stack", R.ONE, "stackOwner", R.MANY);

      Clazz playerClass = mauMauClass.createClassAndAssoc("Player", "players", MANY, "game", ONE)
            .withSuperClass(holderClass)
            .withAttributes("name", STRING);
      
      playerClass.withAssoc(playerClass, "next", ONE, "prev", ONE);

      mauMauClass.withAssoc(playerClass, "currentMove", R.ONE, "assignment", R.ONE);
      

      scenario.addImage(model.dumpClassDiag("src", "MauMauClassDiag"));

      model.generate("examples");

      scenario.dumpHTML();
   }
}
