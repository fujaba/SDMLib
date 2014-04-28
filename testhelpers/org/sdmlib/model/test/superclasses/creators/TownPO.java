package org.sdmlib.model.test.superclasses.creators;

import org.sdmlib.model.test.superclasses.Town;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.model.test.superclasses.creators.TownSet;

public class TownPO extends PatternObject<TownPO, Town>
{
   public TownPO hasTest(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Town.PROPERTY_TEST).withTgtValue(value).withSrc(this)
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

   public String getTest()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Town) getCurrentMatch()).getTest();
      }
      return null;
   }

   public TownPO hasTest(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Town.PROPERTY_TEST).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public TownPO createTest(String value)
   {
      this.startCreate().hasTest(value).endCreate();
      return this;
   }

}
