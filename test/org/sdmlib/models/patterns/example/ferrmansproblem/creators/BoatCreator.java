package org.sdmlib.models.patterns.example.ferrmansproblem.creators;

import org.sdmlib.models.patterns.example.ferrmansproblem.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.patterns.example.ferrmansproblem.Boat;

public class BoatCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Boat.PROPERTY_BANK,
      Boat.PROPERTY_CARGO,
      Boat.PROPERTY_RIVER,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Boat();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Boat) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Boat) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Boat) entity).removeYou();
   }
}


