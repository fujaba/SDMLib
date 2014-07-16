package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Parameter;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class MethodPO extends PatternObject<MethodPO, Method>
{
   public MethodPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MethodPO(Method... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   @Override
   public MethodPO startNAC()
   {
      return (MethodPO) super.startNAC();
   }
   
   @Override
   public MethodPO endNAC()
   {
      return (MethodPO) super.endNAC();
   }
   
   public MethodSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MethodSet matches = new MethodSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Method) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public MethodPO hasSignature(String value)
   {
      new AttributeConstraint()
      .withAttrName(Method.PROPERTY_PARAMETER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ParameterSet getSignature()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Method) getCurrentMatch()).getParameter();
      }
      return null;
   }
   
   public MethodPO hasReturnType(String value)
   {
      new AttributeConstraint()
      .withAttrName(Method.PROPERTY_RETURNTYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public DataType getReturnType()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Method) getCurrentMatch()).getReturnType();
      }
      return null;
   }
   
   public ClazzPO hasClazz()
   {
      ClazzPO result = new ClazzPO(new Clazz[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Method.PROPERTY_CLAZZ, result);
      
      return result;
   }
   
   public MethodPO hasClazz(ClazzPO tgt)
   {
      return hasLinkConstraint(tgt, Method.PROPERTY_CLAZZ);
   }
   
   public Clazz getClazz()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Method) this.getCurrentMatch()).getClazz();
      }
      return null;
   }
   
   public MethodPO hasBody(String value)
   {
      new AttributeConstraint()
      .withAttrName(Method.PROPERTY_BODY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getBody()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Method) getCurrentMatch()).getBody();
      }
      return null;
   }
   
   public MethodPO withBody(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Method) getCurrentMatch()).setBody(value);
      }
      return this;
   }
   
   public MethodPO hasSignature(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Method.PROPERTY_PARAMETER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MethodPO hasReturnType(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Method.PROPERTY_RETURNTYPE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MethodPO hasBody(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Method.PROPERTY_BODY)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MethodPO createSignature(String value)
   {
      this.startCreate().hasSignature(value).endCreate();
      return this;
   }
   
   public MethodPO createReturnType(String value)
   {
      this.startCreate().hasReturnType(value).endCreate();
      return this;
   }
   
   public MethodPO createBody(String value)
   {
      this.startCreate().hasBody(value).endCreate();
      return this;
   }
   
   public ClazzPO createClazz()
   {
      return this.startCreate().hasClazz().endCreate();
   }

   public MethodPO createClazz(ClazzPO tgt)
   {
      return this.startCreate().hasClazz(tgt).endCreate();
   }

   public MethodPO hasReturnType(DataType value)
   {
      new AttributeConstraint()
      .withAttrName(Method.PROPERTY_RETURNTYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MethodPO createReturnType(DataType value)
   {
      this.startCreate().hasReturnType(value).endCreate();
      return this;
   }
   
   public MethodPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Method.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MethodPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Method.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MethodPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Method) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public MethodPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Method) getCurrentMatch()).withName(value);
      }
      return this;
   }
   
   public ParameterPO hasParameter()
   {
      ParameterPO result = new ParameterPO(new Parameter[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Method.PROPERTY_PARAMETER, result);
      
      return result;
   }

   public ParameterPO createParameter()
   {
      return this.startCreate().hasParameter().endCreate();
   }

   public MethodPO hasParameter(ParameterPO tgt)
   {
      return hasLinkConstraint(tgt, Method.PROPERTY_PARAMETER);
   }

   public MethodPO createParameter(ParameterPO tgt)
   {
      return this.startCreate().hasParameter(tgt).endCreate();
   }

   public ParameterSet getParameter()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Method) this.getCurrentMatch()).getParameter();
      }
      return null;
   }

}




