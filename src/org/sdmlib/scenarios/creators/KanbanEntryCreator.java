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

import org.sdmlib.scenarios.KanbanEntry;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;


public class KanbanEntryCreator implements SendableEntityCreator 
{
   private final String[] properties = new  String[] {
         KanbanEntry.PROPERTY_SUBENTRIES,
         KanbanEntry.PROPERTY_LOGENTRIES,
         KanbanEntry.PROPERTY_PHASE_ENTRIES,
         KanbanEntry.PROPERTY_NAME,
         KanbanEntry.PROPERTY_PHASE,
         KanbanEntry.PROPERTY_LAST_DEVELOPER,
         KanbanEntry.PROPERTY_HOURS_REMAINING,
         KanbanEntry.PROPERTY_HOURS_SPEND,
         KanbanEntry.PROPERTY_FILES,
         KanbanEntry.PROPERTY_PARENT
      };

   public static JsonIdMap createIdMap(String sessionID) {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);

      jsonIdMap.addCreator(new KanbanEntryCreator());
      jsonIdMap.addCreator(new LogEntryCreator());
      jsonIdMap.addCreator(new PhaseEntryCreator());

      return jsonIdMap;
   }
   
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
   public Object getValue(Object entity, String attribute)
   {
      return ((KanbanEntry) entity).get(attribute);
   }
   
   @Override
   public boolean setValue(Object entity, String attribute, Object value)
   {
      return ((KanbanEntry) entity).set(attribute, value);
   }
   
}









