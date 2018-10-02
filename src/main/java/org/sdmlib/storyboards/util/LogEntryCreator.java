/*
   Copyright (c) 2018 zuendorf
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.storyboards.util;

import org.sdmlib.storyboards.Goal;
import org.sdmlib.storyboards.LogEntry;
import org.sdmlib.storyboards.MikadoLog;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.AggregatedEntityCreator;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class LogEntryCreator implements AggregatedEntityCreator
{
   public static final LogEntryCreator it = new LogEntryCreator();
   
   private final String[] properties = new String[]
   {
      LogEntry.PROPERTY_DATE,
      LogEntry.PROPERTY_HOURSDONE,
      LogEntry.PROPERTY_HOURSREMAINING,
      LogEntry.PROPERTY_GOAL,
      LogEntry.PROPERTY_PARENT,
   };
   
   private final String[] upProperties = new String[]
   {
   };
   
   private final String[] downProperties = new String[]
   {
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public String[] getUpProperties()
   {
      return upProperties;
   }
   
   @Override
   public String[] getDownProperties()
   {
      return downProperties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new LogEntry();
   }
   
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (LogEntry.PROPERTY_DATE.equalsIgnoreCase(attribute))
      {
         return ((LogEntry) target).getDate();
      }

      if (LogEntry.PROPERTY_HOURSDONE.equalsIgnoreCase(attribute))
      {
         return ((LogEntry) target).getHoursDone();
      }

      if (LogEntry.PROPERTY_HOURSREMAINING.equalsIgnoreCase(attribute))
      {
         return ((LogEntry) target).getHoursRemaining();
      }

      if (LogEntry.PROPERTY_GOAL.equalsIgnoreCase(attribute))
      {
         return ((LogEntry) target).getGoal();
      }

      if (LogEntry.PROPERTY_PARENT.equalsIgnoreCase(attribute))
      {
         return ((LogEntry) target).getParent();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (LogEntry.PROPERTY_HOURSREMAINING.equalsIgnoreCase(attrName))
      {
         ((LogEntry) target).setHoursRemaining(Double.parseDouble(value.toString()));
         return true;
      }

      if (LogEntry.PROPERTY_HOURSDONE.equalsIgnoreCase(attrName))
      {
         ((LogEntry) target).setHoursDone(Double.parseDouble(value.toString()));
         return true;
      }

      if (LogEntry.PROPERTY_DATE.equalsIgnoreCase(attrName))
      {
         ((LogEntry) target).setDate((String) value);
         return true;
      }

      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {
           ((LogEntry)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (LogEntry.PROPERTY_GOAL.equalsIgnoreCase(attrName))
      {
         ((LogEntry) target).setGoal((Goal) value);
         return true;
      }

      if (LogEntry.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         ((LogEntry) target).setParent((MikadoLog) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.storyboards.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((LogEntry) entity).removeYou();
   }
}
