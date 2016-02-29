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
import org.sdmlib.test.examples.studyright.model.Lecture;
import org.sdmlib.test.examples.studyright.model.Professor;
import org.sdmlib.test.examples.studyright.model.Room;
import org.sdmlib.test.examples.studyright.model.Student;

import de.uniks.networkparser.IdMap;

public class LectureCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Lecture.PROPERTY_TITLE,
      Lecture.PROPERTY_IN,
      Lecture.PROPERTY_HAS,
      Lecture.PROPERTY_LISTEN,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Lecture();
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

      if (Lecture.PROPERTY_TITLE.equalsIgnoreCase(attribute))
      {
         return ((Lecture) target).getTitle();
      }

      if (Lecture.PROPERTY_IN.equalsIgnoreCase(attribute))
      {
         return ((Lecture) target).getIn();
      }

      if (Lecture.PROPERTY_HAS.equalsIgnoreCase(attribute))
      {
         return ((Lecture) target).getHas();
      }

      if (Lecture.PROPERTY_LISTEN.equalsIgnoreCase(attribute))
      {
         return ((Lecture) target).getListen();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (IdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Lecture.PROPERTY_TITLE.equalsIgnoreCase(attrName))
      {
         ((Lecture) target).setTitle((String) value);
         return true;
      }

      if (Lecture.PROPERTY_IN.equalsIgnoreCase(attrName))
      {
         ((Lecture) target).setIn((Room) value);
         return true;
      }

      if (Lecture.PROPERTY_HAS.equalsIgnoreCase(attrName))
      {
         ((Lecture) target).setHas((Professor) value);
         return true;
      }

      if (Lecture.PROPERTY_LISTEN.equalsIgnoreCase(attrName))
      {
         ((Lecture) target).setListen((Student) value);
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
      ((Lecture) entity).removeYou();
   }
}
