package org.sdmlib.model.classes.test.creators;

import org.sdmlib.model.classes.test.Parent;
import org.sdmlib.model.classes.test.Uncle;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class ParentPO extends PatternObject<ParentPO, Parent>
{
   public ParentSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ParentSet matches = new ParentSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Parent) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public ParentPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Parent.PROPERTY_NAME)
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
         return ((Parent) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public ParentPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Parent) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public UnclePO hasUncle()
   {
      UnclePO result = new UnclePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Parent.PROPERTY_UNCLE, result);
      
      return result;
   }
   
   public ParentPO hasUncle(UnclePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Parent.PROPERTY_UNCLE)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Uncle getUncle()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Parent) this.getCurrentMatch()).getUncle();
      }
      return null;
   }
   
}

