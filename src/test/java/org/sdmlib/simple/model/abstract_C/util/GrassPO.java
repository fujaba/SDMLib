package org.sdmlib.simple.model.abstract_C.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.abstract_C.Grass;
import org.sdmlib.simple.model.abstract_C.util.GamePO;
import org.sdmlib.simple.model.abstract_C.Game;
import org.sdmlib.simple.model.abstract_C.util.GrassPO;

public class GrassPO extends PatternObject<GrassPO, Grass>
{

    public GrassSet allMatches()
   {
      this.setDoAllMatches(true);
      
      GrassSet matches = new GrassSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Grass) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public GrassPO(){
      newInstance(null);
   }

   public GrassPO(Grass... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public GrassPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public GamePO createGamePO()
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Grass.PROPERTY_GAME, result);
      
      return result;
   }

   public GamePO createGamePO(String modifier)
   {
      GamePO result = new GamePO(new Game[]{});
      
      result.setModifier(modifier);
      super.hasLink(Grass.PROPERTY_GAME, result);
      
      return result;
   }

   public GrassPO createGameLink(GamePO tgt)
   {
      return hasLinkConstraint(tgt, Grass.PROPERTY_GAME);
   }

   public GrassPO createGameLink(GamePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Grass.PROPERTY_GAME, modifier);
   }

   public Game getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Grass) this.getCurrentMatch()).getGame();
      }
      return null;
   }

}
