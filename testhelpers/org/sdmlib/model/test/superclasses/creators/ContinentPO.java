package org.sdmlib.model.test.superclasses.creators;

import org.sdmlib.model.test.superclasses.Continent;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.model.test.superclasses.creators.ContinentSet;

public class ContinentPO extends PatternObject<ContinentPO, Continent>
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
   
   public ContinentPO hasTest(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Continent.PROPERTY_TEST)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ContinentPO createTest(String value)
   {
      this.startCreate().hasTest(value).endCreate();
      return this;
   }
   
}



