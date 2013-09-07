package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.transformations.Statement;
import org.sdmlib.models.transformations.creators.StatementSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.transformations.creators.StatementPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.transformations.creators.OperationObjectPO;
import org.sdmlib.models.transformations.OperationObject;
import org.sdmlib.models.transformations.creators.OperationObjectSet;
import org.sdmlib.models.transformations.creators.TransformOpPO;
import org.sdmlib.models.transformations.TransformOp;

public class StatementPO extends PatternObject<StatementPO, Statement>
{
   public StatementSet allMatches()
   {
      this.setDoAllMatches(true);
      
      StatementSet matches = new StatementSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Statement) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public StatementPO hasText(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Statement.PROPERTY_TEXT)
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
         return ((Statement) getCurrentMatch()).getText();
      }
      return null;
   }
   
   public StatementPO withText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Statement) getCurrentMatch()).setText(value);
      }
      return this;
   }
   
   public StatementPO hasNext()
   {
      StatementPO result = new StatementPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Statement.PROPERTY_NEXT, result);
      
      return result;
   }

   public StatementPO hasNext(StatementPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Statement.PROPERTY_NEXT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Statement getNext()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Statement) this.getCurrentMatch()).getNext();
      }
      return null;
   }

   public StatementPO hasPrev()
   {
      StatementPO result = new StatementPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Statement.PROPERTY_PREV, result);
      
      return result;
   }

   public StatementPO hasPrev(StatementPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Statement.PROPERTY_PREV)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Statement getPrev()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Statement) this.getCurrentMatch()).getPrev();
      }
      return null;
   }

   public OperationObjectPO hasOperationObjects()
   {
      OperationObjectPO result = new OperationObjectPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Statement.PROPERTY_OPERATIONOBJECTS, result);
      
      return result;
   }

   public StatementPO hasOperationObjects(OperationObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Statement.PROPERTY_OPERATIONOBJECTS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public OperationObjectSet getOperationObjects()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Statement) this.getCurrentMatch()).getOperationObjects();
      }
      return null;
   }

   public TransformOpPO hasTransformOp()
   {
      TransformOpPO result = new TransformOpPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Statement.PROPERTY_TRANSFORMOP, result);
      
      return result;
   }

   public StatementPO hasTransformOp(TransformOpPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Statement.PROPERTY_TRANSFORMOP)
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
         return ((Statement) this.getCurrentMatch()).getTransformOp();
      }
      return null;
   }

}

