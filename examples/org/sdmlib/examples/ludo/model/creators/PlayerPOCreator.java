package org.sdmlib.examples.ludo.model.creators;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class PlayerPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new PlayerPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((PlayerPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((PlayerPO) target).set(attrName, value);
   }
}

