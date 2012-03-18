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
   
package org.sdmlib.models.classes;

import java.util.LinkedHashSet;

public class Method
{

   
   //==========================================================================
   
   public static final String PROPERTY_SIGNATURE = "signature";
   
   private String signature;
   
   public String getSignature()
   {
      return this.signature;
   }
   
   public void setSignature(String value)
   {
      this.signature = value;
   }
   
   public Method withSignature(String value)
   {
      setSignature(value);
      return this;
   } 

   
   public static final LinkedHashSet<Method> EMPTY_SET = new LinkedHashSet<Method>();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Method ----------------------------------- Clazz
    *              methods                   clazz
    * </pre>
    */
   
   public static final String PROPERTY_CLAZZ = "clazz";
   
   private Clazz clazz = null;
   
   public Clazz getClazz()
   {
      return this.clazz;
   }
   
   public boolean setClazz(Clazz value)
   {
      boolean changed = false;
      
      if (this.clazz != value)
      {
         Clazz oldValue = this.clazz;
         
         if (this.clazz != null)
         {
            this.clazz = null;
            oldValue.withoutMethods(this);
         }
         
         this.clazz = value;
         
         if (value != null)
         {
            value.withMethods(this);
         }
         
         // getPropertyChangeSupport().firePropertyChange(PROPERTY_CLAZZ, null, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Method withClazz(Clazz value)
   {
      setClazz(value);
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

      if (PROPERTY_SIGNATURE.equalsIgnoreCase(attrName))
      {
         return getSignature();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_SIGNATURE.equalsIgnoreCase(attrName))
      {
         setSignature((String) value);
         return true;
      }

      return false;
   }
}



