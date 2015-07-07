package org.sdmlib.test.examples.mancala.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.mancala.model.Mancala;
import org.sdmlib.test.examples.mancala.model.Pit;
import org.sdmlib.test.examples.mancala.model.Player;

public class MancalaPO extends PatternObject<MancalaPO, Mancala>
{

    public MancalaSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MancalaSet matches = new MancalaSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Mancala) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MancalaPO(){
      newInstance(org.sdmlib.test.examples.mancala.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MancalaPO(Mancala... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.mancala.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   //==========================================================================
   
   public void checkEnd()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Mancala) getCurrentMatch()).checkEnd();
      }
   }

   
   //==========================================================================
   
   public void initGame(String firstPlayerName, String secondPlayerName)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Mancala) getCurrentMatch()).initGame(firstPlayerName, secondPlayerName);
      }
   }

   public PlayerPO hasActivePlayer()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Mancala.PROPERTY_ACTIVEPLAYER, result);
      
      return result;
   }

   public PlayerPO createActivePlayer()
   {
      return this.startCreate().hasActivePlayer().endCreate();
   }

   public MancalaPO hasActivePlayer(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Mancala.PROPERTY_ACTIVEPLAYER);
   }

   public MancalaPO createActivePlayer(PlayerPO tgt)
   {
      return this.startCreate().hasActivePlayer(tgt).endCreate();
   }

   public Player getActivePlayer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Mancala) this.getCurrentMatch()).getActivePlayer();
      }
      return null;
   }

   public PlayerPO hasPlayers()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Mancala.PROPERTY_PLAYERS, result);
      
      return result;
   }

   public PlayerPO createPlayers()
   {
      return this.startCreate().hasPlayers().endCreate();
   }

   public MancalaPO hasPlayers(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Mancala.PROPERTY_PLAYERS);
   }

   public MancalaPO createPlayers(PlayerPO tgt)
   {
      return this.startCreate().hasPlayers(tgt).endCreate();
   }

   public PlayerSet getPlayers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Mancala) this.getCurrentMatch()).getPlayers();
      }
      return null;
   }

   public PitPO hasPits()
   {
      PitPO result = new PitPO(new Pit[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Mancala.PROPERTY_PITS, result);
      
      return result;
   }

   public PitPO createPits()
   {
      return this.startCreate().hasPits().endCreate();
   }

   public MancalaPO hasPits(PitPO tgt)
   {
      return hasLinkConstraint(tgt, Mancala.PROPERTY_PITS);
   }

   public MancalaPO createPits(PitPO tgt)
   {
      return this.startCreate().hasPits(tgt).endCreate();
   }

   public PitSet getPits()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Mancala) this.getCurrentMatch()).getPits();
      }
      return null;
   }

}
