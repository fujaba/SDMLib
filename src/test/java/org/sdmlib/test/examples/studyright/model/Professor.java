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
   
package org.sdmlib.test.examples.studyright.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.studyright.model.util.LectureSet;

import de.uniks.networkparser.interfaces.SendableEntity;
import org.sdmlib.test.examples.studyright.model.Topic;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/studyright/StudyRightModel.java'>StudyRightModel.java</a>
*/
   public class Professor extends Female implements PropertyChangeInterface, SendableEntity
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      super.removeYou();
      withoutLecture(this.getLecture().toArray(new Lecture[this.getLecture().size()]));
      setTopic(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_PERSNR = "PersNr";
   
   private int PersNr;

   public int getPersNr()
   {
      return this.PersNr;
   }
   
   public void setPersNr(int value)
   {
      if (this.PersNr != value)
      {
         int oldValue = this.PersNr;
         this.PersNr = value;
         firePropertyChange(PROPERTY_PERSNR, oldValue, value);
      }
   }
   
   public Professor withPersNr(int value)
   {
      setPersNr(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getPersNr());
      s.append(" ").append(this.getName());
      return s.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       many
    * Professor ----------------------------------- Lecture
    *              has                   lecture
    * </pre>
    */
   
   public static final String PROPERTY_LECTURE = "lecture";

   private LectureSet lecture = null;
   
   public LectureSet getLecture()
   {
      if (this.lecture == null)
      {
         return Lecture.EMPTY_SET;
      }
   
      return this.lecture;
   }

   public boolean addToLecture(Lecture value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.lecture == null)
         {
            this.lecture = new LectureSet();
         }
         
         changed = this.lecture.add (value);
         
         if (changed)
         {
            value.withHas(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LECTURE, null, value);
         }
      }
         
      return changed;   
   }

   public boolean removeFromLecture(Lecture value)
   {
      boolean changed = false;
      
      if ((this.lecture != null) && (value != null))
      {
         changed = this.lecture.remove(value);
         
         if (changed)
         {
            value.setHas(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LECTURE, value, null);
         }
      }
         
      return changed;   
   }

   public Professor withLecture(Lecture... value)
   {
      if(value==null){
         return this;
      }
      for (Lecture item : value)
      {
         addToLecture(item);
      }
      return this;
   } 

   public Professor withoutLecture(Lecture... value)
   {
      for (Lecture item : value)
      {
         removeFromLecture(item);
      }
      return this;
   }

   public void removeAllFromLecture()
   {
      LinkedHashSet<Lecture> tmpSet = new LinkedHashSet<Lecture>(this.getLecture());
   
      for (Lecture value : tmpSet)
      {
         this.removeFromLecture(value);
      }
   }

   public Lecture createLecture()
   {
      Lecture value = new Lecture();
      withLecture(value);
      return value;
   } 

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
      return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
      getPropertyChangeSupport().removePropertyChangeListener(listener);
      return true;
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;

   @Override
   public String getName()
   {
      return this.name;
   }
   
   @Override
   public void setName(String value)
   {
      if ( ! StrUtil.stringEquals(this.name, value))
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   @Override
   public Professor withName(String value)
   {
      setName(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Professor ----------------------------------- Topic
    *              prof                   topic
    * </pre>
    */
   
   public static final String PROPERTY_TOPIC = "topic";

   private Topic topic = null;

   public Topic getTopic()
   {
      return this.topic;
   }

   public boolean setTopic(Topic value)
   {
      boolean changed = false;
      
      if (this.topic != value)
      {
         Topic oldValue = this.topic;
         
         if (this.topic != null)
         {
            this.topic = null;
            oldValue.setProf(null);
         }
         
         this.topic = value;
         
         if (value != null)
         {
            value.withProf(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TOPIC, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Professor withTopic(Topic value)
   {
      setTopic(value);
      return this;
   } 

   public Topic createTopic()
   {
      Topic value = new Topic();
      withTopic(value);
      return value;
   } 

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   
   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(propertyName, listener);
   	}
   	return true;
   }
}

