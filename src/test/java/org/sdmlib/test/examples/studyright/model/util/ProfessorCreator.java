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
import org.sdmlib.test.examples.studyright.model.Female;
import org.sdmlib.test.examples.studyright.model.Lecture;
import org.sdmlib.test.examples.studyright.model.Professor;
import org.sdmlib.test.examples.studyright.model.Topic;

import de.uniks.networkparser.IdMap;

public class ProfessorCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Professor.PROPERTY_PERSNR,
      Female.PROPERTY_NAME,
      Professor.PROPERTY_LECTURE,
      Professor.PROPERTY_TOPIC,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Professor();
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

      if (Professor.PROPERTY_PERSNR.equalsIgnoreCase(attribute))
      {
         return ((Professor) target).getPersNr();
      }

      if (Female.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Female) target).getName();
      }

      if (Professor.PROPERTY_LECTURE.equalsIgnoreCase(attribute))
      {
         return ((Professor) target).getLecture();
      }

      if (Professor.PROPERTY_TOPIC.equalsIgnoreCase(attribute))
      {
         return ((Professor) target).getTopic();
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

      if (Professor.PROPERTY_PERSNR.equalsIgnoreCase(attrName))
      {
         ((Professor) target).setPersNr(Integer.parseInt(value.toString()));
         return true;
      }

      if (Female.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Female) target).setName((String) value);
         return true;
      }

      if (Professor.PROPERTY_LECTURE.equalsIgnoreCase(attrName))
      {
         ((Professor) target).addToLecture((Lecture) value);
         return true;
      }
      
      if ((Professor.PROPERTY_LECTURE + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Professor) target).removeFromLecture((Lecture) value);
         return true;
      }

      if (Professor.PROPERTY_TOPIC.equalsIgnoreCase(attrName))
      {
         ((Professor) target).setTopic((Topic) value);
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
      ((Professor) entity).removeYou();
   }
}
