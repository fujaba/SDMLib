package org.sdmlib.examples.studyright.creators;

import org.sdmlib.examples.studyright.Professor;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class ProfessorCreator implements EntityFactory
{
   private final String[] properties = new String[]
   {
      Professor.PROPERTY_NAME,
      Professor.PROPERTY_TOPIC,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Professor();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Professor) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Professor) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Professor) entity).removeYou();
   }
}


