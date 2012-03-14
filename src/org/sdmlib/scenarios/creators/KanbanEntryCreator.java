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

import org.sdmlib.json.JsonCreator;
import org.sdmlib.json.JsonIdMap;
import org.sdmlib.scenarios.KanbanEntry;


public class KanbanEntryCreator implements JsonCreator {
   private final String[] attribute = new  String[] {
                  KanbanEntry.PROPERTY_NAME,
                  KanbanEntry.PROPERTY_PHASE,
                  KanbanEntry.PROPERTY_LAST_DEVELOPER,
                  KanbanEntry.PROPERTY_HOURS_REMAINING,
                  KanbanEntry.PROPERTY_HOURS_SPEND,
                  KanbanEntry.PROPERTY_FILES
            };
   private final HashSet <String> toManyFields = new HashSet<String>(Arrays.asList(new String[] {
                  KanbanEntry.PROPERTY_SUBENTRIES,
                  KanbanEntry.PROPERTY_PHASE_ENTRIES
            }));
   private final String[] reference = new String[] {
                  KanbanEntry.PROPERTY_PARENT
            };
   private final String[] aggregationen = new  String[] {
                  KanbanEntry.PROPERTY_SUBENTRIES,
                  KanbanEntry.PROPERTY_PHASE_ENTRIES
            };
   @Override
   public String[] getAttributesTypes () 
   {
      return attribute;
             	 }
   @Override
   public String[] getAggregationTypes () 
   {
      return aggregationen;
            	 }
   @Override
   public String[] getReferenceTypes () 
   {
      return reference;
            	 }
   @Override
   public Object newInstance () 
   {
      return new KanbanEntry();
            	 }
   @Override
   public String getClassName () 
   {
      return "org.sdmlib.scenarios.KanbanEntry";
             	 }
   @Override
   public boolean isToManyField (String fieldName) 
   {
      return toManyFields.contains(fieldName);
            	 }
   @Override
   public Object get (Object target, String attribute) 
   {
      return ((KanbanEntry)target).get(attribute);
            }
   @Override
   public boolean set (Object target, String attribute, Object value) 
   {
      return ((KanbanEntry)target).set(attribute, value);
            }
   public static JsonIdMap createIdMap(String sessionID) {
      JsonIdMap jsonIdMap = new JsonIdMap(sessionID);
            		
         jsonIdMap.addCreater(new KanbanEntryCreator());
         jsonIdMap.addCreater(new LogEntryCreator());
         jsonIdMap.addCreater(new PhaseEntryCreator());
            		 
            		return jsonIdMap;
            	 }
   
   }
