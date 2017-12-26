package org.sdmlib.test.examples.reachabilitygraphs.sokoban.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Tile;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.TilePO;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.TileSet;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.MazePO;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Maze;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.MazeSet;

public class TilePO extends PatternObject<TilePO, Tile>
{

    public TileSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TileSet matches = new TileSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Tile) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public TilePO(){
      newInstance(null);
   }

   public TilePO(Tile... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public TilePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public TilePO createGoalCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Tile.PROPERTY_GOAL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TilePO createGoalAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Tile.PROPERTY_GOAL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public boolean getGoal()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Tile) getCurrentMatch()).isGoal();
      }
      return false;
   }
   
   public TilePO withGoal(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Tile) getCurrentMatch()).setGoal(value);
      }
      return this;
   }
   
   public TilePO createWallCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Tile.PROPERTY_WALL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TilePO createWallAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Tile.PROPERTY_WALL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public boolean getWall()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Tile) getCurrentMatch()).isWall();
      }
      return false;
   }
   
   public TilePO withWall(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Tile) getCurrentMatch()).setWall(value);
      }
      return this;
   }
   
   public TilePO createXCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Tile.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TilePO createXCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Tile.PROPERTY_X)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TilePO createXAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Tile.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getX()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Tile) getCurrentMatch()).getX();
      }
      return 0;
   }
   
   public TilePO withX(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Tile) getCurrentMatch()).setX(value);
      }
      return this;
   }
   
   public TilePO createYCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Tile.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TilePO createYCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Tile.PROPERTY_Y)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TilePO createYAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Tile.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getY()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Tile) getCurrentMatch()).getY();
      }
      return 0;
   }
   
   public TilePO withY(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Tile) getCurrentMatch()).setY(value);
      }
      return this;
   }
   
   public TilePO createNeighborsPO()
   {
      TilePO result = new TilePO(new Tile[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Tile.PROPERTY_NEIGHBORS, result);
      
      return result;
   }

   public TilePO createNeighborsPO(String modifier)
   {
      TilePO result = new TilePO(new Tile[]{});
      
      result.setModifier(modifier);
      super.hasLink(Tile.PROPERTY_NEIGHBORS, result);
      
      return result;
   }

   public TilePO createNeighborsLink(TilePO tgt)
   {
      return hasLinkConstraint(tgt, Tile.PROPERTY_NEIGHBORS);
   }

   public TilePO createNeighborsLink(TilePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Tile.PROPERTY_NEIGHBORS, modifier);
   }

   public TileSet getNeighbors()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Tile) this.getCurrentMatch()).getNeighbors();
      }
      return null;
   }

   public MazePO createMazePO()
   {
      MazePO result = new MazePO(new Maze[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Tile.PROPERTY_MAZE, result);
      
      return result;
   }

   public MazePO createMazePO(String modifier)
   {
      MazePO result = new MazePO(new Maze[]{});
      
      result.setModifier(modifier);
      super.hasLink(Tile.PROPERTY_MAZE, result);
      
      return result;
   }

   public TilePO createMazeLink(MazePO tgt)
   {
      return hasLinkConstraint(tgt, Tile.PROPERTY_MAZE);
   }

   public TilePO createMazeLink(MazePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Tile.PROPERTY_MAZE, modifier);
   }

   public MazeSet getMaze()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Tile) this.getCurrentMatch()).getMaze();
      }
      return null;
   }

}
