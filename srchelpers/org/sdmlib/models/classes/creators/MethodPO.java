package org.sdmlib.models.classes.creators;

import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class MethodPO extends PatternObject
{
   public MethodPO startNAC()
   {
      return (MethodPO) super.startNAC();
   }
   
   public MethodPO endNAC()
   {
      return (MethodPO) super.endNAC();
   }
   
   public MethodSet allMatches()
   {
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Method.PROPERTY_SIGNATURE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getSignature()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Method) getCurrentMatch()).getSignature();
      }
      return null;
   }
   
   public MethodPO hasReturnType(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Method.PROPERTY_RETURNTYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getReturnType()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Method) getCurrentMatch()).getReturnType();
      }
      return null;
   }
   
   public ClazzPO hasClazz()
   {
      ClazzPO result = new ClazzPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Method.PROPERTY_CLAZZ, result);
      
      return result;
   }
   
   public MethodPO hasClazz(ClazzPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Method.PROPERTY_CLAZZ)
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
         return ((Method) this.getCurrentMatch()).getClazz();
      }
      return null;
   }
   
}

