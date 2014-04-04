package org.sdmlib.model.classes.test.creators;

import org.sdmlib.model.classes.test.Kid;
import org.sdmlib.model.classes.test.Parent;
import org.sdmlib.model.classes.test.Uncle;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.model.classes.test.creators.KidSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.model.classes.test.creators.UnclePO;
import org.sdmlib.model.classes.test.creators.KidPO;

public class KidPO extends PatternObject<KidPO, Kid>
{
   public KidSet allMatches()
   {
      this.setDoAllMatches(true);

      KidSet matches = new KidSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Kid) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }

   public KidPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Kid.PROPERTY_NAME).withTgtValue(value).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Kid) getCurrentMatch()).getName();
      }
      return null;
   }

   public KidPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Kid) getCurrentMatch()).setName(value);
      }
      return this;
   }

   public UnclePO hasUncle()
   {
      UnclePO result = new UnclePO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(Parent.PROPERTY_UNCLE, result);

      return result;
   }

   public KidPO hasUncle(UnclePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(Parent.PROPERTY_UNCLE).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public Uncle getUncle()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Parent) this.getCurrentMatch()).getUncle();
      }
      return null;
   }

   public KidPO hasName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Kid.PROPERTY_NAME).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public KidPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }

   public UnclePO createUncle()
   {
      return this.startCreate().hasUncle().endCreate();
   }

   public KidPO createUncle(UnclePO tgt)
   {
      return this.startCreate().hasUncle(tgt).endCreate();
   }

}
