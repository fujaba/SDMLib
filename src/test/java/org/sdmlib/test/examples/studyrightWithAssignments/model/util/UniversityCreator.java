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

import de.uniks.networkparser.interfaces.AggregatedEntityCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.test.examples.studyrightWithAssignments.model.President;

public class UniversityCreator implements AggregatedEntityCreator
{
   public static final UniversityCreator it = new UniversityCreator();
   
   private final String[] properties = new String[]
   {
      University.PROPERTY_NAME,
      University.PROPERTY_STUDENTS,
      University.PROPERTY_ROOMS,
      University.PROPERTY_PRESIDENT,
   };
   
   private final String[] upProperties = new String[]
   {
   };
   
   private final String[] downProperties = new String[]
   {
      University.PROPERTY_ROOMS,
      University.PROPERTY_PRESIDENT,
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
      return new University();
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

      if (University.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((University) target).getName();
      }

      if (University.PROPERTY_STUDENTS.equalsIgnoreCase(attribute))
      {
         return ((University) target).getStudents();
      }

      if (University.PROPERTY_ROOMS.equalsIgnoreCase(attribute))
      {
         return ((University) target).getRooms();
      }

      if (University.PROPERTY_PRESIDENT.equalsIgnoreCase(attribute))
      {
         return ((University) target).getPresident();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (University.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((University) target).setName((String) value);
         return true;
      }

      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {
           ((University)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (University.PROPERTY_STUDENTS.equalsIgnoreCase(attrName))
      {
         ((University) target).withStudents((Student) value);
         return true;
      }
      
      if ((University.PROPERTY_STUDENTS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((University) target).withoutStudents((Student) value);
         return true;
      }

      if (University.PROPERTY_ROOMS.equalsIgnoreCase(attrName))
      {
         ((University) target).withRooms((Room) value);
         return true;
      }
      
      if ((University.PROPERTY_ROOMS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((University) target).withoutRooms((Room) value);
         return true;
      }

      if (University.PROPERTY_PRESIDENT.equalsIgnoreCase(attrName))
      {
         ((University) target).setPresident((President) value);
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
      ((University) entity).removeYou();
   }
}
