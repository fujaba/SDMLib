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
   
package org.sdmlib.simple.model.association_h.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.simple.model.association_h.Person;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.sdmlib.simple.model.association_h.util.RoomSet;
import org.sdmlib.simple.model.association_h.Room;
import org.sdmlib.simple.model.association_h.util.TeacherSet;
import org.sdmlib.simple.model.association_h.Teacher;

public class PersonSet extends SDMSet<Person>
{

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

   public static final PersonSet EMPTY_SET = new PersonSet();


   public PersonPO filterPersonPO()
   {
      return new PersonPO(this.toArray(new Person[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.association_h.Person";
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
    * Loop through the current set of Person objects and collect a set of the Room objects reached via rooms. 
    * 
    * @return Set of Room objects reachable via rooms
    */
   public RoomSet getRooms()
   {
      RoomSet result = new RoomSet();
      
      for (Person obj : this)
      {
         result.with(obj.getRooms());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Person objects and collect all contained objects with reference rooms pointing to the object passed as parameter. 
    * 
    * @param value The object required as rooms neighbor of the collected results. 
    * 
    * @return Set of Room objects referring to value via rooms
    */
   public PersonSet filterRooms(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getRooms()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Person object passed as parameter to the Rooms attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Rooms attributes.
    */
   public PersonSet withRooms(Room value)
   {
      for (Person obj : this)
      {
         obj.withRooms(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Person object passed as parameter from the Rooms attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public PersonSet withoutRooms(Room value)
   {
      for (Person obj : this)
      {
         obj.withoutRooms(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Person objects and collect a set of the Teacher objects reached via teachers. 
    * 
    * @return Set of Teacher objects reachable via teachers
    */
   public TeacherSet getTeachers()
   {
      TeacherSet result = new TeacherSet();
      
      for (Person obj : this)
      {
         result.with(obj.getTeachers());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Person objects and collect all contained objects with reference teachers pointing to the object passed as parameter. 
    * 
    * @param value The object required as teachers neighbor of the collected results. 
    * 
    * @return Set of Teacher objects referring to value via teachers
    */
   public PersonSet filterTeachers(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getTeachers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Person object passed as parameter to the Teachers attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Teachers attributes.
    */
   public PersonSet withTeachers(Teacher value)
   {
      for (Person obj : this)
      {
         obj.withTeachers(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Person object passed as parameter from the Teachers attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public PersonSet withoutTeachers(Teacher value)
   {
      for (Person obj : this)
      {
         obj.withoutTeachers(value);
      }
      
      return this;
   }

}
