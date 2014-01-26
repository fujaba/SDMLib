package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.transformations.LinkOp;
import org.sdmlib.models.transformations.creators.LinkOpSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.transformations.creators.OperationObjectPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.transformations.creators.LinkOpPO;
import org.sdmlib.models.transformations.OperationObject;
import org.sdmlib.models.transformations.creators.TransformOpPO;
import org.sdmlib.models.transformations.TransformOp;

public class LinkOpPO extends PatternObject<LinkOpPO, LinkOp>
{
   public LinkOpSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LinkOpSet matches = new LinkOpSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((LinkOp) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public LinkOpPO hasSrcText(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(LinkOp.PROPERTY_SRCTEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getSrcText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LinkOp) getCurrentMatch()).getSrcText();
      }
      return null;
   }
   
   public LinkOpPO withSrcText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LinkOp) getCurrentMatch()).setSrcText(value);
      }
      return this;
   }
   
   public LinkOpPO hasTgtText(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(LinkOp.PROPERTY_TGTTEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getTgtText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LinkOp) getCurrentMatch()).getTgtText();
      }
      return null;
   }
   
   public LinkOpPO withTgtText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LinkOp) getCurrentMatch()).setTgtText(value);
      }
      return this;
   }
   
   public OperationObjectPO hasSrc()
   {
      OperationObjectPO result = new OperationObjectPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(LinkOp.PROPERTY_SRC, result);
      
      return result;
   }

   public LinkOpPO hasSrc(OperationObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(LinkOp.PROPERTY_SRC)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public OperationObject getSrc()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LinkOp) this.getCurrentMatch()).getSrc();
      }
      return null;
   }

   public OperationObjectPO hasTgt()
   {
      OperationObjectPO result = new OperationObjectPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(LinkOp.PROPERTY_TGT, result);
      
      return result;
   }

   public LinkOpPO hasTgt(OperationObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(LinkOp.PROPERTY_TGT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public OperationObject getTgt()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LinkOp) this.getCurrentMatch()).getTgt();
      }
      return null;
   }

   public TransformOpPO hasTransformOp()
   {
      TransformOpPO result = new TransformOpPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(LinkOp.PROPERTY_TRANSFORMOP, result);
      
      return result;
   }

   public LinkOpPO hasTransformOp(TransformOpPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(LinkOp.PROPERTY_TRANSFORMOP)
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
         return ((LinkOp) this.getCurrentMatch()).getTransformOp();
      }
      return null;
   }

   public LinkOpPO hasSrcText(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(LinkOp.PROPERTY_SRCTEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LinkOpPO hasTgtText(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(LinkOp.PROPERTY_TGTTEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
}


