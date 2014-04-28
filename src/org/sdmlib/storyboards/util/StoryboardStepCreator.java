package org.sdmlib.storyboards.util;

import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.storyboards.StoryboardStep;

public class StoryboardStepCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      StoryboardStep.PROPERTY_TEXT,
      StoryboardStep.PROPERTY_STORYBOARD,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new StoryboardStep();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((StoryboardStep) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((StoryboardStep) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((StoryboardStep) entity).removeYou();
   }
}

