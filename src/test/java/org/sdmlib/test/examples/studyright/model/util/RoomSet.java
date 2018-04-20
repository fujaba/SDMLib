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
   
package org.sdmlib.test.examples.studyright.model.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.intList;
import org.sdmlib.test.examples.studyright.model.Assignment;
import org.sdmlib.test.examples.studyright.model.Lecture;
import org.sdmlib.test.examples.studyright.model.Room;
import org.sdmlib.test.examples.studyright.model.Student;
import org.sdmlib.test.examples.studyright.model.University;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;
import de.uniks.networkparser.list.NumberList;
import org.sdmlib.test.examples.studyright.model.util.AssignmentSet;

public class RoomSet extends SimpleSet<Room>
{


   public RoomPO hasRoomPO()
   {
      return new RoomPO(this.toArray(new Room[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public RoomSet with(Object value)
   {
      if (value instanceof java.util.Collection)
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

   
   //==========================================================================
   
   public intList studentCount()
   {
      intList result = new intList();
      for (Room obj : this)
      {
         result.add(obj.studentCount());
      }
      return result;
   }

   public StringList getRoomNo()
   {
      StringList result = new StringList();
      
      for (Room obj : this)
      {
         result.add(obj.getRoomNo());
      }
      
      return result;
   }

   public RoomSet hasRoomNo(String value)
   {
      RoomSet result = new RoomSet();
      
      for (Room obj : this)
      {
         if (value.equals(obj.getRoomNo()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public RoomSet withRoomNo(String value)
   {
      for (Room obj : this)
      {
         obj.setRoomNo(value);
      }
      
      return this;
   }

   public intList getCredits()
   {
      intList result = new intList();
      
      for (Room obj : this)
      {
         result.add(obj.getCredits());
      }
      
      return result;
   }

   public RoomSet hasCredits(int value)
   {
      RoomSet result = new RoomSet();
      
      for (Room obj : this)
      {
         if (value == obj.getCredits())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public RoomSet withCredits(int value)
   {
      for (Room obj : this)
      {
         obj.setCredits(value);
      }
      
      return this;
   }

   public RoomSet getNeighbors()
   {
      RoomSet result = new RoomSet();
      
      for (Room obj : this)
      {
         result.addAll(obj.getNeighbors());
      }
      
      return result;
   }

   public RoomSet hasNeighbors(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getNeighbors()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public RoomSet getNeighborsTransitive()
   {
      RoomSet todo = new RoomSet().with(this);
      
      RoomSet result = new RoomSet();
      
      while ( ! todo.isEmpty())
      {
         Room current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getNeighbors().minus(result));
         }
      }
      
      return result;
   }

   public RoomSet withNeighbors(Room value)
   {
      for (Room obj : this)
      {
         obj.withNeighbors(value);
      }
      
      return this;
   }

   public RoomSet withoutNeighbors(Room value)
   {
      for (Room obj : this)
      {
         obj.withoutNeighbors(value);
      }
      
      return this;
   }

   public LectureSet getLecture()
   {
      LectureSet result = new LectureSet();
      
      for (Room obj : this)
      {
         result.addAll(obj.getLecture());
      }
      
      return result;
   }

   public RoomSet hasLecture(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getLecture()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public RoomSet withLecture(Lecture value)
   {
      for (Room obj : this)
      {
         obj.withLecture(value);
      }
      
      return this;
   }

   public RoomSet withoutLecture(Lecture value)
   {
      for (Room obj : this)
      {
         obj.withoutLecture(value);
      }
      
      return this;
   }

   public UniversitySet getUni()
   {
      UniversitySet result = new UniversitySet();
      
      for (Room obj : this)
      {
         result.add(obj.getUni());
      }
      
      return result;
   }

   public RoomSet hasUni(Object value)
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
         if (neighbors.contains(obj.getUni()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public RoomSet withUni(University value)
   {
      for (Room obj : this)
      {
         obj.withUni(value);
      }
      
      return this;
   }

   
   //==========================================================================
   
   public RoomSet findPath(String p0, int p1)
   {
      for (Room obj : this)
      {
         obj.findPath(p0, p1);
      }
      return this;
   }

   public StudentSet getStudents()
   {
      StudentSet result = new StudentSet();
      
      for (Room obj : this)
      {
         result.addAll(obj.getStudents());
      }
      
      return result;
   }

   public RoomSet hasStudents(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getStudents()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public RoomSet withStudents(Student value)
   {
      for (Room obj : this)
      {
         obj.withStudents(value);
      }
      
      return this;
   }

   public RoomSet withoutStudents(Student value)
   {
      for (Room obj : this)
      {
         obj.withoutStudents(value);
      }
      
      return this;
   }

   public AssignmentSet getRoom()
   {
      AssignmentSet result = new AssignmentSet();
      
      for (Room obj : this)
      {
         result.add(obj.getRoom());
      }
      
      return result;
   }

   public RoomSet hasRoom(Object value)
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
         if (neighbors.contains(obj.getRoom()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public RoomSet withRoom(Assignment value)
   {
      for (Room obj : this)
      {
         obj.withRoom(value);
      }
      
      return this;
   }


   public static final RoomSet EMPTY_SET = new RoomSet().withFlag(RoomSet.READONLY);
   public RoomSet hasRoomNo(String lower, String upper)
   {
      RoomSet result = new RoomSet();
      
      for (Room obj : this)
      {
         if (lower.compareTo(obj.getRoomNo()) <= 0 && obj.getRoomNo().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public RoomSet hasCredits(int lower, int upper)
   {
      RoomSet result = new RoomSet();
      
      for (Room obj : this)
      {
         if (lower <= obj.getCredits() && obj.getCredits() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public RoomPO filterRoomPO()
   {
      return new RoomPO(this.toArray(new Room[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.studyright.model.Room";
   }

   /**
    * Loop through the current set of Room objects and collect those Room objects where the roomNo attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Room objects that match the parameter
    */
   public RoomSet filterRoomNo(String value)
   {
      RoomSet result = new RoomSet();
      
      for (Room obj : this)
      {
         if (value.equals(obj.getRoomNo()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Room objects and collect those Room objects where the roomNo attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Room objects that match the parameter
    */
   public RoomSet filterRoomNo(String lower, String upper)
   {
      RoomSet result = new RoomSet();
      
      for (Room obj : this)
      {
         if (lower.compareTo(obj.getRoomNo()) <= 0 && obj.getRoomNo().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Room objects and collect those Room objects where the credits attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Room objects that match the parameter
    */
   public RoomSet filterCredits(int value)
   {
      RoomSet result = new RoomSet();
      
      for (Room obj : this)
      {
         if (value == obj.getCredits())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Room objects and collect those Room objects where the credits attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Room objects that match the parameter
    */
   public RoomSet filterCredits(int lower, int upper)
   {
      RoomSet result = new RoomSet();
      
      for (Room obj : this)
      {
         if (lower <= obj.getCredits() && obj.getCredits() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
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


   public RoomPO createRoomPO()
   {
      return new RoomPO(this.toArray(new Room[this.size()]));
   }


   @Override
   public RoomSet getNewList(boolean keyValue)
   {
      return new RoomSet();
   }

   /**
    * Loop through the current set of Room objects and collect those Room objects where the credits attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Room objects that match the parameter
    */
   public RoomSet createCreditsCondition(int value)
   {
      RoomSet result = new RoomSet();
      
      for (Room obj : this)
      {
         if (value == obj.getCredits())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Room objects and collect those Room objects where the credits attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Room objects that match the parameter
    */
   public RoomSet createCreditsCondition(int lower, int upper)
   {
      RoomSet result = new RoomSet();
      
      for (Room obj : this)
      {
         if (lower <= obj.getCredits() && obj.getCredits() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Room objects and collect those Room objects where the roomNo attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Room objects that match the parameter
    */
   public RoomSet createRoomNoCondition(String value)
   {
      RoomSet result = new RoomSet();
      
      for (Room obj : this)
      {
         if (value.equals(obj.getRoomNo()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Room objects and collect those Room objects where the roomNo attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Room objects that match the parameter
    */
   public RoomSet createRoomNoCondition(String lower, String upper)
   {
      RoomSet result = new RoomSet();
      
      for (Room obj : this)
      {
         if (lower.compareTo(obj.getRoomNo()) <= 0 && obj.getRoomNo().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
