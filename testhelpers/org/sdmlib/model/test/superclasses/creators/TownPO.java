package org.sdmlib.model.test.superclasses.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.model.test.superclasses.Town;
import org.sdmlib.models.pattern.AttributeConstraint;

public class TownPO extends PatternObject
{
   public TownPO hasTest(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Town.PROPERTY_TEST)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public TownPO withTest(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Town) getCurrentMatch()).withTest(value);
      }
      return this;
   }
   
}

