/*
   Copyright (c) 2012 zuendorf 
   
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
   
package org.sdmlib.examples.groupAccount.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.groupAccount.GroupAccount;
import org.sdmlib.examples.groupAccount.Item;
import org.sdmlib.examples.groupAccount.Person;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.doubleList;

public class ItemSet extends LinkedHashSet<Item>
{
	private static final long serialVersionUID = 1L;

	public StringList getDescription()
   {
      StringList result = new StringList();
      
      for (Item obj : this)
      {
         result.add(obj.getDescription());
      }
      
      return result;
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

   public GroupAccountSet getParent()
   {
      GroupAccountSet result = new GroupAccountSet();
      
      for (Item obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
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

   public ItemSet withDescription(String value)
   {
      for (Item obj : this)
      {
         obj.withDescription(value);
      }
      
      return this;
   }

   public ItemSet withValue(double value)
   {
      for (Item obj : this)
      {
         obj.withValue(value);
      }
      
      return this;
   }

   public ItemSet withParent(GroupAccount value)
   {
      for (Item obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

   public ItemSet withBuyer(Person value)
   {
      for (Item obj : this)
      {
         obj.withBuyer(value);
      }
      
      return this;
   }

   public ItemSet withoutBuyer(Person value)
   {
      for (Item obj : this)
      {
         obj.withBuyer(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Item elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.groupAccount.Item";
   }


   public ItemSet with(Item value)
   {
      this.add(value);
      return this;
   }
   
   public ItemSet without(Item value)
   {
      this.remove(value);
      return this;
   }
}









