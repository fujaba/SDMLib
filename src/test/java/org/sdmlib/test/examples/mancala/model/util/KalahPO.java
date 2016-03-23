package org.sdmlib.test.examples.mancala.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.mancala.model.Kalah;
import org.sdmlib.test.examples.mancala.model.Mancala;
import org.sdmlib.test.examples.mancala.model.Pit;
import org.sdmlib.test.examples.mancala.model.Player;

public class KalahPO extends PatternObject<KalahPO, Kalah>
{

    public KalahSet allMatches()
   {
      this.setDoAllMatches(true);
      
      KalahSet matches = new KalahSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Kalah) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public KalahPO(){
      newInstance(org.sdmlib.test.examples.mancala.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public KalahPO(Kalah... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.mancala.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public KalahPO hasNr(int value)
   {
      new AttributeConstraint()
      .withAttrName(Kalah.PROPERTY_NR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public KalahPO hasNr(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Kalah.PROPERTY_NR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public KalahPO createNr(int value)
   {
      this.startCreate().hasNr(value).endCreate();
      return this;
   }
   
   public int getNr()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Kalah) getCurrentMatch()).getNr();
      }
      return 0;
   }
   
   public KalahPO withNr(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Kalah) getCurrentMatch()).setNr(value);
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

   public KalahPO hasGame(MancalaPO tgt)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_GAME);
   }

   public KalahPO createGame(MancalaPO tgt)
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

   public KalahPO hasPlayer(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_PLAYER);
   }

   public KalahPO createPlayer(PlayerPO tgt)
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

   public KalahPO hasNext(PitPO tgt)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_NEXT);
   }

   public KalahPO createNext(PitPO tgt)
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

   public KalahPO hasPrevious(PitPO tgt)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_PREVIOUS);
   }

   public KalahPO createPrevious(PitPO tgt)
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

   public KalahPO hasCounterpart(PitPO tgt)
   {
      return hasLinkConstraint(tgt, Pit.PROPERTY_COUNTERPART);
   }

   public KalahPO createCounterpart(PitPO tgt)
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

   public PlayerPO hasKalahPlayer()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Kalah.PROPERTY_KALAHPLAYER, result);
      
      return result;
   }

   public PlayerPO createKalahPlayer()
   {
      return this.startCreate().hasKalahPlayer().endCreate();
   }

   public KalahPO hasKalahPlayer(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Kalah.PROPERTY_KALAHPLAYER);
   }

   public KalahPO createKalahPlayer(PlayerPO tgt)
   {
      return this.startCreate().hasKalahPlayer(tgt).endCreate();
   }

   public Player getKalahPlayer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Kalah) this.getCurrentMatch()).getKalahPlayer();
      }
      return null;
   }

   public KalahPO filterNr(int value)
   {
      new AttributeConstraint()
      .withAttrName(Kalah.PROPERTY_NR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public KalahPO filterNr(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Kalah.PROPERTY_NR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
}
