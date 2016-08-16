/*
   Copyright (c) 2016 Stefan
   
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
   
package org.sdmlib.simple.model.modelling_a.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.simple.model.modelling_a.StudentEnum;

public class StudentEnumSet extends SDMSet<StudentEnum>
{

   public static final StudentEnumSet EMPTY_SET = new StudentEnumSet().withFlag(StudentEnumSet.READONLY);


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.modelling_a.StudentEnum";
   }

   /**
    * Loop through the current set of StudentEnum objects and collect a list of the value0 attribute values. 
    * 
    * @return List of java.lang.Integer objects reachable via value0 attribute
    */
   public IntegerSet getValue0()
   {
      IntegerSet result = new IntegerSet();
      
      for (StudentEnum obj : this)
      {
         result.add(obj.getValue0());
      }
      
      return result;
   }


   /**
    * Loop through the current set of StudentEnum objects and collect those StudentEnum objects where the value0 attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of StudentEnum objects that match the parameter
    */
   public StudentEnumSet createValue0Condition(Integer value)
   {
      StudentEnumSet result = new StudentEnumSet();
      
      for (StudentEnum obj : this)
      {
         if (value == obj.getValue0())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of StudentEnum objects and assign value to the value0 attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of StudentEnum objects now with new attribute values.
    */
   public StudentEnumSet withValue0(Integer value)
   {
      for (StudentEnum obj : this)
      {
         obj.setValue0(value);
      }
      
      return this;
   }

}
