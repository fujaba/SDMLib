package org.sdmlib.storyboards.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.storyboards.KanbanEntry;
import org.sdmlib.storyboards.LogEntryStoryBoard;

import de.uniks.networkparser.json.JsonIdMap;

public class KanbanEntryCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      KanbanEntry.PROPERTY_NAME,
      KanbanEntry.PROPERTY_PHASE,
      KanbanEntry.PROPERTY_LAST_DEVELOPER,
      KanbanEntry.PROPERTY_HOURS_REMAINING,
      KanbanEntry.PROPERTY_HOURS_SPEND,
      KanbanEntry.PROPERTY_PARENT,
      KanbanEntry.PROPERTY_SUBENTRIES,
      KanbanEntry.PROPERTY_LOGENTRIES,
      KanbanEntry.PROPERTY_FILES, 
      KanbanEntry.PROPERTY_OLDNOOFLOGENTRIES,
      KanbanEntry.PROPERTY_PHASES
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new KanbanEntry();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos=attrName.indexOf(".");
      String attribute = attrName;

      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }
      if (KanbanEntry.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((KanbanEntry)target).getName();
      }

      if (KanbanEntry.PROPERTY_LOGENTRIES.equalsIgnoreCase(attrName))
      {
         return ((KanbanEntry)target).getLogEntries();
      }

      if (KanbanEntry.PROPERTY_OLDNOOFLOGENTRIES.equalsIgnoreCase(attrName))
      {
         return ((KanbanEntry)target).getOldNoOfLogEntries();
      }

      if (KanbanEntry.PROPERTY_PHASES.equalsIgnoreCase(attrName))
      {
         return ((KanbanEntry)target).getPhases();
      }

      if (KanbanEntry.PROPERTY_PHASE.equalsIgnoreCase(attribute))
      {
         return ((KanbanEntry)target).getPhase();
      }
      if (KanbanEntry.PROPERTY_LAST_DEVELOPER.equalsIgnoreCase(attribute))
      {
         return ((KanbanEntry)target).getLastDeveloper();
      }
      if (KanbanEntry.PROPERTY_HOURS_REMAINING.equalsIgnoreCase(attribute))
      {
         return ((KanbanEntry)target).getHoursRemaining();
      }
      if (KanbanEntry.PROPERTY_HOURS_SPEND.equalsIgnoreCase(attribute))
      {
         return ((KanbanEntry)target).getHoursSpend();
      }
      if (KanbanEntry.PROPERTY_PARENT.equalsIgnoreCase(attribute))
      {
         if (pos > 0)
         {
            return getValue(((KanbanEntry)target).getParent(), attrName.substring(pos + 1));
         }

         return ((KanbanEntry)target).getParent();
      }
      if (KanbanEntry.PROPERTY_SUBENTRIES.equalsIgnoreCase(attribute))
      {
         return ((KanbanEntry)target).getSubentries();
      }
      if (KanbanEntry.PROPERTY_FILES.equalsIgnoreCase(attribute))
      {
         return ((KanbanEntry)target).getFiles();
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

      if (KanbanEntry.PROPERTY_NAME.equalsIgnoreCase(attrName)) 
      {
         ((KanbanEntry)target).setName((String) value);
         return true;
      } 
      if (KanbanEntry.PROPERTY_LOGENTRIES.equalsIgnoreCase(attrName))
      {
         ((KanbanEntry)target).addToLogEntries((LogEntryStoryBoard) value);
         return true;
      }
      
      if ((KanbanEntry.PROPERTY_LOGENTRIES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((KanbanEntry)target).removeFromLogEntries((LogEntryStoryBoard) value);
         return true;
      }

      if (KanbanEntry.PROPERTY_OLDNOOFLOGENTRIES.equalsIgnoreCase(attrName))
      {
         ((KanbanEntry)target).setOldNoOfLogEntries(Integer.parseInt(value.toString()));
         return true;
      }

      if (KanbanEntry.PROPERTY_PHASES.equalsIgnoreCase(attrName))
      {
         ((KanbanEntry)target).setPhases((String) value);
         return true;
      }

      if (KanbanEntry.PROPERTY_PHASE.equalsIgnoreCase(attrName)) 
      {
         ((KanbanEntry)target).setPhase((String) value);
         return true;
      } 
      if (KanbanEntry.PROPERTY_LAST_DEVELOPER.equalsIgnoreCase(attrName)) 
      {
         ((KanbanEntry)target).setLastDeveloper((String) value);
         return true;
      } 
      if (KanbanEntry.PROPERTY_HOURS_REMAINING.equalsIgnoreCase(attrName)) 
      {
         ((KanbanEntry)target).setHoursRemaining(Double.parseDouble(value.toString()));
         return true;
      } 
      if (KanbanEntry.PROPERTY_HOURS_SPEND.equalsIgnoreCase(attrName)) 
      {
         ((KanbanEntry)target).setHoursSpend(Double.parseDouble(value.toString()));
         return true;
      } 
      if (KanbanEntry.PROPERTY_PARENT.equalsIgnoreCase(attrName)) 
      {
         ((KanbanEntry)target).setParent((KanbanEntry) value);
         return true;
      } 
      if (KanbanEntry.PROPERTY_SUBENTRIES.equalsIgnoreCase(attrName)) 
      {
         ((KanbanEntry)target).addToSubentries((KanbanEntry) value);
         return true;
      } 
      if (KanbanEntry.PROPERTY_FILES.equalsIgnoreCase(attrName)) 
      {
         ((KanbanEntry)target).setFiles((String) value);
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
      ((KanbanEntry) entity).removeYou();
   }
}








