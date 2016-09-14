package org.sdmlib.storyboards.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.storyboards.StoryboardImpl;
import org.sdmlib.storyboards.StoryboardStep;

import de.uniks.networkparser.IdMap;

@SuppressWarnings("deprecation")
public class StoryboardCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      StoryboardImpl.PROPERTY_STORYBOARDSTEPS,
      StoryboardImpl.PROPERTY_WALL,
      StoryboardImpl.PROPERTY_ROOTDIR,
      StoryboardImpl.PROPERTY_STEPCOUNTER,
      StoryboardImpl.PROPERTY_STEPDONECOUNTER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new StoryboardImpl(null);
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (StoryboardImpl.PROPERTY_ROOTDIR.equalsIgnoreCase(attrName))
      {
         return ((StoryboardImpl) target).getRootDir();
      }

      if (StoryboardImpl.PROPERTY_STEPCOUNTER.equalsIgnoreCase(attrName))
      {
         return ((StoryboardImpl) target).getStepCounter();
      }

      if (StoryboardImpl.PROPERTY_STEPDONECOUNTER.equalsIgnoreCase(attrName))
      {
         return ((StoryboardImpl) target).getStepDoneCounter();
      }

//      if (Storyboard.PROPERTY_WALL.equalsIgnoreCase(attrName))
//      {
//         return ((Storyboard) target).getWall();
//      }

      if (StoryboardImpl.PROPERTY_STORYBOARDSTEPS.equalsIgnoreCase(attrName))
      {
         return ((StoryboardImpl) target).getStoryboardSteps();
      }

      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (IdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (StoryboardImpl.PROPERTY_ROOTDIR.equalsIgnoreCase(attrName))
      {
         ((StoryboardImpl) target).setRootDir((String) value);
         return true;
      }

      if (StoryboardImpl.PROPERTY_STEPCOUNTER.equalsIgnoreCase(attrName))
      {
         ((StoryboardImpl) target).setStepCounter(Integer.parseInt(value.toString()));
         return true;
      }

      if (StoryboardImpl.PROPERTY_STEPDONECOUNTER.equalsIgnoreCase(attrName))
      {
         ((StoryboardImpl) target).setStepDoneCounter(Integer.parseInt(value.toString()));
         return true;
      }

//      if (Storyboard.PROPERTY_WALL.equalsIgnoreCase(attrName))
//      {
//         ((Storyboard) target).setWall((StoryboardWall) value);
//         return true;
//      }

      if (StoryboardImpl.PROPERTY_STORYBOARDSTEPS.equalsIgnoreCase(attrName))
      {
         ((StoryboardImpl) target).addToStoryboardSteps((StoryboardStep) value);
         return true;
      }
      
      if ((StoryboardImpl.PROPERTY_STORYBOARDSTEPS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((StoryboardImpl) target).removeFromStoryboardSteps((StoryboardStep) value);
         return true;
      }

      return super.setValue(target, attrName, value, type);
   }
   
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((StoryboardImpl) entity).removeYou();
   }
}





