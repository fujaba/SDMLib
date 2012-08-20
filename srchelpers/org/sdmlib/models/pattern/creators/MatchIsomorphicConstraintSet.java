package org.sdmlib.models.pattern.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.pattern.MatchIsomorphicConstraint;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.booleanList;

public class MatchIsomorphicConstraintSet extends LinkedHashSet<MatchIsomorphicConstraint>
{
   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (MatchIsomorphicConstraint obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public MatchIsomorphicConstraintSet withModifier(String value)
   {
      for (MatchIsomorphicConstraint obj : this)
      {
         obj.withModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (MatchIsomorphicConstraint obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public MatchIsomorphicConstraintSet withHasMatch(boolean value)
   {
      for (MatchIsomorphicConstraint obj : this)
      {
         obj.withHasMatch(value);
      }
      
      return this;
   }

   public booleanList getDoAllMatches()
   {
      booleanList result = new booleanList();
      
      for (MatchIsomorphicConstraint obj : this)
      {
         result.add(obj.getDoAllMatches());
      }
      
      return result;
   }

   public MatchIsomorphicConstraintSet withDoAllMatches(boolean value)
   {
      for (MatchIsomorphicConstraint obj : this)
      {
         obj.withDoAllMatches(value);
      }
      
      return this;
   }

   public StringList getPatternObjectName()
   {
      StringList result = new StringList();
      
      for (MatchIsomorphicConstraint obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }

   public MatchIsomorphicConstraintSet withPatternObjectName(String value)
   {
      for (MatchIsomorphicConstraint obj : this)
      {
         obj.withPatternObjectName(value);
      }
      
      return this;
   }

}



