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
   
package org.sdmlib.simple.model.attribute_h.util;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.simple.model.attribute_h.Person;
import java.util.Collection;
import de.uniks.networkparser.list.SimpleKeyValueList;

public class PersonSet extends SimpleSet<Person>
{
	protected Class<?> getTypClass() {
		return Person.class;
	}

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


   public PersonPO createPersonPO()
   {
      return new PersonPO(this.toArray(new Person[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.attribute_h.Person";
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


   /**
    * Loop through the current set of Person objects and collect a list of the names attribute values. 
    * 
    * @return List of de.uniks.networkparser.list.SimpleKeyValueList<String,String> objects reachable via names attribute
    */
   public SimpleKeyValueList<String,String> getNames()
   {
      SimpleKeyValueList<String,String> result = new SimpleKeyValueList<String,String>();
      
      for (Person obj : this)
      {
         result.with(obj.getNames());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and collect those Person objects where the names attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Person objects that match the parameter
    */
   public PersonSet createNamesCondition(SimpleKeyValueList<String,String> value)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (value == obj.getNames())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and assign value to the names attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Person objects now with new attribute values.
    */
   public PersonSet withNames(SimpleKeyValueList<String,String> value)
   {
      for (Person obj : this)
      {
         obj.setNames(value);
      }
      
      return this;
   }

}
