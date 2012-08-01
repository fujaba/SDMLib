package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class MatchIsomorphicConstraintPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new MatchIsomorphicConstraintPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((MatchIsomorphicConstraintPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((MatchIsomorphicConstraintPO) target).set(attrName, value);
   }
}

