package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class SharedSpacePOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new SharedSpacePO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((SharedSpacePO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((SharedSpacePO) target).set(attrName, value);
   }
}

