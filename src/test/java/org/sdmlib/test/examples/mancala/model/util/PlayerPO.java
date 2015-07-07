package org.sdmlib.test.examples.mancala.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.mancala.model.Kalah;
import org.sdmlib.test.examples.mancala.model.Mancala;
import org.sdmlib.test.examples.mancala.model.Pit;
import org.sdmlib.test.examples.mancala.model.Player;
import org.sdmlib.test.examples.mancala.model.PlayerState;
import org.sdmlib.test.examples.mancala.referencemodel.Color;

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
      newInstance(org.sdmlib.test.examples.mancala.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PlayerPO(Player... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.mancala.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public PlayerPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public PlayerPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public PlayerPO hasState(PlayerState value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_STATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO createState(PlayerState value)
   {
      this.startCreate().hasState(value).endCreate();
      return this;
   }
   
   public PlayerState getState()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getState();
      }
      return null;
   }
   
   public PlayerPO withState(PlayerState value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setState(value);
      }
      return this;
   }
   
   public PlayerPO hasColor(Color value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_COLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO createColor(Color value)
   {
      this.startCreate().hasColor(value).endCreate();
      return this;
   }
   
   public Color getColor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getColor();
      }
      return null;
   }
   
   public PlayerPO withColor(Color value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setColor(value);
      }
      return this;
   }
   
   public MancalaPO hasActiveGame()
   {
      MancalaPO result = new MancalaPO(new Mancala[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_ACTIVEGAME, result);
      
      return result;
   }

   public MancalaPO createActiveGame()
   {
      return this.startCreate().hasActiveGame().endCreate();
   }

   public PlayerPO hasActiveGame(MancalaPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_ACTIVEGAME);
   }

   public PlayerPO createActiveGame(MancalaPO tgt)
   {
      return this.startCreate().hasActiveGame(tgt).endCreate();
   }

   public Mancala getActiveGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getActiveGame();
      }
      return null;
   }

   public MancalaPO hasGame()
   {
      MancalaPO result = new MancalaPO(new Mancala[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_GAME, result);
      
      return result;
   }

   public MancalaPO createGame()
   {
      return this.startCreate().hasGame().endCreate();
   }

   public PlayerPO hasGame(MancalaPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_GAME);
   }

   public PlayerPO createGame(MancalaPO tgt)
   {
      return this.startCreate().hasGame(tgt).endCreate();
   }

   public Mancala getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getGame();
      }
      return null;
   }

   public PitPO hasPits()
   {
      PitPO result = new PitPO(new Pit[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_PITS, result);
      
      return result;
   }

   public PitPO createPits()
   {
      return this.startCreate().hasPits().endCreate();
   }

   public PlayerPO hasPits(PitPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_PITS);
   }

   public PlayerPO createPits(PitPO tgt)
   {
      return this.startCreate().hasPits(tgt).endCreate();
   }

   public PitSet getPits()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getPits();
      }
      return null;
   }

   public KalahPO hasKalah()
   {
      KalahPO result = new KalahPO(new Kalah[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_KALAH, result);
      
      return result;
   }

   public KalahPO createKalah()
   {
      return this.startCreate().hasKalah().endCreate();
   }

   public PlayerPO hasKalah(KalahPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_KALAH);
   }

   public PlayerPO createKalah(KalahPO tgt)
   {
      return this.startCreate().hasKalah(tgt).endCreate();
   }

   public Kalah getKalah()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getKalah();
      }
      return null;
   }

}
