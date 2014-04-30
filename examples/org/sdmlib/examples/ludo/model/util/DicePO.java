package org.sdmlib.examples.ludo.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.ludo.model.Dice;
import org.sdmlib.examples.ludo.model.util.DiceSet;
import org.sdmlib.models.pattern.AttributeConstraint;

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
   
   public DicePO hasValue(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Dice.PROPERTY_VALUE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public DicePO createValue(int value)
   {
      this.startCreate().hasValue(value).endCreate();
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
   
   public DicePO withValue(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Dice) getCurrentMatch()).setValue(value);
      }
      return this;
   }
   
}

