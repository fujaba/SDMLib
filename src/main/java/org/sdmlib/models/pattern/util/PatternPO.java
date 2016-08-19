package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.util.PatternPO;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.util.PatternElementPO;
import org.sdmlib.models.pattern.util.PatternElementSet;
import org.sdmlib.models.pattern.util.ReachabilityGraphPO;
import org.sdmlib.models.pattern.ReachabilityGraph;

public class PatternPO extends PatternObject<PatternPO, Pattern>
{

    public PatternSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PatternSet matches = new PatternSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Pattern) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PatternPO(){
      newInstance(null);
   }

   public PatternPO(Pattern... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public PatternPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PatternPO createDebugModeCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_DEBUGMODE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternPO createDebugModeCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_DEBUGMODE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternPO createDebugModeAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_DEBUGMODE)
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
         return ((Pattern) getCurrentMatch()).getDebugMode();
      }
      return 0;
   }
   
   public PatternPO withDebugMode(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pattern) getCurrentMatch()).setDebugMode(value);
      }
      return this;
   }
   
   public PatternPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_NAME)
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
         return ((Pattern) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public PatternPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pattern) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public PatternPO createModifierCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternPO createModifierCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternPO createModifierAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_MODIFIER)
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
         return ((Pattern) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public PatternPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pattern) getCurrentMatch()).setModifier(value);
      }
      return this;
   }
   
   public PatternPO createHasMatchCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternPO createHasMatchAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_HASMATCH)
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
         return ((Pattern) getCurrentMatch()).isHasMatch();
      }
      return false;
   }
   
   public PatternPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pattern) getCurrentMatch()).setHasMatch(value);
      }
      return this;
   }
   
   public PatternPO createPatternObjectNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternPO createPatternObjectNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternPO createPatternObjectNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_PATTERNOBJECTNAME)
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
         return ((Pattern) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public PatternPO withPatternObjectName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pattern) getCurrentMatch()).setPatternObjectName(value);
      }
      return this;
   }
   
   public PatternPO createDoAllMatchesCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PatternPO createDoAllMatchesAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_DOALLMATCHES)
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
         return ((Pattern) getCurrentMatch()).isDoAllMatches();
      }
      return false;
   }
   
   public PatternPO withDoAllMatches(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pattern) getCurrentMatch()).setDoAllMatches(value);
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

   public PatternPO createPatternLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }

   public PatternPO createPatternLink(PatternPO tgt, String modifier)
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

   public PatternPO createElementsLink(PatternElementPO tgt)
   {
      return hasLinkConstraint(tgt, Pattern.PROPERTY_ELEMENTS);
   }

   public PatternPO createElementsLink(PatternElementPO tgt, String modifier)
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

   public PatternPO createCurrentSubPatternLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, Pattern.PROPERTY_CURRENTSUBPATTERN);
   }

   public PatternPO createCurrentSubPatternLink(PatternPO tgt, String modifier)
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
      super.hasLink(Pattern.PROPERTY_RGRAPH, result);
      
      return result;
   }

   public ReachabilityGraphPO createRgraphPO(String modifier)
   {
      ReachabilityGraphPO result = new ReachabilityGraphPO(new ReachabilityGraph[]{});
      
      result.setModifier(modifier);
      super.hasLink(Pattern.PROPERTY_RGRAPH, result);
      
      return result;
   }

   public PatternPO createRgraphLink(ReachabilityGraphPO tgt)
   {
      return hasLinkConstraint(tgt, Pattern.PROPERTY_RGRAPH);
   }

   public PatternPO createRgraphLink(ReachabilityGraphPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Pattern.PROPERTY_RGRAPH, modifier);
   }

   public ReachabilityGraph getRgraph()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pattern) this.getCurrentMatch()).getRgraph();
      }
      return null;
   }

}
