package org.sdmlib.examples.ludo.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.ludo.Ludo;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.ludo.creators.PlayerPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.ludo.creators.LudoPO;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.examples.ludo.creators.DicePO;
import org.sdmlib.examples.ludo.Dice;
import org.sdmlib.examples.ludo.creators.FieldPO;
import org.sdmlib.examples.ludo.Field;

public class LudoPO extends PatternObject
{
   public PlayerPO hasPlayers()
   {
      PlayerPO result = new PlayerPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Ludo.PROPERTY_PLAYERS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public LudoPO hasPlayers(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Ludo.PROPERTY_PLAYERS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LudoPO withPlayers(PlayerPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Ludo) this.getCurrentMatch()).withPlayers((Player) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public LudoPO withoutPlayers(PlayerPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Ludo) this.getCurrentMatch()).withoutPlayers((Player) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public DicePO hasDice()
   {
      DicePO result = new DicePO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Ludo.PROPERTY_DICE)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public LudoPO hasDice(DicePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Ludo.PROPERTY_DICE)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LudoPO withDice(DicePO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Ludo) this.getCurrentMatch()).withDice((Dice) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public FieldPO hasFields()
   {
      FieldPO result = new FieldPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Ludo.PROPERTY_FIELDS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public LudoPO hasFields(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Ludo.PROPERTY_FIELDS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LudoPO withFields(FieldPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Ludo) this.getCurrentMatch()).withFields((Field) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public LudoPO withoutFields(FieldPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Ludo) this.getCurrentMatch()).withoutFields((Field) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
}


