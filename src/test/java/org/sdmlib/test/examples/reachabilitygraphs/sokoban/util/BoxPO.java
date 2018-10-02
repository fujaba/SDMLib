package org.sdmlib.test.examples.reachabilitygraphs.sokoban.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Box;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Tile;

public class BoxPO extends PatternObject<BoxPO, Box>
{

    public BoxSet allMatches()
   {
      this.setDoAllMatches(true);
      
      BoxSet matches = new BoxSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Box) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public BoxPO(){
      newInstance(null);
   }

   public BoxPO(Box... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public BoxPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public TilePO createTilePO()
   {
      TilePO result = new TilePO(new Tile[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Box.PROPERTY_TILE, result);
      
      return result;
   }

   public TilePO createTilePO(String modifier)
   {
      TilePO result = new TilePO(new Tile[]{});
      
      result.setModifier(modifier);
      super.hasLink(Box.PROPERTY_TILE, result);
      
      return result;
   }

   public BoxPO createTileLink(TilePO tgt)
   {
      return hasLinkConstraint(tgt, Box.PROPERTY_TILE);
   }

   public BoxPO createTileLink(TilePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Box.PROPERTY_TILE, modifier);
   }

   public Tile getTile()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Box) this.getCurrentMatch()).getTile();
      }
      return null;
   }

}
