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
   
package org.sdmlib.test.examples.studyrightWithAssignments.model.util;

import org.sdmlib.test.examples.studyrightWithAssignments.model.Assignment;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.AggregatedEntityCreator;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class AssignmentCreator implements AggregatedEntityCreator
{
   public static final AssignmentCreator it = new AssignmentCreator();
   
   private final String[] properties = new String[]
   {
      Assignment.PROPERTY_CONTENT,
      Assignment.PROPERTY_POINTS,
      Assignment.PROPERTY_ROOM,
      Assignment.PROPERTY_STUDENTS,
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
      if (Assignment.PROPERTY_POINTS.equalsIgnoreCase(attrName))
      {
         ((Assignment) target).setPoints(Integer.parseInt(value.toString()));
         return true;
      }

      if (Assignment.PROPERTY_CONTENT.equalsIgnoreCase(attrName))
      {
         ((Assignment) target).setContent((String) value);
         return true;
      }

      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {
           ((Assignment)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
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
      
      if ((Assignment.PROPERTY_STUDENTS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Assignment) target).withoutStudents((Student) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.studyrightWithAssignments.model.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Assignment) entity).removeYou();
   }
}
