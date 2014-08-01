/*
   Copyright (c) 2014 christian 
   
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

import org.sdmlib.models.classes.SDMLibClass;
import org.sdmlib.StrUtil;
import org.sdmlib.models.classes.util.EnumerationValueSet;

public class EnumerationValue extends SDMLibClass
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      super.removeYou();

      setEnumeration(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_VALUENAME = "valueName";
   
   private String valueName;

   public String getValueName()
   {
      return this.valueName;
   }
   
   public void setValueName(String value)
   {
      if ( ! StrUtil.stringEquals(this.valueName, value))
      {
         String oldValue = this.valueName;
         this.valueName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_VALUENAME, oldValue, value);
      }
   }
   
   public EnumerationValue withValueName(String value)
   {
      setValueName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getValueName());
      result.append(" ").append(this.getName());
      return result.substring(1);
   }


   
   public static final EnumerationValueSet EMPTY_SET = new EnumerationValueSet().withReadonly(true);

   
   /********************************************************************
    * <pre>
    *              many                       one
    * EnumerationValue ----------------------------------- Enumeration
    *              values                   enumeration
    * </pre>
    */
   
   public static final String PROPERTY_ENUMERATION = "enumeration";

   private Enumeration enumeration = null;

   public Enumeration getEnumeration()
   {
      return this.enumeration;
   }

   public boolean setEnumeration(Enumeration value)
   {
      boolean changed = false;
      
      if (this.enumeration != value)
      {
         Enumeration oldValue = this.enumeration;
         
         if (this.enumeration != null)
         {
            this.enumeration = null;
            oldValue.withoutValues(this);
         }
         
         this.enumeration = value;
         
         if (value != null)
         {
            value.withValues(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ENUMERATION, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public EnumerationValue withEnumeration(Enumeration value)
   {
      setEnumeration(value);
      return this;
   } 

   public Enumeration createEnumeration()
   {
      Enumeration value = new Enumeration();
      withEnumeration(value);
      return value;
   }

   @Override
   public EnumerationValue withName(String value)
   {
      setName(value);
      return this;
   } 
}
