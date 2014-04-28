package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class RemoteTaskBoardPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new RemoteTaskBoardPO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((RemoteTaskBoardPO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((RemoteTaskBoardPO) target).set(attrName, value);
   }
}
