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

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.groupaccount.model.Item;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.NumberList;
import org.sdmlib.test.examples.groupaccount.model.util.PersonSet;
import org.sdmlib.test.examples.groupaccount.model.Person;

public class ItemSet extends SimpleSet<Item>
{
	public Class<?> getTypClass() {
		return Item.class;
	}

   public ItemSet()
   {
      // empty
   }

   public ItemSet(Item... objects)
   {
      for (Item obj : objects)
      {
         this.add(obj);
      }
   }

   public ItemSet(Collection<Item> objects)
   {
      this.addAll(objects);
   }

   public static final ItemSet EMPTY_SET = new ItemSet().withFlag(ItemSet.READONLY);


   public ItemPO createItemPO()
   {
      return new ItemPO(this.toArray(new Item[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.groupaccount.model.Item";
   }


   @Override
   public ItemSet getNewList(boolean keyValue)
   {
      return new ItemSet();
   }


   @SuppressWarnings("unchecked")
   public ItemSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Item>)value);
      }
      else if (value != null)
      {
         this.add((Item) value);
      }
      
      return this;
   }
   
   public ItemSet without(Item value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Item objects and collect a list of the description attribute values. 
    * 
    * @return List of String objects reachable via description attribute
    */
   public ObjectSet getDescription()
   {
      ObjectSet result = new ObjectSet();
      
      for (Item obj : this)
      {
         result.add(obj.getDescription());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Item objects and collect those Item objects where the description attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Item objects that match the parameter
    */
   public ItemSet createDescriptionCondition(String value)
   {
      ItemSet result = new ItemSet();
      
      for (Item obj : this)
      {
         if (value.equals(obj.getDescription()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Item objects and collect those Item objects where the description attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Item objects that match the parameter
    */
   public ItemSet createDescriptionCondition(String lower, String upper)
   {
      ItemSet result = new ItemSet();
      
      for (Item obj : this)
      {
         if (lower.compareTo(obj.getDescription()) <= 0 && obj.getDescription().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Item objects and assign value to the description attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Item objects now with new attribute values.
    */
   public ItemSet withDescription(String value)
   {
      for (Item obj : this)
      {
         obj.setDescription(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Item objects and collect a list of the price attribute values. 
    * 
    * @return List of double objects reachable via price attribute
    */
   public NumberList getPrice()
   {
      NumberList result = new NumberList();
      
      for (Item obj : this)
      {
         result.add(obj.getPrice());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Item objects and collect those Item objects where the price attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Item objects that match the parameter
    */
   public ItemSet createPriceCondition(double value)
   {
      ItemSet result = new ItemSet();
      
      for (Item obj : this)
      {
         if (value == obj.getPrice())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Item objects and collect those Item objects where the price attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Item objects that match the parameter
    */
   public ItemSet createPriceCondition(double lower, double upper)
   {
      ItemSet result = new ItemSet();
      
      for (Item obj : this)
      {
         if (lower <= obj.getPrice() && obj.getPrice() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Item objects and assign value to the price attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Item objects now with new attribute values.
    */
   public ItemSet withPrice(double value)
   {
      for (Item obj : this)
      {
         obj.setPrice(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Item objects and collect a set of the Person objects reached via person. 
    * 
    * @return Set of Person objects reachable via person
    */
   public PersonSet getPerson()
   {
      PersonSet result = new PersonSet();
      
      for (Item obj : this)
      {
         result.with(obj.getPerson());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Item objects and collect all contained objects with reference person pointing to the object passed as parameter. 
    * 
    * @param value The object required as person neighbor of the collected results. 
    * 
    * @return Set of Person objects referring to value via person
    */
   public ItemSet filterPerson(Object value)
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
      
      ItemSet answer = new ItemSet();
      
      for (Item obj : this)
      {
         if (neighbors.contains(obj.getPerson()) || (neighbors.isEmpty() && obj.getPerson() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Item object passed as parameter to the Person attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Person attributes.
    */
   public ItemSet withPerson(Person value)
   {
      for (Item obj : this)
      {
         obj.withPerson(value);
      }
      
      return this;
   }

}
