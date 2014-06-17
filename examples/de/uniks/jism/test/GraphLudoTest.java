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

package de.uniks.jism.test;

import org.junit.Test;
import org.sdmlib.serialization.Filter;
import org.sdmlib.serialization.graph.GraphConverter;
import org.sdmlib.serialization.graph.GraphIdMap;
import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.JsonObject;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.jism.test.model.SortedMsg;
import de.uniks.jism.test.model.SortedMsgCreator;
import de.uniks.jism.test.model.ludo.Field;
import de.uniks.jism.test.model.ludo.Ludo;
import de.uniks.jism.test.model.ludo.LudoColor;
import de.uniks.jism.test.model.ludo.Pawn;
import de.uniks.jism.test.model.ludo.Player;
import de.uniks.jism.test.model.ludo.creator.DateCreator;
import de.uniks.jism.test.model.ludo.creator.DiceCreator;
import de.uniks.jism.test.model.ludo.creator.FieldCreator;
import de.uniks.jism.test.model.ludo.creator.LudoCreator;
import de.uniks.jism.test.model.ludo.creator.PawnCreator;
import de.uniks.jism.test.model.ludo.creator.PlayerCreator;

public class GraphLudoTest
{
   private static final String RED = "red";

   @Test
   public void testLudoStoryboard()
   {
      JsonIdMap jsonIdMap = new JsonIdMap();
      jsonIdMap.withCreator(new DateCreator()).withCreator(new DiceCreator())
         .withCreator(new FieldCreator()).withCreator(new LudoCreator())
         .withCreator(new PawnCreator()).withCreator(new PlayerCreator());

      // create a simple ludo storyboard

      Ludo ludo = new Ludo();

      Player tom = ludo.createPlayers().withName("Tom").withColor("blue")
         .withEnumColor(LudoColor.blue);

      Player sabine = ludo.createPlayers().withName("Sabine").withColor(RED)
         .withEnumColor(LudoColor.red);

      tom.createDice().withValue(6);

      Pawn p2 = tom.createPawns().withColor("blue");

      Field tomStartField = tom.createStart().withColor("blue")
         .withKind("start");

      sabine.createStart().withColor(RED).withKind("start");

      Field tmp = tomStartField;
      for (int i = 0; i < 4; i++)
      {
         tmp = tmp.createNext();
      }

      tom.createBase().withColor("blue").withKind("base").withPawns(p2);

      sabine.createPawns().withColor(RED).withPos(tomStartField);

      JsonArray jsonArray = jsonIdMap.toJsonArray(ludo);
      // System.out.println(jsonArray.toString());
      // GraphIdMap docMap=new
      GraphConverter graphConverter = new GraphConverter();
      JsonObject converter = graphConverter.convertToJson(GraphIdMap.CLASS,
         jsonArray, true);
      System.out.println("###############################");
      System.out.println(converter.toString(2));
      System.out.println("###############################");
   }

   @Test
   public void testSimpleGraph()
   {
      SortedMsg root = new SortedMsg();
      root.withMsg("Hallo Welt");

      root.setChild(new SortedMsg().withMsg("Child"));

      JsonIdMap map = new JsonIdMap();
      map.withCreator(new SortedMsgCreator());

      JsonArray jsonArray = map.toJsonArray(root, new Filter().withFull(true));
      JsonObject item = jsonArray.get(map.getKey(root));
      item.put(GraphConverter.HEADIMAGE, "map.png");

      GraphConverter graphConverter = new GraphConverter();
      JsonObject objectModel = graphConverter.convertToJson(GraphIdMap.OBJECT,
         jsonArray, true);
      System.out.println(objectModel.toString(2));

      JsonObject clazzModel = graphConverter.convertToJson(GraphIdMap.CLASS,
         jsonArray, true);
      System.out.println(clazzModel.toString(2));

      Storyboard story = new Storyboard("examples");

      story.addObjectDiagram(root);

      story.dumpHTML();
   }

   // @Test
   // public void testLudoStoryboardManual()
   // {
   // Storyboard storyboard = new Storyboard("examples",
   // "LudoStoryboardManual");
   //
   // storyboard.add("Start situation: ",
   // DONE, "zuendorf", "19.07.2012 14:41:05", 1, 0);
   //
   // // create a simple ludo storyboard
   //
   // Player tom = new Player().withName("Tom").withColor("blue");
   // Player sabine = new Player().withName("Sabine").withColor(RED);
   //
   // Dice dice = new Dice().withValue(6)
   // .withPlayer(tom);
   //
   // Pawn p8 = new Pawn().withColor("blue")
   // .withPlayer(tom);
   //
   // Field tomStartField = new Field().withColor("blue").withKind("start");
   // tom.withStart(tomStartField);
   //
   // Field tmp = tomStartField;
   // for (int i = 0; i < 4; i++)
   // {
   // tmp = new Field().withPrev(tmp);
   // }
   //
   // Field tomBase = new
   // Field().withColor("blue").withKind("base").withPawns(p8);
   // tom.withBase(tomBase);
   //
   // Pawn p9 = new Pawn().withColor(RED)
   // .withPlayer(sabine)
   // .withPos(tomStartField);
   //
   // JsonIdMap jsonIdMap = CreatorCreator.createIdMap("l1");
   //
   // storyboard.addObjectDiagram(jsonIdMap, tom);
   //
   // storyboard.add("now the pawn may move to Tom's start field");
   //
   // storyboard.markCodeStart();
   // // build move operation with SDM model transformations
   // Player player = p8.getPlayer();
   //
   // if (player.getDice() != null && player.getDice().getValue() == 6
   // && p8.getPos() != null && "base".equals(p8.getPos().getKind())
   // && p8.getPos() == player.getBase())
   // {
   // Field startField = player.getStart();
   // boolean hasOtherOwnPawn = false;
   //
   // for (Pawn otherOwnPawn : startField.getPawns())
   // {
   // if (otherOwnPawn.getPlayer() == player)
   // {
   // hasOtherOwnPawn = true;
   // break;
   // }
   // }
   //
   // if ( ! hasOtherOwnPawn)
   // {
   // p8.setPos(startField);
   // }
   // }
   // storyboard.addCode("examples");
   //
   // storyboard.addObjectDiagram(jsonIdMap, tom);
   //
   // StoryboardManager.get()
   // .add(storyboard)
   // .dumpHTML();
   // }
}
