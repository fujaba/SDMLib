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
   
package org.sdmlib.simple.model.attribute_i.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.simple.model.attribute_i.Person;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.SimpleKeyValueList;

public class PersonSet extends SDMSet<Person>
{

   public PersonSet()
   {
      // empty
   }

   public PersonSet(Person... objects)
   {
      for (Person obj : objects)
      {
         this.add(obj);
      }
   }

   public PersonSet(Collection<Person> objects)
   {
      this.addAll(objects);
   }

   public static final PersonSet EMPTY_SET = new PersonSet().withFlag(PersonSet.READONLY);


   public PersonPO filterPersonPO()
   {
      return new PersonPO(this.toArray(new Person[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.attribute_i.Person";
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

   /**
    * Loop through the current set of Person objects and collect a list of the namesMap attribute values. 
    * 
    * @return List of de.uniks.networkparser.list.SimpleKeyValueList<String,de.uniks.networkparser.list.SimpleKeyValueList> objects reachable via namesMap attribute
    */
   public SimpleKeyValueList<String,SimpleKeyValueList> getNamesMap()
   {
      SimpleKeyValueList<String,SimpleKeyValueList> result = new SimpleKeyValueList<String,SimpleKeyValueList>();
      
      for (Person obj : this)
      {
         result.with(obj.getNamesMap());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and collect those Person objects where the namesMap attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Person objects that match the parameter
    */
   public PersonSet filterNamesMap(SimpleKeyValueList<String,SimpleKeyValueList> value)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (value == obj.getNamesMap())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and assign value to the namesMap attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Person objects now with new attribute values.
    */
   public PersonSet withNamesMap(SimpleKeyValueList<String,SimpleKeyValueList> value)
   {
      for (Person obj : this)
      {
         obj.setNamesMap(value);
      }
      
      return this;
   }

}
