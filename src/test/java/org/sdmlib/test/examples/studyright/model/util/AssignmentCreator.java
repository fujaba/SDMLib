/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.test.examples.studyright.model.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.test.examples.studyright.model.Assignment;
import org.sdmlib.test.examples.studyright.model.Room;
import org.sdmlib.test.examples.studyright.model.Student;

import de.uniks.networkparser.json.JsonIdMap;

public class AssignmentCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Assignment.PROPERTY_NAME,
      Assignment.PROPERTY_POINTS,
      Assignment.PROPERTY_ASSIGNMENTS,
      Assignment.PROPERTY_STUDENTS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Assignment();
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

      if (Assignment.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Assignment) target).getName();
      }

      if (Assignment.PROPERTY_POINTS.equalsIgnoreCase(attribute))
      {
         return ((Assignment) target).getPoints();
      }

      if (Assignment.PROPERTY_ASSIGNMENTS.equalsIgnoreCase(attribute))
      {
         return ((Assignment) target).getAssignments();
      }

      if (Assignment.PROPERTY_STUDENTS.equalsIgnoreCase(attribute))
      {
         return ((Assignment) target).getStudents();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Assignment.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Assignment) target).setName((String) value);
         return true;
      }

      if (Assignment.PROPERTY_POINTS.equalsIgnoreCase(attrName))
      {
         ((Assignment) target).setPoints(Integer.parseInt(value.toString()));
         return true;
      }

      if (Assignment.PROPERTY_ASSIGNMENTS.equalsIgnoreCase(attrName))
      {
         ((Assignment) target).addToAssignments((Room) value);
         return true;
      }
      
      if ((Assignment.PROPERTY_ASSIGNMENTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Assignment) target).removeFromAssignments((Room) value);
         return true;
      }

      if (Assignment.PROPERTY_STUDENTS.equalsIgnoreCase(attrName))
      {
         ((Assignment) target).setStudents((Student) value);
         return true;
      }
      
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Assignment) entity).removeYou();
   }
}
