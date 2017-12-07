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

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.StringList;

public class UserSet extends SDMSet<User>
{

   public static final UserSet EMPTY_SET = new UserSet().withFlag(UserSet.READONLY);


   public UserPO filterUserPO()
   {
      return new UserPO(this.toArray(new User[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.couchspace.tasks.User";
   }


   @SuppressWarnings("unchecked")
   public UserSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<User>)value);
      }
      else if (value != null)
      {
         this.add((User) value);
      }
      
      return this;
   }
   
   public UserSet without(User value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of User objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (User obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of User objects and collect those User objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of User objects that match the parameter
    */
   public UserSet filterName(String value)
   {
      UserSet result = new UserSet();
      
      for (User obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of User objects and collect those User objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of User objects that match the parameter
    */
   public UserSet filterName(String lower, String upper)
   {
      UserSet result = new UserSet();
      
      for (User obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of User objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of User objects now with new attribute values.
    */
   public UserSet withName(String value)
   {
      for (User obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of User objects and collect a set of the Task objects reached via handledTasks. 
    * 
    * @return Set of Task objects reachable via handledTasks
    */
   public TaskSet getHandledTasks()
   {
      TaskSet result = new TaskSet();
      
      for (User obj : this)
      {
         result.with(obj.getHandledTasks());
      }
      
      return result;
   }

   /**
    * Loop through the current set of User objects and collect all contained objects with reference handledTasks pointing to the object passed as parameter. 
    * 
    * @param value The object required as handledTasks neighbor of the collected results. 
    * 
    * @return Set of Task objects referring to value via handledTasks
    */
   public UserSet filterHandledTasks(Object value)
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
      
      UserSet answer = new UserSet();
      
      for (User obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getHandledTasks()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the User object passed as parameter to the HandledTasks attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their HandledTasks attributes.
    */
   public UserSet withHandledTasks(Task value)
   {
      for (User obj : this)
      {
         obj.withHandledTasks(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the User object passed as parameter from the HandledTasks attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public UserSet withoutHandledTasks(Task value)
   {
      for (User obj : this)
      {
         obj.withoutHandledTasks(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of User objects and collect a set of the UserGroup objects reached via groups. 
    * 
    * @return Set of UserGroup objects reachable via groups
    */
   public UserGroupSet getGroups()
   {
      UserGroupSet result = new UserGroupSet();
      
      for (User obj : this)
      {
         result.with(obj.getGroups());
      }
      
      return result;
   }

   /**
    * Loop through the current set of User objects and collect all contained objects with reference groups pointing to the object passed as parameter. 
    * 
    * @param value The object required as groups neighbor of the collected results. 
    * 
    * @return Set of UserGroup objects referring to value via groups
    */
   public UserSet filterGroups(Object value)
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
      
      UserSet answer = new UserSet();
      
      for (User obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getGroups()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the User object passed as parameter to the Groups attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Groups attributes.
    */
   public UserSet withGroups(UserGroup value)
   {
      for (User obj : this)
      {
         obj.withGroups(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the User object passed as parameter from the Groups attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public UserSet withoutGroups(UserGroup value)
   {
      for (User obj : this)
      {
         obj.withoutGroups(value);
      }
      
      return this;
   }


   public UserSet()
   {
      // empty
   }

   public UserSet(User... objects)
   {
      for (User obj : objects)
      {
         this.add(obj);
      }
   }

   public UserSet(Collection<User> objects)
   {
      this.addAll(objects);
   }


   public UserPO createUserPO()
   {
      return new UserPO(this.toArray(new User[this.size()]));
   }


   @Override
   public UserSet getNewList(boolean keyValue)
   {
      return new UserSet();
   }

   /**
    * Loop through the current set of User objects and collect those User objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of User objects that match the parameter
    */
   public UserSet createNameCondition(String value)
   {
      UserSet result = new UserSet();
      
      for (User obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of User objects and collect those User objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of User objects that match the parameter
    */
   public UserSet createNameCondition(String lower, String upper)
   {
      UserSet result = new UserSet();
      
      for (User obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
