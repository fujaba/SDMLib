package org.sdmlib.replication.creators;

import org.sdmlib.replication.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.replication.SharedSpace;

public class SharedSpaceCreator extends EntityFactory
{
   private final String[] properties = new String[]
   { SharedSpace.PROPERTY_SPACEID, SharedSpace.PROPERTY_HISTORY,
         SharedSpace.PROPERTY_LASTCHANGEID, SharedSpace.PROPERTY_NODEID,
         SharedSpace.PROPERTY_NODE, SharedSpace.PROPERTY_CHANNELS, };

   public String[] getProperties()
   {
      return properties;
   }

   public Object getSendableInstance(boolean reference)
   {
      return new SharedSpace();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((SharedSpace) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((SharedSpace) target).set(attrName, value);
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   // ==========================================================================

   @Override
   public void removeObject(Object entity)
   {
      ((SharedSpace) entity).removeYou();
   }
}
