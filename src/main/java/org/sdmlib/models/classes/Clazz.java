/*
   Copyright (c) 2016 zuendorf
   
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
   /**
    * 
    * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/ClassModelTest.java'>ClassModelTest.java</a>
 */
   public  class Clazz extends SDMLibClass
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
   
      super.removeYou();

      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_INTERFAZE = "interfaze";
   
   private boolean interfaze;

   public boolean isInterfaze()
   {
      return this.interfaze;
   }
   
   public void setInterfaze(boolean value)
   {
      if (this.interfaze != value) {
      
         boolean oldValue = this.interfaze;
         this.interfaze = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_INTERFAZE, oldValue, value);
      }
   }
   
   public Clazz withInterfaze(boolean value)
   {
      setInterfaze(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_EXTERNAL = "external";
   
   private boolean external;

   public boolean isExternal()
   {
      return this.external;
   }
   
   public void setExternal(boolean value)
   {
      if (this.external != value) {
      
         boolean oldValue = this.external;
         this.external = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_EXTERNAL, oldValue, value);
      }
   }
   
   public Clazz withExternal(boolean value)
   {
      setExternal(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      return result.substring(1);
   }

}
