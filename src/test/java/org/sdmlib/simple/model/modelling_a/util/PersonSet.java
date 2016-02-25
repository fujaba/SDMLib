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
import org.sdmlib.simple.model.modelling_a.Person;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.simple.model.modelling_a.util.RoomSet;
import org.sdmlib.simple.model.modelling_a.Room;

public class PersonSet extends SDMSet<Person>
{

   public static final PersonSet EMPTY_SET = new PersonSet().withFlag(PersonSet.READONLY);


   public PersonPO filterPersonPO()
   {
      return new PersonPO(this.toArray(new Person[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.modelling_a.Person";
   }


   @SuppressWarnings("unchecked")
   public PersonSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Person>)value);
      }
      else if (value != null)
      {
         this.add((Person) value);
      }
      
      return this;
   }
   
   public PersonSet without(Person value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public PersonSet filter(Condition<Person> newValue) {
      PersonSet filterList = new PersonSet();
      filterItems(filterList, newValue);
      return filterList;
   }
   
   //==========================================================================
   
   public PersonSet think()
   {
      for (Person obj : this)
      {
         obj.think();
      }
      return this;
   }

   
   //==========================================================================
   
   public PersonSet dontThink(boolean p0)
   {
      for (Person obj : this)
      {
         obj.dontThink(p0);
      }
      return this;
   }


   /**
    * Loop through the current set of Person objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Person obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and collect those Person objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Person objects that match the parameter
    */
   public PersonSet filterName(String value)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and collect those Person objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Person objects that match the parameter
    */
   public PersonSet filterName(String lower, String upper)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Person objects now with new attribute values.
    */
   public PersonSet withName(String value)
   {
      for (Person obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Person objects and collect a list of the age attribute values. 
    * 
    * @return List of int objects reachable via age attribute
    */
   public intList getAge()
   {
      intList result = new intList();
      
      for (Person obj : this)
      {
         result.add(obj.getAge());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and collect those Person objects where the age attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Person objects that match the parameter
    */
   public PersonSet filterAge(int value)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (value == obj.getAge())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and collect those Person objects where the age attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Person objects that match the parameter
    */
   public PersonSet filterAge(int lower, int upper)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (lower <= obj.getAge() && obj.getAge() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and assign value to the age attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Person objects now with new attribute values.
    */
   public PersonSet withAge(int value)
   {
      for (Person obj : this)
      {
         obj.setAge(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Person objects and collect a set of the Room objects reached via room. 
    * 
    * @return Set of Room objects reachable via room
    */
   public RoomSet getRoom()
   {
      RoomSet result = new RoomSet();
      
      for (Person obj : this)
      {
         result.with(obj.getRoom());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Person objects and collect all contained objects with reference room pointing to the object passed as parameter. 
    * 
    * @param value The object required as room neighbor of the collected results. 
    * 
    * @return Set of Room objects referring to value via room
    */
   public PersonSet filterRoom(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      PersonSet answer = new PersonSet();
      
      for (Person obj : this)
      {
         if (neighbors.contains(obj.getRoom()) || (neighbors.isEmpty() && obj.getRoom() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Person object passed as parameter to the Room attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Room attributes.
    */
   public PersonSet withRoom(Room value)
   {
      for (Person obj : this)
      {
         obj.withRoom(value);
      }
      
      return this;
   }

}
