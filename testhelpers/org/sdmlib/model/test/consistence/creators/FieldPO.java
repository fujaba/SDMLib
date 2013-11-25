package org.sdmlib.model.test.consistence.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.model.test.consistence.Field;
import org.sdmlib.model.test.consistence.creators.FieldSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.model.test.consistence.creators.BorderPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.model.test.consistence.creators.FieldPO;
import org.sdmlib.model.test.consistence.Border;

public class FieldPO extends PatternObject<FieldPO, Field>
{
   public FieldSet allMatches()
   {
      this.setDoAllMatches(true);
      
      FieldSet matches = new FieldSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Field) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public BorderPO hasBorder()
   {
      BorderPO result = new BorderPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Field.PROPERTY_BORDER, result);
      
      return result;
   }

   public FieldPO hasBorder(BorderPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Field.PROPERTY_BORDER)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Border getBorder()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getBorder();
      }
      return null;
   }

}

