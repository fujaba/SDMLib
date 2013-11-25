package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.transformations.AttributeOp;
import org.sdmlib.models.transformations.creators.AttributeOpSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.transformations.creators.OperationObjectPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.transformations.creators.AttributeOpPO;
import org.sdmlib.models.transformations.OperationObject;

public class AttributeOpPO extends PatternObject<AttributeOpPO, AttributeOp>
{
   public AttributeOpSet allMatches()
   {
      this.setDoAllMatches(true);
      
      AttributeOpSet matches = new AttributeOpSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((AttributeOp) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public AttributeOpPO hasText(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(AttributeOp.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((AttributeOp) getCurrentMatch()).getText();
      }
      return null;
   }
   
   public AttributeOpPO withText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((AttributeOp) getCurrentMatch()).setText(value);
      }
      return this;
   }
   
   public OperationObjectPO hasOperationObject()
   {
      OperationObjectPO result = new OperationObjectPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(AttributeOp.PROPERTY_OPERATIONOBJECT, result);
      
      return result;
   }

   public AttributeOpPO hasOperationObject(OperationObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(AttributeOp.PROPERTY_OPERATIONOBJECT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public OperationObject getOperationObject()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((AttributeOp) this.getCurrentMatch()).getOperationObject();
      }
      return null;
   }

}

