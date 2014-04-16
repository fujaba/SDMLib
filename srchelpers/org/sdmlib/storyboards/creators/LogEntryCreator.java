package org.sdmlib.storyboards.creators;

import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.storyboards.LogEntryStoryBoard;

public class LogEntryCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
         LogEntryStoryBoard.PROPERTY_DATE,
         LogEntryStoryBoard.PROPERTY_HOURS_SPEND,
         LogEntryStoryBoard.PROPERTY_HOURS_REMAINING_IN_TOTAL,
         LogEntryStoryBoard.PROPERTY_KANBANENTRY,
         LogEntryStoryBoard.PROPERTY_DEVELOPER,
         LogEntryStoryBoard.PROPERTY_PHASE,
         LogEntryStoryBoard.PROPERTY_COMMENT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new LogEntryStoryBoard();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((LogEntryStoryBoard) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((LogEntryStoryBoard) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((LogEntryStoryBoard) entity).removeYou();
   }
}





