package org.sdmlib.model.test.superclasses.creators;

import org.sdmlib.model.test.superclasses.Continent;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class ContinentCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Continent.PROPERTY_TEST,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Continent();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Continent) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Continent) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Continent) entity).removeYou();
   }
}



