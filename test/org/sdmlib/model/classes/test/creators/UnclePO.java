package org.sdmlib.model.classes.test.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.model.classes.test.Uncle;
import org.sdmlib.model.classes.test.creators.UncleSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.model.classes.test.creators.ParentPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.model.classes.test.creators.UnclePO;
import org.sdmlib.model.classes.test.Parent;

public class UnclePO extends PatternObject<UnclePO, Uncle>
{
   public UncleSet allMatches()
   {
      this.setDoAllMatches(true);
      
      UncleSet matches = new UncleSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Uncle) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public ParentPO hasBrother()
   {
      ParentPO result = new ParentPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Uncle.PROPERTY_BROTHER, result);
      
      return result;
   }
   
   public UnclePO hasBrother(ParentPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Uncle.PROPERTY_BROTHER)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Parent getBrother()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Uncle) this.getCurrentMatch()).getBrother();
      }
      return null;
   }
   
}

