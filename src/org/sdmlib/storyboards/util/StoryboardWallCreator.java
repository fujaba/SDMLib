package org.sdmlib.storyboards.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardWall;

import de.uniks.networkparser.json.JsonIdMap;

public class StoryboardWallCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      StoryboardWall.PROPERTY_STORYBOARD,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new StoryboardWall();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (StoryboardWall.PROPERTY_STORYBOARD.equalsIgnoreCase(attrName))
      {
         return ((StoryboardWall)target).getStoryboard();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (StoryboardWall.PROPERTY_STORYBOARD.equalsIgnoreCase(attrName))
      {
         ((StoryboardWall)target).setStoryboard((Storyboard) value);
         return true;
      }
      return super.setValue(target, attrName, value, type);
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

