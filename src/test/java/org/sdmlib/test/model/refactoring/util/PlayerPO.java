package org.sdmlib.test.model.refactoring.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.model.refactoring.Ludo;
import org.sdmlib.test.model.refactoring.Player;

public class PlayerPO extends PatternObject<PlayerPO, Player>
{

    public PlayerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PlayerSet matches = new PlayerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Player) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PlayerPO(){
      newInstance(org.sdmlib.test.model.refactoring.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PlayerPO(Player... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.model.refactoring.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public LudoPO hasGame()
   {
      LudoPO result = new LudoPO(new Ludo[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_GAME, result);
      
      return result;
   }

   public LudoPO createGame()
   {
      return this.startCreate().hasGame().endCreate();
   }

   public PlayerPO hasGame(LudoPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_GAME);
   }

   public PlayerPO createGame(LudoPO tgt)
   {
      return this.startCreate().hasGame(tgt).endCreate();
   }

   public Ludo getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getGame();
      }
      return null;
   }

   public LudoPO filterGame()
   {
      LudoPO result = new LudoPO(new Ludo[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_GAME, result);
      
      return result;
   }

   public PlayerPO filterGame(LudoPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_GAME);
   }


   public PlayerPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public LudoPO createGamePO()
   {
      LudoPO result = new LudoPO(new Ludo[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_GAME, result);
      
      return result;
   }

   public LudoPO createGamePO(String modifier)
   {
      LudoPO result = new LudoPO(new Ludo[]{});
      
      result.setModifier(modifier);
      super.hasLink(Player.PROPERTY_GAME, result);
      
      return result;
   }

   public PlayerPO createGameLink(LudoPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_GAME);
   }

   public PlayerPO createGameLink(LudoPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_GAME, modifier);
   }

}
