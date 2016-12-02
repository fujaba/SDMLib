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
   
package org.sdmlib.test.examples.studyrightWithAssignments.model.util;

import org.sdmlib.test.examples.studyrightWithAssignments.model.Assignment;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.test.examples.studyrightWithAssignments.model.TeachingAssistant;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class TeachingAssistantCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      TeachingAssistant.PROPERTY_CERTIFIED,
      Student.PROPERTY_NAME,
      Student.PROPERTY_ID,
      Student.PROPERTY_ASSIGNMENTPOINTS,
      Student.PROPERTY_MOTIVATION,
      Student.PROPERTY_CREDITS,
      Student.PROPERTY_UNIVERSITY,
      Student.PROPERTY_IN,
      Student.PROPERTY_FRIENDS,
      Student.PROPERTY_DONE,
      TeachingAssistant.PROPERTY_ROOM,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new TeachingAssistant();
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

      if (TeachingAssistant.PROPERTY_CERTIFIED.equalsIgnoreCase(attribute))
      {
         return ((TeachingAssistant) target).isCertified();
      }

      if (Student.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Student) target).getName();
      }

      if (Student.PROPERTY_ID.equalsIgnoreCase(attribute))
      {
         return ((Student) target).getId();
      }

      if (Student.PROPERTY_ASSIGNMENTPOINTS.equalsIgnoreCase(attribute))
      {
         return ((Student) target).getAssignmentPoints();
      }

      if (Student.PROPERTY_MOTIVATION.equalsIgnoreCase(attribute))
      {
         return ((Student) target).getMotivation();
      }

      if (Student.PROPERTY_CREDITS.equalsIgnoreCase(attribute))
      {
         return ((Student) target).getCredits();
      }

      if (TeachingAssistant.PROPERTY_UNIVERSITY.equalsIgnoreCase(attribute))
      {
         return ((TeachingAssistant) target).getUniversity();
      }

      if (TeachingAssistant.PROPERTY_IN.equalsIgnoreCase(attribute))
      {
         return ((TeachingAssistant) target).getIn();
      }

      if (TeachingAssistant.PROPERTY_FRIENDS.equalsIgnoreCase(attribute))
      {
         return ((TeachingAssistant) target).getFriends();
      }

      if (TeachingAssistant.PROPERTY_DONE.equalsIgnoreCase(attribute))
      {
         return ((TeachingAssistant) target).getDone();
      }

      if (TeachingAssistant.PROPERTY_ROOM.equalsIgnoreCase(attribute))
      {
         return ((TeachingAssistant) target).getRoom();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Student.PROPERTY_CREDITS.equalsIgnoreCase(attrName))
      {
         ((Student) target).setCredits(Integer.parseInt(value.toString()));
         return true;
      }

      if (Student.PROPERTY_MOTIVATION.equalsIgnoreCase(attrName))
      {
         ((Student) target).setMotivation(Integer.parseInt(value.toString()));
         return true;
      }

      if (Student.PROPERTY_ASSIGNMENTPOINTS.equalsIgnoreCase(attrName))
      {
         ((Student) target).setAssignmentPoints(Integer.parseInt(value.toString()));
         return true;
      }

      if (Student.PROPERTY_ID.equalsIgnoreCase(attrName))
      {
         ((Student) target).setId((String) value);
         return true;
      }

      if (Student.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Student) target).setName((String) value);
         return true;
      }

      if (TeachingAssistant.PROPERTY_CERTIFIED.equalsIgnoreCase(attrName))
      {
         ((TeachingAssistant) target).setCertified((Boolean) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (TeachingAssistant.PROPERTY_UNIVERSITY.equalsIgnoreCase(attrName))
      {
         ((TeachingAssistant) target).setUniversity((University) value);
         return true;
      }

      if (TeachingAssistant.PROPERTY_IN.equalsIgnoreCase(attrName))
      {
         ((TeachingAssistant) target).setIn((Room) value);
         return true;
      }

      if (TeachingAssistant.PROPERTY_FRIENDS.equalsIgnoreCase(attrName))
      {
         ((TeachingAssistant) target).withFriends((Student) value);
         return true;
      }
      
      if ((TeachingAssistant.PROPERTY_FRIENDS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((TeachingAssistant) target).withoutFriends((Student) value);
         return true;
      }

      if (TeachingAssistant.PROPERTY_DONE.equalsIgnoreCase(attrName))
      {
         ((TeachingAssistant) target).withDone((Assignment) value);
         return true;
      }
      
      if ((TeachingAssistant.PROPERTY_DONE + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((TeachingAssistant) target).withoutDone((Assignment) value);
         return true;
      }

      if (TeachingAssistant.PROPERTY_ROOM.equalsIgnoreCase(attrName))
      {
         ((TeachingAssistant) target).setRoom((Room) value);
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
      ((TeachingAssistant) entity).removeYou();
   }
}
