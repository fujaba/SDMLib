package de.kassel.roombook.creators;

import de.kassel.roombook.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import de.kassel.roombook.Floor;

public class FloorCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Floor.PROPERTY_LEVEL,
      Floor.PROPERTY_NAME,
      Floor.PROPERTY_GUEST,
      Floor.PROPERTY_BUILDINGS,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Floor();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Floor) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }
      return ((Floor) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Floor) entity).removeYou();
   }
}

