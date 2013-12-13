package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class MatchPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new MatchPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((MatchPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((MatchPO) target).set(attrName, value);
   }
}

