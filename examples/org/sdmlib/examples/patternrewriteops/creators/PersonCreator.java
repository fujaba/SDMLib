package org.sdmlib.examples.patternrewriteops.creators;

import org.sdmlib.examples.patternrewriteops.Person;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class PersonCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Person.PROPERTY_STATION,
      Person.PROPERTY_TRAIN,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Person();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Person) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Person) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Person) entity).removeYou();
   }
}

