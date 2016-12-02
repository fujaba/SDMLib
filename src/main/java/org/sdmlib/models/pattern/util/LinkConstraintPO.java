package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;

public class LinkConstraintPO extends PatternObject<LinkConstraintPO, LinkConstraint>
{

    public LinkConstraintSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LinkConstraintSet matches = new LinkConstraintSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((LinkConstraint) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public LinkConstraintPO(){
      newInstance(null);
   }

   public LinkConstraintPO(LinkConstraint... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public LinkConstraintPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public LinkConstraintPO createTgtRoleNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_TGTROLENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LinkConstraintPO createTgtRoleNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_TGTROLENAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LinkConstraintPO createTgtRoleNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_TGTROLENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getTgtRoleName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LinkConstraint) getCurrentMatch()).getTgtRoleName();
      }
      return null;
   }
   
   public LinkConstraintPO withTgtRoleName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LinkConstraint) getCurrentMatch()).setTgtRoleName(value);
      }
      return this;
   }
   
   public LinkConstraintPO createHostGraphSrcObjectCondition(Object value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LinkConstraintPO createHostGraphSrcObjectAssignment(Object value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public Object getHostGraphSrcObject()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LinkConstraint) getCurrentMatch()).getHostGraphSrcObject();
      }
      return null;
   }
   
   public LinkConstraintPO withHostGraphSrcObject(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LinkConstraint) getCurrentMatch()).setHostGraphSrcObject(value);
      }
      return this;
   }
   
   public LinkConstraintPO createModifierCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LinkConstraintPO createModifierCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LinkConstraintPO createModifierAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getModifier()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LinkConstraint) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public LinkConstraintPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LinkConstraint) getCurrentMatch()).setModifier(value);
      }
      return this;
   }
   
   public LinkConstraintPO createHasMatchCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LinkConstraintPO createHasMatchAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public boolean getHasMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LinkConstraint) getCurrentMatch()).isHasMatch();
      }
      return false;
   }
   
   public LinkConstraintPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LinkConstraint) getCurrentMatch()).setHasMatch(value);
      }
      return this;
   }
   
   public LinkConstraintPO createPatternObjectNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LinkConstraintPO createPatternObjectNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LinkConstraintPO createPatternObjectNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getPatternObjectName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LinkConstraint) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public LinkConstraintPO withPatternObjectName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LinkConstraint) getCurrentMatch()).setPatternObjectName(value);
      }
      return this;
   }
   
   public LinkConstraintPO createDoAllMatchesCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LinkConstraintPO createDoAllMatchesAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public boolean getDoAllMatches()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LinkConstraint) getCurrentMatch()).isDoAllMatches();
      }
      return false;
   }
   
   public LinkConstraintPO withDoAllMatches(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LinkConstraint) getCurrentMatch()).setDoAllMatches(value);
      }
      return this;
   }
   
   public PatternPO createPatternPO()
   {
      PatternPO result = new PatternPO(new Pattern[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PatternElement.PROPERTY_PATTERN, result);
      
      return result;
   }

   public PatternPO createPatternPO(String modifier)
   {
      PatternPO result = new PatternPO(new Pattern[]{});
      
      result.setModifier(modifier);
      super.hasLink(PatternElement.PROPERTY_PATTERN, result);
      
      return result;
   }

   public LinkConstraintPO createPatternLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }

   public LinkConstraintPO createPatternLink(PatternPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN, modifier);
   }

   public Pattern getPattern()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternElement) this.getCurrentMatch()).getPattern();
      }
      return null;
   }

}
