/*
   Copyright (c) 2012 zuendorf 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.examples.ludo;
   
import org.junit.Test;
import org.sdmlib.examples.ludo.creators.CreatorCreator;
import org.sdmlib.examples.ludo.creators.DicePO;
import org.sdmlib.examples.ludo.creators.FieldPO;
import org.sdmlib.examples.ludo.creators.ModelPattern;
import org.sdmlib.examples.ludo.creators.PawnPO;
import org.sdmlib.examples.ludo.creators.PlayerPO;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.scenarios.LogEntry;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.scenarios.ScenarioManager;
import org.sdmlib.serialization.json.JsonIdMap;
   
public class LudoScenario 
{
   @Test
   public void testLudoScenario()
   {
      Scenario scenario = new Scenario("examples", "LudoScenario");
      
      scenario.add("Start situation: ",
         MODELING, "zuendorf", "15.07.2012 15:37:05", 0, 4);
      
      // create a simple ludo scenario
      
      Player tom = new Player().withName("Tom").withColor("blue");
      Player sabine = new Player().withName("Sabine").withColor("red");
      
      Dice dice = tom.createDice().withValue(6);
      
      Pawn p2 = tom.createPawns().withColor("blue");
      
      Field tomStartField = tom.createStart().withColor("blue").withKind("start");
      
      Field tmp = tomStartField;
      for (int i = 0; i < 4; i++)
      {
         tmp = tmp.createNext();
      }
      
      Field tomBase = tom.createBase().withColor("blue").withKind("base").withPawns(p2);
      
      Pawn p9 = sabine.createPawns().withColor("red")
            .withPos(tomStartField);
      
      JsonIdMap jsonIdMap = CreatorCreator.createIdMap("l1");
      
      scenario.addObjectDiag(jsonIdMap, tom);
      
      scenario.add("now the pawn may move to Tom's start field");
      
      scenario.markCodeStart();
      // build move operation with SDM model transformations
      ModelPattern p = new ModelPattern();
      
      PawnPO pawnPO = (PawnPO) p.hasElementPawnPO(p2);
      
      PlayerPO playerPO = pawnPO.hasPlayer();
      
      DicePO dicePO = playerPO.hasDice().hasValue(6);
      
      FieldPO baseField = pawnPO.hasPos().hasKind("base");
      
      playerPO.hasBase(baseField);
      
      FieldPO startFieldPO = playerPO.hasStart();
      
      startFieldPO.startNAC().hasPawns().hasPlayer(playerPO).endNAC();
      scenario.addCode("examples");
      
      // scenario.addObjectDiag(jsonIdMap, pawnPO);
      scenario.add(p.dumpDiagram("LudoMoveWithMatch01"));
      
      scenario.markCodeStart();
      p.startCreate();
      pawnPO.hasPos(startFieldPO);
      scenario.addCode("examples");
      
      //scenario.addObjectDiag(jsonIdMap, pawnPO);
      scenario.add(p.dumpDiagram("LudoMoveAfterSetPos02"));
      
      System.out.println("pattern has match is " + p.getHasMatch());
      
      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();
   }

   @Test
   public void testLudoScenarioManual()
   {
      Scenario scenario = new Scenario("examples", "LudoScenarioManual");
      
      scenario.add("Start situation: ",
         DONE, "zuendorf", "19.07.2012 14:41:05", 1, 0);
      
      // create a simple ludo scenario
      
      Player tom = new Player().withName("Tom").withColor("blue");
      Player sabine = new Player().withName("Sabine").withColor("red");
      
      Dice dice = new Dice().withValue(6)
            .withPlayer(tom);
      
      Pawn p8 = new Pawn().withColor("blue")
            .withPlayer(tom);
      
      Field tomStartField = new Field().withColor("blue").withKind("start");
      tom.withStart(tomStartField);
      
      Field tmp = tomStartField;
      for (int i = 0; i < 4; i++)
      {
         tmp = new Field().withPrev(tmp);
      }
      
      Field tomBase = new Field().withColor("blue").withKind("base").withPawns(p8);
      tom.withBase(tomBase);
      
      Pawn p9 = new Pawn().withColor("red")
            .withPlayer(sabine)
            .withPos(tomStartField);
      
      JsonIdMap jsonIdMap = CreatorCreator.createIdMap("l1");
      
      scenario.addObjectDiag(jsonIdMap, tom);
      
      scenario.add("now the pawn may move to Tom's start field");
      
      scenario.markCodeStart();
      // build move operation with SDM model transformations
      Player player = p8.getPlayer();
      
      if (player.getDice() != null && player.getDice().getValue() == 6
            && p8.getPos() != null && "base".equals(p8.getPos().getKind())
            && p8.getPos() == player.getBase())
      {
         Field startField = player.getStart();
         boolean hasOtherOwnPawn = false;
         
         for (Pawn otherOwnPawn : startField.getPawns())
         {
            if (otherOwnPawn.getPlayer() == player)
            {
               hasOtherOwnPawn = true;
               break;
            }
         }
         
         if ( ! hasOtherOwnPawn)
         {
            p8.setPos(startField);
         }
      }
      scenario.addCode("examples");
      
      scenario.addObjectDiag(jsonIdMap, tom);
      
      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();
   }

   private static final String MODELING = "modeling";
   private static final String ACTIVE = "active";
   private static final String DONE = "done";
   private static final String IMPLEMENTATION = "implementation";
   private static final String BACKLOG = "backlog";
   private static final String BUG = "bug";
}




