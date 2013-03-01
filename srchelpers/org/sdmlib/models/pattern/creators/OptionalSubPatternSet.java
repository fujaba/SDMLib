package org.sdmlib.models.pattern.creators;

import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.pattern.OptionalSubPattern;
import org.sdmlib.models.pattern.Pattern;

public class OptionalSubPatternSet extends LinkedHashSet<OptionalSubPattern>
{
   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (OptionalSubPattern obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public OptionalSubPatternSet withModifier(String value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.withModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (OptionalSubPattern obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public OptionalSubPatternSet withHasMatch(boolean value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.withHasMatch(value);
      }
      
      return this;
   }

   public StringList getPatternObjectName()
   {
      StringList result = new StringList();
      
      for (OptionalSubPattern obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }

   public OptionalSubPatternSet withPatternObjectName(String value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.withPatternObjectName(value);
      }
      
      return this;
   }

   public booleanList getDoAllMatches()
   {
      booleanList result = new booleanList();
      
      for (OptionalSubPattern obj : this)
      {
         result.add(obj.getDoAllMatches());
      }
      
      return result;
   }

   public OptionalSubPatternSet withDoAllMatches(boolean value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.withDoAllMatches(value);
      }
      
      return this;
   }

   public booleanList getMatchForward()
   {
      booleanList result = new booleanList();
      
      for (OptionalSubPattern obj : this)
      {
         result.add(obj.getMatchForward());
      }
      
      return result;
   }

   public OptionalSubPatternSet withMatchForward(boolean value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.withMatchForward(value);
      }
      
      return this;
   }

   public PatternSet getCurrentSubPattern()
   {
      PatternSet result = new PatternSet();
      
      for (OptionalSubPattern obj : this)
      {
         result.add(obj.getCurrentSubPattern());
      }
      
      return result;
   }

   public OptionalSubPatternSet withCurrentSubPattern(Pattern value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.withCurrentSubPattern(value);
      }
      
      return this;
   }

}


