package org.sdmlib.models.pattern.creators;

import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.booleanSet;
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

   public NegativeApplicationConditionSet getCurrentNAC()
   {
      NegativeApplicationConditionSet result = new NegativeApplicationConditionSet();
      
      for (Pattern obj : this)
      {
         result.add(obj.getCurrentNAC());
      }
      
      return result;
   }

}



