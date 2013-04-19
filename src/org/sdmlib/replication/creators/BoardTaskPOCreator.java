package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class BoardTaskPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new BoardTaskPO();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((BoardTaskPO) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((BoardTaskPO) target).set(attrName, value);
   }
}

