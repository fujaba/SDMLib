package org.sdmlib.examples.ludo.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.ludo.model.Pawn;
import org.sdmlib.examples.ludo.model.util.PawnSet;
import org.sdmlib.models.pattern.AttributeConstraint;

public class PawnPO extends PatternObject<PawnPO, Pawn>
{

    public PawnSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PawnSet matches = new PawnSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Pawn) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public PawnPO hasColor(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pawn.PROPERTY_COLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PawnPO hasColor(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pawn.PROPERTY_COLOR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PawnPO createColor(String value)
   {
      this.startCreate().hasColor(value).endCreate();
      return this;
   }
   
   public String getColor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pawn) getCurrentMatch()).getColor();
      }
      return null;
   }
   
   public PawnPO withColor(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pawn) getCurrentMatch()).setColor(value);
      }
      return this;
   }
   
   public PawnPO hasX(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pawn.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PawnPO hasX(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pawn.PROPERTY_X)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PawnPO createX(int value)
   {
      this.startCreate().hasX(value).endCreate();
      return this;
   }
   
   public int getX()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pawn) getCurrentMatch()).getX();
      }
      return 0;
   }
   
   public PawnPO withX(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pawn) getCurrentMatch()).setX(value);
      }
      return this;
   }
   
   public PawnPO hasY(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pawn.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PawnPO hasY(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pawn.PROPERTY_Y)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PawnPO createY(int value)
   {
      this.startCreate().hasY(value).endCreate();
      return this;
   }
   
   public int getY()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pawn) getCurrentMatch()).getY();
      }
      return 0;
   }
   
   public PawnPO withY(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pawn) getCurrentMatch()).setY(value);
      }
      return this;
   }
   
}

