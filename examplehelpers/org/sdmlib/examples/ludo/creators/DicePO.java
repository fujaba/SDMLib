package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.Dice;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

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

}
