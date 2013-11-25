package org.sdmlib.model.test.superclasses.creators;

import org.sdmlib.model.test.superclasses.Town;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class TownCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Town.PROPERTY_TEST,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Town();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Town) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Town) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Town) entity).removeYou();
   }
}



