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
   
package org.sdmlib.simple.model.association_i.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.simple.model.association_i.Teacher;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.modelsets.ObjectSet;
import java.util.Collections;
import org.sdmlib.simple.model.association_i.util.PersonSet;
import org.sdmlib.simple.model.association_i.Person;
import org.sdmlib.simple.model.association_i.util.RoomSet;
import org.sdmlib.simple.model.association_i.Room;

public class TeacherSet extends SDMSet<Teacher>
{

   public static final TeacherSet EMPTY_SET = new TeacherSet().withFlag(TeacherSet.READONLY);


   public TeacherPO filterTeacherPO()
   {
      return new TeacherPO(this.toArray(new Teacher[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.association_i.Teacher";
   }


   @SuppressWarnings("unchecked")
   public TeacherSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Teacher>)value);
      }
      else if (value != null)
      {
         this.add((Teacher) value);
      }
      
      return this;
   }
   
   public TeacherSet without(Teacher value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public TeacherSet filter(Condition<Teacher> newValue) {
      TeacherSet filterList = new TeacherSet();
      filterItems(filterList, newValue);
      return filterList;
   }
   /**
    * Loop through the current set of Teacher objects and collect a set of the Person objects reached via persons. 
    * 
    * @return Set of Person objects reachable via persons
    */
   public PersonSet getPersons()
   {
      PersonSet result = new PersonSet();
      
      for (Teacher obj : this)
      {
         result.with(obj.getPersons());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Teacher objects and collect all contained objects with reference persons pointing to the object passed as parameter. 
    * 
    * @param value The object required as persons neighbor of the collected results. 
    * 
    * @return Set of Person objects referring to value via persons
    */
   public TeacherSet filterPersons(Object value)
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
      
      TeacherSet answer = new TeacherSet();
      
      for (Teacher obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPersons()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Teacher object passed as parameter to the Persons attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Persons attributes.
    */
   public TeacherSet withPersons(Person value)
   {
      for (Teacher obj : this)
      {
         obj.withPersons(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Teacher object passed as parameter from the Persons attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TeacherSet withoutPersons(Person value)
   {
      for (Teacher obj : this)
      {
         obj.withoutPersons(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Teacher objects and collect a set of the Room objects reached via rooms. 
    * 
    * @return Set of Room objects reachable via rooms
    */
   public RoomSet getRooms()
   {
      RoomSet result = new RoomSet();
      
      for (Teacher obj : this)
      {
         result.with(obj.getRooms());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Teacher objects and collect all contained objects with reference rooms pointing to the object passed as parameter. 
    * 
    * @param value The object required as rooms neighbor of the collected results. 
    * 
    * @return Set of Room objects referring to value via rooms
    */
   public TeacherSet filterRooms(Object value)
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
      
      TeacherSet answer = new TeacherSet();
      
      for (Teacher obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getRooms()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Teacher object passed as parameter to the Rooms attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Rooms attributes.
    */
   public TeacherSet withRooms(Room value)
   {
      for (Teacher obj : this)
      {
         obj.withRooms(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Teacher object passed as parameter from the Rooms attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TeacherSet withoutRooms(Room value)
   {
      for (Teacher obj : this)
      {
         obj.withoutRooms(value);
      }
      
      return this;
   }

}
