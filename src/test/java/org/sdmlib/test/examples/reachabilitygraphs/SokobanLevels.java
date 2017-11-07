package org.sdmlib.test.examples.reachabilitygraphs;

import static org.junit.Assert.*;

import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Maze;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Sokoban;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Tile;

public class SokobanLevels
{
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

      story.dumpHTML();
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
            neighbor = maze.getTile(x-1, y-1);
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
