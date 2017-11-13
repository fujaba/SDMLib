package org.sdmlib.test.examples.reachabilitygraphs;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.tables.Table;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Maze;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Sokoban;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Tile;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.BoxPO;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.KarliPO;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.SokobanCreator;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.SokobanPO;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.TilePO;

import de.uniks.networkparser.list.ObjectSet;

public class SokobanLevels
{
   @Test
   public void MemoryMeasurements() throws Exception
   {
      Storyboard story = new Storyboard().withDocDirName("doc/internal");
      
      Runtime runtime = Runtime.getRuntime();
      // Run the garbage collector
      runtime.gc();
      // Calculate the used memory
      long emptyMemory = runtime.totalMemory() - runtime.freeMemory();
      
      story.add("empty memory: " + emptyMemory);
      
      Sokoban soko = new Sokoban();
      
      runtime.gc();
      long sokoMemory = runtime.totalMemory() - runtime.freeMemory();
      
      story.add("soko memory: " + sokoMemory);

      Sokoban soko2 = new Sokoban();
      
      runtime.gc();
      long soko2Memory = runtime.totalMemory() - runtime.freeMemory();
      
      story.add("soko memory: " + soko2Memory);

      story.dumpHTML();
   }
   
   
   
     /**
    * 
    * @see <a href='../../../../../../../../doc/SokobanLevel1.html'>SokobanLevel1.html</a>
 */
   @Test
   public void SokobanLevel1() throws Exception
   {
      Storyboard story = new Storyboard();
      
      story.addStep("First Level");
      
      String level = ""
         + " wwww\n"
         + " wk.w\n"
         + "wwb.w\n"
         + "wo..w\n"
         + "wwwww\n";
      
      Sokoban soko = createLevel(level);
      
      // story.addObjectDiagram(soko);
      
      SokobanPO sokoPO = createMoveKarlRule();

      story.addPattern(sokoPO, false);
      
      SokobanPO pushBoxSokoPO = createPushBoxRule();
      
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph()
            .withMasterMap(SokobanCreator.createIdMap("s"))
            .withLazyCloning()
            .withRules("move karl", sokoPO)
            .withRules("push box", pushBoxSokoPO);
      
      ReachableState startState = new ReachableState().withGraphRoot(soko);
      reachabilityGraph.withStart(startState);
      
      ObjectSet startElems = new ObjectSet();
      reachabilityGraph.getLazyCloneOp().aggregate(startElems, soko);
      
      //=============================================================
      reachabilityGraph.explore();
      //=============================================================
      
      Table stats = new Table();
      stats.createColumns().withName("Descr").withTdCssClass("text-right col-md-1");
      stats.createColumns().withName("Data").withTdCssClass("text-right col-md-1");
      stats.createColumns().withName(" ").withTdCssClass("text-right col-md-6");
      
      int startElemsSize = startElems.size();
      stats.createRows("nodes per state:", startElemsSize, " ");
      
      int noOfStates = reachabilityGraph.getStates().size();
      stats.createRows("number of states:", noOfStates, " ");
      
      int expectedObjectNumber = startElemsSize * noOfStates;
      stats.createRows("product:", expectedObjectNumber, " ");
      
      startElems.clear();
      for (ReachableState state : reachabilityGraph.getStates())
      {
         reachabilityGraph.getLazyCloneOp().aggregate(startElems, state.getGraphRoot());
      }
      int actualObjectNumber = startElems.size();
      stats.createRows("actual object number:", actualObjectNumber, " ");
      
      startElems.clear();
      reachabilityGraph.getLazyCloneOp().collectComponent(startElems, soko);
      int collectedComponentSize = startElems.size();

      stats.createRows("collectable object number:", collectedComponentSize, " ");
      int garbageSize = collectedComponentSize - actualObjectNumber;
      stats.createRows("garbage object number:", garbageSize, " ");

      double ratio = 1.0 * actualObjectNumber / expectedObjectNumber * 100;
      String txt = String.format("%.2f", ratio);
      stats.createRows("ratio:", txt+"%", " ");
      
      story.addStep("Statistics:");
      story.addTable(stats);

      // story.assertEquals("Garbage size", 0, garbageSize);
      printStates(story, reachabilityGraph);
      
      Runtime runtime = Runtime.getRuntime();
      // Run the garbage collector
      runtime.gc();
      // Calculate the used memory
      long emptyMemory = runtime.totalMemory() - runtime.freeMemory();

      try 
      {
         Date now = Date.from(Instant.now());
         
         String msg = "" +  now + " memory lazy keep certificates " + emptyMemory + "\n";
         System.out.println(msg);
         Files.write(Paths.get("Sokoban.log"), msg.getBytes(), StandardOpenOption.APPEND);
         story.add(msg);
      }
      catch (IOException e) 
      {
         //exception handling left as an exercise for the reader
      }
      
      
      story.addObjectDiagramOnlyWith(reachabilityGraph.getStates(), reachabilityGraph.getStates().getRuleapplications());
      
      
      
      story.dumpHTML();
   }

   private void printStates(Storyboard story, ReachabilityGraph reachabilityGraph)
   {
      for (ReachableState state : reachabilityGraph.getStates())
      {
         story.addPreformatted(state.toString());
      }
   }

   private SokobanPO createMoveKarlRule()
   {
      SokobanPO sokoPO = new SokobanPO();
      
      KarliPO karliPO = sokoPO.createKarliPO();
      
      TilePO posPO = karliPO.createTilePO();
      
      TilePO newPosPO = posPO.createNeighborsPO();
      
      newPosPO.createWallCondition(false);
      
      sokoPO.startNAC();
      
      BoxPO obstacleBoxPO = sokoPO.createBoxesPO();
      
      obstacleBoxPO.createTileLink(newPosPO);
      
      sokoPO.endNAC();
      
      karliPO.createTileLink(posPO, Pattern.DESTROY);
      karliPO.createTileLink(newPosPO, Pattern.CREATE);
      
      return sokoPO;
   }

   private SokobanPO createPushBoxRule()
   {
      SokobanPO sokoPO = new SokobanPO();
      
      KarliPO karliPO = sokoPO.createKarliPO().withPatternObjectName("karli");
      
      TilePO karliPosPO = karliPO.createTilePO();
      
      BoxPO boxPO = sokoPO.createBoxesPO();
      
      TilePO boxPosPO = boxPO.createTilePO();
      
      karliPosPO.createNeighborsLink(boxPosPO);
      
      TilePO newBoxPosPO = boxPosPO.createNeighborsPO();
      
      newBoxPosPO.createWallCondition(false);
      
      newBoxPosPO.createCondition( p -> straight(karliPosPO, boxPosPO, newBoxPosPO), "straight karliPos, boxPos, newBoxPos");
      
      sokoPO.startNAC();
      
      BoxPO obstacleBoxPO = sokoPO.createBoxesPO();
      
      obstacleBoxPO.createTileLink(newBoxPosPO);
      
      sokoPO.endNAC();
      
      boxPO.createTileLink(newBoxPosPO, Pattern.CREATE);

      karliPO.createTileLink(boxPosPO, Pattern.CREATE);
      
      return sokoPO;
   }

   private boolean straight(TilePO karliPosPO, TilePO boxPosPO, TilePO newBoxPosPO)
   {
      int xDiff = karliPosPO.getX() - boxPosPO.getX();
      int yDiff = karliPosPO.getY() - boxPosPO.getY();
      
      if ( xDiff == boxPosPO.getX() - newBoxPosPO.getX() 
            && yDiff == boxPosPO.getY() - newBoxPosPO.getY())
      {
         return true;
      }
      return false;
   }

   private Sokoban createLevel(String level)
   {
      Sokoban soko = new Sokoban();
      Maze maze = soko.createMaze();

      Tile tile = null;
      Tile neighbor;
      int y = 0;
      
      String[] split = level.split("\n");
      maze.withHeight(split.length);
      for (String line : split)
      {
         maze.withWidth(line.length());
         for (int x = 0; x < line.length(); x++)
         {
            char c = line.charAt(x);
            
            if (c == ' ')
            {
               tile = null;
            }
            else if (c == 'w')
            {
               tile = maze.createTiles()
               .withX(x)
               .withY(y)
               .withWall(true);
            }
            else if (c == '.')
            {
               tile = maze.createTiles()
               .withX(x)
               .withY(y);               
            }
            else if (c == 'o')
            {
               tile = maze.createTiles()
               .withX(x)
               .withY(y)
               .withGoal(true);               
            }
            else if (c == 'b')
            {
               tile = maze.createTiles()
               .withX(x)
               .withY(y);   
               
               soko.createBoxes().withTile(tile);
            }
            else if (c == 'k')
            {
               tile = maze.createTiles()
               .withX(x)
               .withY(y);   
               
               soko.createKarli().withTile(tile);
            }
            
            neighbor = maze.getTile(x-1, y);
            if (neighbor != null)
            {
               neighbor.withNeighbors(tile);
            }
            neighbor = maze.getTile(x, y-1);
            if (neighbor != null)
            {
               neighbor.withNeighbors(tile);
            }
            
         }
         
         y++;
      }
      
      return soko;
   }
}
