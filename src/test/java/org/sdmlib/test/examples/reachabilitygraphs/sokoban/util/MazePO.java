package org.sdmlib.test.examples.reachabilitygraphs.sokoban.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Maze;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Tile;

public class MazePO extends PatternObject<MazePO, Maze>
{

    public MazeSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MazeSet matches = new MazeSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Maze) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MazePO(){
      newInstance(null);
   }

   public MazePO(Maze... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public MazePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public MazePO createHeightCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Maze.PROPERTY_HEIGHT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MazePO createHeightCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Maze.PROPERTY_HEIGHT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MazePO createHeightAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Maze.PROPERTY_HEIGHT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getHeight()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Maze) getCurrentMatch()).getHeight();
      }
      return 0;
   }
   
   public MazePO withHeight(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Maze) getCurrentMatch()).setHeight(value);
      }
      return this;
   }
   
   public MazePO createWidthCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Maze.PROPERTY_WIDTH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MazePO createWidthCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Maze.PROPERTY_WIDTH)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MazePO createWidthAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Maze.PROPERTY_WIDTH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getWidth()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Maze) getCurrentMatch()).getWidth();
      }
      return 0;
   }
   
   public MazePO withWidth(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Maze) getCurrentMatch()).setWidth(value);
      }
      return this;
   }
   
   public TilePO createTilesPO()
   {
      TilePO result = new TilePO(new Tile[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Maze.PROPERTY_TILES, result);
      
      return result;
   }

   public TilePO createTilesPO(String modifier)
   {
      TilePO result = new TilePO(new Tile[]{});
      
      result.setModifier(modifier);
      super.hasLink(Maze.PROPERTY_TILES, result);
      
      return result;
   }

   public MazePO createTilesLink(TilePO tgt)
   {
      return hasLinkConstraint(tgt, Maze.PROPERTY_TILES);
   }

   public MazePO createTilesLink(TilePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Maze.PROPERTY_TILES, modifier);
   }

   public TileSet getTiles()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Maze) this.getCurrentMatch()).getTiles();
      }
      return null;
   }

}
