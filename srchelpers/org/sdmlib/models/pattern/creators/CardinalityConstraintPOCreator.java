package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class CardinalityConstraintPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new CardinalityConstraintPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((CardinalityConstraintPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((CardinalityConstraintPO) target).set(attrName, value);
   }
}

