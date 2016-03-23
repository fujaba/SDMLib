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
   
package org.sdmlib.simple.model.superclazzes_b.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.simple.model.superclazzes_b.Pupil;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;

public class PupilSet extends SDMSet<Pupil>
{

   public static final PupilSet EMPTY_SET = new PupilSet().withFlag(PupilSet.READONLY);


   public PupilPO filterPupilPO()
   {
      return new PupilPO(this.toArray(new Pupil[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.superclazzes_b.Pupil";
   }


   @SuppressWarnings("unchecked")
   public PupilSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Pupil>)value);
      }
      else if (value != null)
      {
         this.add((Pupil) value);
      }
      
      return this;
   }
   
   public PupilSet without(Pupil value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public PupilSet filter(Condition<Pupil> newValue) {
      PupilSet filterList = new PupilSet();
      filterItems(filterList, newValue);
      return filterList;
   }
}
