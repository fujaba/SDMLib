package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.util.ReachabilityGraphPO;
import org.sdmlib.models.pattern.util.NegativeApplicationConditionPO;
import org.sdmlib.models.pattern.util.PatternPO;

public class NegativeApplicationConditionPO extends PatternObject<NegativeApplicationConditionPO, NegativeApplicationCondition>
{
   public NegativeApplicationConditionPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public NegativeApplicationConditionPO(NegativeApplicationCondition... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   public NegativeApplicationConditionPO withCurrentNAC(NegativeApplicationCondition value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((NegativeApplicationCondition) getCurrentMatch()).withCurrentSubPattern(value);
      }
      return this;
   }
   
   public NegativeApplicationConditionPO hasModifier(String value)
   {
      new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NegativeApplicationConditionPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((NegativeApplicationCondition) getCurrentMatch()).withModifier(value);
      }
      return this;
   }
   
   public NegativeApplicationConditionPO hasHasMatch(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NegativeApplicationConditionPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((NegativeApplicationCondition) getCurrentMatch()).withHasMatch(value);
      }
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
   
   public boolean getHasMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((NegativeApplicationCondition) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
   public NegativeApplicationConditionPO hasDoAllMatches(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_DOALLMATCHES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public boolean getDoAllMatches()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((NegativeApplicationCondition) getCurrentMatch()).getDoAllMatches();
      }
      return false;
   }
   
   public NegativeApplicationConditionPO hasPatternObjectName(String value)
   {
      new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
   
   public NegativeApplicationConditionPO hasCurrentSubPattern(Pattern value)
   {
      new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_CURRENTSUBPATTERN)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Pattern getCurrentSubPattern()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((NegativeApplicationCondition) getCurrentMatch()).getCurrentSubPattern();
      }
      return null;
   }
   
   public NegativeApplicationConditionPO hasDebugMode(int value)
   {
      new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_DEBUGMODE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
   
   public PatternElementPO hasElements()
   {
      PatternElementPO result = new PatternElementPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Pattern.PROPERTY_ELEMENTS, result);
      
      return result;
   }

   public NegativeApplicationConditionPO hasElements(PatternElementPO tgt)
   {
      return hasLinkConstraint(tgt, Pattern.PROPERTY_ELEMENTS);
   }

   public PatternElementSet getElements()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pattern) this.getCurrentMatch()).getElements();
      }
      return null;
   }

   public PatternPO hasPattern()
   {
      PatternPO result = new PatternPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(PatternElement.PROPERTY_PATTERN, result);
      
      return result;
   }

   public NegativeApplicationConditionPO hasPattern(PatternPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternElement.PROPERTY_PATTERN)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Pattern getPattern()
   {
      if (super.getPattern().getHasMatch())
      {
         return ((PatternElement) this.getCurrentMatch()).getPattern();
      }
      return super.getPattern();
   }


   public NegativeApplicationConditionPO hasTrace(StringBuilder value)
   {
      new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_TRACE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public StringBuilder getTrace()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((NegativeApplicationCondition) getCurrentMatch()).getTrace();
      }
      return null;
   }
   
   public NegativeApplicationConditionPO withTrace(StringBuilder value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((NegativeApplicationCondition) getCurrentMatch()).setTrace(value);
      }
      return this;
   }
   
   public ReachabilityGraphPO hasRgraph()
   {
      ReachabilityGraphPO result = new ReachabilityGraphPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Pattern.PROPERTY_RGRAPH, result);
      
      return result;
   }

   public NegativeApplicationConditionPO hasRgraph(ReachabilityGraphPO tgt)
   {
      return hasLinkConstraint(tgt, Pattern.PROPERTY_RGRAPH);
   }

   public ReachabilityGraph getRgraph()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pattern) this.getCurrentMatch()).getRgraph();
      }
      return null;
   }

   public NegativeApplicationConditionPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_NAME)
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
   
   public NegativeApplicationConditionPO hasCurrentSubPattern(Pattern<?> lower, Pattern<?> upper)
   {
      new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_CURRENTSUBPATTERN)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NegativeApplicationConditionPO hasDebugMode(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_DEBUGMODE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NegativeApplicationConditionPO hasTrace(StringBuilder lower, StringBuilder upper)
   {
      new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_TRACE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NegativeApplicationConditionPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NegativeApplicationConditionPO hasModifier(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NegativeApplicationConditionPO hasHasMatch(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_HASMATCH)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NegativeApplicationConditionPO hasPatternObjectName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NegativeApplicationConditionPO hasDoAllMatches(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(NegativeApplicationCondition.PROPERTY_DOALLMATCHES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public NegativeApplicationConditionPO createCurrentSubPattern(Pattern value)
   {
      this.startCreate().hasCurrentSubPattern(value).endCreate();
      return this;
   }
   
   public NegativeApplicationConditionPO createDebugMode(int value)
   {
      this.startCreate().hasDebugMode(value).endCreate();
      return this;
   }
   
   public NegativeApplicationConditionPO createTrace(StringBuilder value)
   {
      this.startCreate().hasTrace(value).endCreate();
      return this;
   }
   
   public NegativeApplicationConditionPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public NegativeApplicationConditionPO createModifier(String value)
   {
      this.startCreate().hasModifier(value).endCreate();
      return this;
   }
   
   public NegativeApplicationConditionPO createHasMatch(boolean value)
   {
      this.startCreate().hasHasMatch(value).endCreate();
      return this;
   }
   
   public NegativeApplicationConditionPO createPatternObjectName(String value)
   {
      this.startCreate().hasPatternObjectName(value).endCreate();
      return this;
   }
   
   public NegativeApplicationConditionPO createDoAllMatches(boolean value)
   {
      this.startCreate().hasDoAllMatches(value).endCreate();
      return this;
   }
   
   public PatternElementPO createElements()
   {
      return (PatternElementPO) this.startCreate().hasElements().endCreate();
   }

   public NegativeApplicationConditionPO createElements(PatternElementPO tgt)
   {
      return this.startCreate().hasElements(tgt).endCreate();
   }

   public PatternPO createPattern()
   {
      return (PatternPO) this.startCreate().hasPattern().endCreate();
   }

   public NegativeApplicationConditionPO createPattern(PatternPO tgt)
   {
      return this.startCreate().hasPattern(tgt).endCreate();
   }

   public ReachabilityGraphPO createRgraph()
   {
      return this.startCreate().hasRgraph().endCreate();
   }

   public NegativeApplicationConditionPO createRgraph(ReachabilityGraphPO tgt)
   {
      return this.startCreate().hasRgraph(tgt).endCreate();
   }

   public NegativeApplicationConditionPO filterDebugMode(int value)
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
   
   public NegativeApplicationConditionPO filterDebugMode(int lower, int upper)
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
   
   public NegativeApplicationConditionPO filterName(String value)
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
   
   public NegativeApplicationConditionPO filterName(String lower, String upper)
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
   
   public NegativeApplicationConditionPO filterModifier(String value)
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
   
   public NegativeApplicationConditionPO filterModifier(String lower, String upper)
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
   
   public NegativeApplicationConditionPO filterHasMatch(boolean value)
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
   
   public NegativeApplicationConditionPO filterPatternObjectName(String value)
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
   
   public NegativeApplicationConditionPO filterPatternObjectName(String lower, String upper)
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
   
   public NegativeApplicationConditionPO filterDoAllMatches(boolean value)
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
   
   public ReachabilityGraphPO filterRgraph()
   {
      ReachabilityGraphPO result = new ReachabilityGraphPO(new ReachabilityGraph[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pattern.PROPERTY_RGRAPH, result);
      
      return result;
   }

   public NegativeApplicationConditionPO filterRgraph(ReachabilityGraphPO tgt)
   {
      return hasLinkConstraint(tgt, Pattern.PROPERTY_RGRAPH);
   }

   public PatternPO filterCurrentSubPattern()
   {
      PatternPO result = new PatternPO(new Pattern[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pattern.PROPERTY_CURRENTSUBPATTERN, result);
      
      return result;
   }

   public PatternPO createCurrentSubPattern()
   {
      return this.startCreate().filterCurrentSubPattern().endCreate();
   }

   public NegativeApplicationConditionPO filterCurrentSubPattern(PatternPO tgt)
   {
      return hasLinkConstraint(tgt, Pattern.PROPERTY_CURRENTSUBPATTERN);
   }

   public NegativeApplicationConditionPO createCurrentSubPattern(PatternPO tgt)
   {
      return this.startCreate().filterCurrentSubPattern(tgt).endCreate();
   }

}












