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
   
package org.sdmlib.test.examples.studyrightWithAssignments.model.util;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Assignment;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.NumberList;
import java.util.Collections;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.StudentSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.RoomSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;

public class AssignmentSet extends SimpleSet<Assignment>
{
	protected Class<?> getTypClass() {
		return Assignment.class;
	}

   public AssignmentSet()
   {
      // empty
   }

   public AssignmentSet(Assignment... objects)
   {
      for (Assignment obj : objects)
      {
         this.add(obj);
      }
   }

   public AssignmentSet(Collection<Assignment> objects)
   {
      this.addAll(objects);
   }

   public static final AssignmentSet EMPTY_SET = new AssignmentSet().withFlag(AssignmentSet.READONLY);


   public AssignmentPO createAssignmentPO()
   {
      return new AssignmentPO(this.toArray(new Assignment[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.studyrightWithAssignments.model.Assignment";
   }


   public AssignmentSet filter(Condition<Assignment> condition) {
      AssignmentSet filterList = new AssignmentSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public AssignmentSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Assignment>)value);
      }
      else if (value != null)
      {
         this.add((Assignment) value);
      }
      
      return this;
   }
   
   public AssignmentSet without(Assignment value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Assignment objects and collect a list of the content attribute values. 
    * 
    * @return List of String objects reachable via content attribute
    */
   public ObjectSet getContent()
   {
      ObjectSet result = new ObjectSet();
      
      for (Assignment obj : this)
      {
         result.add(obj.getContent());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Assignment objects and collect those Assignment objects where the content attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Assignment objects that match the parameter
    */
   public AssignmentSet filterContent(String value)
   {
      AssignmentSet result = new AssignmentSet();
      
      for (Assignment obj : this)
      {
         if (value.equals(obj.getContent()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Assignment objects and collect those Assignment objects where the content attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Assignment objects that match the parameter
    */
   public AssignmentSet filterContent(String lower, String upper)
   {
      AssignmentSet result = new AssignmentSet();
      
      for (Assignment obj : this)
      {
         if (lower.compareTo(obj.getContent()) <= 0 && obj.getContent().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Assignment objects and assign value to the content attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Assignment objects now with new attribute values.
    */
   public AssignmentSet withContent(String value)
   {
      for (Assignment obj : this)
      {
         obj.setContent(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Assignment objects and collect a list of the points attribute values. 
    * 
    * @return List of int objects reachable via points attribute
    */
   public NumberList getPoints()
   {
      NumberList result = new NumberList();
      
      for (Assignment obj : this)
      {
         result.add(obj.getPoints());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Assignment objects and collect those Assignment objects where the points attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Assignment objects that match the parameter
    */
   public AssignmentSet filterPoints(int value)
   {
      AssignmentSet result = new AssignmentSet();
      
      for (Assignment obj : this)
      {
         if (value == obj.getPoints())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Assignment objects and collect those Assignment objects where the points attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Assignment objects that match the parameter
    */
   public AssignmentSet filterPoints(int lower, int upper)
   {
      AssignmentSet result = new AssignmentSet();
      
      for (Assignment obj : this)
      {
         if (lower <= obj.getPoints() && obj.getPoints() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Assignment objects and assign value to the points attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Assignment objects now with new attribute values.
    */
   public AssignmentSet withPoints(int value)
   {
      for (Assignment obj : this)
      {
         obj.setPoints(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Assignment objects and collect a set of the Student objects reached via students. 
    * 
    * @return Set of Student objects reachable via students
    */
   public StudentSet getStudents()
   {
      StudentSet result = new StudentSet();
      
      for (Assignment obj : this)
      {
         result.with(obj.getStudents());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Assignment objects and collect all contained objects with reference students pointing to the object passed as parameter. 
    * 
    * @param value The object required as students neighbor of the collected results. 
    * 
    * @return Set of Student objects referring to value via students
    */
   public AssignmentSet filterStudents(Object value)
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
      
      AssignmentSet answer = new AssignmentSet();
      
      for (Assignment obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getStudents()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Assignment object passed as parameter to the Students attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Students attributes.
    */
   public AssignmentSet withStudents(Student value)
   {
      for (Assignment obj : this)
      {
         obj.withStudents(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Assignment object passed as parameter from the Students attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public AssignmentSet withoutStudents(Student value)
   {
      for (Assignment obj : this)
      {
         obj.withoutStudents(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Assignment objects and collect a set of the Room objects reached via room. 
    * 
    * @return Set of Room objects reachable via room
    */
   public RoomSet getRoom()
   {
      RoomSet result = new RoomSet();
      
      for (Assignment obj : this)
      {
         result.with(obj.getRoom());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Assignment objects and collect all contained objects with reference room pointing to the object passed as parameter. 
    * 
    * @param value The object required as room neighbor of the collected results. 
    * 
    * @return Set of Room objects referring to value via room
    */
   public AssignmentSet filterRoom(Object value)
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
      
      AssignmentSet answer = new AssignmentSet();
      
      for (Assignment obj : this)
      {
         if (neighbors.contains(obj.getRoom()) || (neighbors.isEmpty() && obj.getRoom() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Assignment object passed as parameter to the Room attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Room attributes.
    */
   public AssignmentSet withRoom(Room value)
   {
      for (Assignment obj : this)
      {
         obj.withRoom(value);
      }
      
      return this;
   }

}
