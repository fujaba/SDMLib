package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.util.ReachabilityGraphPO;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.util.NegativeApplicationConditionPO;

public class NegativeApplicationConditionPO extends PatternObject<NegativeApplicationConditionPO, NegativeApplicationCondition>
{

   public NegativeApplicationConditionSet allMatches()
   {
      this.setDoAllMatches(true);

      NegativeApplicationConditionSet matches = new NegativeApplicationConditionSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((NegativeApplicationCondition) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }


   public NegativeApplicationConditionPO()
   {
      newInstance(null);
   }


   public NegativeApplicationConditionPO(NegativeApplicationCondition... hostGraphObject)
   {
      if (hostGraphObject == null || hostGraphObject.length < 1)
      {
         return;
      }
      newInstance(null, hostGraphObject);
   }


   public NegativeApplicationConditionPO(String modifier)
   {
      this.setModifier(modifier);
   }


   public NegativeApplicationConditionPO createDebugModeCondition(int value)
   {
      new AttributeConstraint()
         .withAttrName(NegativeApplicationCondition.PROPERTY_DEBUGMODE)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }


   public NegativeApplicationConditionPO createDebugModeCondition(int lower, int upper)
   {
      new AttributeConstraint()
         .withAttrName(NegativeApplicationCondition.PROPERTY_DEBUGMODE)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }


   public NegativeApplicationConditionPO createDebugModeAssignment(int value)
   {
      new AttributeConstraint()
         .withAttrName(NegativeApplicationCondition.PROPERTY_DEBUGMODE)
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
         return ((NegativeApplicationCondition) getCurrentMatch()).getDebugMode();
      }
      return 0;
   }


   public NegativeApplicationConditionPO withDebugMode(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((NegativeApplicationCondition) getCurrentMatch()).setDebugMode(value);
      }
      return this;
   }


   public NegativeApplicationConditionPO createNameCondition(String value)
   {
      new AttributeConstraint()
         .withAttrName(NegativeApplicationCondition.PROPERTY_NAME)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }


   public NegativeApplicationConditionPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(NegativeApplicationCondition.PROPERTY_NAME)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }


   public NegativeApplicationConditionPO createNameAssignment(String value)
   {
      new AttributeConstraint()
         .withAttrName(NegativeApplicationCondition.PROPERTY_NAME)
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
         return ((NegativeApplicationCondition) getCurrentMatch()).getName();
      }
      return null;
   }


   public NegativeApplicationConditionPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((NegativeApplicationCondition) getCurrentMatch()).setName(value);
      }
      return this;
   }


   public NegativeApplicationConditionPO createModifierCondition(String value)
   {
      new AttributeConstraint()
         .withAttrName(NegativeApplicationCondition.PROPERTY_MODIFIER)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }


   public NegativeApplicationConditionPO createModifierCondition(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(NegativeApplicationCondition.PROPERTY_MODIFIER)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }


   public NegativeApplicationConditionPO createModifierAssignment(String value)
   {
      new AttributeConstraint()
         .withAttrName(NegativeApplicationCondition.PROPERTY_MODIFIER)
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
         return ((NegativeApplicationCondition) getCurrentMatch()).getModifier();
      }
      return null;
   }


   public NegativeApplicationConditionPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((NegativeApplicationCondition) getCurrentMatch()).setModifier(value);
      }
      return this;
   }


   public NegativeApplicationConditionPO createHasMatchCondition(boolean value)
   {
      new AttributeConstraint()
         .withAttrName(NegativeApplicationCondition.PROPERTY_HASMATCH)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }


   public NegativeApplicationConditionPO createHasMatchAssignment(boolean value)
   {
      new AttributeConstraint()
         .withAttrName(NegativeApplicationCondition.PROPERTY_HASMATCH)
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
         return ((NegativeApplicationCondition) getCurrentMatch()).isHasMatch();
      }
      return false;
   }


   public NegativeApplicationConditionPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((NegativeApplicationCondition) getCurrentMatch()).setHasMatch(value);
      }
      return this;
   }


   public NegativeApplicationConditionPO createPatternObjectNameCondition(String value)
   {
      new AttributeConstraint()
         .withAttrName(NegativeApplicationCondition.PROPERTY_PATTERNOBJECTNAME)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }


   public NegativeApplicationConditionPO createPatternObjectNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(NegativeApplicationCondition.PROPERTY_PATTERNOBJECTNAME)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }


   public NegativeApplicationConditionPO createPatternObjectNameAssignment(String value)
   {
      new AttributeConstraint()
         .withAttrName(NegativeApplicationCondition.PROPERTY_PATTERNOBJECTNAME)
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
         return ((NegativeApplicationCondition) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }


   public NegativeApplicationConditionPO withPatternObjectName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((NegativeApplicationCondition) getCurrentMatch()).setPatternObjectName(value);
      }
      return this;
   }


   public NegativeApplicationConditionPO createDoAllMatchesCondition(boolean value)
   {
      new AttributeConstraint()
         .withAttrName(NegativeApplicationCondition.PROPERTY_DOALLMATCHES)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }


   public NegativeApplicationConditionPO createDoAllMatchesAssignment(boolean value)
   {
      new AttributeConstraint()
         .withAttrName(NegativeApplicationCondition.PROPERTY_DOALLMATCHES)
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
         return ((NegativeApplicationCondition) getCurrentMatch()).isDoAllMatches();
      }
      return false;
   }


   public NegativeApplicationConditionPO withDoAllMatches(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((NegativeApplicationCondition) getCurrentMatch()).setDoAllMatches(value);
      }
      return this;
   }


   public PatternPO createPatternPO()
   {
      PatternPO result = new PatternPO(new Pattern[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PatternElement.PROPERTY_PATTERN, result);

      return result;
   }


   public PatternPO createPatternPO(String modifier)
   {
      PatternPO result = new PatternPO(new Pattern[]
      {});

      result.setModifier(modifier);
      super.hasLink(PatternElement.PROPERTY_PATTERN, result);

      return result;
   }


   public NegativeApplicationConditionPO createPatternLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }


   public NegativeApplicationConditionPO createPatternLink(PatternPO tgt, String modifier)
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
      PatternElementPO result = new PatternElementPO(new PatternElement[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pattern.PROPERTY_ELEMENTS, result);

      return result;
   }


   public PatternElementPO createElementsPO(String modifier)
   {
      PatternElementPO result = new PatternElementPO(new PatternElement[]
      {});

      result.setModifier(modifier);
      super.hasLink(Pattern.PROPERTY_ELEMENTS, result);

      return result;
   }


   public NegativeApplicationConditionPO createElementsLink(PatternElementPO tgt)
   {
      return hasLinkConstraint(tgt, Pattern.PROPERTY_ELEMENTS);
   }


   public NegativeApplicationConditionPO createElementsLink(PatternElementPO tgt, String modifier)
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
      PatternPO result = new PatternPO(new Pattern[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pattern.PROPERTY_CURRENTSUBPATTERN, result);

      return result;
   }


   public PatternPO createCurrentSubPatternPO(String modifier)
   {
      PatternPO result = new PatternPO(new Pattern[]
      {});

      result.setModifier(modifier);
      super.hasLink(Pattern.PROPERTY_CURRENTSUBPATTERN, result);

      return result;
   }


   public NegativeApplicationConditionPO createCurrentSubPatternLink(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, Pattern.PROPERTY_CURRENTSUBPATTERN);
   }


   public NegativeApplicationConditionPO createCurrentSubPatternLink(PatternPO tgt, String modifier)
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
      super.hasLink(NegativeApplicationCondition.PROPERTY_RGRAPH, result);
      
      return result;
   }

   public ReachabilityGraphPO createRgraphPO(String modifier)
   {
      ReachabilityGraphPO result = new ReachabilityGraphPO(new ReachabilityGraph[]{});
      
      result.setModifier(modifier);
      super.hasLink(NegativeApplicationCondition.PROPERTY_RGRAPH, result);
      
      return result;
   }

   public NegativeApplicationConditionPO createRgraphLink(ReachabilityGraphPO tgt)
   {
      return hasLinkConstraint(tgt, NegativeApplicationCondition.PROPERTY_RGRAPH);
   }

   public NegativeApplicationConditionPO createRgraphLink(ReachabilityGraphPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, NegativeApplicationCondition.PROPERTY_RGRAPH, modifier);
   }

   public ReachabilityGraph getRgraph()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((NegativeApplicationCondition) this.getCurrentMatch()).getRgraph();
      }
      return null;
   }

}
