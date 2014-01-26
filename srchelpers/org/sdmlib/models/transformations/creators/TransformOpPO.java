package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.transformations.TransformOp;
import org.sdmlib.models.transformations.creators.TransformOpSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.transformations.creators.OperationObjectPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.transformations.creators.TransformOpPO;
import org.sdmlib.models.transformations.OperationObject;
import org.sdmlib.models.transformations.creators.OperationObjectSet;
import org.sdmlib.models.transformations.creators.LinkOpPO;
import org.sdmlib.models.transformations.LinkOp;
import org.sdmlib.models.transformations.creators.LinkOpSet;
import org.sdmlib.models.transformations.creators.StatementPO;
import org.sdmlib.models.transformations.Statement;
import org.sdmlib.models.transformations.creators.StatementSet;

public class TransformOpPO extends PatternObject<TransformOpPO, TransformOp>
{
   public TransformOpSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TransformOpSet matches = new TransformOpSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((TransformOp) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public TransformOpPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(TransformOp.PROPERTY_NAME)
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
         return ((TransformOp) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public TransformOpPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TransformOp) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public OperationObjectPO hasOpObjects()
   {
      OperationObjectPO result = new OperationObjectPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(TransformOp.PROPERTY_OPOBJECTS, result);
      
      return result;
   }

   public TransformOpPO hasOpObjects(OperationObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(TransformOp.PROPERTY_OPOBJECTS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public OperationObjectSet getOpObjects()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TransformOp) this.getCurrentMatch()).getOpObjects();
      }
      return null;
   }

   public LinkOpPO hasLinkOps()
   {
      LinkOpPO result = new LinkOpPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(TransformOp.PROPERTY_LINKOPS, result);
      
      return result;
   }

   public TransformOpPO hasLinkOps(LinkOpPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(TransformOp.PROPERTY_LINKOPS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public LinkOpSet getLinkOps()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TransformOp) this.getCurrentMatch()).getLinkOps();
      }
      return null;
   }

   public StatementPO hasStatements()
   {
      StatementPO result = new StatementPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(TransformOp.PROPERTY_STATEMENTS, result);
      
      return result;
   }

   public TransformOpPO hasStatements(StatementPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(TransformOp.PROPERTY_STATEMENTS)
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
         return ((TransformOp) this.getCurrentMatch()).getStatements();
      }
      return null;
   }

   public TransformOpPO hasName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(TransformOp.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
}


