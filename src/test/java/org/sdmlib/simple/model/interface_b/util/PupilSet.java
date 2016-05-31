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
   
package org.sdmlib.simple.model.interface_b.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.simple.model.interface_b.Pupil;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.modelsets.StringList;

public class PupilSet extends SDMSet<Pupil>
{

   public PupilSet()
   {
      // empty
   }

   public PupilSet(Pupil... objects)
   {
      for (Pupil obj : objects)
      {
         this.add(obj);
      }
   }

   public PupilSet(Collection<Pupil> objects)
   {
      this.addAll(objects);
   }

   public static final PupilSet EMPTY_SET = new PupilSet().withFlag(PupilSet.READONLY);


   public PupilPO filterPupilPO()
   {
      return new PupilPO(this.toArray(new Pupil[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.interface_b.Pupil";
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

   /**
    * Loop through the current set of Pupil objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Pupil obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pupil objects and collect those Pupil objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Pupil objects that match the parameter
    */
   public PupilSet filterName(String value)
   {
      PupilSet result = new PupilSet();
      
      for (Pupil obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pupil objects and collect those Pupil objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Pupil objects that match the parameter
    */
   public PupilSet filterName(String lower, String upper)
   {
      PupilSet result = new PupilSet();
      
      for (Pupil obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pupil objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Pupil objects now with new attribute values.
    */
   public PupilSet withName(String value)
   {
      for (Pupil obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   
   //==========================================================================
   
   public PupilSet think()
   {
      for (Pupil obj : this)
      {
         obj.think();
      }
      return this;
   }

   
   //==========================================================================
   
   public PupilSet sayName(String p0)
   {
      for (Pupil obj : this)
      {
         obj.sayName(p0);
      }
      return this;
   }

}
