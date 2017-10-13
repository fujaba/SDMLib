/*
   Copyright (c) 2017 zuendorf
   
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
   
package org.sdmlib.simple.model.association_l;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.simple.model.association_l.util.LectureSet;
import org.sdmlib.simple.model.association_l.Lecture;
import org.sdmlib.simple.model.association_l.University;
   /**
    * 
    * @see <a href='../../../../../../../../src/test/java/org/sdmlib/simple/TestAssociation.java'>TestAssociation.java</a>
 */
   public  class Student implements SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = null;
   
   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(listener);
   	return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(propertyName, listener);
   	return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners.removePropertyChangeListener(listener);
   	}
   	listeners.removePropertyChangeListener(listener);
   	return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(propertyName, listener);
   	}
   	return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      withoutAttended(this.getAttended().toArray(new Lecture[this.getAttended().size()]));
      setStuds(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Student ----------------------------------- Lecture
    *              has                   attended
    * </pre>
    */
   
   public static final String PROPERTY_ATTENDED = "attended";

   private LectureSet attended = null;
   
   public LectureSet getAttended()
   {
      if (this.attended == null)
      {
         return LectureSet.EMPTY_SET;
      }
   
      return this.attended;
   }

   public Student withAttended(Lecture... value)
   {
      if(value==null){
         return this;
      }
      for (Lecture item : value)
      {
         if (item != null)
         {
            if (this.attended == null)
            {
               this.attended = new LectureSet();
            }
            
            boolean changed = this.attended.add (item);

            if (changed)
            {
               item.withHas(this);
               firePropertyChange(PROPERTY_ATTENDED, null, item);
            }
         }
      }
      return this;
   } 

   public Student withoutAttended(Lecture... value)
   {
      for (Lecture item : value)
      {
         if ((this.attended != null) && (item != null))
         {
            if (this.attended.remove(item))
            {
               item.withoutHas(this);
               firePropertyChange(PROPERTY_ATTENDED, item, null);
            }
         }
      }
      return this;
   }

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Student ----------------------------------- University
    *              students                   studs
    * </pre>
    */
   
   public static final String PROPERTY_STUDS = "studs";

   private University studs = null;

   public University getStuds()
   {
      return this.studs;
   }

   public boolean setStuds(University value)
   {
      boolean changed = false;
      
      if (this.studs != value)
      {
         University oldValue = this.studs;
         
         if (this.studs != null)
         {
            this.studs = null;
            oldValue.withoutStudents(this);
         }
         
         this.studs = value;
         
         if (value != null)
         {
            value.withStudents(this);
         }
         
         firePropertyChange(PROPERTY_STUDS, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Student withStuds(University value)
   {
      setStuds(value);
      return this;
   } 
}
