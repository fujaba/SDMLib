package org.sdmlib.models.pattern.creators;

import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.booleanSet;
import org.sdmlib.models.pattern.NegativeApplicationCondition;

public class NegativeApplicationConditionSet extends LinkedHashSet<NegativeApplicationCondition>
{
   public booleanSet getHasMatch()
   {
      booleanSet result = new booleanSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public NegativeApplicationConditionSet getCurrentNAC()
   {
      NegativeApplicationConditionSet result = new NegativeApplicationConditionSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         result.add(obj.getCurrentNAC());
      }
      
      return result;
   }

}


