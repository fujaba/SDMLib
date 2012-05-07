/*
   Copyright (c) 2012 NeTH 
   
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

import org.sdmlib.examples.studyrightextends.Male;
import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.utils.StrUtil;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;

public class Student implements Male, PropertyChangeInterface
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

      if (PROPERTY_MATRNO.equalsIgnoreCase(attrName))
      {
         return getMatrNo();
      }

      if (PROPERTY_LECTURE.equalsIgnoreCase(attrName))
      {
         return getLecture();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_MATRNO.equalsIgnoreCase(attrName))
      {
         setMatrNo((Integer) value);
         return true;
      }

      if (PROPERTY_LECTURE.equalsIgnoreCase(attrName))
      {
         addToLecture((Lecture) value);
         return true;
      }
      
      if ((PROPERTY_LECTURE + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromLecture((Lecture) value);
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
      removeAllFromLecture();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   // methods for PROPERTY_NAME = "name"
   
   private String name;
   
   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      if ( ! StrUtil.stringEquals(this.name, value))
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public Student withName(String value)
   {
      setName(value);
      return this;
   } 

   
   //==========================================================================
   
   public void findMyPosition(  )
   {
   }

   
   //==========================================================================
   
   public void findMyPosition( String p0 )
   {
   }

   
   //==========================================================================
   
   public void findMyPosition( String p0, int p1 )
   {
   }

   
   //==========================================================================
   
   public static final String PROPERTY_MATRNO = "matrNo";
   
   private int matrNo;
   
   public int getMatrNo()
   {
      return this.matrNo;
   }
   
   public void setMatrNo(int value)
   {
      if (this.matrNo != value)
      {
         int oldValue = this.matrNo;
         this.matrNo = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MATRNO, oldValue, value);
      }
   }
   
   public Student withMatrNo(int value)
   {
      setMatrNo(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Student ----------------------------------- Lecture
    *              listen                   lecture
    * </pre>
    */
   
   public static final String PROPERTY_LECTURE = "lecture";
   
   private LinkedHashSet<Lecture> lecture = null;
   
   public LinkedHashSet<Lecture> getLecture()
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
            this.lecture = new LinkedHashSet<Lecture>();
         }
         
         changed = this.lecture.add (value);
         
         if (changed)
         {
            value.withListen(this);
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
         changed = this.lecture.remove (value);
         
         if (changed)
         {
            value.setListen(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LECTURE, value, null);
         }
      }
         
      return changed;   
   }
   
   public Student withLecture(Lecture value)
   {
      addToLecture(value);
      return this;
   } 
   
   public Student withoutLecture(Lecture value)
   {
      removeFromLecture(value);
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
}

