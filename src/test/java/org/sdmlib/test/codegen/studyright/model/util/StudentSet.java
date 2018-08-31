/*
   Copyright (c) 2018 zuend
   
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
   
package org.sdmlib.test.codegen.studyright.model.util;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.codegen.studyright.model.Student;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.test.codegen.studyright.model.util.UniversitySet;
import org.sdmlib.test.codegen.studyright.model.University;

public class StudentSet extends SimpleSet<Student>
{
	public Class<?> getTypClass() {
		return Student.class;
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

   public static final StudentSet EMPTY_SET = new StudentSet().withFlag(StudentSet.READONLY);


   public String getEntryType()
   {
      return "org.sdmlib.test.codegen.studyright.model.Student";
   }


   @Override
   public StudentSet getNewList(boolean keyValue)
   {
      return new StudentSet();
   }


   @SuppressWarnings("unchecked")
   public StudentSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
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


   /**
    * Loop through the current set of Student objects and collect a list of the assignmentPoints attribute values. 
    * 
    * @return List of int objects reachable via assignmentPoints attribute
    */
   public NumberList getAssignmentPoints()
   {
      NumberList result = new NumberList();
      
      for (Student obj : this)
      {
         result.add(obj.getAssignmentPoints());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and collect those Student objects where the assignmentPoints attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Student objects that match the parameter
    */
   public StudentSet createAssignmentPointsCondition(int value)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (value == obj.getAssignmentPoints())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and collect those Student objects where the assignmentPoints attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Student objects that match the parameter
    */
   public StudentSet createAssignmentPointsCondition(int lower, int upper)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (lower <= obj.getAssignmentPoints() && obj.getAssignmentPoints() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and assign value to the assignmentPoints attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Student objects now with new attribute values.
    */
   public StudentSet withAssignmentPoints(int value)
   {
      for (Student obj : this)
      {
         obj.setAssignmentPoints(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Student objects and collect a list of the credits attribute values. 
    * 
    * @return List of int objects reachable via credits attribute
    */
   public NumberList getCredits()
   {
      NumberList result = new NumberList();
      
      for (Student obj : this)
      {
         result.add(obj.getCredits());
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
   public StudentSet createCreditsCondition(int value)
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
   public StudentSet createCreditsCondition(int lower, int upper)
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
    * Loop through the current set of Student objects and assign value to the credits attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Student objects now with new attribute values.
    */
   public StudentSet withCredits(int value)
   {
      for (Student obj : this)
      {
         obj.setCredits(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Student objects and collect a list of the id attribute values. 
    * 
    * @return List of String objects reachable via id attribute
    */
   public ObjectSet getId()
   {
      ObjectSet result = new ObjectSet();
      
      for (Student obj : this)
      {
         result.add(obj.getId());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and collect those Student objects where the id attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Student objects that match the parameter
    */
   public StudentSet createIdCondition(String value)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (value.equals(obj.getId()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and collect those Student objects where the id attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Student objects that match the parameter
    */
   public StudentSet createIdCondition(String lower, String upper)
   {
      StudentSet result = new StudentSet();
      
      for (Student obj : this)
      {
         if (lower.compareTo(obj.getId()) <= 0 && obj.getId().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and assign value to the id attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Student objects now with new attribute values.
    */
   public StudentSet withId(String value)
   {
      for (Student obj : this)
      {
         obj.setId(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Student objects and collect a list of the motivation attribute values. 
    * 
    * @return List of int objects reachable via motivation attribute
    */
   public NumberList getMotivation()
   {
      NumberList result = new NumberList();
      
      for (Student obj : this)
      {
         result.add(obj.getMotivation());
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
   public StudentSet createMotivationCondition(int value)
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
   public StudentSet createMotivationCondition(int lower, int upper)
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


   /**
    * Loop through the current set of Student objects and assign value to the motivation attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Student objects now with new attribute values.
    */
   public StudentSet withMotivation(int value)
   {
      for (Student obj : this)
      {
         obj.setMotivation(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Student objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public ObjectSet getName()
   {
      ObjectSet result = new ObjectSet();
      
      for (Student obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Student objects and collect those Student objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Student objects that match the parameter
    */
   public StudentSet createNameCondition(String value)
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
   public StudentSet createNameCondition(String lower, String upper)
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
    * Loop through the current set of Student objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Student objects now with new attribute values.
    */
   public StudentSet withName(String value)
   {
      for (Student obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Student objects and collect a set of the University objects reached via university. 
    * 
    * @return Set of University objects reachable via university
    */
   public UniversitySet getUniversity()
   {
      UniversitySet result = new UniversitySet();
      
      for (Student obj : this)
      {
         result.with(obj.getUniversity());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Student objects and collect all contained objects with reference university pointing to the object passed as parameter. 
    * 
    * @param value The object required as university neighbor of the collected results. 
    * 
    * @return Set of University objects referring to value via university
    */
   public StudentSet filterUniversity(Object value)
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
         if (neighbors.contains(obj.getUniversity()) || (neighbors.isEmpty() && obj.getUniversity() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Student object passed as parameter to the University attribute of each of it. 
    * 
    * @param value value    * @return The original set of ModelType objects now with the new neighbor attached to their University attributes.
    */
   public StudentSet withUniversity(University value)
   {
      for (Student obj : this)
      {
         obj.withUniversity(value);
      }
      
      return this;
   }

}
