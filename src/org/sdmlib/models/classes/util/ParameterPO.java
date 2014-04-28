package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class ParameterPO extends PatternObject<ParameterPO, Attribute>
{
   @Override
   public ParameterPO startNAC()
   {
      return (ParameterPO) super.startNAC();
   }
   
   @Override
   public ParameterPO endNAC()
   {
      return (ParameterPO) super.endNAC();
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
   
   public ParameterPO hasInitialization(String value)
   {
      new AttributeConstraint()
      .withAttrName(Attribute.PROPERTY_INITIALIZATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
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
   
   public ParameterPO hasClazz(ClazzPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Attribute.PROPERTY_CLAZZ)
      .withSrc(this)
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
   
   public ParameterPO hasType(String value)
   {
      new AttributeConstraint()
      .withAttrName(Attribute.PROPERTY_TYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public DataType getType()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Attribute) getCurrentMatch()).getType();
      }
      return null;
   }
   
   public ParameterPO withType(DataType value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Attribute) getCurrentMatch()).setType(value);
      }
      return this;
   }
   
   public ParameterPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Attribute.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
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
   
   public ParameterPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Attribute) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public ParameterPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Attribute.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ParameterPO hasType(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Attribute.PROPERTY_TYPE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ParameterPO hasInitialization(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Attribute.PROPERTY_INITIALIZATION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ParameterPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public ParameterPO createType(String value)
   {
      this.startCreate().hasType(value).endCreate();
      return this;
   }
   
   public ParameterPO createInitialization(String value)
   {
      this.startCreate().hasInitialization(value).endCreate();
      return this;
   }
   
   public ClazzPO createClazz()
   {
      return this.startCreate().hasClazz().endCreate();
   }

   public ParameterPO createClazz(ClazzPO tgt)
   {
      return this.startCreate().hasClazz(tgt).endCreate();
   }

}
