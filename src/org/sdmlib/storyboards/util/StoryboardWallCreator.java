package org.sdmlib.storyboards.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.storyboards.StoryboardWall;

import de.uniks.networkparser.json.JsonIdMap;

public class StoryboardWallCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      StoryboardWall.PROPERTY_STORYBOARD,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new StoryboardWall();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((StoryboardWall) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((StoryboardWall) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((StoryboardWall) entity).removeYou();
   }
}

