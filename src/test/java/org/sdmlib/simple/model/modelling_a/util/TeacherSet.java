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
import org.sdmlib.simple.model.modelling_a.Teacher;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.StringList;
import org.sdmlib.models.modelsets.intList;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.simple.model.modelling_a.util.RoomSet;
import org.sdmlib.simple.model.modelling_a.Room;
import java.util.Collections;
import org.sdmlib.simple.model.modelling_a.util.PupilSet;
import org.sdmlib.simple.model.modelling_a.Pupil;

public class TeacherSet extends SDMSet<Teacher>
{

   public static final TeacherSet EMPTY_SET = new TeacherSet().withFlag(TeacherSet.READONLY);


   public TeacherPO filterTeacherPO()
   {
      return new TeacherPO(this.toArray(new Teacher[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.modelling_a.Teacher";
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
   
   //==========================================================================
   
   public StringList teach()
   {
      StringList result = new StringList();
      for (Teacher obj : this)
      {
         result.add(obj.teach());
      }
      return result;
   }


   /**
    * Loop through the current set of Teacher objects and collect a list of the rank attribute values. 
    * 
    * @return List of String objects reachable via rank attribute
    */
   public StringList getRank()
   {
      StringList result = new StringList();
      
      for (Teacher obj : this)
      {
         result.add(obj.getRank());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Teacher objects and collect those Teacher objects where the rank attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Teacher objects that match the parameter
    */
   public TeacherSet filterRank(String value)
   {
      TeacherSet result = new TeacherSet();
      
      for (Teacher obj : this)
      {
         if (value.equals(obj.getRank()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Teacher objects and collect those Teacher objects where the rank attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Teacher objects that match the parameter
    */
   public TeacherSet filterRank(String lower, String upper)
   {
      TeacherSet result = new TeacherSet();
      
      for (Teacher obj : this)
      {
         if (lower.compareTo(obj.getRank()) <= 0 && obj.getRank().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Teacher objects and assign value to the rank attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Teacher objects now with new attribute values.
    */
   public TeacherSet withRank(String value)
   {
      for (Teacher obj : this)
      {
         obj.setRank(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Teacher objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Teacher obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Teacher objects and collect those Teacher objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Teacher objects that match the parameter
    */
   public TeacherSet filterName(String value)
   {
      TeacherSet result = new TeacherSet();
      
      for (Teacher obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Teacher objects and collect those Teacher objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Teacher objects that match the parameter
    */
   public TeacherSet filterName(String lower, String upper)
   {
      TeacherSet result = new TeacherSet();
      
      for (Teacher obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Teacher objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Teacher objects now with new attribute values.
    */
   public TeacherSet withName(String value)
   {
      for (Teacher obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Teacher objects and collect a list of the age attribute values. 
    * 
    * @return List of int objects reachable via age attribute
    */
   public intList getAge()
   {
      intList result = new intList();
      
      for (Teacher obj : this)
      {
         result.add(obj.getAge());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Teacher objects and collect those Teacher objects where the age attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Teacher objects that match the parameter
    */
   public TeacherSet filterAge(int value)
   {
      TeacherSet result = new TeacherSet();
      
      for (Teacher obj : this)
      {
         if (value == obj.getAge())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Teacher objects and collect those Teacher objects where the age attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Teacher objects that match the parameter
    */
   public TeacherSet filterAge(int lower, int upper)
   {
      TeacherSet result = new TeacherSet();
      
      for (Teacher obj : this)
      {
         if (lower <= obj.getAge() && obj.getAge() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Teacher objects and assign value to the age attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Teacher objects now with new attribute values.
    */
   public TeacherSet withAge(int value)
   {
      for (Teacher obj : this)
      {
         obj.setAge(value);
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

   /**
    * Loop through the current set of Teacher objects and collect a set of the Room objects reached via currentRoom. 
    * 
    * @return Set of Room objects reachable via currentRoom
    */
   public RoomSet getCurrentRoom()
   {
      RoomSet result = new RoomSet();
      
      for (Teacher obj : this)
      {
         result.with(obj.getCurrentRoom());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Teacher objects and collect all contained objects with reference currentRoom pointing to the object passed as parameter. 
    * 
    * @param value The object required as currentRoom neighbor of the collected results. 
    * 
    * @return Set of Room objects referring to value via currentRoom
    */
   public TeacherSet filterCurrentRoom(Object value)
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
         if (neighbors.contains(obj.getCurrentRoom()) || (neighbors.isEmpty() && obj.getCurrentRoom() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Teacher object passed as parameter to the CurrentRoom attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their CurrentRoom attributes.
    */
   public TeacherSet withCurrentRoom(Room value)
   {
      for (Teacher obj : this)
      {
         obj.withCurrentRoom(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Teacher objects and collect a set of the Pupil objects reached via pupils. 
    * 
    * @return Set of Pupil objects reachable via pupils
    */
   public PupilSet getPupils()
   {
      PupilSet result = new PupilSet();
      
      for (Teacher obj : this)
      {
         result.with(obj.getPupils());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Teacher objects and collect all contained objects with reference pupils pointing to the object passed as parameter. 
    * 
    * @param value The object required as pupils neighbor of the collected results. 
    * 
    * @return Set of Pupil objects referring to value via pupils
    */
   public TeacherSet filterPupils(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getPupils()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Teacher object passed as parameter to the Pupils attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Pupils attributes.
    */
   public TeacherSet withPupils(Pupil value)
   {
      for (Teacher obj : this)
      {
         obj.withPupils(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Teacher object passed as parameter from the Pupils attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TeacherSet withoutPupils(Pupil value)
   {
      for (Teacher obj : this)
      {
         obj.withoutPupils(value);
      }
      
      return this;
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
}
