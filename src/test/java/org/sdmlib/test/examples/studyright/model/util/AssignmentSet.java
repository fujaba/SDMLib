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
import org.sdmlib.test.examples.studyright.model.Room;
import org.sdmlib.test.examples.studyright.model.Student;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;
import de.uniks.networkparser.list.NumberList;
import org.sdmlib.test.examples.studyright.model.util.RoomSet;
import org.sdmlib.test.examples.studyright.model.util.StudentSet;

public class AssignmentSet extends SimpleSet<Assignment>
{


   public AssignmentPO hasAssignmentPO()
   {
      return new AssignmentPO(this.toArray(new Assignment[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public AssignmentSet with(Object value)
   {
      if (value instanceof java.util.Collection)
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

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Assignment obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public AssignmentSet hasName(String value)
   {
      AssignmentSet result = new AssignmentSet();
      
      for (Assignment obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public AssignmentSet withName(String value)
   {
      for (Assignment obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public intList getPoints()
   {
      intList result = new intList();
      
      for (Assignment obj : this)
      {
         result.add(obj.getPoints());
      }
      
      return result;
   }

   public AssignmentSet hasPoints(int value)
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

   public AssignmentSet withPoints(int value)
   {
      for (Assignment obj : this)
      {
         obj.setPoints(value);
      }
      
      return this;
   }

   public RoomSet getAssignments()
   {
      RoomSet result = new RoomSet();
      
      for (Assignment obj : this)
      {
         result.addAll(obj.getAssignments());
      }
      
      return result;
   }

   public AssignmentSet hasAssignments(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getAssignments()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public AssignmentSet withAssignments(Room value)
   {
      for (Assignment obj : this)
      {
         obj.withAssignments(value);
      }
      
      return this;
   }

   public AssignmentSet withoutAssignments(Room value)
   {
      for (Assignment obj : this)
      {
         obj.withoutAssignments(value);
      }
      
      return this;
   }

   public StudentSet getStudents()
   {
      StudentSet result = new StudentSet();
      
      for (Assignment obj : this)
      {
         result.add(obj.getStudents());
      }
      
      return result;
   }

   public AssignmentSet hasStudents(Object value)
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
         if (neighbors.contains(obj.getStudents()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public AssignmentSet withStudents(Student value)
   {
      for (Assignment obj : this)
      {
         obj.withStudents(value);
      }
      
      return this;
   }


   public static final AssignmentSet EMPTY_SET = new AssignmentSet().withFlag(AssignmentSet.READONLY);
   public AssignmentSet hasName(String lower, String upper)
   {
      AssignmentSet result = new AssignmentSet();
      
      for (Assignment obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public AssignmentSet hasPoints(int lower, int upper)
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



   public AssignmentPO filterAssignmentPO()
   {
      return new AssignmentPO(this.toArray(new Assignment[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.studyright.model.Assignment";
   }

   /**
    * Loop through the current set of Assignment objects and collect those Assignment objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Assignment objects that match the parameter
    */
   public AssignmentSet filterName(String value)
   {
      AssignmentSet result = new AssignmentSet();
      
      for (Assignment obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Assignment objects and collect those Assignment objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Assignment objects that match the parameter
    */
   public AssignmentSet filterName(String lower, String upper)
   {
      AssignmentSet result = new AssignmentSet();
      
      for (Assignment obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
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


   public AssignmentPO createAssignmentPO()
   {
      return new AssignmentPO(this.toArray(new Assignment[this.size()]));
   }


   @Override
   public AssignmentSet getNewList(boolean keyValue)
   {
      return new AssignmentSet();
   }

   /**
    * Loop through the current set of Assignment objects and collect those Assignment objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Assignment objects that match the parameter
    */
   public AssignmentSet createNameCondition(String value)
   {
      AssignmentSet result = new AssignmentSet();
      
      for (Assignment obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Assignment objects and collect those Assignment objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Assignment objects that match the parameter
    */
   public AssignmentSet createNameCondition(String lower, String upper)
   {
      AssignmentSet result = new AssignmentSet();
      
      for (Assignment obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
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
   public AssignmentSet createPointsCondition(int value)
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
   public AssignmentSet createPointsCondition(int lower, int upper)
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

}
