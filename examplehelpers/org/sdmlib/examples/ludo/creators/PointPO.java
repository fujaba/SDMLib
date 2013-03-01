package org.sdmlib.examples.ludo.creators;

import java.awt.Point;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

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
   
   public int getX()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Point) getCurrentMatch()).x;
      }
      return 0;
   }
   
   public int getY()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Point) getCurrentMatch()).y;
      }
      return 0;
   }
   
   public PointPO hasX(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PointCreator.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PointPO hasY(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PointCreator.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
}



