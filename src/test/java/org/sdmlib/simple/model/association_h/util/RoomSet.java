/*
   Copyright (c) 2016 Stefan
   
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

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.simple.model.association_h.Room;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.simple.model.association_h.util.PersonSet;
import org.sdmlib.simple.model.association_h.Person;
import java.util.Collections;
import org.sdmlib.simple.model.association_h.util.TeacherSet;
import org.sdmlib.simple.model.association_h.Teacher;

public class RoomSet extends SimpleSet<Room>
{
	protected Class<?> getTypClass() {
		return Room.class;
	}

   public RoomSet()
   {
      // empty
   }

   public RoomSet(Room... objects)
   {
      for (Room obj : objects)
      {
         this.add(obj);
      }
   }

   public RoomSet(Collection<Room> objects)
   {
      this.addAll(objects);
   }

   public static final RoomSet EMPTY_SET = new RoomSet().withFlag(RoomSet.READONLY);


   public RoomPO createRoomPO()
   {
      return new RoomPO(this.toArray(new Room[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.association_h.Room";
   }


   @SuppressWarnings("unchecked")
   public RoomSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Room>)value);
      }
      else if (value != null)
      {
         this.add((Room) value);
      }
      
      return this;
   }
   
   public RoomSet without(Room value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of Room objects and collect a set of the Person objects reached via person. 
    * 
    * @return Set of Person objects reachable via person
    */
   public PersonSet getPerson()
   {
      PersonSet result = new PersonSet();
      
      for (Room obj : this)
      {
         result.with(obj.getPerson());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Room objects and collect all contained objects with reference person pointing to the object passed as parameter. 
    * 
    * @param value The object required as person neighbor of the collected results. 
    * 
    * @return Set of Person objects referring to value via person
    */
   public RoomSet filterPerson(Object value)
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
      
      RoomSet answer = new RoomSet();
      
      for (Room obj : this)
      {
         if (neighbors.contains(obj.getPerson()) || (neighbors.isEmpty() && obj.getPerson() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Room object passed as parameter to the Person attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Person attributes.
    */
   public RoomSet withPerson(Person value)
   {
      for (Room obj : this)
      {
         obj.withPerson(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Room objects and collect a set of the Teacher objects reached via teachers. 
    * 
    * @return Set of Teacher objects reachable via teachers
    */
   public TeacherSet getTeachers()
   {
      TeacherSet result = new TeacherSet();
      
      for (Room obj : this)
      {
         result.with(obj.getTeachers());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Room objects and collect all contained objects with reference teachers pointing to the object passed as parameter. 
    * 
    * @param value The object required as teachers neighbor of the collected results. 
    * 
    * @return Set of Teacher objects referring to value via teachers
    */
   public RoomSet filterTeachers(Object value)
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
      
      RoomSet answer = new RoomSet();
      
      for (Room obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getTeachers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Room object passed as parameter to the Teachers attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Teachers attributes.
    */
   public RoomSet withTeachers(Teacher value)
   {
      for (Room obj : this)
      {
         obj.withTeachers(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Room object passed as parameter from the Teachers attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public RoomSet withoutTeachers(Teacher value)
   {
      for (Room obj : this)
      {
         obj.withoutTeachers(value);
      }
      
      return this;
   }

}
