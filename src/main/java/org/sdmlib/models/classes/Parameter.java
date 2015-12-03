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

package org.sdmlib.models.classes;

import org.sdmlib.models.classes.util.ParameterSet;
   /**
    * 
    * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/ClassModelTest.java'>ClassModelTest.java</a>
*/
   public class Parameter extends Value
{
   public static final String PROPERTY_METHOD = "method";
   private Method method = null;
   
   protected Parameter()
   {
      
   }
   
   public Parameter(Type type)
   {
      this.type = DataType.ref(type);
   }
   
   public Parameter(String name, Type type)
   {
      withName(name);
      this.type = DataType.ref(type);
   }


   public Method getMethod()
   {
      return this.method;
   }

   public boolean setMethod(Method value)
   {
      boolean changed = false;

      if (this.method != value)
      {
         Method oldValue = this.method;

         if (this.method != null)
         {
            this.method = null;
            oldValue.without(this);
         }

         this.method = value;

         if (value != null)
         {
            value.with(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_METHOD, oldValue, value);
         changed = true;
      }

      return changed;
   }

   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      result.append(" ").append(this.getType());
      if(this.name!=null){
    	  result.append(" ").append(this.getName());
      }
      if(this.initialization!=null){
    	  result.append(":").append(this.getInitialization());
      }
      return result.substring(1);
   }
   
   //==========================================================================
   @Override
   public void removeYou()
   {
      super.removeYou();
      setMethod(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }
   
   @Override
   public Parameter withName(String string)
   {
      setName(string);
      return this;
   }

   
   @Override
   public Parameter with(DataType value)
   {
      setType(value);
      return this;
   } 
   
   @Override
   public Parameter withInitialization(String value){
      setInitialization(value);
      return this;
   }

   public Parameter with(Method value)
   {
      setMethod(value);
      return this;
   }

   protected Method createMethod()
   {
      Method value = new Method();
      with(value);
      return value;
   } 

   
   public static final ParameterSet EMPTY_SET = new ParameterSet().withReadOnly(true);

   Parameter withMethod(Method value)
   {
      setMethod(value);
      return this;
   } 
}
