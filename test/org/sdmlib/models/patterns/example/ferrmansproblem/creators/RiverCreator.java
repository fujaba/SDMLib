package org.sdmlib.models.patterns.example.ferrmansproblem.creators;

import org.sdmlib.models.patterns.example.ferrmansproblem.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.patterns.example.ferrmansproblem.River;

public class RiverCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      River.PROPERTY_BANKS,
      River.PROPERTY_BOAT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new River();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((River) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((River) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((River) entity).removeYou();
   }
}


