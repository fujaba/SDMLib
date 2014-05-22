/*
   Copyright (c) 2014 Stefan 
   
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
   
package org.sdmlib.examples.groupAccount.model.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.groupAccount.model.Item;
import java.util.Collection;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.doubleList;
import org.sdmlib.examples.groupAccount.model.util.GroupAccountSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.examples.groupAccount.model.GroupAccount;
import org.sdmlib.examples.groupAccount.model.util.PersonSet;
import org.sdmlib.examples.groupAccount.model.Person;

public class ItemSet extends SDMSet<Item>
{


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.groupAccount.model.Item";
   }


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

   public ItemSet withDescription(String value)
   {
      for (Item obj : this)
      {
         obj.setDescription(value);
      }
      
      return this;
   }

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

   public ItemSet withValue(double value)
   {
      for (Item obj : this)
      {
         obj.setValue(value);
      }
      
      return this;
   }

   public GroupAccountSet getParent()
   {
      GroupAccountSet result = new GroupAccountSet();
      
      for (Item obj : this)
      {
         result.with(obj.getParent());
      }
      
      return result;
   }

   public ItemSet hasParent(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      ItemSet answer = new ItemSet();
      
      for (Item obj : this)
      {
         if (neighbors.contains(obj.getParent()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ItemSet withParent(GroupAccount value)
   {
      for (Item obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

   public PersonSet getBuyer()
   {
      PersonSet result = new PersonSet();
      
      for (Item obj : this)
      {
         result.with(obj.getBuyer());
      }
      
      return result;
   }

   public ItemSet hasBuyer(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection) value);
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

}

