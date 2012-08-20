package org.sdmlib.models.pattern.creators;

import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.booleanSet;
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.booleanList;

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

   public booleanList getDoAllMatches()
   {
      booleanList result = new booleanList();
      
      for (NegativeApplicationCondition obj : this)
      {
         result.add(obj.getDoAllMatches());
      }
      
      return result;
   }

   public NegativeApplicationConditionSet withDoAllMatches(boolean value)
   {
      for (NegativeApplicationCondition obj : this)
      {
         obj.withDoAllMatches(value);
      }
      
      return this;
   }

   public StringList getPatternObjectName()
   {
      StringList result = new StringList();
      
      for (NegativeApplicationCondition obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }

   public NegativeApplicationConditionSet withPatternObjectName(String value)
   {
      for (NegativeApplicationCondition obj : this)
      {
         obj.withPatternObjectName(value);
      }
      
      return this;
   }

}







