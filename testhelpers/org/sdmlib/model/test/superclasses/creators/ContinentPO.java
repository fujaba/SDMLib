package org.sdmlib.model.test.superclasses.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.model.test.superclasses.Continent;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.model.test.superclasses.creators.ContinentSet;

public class ContinentPO extends PatternObject
{
   public ContinentPO hasTest(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Continent.PROPERTY_TEST)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ContinentPO withTest(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Continent) getCurrentMatch()).withTest(value);
      }
      return this;
   }
   
   public String getTest()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Continent) getCurrentMatch()).getTest();
      }
      return null;
   }
   
}


