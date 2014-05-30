package org.sdmlib.storyboards.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.storyboards.KanbanEntry;
import org.sdmlib.storyboards.LogEntryStoryBoard;

import de.uniks.networkparser.json.JsonIdMap;

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
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new LogEntryStoryBoard();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos=attrName.indexOf(".");
      String attribute = attrName;

      if(pos>0)
      {
         attribute=attrName.substring(0, pos);
      }

      if (LogEntryStoryBoard.PROPERTY_DATE.equalsIgnoreCase(attribute)) 
      {
         return ((LogEntryStoryBoard)target).getDate();
      }
      if (LogEntryStoryBoard.PROPERTY_KANBANENTRY.equalsIgnoreCase(attribute))
      {
         return ((LogEntryStoryBoard)target).getKanbanEntry();
      }
      if (LogEntryStoryBoard.PROPERTY_HOURS_SPEND.equalsIgnoreCase(attribute)) 
      {
         return ((LogEntryStoryBoard)target).getHoursSpend();
      }
      if (LogEntryStoryBoard.PROPERTY_HOURS_REMAINING_IN_TOTAL.equalsIgnoreCase(attribute)) 
      {
         return ((LogEntryStoryBoard)target).getHoursRemainingInTotal();
      }
      if (LogEntryStoryBoard.PROPERTY_KANBANENTRY.equalsIgnoreCase(attribute)) 
      {
         return ((LogEntryStoryBoard)target).getKanbanEntry();
      }  
      if (LogEntryStoryBoard.PROPERTY_DEVELOPER.equalsIgnoreCase(attribute)) 
      {
         return ((LogEntryStoryBoard)target).getDeveloper();
      }
      if (LogEntryStoryBoard.PROPERTY_PHASE.equalsIgnoreCase(attribute)) 
      {
         return ((LogEntryStoryBoard)target).getPhase();
      }
      if (LogEntryStoryBoard.PROPERTY_COMMENT.equalsIgnoreCase(attribute)) 
      {
         return ((LogEntryStoryBoard)target).getComment();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (LogEntryStoryBoard.PROPERTY_DATE.equalsIgnoreCase(attrName)) 
      {
         ((LogEntryStoryBoard)target).setDate((String) value);
         return true;
      }

      if (LogEntryStoryBoard.PROPERTY_KANBANENTRY.equalsIgnoreCase(attrName))
      {
         ((LogEntryStoryBoard)target).setKanbanEntry((KanbanEntry) value);
         return true;
      }
      if (LogEntryStoryBoard.PROPERTY_HOURS_SPEND.equalsIgnoreCase(attrName)) 
      {
         ((LogEntryStoryBoard)target).setHoursSpend(Double.parseDouble(value.toString()));
         return true;
      }
      if (LogEntryStoryBoard.PROPERTY_HOURS_REMAINING_IN_TOTAL.equalsIgnoreCase(attrName)) 
      {
         ((LogEntryStoryBoard)target).setHoursRemainingInTotal(Double.parseDouble(value.toString()));
         return true;
      }
      if (LogEntryStoryBoard.PROPERTY_KANBANENTRY.equalsIgnoreCase(attrName)) 
      {
         ((LogEntryStoryBoard)target).setKanbanEntry((KanbanEntry) value);
         return true;
      }
      if (LogEntryStoryBoard.PROPERTY_DEVELOPER.equalsIgnoreCase(attrName)) 
      {
         ((LogEntryStoryBoard)target).setDeveloper((String) value);
         return true;
      }
      if (LogEntryStoryBoard.PROPERTY_PHASE.equalsIgnoreCase(attrName)) 
      {
         ((LogEntryStoryBoard)target).setPhase((String) value);
         return true;
      }
      if (LogEntryStoryBoard.PROPERTY_COMMENT.equalsIgnoreCase(attrName)) 
      {
         ((LogEntryStoryBoard)target).setComment((String) value);
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
      ((LogEntryStoryBoard) entity).removeYou();
   }
}





