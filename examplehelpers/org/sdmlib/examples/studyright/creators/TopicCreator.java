package org.sdmlib.examples.studyright.creators;

import org.sdmlib.examples.studyright.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.studyright.Topic;

public class TopicCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Topic.PROPERTY_TITLE,
      Topic.PROPERTY_PROF,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Topic();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Topic) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Topic) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Topic) entity).removeYou();
   }
}

