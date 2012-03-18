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

import java.util.LinkedHashSet;

public class Student
{ 
   /********************************************************************
    * <pre>
    *              many                       one
    * Student ----------------------------------- University
    *              students                   uni
    * </pre>
    */
   
   public static final String PROPERTY_UNI = "uni";
   
   private University uni = null;
   
   public University getUni()
   {
      return this.uni;
   }
   
   public boolean setUni(University value)
   {
      boolean changed = false;
      
      if (this.uni != value)
      {
         University oldValue = this.uni;
         
         if (this.uni != null)
         {
            this.uni = null;
            oldValue.withoutStudents(this);
         }
         
         this.uni = value;
         
         if (value != null)
         {
            value.withStudents(this);
         }
         
         // getPropertyChangeSupport().firePropertyChange(PROPERTY_UNI, null, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Student withUni(University value)
   {
      setUni(value);
      return this;
   } 

   
   public static final LinkedHashSet<Student> EMPTY_SET = new LinkedHashSet<Student>();

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;
   
   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      this.name = value;
   }
   
   public Student withName(String value)
   {
      setName(value);
      return this;
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
      this.matrNo = value;
   }
   
   public Student withMatrNo(int value)
   {
      setMatrNo(value);
      return this;
   } 

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_MATRNO.equalsIgnoreCase(attrName))
      {
         return getMatrNo();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_MATRNO.equalsIgnoreCase(attrName))
      {
         setMatrNo((Integer) value);
         return true;
      }

      return false;
   }
}


























