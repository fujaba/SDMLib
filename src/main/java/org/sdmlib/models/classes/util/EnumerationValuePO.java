package org.sdmlib.models.classes.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.classes.EnumerationValue;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.classes.util.EnumerationPO;
import org.sdmlib.models.classes.Enumeration;
import org.sdmlib.models.classes.util.EnumerationValuePO;

public class EnumerationValuePO extends PatternObject<EnumerationValuePO, EnumerationValue>
{

    public EnumerationValueSet allMatches()
   {
      this.setDoAllMatches(true);
      
      EnumerationValueSet matches = new EnumerationValueSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((EnumerationValue) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public EnumerationValuePO(){
      newInstance(org.sdmlib.models.classes.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public EnumerationValuePO(EnumerationValue... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.models.classes.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public EnumerationValuePO hasValueName(String value)
   {
      new AttributeConstraint()
      .withAttrName(EnumerationValue.PROPERTY_VALUENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public EnumerationValuePO hasValueName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(EnumerationValue.PROPERTY_VALUENAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public EnumerationValuePO createValueName(String value)
   {
      this.startCreate().hasValueName(value).endCreate();
      return this;
   }
   
   public String getValueName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((EnumerationValue) getCurrentMatch()).getValueName();
      }
      return null;
   }
   
   public EnumerationValuePO withValueName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((EnumerationValue) getCurrentMatch()).setValueName(value);
      }
      return this;
   }
   
   public EnumerationValuePO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(EnumerationValue.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public EnumerationValuePO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(EnumerationValue.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public EnumerationValuePO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((EnumerationValue) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public EnumerationValuePO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((EnumerationValue) getCurrentMatch()).withName(value);
      }
      return this;
   }
   
   public EnumerationPO hasEnumeration()
   {
      EnumerationPO result = new EnumerationPO(new Enumeration[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(EnumerationValue.PROPERTY_ENUMERATION, result);
      
      return result;
   }

   public EnumerationPO createEnumeration()
   {
      return this.startCreate().hasEnumeration().endCreate();
   }

   public EnumerationValuePO hasEnumeration(EnumerationPO tgt)
   {
      return hasLinkConstraint(tgt, EnumerationValue.PROPERTY_ENUMERATION);
   }

   public EnumerationValuePO createEnumeration(EnumerationPO tgt)
   {
      return this.startCreate().hasEnumeration(tgt).endCreate();
   }

   public Enumeration getEnumeration()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((EnumerationValue) this.getCurrentMatch()).getEnumeration();
      }
      return null;
   }

}
