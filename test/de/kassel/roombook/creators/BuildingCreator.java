package de.kassel.roombook.creators;

import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

import de.kassel.roombook.Building;

public class BuildingCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Building.PROPERTY_ID,
      Building.PROPERTY_HAS,
      Building.PROPERTY_NAME,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Building();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Building) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Building) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Building) entity).removeYou();
   }
}



