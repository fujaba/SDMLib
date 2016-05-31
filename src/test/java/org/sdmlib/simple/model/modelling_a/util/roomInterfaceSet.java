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
   
package org.sdmlib.simple.model.modelling_a.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.simple.model.modelling_a.roomInterface;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.modelsets.intList;

public class roomInterfaceSet extends SDMSet<roomInterface>
{

   public static final roomInterfaceSet EMPTY_SET = new roomInterfaceSet().withFlag(roomInterfaceSet.READONLY);


   public roomInterfacePO filterroomInterfacePO()
   {
      return new roomInterfacePO(this.toArray(new roomInterface[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.modelling_a.roomInterface";
   }


   @SuppressWarnings("unchecked")
   public roomInterfaceSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<roomInterface>)value);
      }
      else if (value != null)
      {
         this.add((roomInterface) value);
      }
      
      return this;
   }
   
   public roomInterfaceSet without(roomInterface value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public roomInterfaceSet filter(Condition<roomInterface> newValue) {
      roomInterfaceSet filterList = new roomInterfaceSet();
      filterItems(filterList, newValue);
      return filterList;
   }

   /**
    * Loop through the current set of roomInterface objects and collect a list of the number attribute values. 
    * 
    * @return List of int objects reachable via number attribute
    */
   public intList getNumber()
   {
      intList result = new intList();
      
      for (roomInterface obj : this)
      {
         result.add(obj.getNumber());
      }
      
      return result;
   }


   /**
    * Loop through the current set of roomInterface objects and collect those roomInterface objects where the number attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of roomInterface objects that match the parameter
    */
   public roomInterfaceSet filterNumber(int value)
   {
      roomInterfaceSet result = new roomInterfaceSet();
      
      for (roomInterface obj : this)
      {
         if (value == obj.getNumber())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of roomInterface objects and collect those roomInterface objects where the number attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of roomInterface objects that match the parameter
    */
   public roomInterfaceSet filterNumber(int lower, int upper)
   {
      roomInterfaceSet result = new roomInterfaceSet();
      
      for (roomInterface obj : this)
      {
         if (lower <= obj.getNumber() && obj.getNumber() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of roomInterface objects and assign value to the number attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of roomInterface objects now with new attribute values.
    */
   public roomInterfaceSet withNumber(int value)
   {
      for (roomInterface obj : this)
      {
         obj.setNumber(value);
      }
      
      return this;
   }


   public roomInterfaceSet()
   {
      // empty
   }

   public roomInterfaceSet(roomInterface... objects)
   {
      for (roomInterface obj : objects)
      {
         this.add(obj);
      }
   }

   public roomInterfaceSet(Collection<roomInterface> objects)
   {
      this.addAll(objects);
   }
}
