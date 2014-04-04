package org.sdmlib.replication.creators;

import org.sdmlib.replication.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.replication.RemoteTaskBoard;

public class RemoteTaskBoardCreator extends EntityFactory
{
   private final String[] properties = new String[]
   { RemoteTaskBoard.PROPERTY_LANES, };

   public String[] getProperties()
   {
      return properties;
   }

   public Object getSendableInstance(boolean reference)
   {
      return new RemoteTaskBoard();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((RemoteTaskBoard) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }
      return ((RemoteTaskBoard) target).set(attrName, value);
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   // ==========================================================================

   @Override
   public void removeObject(Object entity)
   {
      ((RemoteTaskBoard) entity).removeYou();
   }
}
