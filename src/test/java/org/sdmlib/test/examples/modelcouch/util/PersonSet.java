/*
   Copyright (c) 2016 deeptought
   
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
   
package org.sdmlib.test.examples.modelcouch.util;

import java.util.Collection;
import java.util.Collections;

import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.test.examples.modelcouch.DocumentData;
import org.sdmlib.test.examples.modelcouch.Person;
import org.sdmlib.test.examples.modelcouch.Task;

import de.uniks.networkparser.list.SimpleSet;

public class PersonSet extends SimpleSet<Person>
{

   public static final PersonSet EMPTY_SET = new PersonSet().withFlag(PersonSet.READONLY);


   public PersonPO hasPersonPO()
   {
      return new PersonPO(this.toArray(new Person[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.modelcouch.Person";
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
    * Loop through the current set of Person objects and collect a set of the Person objects reached via members. 
    * 
    * @return Set of Person objects reachable via members
    */
   public PersonSet getMembers()
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         result.addAll(obj.getMembers());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Person objects and collect all contained objects with reference members pointing to the object passed as parameter. 
    * 
    * @param value The object required as members neighbor of the collected results. 
    * 
    * @return Set of Person objects referring to value via members
    */
   public PersonSet hasMembers(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getMembers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow members reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Person objects reachable via members transitively (including the start set)
    */
   public PersonSet getMembersTransitive()
   {
      PersonSet todo = new PersonSet().with(this);
      
      PersonSet result = new PersonSet();
      
      while ( ! todo.isEmpty())
      {
         Person current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getMembers().minus(result));
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Person object passed as parameter to the Members attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Members attributes.
    */
   public PersonSet withMembers(Person value)
   {
      for (Person obj : this)
      {
         obj.withMembers(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Person object passed as parameter from the Members attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public PersonSet withoutMembers(Person value)
   {
      for (Person obj : this)
      {
         obj.withoutMembers(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Person objects and collect a set of the Person objects reached via groups. 
    * 
    * @return Set of Person objects reachable via groups
    */
   public PersonSet getGroups()
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         result.addAll(obj.getGroups());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Person objects and collect all contained objects with reference groups pointing to the object passed as parameter. 
    * 
    * @param value The object required as groups neighbor of the collected results. 
    * 
    * @return Set of Person objects referring to value via groups
    */
   public PersonSet hasGroups(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getGroups()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow groups reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Person objects reachable via groups transitively (including the start set)
    */
   public PersonSet getGroupsTransitive()
   {
      PersonSet todo = new PersonSet().with(this);
      
      PersonSet result = new PersonSet();
      
      while ( ! todo.isEmpty())
      {
         Person current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getGroups().minus(result));
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Person object passed as parameter to the Groups attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Groups attributes.
    */
   public PersonSet withGroups(Person value)
   {
      for (Person obj : this)
      {
         obj.withGroups(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Person object passed as parameter from the Groups attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public PersonSet withoutGroups(Person value)
   {
      for (Person obj : this)
      {
         obj.withoutGroups(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Person objects and collect a set of the Task objects reached via tasks. 
    * 
    * @return Set of Task objects reachable via tasks
    */
   public TaskSet getTasks()
   {
      TaskSet result = new TaskSet();
      
      for (Person obj : this)
      {
         result.addAll(obj.getTasks());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Person objects and collect all contained objects with reference tasks pointing to the object passed as parameter. 
    * 
    * @param value The object required as tasks neighbor of the collected results. 
    * 
    * @return Set of Task objects referring to value via tasks
    */
   public PersonSet hasTasks(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getTasks()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Person object passed as parameter to the Tasks attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Tasks attributes.
    */
   public PersonSet withTasks(Task value)
   {
      for (Person obj : this)
      {
         obj.withTasks(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Person object passed as parameter from the Tasks attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public PersonSet withoutTasks(Task value)
   {
      for (Person obj : this)
      {
         obj.withoutTasks(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Person objects and collect a set of the DocumentData objects reached via personData. 
    * 
    * @return Set of DocumentData objects reachable via personData
    */
   public DocumentDataSet getPersonData()
   {
      DocumentDataSet result = new DocumentDataSet();
      
      for (Person obj : this)
      {
         result.addAll(obj.getPersonData());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Person objects and collect all contained objects with reference personData pointing to the object passed as parameter. 
    * 
    * @param value The object required as personData neighbor of the collected results. 
    * 
    * @return Set of DocumentData objects referring to value via personData
    */
   public PersonSet hasPersonData(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getPersonData()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Person object passed as parameter to the PersonData attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their PersonData attributes.
    */
   public PersonSet withPersonData(DocumentData value)
   {
      for (Person obj : this)
      {
         obj.withPersonData(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Person object passed as parameter from the PersonData attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public PersonSet withoutPersonData(DocumentData value)
   {
      for (Person obj : this)
      {
         obj.withoutPersonData(value);
      }
      
      return this;
   }

}
