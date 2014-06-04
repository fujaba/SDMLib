package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Parameter;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.classes.util.ParameterSet;
import org.sdmlib.models.classes.util.MethodPO;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.util.ParameterPO;

public class ParameterPO extends PatternObject<ParameterPO, Parameter>
{
   public ParameterPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ParameterPO(Parameter... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }

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
   
   public ParameterSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ParameterSet matches = new ParameterSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Parameter) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public ParameterPO hasInitialization(String value)
   {
      new AttributeConstraint()
      .withAttrName(Parameter.PROPERTY_INITIALIZATION)
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
         return ((Parameter) getCurrentMatch()).getInitialization();
      }
      return null;
   }
   
   public ParameterPO hasType(String value)
   {
      new AttributeConstraint()
      .withAttrName(Parameter.PROPERTY_TYPE)
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
         return ((Parameter) getCurrentMatch()).getType();
      }
      return null;
   }
   
   public ParameterPO withType(DataType value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Parameter) getCurrentMatch()).setType(value);
      }
      return this;
   }
   
   public ParameterPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Parameter.PROPERTY_NAME)
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
         return ((Parameter) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public ParameterPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Parameter) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public ParameterPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Parameter.PROPERTY_NAME)
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
      .withAttrName(Parameter.PROPERTY_TYPE)
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
      .withAttrName(Parameter.PROPERTY_INITIALIZATION)
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
   public ParameterPO hasType(DataType value)
   {
      new AttributeConstraint()
      .withAttrName(Parameter.PROPERTY_TYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ParameterPO createType(DataType value)
   {
      this.startCreate().hasType(value).endCreate();
      return this;
   }
   
   public MethodPO hasMethod()
   {
      MethodPO result = new MethodPO(new Method[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Parameter.PROPERTY_METHOD, result);
      
      return result;
   }

   public MethodPO createMethod()
   {
      return this.startCreate().hasMethod().endCreate();
   }

   public ParameterPO hasMethod(MethodPO tgt)
   {
      return hasLinkConstraint(tgt, Parameter.PROPERTY_METHOD);
   }

   public ParameterPO createMethod(MethodPO tgt)
   {
      return this.startCreate().hasMethod(tgt).endCreate();
   }

   public Method getMethod()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Parameter) this.getCurrentMatch()).getMethod();
      }
      return null;
   }

}
