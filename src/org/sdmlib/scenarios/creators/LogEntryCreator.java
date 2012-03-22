/*
   Copyright (c) 2012 Albert Zündorf

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

package org.sdmlib.scenarios.creators;

import java.util.Arrays;
import java.util.HashSet;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.scenarios.LogEntry;


public class LogEntryCreator implements SendableEntityCreator {
   private final String[] attributes = new  String[] {
         LogEntry.PROPERTY_DATE     ,
         LogEntry.PROPERTY_HOURS_SPEND     ,
         LogEntry.PROPERTY_HOURS_REMAINING_IN_PHASE     ,
         LogEntry.PROPERTY_HOURS_REMAINING_IN_TOTAL     ,
         LogEntry.PROPERTY_DEVELOPER     ,
         LogEntry.PROPERTY_PHASE   ,
         LogEntry.PROPERTY_COMMENT,
         LogEntry.PROPERTY_KANBANENTRY            
      };

   @Override
   public Object getValue (Object target, String attribute) 
   {
      return ((LogEntry)target).get(attribute);
   }
   
   @Override
   public boolean setValue (Object target, String attribute, Object value) 
   {
      return ((LogEntry)target).set(attribute, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);

      jsonIdMap.addCreator(new KanbanEntryCreator());
      jsonIdMap.addCreator(new LogEntryCreator());
      jsonIdMap.addCreator(new PhaseEntryCreator());

      return jsonIdMap;
   }
   
   @Override
   public String[] getProperties()
   {
      return attributes;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new LogEntry();
   }
}
