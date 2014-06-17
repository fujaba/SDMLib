package org.sdmlib.storyboards.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardStep;
import org.sdmlib.storyboards.StoryboardWall;

import de.uniks.networkparser.json.JsonIdMap;

public class StoryboardCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Storyboard.PROPERTY_STORYBOARDSTEPS,
      Storyboard.PROPERTY_WALL,
      Storyboard.PROPERTY_ROOTDIR,
      Storyboard.PROPERTY_STEPCOUNTER,
      Storyboard.PROPERTY_STEPDONECOUNTER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Storyboard(null);
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Storyboard.PROPERTY_ROOTDIR.equalsIgnoreCase(attrName))
      {
         return ((Storyboard) target).getRootDir();
      }

      if (Storyboard.PROPERTY_STEPCOUNTER.equalsIgnoreCase(attrName))
      {
         return ((Storyboard) target).getStepCounter();
      }

      if (Storyboard.PROPERTY_STEPDONECOUNTER.equalsIgnoreCase(attrName))
      {
         return ((Storyboard) target).getStepDoneCounter();
      }

      if (Storyboard.PROPERTY_WALL.equalsIgnoreCase(attrName))
      {
         return ((Storyboard) target).getWall();
      }

      if (Storyboard.PROPERTY_STORYBOARDSTEPS.equalsIgnoreCase(attrName))
      {
         return ((Storyboard) target).getStoryboardSteps();
      }

      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Storyboard.PROPERTY_ROOTDIR.equalsIgnoreCase(attrName))
      {
         ((Storyboard) target).setRootDir((String) value);
         return true;
      }

      if (Storyboard.PROPERTY_STEPCOUNTER.equalsIgnoreCase(attrName))
      {
         ((Storyboard) target).setStepCounter(Integer.parseInt(value.toString()));
         return true;
      }

      if (Storyboard.PROPERTY_STEPDONECOUNTER.equalsIgnoreCase(attrName))
      {
         ((Storyboard) target).setStepDoneCounter(Integer.parseInt(value.toString()));
         return true;
      }

      if (Storyboard.PROPERTY_WALL.equalsIgnoreCase(attrName))
      {
         ((Storyboard) target).setWall((StoryboardWall) value);
         return true;
      }

      if (Storyboard.PROPERTY_STORYBOARDSTEPS.equalsIgnoreCase(attrName))
      {
         ((Storyboard) target).addToStoryboardSteps((StoryboardStep) value);
         return true;
      }
      
      if ((Storyboard.PROPERTY_STORYBOARDSTEPS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Storyboard) target).removeFromStoryboardSteps((StoryboardStep) value);
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
      ((Storyboard) entity).removeYou();
   }
}





