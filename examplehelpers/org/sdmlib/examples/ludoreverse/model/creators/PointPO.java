package org.sdmlib.examples.ludoreverse.model.creators;

import java.awt.Point;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.ludoreverse.model.creators.PointSet;

public class PointPO extends PatternObject<PointPO, Point>
{
   public PointSet allMatches()
   {
      this.setDoAllMatches(true);

      PointSet matches = new PointSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Point) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }

   public PointPO hasX(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(PointCreator.PROPERTY_X).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public int getX()
   {
      if (this.getPattern().getHasMatch())
      {
         return (int) ((Point) getCurrentMatch()).getX();
      }
      return 0;
   }

   public PointPO withX(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Point) getCurrentMatch()).x = (value);
      }
      return this;
   }

   public PointPO hasY(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(PointCreator.PROPERTY_Y).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public int getY()
   {
      if (this.getPattern().getHasMatch())
      {
         return (int) ((Point) getCurrentMatch()).getY();
      }
      return 0;
   }

   public PointPO withY(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Point) getCurrentMatch()).y = (value);
      }
      return this;
   }

   public PointPO hasX(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(PointCreator.PROPERTY_X).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public PointPO createX(int value)
   {
      this.startCreate().hasX(value).endCreate();
      return this;
   }

   public PointPO hasY(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(PointCreator.PROPERTY_Y).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public PointPO createY(int value)
   {
      this.startCreate().hasY(value).endCreate();
      return this;
   }

}
