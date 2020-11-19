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
import org.sdmlib.test.examples.studyrightWithAssignments.model.TeachingAssistant;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.AggregatedEntityCreator;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class RoomCreator implements AggregatedEntityCreator
{
   public static final RoomCreator it = new RoomCreator();
   
   private final String[] properties = new String[]
   {
      Room.PROPERTY_CREDITS,
      Room.PROPERTY_NAME,
      Room.PROPERTY_TOPIC,
      Room.PROPERTY_DOORS,
      Room.PROPERTY_STUDENTS,
      Room.PROPERTY_ASSIGNMENTS,
      Room.PROPERTY_TAS,
      Room.PROPERTY_UNIVERSITY,
   };
   
   private final String[] upProperties = new String[]
   {
      Room.PROPERTY_UNIVERSITY,
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
      return new Room();
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

      if (Room.PROPERTY_CREDITS.equalsIgnoreCase(attribute))
      {
         return ((Room) target).getCredits();
      }

      if (Room.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Room) target).getName();
      }

      if (Room.PROPERTY_TOPIC.equalsIgnoreCase(attribute))
      {
         return ((Room) target).getTopic();
      }

      if (Room.PROPERTY_DOORS.equalsIgnoreCase(attribute))
      {
         return ((Room) target).getDoors();
      }

      if (Room.PROPERTY_STUDENTS.equalsIgnoreCase(attribute))
      {
         return ((Room) target).getStudents();
      }

      if (Room.PROPERTY_ASSIGNMENTS.equalsIgnoreCase(attribute))
      {
         return ((Room) target).getAssignments();
      }

      if (Room.PROPERTY_TAS.equalsIgnoreCase(attribute))
      {
         return ((Room) target).getTas();
      }

      if (Room.PROPERTY_UNIVERSITY.equalsIgnoreCase(attribute))
      {
         return ((Room) target).getUniversity();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Room.PROPERTY_TOPIC.equalsIgnoreCase(attrName))
      {
         ((Room) target).setTopic((String) value);
         return true;
      }

      if (Room.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Room) target).setName((String) value);
         return true;
      }

      if (Room.PROPERTY_CREDITS.equalsIgnoreCase(attrName))
      {
         ((Room) target).setCredits(Integer.parseInt(value.toString()));
         return true;
      }

      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {
           ((Room)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Room.PROPERTY_DOORS.equalsIgnoreCase(attrName))
      {
         ((Room) target).withDoors((Room) value);
         return true;
      }
      
      if ((Room.PROPERTY_DOORS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Room) target).withoutDoors((Room) value);
         return true;
      }

      if (Room.PROPERTY_STUDENTS.equalsIgnoreCase(attrName))
      {
         ((Room) target).withStudents((Student) value);
         return true;
      }
      
      if ((Room.PROPERTY_STUDENTS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Room) target).withoutStudents((Student) value);
         return true;
      }

      if (Room.PROPERTY_ASSIGNMENTS.equalsIgnoreCase(attrName))
      {
         ((Room) target).withAssignments((Assignment) value);
         return true;
      }
      
      if ((Room.PROPERTY_ASSIGNMENTS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Room) target).withoutAssignments((Assignment) value);
         return true;
      }

      if (Room.PROPERTY_TAS.equalsIgnoreCase(attrName))
      {
         ((Room) target).withTas((TeachingAssistant) value);
         return true;
      }
      
      if ((Room.PROPERTY_TAS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Room) target).withoutTas((TeachingAssistant) value);
         return true;
      }

      if (Room.PROPERTY_UNIVERSITY.equalsIgnoreCase(attrName))
      {
         ((Room) target).setUniversity((University) value);
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
      ((Room) entity).removeYou();
   }
}
