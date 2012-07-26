package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.Field;
import org.sdmlib.examples.ludo.Pawn;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.examples.ludo.creators.PlayerPO;
import org.sdmlib.examples.ludo.creators.PawnPO;
import org.sdmlib.examples.ludo.creators.FieldPO;

public class PawnPO extends PatternObject
{
   public PlayerPO hasPlayer()
   {
      PlayerPO result = new PlayerPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Pawn.PROPERTY_PLAYER)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }

   public PawnPO hasPlayer(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Pawn.PROPERTY_PLAYER)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
           
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO hasPos()
   {
      FieldPO result = new FieldPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Pawn.PROPERTY_POS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }

   public PawnPO endNAC()
   {
      Pattern directPattern = this.getPattern();
      if (directPattern instanceof NegativeApplicationCondition)
      {
         directPattern = directPattern.getPattern();
      }
      
      directPattern.setCurrentNAC(null);
      
      directPattern.findMatch();
      
      return this;
   }

   public void setPos(FieldPO startFieldPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pawn) this.getCurrentMatch()).setPos((Field) startFieldPO.getCurrentMatch());
      }      
   }

   
   
   public PawnPO hasColor(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pawn.PROPERTY_COLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PawnPO withColor(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pawn) getCurrentMatch()).withColor(value);
      }
      return this;
   }
   
   public PawnPO hasX(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pawn.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PawnPO withX(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pawn) getCurrentMatch()).withX(value);
      }
      return this;
   }
   
   public PawnPO hasY(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pawn.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PawnPO withY(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pawn) getCurrentMatch()).withY(value);
      }
      return this;
   }
   
   public PawnPO withPlayer(PlayerPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pawn) this.getCurrentMatch()).withPlayer((Player) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PawnPO hasPos(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Pawn.PROPERTY_POS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PawnPO withPos(FieldPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pawn) this.getCurrentMatch()).withPos((Field) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
}

