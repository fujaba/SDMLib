package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.transformations.OperationObject;
import org.sdmlib.models.transformations.TransformOp;

public class OperationObjectPO extends PatternObject
{
   public OperationObjectPO startNAC()
   {
      return (OperationObjectPO) super.startNAC();
   }
   
   public OperationObjectPO endNAC()
   {
      return (OperationObjectPO) super.endNAC();
   }
   
   public OperationObjectSet allMatches()
   {
      OperationObjectSet matches = new OperationObjectSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((OperationObject) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public OperationObjectPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(OperationObject.PROPERTY_NAME)
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
         return ((OperationObject) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public OperationObjectPO hasType(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(OperationObject.PROPERTY_TYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getType()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((OperationObject) getCurrentMatch()).getType();
      }
      return null;
   }
   
   public OperationObjectPO hasSet(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(OperationObject.PROPERTY_SET)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public boolean getSet()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((OperationObject) getCurrentMatch()).getSet();
      }
      return false;
   }
   
   public TransformOpPO hasTransformOp()
   {
      TransformOpPO result = new TransformOpPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(OperationObject.PROPERTY_TRANSFORMOP, result);
      
      return result;
   }
   
   public OperationObjectPO hasTransformOp(TransformOpPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(OperationObject.PROPERTY_TRANSFORMOP)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public TransformOp getTransformOp()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((OperationObject) this.getCurrentMatch()).getTransformOp();
      }
      return null;
   }
   
   public AttributeOpPO hasAttributeOps()
   {
      AttributeOpPO result = new AttributeOpPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(OperationObject.PROPERTY_ATTRIBUTEOPS, result);
      
      return result;
   }
   
   public OperationObjectPO hasAttributeOps(AttributeOpPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(OperationObject.PROPERTY_ATTRIBUTEOPS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AttributeOpSet getAttributeOps()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((OperationObject) this.getCurrentMatch()).getAttributeOps();
      }
      return null;
   }
   
   public LinkOpPO hasOutgoings()
   {
      LinkOpPO result = new LinkOpPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(OperationObject.PROPERTY_OUTGOINGS, result);
      
      return result;
   }
   
   public OperationObjectPO hasOutgoings(LinkOpPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(OperationObject.PROPERTY_OUTGOINGS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LinkOpSet getOutgoings()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((OperationObject) this.getCurrentMatch()).getOutgoings();
      }
      return null;
   }
   
   public LinkOpPO hasIncomings()
   {
      LinkOpPO result = new LinkOpPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(OperationObject.PROPERTY_INCOMINGS, result);
      
      return result;
   }
   
   public OperationObjectPO hasIncomings(LinkOpPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(OperationObject.PROPERTY_INCOMINGS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LinkOpSet getIncomings()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((OperationObject) this.getCurrentMatch()).getIncomings();
      }
      return null;
   }
   
   public StatementPO hasStatements()
   {
      StatementPO result = new StatementPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(OperationObject.PROPERTY_STATEMENTS, result);
      
      return result;
   }
   
   public OperationObjectPO hasStatements(StatementPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(OperationObject.PROPERTY_STATEMENTS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public StatementSet getStatements()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((OperationObject) this.getCurrentMatch()).getStatements();
      }
      return null;
   }
   
}

