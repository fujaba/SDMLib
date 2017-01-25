package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.OptionalSubPattern;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.util.ReachabilityGraphPO;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.util.OptionalSubPatternPO;

public class OptionalSubPatternPO extends PatternObject<OptionalSubPatternPO, OptionalSubPattern>
{

    public OptionalSubPatternSet allMatches()
   {
      this.setDoAllMatches(true);
      
      OptionalSubPatternSet matches = new OptionalSubPatternSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((OptionalSubPattern) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public OptionalSubPatternPO(){
      newInstance(null);
   }

   public OptionalSubPatternPO(OptionalSubPattern... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public OptionalSubPatternPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public OptionalSubPatternPO createMatchForwardCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_MATCHFORWARD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public OptionalSubPatternPO createMatchForwardAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_MATCHFORWARD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public boolean getMatchForward()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((OptionalSubPattern) getCurrentMatch()).isMatchForward();
      }
      return false;
   }
   
   public OptionalSubPatternPO withMatchForward(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((OptionalSubPattern) getCurrentMatch()).setMatchForward(value);
      }
      return this;
   }
   
   public OptionalSubPatternPO createDebugModeCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_DEBUGMODE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public OptionalSubPatternPO createDebugModeCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_DEBUGMODE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public OptionalSubPatternPO createDebugModeAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_DEBUGMODE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public int getDebugMode()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((OptionalSubPattern) getCurrentMatch()).getDebugMode();
      }
      return 0;
   }
   
   public OptionalSubPatternPO withDebugMode(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((OptionalSubPattern) getCurrentMatch()).setDebugMode(value);
      }
      return this;
   }
   
   public OptionalSubPatternPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public OptionalSubPatternPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public OptionalSubPatternPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((OptionalSubPattern) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public OptionalSubPatternPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((OptionalSubPattern) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public OptionalSubPatternPO createModifierCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public OptionalSubPatternPO createModifierCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public OptionalSubPatternPO createModifierAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_MODIFIER)
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
         return ((OptionalSubPattern) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public OptionalSubPatternPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((OptionalSubPattern) getCurrentMatch()).setModifier(value);
      }
      return this;
   }
   
   public OptionalSubPatternPO createHasMatchCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public OptionalSubPatternPO createHasMatchAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_HASMATCH)
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
         return ((OptionalSubPattern) getCurrentMatch()).isHasMatch();
      }
      return false;
   }
   
   public OptionalSubPatternPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((OptionalSubPattern) getCurrentMatch()).setHasMatch(value);
      }
      return this;
   }
   
   public OptionalSubPatternPO createPatternObjectNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public OptionalSubPatternPO createPatternObjectNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public OptionalSubPatternPO createPatternObjectNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_PATTERNOBJECTNAME)
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
         return ((OptionalSubPattern) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public OptionalSubPatternPO withPatternObjectName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((OptionalSubPattern) getCurrentMatch()).setPatternObjectName(value);
      }
      return this;
   }
   
   public OptionalSubPatternPO createDoAllMatchesCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public OptionalSubPatternPO createDoAllMatchesAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(OptionalSubPattern.PROPERTY_DOALLMATCHES)
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
         return ((OptionalSubPattern) getCurrentMatch()).isDoAllMatches();
      }
      return false;
   }
   
   public OptionalSubPatternPO withDoAllMatches(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((OptionalSubPattern) getCurrentMatch()).setDoAllMatches(value);
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

   public OptionalSubPatternPO createPatternLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }

   public OptionalSubPatternPO createPatternLink(PatternPO tgt, String modifier)
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

   public PatternElementPO createElementsPO()
   {
      PatternElementPO result = new PatternElementPO(new PatternElement[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pattern.PROPERTY_ELEMENTS, result);
      
      return result;
   }

   public PatternElementPO createElementsPO(String modifier)
   {
      PatternElementPO result = new PatternElementPO(new PatternElement[]{});
      
      result.setModifier(modifier);
      super.hasLink(Pattern.PROPERTY_ELEMENTS, result);
      
      return result;
   }

   public OptionalSubPatternPO createElementsLink(PatternElementPO tgt)
   {
      return hasLinkConstraint(tgt, Pattern.PROPERTY_ELEMENTS);
   }

   public OptionalSubPatternPO createElementsLink(PatternElementPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Pattern.PROPERTY_ELEMENTS, modifier);
   }

   public PatternElementSet getElements()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pattern) this.getCurrentMatch()).getElements();
      }
      return null;
   }

   public PatternPO createCurrentSubPatternPO()
   {
      PatternPO result = new PatternPO(new Pattern[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pattern.PROPERTY_CURRENTSUBPATTERN, result);
      
      return result;
   }

   public PatternPO createCurrentSubPatternPO(String modifier)
   {
      PatternPO result = new PatternPO(new Pattern[]{});
      
      result.setModifier(modifier);
      super.hasLink(Pattern.PROPERTY_CURRENTSUBPATTERN, result);
      
      return result;
   }

   public OptionalSubPatternPO createCurrentSubPatternLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, Pattern.PROPERTY_CURRENTSUBPATTERN);
   }

   public OptionalSubPatternPO createCurrentSubPatternLink(PatternPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Pattern.PROPERTY_CURRENTSUBPATTERN, modifier);
   }

   public Pattern getCurrentSubPattern()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pattern) this.getCurrentMatch()).getCurrentSubPattern();
      }
      return null;
   }

   public ReachabilityGraphPO createRgraphPO()
   {
      ReachabilityGraphPO result = new ReachabilityGraphPO(new ReachabilityGraph[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(OptionalSubPattern.PROPERTY_RGRAPH, result);
      
      return result;
   }

   public ReachabilityGraphPO createRgraphPO(String modifier)
   {
      ReachabilityGraphPO result = new ReachabilityGraphPO(new ReachabilityGraph[]{});
      
      result.setModifier(modifier);
      super.hasLink(OptionalSubPattern.PROPERTY_RGRAPH, result);
      
      return result;
   }

   public OptionalSubPatternPO createRgraphLink(ReachabilityGraphPO tgt)
   {
      return hasLinkConstraint(tgt, OptionalSubPattern.PROPERTY_RGRAPH);
   }

   public OptionalSubPatternPO createRgraphLink(ReachabilityGraphPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, OptionalSubPattern.PROPERTY_RGRAPH, modifier);
   }

   public ReachabilityGraph getRgraph()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((OptionalSubPattern) this.getCurrentMatch()).getRgraph();
      }
      return null;
   }

}
