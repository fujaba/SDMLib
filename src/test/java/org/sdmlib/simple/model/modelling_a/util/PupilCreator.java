/*
   Copyright (c) 2016 zuendorf
   
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
   
package org.sdmlib.simple.model.modelling_a.util;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.simple.model.modelling_a.Pupil;
import org.sdmlib.simple.model.modelling_a.Person;
import org.sdmlib.simple.model.modelling_a.Room;
import org.sdmlib.simple.model.modelling_a.Teacher;

public class PupilCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Pupil.PROPERTY_CREDITS,
      Person.PROPERTY_NAME,
      Person.PROPERTY_AGE,
      Person.PROPERTY_ROOM,
      Pupil.PROPERTY_CURRENTROOM,
      Pupil.PROPERTY_TEACHER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Pupil();
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

      if (Pupil.PROPERTY_CREDITS.equalsIgnoreCase(attribute))
      {
         return ((Pupil) target).getCredits();
      }

      if (Person.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Person) target).getName();
      }

      if (Person.PROPERTY_AGE.equalsIgnoreCase(attribute))
      {
         return ((Person) target).getAge();
      }

      if (Pupil.PROPERTY_ROOM.equalsIgnoreCase(attribute))
      {
         return ((Pupil) target).getRoom();
      }

      if (Pupil.PROPERTY_CURRENTROOM.equalsIgnoreCase(attribute))
      {
         return ((Pupil) target).getCurrentRoom();
      }

      if (Pupil.PROPERTY_TEACHER.equalsIgnoreCase(attribute))
      {
         return ((Pupil) target).getTeacher();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Person.PROPERTY_AGE.equalsIgnoreCase(attrName))
      {
         ((Person) target).withAge(Integer.parseInt(value.toString()));
         return true;
      }

      if (Person.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Person) target).withName((String) value);
         return true;
      }

      if (Pupil.PROPERTY_CREDITS.equalsIgnoreCase(attrName))
      {
         ((Pupil) target).withCredits(Integer.parseInt(value.toString()));
         return true;
      }

      if (IdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Pupil.PROPERTY_ROOM.equalsIgnoreCase(attrName))
      {
         ((Pupil) target).setRoom((Room) value);
         return true;
      }

      if (Pupil.PROPERTY_CURRENTROOM.equalsIgnoreCase(attrName))
      {
         ((Pupil) target).setCurrentRoom((Room) value);
         return true;
      }

      if (Pupil.PROPERTY_TEACHER.equalsIgnoreCase(attrName))
      {
         ((Pupil) target).setTeacher((Teacher) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.simple.model.modelling_a.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Pupil) entity).removeYou();
   }
}
