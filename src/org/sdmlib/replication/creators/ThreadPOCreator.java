package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class ThreadPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new ThreadPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ThreadPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ThreadPO) target).set(attrName, value);
   }
}

