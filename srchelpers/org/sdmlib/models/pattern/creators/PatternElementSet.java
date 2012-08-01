package org.sdmlib.models.pattern.creators;

import java.util.ArrayList;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import java.util.List;

public class PatternElementSet extends ArrayList<PatternElement>
{
   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (PatternElement obj : this)
      {
         result.add(obj.getPattern());
      }
      
      return result;
   }
   public PatternElementSet withPattern(Pattern value)
   {
      for (PatternElement obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }

   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (PatternElement obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public PatternElementSet withModifier(String value)
   {
      for (PatternElement obj : this)
      {
         obj.withModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (PatternElement obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public PatternElementSet withHasMatch(boolean value)
   {
      for (PatternElement obj : this)
      {
         obj.withHasMatch(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (PatternElement obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public PatternElementSet withName(String value)
   {
      for (PatternElement obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

}





