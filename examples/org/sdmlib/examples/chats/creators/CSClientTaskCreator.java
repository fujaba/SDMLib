package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.CSClientTask;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class CSClientTaskCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      TaskFlow.PROPERTY_TASKNO,
      CSClientTask.PROPERTY_PARENT,
      TaskFlow.PROPERTY_IDMAP,
      TaskFlow.PROPERTY_SUBFLOW,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return null;
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((CSClientTask) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((CSClientTask) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((CSClientTask) entity).removeYou();
   }
}



