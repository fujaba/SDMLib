/*
   Copyright (c) 2017 zuendorf
   
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
   
package org.sdmlib.simple.model.association_g.util;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.simple.model.association_g.Teacher;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.simple.model.association_g.util.PersonSet;
import org.sdmlib.simple.model.association_g.Person;
import org.sdmlib.simple.model.association_g.util.RoomSet;
import org.sdmlib.simple.model.association_g.Room;

public class TeacherSet extends SimpleSet<Teacher>
{
	public Class<?> getTypClass() {
		return Teacher.class;
	}

   public TeacherSet()
   {
      // empty
   }

   public TeacherSet(Teacher... objects)
   {
      for (Teacher obj : objects)
      {
         this.add(obj);
      }
   }

   public TeacherSet(Collection<Teacher> objects)
   {
      this.addAll(objects);
   }

   public static final TeacherSet EMPTY_SET = new TeacherSet().withFlag(TeacherSet.READONLY);


   public TeacherPO createTeacherPO()
   {
      return new TeacherPO(this.toArray(new Teacher[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.association_g.Teacher";
   }


   @Override
   public TeacherSet getNewList(boolean keyValue)
   {
      return new TeacherSet();
   }


   public TeacherSet filter(Condition<Teacher> condition) {
      TeacherSet filterList = new TeacherSet();
      filterItems(filterList, condition);
      return filterList;
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

   /**
    * Loop through the current set of Teacher objects and collect a set of the Person objects reached via person. 
    * 
    * @return Set of Person objects reachable via person
    */
   public PersonSet getPerson()
   {
      PersonSet result = new PersonSet();
      
      for (Teacher obj : this)
      {
         result.with(obj.getPerson());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Teacher objects and collect all contained objects with reference person pointing to the object passed as parameter. 
    * 
    * @param value The object required as person neighbor of the collected results. 
    * 
    * @return Set of Person objects referring to value via person
    */
   public TeacherSet filterPerson(Object value)
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
         if (neighbors.contains(obj.getPerson()) || (neighbors.isEmpty() && obj.getPerson() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Teacher object passed as parameter to the Person attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Person attributes.
    */
   public TeacherSet withPerson(Person value)
   {
      for (Teacher obj : this)
      {
         obj.withPerson(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Teacher objects and collect a set of the Room objects reached via room. 
    * 
    * @return Set of Room objects reachable via room
    */
   public RoomSet getRoom()
   {
      RoomSet result = new RoomSet();
      
      for (Teacher obj : this)
      {
         result.with(obj.getRoom());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Teacher objects and collect all contained objects with reference room pointing to the object passed as parameter. 
    * 
    * @param value The object required as room neighbor of the collected results. 
    * 
    * @return Set of Room objects referring to value via room
    */
   public TeacherSet filterRoom(Object value)
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
         if (neighbors.contains(obj.getRoom()) || (neighbors.isEmpty() && obj.getRoom() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Teacher object passed as parameter to the Room attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Room attributes.
    */
   public TeacherSet withRoom(Room value)
   {
      for (Teacher obj : this)
      {
         obj.withRoom(value);
      }
      
      return this;
   }

}
