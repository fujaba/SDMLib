package org.sdmlib.replication.creators;

import org.sdmlib.replication.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.replication.Lane;

public class LaneCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Lane.PROPERTY_NAME,
      Lane.PROPERTY_BOARD,
      Lane.PROPERTY_TASKS,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Lane();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Lane) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Lane) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Lane) entity).removeYou();
   }
}

