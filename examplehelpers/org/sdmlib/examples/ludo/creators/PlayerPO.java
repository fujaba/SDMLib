package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.Pawn;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.examples.ludo.creators.LudoPO;
import org.sdmlib.examples.ludo.creators.PlayerPO;
import org.sdmlib.examples.ludo.Ludo;
import org.sdmlib.examples.ludo.creators.DicePO;
import org.sdmlib.examples.ludo.Dice;
import org.sdmlib.examples.ludo.creators.FieldPO;
import org.sdmlib.examples.ludo.Field;
import org.sdmlib.examples.ludo.creators.PawnPO;

public class PlayerPO extends PatternObject
{
   public DicePO hasDice()
   {
      DicePO result = new DicePO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Player.PROPERTY_DICE)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }

   public FieldPO hasStart()
   {
      FieldPO result = new FieldPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Player.PROPERTY_START)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }

   public PlayerPO hasBase(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_BASE)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public PlayerPO hasColor(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Player.PROPERTY_COLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO withColor(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).withColor(value);
      }
      return this;
   }
   
   public PlayerPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).withName(value);
      }
      return this;
   }
   
   public PlayerPO hasX(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Player.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO withX(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).withX(value);
      }
      return this;
   }
   
   public PlayerPO hasY(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Player.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO withY(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).withY(value);
      }
      return this;
   }
   
   public LudoPO hasGame()
   {
      LudoPO result = new LudoPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Player.PROPERTY_GAME)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public PlayerPO hasGame(LudoPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_GAME)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO withGame(LudoPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) this.getCurrentMatch()).withGame((Ludo) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PlayerPO hasNext()
   {
      PlayerPO result = new PlayerPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Player.PROPERTY_NEXT)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public PlayerPO hasNext(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_NEXT)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO withNext(PlayerPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) this.getCurrentMatch()).withNext((Player) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PlayerPO hasPrev()
   {
      PlayerPO result = new PlayerPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Player.PROPERTY_PREV)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public PlayerPO hasPrev(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_PREV)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO withPrev(PlayerPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) this.getCurrentMatch()).withPrev((Player) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PlayerPO hasDice(DicePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_DICE)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO withDice(DicePO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) this.getCurrentMatch()).withDice((Dice) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PlayerPO hasStart(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_START)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO withStart(FieldPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) this.getCurrentMatch()).withStart((Field) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public FieldPO hasBase()
   {
      FieldPO result = new FieldPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Player.PROPERTY_BASE)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public PlayerPO withBase(FieldPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) this.getCurrentMatch()).withBase((Field) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public FieldPO hasLanding()
   {
      FieldPO result = new FieldPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Player.PROPERTY_LANDING)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public PlayerPO hasLanding(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_LANDING)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO withLanding(FieldPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) this.getCurrentMatch()).withLanding((Field) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PawnPO hasPawns()
   {
      PawnPO result = new PawnPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Player.PROPERTY_PAWNS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public PlayerPO hasPawns(PawnPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_PAWNS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO withPawns(PawnPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) this.getCurrentMatch()).withPawns((Pawn) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PlayerPO withoutPawns(PawnPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) this.getCurrentMatch()).withoutPawns((Pawn) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
}

