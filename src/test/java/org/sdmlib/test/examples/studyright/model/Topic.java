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

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import de.uniks.networkparser.interfaces.SendableEntity;
import org.sdmlib.test.examples.studyright.model.Professor;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/studyright/StudyRightModel.java'>StudyRightModel.java</a>
*/
   public class Topic implements PropertyChangeInterface, SendableEntity
{

   
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
   
   
   public void removeYou()
   {
      setProf(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_TITLE = "title";
   
   private String title;

   public String getTitle()
   {
      return this.title;
   }
   
   public void setTitle(String value)
   {
      if ( ! StrUtil.stringEquals(this.title, value))
      {
         String oldValue = this.title;
         this.title = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TITLE, oldValue, value);
      }
   }
   
   public Topic withTitle(String value)
   {
      setTitle(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getTitle());
      return s.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       one
    * Topic ----------------------------------- Professor
    *              topic                   prof
    * </pre>
    */
   
   public static final String PROPERTY_PROF = "prof";

   private Professor prof = null;

   public Professor getProf()
   {
      return this.prof;
   }

   public boolean setProf(Professor value)
   {
      boolean changed = false;
      
      if (this.prof != value)
      {
         Professor oldValue = this.prof;
         
         if (this.prof != null)
         {
            this.prof = null;
            oldValue.setTopic(null);
         }
         
         this.prof = value;
         
         if (value != null)
         {
            value.withTopic(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PROF, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Topic withProf(Professor value)
   {
      setProf(value);
      return this;
   } 

   public Professor createProf()
   {
      Professor value = new Professor();
      withProf(value);
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
   }

