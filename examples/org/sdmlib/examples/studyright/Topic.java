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
   
package org.sdmlib.examples.studyright;

import java.beans.PropertyChangeSupport;

import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

public class Topic implements PropertyChangeInterface
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

      if (PROPERTY_PROF.equalsIgnoreCase(attrName))
      {
         return getProf();
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

      if (PROPERTY_PROF.equalsIgnoreCase(attrName))
      {
         setProf((Professor) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
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

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getTitle());
      return _.substring(1);
   }}

