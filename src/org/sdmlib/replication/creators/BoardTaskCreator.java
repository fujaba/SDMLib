package org.sdmlib.replication.creators;

import org.sdmlib.replication.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.Task;

public class BoardTaskCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      BoardTask.PROPERTY_NAME,
      Task.PROPERTY_LOGENTRIES,
      BoardTask.PROPERTY_LANE,
      BoardTask.PROPERTY_STATUS,
      BoardTask.PROPERTY_NEXT,
      BoardTask.PROPERTY_PREV,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new BoardTask();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((BoardTask) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((BoardTask) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((BoardTask) entity).removeYou();
   }
}


