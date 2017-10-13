package org.sdmlib.simple.model.abstract_C.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.abstract_C.Game;
import org.sdmlib.simple.model.abstract_C.util.GroundPO;
import org.sdmlib.simple.model.abstract_C.Ground;
import org.sdmlib.simple.model.abstract_C.util.GamePO;
import org.sdmlib.simple.model.abstract_C.util.GroundSet;

public class GamePO extends PatternObject<GamePO, Game>
{

    public GameSet allMatches()
   {
      this.setDoAllMatches(true);
      
      GameSet matches = new GameSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Game) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public GamePO(){
      newInstance(null);
   }

   public GamePO(Game... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public GamePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public GroundPO createGroundsPO()
   {
      GroundPO result = new GroundPO(new Ground[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Game.PROPERTY_GROUNDS, result);
      
      return result;
   }

   public GroundPO createGroundsPO(String modifier)
   {
      GroundPO result = new GroundPO(new Ground[]{});
      
      result.setModifier(modifier);
      super.hasLink(Game.PROPERTY_GROUNDS, result);
      
      return result;
   }

   public GamePO createGroundsLink(GroundPO tgt)
   {
      return hasLinkConstraint(tgt, Game.PROPERTY_GROUNDS);
   }

   public GamePO createGroundsLink(GroundPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Game.PROPERTY_GROUNDS, modifier);
   }

   public GroundSet getGrounds()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Game) this.getCurrentMatch()).getGrounds();
      }
      return null;
   }

}
