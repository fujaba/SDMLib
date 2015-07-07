package org.sdmlib.test.examples.mancala.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.mancala.model.Mancala;
import org.sdmlib.test.examples.mancala.model.Pit;
import org.sdmlib.test.examples.mancala.model.Player;

public class PitPO extends PatternObject<PitPO, Pit>
{

    public PitSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PitSet matches = new PitSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Pit) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PitPO(){
      newInstance(org.sdmlib.test.examples.mancala.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PitPO(Pit... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.mancala.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   //==========================================================================
   
   public void moveStones()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Pit) getCurrentMatch()).moveStones();
      }
   }

   public PitPO hasNr(int value)
   {
      new AttributeConstraint()
      .withAttrName(Pit.PROPERTY_NR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PitPO hasNr(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Pit.PROPERTY_NR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PitPO createNr(int value)
   {
      this.startCreate().hasNr(value).endCreate();
      return this;
   }
   
   public int getNr()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pit) getCurrentMatch()).getNr();
      }
      return 0;
   }
   
   public PitPO withNr(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pit) getCurrentMatch()).setNr(value);
      }
      return this;
   }
   
   public MancalaPO hasGame()
   {
      MancalaPO result = new MancalaPO(new Mancala[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pit.PROPERTY_GAME, result);
      
      return result;
   }

   public MancalaPO createGame()
   {
      return this.startCreate().hasGame().endCreate();
   }

   public PitPO hasGame(MancalaPO tgt)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_GAME);
   }

   public PitPO createGame(MancalaPO tgt)
   {
      return this.startCreate().hasGame(tgt).endCreate();
   }

   public Mancala getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pit) this.getCurrentMatch()).getGame();
      }
      return null;
   }

   public PlayerPO hasPlayer()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pit.PROPERTY_PLAYER, result);
      
      return result;
   }

   public PlayerPO createPlayer()
   {
      return this.startCreate().hasPlayer().endCreate();
   }

   public PitPO hasPlayer(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_PLAYER);
   }

   public PitPO createPlayer(PlayerPO tgt)
   {
      return this.startCreate().hasPlayer(tgt).endCreate();
   }

   public Player getPlayer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pit) this.getCurrentMatch()).getPlayer();
      }
      return null;
   }

   public PitPO hasNext()
   {
      PitPO result = new PitPO(new Pit[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pit.PROPERTY_NEXT, result);
      
      return result;
   }

   public PitPO createNext()
   {
      return this.startCreate().hasNext().endCreate();
   }

   public PitPO hasNext(PitPO tgt)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_NEXT);
   }

   public PitPO createNext(PitPO tgt)
   {
      return this.startCreate().hasNext(tgt).endCreate();
   }

   public Pit getNext()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pit) this.getCurrentMatch()).getNext();
      }
      return null;
   }

   public PitPO hasPrevious()
   {
      PitPO result = new PitPO(new Pit[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pit.PROPERTY_PREVIOUS, result);
      
      return result;
   }

   public PitPO createPrevious()
   {
      return this.startCreate().hasPrevious().endCreate();
   }

   public PitPO hasPrevious(PitPO tgt)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_PREVIOUS);
   }

   public PitPO createPrevious(PitPO tgt)
   {
      return this.startCreate().hasPrevious(tgt).endCreate();
   }

   public Pit getPrevious()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pit) this.getCurrentMatch()).getPrevious();
      }
      return null;
   }

   public PitPO hasCounterpart()
   {
      PitPO result = new PitPO(new Pit[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pit.PROPERTY_COUNTERPART, result);
      
      return result;
   }

   public PitPO createCounterpart()
   {
      return this.startCreate().hasCounterpart().endCreate();
   }

   public PitPO hasCounterpart(PitPO tgt)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_COUNTERPART);
   }

   public PitPO createCounterpart(PitPO tgt)
   {
      return this.startCreate().hasCounterpart(tgt).endCreate();
   }

   public Pit getCounterpart()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pit) this.getCurrentMatch()).getCounterpart();
      }
      return null;
   }

}
