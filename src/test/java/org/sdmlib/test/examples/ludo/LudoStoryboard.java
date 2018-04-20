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
   
package org.sdmlib.test.examples.ludo;
   
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.ludo.LudoModel.LudoColor;
import org.sdmlib.test.examples.ludo.model.Dice;
import org.sdmlib.test.examples.ludo.model.Field;
import org.sdmlib.test.examples.ludo.model.Ludo;
import org.sdmlib.test.examples.ludo.model.Pawn;
import org.sdmlib.test.examples.ludo.model.Player;
import org.sdmlib.test.examples.ludo.model.util.DicePO;
import org.sdmlib.test.examples.ludo.model.util.FieldPO;
import org.sdmlib.test.examples.ludo.model.util.LudoCreator;
import org.sdmlib.test.examples.ludo.model.util.PawnPO;
import org.sdmlib.test.examples.ludo.model.util.PlayerPO;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.json.JsonArray;
   
public class LudoStoryboard
{
   private static final String RED = "red";


     /**
    * 
    * <p>Storyboard <a href='.././src/test/java/org/sdmlib/test/examples/ludo/LudoStoryboard.java' type='text/x-java'>LudoStoryboard</a></p>
    * <p>Start situation: </p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"D4 : Dice",
    *          "attributes":[
    *             "game=null",
    *             "value=6"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"F10 : Field",
    *          "attributes":[
    *             "baseowner=null",
    *             "color=null",
    *             "entry=null",
    *             "game=null",
    *             "kind=null",
    *             "lander=null",
    *             "landing=null",
    *             "point=null",
    *             "starter=null",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"F11 : Field",
    *          "attributes":[
    *             "baseowner=null",
    *             "color=null",
    *             "entry=null",
    *             "game=null",
    *             "kind=null",
    *             "lander=null",
    *             "landing=null",
    *             "point=null",
    *             "starter=null",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"F12 : Field",
    *          "attributes":[
    *             "baseowner=null",
    *             "color=null",
    *             "entry=null",
    *             "game=null",
    *             "kind=null",
    *             "lander=null",
    *             "landing=null",
    *             "point=null",
    *             "starter=null",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"F13 : Field",
    *          "attributes":[
    *             "baseowner=null",
    *             "color=null",
    *             "entry=null",
    *             "game=null",
    *             "kind=null",
    *             "lander=null",
    *             "landing=null",
    *             "point=null",
    *             "starter=null",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"F5 : Field",
    *          "attributes":[
    *             "baseowner=null",
    *             "color=blue",
    *             "entry=null",
    *             "game=null",
    *             "kind=start",
    *             "lander=null",
    *             "landing=null",
    *             "point=null",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"F6 : Field",
    *          "attributes":[
    *             "color=blue",
    *             "entry=null",
    *             "game=null",
    *             "kind=base",
    *             "lander=null",
    *             "landing=null",
    *             "next=null",
    *             "point=null",
    *             "prev=null",
    *             "starter=null",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"F8 : Field",
    *          "attributes":[
    *             "baseowner=null",
    *             "color=red",
    *             "entry=null",
    *             "game=null",
    *             "kind=start",
    *             "lander=null",
    *             "landing=null",
    *             "next=null",
    *             "point=null",
    *             "prev=null",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"L1 : Ludo",
    *          "attributes":[
    *             "date=null",
    *             "dice=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P2 : Player",
    *          "attributes":[
    *             "color=blue",
    *             "enumColor=blue",
    *             "landing=null",
    *             "name=Tom",
    *             "next=null",
    *             "prev=null",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P3 : Player",
    *          "attributes":[
    *             "base=null",
    *             "color=red",
    *             "dice=null",
    *             "enumColor=red",
    *             "landing=null",
    *             "name=Sabine",
    *             "next=null",
    *             "prev=null",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P7 : Pawn",
    *          "attributes":[
    *             "color=blue",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P9 : Pawn",
    *          "attributes":[
    *             "color=red",
    *             "x=0",
    *             "y=0"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"base",
    *             "id":"F6 : Field"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"baseowner",
    *             "id":"P2 : Player"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"dice",
    *             "id":"D4 : Dice"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"player",
    *             "id":"P2 : Player"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"F10 : Field"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"F5 : Field"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"F11 : Field"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"F10 : Field"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"F12 : Field"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"F11 : Field"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"F13 : Field"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"F12 : Field"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"pawns",
    *             "id":"P7 : Pawn"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"player",
    *             "id":"P2 : Player"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"pawns",
    *             "id":"P9 : Pawn"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"player",
    *             "id":"P3 : Player"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"pawns",
    *             "id":"P9 : Pawn"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"pos",
    *             "id":"F5 : Field"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"pawns",
    *             "id":"P7 : Pawn"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"pos",
    *             "id":"F6 : Field"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"players",
    *             "id":"P2 : Player"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"game",
    *             "id":"L1 : Ludo"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"players",
    *             "id":"P3 : Player"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"game",
    *             "id":"L1 : Ludo"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"start",
    *             "id":"F5 : Field"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"starter",
    *             "id":"P2 : Player"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"start",
    *             "id":"F8 : Field"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"starter",
    *             "id":"P3 : Player"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasLudoStoryboard2", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p>now the pawn may move to Tom's start field</p>
    * <pre>            &#x2F;&#x2F; build move operation with SDM model transformations
    *       PawnPO pawnPO = new PawnPO(p2);
    *       
    *       PlayerPO playerPO = pawnPO.hasPlayer();
    *       
    *       DicePO diePO = playerPO.hasDice().hasValue(6);
    *       
    *       FieldPO baseField = pawnPO.hasPos().hasKind(&quot;base&quot;);
    *       
    *       playerPO.hasBase(baseField);
    *       
    *       FieldPO startFieldPO = playerPO.hasStart();
    *       
    *       startFieldPO.startNAC().hasPawns().hasPlayer(playerPO).endNAC();
    * </pre>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"p1 : PawnPO",
    *          "attributes":[
    *             "<< bound>>"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"p2 : PlayerPO",
    *          "attributes":[]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"d3 : DicePO",
    *          "attributes":[
    *             "value == 6"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"f4 : FieldPO",
    *          "attributes":[
    *             "kind == base"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"f5 : FieldPO",
    *          "attributes":[]
    *       },
    *       {
    *          "type":"objectdiagram",
    *          "style":"nac",
    *          "info":"NegativeApplicationCondition",
    *          "nodes":[
    *             {
    *                "type":"patternObject",
    *                "id":"p6 : PawnPO",
    *                "attributes":[]
    *             }
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p1 : PawnPO"
    *          },
    *          "target":{
    *             "property":"player",
    *             "id":"p2 : PlayerPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p2 : PlayerPO"
    *          },
    *          "target":{
    *             "property":"dice",
    *             "id":"d3 : DicePO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p1 : PawnPO"
    *          },
    *          "target":{
    *             "property":"pos",
    *             "id":"f4 : FieldPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p2 : PlayerPO"
    *          },
    *          "target":{
    *             "property":"base",
    *             "id":"f4 : FieldPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p2 : PlayerPO"
    *          },
    *          "target":{
    *             "property":"start",
    *             "id":"f5 : FieldPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"f5 : FieldPO"
    *          },
    *          "target":{
    *             "property":"pawns",
    *             "id":"p6 : PawnPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p6 : PawnPO"
    *          },
    *          "target":{
    *             "property":"player",
    *             "id":"p2 : PlayerPO"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasLudoStoryboardPatternDiagram4", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <pre>            pawnPO.startDestroy();
    *       pawnPO.hasPos(baseField);
    *       
    *       pawnPO.startCreate();
    *       pawnPO.hasPos(startFieldPO);
    * </pre>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"p1 : PawnPO",
    *          "attributes":[
    *             "<< bound>>"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"p2 : PlayerPO",
    *          "attributes":[]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"d3 : DicePO",
    *          "attributes":[
    *             "value == 6"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"f4 : FieldPO",
    *          "attributes":[
    *             "kind == base"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"f5 : FieldPO",
    *          "attributes":[]
    *       },
    *       {
    *          "type":"objectdiagram",
    *          "style":"nac",
    *          "info":"NegativeApplicationCondition",
    *          "nodes":[
    *             {
    *                "type":"patternObject",
    *                "id":"p6 : PawnPO",
    *                "attributes":[]
    *             }
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p1 : PawnPO"
    *          },
    *          "target":{
    *             "property":"player",
    *             "id":"p2 : PlayerPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p2 : PlayerPO"
    *          },
    *          "target":{
    *             "property":"dice",
    *             "id":"d3 : DicePO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p1 : PawnPO"
    *          },
    *          "target":{
    *             "property":"pos",
    *             "id":"f4 : FieldPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p2 : PlayerPO"
    *          },
    *          "target":{
    *             "property":"base",
    *             "id":"f4 : FieldPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p2 : PlayerPO"
    *          },
    *          "target":{
    *             "property":"start",
    *             "id":"f5 : FieldPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"f5 : FieldPO"
    *          },
    *          "target":{
    *             "property":"pawns",
    *             "id":"p6 : PawnPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p6 : PawnPO"
    *          },
    *          "target":{
    *             "property":"player",
    *             "id":"p2 : PlayerPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p1 : PawnPO"
    *          },
    *          "target":{
    *             "property":"pos",
    *             "id":"f4 : FieldPO"
    *          },
    *          "style":"destroy"
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p1 : PawnPO"
    *          },
    *          "target":{
    *             "property":"pos",
    *             "id":"f5 : FieldPO"
    *          },
    *          "style":"create"
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasLudoStoryboardPatternDiagram6", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p>Check: Pattern has match: true</p>
    * @see <a href='../../../../../../../../doc/LudoStoryboard.html'>LudoStoryboard.html</a>
*/
   @Test
   public void testLudoStoryboard()
   {
      // file:///C:/Users/zuendorf/eclipseworkspaces/indigo/SDMLib/doc/LudoStoryboard.html
      Storyboard storyboard = new Storyboard().withDocDirName("doc/internal");
      
      storyboard.add("Start situation: ");
      
      IdMap jsonIdMap = LudoCreator.createIdMap("l1");
      
      // create a simple ludo storyboard
      
      Ludo ludo = new Ludo();
      
      Player tom = ludo.createPlayers().withName("Tom").withColor("blue").withEnumColor(LudoColor.blue);
      
      JsonArray jsonArray = jsonIdMap.toJsonArray(tom);
      
      // System.out.println(jsonArray.toString(3));
      
      
      Player sabine = ludo.createPlayers().withName("Sabine").withColor(RED).withEnumColor(LudoColor.red);
      
      IdMap jsonIdMapClone = LudoCreator.createIdMap("l2");

      Object clone = jsonIdMap.decode(jsonArray);
      
      Dice dice = tom.createDice().withValue(6);
      
      Pawn p2 = tom.createPawns().withColor("blue");
      
      Field tomStartField = tom.createStart().withColor("blue").withKind("start");
      
      sabine.createStart().withColor(RED).withKind("start");
      
      Field tmp = tomStartField;
      for (int i = 0; i < 4; i++)
      {
         tmp = tmp.createNext();
      }
      
      Field tomBase = tom.createBase().withColor("blue").withKind("base").withPawns(p2);
      
      Pawn p9 = sabine.createPawns().withColor(RED)
            .withPos(tomStartField);
      
      
      storyboard.addObjectDiagram(ludo);
      
      storyboard.add("now the pawn may move to Tom's start field");
      
      storyboard.markCodeStart();
      // build move operation with SDM model transformations
      PawnPO pawnPO = new PawnPO(p2);
      
      PlayerPO playerPO = pawnPO.hasPlayer();
      
      DicePO diePO = playerPO.hasDice().hasValue(6);
      
      FieldPO baseField = pawnPO.hasPos().hasKind("base");
      
      playerPO.hasBase(baseField);
      
      FieldPO startFieldPO = playerPO.hasStart();
      
      startFieldPO.startNAC().hasPawns().hasPlayer(playerPO).endNAC();
      storyboard.addCode();
      
      // storyboard.addObjectDiag(jsonIdMap, pawnPO);
      storyboard.addPattern(pawnPO, true);
      
      storyboard.markCodeStart();
      pawnPO.startDestroy();
      pawnPO.hasPos(baseField);
      
      pawnPO.startCreate();
      pawnPO.hasPos(startFieldPO);
      storyboard.addCode();
      
      //storyboard.addObjectDiag(jsonIdMap, pawnPO);
      storyboard.addPattern(pawnPO, true);
      
      storyboard.assertTrue("Pattern has match:", pawnPO.getPattern().getHasMatch());
      
      storyboard.dumpHTML();
   }

     /**
    * 
    * <p>Storyboard <a href='.././src/test/java/org/sdmlib/test/examples/ludo/LudoStoryboard.java' type='text/x-java'>LudoStoryboard</a></p>
    * <p>Start situation: </p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"D4 : Dice",
    *          "attributes":[
    *             "game=null",
    *             "value=6"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"F10 : Field",
    *          "attributes":[
    *             "baseowner=null",
    *             "color=null",
    *             "entry=null",
    *             "game=null",
    *             "kind=null",
    *             "lander=null",
    *             "landing=null",
    *             "point=null",
    *             "starter=null",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"F11 : Field",
    *          "attributes":[
    *             "baseowner=null",
    *             "color=null",
    *             "entry=null",
    *             "game=null",
    *             "kind=null",
    *             "lander=null",
    *             "landing=null",
    *             "point=null",
    *             "starter=null",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"F12 : Field",
    *          "attributes":[
    *             "baseowner=null",
    *             "color=null",
    *             "entry=null",
    *             "game=null",
    *             "kind=null",
    *             "lander=null",
    *             "landing=null",
    *             "point=null",
    *             "starter=null",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"F13 : Field",
    *          "attributes":[
    *             "baseowner=null",
    *             "color=null",
    *             "entry=null",
    *             "game=null",
    *             "kind=null",
    *             "lander=null",
    *             "landing=null",
    *             "point=null",
    *             "starter=null",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"F5 : Field",
    *          "attributes":[
    *             "baseowner=null",
    *             "color=blue",
    *             "entry=null",
    *             "game=null",
    *             "kind=start",
    *             "lander=null",
    *             "landing=null",
    *             "point=null",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"F6 : Field",
    *          "attributes":[
    *             "color=blue",
    *             "entry=null",
    *             "game=null",
    *             "kind=base",
    *             "lander=null",
    *             "landing=null",
    *             "next=null",
    *             "point=null",
    *             "prev=null",
    *             "starter=null",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"F8 : Field",
    *          "attributes":[
    *             "baseowner=null",
    *             "color=red",
    *             "entry=null",
    *             "game=null",
    *             "kind=start",
    *             "lander=null",
    *             "landing=null",
    *             "next=null",
    *             "point=null",
    *             "prev=null",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"L1 : Ludo",
    *          "attributes":[
    *             "date=null",
    *             "dice=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P2 : Player",
    *          "attributes":[
    *             "color=blue",
    *             "enumColor=blue",
    *             "landing=null",
    *             "name=Tom",
    *             "next=null",
    *             "prev=null",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P3 : Player",
    *          "attributes":[
    *             "base=null",
    *             "color=red",
    *             "dice=null",
    *             "enumColor=red",
    *             "landing=null",
    *             "name=Sabine",
    *             "next=null",
    *             "prev=null",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P7 : Pawn",
    *          "attributes":[
    *             "color=blue",
    *             "x=0",
    *             "y=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P9 : Pawn",
    *          "attributes":[
    *             "color=red",
    *             "x=0",
    *             "y=0"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"base",
    *             "id":"F6 : Field"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"baseowner",
    *             "id":"P2 : Player"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"dice",
    *             "id":"D4 : Dice"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"player",
    *             "id":"P2 : Player"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"F10 : Field"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"F5 : Field"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"F11 : Field"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"F10 : Field"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"F12 : Field"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"F11 : Field"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"F13 : Field"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"F12 : Field"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"pawns",
    *             "id":"P7 : Pawn"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"player",
    *             "id":"P2 : Player"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"pawns",
    *             "id":"P9 : Pawn"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"player",
    *             "id":"P3 : Player"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"pawns",
    *             "id":"P9 : Pawn"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"pos",
    *             "id":"F5 : Field"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"pawns",
    *             "id":"P7 : Pawn"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"pos",
    *             "id":"F6 : Field"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"players",
    *             "id":"P2 : Player"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"game",
    *             "id":"L1 : Ludo"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"players",
    *             "id":"P3 : Player"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"game",
    *             "id":"L1 : Ludo"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"start",
    *             "id":"F5 : Field"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"starter",
    *             "id":"P2 : Player"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"start",
    *             "id":"F8 : Field"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"starter",
    *             "id":"P3 : Player"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasLudoStoryboard2", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p>now the pawn may move to Tom's start field</p>
    * <pre>            &#x2F;&#x2F; build move operation with SDM model transformations
    *       PawnPO pawnPO = new PawnPO(p2);
    *       
    *       PlayerPO playerPO = pawnPO.hasPlayer();
    *       
    *       DicePO diePO = playerPO.hasDice().hasValue(6);
    *       
    *       FieldPO baseField = pawnPO.hasPos().hasKind(&quot;base&quot;);
    *       
    *       playerPO.hasBase(baseField);
    *       
    *       FieldPO startFieldPO = playerPO.hasStart();
    *       
    *       startFieldPO.startNAC().hasPawns().hasPlayer(playerPO).endNAC();
    * </pre>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"p1 : PawnPO",
    *          "attributes":[
    *             "<< bound>>"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"p2 : PlayerPO",
    *          "attributes":[]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"d3 : DicePO",
    *          "attributes":[
    *             "value == 6"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"f4 : FieldPO",
    *          "attributes":[
    *             "kind == base"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"f5 : FieldPO",
    *          "attributes":[]
    *       },
    *       {
    *          "type":"objectdiagram",
    *          "style":"nac",
    *          "info":"NegativeApplicationCondition",
    *          "nodes":[
    *             {
    *                "type":"patternObject",
    *                "id":"p6 : PawnPO",
    *                "attributes":[]
    *             }
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p1 : PawnPO"
    *          },
    *          "target":{
    *             "property":"player",
    *             "id":"p2 : PlayerPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p2 : PlayerPO"
    *          },
    *          "target":{
    *             "property":"dice",
    *             "id":"d3 : DicePO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p1 : PawnPO"
    *          },
    *          "target":{
    *             "property":"pos",
    *             "id":"f4 : FieldPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p2 : PlayerPO"
    *          },
    *          "target":{
    *             "property":"base",
    *             "id":"f4 : FieldPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p2 : PlayerPO"
    *          },
    *          "target":{
    *             "property":"start",
    *             "id":"f5 : FieldPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"f5 : FieldPO"
    *          },
    *          "target":{
    *             "property":"pawns",
    *             "id":"p6 : PawnPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p6 : PawnPO"
    *          },
    *          "target":{
    *             "property":"player",
    *             "id":"p2 : PlayerPO"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasLudoStoryboardPatternDiagram4", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <pre>            pawnPO.startDestroy();
    *       pawnPO.hasPos(baseField);
    *       
    *       pawnPO.startCreate();
    *       pawnPO.hasPos(startFieldPO);
    * </pre>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"p1 : PawnPO",
    *          "attributes":[
    *             "<< bound>>"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"p2 : PlayerPO",
    *          "attributes":[]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"d3 : DicePO",
    *          "attributes":[
    *             "value == 6"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"f4 : FieldPO",
    *          "attributes":[
    *             "kind == base"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"f5 : FieldPO",
    *          "attributes":[]
    *       },
    *       {
    *          "type":"objectdiagram",
    *          "style":"nac",
    *          "info":"NegativeApplicationCondition",
    *          "nodes":[
    *             {
    *                "type":"patternObject",
    *                "id":"p6 : PawnPO",
    *                "attributes":[]
    *             }
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p1 : PawnPO"
    *          },
    *          "target":{
    *             "property":"player",
    *             "id":"p2 : PlayerPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p2 : PlayerPO"
    *          },
    *          "target":{
    *             "property":"dice",
    *             "id":"d3 : DicePO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p1 : PawnPO"
    *          },
    *          "target":{
    *             "property":"pos",
    *             "id":"f4 : FieldPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p2 : PlayerPO"
    *          },
    *          "target":{
    *             "property":"base",
    *             "id":"f4 : FieldPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p2 : PlayerPO"
    *          },
    *          "target":{
    *             "property":"start",
    *             "id":"f5 : FieldPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"f5 : FieldPO"
    *          },
    *          "target":{
    *             "property":"pawns",
    *             "id":"p6 : PawnPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p6 : PawnPO"
    *          },
    *          "target":{
    *             "property":"player",
    *             "id":"p2 : PlayerPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p1 : PawnPO"
    *          },
    *          "target":{
    *             "property":"pos",
    *             "id":"f4 : FieldPO"
    *          },
    *          "style":"destroy"
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"p1 : PawnPO"
    *          },
    *          "target":{
    *             "property":"pos",
    *             "id":"f5 : FieldPO"
    *          },
    *          "style":"create"
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasLudoStoryboardPatternDiagram6", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p>Check: Pattern has match: true</p>
    * @see <a href='../../../../../../../../doc/LudoStoryboardManual.html'>LudoStoryboardManual.html</a>
* @see <a href='../../../../../../../../doc/LudoStoryboard.html'>LudoStoryboard.html</a>
*/
   @Test
   public void testLudoStoryboardManual()
   {
      Storyboard storyboard = new Storyboard().withDocDirName("doc/internal");
      
      storyboard.add("Start situation: ");
      
      // create a simple ludo storyboard
      
      Player tom = new Player().withName("Tom").withColor("blue");
      Player sabine = new Player().withName("Sabine").withColor(RED);
      
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
      
      Pawn p9 = new Pawn().withColor(RED)
            .withPlayer(sabine)
            .withPos(tomStartField);
      
      IdMap jsonIdMap = LudoCreator.createIdMap("l1");
      
      storyboard.addObjectDiagram(tom);
      
      storyboard.add("now the pawn may move to Tom's start field");
      
      storyboard.markCodeStart();
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
      storyboard.addCode();
      
      storyboard.addObjectDiagram(tom);
      
      storyboard.dumpHTML();
   }
}

