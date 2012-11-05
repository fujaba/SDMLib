package org.sdmlib.examples.ludo.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.examples.ludo.creators.PlayerSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.ludo.creators.LudoPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.ludo.creators.PlayerPO;
import org.sdmlib.examples.ludo.Ludo;
import org.sdmlib.examples.ludo.creators.DicePO;
import org.sdmlib.examples.ludo.Dice;
import org.sdmlib.examples.ludo.creators.FieldPO;
import org.sdmlib.examples.ludo.Field;
import org.sdmlib.examples.ludo.creators.PawnPO;
import org.sdmlib.examples.ludo.Pawn;
import org.sdmlib.examples.ludo.creators.PawnSet;
import org.sdmlib.examples.ludo.LudoModel.LudoColor;

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
   
   public PlayerPO hasColor(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Player.PROPERTY_COLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getColor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getColor();
      }
      return null;
   }
   
   public PlayerPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
   
   public PlayerPO hasX(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Player.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getX()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getX();
      }
      return 0;
   }
   
   public PlayerPO hasY(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Player.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getY()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getY();
      }
      return 0;
   }
   
   public LudoPO hasGame()
   {
      LudoPO result = new LudoPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Player.PROPERTY_GAME, result);
      
      return result;
   }
   
   public PlayerPO hasGame(LudoPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_GAME)
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
         return ((Player) this.getCurrentMatch()).getGame();
      }
      return null;
   }
   
   public PlayerPO hasNext()
   {
      PlayerPO result = new PlayerPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Player.PROPERTY_NEXT, result);
      
      return result;
   }
   
   public PlayerPO hasNext(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_NEXT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Player getNext()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getNext();
      }
      return null;
   }
   
   public PlayerPO hasPrev()
   {
      PlayerPO result = new PlayerPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Player.PROPERTY_PREV, result);
      
      return result;
   }
   
   public PlayerPO hasPrev(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_PREV)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Player getPrev()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getPrev();
      }
      return null;
   }
   
   public DicePO hasDice()
   {
      DicePO result = new DicePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Player.PROPERTY_DICE, result);
      
      return result;
   }
   
   public PlayerPO hasDice(DicePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_DICE)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Dice getDice()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getDice();
      }
      return null;
   }
   
   public FieldPO hasStart()
   {
      FieldPO result = new FieldPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Player.PROPERTY_START, result);
      
      return result;
   }
   
   public PlayerPO hasStart(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_START)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Field getStart()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getStart();
      }
      return null;
   }
   
   public FieldPO hasBase()
   {
      FieldPO result = new FieldPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Player.PROPERTY_BASE, result);
      
      return result;
   }
   
   public PlayerPO hasBase(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_BASE)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Field getBase()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getBase();
      }
      return null;
   }
   
   public FieldPO hasLanding()
   {
      FieldPO result = new FieldPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Player.PROPERTY_LANDING, result);
      
      return result;
   }
   
   public PlayerPO hasLanding(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_LANDING)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Field getLanding()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getLanding();
      }
      return null;
   }
   
   public PawnPO hasPawns()
   {
      PawnPO result = new PawnPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Player.PROPERTY_PAWNS, result);
      
      return result;
   }
   
   public PlayerPO hasPawns(PawnPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_PAWNS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PawnSet getPawns()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getPawns();
      }
      return null;
   }
   
   public PlayerPO hasEnumColor(LudoColor value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Player.PROPERTY_ENUMCOLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LudoColor getEnumColor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getEnumColor();
      }
      return null;
   }
   
   public PlayerPO withEnumColor(LudoColor value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setEnumColor(value);
      }
      return this;
   }
   
}



