package org.sdmlib.models.classes.creators;

import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class AttributePO extends PatternObject
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
   
   public AttributePO hasClazz(ClazzPO tgt)
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
   
}

