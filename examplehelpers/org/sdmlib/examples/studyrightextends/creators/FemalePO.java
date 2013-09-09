package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyrightextends.Female;
import org.sdmlib.examples.studyrightextends.creators.FemaleSet;
import org.sdmlib.models.pattern.AttributeConstraint;

public class FemalePO extends PatternObject<FemalePO, Female>
{
   public FemaleSet allMatches()
   {
      this.setDoAllMatches(true);
      
      FemaleSet matches = new FemaleSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Female) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   
   //==========================================================================
   
   public void findMyPosition()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Female) getCurrentMatch()).findMyPosition();
      }
   }

   
   //==========================================================================
   
   public void findMyPosition(String p0)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Female) getCurrentMatch()).findMyPosition( p0);
      }
   }

   
   //==========================================================================
   
   public void findMyPosition(String p0, int p1)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Female) getCurrentMatch()).findMyPosition( p0,  p1);
      }
   }

   public FemalePO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Female.PROPERTY_NAME)
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
         return ((Female) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public FemalePO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Female) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
}

