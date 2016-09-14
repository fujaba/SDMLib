/*
   Copyright (c) 2014 zuendorf 
   
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

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.StringList;
import org.sdmlib.models.modelsets.doubleList;
import org.sdmlib.test.examples.groupaccount.model.Item;
import org.sdmlib.test.examples.groupaccount.model.Person;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.groupaccount.model.util.PersonSet;

public class ItemSet extends SimpleSet<Item>
{


   public ItemPO hasItemPO()
   {
      return new ItemPO(this.toArray(new Item[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public ItemSet with(Object value)
   {
      if (value instanceof java.util.Collection)
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

   public StringList getDescription()
   {
      StringList result = new StringList();
      
      for (Item obj : this)
      {
         result.add(obj.getDescription());
      }
      
      return result;
   }

   public ItemSet hasDescription(String value)
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

   public ItemSet hasDescription(String lower, String upper)
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

   public ItemSet withDescription(String value)
   {
      for (Item obj : this)
      {
         obj.setDescription(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Item objects and collect Item.value attributes as a list of double values.
    * @return list of double values
    */
   public doubleList getValue()
   {
      doubleList result = new doubleList();
      
      for (Item obj : this)
      {
         result.add(obj.getValue());
      }
      
      return result;
   }

   public ItemSet hasValue(double value)
   {
      ItemSet result = new ItemSet();
      
      for (Item obj : this)
      {
         if (value == obj.getValue())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ItemSet hasValue(double lower, double upper)
   {
      ItemSet result = new ItemSet();
      
      for (Item obj : this)
      {
         if (lower <= obj.getValue() && obj.getValue() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ItemSet withValue(double value)
   {
      for (Item obj : this)
      {
         obj.setValue(value);
      }
      
      return this;
   }

   public PersonSet getBuyer()
   {
      PersonSet result = new PersonSet();
      
      for (Item obj : this)
      {
         result.add(obj.getBuyer());
      }
      
      return result;
   }

   public ItemSet hasBuyer(Object value)
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
         if (neighbors.contains(obj.getBuyer()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ItemSet withBuyer(Person value)
   {
      for (Item obj : this)
      {
         obj.withBuyer(value);
      }
      
      return this;
   }


   public static final ItemSet EMPTY_SET = new ItemSet().withFlag(ItemSet.READONLY);


   public ItemPO filterItemPO()
   {
      return new ItemPO(this.toArray(new Item[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.groupaccount.model.Item";
   }

   /**
    * Loop through the current set of Item objects and collect those Item objects where the description attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Item objects that match the parameter
    */
   public ItemSet filterDescription(String value)
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
   public ItemSet filterDescription(String lower, String upper)
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
    * Loop through the current set of Item objects and collect those Item objects where the value attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Item objects that match the parameter
    */
   public ItemSet filterValue(double value)
   {
      ItemSet result = new ItemSet();
      
      for (Item obj : this)
      {
         if (value == obj.getValue())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Item objects and collect those Item objects where the value attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Item objects that match the parameter
    */
   public ItemSet filterValue(double lower, double upper)
   {
      ItemSet result = new ItemSet();
      
      for (Item obj : this)
      {
         if (lower <= obj.getValue() && obj.getValue() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
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
}
