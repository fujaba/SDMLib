package org.sdmlib.test.examples.reachabilitygraphs;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Logger;

import org.junit.Test;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachabilityGraph.Searchmode;
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
import de.uniks.networkparser.list.SimpleList;

public class SokobanLevels
{
   private String level;
   private ReachabilityGraph reachabilityGraph;

   @Test
   public void SokobanSimpleLevel() throws Exception
   {
      level = ""
            + "  wwww\n"
            + "  w..w\n"
            + "wwwb.w\n"
            + "woobkw\n"
            + "wwwwww\n";
      
      long usedMillis = SokobanLevel1(false, false, "Level0", level, 3000);
      
      int size = reachabilityGraph.getStates().size();
      
      for (ReachableState s : reachabilityGraph.getStates())
      {
         System.out.println(s.toString());
      }
      
      assertEquals("wrong number of levels", 42, size);
   }
   
   
   public static void main(String[] args)
   {
      try
      {
         new SokobanLevels().SokobanLevel1();
      }
      catch (Exception e)
      {
         Logger.getGlobal().warning(e.toString());
      }
   }
   
   public void SokobanLevel1() throws Exception
   {
      
      
      level = ""
            + "     wwwww\n"
            + "    ww.o.w\n"
            + "   ww.bo.w\n"
            + "  ww.b...w\n"
            + " ww.bk.www\n"
            + " w.b..ww  \n"
            + " woo.ww   \n"
            + " w...ww   \n"
            + " wwwwww   \n";
      
         
      long prevKeepTimeLong = Long.MAX_VALUE;
      long prevCleanTimeLong = Long.MAX_VALUE;
      long newKeepTimeLong = Long.MAX_VALUE - 1;
      long newCleanTimeLong = Long.MAX_VALUE - 1;

      long prevKeepTimeString = Long.MAX_VALUE;
      long prevCleanTimeString = Long.MAX_VALUE;
      long newKeepTimeString = Long.MAX_VALUE - 1;
      long newCleanTimeString = Long.MAX_VALUE - 1;
      
      int abortCount = 0;

      while (abortCount < 2)
      {
         if (newKeepTimeLong < prevKeepTimeLong && newCleanTimeLong < prevCleanTimeLong 
         && newKeepTimeString < prevKeepTimeString && newCleanTimeString < prevCleanTimeString)
         {
            abortCount = 0;
         }
         else
         {
            abortCount++;
         }
         
         prevCleanTimeLong = newCleanTimeLong;
         
         newCleanTimeLong = SokobanLevel1(true, true, "Level1", level, 3000);

         prevKeepTimeLong = newKeepTimeLong;
         
         newKeepTimeLong = SokobanLevel1(false, true, "Level1", level, 3000);
         
         prevCleanTimeString = newCleanTimeString;
         
         newCleanTimeString = SokobanLevel1(true, false, "Level1", level, 3000);

         prevKeepTimeString = newKeepTimeString;
         
         newKeepTimeString = SokobanLevel1(false, false, "Level1", level, 3000);
         
      }
   }

   public long SokobanLevel1(boolean cleanUp, boolean useLong, String levelName, String level, long noOfStates) throws Exception
   {
      Storyboard story = new Storyboard().withDocDirName("doc/internal");
      
      story.getStoryboard().withName("SokobanLevel1_" + levelName + cleanUp);
      
      story.addStep("First Level");
      
      Sokoban soko = createLevel(level);
      
      // story.addObjectDiagram(soko);
      
      SokobanPO sokoPO = createMoveKarlRule();

      story.addPattern(sokoPO, false);
      
      SokobanPO pushBoxSokoPO = createPushBoxRule();
      
      reachabilityGraph = new ReachabilityGraph()
            .withMasterMap(SokobanCreator.createIdMap("s"))
            .withRules("move karl", sokoPO)
            .withRules("push box", pushBoxSokoPO);
      
      ObjectSet statics = new ObjectSet();
      statics.with(soko.getMaze()).withList(soko.getMaze().getTiles());
      reachabilityGraph.setStaticNodes(statics);
      
      if (cleanUp)
      {
         reachabilityGraph.withDoCleanUpTemps();
      }
      
      if (useLong)
      {
         reachabilityGraph.withUseLongCertificates();
      }
      
      ReachableState startState = new ReachableState().withGraphRoot(soko);
      reachabilityGraph.withStart(startState);
      
      ObjectSet startElems = new ObjectSet();
      SimpleList<Object> dynEdges = new SimpleList<Object>();
      // reachabilityGraph.getLazyCloneOp().aggregate(startElems, dynEdges, reachabilityGraph.getStaticNodes(), soko);
      
      //=============================================================
      long startTime = System.currentTimeMillis();
      reachabilityGraph.explore(noOfStates, Searchmode.DEFAULT);
      long endTime = System.currentTimeMillis();
      long usedMillis = endTime - startTime;
      //=============================================================
      
      Table stats = new Table();
      stats.createColumns().withName("Descr").withTdCssClass("text-right col-md-1");
      stats.createColumns().withName("Data").withTdCssClass("text-right col-md-1");
      stats.createColumns().withName(" ").withTdCssClass("text-right col-md-6");
      
      int startElemsSize = startElems.size();
      stats.createRows("nodes per state:", startElemsSize, " ");
      
      noOfStates = reachabilityGraph.getStates().size();
      stats.createRows("number of states:", noOfStates, " ");
      
      long expectedObjectNumber = startElemsSize * noOfStates;
      stats.createRows("product:", expectedObjectNumber, " ");
      
      startElems.clear();
      dynEdges.clear();
      
      for (ReachableState state : reachabilityGraph.getStates())
      {
         reachabilityGraph.getLazyCloneOp().aggregate(startElems, dynEdges, reachabilityGraph.getStaticNodes(), state.getGraphRoot());
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
      // printStates(story, reachabilityGraph);
      
      Runtime runtime = Runtime.getRuntime();
      // Run the garbage collector
      runtime.gc();
      // Calculate the used memory
      long emptyMemory = runtime.totalMemory() - runtime.freeMemory();

      try 
      {
         Date now = Date.from(Instant.now());
         
         String msg = String.format("%s memory lazy %s uselong %s certificates %,d bytes %,d millis \n", 
            now.toString(), 
            cleanUp ? "clean" : "keep ",
            useLong ? "true " : "false",
            emptyMemory, usedMillis );
         System.out.println(msg);
         Files.write(Paths.get("Sokoban.log"), msg.getBytes(), StandardOpenOption.APPEND);
         story.add(msg);
      }
      catch (IOException e) 
      {
         //exception handling left as an exercise for the reader
      }
      
      
      // story.addObjectDiagramOnlyWith(reachabilityGraph.getStates(), reachabilityGraph.getStates().getRuleapplications());
      
      
      
      story.dumpHTML();
      
      return usedMillis;
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
