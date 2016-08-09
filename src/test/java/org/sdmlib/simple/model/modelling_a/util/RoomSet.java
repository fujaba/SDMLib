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
   
package org.sdmlib.simple.model.modelling_a.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.simple.model.modelling_a.Room;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.modelsets.intList;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.sdmlib.simple.model.modelling_a.util.PersonSet;
import org.sdmlib.simple.model.modelling_a.Person;
import org.sdmlib.simple.model.modelling_a.util.PupilSet;
import org.sdmlib.simple.model.modelling_a.Pupil;
import org.sdmlib.simple.model.modelling_a.util.TeacherSet;
import org.sdmlib.simple.model.modelling_a.Teacher;

public class RoomSet extends SDMSet<Room>
{

   public static final RoomSet EMPTY_SET = new RoomSet().withFlag(RoomSet.READONLY);


   public RoomPO filterRoomPO()
   {
      return new RoomPO(this.toArray(new Room[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.modelling_a.Room";
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

   @Override
   public RoomSet filter(Condition<Room> newValue) {
      RoomSet filterList = new RoomSet();
      filterItems(filterList, newValue);
      return filterList;
   }

   /**
    * Loop through the current set of Room objects and collect a list of the number attribute values. 
    * 
    * @return List of int objects reachable via number attribute
    */
   public intList getNumber()
   {
      intList result = new intList();
      
      for (Room obj : this)
      {
         result.add(obj.getNumber());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Room objects and collect those Room objects where the number attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Room objects that match the parameter
    */
   public RoomSet filterNumber(int value)
   {
      RoomSet result = new RoomSet();
      
      for (Room obj : this)
      {
         if (value == obj.getNumber())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Room objects and collect those Room objects where the number attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Room objects that match the parameter
    */
   public RoomSet filterNumber(int lower, int upper)
   {
      RoomSet result = new RoomSet();
      
      for (Room obj : this)
      {
         if (lower <= obj.getNumber() && obj.getNumber() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Room objects and assign value to the number attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Room objects now with new attribute values.
    */
   public RoomSet withNumber(int value)
   {
      for (Room obj : this)
      {
         obj.setNumber(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Room objects and collect a set of the Person objects reached via persons. 
    * 
    * @return Set of Person objects reachable via persons
    */
   public PersonSet getPersons()
   {
      PersonSet result = new PersonSet();
      
      for (Room obj : this)
      {
         result.with(obj.getPersons());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Room objects and collect all contained objects with reference persons pointing to the object passed as parameter. 
    * 
    * @param value The object required as persons neighbor of the collected results. 
    * 
    * @return Set of Person objects referring to value via persons
    */
   public RoomSet filterPersons(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getPersons()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Room object passed as parameter to the Persons attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Persons attributes.
    */
   public RoomSet withPersons(Person value)
   {
      for (Room obj : this)
      {
         obj.withPersons(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Room object passed as parameter from the Persons attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public RoomSet withoutPersons(Person value)
   {
      for (Room obj : this)
      {
         obj.withoutPersons(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Room objects and collect a set of the Pupil objects reached via currentPupils. 
    * 
    * @return Set of Pupil objects reachable via currentPupils
    */
   public PupilSet getCurrentPupils()
   {
      PupilSet result = new PupilSet();
      
      for (Room obj : this)
      {
         result.with(obj.getCurrentPupils());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Room objects and collect all contained objects with reference currentPupils pointing to the object passed as parameter. 
    * 
    * @param value The object required as currentPupils neighbor of the collected results. 
    * 
    * @return Set of Pupil objects referring to value via currentPupils
    */
   public RoomSet filterCurrentPupils(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getCurrentPupils()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Room object passed as parameter to the CurrentPupils attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their CurrentPupils attributes.
    */
   public RoomSet withCurrentPupils(Pupil value)
   {
      for (Room obj : this)
      {
         obj.withCurrentPupils(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Room object passed as parameter from the CurrentPupils attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public RoomSet withoutCurrentPupils(Pupil value)
   {
      for (Room obj : this)
      {
         obj.withoutCurrentPupils(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Room objects and collect a set of the Teacher objects reached via currentTeacher. 
    * 
    * @return Set of Teacher objects reachable via currentTeacher
    */
   public TeacherSet getCurrentTeacher()
   {
      TeacherSet result = new TeacherSet();
      
      for (Room obj : this)
      {
         result.with(obj.getCurrentTeacher());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Room objects and collect all contained objects with reference currentTeacher pointing to the object passed as parameter. 
    * 
    * @param value The object required as currentTeacher neighbor of the collected results. 
    * 
    * @return Set of Teacher objects referring to value via currentTeacher
    */
   public RoomSet filterCurrentTeacher(Object value)
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
         if (neighbors.contains(obj.getCurrentTeacher()) || (neighbors.isEmpty() && obj.getCurrentTeacher() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Room object passed as parameter to the CurrentTeacher attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their CurrentTeacher attributes.
    */
   public RoomSet withCurrentTeacher(Teacher value)
   {
      for (Room obj : this)
      {
         obj.withCurrentTeacher(value);
      }
      
      return this;
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
}
