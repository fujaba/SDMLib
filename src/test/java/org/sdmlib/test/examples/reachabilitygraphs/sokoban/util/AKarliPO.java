package org.sdmlib.test.examples.reachabilitygraphs.sokoban.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.AKarli;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.TilePO;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Tile;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.AKarliPO;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.TileSet;

public class AKarliPO extends PatternObject<AKarliPO, AKarli>
{

    public AKarliSet allMatches()
   {
      this.setDoAllMatches(true);
      
      AKarliSet matches = new AKarliSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((AKarli) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public AKarliPO(){
      newInstance(null);
   }

   public AKarliPO(AKarli... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public AKarliPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public TilePO createTilesPO()
   {
      TilePO result = new TilePO(new Tile[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(AKarli.PROPERTY_TILES, result);
      
      return result;
   }

   public TilePO createTilesPO(String modifier)
   {
      TilePO result = new TilePO(new Tile[]{});
      
      result.setModifier(modifier);
      super.hasLink(AKarli.PROPERTY_TILES, result);
      
      return result;
   }

   public AKarliPO createTilesLink(TilePO tgt)
   {
      return hasLinkConstraint(tgt, AKarli.PROPERTY_TILES);
   }

   public AKarliPO createTilesLink(TilePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, AKarli.PROPERTY_TILES, modifier);
   }

   public TileSet getTiles()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((AKarli) this.getCurrentMatch()).getTiles();
      }
      return null;
   }

}
