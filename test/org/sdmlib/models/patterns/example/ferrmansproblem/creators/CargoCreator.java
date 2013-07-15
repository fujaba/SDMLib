package org.sdmlib.models.patterns.example.ferrmansproblem.creators;

import org.sdmlib.models.patterns.example.ferrmansproblem.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.patterns.example.ferrmansproblem.Cargo;

public class CargoCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Cargo.PROPERTY_NAME,
      Cargo.PROPERTY_BANK,
      Cargo.PROPERTY_BOAT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Cargo();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Cargo) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Cargo) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Cargo) entity).removeYou();
   }
}

