package org.sdmlib.examples.studyrightWithAssignments.creators;

import org.sdmlib.examples.studyrightWithAssignments.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.studyrightWithAssignments.TeachingAssistant;
import org.sdmlib.examples.studyrightWithAssignments.Student;

public class TeachingAssistantCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      TeachingAssistant.PROPERTY_CERTIFIED,
      Student.PROPERTY_NAME,
      Student.PROPERTY_ID,
      Student.PROPERTY_ASSIGNMENTPOINTS,
      Student.PROPERTY_MOTIVATION,
      Student.PROPERTY_CREDITS,
      Student.PROPERTY_UNIVERSITY,
      Student.PROPERTY_IN,
      Student.PROPERTY_DONE,
      Student.PROPERTY_FRIENDS,
      TeachingAssistant.PROPERTY_ROOM,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new TeachingAssistant();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((TeachingAssistant) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }
      return ((TeachingAssistant) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((TeachingAssistant) entity).removeYou();
   }
}

