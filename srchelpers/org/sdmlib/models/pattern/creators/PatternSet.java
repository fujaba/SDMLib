package org.sdmlib.models.pattern.creators;

import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.modelsets.booleanSet;
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;

public class PatternSet extends LinkedHashSet<Pattern>
{
   public PatternElementSet getElements()
   {
      PatternElementSet result = new PatternElementSet();
      
      for (Pattern obj : this)
      {
         result.addAll(obj.getElements());
      }
      
      return result;
   }
   public booleanSet getHasMatch()
   {
      booleanSet result = new booleanSet();
      
      for (Pattern obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public PatternSet withHasMatch(boolean value)
   {
      for (Pattern obj : this)
      {
         obj.withHasMatch(value);
      }
      
      return this;
   }

   public PatternSet withCurrentNAC(NegativeApplicationCondition value)
   {
      for (Pattern obj : this)
      {
         obj.withCurrentSubPattern(value);
      }
      
      return this;
   }

   public PatternSet withElements(PatternElement value)
   {
      for (Pattern obj : this)
      {
         obj.withElements(value);
      }
      
      return this;
   }

   public PatternSet withoutElements(PatternElement value)
   {
      for (Pattern obj : this)
      {
         obj.withoutElements(value);
      }
      
      return this;
   }

   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (Pattern obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public PatternSet withModifier(String value)
   {
      for (Pattern obj : this)
      {
         obj.withModifier(value);
      }
      
      return this;
   }

   public booleanList getDoAllMatches()
   {
      booleanList result = new booleanList();
      
      for (Pattern obj : this)
      {
         result.add(obj.getDoAllMatches());
      }
      
      return result;
   }

   public PatternSet withDoAllMatches(boolean value)
   {
      for (Pattern obj : this)
      {
         obj.withDoAllMatches(value);
      }
      
      return this;
   }

   public StringList getPatternObjectName()
   {
      StringList result = new StringList();
      
      for (Pattern obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }

   public PatternSet withPatternObjectName(String value)
   {
      for (Pattern obj : this)
      {
         obj.withPatternObjectName(value);
      }
      
      return this;
   }

   public PatternSet getCurrentSubPattern()
   {
      PatternSet result = new PatternSet();
      
      for (Pattern obj : this)
      {
         result.add(obj.getCurrentSubPattern());
      }
      
      return result;
   }

   public PatternSet withCurrentSubPattern(Pattern value)
   {
      for (Pattern obj : this)
      {
         obj.withCurrentSubPattern(value);
      }
      
      return this;
   }

}









