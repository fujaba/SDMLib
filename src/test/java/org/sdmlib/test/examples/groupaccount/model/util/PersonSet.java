/*
   Copyright (c) 2018 zuendorf
   
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
   
package org.sdmlib.test.examples.groupaccount.model.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.test.examples.groupaccount.model.Item;
import org.sdmlib.test.examples.groupaccount.model.Party;
import org.sdmlib.test.examples.groupaccount.model.Person;

import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class PersonSet extends SimpleSet<Person>
{
	public Class<?> getTypClass() {
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
      return "org.sdmlib.test.examples.groupaccount.model.Person";
   }


   @Override
   public PersonSet getNewList(boolean keyValue)
   {
      return new PersonSet();
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
    * Loop through the current set of Person objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public ObjectSet getName()
   {
      ObjectSet result = new ObjectSet();
      
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
   public PersonSet createNameCondition(String value)
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
   public PersonSet createNameCondition(String lower, String upper)
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
    * Loop through the current set of Person objects and collect a list of the saldo attribute values. 
    * 
    * @return List of double objects reachable via saldo attribute
    */
   public NumberList getSaldo()
   {
      NumberList result = new NumberList();
      
      for (Person obj : this)
      {
         result.add(obj.getSaldo());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and collect those Person objects where the saldo attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Person objects that match the parameter
    */
   public PersonSet createSaldoCondition(double value)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (value == obj.getSaldo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and collect those Person objects where the saldo attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Person objects that match the parameter
    */
   public PersonSet createSaldoCondition(double lower, double upper)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (lower <= obj.getSaldo() && obj.getSaldo() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and assign value to the saldo attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Person objects now with new attribute values.
    */
   public PersonSet withSaldo(double value)
   {
      for (Person obj : this)
      {
         obj.setSaldo(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Person objects and collect a list of the total attribute values. 
    * 
    * @return List of double objects reachable via total attribute
    */
   public NumberList getTotal()
   {
      NumberList result = new NumberList();
      
      for (Person obj : this)
      {
         result.add(obj.getTotal());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and collect those Person objects where the total attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Person objects that match the parameter
    */
   public PersonSet createTotalCondition(double value)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (value == obj.getTotal())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and collect those Person objects where the total attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Person objects that match the parameter
    */
   public PersonSet createTotalCondition(double lower, double upper)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (lower <= obj.getTotal() && obj.getTotal() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Person objects and assign value to the total attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Person objects now with new attribute values.
    */
   public PersonSet withTotal(double value)
   {
      for (Person obj : this)
      {
         obj.setTotal(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Person objects and collect a set of the Party objects reached via party. 
    * 
    * @return Set of Party objects reachable via party
    */
   public PartySet getParty()
   {
      PartySet result = new PartySet();
      
      for (Person obj : this)
      {
         result.with(obj.getParty());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Person objects and collect all contained objects with reference party pointing to the object passed as parameter. 
    * 
    * @param value The object required as party neighbor of the collected results. 
    * 
    * @return Set of Party objects referring to value via party
    */
   public PersonSet filterParty(Object value)
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
         if (neighbors.contains(obj.getParty()) || (neighbors.isEmpty() && obj.getParty() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Person object passed as parameter to the Party attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Party attributes.
    */
   public PersonSet withParty(Party value)
   {
      for (Person obj : this)
      {
         obj.withParty(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Person objects and collect a set of the Item objects reached via items. 
    * 
    * @return Set of Item objects reachable via items
    */
   public ItemSet getItems()
   {
      ItemSet result = new ItemSet();
      
      for (Person obj : this)
      {
         result.with(obj.getItems());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Person objects and collect all contained objects with reference items pointing to the object passed as parameter. 
    * 
    * @param value The object required as items neighbor of the collected results. 
    * 
    * @return Set of Item objects referring to value via items
    */
   public PersonSet filterItems(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getItems()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Person object passed as parameter to the Items attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Items attributes.
    */
   public PersonSet withItems(Item value)
   {
      for (Person obj : this)
      {
         obj.withItems(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Person object passed as parameter from the Items attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public PersonSet withoutItems(Item value)
   {
      for (Person obj : this)
      {
         obj.withoutItems(value);
      }
      
      return this;
   }

}
