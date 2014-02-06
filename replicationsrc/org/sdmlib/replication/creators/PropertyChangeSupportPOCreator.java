package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class PropertyChangeSupportPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new PropertyChangeSupportPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((PropertyChangeSupportPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((PropertyChangeSupportPO) target).set(attrName, value);
   }
}

