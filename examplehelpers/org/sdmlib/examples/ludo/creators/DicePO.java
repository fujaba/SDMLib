package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.Dice;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.ludo.creators.LudoPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.ludo.creators.DicePO;
import org.sdmlib.examples.ludo.Ludo;
import org.sdmlib.examples.ludo.creators.PlayerPO;
import org.sdmlib.examples.ludo.Player;

public class DicePO extends PatternObject
{

   public DicePO hasValue(int i)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Dice.PROPERTY_VALUE)
      .withTgtValue(i)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }

   public DicePO withValue(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Dice) getCurrentMatch()).withValue(value);
      }
      return this;
   }
   
   public LudoPO hasGame()
   {
      LudoPO result = new LudoPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Dice.PROPERTY_GAME)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public DicePO hasGame(LudoPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Dice.PROPERTY_GAME)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public DicePO withGame(LudoPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Dice) this.getCurrentMatch()).withGame((Ludo) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PlayerPO hasPlayer()
   {
      PlayerPO result = new PlayerPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Dice.PROPERTY_PLAYER)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public DicePO hasPlayer(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Dice.PROPERTY_PLAYER)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public DicePO withPlayer(PlayerPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Dice) this.getCurrentMatch()).withPlayer((Player) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
}

