package org.sdmlib.test.examples.reachabilitygraphs.sokoban.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Karli;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.SokobanPO;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Sokoban;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.KarliPO;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.SokobanSet;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.TilePO;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Tile;

public class KarliPO extends PatternObject<KarliPO, Karli>
{

    public KarliSet allMatches()
   {
      this.setDoAllMatches(true);
      
      KarliSet matches = new KarliSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Karli) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public KarliPO(){
      newInstance(null);
   }

   public KarliPO(Karli... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public KarliPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public SokobanPO createSokobanPO()
   {
      SokobanPO result = new SokobanPO(new Sokoban[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Karli.PROPERTY_SOKOBAN, result);
      
      return result;
   }

   public SokobanPO createSokobanPO(String modifier)
   {
      SokobanPO result = new SokobanPO(new Sokoban[]{});
      
      result.setModifier(modifier);
      super.hasLink(Karli.PROPERTY_SOKOBAN, result);
      
      return result;
   }

   public KarliPO createSokobanLink(SokobanPO tgt)
   {
      return hasLinkConstraint(tgt, Karli.PROPERTY_SOKOBAN);
   }

   public KarliPO createSokobanLink(SokobanPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Karli.PROPERTY_SOKOBAN, modifier);
   }

   public SokobanSet getSokoban()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Karli) this.getCurrentMatch()).getSokoban();
      }
      return null;
   }

   public TilePO createTilePO()
   {
      TilePO result = new TilePO(new Tile[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Karli.PROPERTY_TILE, result);
      
      return result;
   }

   public TilePO createTilePO(String modifier)
   {
      TilePO result = new TilePO(new Tile[]{});
      
      result.setModifier(modifier);
      super.hasLink(Karli.PROPERTY_TILE, result);
      
      return result;
   }

   public KarliPO createTileLink(TilePO tgt)
   {
      return hasLinkConstraint(tgt, Karli.PROPERTY_TILE);
   }

   public KarliPO createTileLink(TilePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Karli.PROPERTY_TILE, modifier);
   }

   public Tile getTile()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Karli) this.getCurrentMatch()).getTile();
      }
      return null;
   }

}
