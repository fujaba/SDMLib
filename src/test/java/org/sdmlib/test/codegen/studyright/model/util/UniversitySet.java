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

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.test.codegen.studyright.model.Student;
import org.sdmlib.test.codegen.studyright.model.University;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class UniversitySet extends SimpleSet<University>
{
	public Class<?> getTypClass() {
		return University.class;
	}

   public UniversitySet()
   {
      // empty
   }

   public UniversitySet(University... objects)
   {
      for (University obj : objects)
      {
         this.add(obj);
      }
   }

   public UniversitySet(Collection<University> objects)
   {
      this.addAll(objects);
   }

   public static final UniversitySet EMPTY_SET = new UniversitySet().withFlag(UniversitySet.READONLY);


   public String getEntryType()
   {
      return "org.sdmlib.test.codegen.studyright.model.University";
   }


   @Override
   public UniversitySet getNewList(boolean keyValue)
   {
      return new UniversitySet();
   }


   @SuppressWarnings("unchecked")
   public UniversitySet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<University>)value);
      }
      else if (value != null)
      {
         this.add((University) value);
      }
      
      return this;
   }
   
   public UniversitySet without(University value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of University objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public ObjectSet getName()
   {
      ObjectSet result = new ObjectSet();
      
      for (University obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of University objects and collect those University objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of University objects that match the parameter
    */
   public UniversitySet createNameCondition(String value)
   {
      UniversitySet result = new UniversitySet();
      
      for (University obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of University objects and collect those University objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of University objects that match the parameter
    */
   public UniversitySet createNameCondition(String lower, String upper)
   {
      UniversitySet result = new UniversitySet();
      
      for (University obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of University objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of University objects now with new attribute values.
    */
   public UniversitySet withName(String value)
   {
      for (University obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of University objects and collect a set of the Student objects reached via students. 
    * 
    * @return Set of Student objects reachable via students
    */
   public StudentSet getStudents()
   {
      StudentSet result = new StudentSet();
      
      for (University obj : this)
      {
         result.with(obj.getStudents());
      }
      
      return result;
   }

   /**
    * Loop through the current set of University objects and collect all contained objects with reference students pointing to the object passed as parameter. 
    * 
    * @param value The object required as students neighbor of the collected results. 
    * 
    * @return Set of Student objects referring to value via students
    */
   public UniversitySet filterStudents(Object value)
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
      
      UniversitySet answer = new UniversitySet();
      
      for (University obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getStudents()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the University object passed as parameter to the Students attribute of each of it. 
    * 
    * @param value value    * @return The original set of ModelType objects now with the new neighbor attached to their Students attributes.
    */
   public UniversitySet withStudents(Student value)
   {
      for (University obj : this)
      {
         obj.withStudents(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the University object passed as parameter from the Students attribute of each of it. 
    * 
    * @param value value    * @return The original set of ModelType objects now without the old neighbor.
    */
   public UniversitySet withoutStudents(Student value)
   {
      for (University obj : this)
      {
         obj.withoutStudents(value);
      }
      
      return this;
   }

}
