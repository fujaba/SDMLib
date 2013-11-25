package org.sdmlib.model.test.consistence.creators;

import org.sdmlib.model.test.consistence.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.model.test.consistence.Border;

public class BorderCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Border.PROPERTY_BORDERLOCATION,
      Border.PROPERTY_CONNECTEDTO,
      Border.PROPERTY_CONNECTEDTOREV,
      Border.PROPERTY_SERVERID,
      Border.PROPERTY_ROAD,
      Border.PROPERTY_FIELD,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Border();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Border) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Border) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Border) entity).removeYou();
   }
}

