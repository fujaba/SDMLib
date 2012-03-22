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

import org.sdmlib.scenarios.PhaseEntry;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;


public class PhaseEntryCreator implements SendableEntityCreator
{
   private final String[] attributes = new String[]
   { 
         PhaseEntry.PROPERTY_PHASE, PhaseEntry.PROPERTY_DEVELOPER,
         PhaseEntry.PROPERTY_PLANNED_START, PhaseEntry.PROPERTY_PLANNED_END,
         PhaseEntry.PROPERTY_ACTUAL_START, PhaseEntry.PROPERTY_ACTUAL_END,
         PhaseEntry.PROPERTY_HOURS_SPEND,
         PhaseEntry.PROPERTY_HOURS_REMAINING_IN_PHASE,
         PhaseEntry.PROPERTY_HOURS_PLANNED,
         PhaseEntry.PROPERTY_HOURS_REMAINING_IN_TOTAL,
         PhaseEntry.PROPERTY_KANBAN_ENTRY,  
   };

   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new PhaseEntry();
   }

   @Override
   public Object getValue(Object target, String attribute)
   {
      return ((PhaseEntry)target).get(attribute);
   }

   @Override
   public boolean setValue(Object target, String attribute, Object value)
   {
      return ((PhaseEntry)target).set(attribute, value);
   }


   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);

      jsonIdMap.addCreator(new LogEntryCreator());
      jsonIdMap.addCreator(new PhaseEntryCreator());

      return jsonIdMap;
   }

   @Override
   public String[] getProperties()
   {
      return attributes;
   }
}
