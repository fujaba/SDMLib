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
import org.sdmlib.test.examples.studyright.model.Room;
import org.sdmlib.test.examples.studyright.model.Student;
import org.sdmlib.test.examples.studyright.model.University;

import de.uniks.networkparser.IdMap;

public class UniversityCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      University.PROPERTY_NAME,
      University.PROPERTY_ROOMS,
      University.PROPERTY_STUDENTS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
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

      if (University.PROPERTY_ROOMS.equalsIgnoreCase(attribute))
      {
         return ((University) target).getRooms();
      }

      if (University.PROPERTY_STUDENTS.equalsIgnoreCase(attribute))
      {
         return ((University) target).getStudents();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (University.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((University) target).setName((String) value);
         return true;
      }

      if (University.PROPERTY_ROOMS.equalsIgnoreCase(attrName))
      {
         ((University) target).addToRooms((Room) value);
         return true;
      }
      
      if ((University.PROPERTY_ROOMS + REMOVE).equalsIgnoreCase(attrName))
      {
         ((University) target).removeFromRooms((Room) value);
         return true;
      }

      if (University.PROPERTY_STUDENTS.equalsIgnoreCase(attrName))
      {
         ((University) target).addToStudents((Student) value);
         return true;
      }
      
      if ((University.PROPERTY_STUDENTS + REMOVE).equalsIgnoreCase(attrName))
      {
         ((University) target).removeFromStudents((Student) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((University) entity).removeYou();
   }
}
