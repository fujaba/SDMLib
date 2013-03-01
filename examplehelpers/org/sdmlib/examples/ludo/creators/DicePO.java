package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.Dice;
import org.sdmlib.examples.ludo.Ludo;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class DicePO extends PatternObject<DicePO, Dice>
{
   public DiceSet allMatches()
   {
      this.setDoAllMatches(true);
      
      DiceSet matches = new DiceSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Dice) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public DicePO hasValue(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Dice.PROPERTY_VALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getValue()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Dice) getCurrentMatch()).getValue();
      }
      return 0;
   }
   
   public LudoPO hasGame()
   {
      LudoPO result = new LudoPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Dice.PROPERTY_GAME, result);
      
      return result;
   }
   
   public DicePO hasGame(LudoPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Dice.PROPERTY_GAME)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Ludo getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Dice) this.getCurrentMatch()).getGame();
      }
      return null;
   }
   
   public PlayerPO hasPlayer()
   {
      PlayerPO result = new PlayerPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Dice.PROPERTY_PLAYER, result);
      
      return result;
   }
   
   public DicePO hasPlayer(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Dice.PROPERTY_PLAYER)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Player getPlayer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Dice) this.getCurrentMatch()).getPlayer();
      }
      return null;
   }
   
}

