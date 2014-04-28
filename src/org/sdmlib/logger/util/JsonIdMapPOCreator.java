package org.sdmlib.logger.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

public class JsonIdMapPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new JsonIdMapPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((JsonIdMapPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((JsonIdMapPO) target).set(attrName, value);
   }
}

