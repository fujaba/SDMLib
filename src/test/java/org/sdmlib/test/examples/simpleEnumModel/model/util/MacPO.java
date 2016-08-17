package org.sdmlib.test.examples.simpleEnumModel.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.simpleEnumModel.model.Mac;
import org.sdmlib.test.examples.simpleEnumModel.model.TEnum;
import org.sdmlib.test.examples.simpleEnumModel.model.Alex;
import org.sdmlib.models.pattern.Pattern;

public class MacPO extends PatternObject<MacPO, Mac>
{

    public MacSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MacSet matches = new MacSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Mac) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MacPO(){
      newInstance(org.sdmlib.test.examples.simpleEnumModel.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MacPO(Mac... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.simpleEnumModel.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public MacPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Mac.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MacPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Mac.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MacPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Mac) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public MacPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Mac) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public MacPO hasType(TEnum value)
   {
      new AttributeConstraint()
      .withAttrName(Mac.PROPERTY_TYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MacPO createType(TEnum value)
   {
      this.startCreate().hasType(value).endCreate();
      return this;
   }
   
   public TEnum getType()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Mac) getCurrentMatch()).getType();
      }
      return null;
   }
   
   public MacPO withType(TEnum value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Mac) getCurrentMatch()).setType(value);
      }
      return this;
   }
   
   public MacPO hasOwner(Alex value)
   {
      new AttributeConstraint()
      .withAttrName(Mac.PROPERTY_OWNER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MacPO createOwner(Alex value)
   {
      this.startCreate().hasOwner(value).endCreate();
      return this;
   }
   
   public Alex getOwner()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Mac) getCurrentMatch()).getOwner();
      }
      return null;
   }
   
   public MacPO withOwner(Alex value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Mac) getCurrentMatch()).setOwner(value);
      }
      return this;
   }
   
   
   //==========================================================================
   
   public String concat(int p1)
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Mac) getCurrentMatch()).concat(p1);
      }
      return null;
   }

   
   //==========================================================================
   
   public org.sdmlib.test.examples.simpleEnumModel.model.TEnum select(int p1)
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Mac) getCurrentMatch()).select(p1);
      }
      return null;
   }

   public MacPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Mac.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MacPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Mac.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MacPO filterType(TEnum value)
   {
      new AttributeConstraint()
      .withAttrName(Mac.PROPERTY_TYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MacPO filterOwner(Alex value)
   {
      new AttributeConstraint()
      .withAttrName(Mac.PROPERTY_OWNER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   

   public MacPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public MacPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Mac.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MacPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Mac.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MacPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Mac.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MacPO createTypeCondition(TEnum value)
   {
      new AttributeConstraint()
      .withAttrName(Mac.PROPERTY_TYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MacPO createTypeAssignment(TEnum value)
   {
      new AttributeConstraint()
      .withAttrName(Mac.PROPERTY_TYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MacPO createOwnerCondition(Alex value)
   {
      new AttributeConstraint()
      .withAttrName(Mac.PROPERTY_OWNER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MacPO createOwnerAssignment(Alex value)
   {
      new AttributeConstraint()
      .withAttrName(Mac.PROPERTY_OWNER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
}
