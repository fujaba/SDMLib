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

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.test.examples.studyright.model.Assignment;
import org.sdmlib.test.examples.studyright.model.Lecture;
import org.sdmlib.test.examples.studyright.model.Room;
import org.sdmlib.test.examples.studyright.model.Student;
import org.sdmlib.test.examples.studyright.model.University;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.studyright.model.util.AssignmentSet;

public class StudentSet extends SimpleSet<Student>
{


   public StudentPO hasStudentPO()
   {
      return new StudentPO(this.toArray(new Student[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public StudentSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Student>)value);
      }
      else if (value != null)
      {
         this.add((Student) value);
      }
      
      return this;
   }
   
   public StudentSet without(Student value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public StudentSet findMyPosition()
   {
      for (Student obj : this)
      {
         obj.findMyPosition();
      }
      return this;
   }

   
   //==========================================================================
   
   public StudentSet findMyPosition(String p0)
   {
      for (Student obj : this)
      {
         obj.findMyPosition(p0);
      }
      return this;
   }

   
   //==========================================================================
   
   public StudentSet findMyPosition(String p0, int p1)
   {
      for (Student obj : this)
      {
         obj.findMyPosition(p0, p1);
      }
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Student obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public StudentSet hasName(String value)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StudentSet withName(String value)
   {
      for (Student obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public intList getMatrNo()
   {
      intList result = new intList();
      
      for (Student obj : this)
      {
         result.add(obj.getMatrNo());
      }
      
      return result;
   }

   public StudentSet hasMatrNo(int value)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (value == obj.getMatrNo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StudentSet withMatrNo(int value)
   {
      for (Student obj : this)
      {
         obj.setMatrNo(value);
      }
      
      return this;
   }

   public LectureSet getLecture()
   {
      LectureSet result = new LectureSet();
      
      for (Student obj : this)
      {
         result.addAll(obj.getLecture());
      }
      
      return result;
   }

   public StudentSet hasLecture(Object value)
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
      
      StudentSet answer = new StudentSet();
      
      for (Student obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getLecture()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public StudentSet withLecture(Lecture value)
   {
      for (Student obj : this)
      {
         obj.withLecture(value);
      }
      
      return this;
   }

   public StudentSet withoutLecture(Lecture value)
   {
      for (Student obj : this)
      {
         obj.withoutLecture(value);
      }
      
      return this;
   }

   public intList getCredits()
   {
      intList result = new intList();
      
      for (Student obj : this)
      {
         result.add(obj.getCredits());
      }
      
      return result;
   }

   public StudentSet hasCredits(int value)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (value == obj.getCredits())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StudentSet withCredits(int value)
   {
      for (Student obj : this)
      {
         obj.setCredits(value);
      }
      
      return this;
   }

   public intList getMotivation()
   {
      intList result = new intList();
      
      for (Student obj : this)
      {
         result.add(obj.getMotivation());
      }
      
      return result;
   }

   public StudentSet hasMotivation(int value)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (value == obj.getMotivation())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StudentSet withMotivation(int value)
   {
      for (Student obj : this)
      {
         obj.setMotivation(value);
      }
      
      return this;
   }

   public UniversitySet getUni()
   {
      UniversitySet result = new UniversitySet();
      
      for (Student obj : this)
      {
         result.add(obj.getUni());
      }
      
      return result;
   }

   public StudentSet hasUni(Object value)
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
      
      StudentSet answer = new StudentSet();
      
      for (Student obj : this)
      {
         if (neighbors.contains(obj.getUni()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public StudentSet withUni(University value)
   {
      for (Student obj : this)
      {
         obj.withUni(value);
      }
      
      return this;
   }

   public RoomSet getIn()
   {
      RoomSet result = new RoomSet();
      
      for (Student obj : this)
      {
         result.add(obj.getIn());
      }
      
      return result;
   }

   public StudentSet hasIn(Object value)
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
      
      StudentSet answer = new StudentSet();
      
      for (Student obj : this)
      {
         if (neighbors.contains(obj.getIn()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public StudentSet withIn(Room value)
   {
      for (Student obj : this)
      {
         obj.withIn(value);
      }
      
      return this;
   }

   public AssignmentSet getDone()
   {
      AssignmentSet result = new AssignmentSet();
      
      for (Student obj : this)
      {
         result.addAll(obj.getDone());
      }
      
      return result;
   }

   public StudentSet hasDone(Object value)
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
      
      StudentSet answer = new StudentSet();
      
      for (Student obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getDone()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public StudentSet withDone(Assignment value)
   {
      for (Student obj : this)
      {
         obj.withDone(value);
      }
      
      return this;
   }

   public StudentSet withoutDone(Assignment value)
   {
      for (Student obj : this)
      {
         obj.withoutDone(value);
      }
      
      return this;
   }


   public static final StudentSet EMPTY_SET = new StudentSet().withFlag(StudentSet.READONLY);
   
   public StudentSet hasName(String lower, String upper)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StudentSet hasMatrNo(int lower, int upper)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (lower <= obj.getMatrNo() && obj.getMatrNo() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StudentSet hasCredits(int lower, int upper)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (lower <= obj.getCredits() && obj.getCredits() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StudentSet hasMotivation(int lower, int upper)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (lower <= obj.getMotivation() && obj.getMotivation() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public StudentPO filterStudentPO()
   {
      return new StudentPO(this.toArray(new Student[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.studyright.model.Student";
   }

   /**
    * Loop through the current set of Student objects and collect those Student objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Student objects that match the parameter
    */
   public StudentSet filterName(String value)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and collect those Student objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Student objects that match the parameter
    */
   public StudentSet filterName(String lower, String upper)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and collect those Student objects where the matrNo attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Student objects that match the parameter
    */
   public StudentSet filterMatrNo(int value)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (value == obj.getMatrNo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and collect those Student objects where the matrNo attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Student objects that match the parameter
    */
   public StudentSet filterMatrNo(int lower, int upper)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (lower <= obj.getMatrNo() && obj.getMatrNo() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and collect those Student objects where the credits attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Student objects that match the parameter
    */
   public StudentSet filterCredits(int value)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (value == obj.getCredits())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and collect those Student objects where the credits attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Student objects that match the parameter
    */
   public StudentSet filterCredits(int lower, int upper)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (lower <= obj.getCredits() && obj.getCredits() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and collect those Student objects where the motivation attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Student objects that match the parameter
    */
   public StudentSet filterMotivation(int value)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (value == obj.getMotivation())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and collect those Student objects where the motivation attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Student objects that match the parameter
    */
   public StudentSet filterMotivation(int lower, int upper)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (lower <= obj.getMotivation() && obj.getMotivation() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public StudentSet()
   {
      // empty
   }

   public StudentSet(Student... objects)
   {
      for (Student obj : objects)
      {
         this.add(obj);
      }
   }

   public StudentSet(Collection<Student> objects)
   {
      this.addAll(objects);
   }
}
