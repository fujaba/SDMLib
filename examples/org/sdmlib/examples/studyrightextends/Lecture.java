/*
   Copyright (c) 2012 zuendorf 
   
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
   
package org.sdmlib.examples.studyrightextends;

import java.beans.PropertyChangeSupport;

import org.sdmlib.examples.studyrightextends.creators.LectureSet;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;
import java.beans.PropertyChangeListener;

public class Lecture implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_TITLE.equalsIgnoreCase(attrName))
      {
         return getTitle();
      }

      if (PROPERTY_IN.equalsIgnoreCase(attrName))
      {
         return getIn();
      }

      if (PROPERTY_HAS.equalsIgnoreCase(attrName))
      {
         return getHas();
      }

      if (PROPERTY_LISTEN.equalsIgnoreCase(attrName))
      {
         return getListen();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_TITLE.equalsIgnoreCase(attrName))
      {
         setTitle((String) value);
         return true;
      }

      if (PROPERTY_IN.equalsIgnoreCase(attrName))
      {
         setIn((Room) value);
         return true;
      }

      if (PROPERTY_HAS.equalsIgnoreCase(attrName))
      {
         setHas((Professor) value);
         return true;
      }

      if (PROPERTY_LISTEN.equalsIgnoreCase(attrName))
      {
         setListen((Student) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setIn(null);
      setHas(null);
      setListen(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_TITLE = "Title";
   
   private String Title;

   public String getTitle()
   {
      return this.Title;
   }
   
   public void setTitle(String value)
   {
      if ( ! StrUtil.stringEquals(this.Title, value))
      {
         String oldValue = this.Title;
         this.Title = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TITLE, oldValue, value);
      }
   }
   
   public Lecture withTitle(String value)
   {
      setTitle(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Lecture ----------------------------------- Room
    *              lecture                   in
    * </pre>
    */
   
   public static final String PROPERTY_IN = "in";
   
   private Room in = null;
   
   public Room getIn()
   {
      return this.in;
   }
   
   public boolean setIn(Room value)
   {
      boolean changed = false;
      
      if (this.in != value)
      {
         Room oldValue = this.in;
         
         if (this.in != null)
         {
            this.in = null;
            oldValue.withoutLecture(this);
         }
         
         this.in = value;
         
         if (value != null)
         {
            value.withLecture(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_IN, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Lecture withIn(Room value)
   {
      setIn(value);
      return this;
   } 

   
   public static final LectureSet EMPTY_SET = new LectureSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Lecture ----------------------------------- Professor
    *              lecture                   has
    * </pre>
    */
   
   public static final String PROPERTY_HAS = "has";
   
   private Professor has = null;
   
   public Professor getHas()
   {
      return this.has;
   }
   
   public boolean setHas(Professor value)
   {
      boolean changed = false;
      
      if (this.has != value)
      {
         Professor oldValue = this.has;
         
         if (this.has != null)
         {
            this.has = null;
            oldValue.withoutLecture(this);
         }
         
         this.has = value;
         
         if (value != null)
         {
            value.withLecture(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HAS, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Lecture withHas(Professor value)
   {
      setHas(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Lecture ----------------------------------- Student
    *              lecture                   listen
    * </pre>
    */
   
   public static final String PROPERTY_LISTEN = "listen";
   
   private Student listen = null;
   
   public Student getListen()
   {
      return this.listen;
   }
   
   public boolean setListen(Student value)
   {
      boolean changed = false;
      
      if (this.listen != value)
      {
         Student oldValue = this.listen;
         
         if (this.listen != null)
         {
            this.listen = null;
            oldValue.withoutLecture(this);
         }
         
         this.listen = value;
         
         if (value != null)
         {
            value.withLecture(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LISTEN, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Lecture withListen(Student value)
   {
      setListen(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getTitle());
      return _.substring(1);
   }}

