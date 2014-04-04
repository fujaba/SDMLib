package org.sdmlib.models.classes.creators;

import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.classes.creators.AttributeSet;
import org.sdmlib.models.classes.creators.ClazzPO;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.classes.creators.AttributePO;

public class AttributePO extends PatternObject<AttributePO, Attribute>
{
   public AttributePO startNAC()
   {
      return (AttributePO) super.startNAC();
   }

   public AttributePO endNAC()
   {
      return (AttributePO) super.endNAC();
   }

   public AttributeSet allMatches()
   {
      AttributeSet matches = new AttributeSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Attribute) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }

   public AttributePO hasInitialization(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Attribute.PROPERTY_INITIALIZATION).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public String getInitialization()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Attribute) getCurrentMatch()).getInitialization();
      }
      return null;
   }

   public ClazzPO hasClazz()
   {
      ClazzPO result = new ClazzPO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(Attribute.PROPERTY_CLAZZ, result);

      return result;
   }

   public AttributePO hasClazz(ClazzPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(Attribute.PROPERTY_CLAZZ).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public Clazz getClazz()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Attribute) this.getCurrentMatch()).getClazz();
      }
      return null;
   }

   public AttributePO hasType(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Attribute.PROPERTY_TYPE).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public String getType()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Attribute) getCurrentMatch()).getType();
      }
      return null;
   }

   public AttributePO withType(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Attribute) getCurrentMatch()).setType(value);
      }
      return this;
   }

   public AttributePO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Attribute.PROPERTY_NAME).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Attribute) getCurrentMatch()).getName();
      }
      return null;
   }

   public AttributePO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Attribute) getCurrentMatch()).setName(value);
      }
      return this;
   }

   public AttributePO hasName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Attribute.PROPERTY_NAME).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public AttributePO hasType(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Attribute.PROPERTY_TYPE).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public AttributePO hasInitialization(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Attribute.PROPERTY_INITIALIZATION).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public AttributePO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }

   public AttributePO createType(String value)
   {
      this.startCreate().hasType(value).endCreate();
      return this;
   }

   public AttributePO createInitialization(String value)
   {
      this.startCreate().hasInitialization(value).endCreate();
      return this;
   }

   public ClazzPO createClazz()
   {
      return this.startCreate().hasClazz().endCreate();
   }

   public AttributePO createClazz(ClazzPO tgt)
   {
      return this.startCreate().hasClazz(tgt).endCreate();
   }

}
