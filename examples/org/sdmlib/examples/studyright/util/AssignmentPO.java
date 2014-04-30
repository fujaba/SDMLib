package org.sdmlib.examples.studyright.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyright.Assignment;
import org.sdmlib.examples.studyright.util.AssignmentSet;
import org.sdmlib.models.pattern.AttributeConstraint;

public class AssignmentPO extends PatternObject<AssignmentPO, Assignment>
{

    public AssignmentSet allMatches()
   {
      this.setDoAllMatches(true);
      
      AssignmentSet matches = new AssignmentSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Assignment) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public AssignmentPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AssignmentPO hasName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AssignmentPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Assignment) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public AssignmentPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Assignment) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public AssignmentPO hasPoints(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_POINTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AssignmentPO hasPoints(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Assignment.PROPERTY_POINTS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AssignmentPO createPoints(int value)
   {
      this.startCreate().hasPoints(value).endCreate();
      return this;
   }
   
   public int getPoints()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Assignment) getCurrentMatch()).getPoints();
      }
      return 0;
   }
   
   public AssignmentPO withPoints(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Assignment) getCurrentMatch()).setPoints(value);
      }
      return this;
   }
   
}

