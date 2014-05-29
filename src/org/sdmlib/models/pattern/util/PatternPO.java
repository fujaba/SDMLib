package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.ReachabilityGraph;

public class PatternPO extends PatternObject<PatternPO, Pattern<Object>>
{
   public PatternPO hasModifier(String value)
   {
      new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pattern) getCurrentMatch()).withModifier(value);
      }
      return this;
   }
   
   public PatternPO hasHasMatch(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pattern) getCurrentMatch()).withHasMatch(value);
      }
      return this;
   }
   
   public PatternElementPO hasElements()
   {
      PatternElementPO result = new PatternElementPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Pattern.PROPERTY_ELEMENTS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public PatternPO hasElements(PatternElementPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Pattern.PROPERTY_ELEMENTS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternPO withElements(PatternElementPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pattern) this.getCurrentMatch()).withElements((PatternElement) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PatternPO withoutElements(PatternElementPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pattern) this.getCurrentMatch()).withoutElements((PatternElement) tgtPO.getCurrentMatch());
      }
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
   
   public boolean getHasMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pattern) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
   public PatternElementSet getElements()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pattern) this.getCurrentMatch()).getElements();
      }
      return null;
   }
   
   public PatternPO hasDoAllMatches(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_DOALLMATCHES)
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
         return ((Pattern) getCurrentMatch()).getDoAllMatches();
      }
      return false;
   }
   
   public PatternPO hasPatternObjectName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_PATTERNOBJECTNAME)
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
         return ((Pattern) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public PatternPO hasCurrentSubPattern(Pattern value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_CURRENTSUBPATTERN)
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
         return ((Pattern) getCurrentMatch()).getCurrentSubPattern();
      }
      return null;
   }
   
   public PatternPO hasDebugMode(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_DEBUGMODE)
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
   
   public PatternPO hasPattern()
   {
      PatternPO result = new PatternPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(PatternElement.PROPERTY_PATTERN, result);
      
      return result;
   }

   public PatternPO hasPattern(PatternPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PatternElement.PROPERTY_PATTERN)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Pattern<Object> getPattern()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PatternElement) this.getCurrentMatch()).getPattern();
      }
      return null;
   }

   public PatternPO hasTrace(StringBuilder value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_TRACE)
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
         return ((Pattern) getCurrentMatch()).getTrace();
      }
      return null;
   }
   
   public PatternPO withTrace(StringBuilder value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pattern) getCurrentMatch()).setTrace(value);
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

   public PatternPO hasRgraph(ReachabilityGraphPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Pattern.PROPERTY_RGRAPH)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public ReachabilityGraph getRgraph()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pattern) this.getCurrentMatch()).getRgraph();
      }
      return null;
   }

   public PatternPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_NAME)
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
   
   public PatternPO hasCurrentSubPattern(Pattern lower, Pattern upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_CURRENTSUBPATTERN)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternPO hasDebugMode(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_DEBUGMODE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternPO hasTrace(StringBuilder lower, StringBuilder upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_TRACE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternPO hasName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternPO hasModifier(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_MODIFIER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternPO hasHasMatch(boolean lower, boolean upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_HASMATCH)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternPO hasPatternObjectName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_PATTERNOBJECTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternPO hasDoAllMatches(boolean lower, boolean upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_DOALLMATCHES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternPO createCurrentSubPattern(Pattern value)
   {
      this.startCreate().hasCurrentSubPattern(value).endCreate();
      return this;
   }
   
   public PatternPO createDebugMode(int value)
   {
      this.startCreate().hasDebugMode(value).endCreate();
      return this;
   }
   
   public PatternPO createTrace(StringBuilder value)
   {
      this.startCreate().hasTrace(value).endCreate();
      return this;
   }
   
   public PatternPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public PatternPO createModifier(String value)
   {
      this.startCreate().hasModifier(value).endCreate();
      return this;
   }
   
   public PatternPO createHasMatch(boolean value)
   {
      this.startCreate().hasHasMatch(value).endCreate();
      return this;
   }
   
   public PatternPO createPatternObjectName(String value)
   {
      this.startCreate().hasPatternObjectName(value).endCreate();
      return this;
   }
   
   public PatternPO createDoAllMatches(boolean value)
   {
      this.startCreate().hasDoAllMatches(value).endCreate();
      return this;
   }
   
   public PatternElementPO createElements()
   {
      return this.startCreate().hasElements().endCreate();
   }

   public PatternPO createElements(PatternElementPO tgt)
   {
      return this.startCreate().hasElements(tgt).endCreate();
   }

   public PatternPO createPattern()
   {
      return this.startCreate().hasPattern().endCreate();
   }

   public PatternPO createPattern(PatternPO tgt)
   {
      return this.startCreate().hasPattern(tgt).endCreate();
   }

   public ReachabilityGraphPO createRgraph()
   {
      return this.startCreate().hasRgraph().endCreate();
   }

   public PatternPO createRgraph(ReachabilityGraphPO tgt)
   {
      return this.startCreate().hasRgraph(tgt).endCreate();
   }

}












