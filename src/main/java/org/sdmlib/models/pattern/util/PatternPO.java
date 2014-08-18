package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.ReachabilityGraph;

public class PatternPO extends PatternObject<PatternPO, Pattern<Object>>
{
   public PatternPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PatternPO(Pattern... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
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
      if (super.getCurrentMatch() != null)
      {
         ((Pattern) getCurrentMatch()).withModifier(value);
      }
      return this;
   }
   
   public PatternPO hasHasMatch(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Pattern.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PatternPO withHasMatch(boolean value)
   {
      if (super.getCurrentMatch() != null)
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
      return hasLinkConstraint(tgt, Pattern.PROPERTY_ELEMENTS);
   }
   
   
   public PatternPO withElements(PatternElementPO tgtPO)
   {
      if (super.getCurrentMatch() != null)
      {
         ((Pattern) this.getCurrentMatch()).withElements((PatternElement) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public PatternPO withoutElements(PatternElementPO tgtPO)
   {
      if (super.getCurrentMatch() != null)
      {
         ((Pattern) this.getCurrentMatch()).withoutElements((PatternElement) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public String getModifier()
   {
      if (super.getCurrentMatch() != null)
      {
         return ((Pattern) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public boolean getHasMatch()
   {
      if (super.getCurrentMatch() != null)
      {
         return ((Pattern) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
   public PatternElementSet getElements()
   {
      if (super.getCurrentMatch() != null)
      {
         return ((Pattern) this.getCurrentMatch()).getElements();
      }
      return null;
   }
   
   public PatternPO hasDoAllMatches(boolean value)
   {
      new AttributeConstraint()
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
      if (super.getCurrentMatch() != null)
      {
         return ((Pattern) getCurrentMatch()).getDoAllMatches();
      }
      return false;
   }
   
   public PatternPO hasPatternObjectName(String value)
   {
      new AttributeConstraint()
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
      if (super.getCurrentMatch() != null)
      {
         return ((Pattern) getCurrentMatch()).getPatternObjectName();
      }
      return null;
   }
   
   public PatternPO hasCurrentSubPattern(Pattern value)
   {
      new AttributeConstraint()
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
      if (super.getCurrentMatch() != null)
      {
         return ((Pattern) getCurrentMatch()).getCurrentSubPattern();
      }
      return null;
   }
   
   public PatternPO hasDebugMode(int value)
   {
      new AttributeConstraint()
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
      if (super.getCurrentMatch() != null)
      {
         return ((Pattern) getCurrentMatch()).getDebugMode();
      }
      return 0;
   }
   
   public PatternPO withDebugMode(int value)
   {
      if (super.getCurrentMatch() != null)
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
      return hasLinkConstraint(tgt, PatternElement.PROPERTY_PATTERN);
   }

   public Pattern<PatternElement<?>> getPattern()
   {
      if (super.getCurrentMatch() != null)
      {
         return ((PatternElement) this.getCurrentMatch()).getPattern();
      }
      return super.getPattern();
   }

   public PatternPO hasTrace(StringBuilder value)
   {
      new AttributeConstraint()
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
      if (super.getCurrentMatch() != null)
      {
         return ((Pattern) getCurrentMatch()).getTrace();
      }
      return null;
   }
   
   public PatternPO withTrace(StringBuilder value)
   {
      if (super.getCurrentMatch() != null)
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
      return hasLinkConstraint(tgt, Pattern.PROPERTY_RGRAPH);
   }

   public ReachabilityGraph getRgraph()
   {
      if (super.getCurrentMatch() != null)
      {
         return ((Pattern) this.getCurrentMatch()).getRgraph();
      }
      return null;
   }

   public PatternPO hasName(String value)
   {
      new AttributeConstraint()
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
      if (super.getCurrentMatch() != null)
      {
         return ((Pattern) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public PatternPO withName(String value)
   {
      if (super.getCurrentMatch() != null)
      {
         ((Pattern) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public PatternPO hasCurrentSubPattern(Pattern lower, Pattern upper)
   {
      new AttributeConstraint()
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
      new AttributeConstraint()
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
      new AttributeConstraint()
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
      new AttributeConstraint()
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
      new AttributeConstraint()
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
      new AttributeConstraint()
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
      new AttributeConstraint()
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
      new AttributeConstraint()
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












