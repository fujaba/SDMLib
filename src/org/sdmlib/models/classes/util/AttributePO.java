package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class AttributePO extends PatternObject<AttributePO, Attribute>
{
   public AttributePO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public AttributePO(Attribute... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   @Override
   public AttributePO startNAC()
   {
      return (AttributePO) super.startNAC();
   }
   
   @Override
   public AttributePO endNAC()
   {
      return (AttributePO) super.endNAC();
   }
   
   public AttributeSet allMatches()
   {
      this.setDoAllMatches(true);
      
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
      ClazzPO result = new ClazzPO(new Clazz[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Attribute.PROPERTY_CLAZZ, result);
      
      return result;
   }
   
   public AttributePO hasClazz(ClazzPO tgt)
   {
      return hasLinkConstraint(tgt, Attribute.PROPERTY_CLAZZ);
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
   
   public AttributePO withType(DataType value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Attribute) getCurrentMatch()).setType(value);
      }
      return this;
   }
   
   public AttributePO hasName(String value)
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
   
   public AttributePO hasType(String lower, String upper)
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
   
   public AttributePO hasInitialization(String lower, String upper)
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
