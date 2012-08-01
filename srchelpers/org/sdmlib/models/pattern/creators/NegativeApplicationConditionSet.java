package org.sdmlib.models.pattern.creators;

import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.booleanSet;
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;

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

   public NegativeApplicationConditionSet withHasMatch(boolean value)
   {
      for (NegativeApplicationCondition obj : this)
      {
         obj.withHasMatch(value);
      }
      
      return this;
   }

   public NegativeApplicationConditionSet withCurrentNAC(NegativeApplicationCondition value)
   {
      for (NegativeApplicationCondition obj : this)
      {
         obj.withCurrentNAC(value);
      }
      
      return this;
   }

   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (NegativeApplicationCondition obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public NegativeApplicationConditionSet withModifier(String value)
   {
      for (NegativeApplicationCondition obj : this)
      {
         obj.withModifier(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (NegativeApplicationCondition obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public NegativeApplicationConditionSet withName(String value)
   {
      for (NegativeApplicationCondition obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

}





