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
import java.util.Collections;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.doubleList;
import org.sdmlib.test.examples.groupaccount.model.GroupAccount;
import org.sdmlib.test.examples.groupaccount.model.Item;
import org.sdmlib.test.examples.groupaccount.model.Person;
import org.sdmlib.test.examples.groupaccount.model.util.PersonSet;
import org.sdmlib.test.examples.groupaccount.model.util.ItemSet;
import org.sdmlib.models.modelsets.StringList;

public class GroupAccountSet extends SDMSet<GroupAccount>
{


   public GroupAccountPO hasGroupAccountPO()
   {
      return new GroupAccountPO(this.toArray(new GroupAccount[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.test.examples.groupAccount.model.GroupAccount";
   }


   @SuppressWarnings("unchecked")
   public GroupAccountSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<GroupAccount>)value);
      }
      else if (value != null)
      {
         this.add((GroupAccount) value);
      }
      
      return this;
   }
   
   public GroupAccountSet without(GroupAccount value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public GroupAccountSet updateBalances()
   {
      for (GroupAccount obj : this)
      {
         obj.updateBalances();
      }
      return this;
   }

   /**
    * Loop through the current set of GroupAccount objects and collect the content of the persons attributes. 
    * 
    * @return Set of Person objects reachable via persons attribute
    */
   public PersonSet getPersons()
   {
      PersonSet result = new PersonSet();
      
      for (GroupAccount obj : this)
      {
         result.addAll(obj.getPersons());
      }
      
      return result;
   }

   public GroupAccountSet hasPersons(Object value)
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
      
      GroupAccountSet answer = new GroupAccountSet();
      
      for (GroupAccount obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPersons()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public GroupAccountSet withPersons(Person value)
   {
      for (GroupAccount obj : this)
      {
         obj.withPersons(value);
      }
      
      return this;
   }

   public GroupAccountSet withoutPersons(Person value)
   {
      for (GroupAccount obj : this)
      {
         obj.withoutPersons(value);
      }
      
      return this;
   }

   public static final GroupAccountSet EMPTY_SET = new GroupAccountSet().withReadOnly(true);


   /**
    * Loop through the current set of GroupAccount objects and collect a list of the task attribute values. 
    * 
    * @return List of String objects reachable via persons attribute
    */
   public StringList getTask()
   {
      StringList result = new StringList();
      
      for (GroupAccount obj : this)
      {
         result.add(obj.getTask());
      }
      
      return result;
   }


   /**
    * Loop through the current set of GroupAccount objects and collect those GroupAccount objects where the task attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of GroupAccount objects that match the parameter
    */
   public GroupAccountSet hasTask(String value)
   {
      GroupAccountSet result = new GroupAccountSet();
      
      for (GroupAccount obj : this)
      {
         if (value.equals(obj.getTask()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GroupAccount objects and collect those GroupAccount objects where the task attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of GroupAccount objects that match the parameter
    */
   public GroupAccountSet hasTask(String lower, String upper)
   {
      GroupAccountSet result = new GroupAccountSet();
      
      for (GroupAccount obj : this)
      {
         if (lower.compareTo(obj.getTask()) <= 0 && obj.getTask().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GroupAccount objects and assign value to the task attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of GroupAccount objects now with new attribute values.
    */
   public GroupAccountSet withTask(String value)
   {
      for (GroupAccount obj : this)
      {
         obj.setTask(value);
      }
      
      return this;
   }

}
