package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;

public class PatternLinkPO extends PatternObject<PatternLinkPO, PatternLink>
{

    public PatternLinkSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PatternLinkSet matches = new PatternLinkSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((PatternLink) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PatternLinkPO(){
      newInstance(null);
   }

   public PatternLinkPO(PatternLink... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public PatternLinkPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PatternLinkPO createTgtRoleNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_TGTROLENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternLinkPO createTgtRoleNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_TGTROLENAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternLinkPO createTgtRoleNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_TGTROLENAME)
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
         return ((PatternLink) getCurrentMatch()).getTgtRoleName();
      }
      return null;
   }
   
   public PatternLinkPO withTgtRoleName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternLink) getCurrentMatch()).setTgtRoleName(value);
      }
      return this;
   }
   
   public PatternLinkPO createHostGraphSrcObjectCondition(Object value)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternLinkPO createHostGraphSrcObjectAssignment(Object value)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_HOSTGRAPHSRCOBJECT)
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
         return ((PatternLink) getCurrentMatch()).getHostGraphSrcObject();
      }
      return null;
   }
   
   public PatternLinkPO withHostGraphSrcObject(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternLink) getCurrentMatch()).setHostGraphSrcObject(value);
      }
      return this;
   }
   
   public PatternLinkPO createModifierCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternLinkPO createModifierCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternLinkPO createModifierAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_MODIFIER)
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
         return ((PatternLink) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public PatternLinkPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternLink) getCurrentMatch()).setModifier(value);
      }
      return this;
   }
   
   public PatternLinkPO createHasMatchCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternLinkPO createHasMatchAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_HASMATCH)
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
         return ((PatternLink) getCurrentMatch()).isHasMatch();
      }
      return false;
   }
   
   public PatternLinkPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternLink) getCurrentMatch()).setHasMatch(value);
      }
      return this;
   }
   
   public PatternLinkPO createPatternObjectNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternLinkPO createPatternObjectNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternLinkPO createPatternObjectNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_PATTERNOBJECTNAME)
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
         return ((PatternLink) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public PatternLinkPO withPatternObjectName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternLink) getCurrentMatch()).setPatternObjectName(value);
      }
      return this;
   }
   
   public PatternLinkPO createDoAllMatchesCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternLinkPO createDoAllMatchesAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(PatternLink.PROPERTY_DOALLMATCHES)
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
         return ((PatternLink) getCurrentMatch()).isDoAllMatches();
      }
      return false;
   }
   
   public PatternLinkPO withDoAllMatches(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PatternLink) getCurrentMatch()).setDoAllMatches(value);
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

   public PatternLinkPO createPatternLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }

   public PatternLinkPO createPatternLink(PatternPO tgt, String modifier)
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
