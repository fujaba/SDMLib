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
   
package org.sdmlib.simple.model.enums_d;

import java.lang.Integer;

public enum TestEnum
{
		TEACHER(42);

   
   //==========================================================================
   
   public static final String PROPERTY_VALUE0 = "value0";
   
   private Integer value0;

   public Integer getValue0()
   {
      return this.value0;
   }
   
   public void setValue0(Integer value)
   {
      if (this.value0 != value) {
      
         Integer oldValue = this.value0;
         this.value0 = value;
      }
   }
   
   public TestEnum withValue0(Integer value)
   {
      setValue0(value);
      return this;
   } 

   
   //==========================================================================
     TestEnum( Integer value0 )
   {
      this.value0 = value0;
   }
}
