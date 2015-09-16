/*
   Copyright (c) 2015 Stefan
   
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
   
package org.sdmlib.test.examples.maumau.model.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.test.examples.maumau.model.DutyType;
import java.util.Collection;

public class DutyTypeSet extends SDMSet<DutyType>
{

   public static final DutyTypeSet EMPTY_SET = new DutyTypeSet().withReadOnly(true);


   public DutyTypePO hasDutyTypePO()
   {
      return new DutyTypePO(this.toArray(new DutyType[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.maumau.model.DutyType";
   }


   @SuppressWarnings("unchecked")
   public DutyTypeSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<DutyType>)value);
      }
      else if (value != null)
      {
         this.add((DutyType) value);
      }
      
      return this;
   }
   
   public DutyTypeSet without(DutyType value)
   {
      this.remove(value);
      return this;
   }

}
