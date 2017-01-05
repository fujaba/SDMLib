package org.sdmlib.simple.model.abstract_C.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.abstract_C.Game;
import org.sdmlib.simple.model.abstract_C.Ground;

public class GroundPO extends PatternObject<GroundPO, Ground>
{

    public GroundSet allMatches()
   {
      this.setDoAllMatches(true);
      
      GroundSet matches = new GroundSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Ground) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public GroundPO(){
      newInstance(null);
   }

   public GroundPO(Ground... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public GroundPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public GamePO createGamePO()
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Ground.PROPERTY_GAME, result);
      
      return result;
   }

   public GamePO createGamePO(String modifier)
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(modifier);
      super.hasLink(Ground.PROPERTY_GAME, result);
      
      return result;
   }

   public GroundPO createGameLink(GamePO tgt)
   {
      return hasLinkConstraint(tgt, Ground.PROPERTY_GAME);
   }

   public GroundPO createGameLink(GamePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Ground.PROPERTY_GAME, modifier);
   }

   public Game getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Ground) this.getCurrentMatch()).getGame();
      }
      return null;
   }

}
