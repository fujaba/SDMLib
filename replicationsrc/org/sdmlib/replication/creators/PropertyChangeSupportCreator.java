package org.sdmlib.replication.creators;

import org.sdmlib.replication.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import java.beans.PropertyChangeSupport;

public class PropertyChangeSupportCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {};

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
      return null;
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return false;
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   // ==========================================================================

   @Override
   public void removeObject(Object entity)
   {
      // wrapped object has no removeYou method
   }
}
