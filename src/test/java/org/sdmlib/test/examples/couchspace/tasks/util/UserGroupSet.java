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
   
package org.sdmlib.test.examples.couchspace.tasks.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.test.examples.couchspace.tasks.Task;
import org.sdmlib.test.examples.couchspace.tasks.User;
import org.sdmlib.test.examples.couchspace.tasks.UserGroup;

import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.StringList;

public class UserGroupSet extends SDMSet<UserGroup>
{

   public static final UserGroupSet EMPTY_SET = new UserGroupSet().withFlag(UserGroupSet.READONLY);


   public UserGroupPO filterUserGroupPO()
   {
      return new UserGroupPO(this.toArray(new UserGroup[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.couchspace.tasks.UserGroup";
   }


   @SuppressWarnings("unchecked")
   public UserGroupSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<UserGroup>)value);
      }
      else if (value != null)
      {
         this.add((UserGroup) value);
      }
      
      return this;
   }
   
   public UserGroupSet without(UserGroup value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public UserGroupSet filter(Condition<UserGroup> newValue) {
      UserGroupSet filterList = new UserGroupSet();
      filterItems(filterList, newValue);
      return filterList;
   }

   /**
    * Loop through the current set of UserGroup objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (UserGroup obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of UserGroup objects and collect those UserGroup objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of UserGroup objects that match the parameter
    */
   public UserGroupSet filterName(String value)
   {
      UserGroupSet result = new UserGroupSet();
      
      for (UserGroup obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of UserGroup objects and collect those UserGroup objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of UserGroup objects that match the parameter
    */
   public UserGroupSet filterName(String lower, String upper)
   {
      UserGroupSet result = new UserGroupSet();
      
      for (UserGroup obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of UserGroup objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of UserGroup objects now with new attribute values.
    */
   public UserGroupSet withName(String value)
   {
      for (UserGroup obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of UserGroup objects and collect a set of the Task objects reached via responsible. 
    * 
    * @return Set of Task objects reachable via responsible
    */
   public TaskSet getResponsible()
   {
      TaskSet result = new TaskSet();
      
      for (UserGroup obj : this)
      {
         result.with(obj.getResponsible());
      }
      
      return result;
   }

   /**
    * Loop through the current set of UserGroup objects and collect all contained objects with reference responsible pointing to the object passed as parameter. 
    * 
    * @param value The object required as responsible neighbor of the collected results. 
    * 
    * @return Set of Task objects referring to value via responsible
    */
   public UserGroupSet filterResponsible(Object value)
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
      
      UserGroupSet answer = new UserGroupSet();
      
      for (UserGroup obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getResponsible()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the UserGroup object passed as parameter to the Responsible attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Responsible attributes.
    */
   public UserGroupSet withResponsible(Task value)
   {
      for (UserGroup obj : this)
      {
         obj.withResponsible(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the UserGroup object passed as parameter from the Responsible attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public UserGroupSet withoutResponsible(Task value)
   {
      for (UserGroup obj : this)
      {
         obj.withoutResponsible(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of UserGroup objects and collect a set of the User objects reached via members. 
    * 
    * @return Set of User objects reachable via members
    */
   public UserSet getMembers()
   {
      UserSet result = new UserSet();
      
      for (UserGroup obj : this)
      {
         result.with(obj.getMembers());
      }
      
      return result;
   }

   /**
    * Loop through the current set of UserGroup objects and collect all contained objects with reference members pointing to the object passed as parameter. 
    * 
    * @param value The object required as members neighbor of the collected results. 
    * 
    * @return Set of User objects referring to value via members
    */
   public UserGroupSet filterMembers(Object value)
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
      
      UserGroupSet answer = new UserGroupSet();
      
      for (UserGroup obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getMembers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the UserGroup object passed as parameter to the Members attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Members attributes.
    */
   public UserGroupSet withMembers(User value)
   {
      for (UserGroup obj : this)
      {
         obj.withMembers(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the UserGroup object passed as parameter from the Members attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public UserGroupSet withoutMembers(User value)
   {
      for (UserGroup obj : this)
      {
         obj.withoutMembers(value);
      }
      
      return this;
   }


   public UserGroupSet()
   {
      // empty
   }

   public UserGroupSet(UserGroup... objects)
   {
      for (UserGroup obj : objects)
      {
         this.add(obj);
      }
   }

   public UserGroupSet(Collection<UserGroup> objects)
   {
      this.addAll(objects);
   }
}
