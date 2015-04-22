/*
   Copyright (c) 2015 Olaf Gunkel 
   
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
   
package org.sdmlib.examples.studyrightWithAssignments.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.studyrightWithAssignments.model.Assignment;
import org.sdmlib.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.examples.studyrightWithAssignments.model.Student;

public class AssignmentCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Assignment.PROPERTY_CONTENT,
      Assignment.PROPERTY_POINTS,
      Assignment.PROPERTY_ROOM,
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

      if (Assignment.PROPERTY_CONTENT.equalsIgnoreCase(attribute))
      {
         return ((Assignment) target).getContent();
      }

      if (Assignment.PROPERTY_POINTS.equalsIgnoreCase(attribute))
      {
         return ((Assignment) target).getPoints();
      }

      if (Assignment.PROPERTY_ROOM.equalsIgnoreCase(attribute))
      {
         return ((Assignment) target).getRoom();
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

      if (Assignment.PROPERTY_CONTENT.equalsIgnoreCase(attrName))
      {
         ((Assignment) target).withContent((String) value);
         return true;
      }

      if (Assignment.PROPERTY_POINTS.equalsIgnoreCase(attrName))
      {
         ((Assignment) target).withPoints(Integer.parseInt(value.toString()));
         return true;
      }

      if (Assignment.PROPERTY_ROOM.equalsIgnoreCase(attrName))
      {
         ((Assignment) target).setRoom((Room) value);
         return true;
      }

      if (Assignment.PROPERTY_STUDENTS.equalsIgnoreCase(attrName))
      {
         ((Assignment) target).withStudents((Student) value);
         return true;
      }
      
      if ((Assignment.PROPERTY_STUDENTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Assignment) target).withoutStudents((Student) value);
         return true;
      }
      
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return org.sdmlib.examples.studyrightWithAssignments.model.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Assignment) entity).removeYou();
   }
}
