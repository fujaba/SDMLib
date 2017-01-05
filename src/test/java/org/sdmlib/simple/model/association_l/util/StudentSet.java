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
   
package org.sdmlib.simple.model.association_l.util;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.simple.model.association_l.Student;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.sdmlib.simple.model.association_l.util.LectureSet;
import org.sdmlib.simple.model.association_l.Lecture;
import org.sdmlib.simple.model.association_l.util.UniversitySet;
import org.sdmlib.simple.model.association_l.University;

public class StudentSet extends SimpleSet<Student>
{
	protected Class<?> getTypClass() {
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


   public StudentPO createStudentPO()
   {
      return new StudentPO(this.toArray(new Student[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.association_l.Student";
   }


   @Override
   public StudentSet getNewList(boolean keyValue)
   {
      return new StudentSet();
   }


   public StudentSet filter(Condition<Student> condition) {
      StudentSet filterList = new StudentSet();
      filterItems(filterList, condition);
      return filterList;
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
    * Loop through the current set of Student objects and collect a set of the Lecture objects reached via attended. 
    * 
    * @return Set of Lecture objects reachable via attended
    */
   public LectureSet getAttended()
   {
      LectureSet result = new LectureSet();
      
      for (Student obj : this)
      {
         result.with(obj.getAttended());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Student objects and collect all contained objects with reference attended pointing to the object passed as parameter. 
    * 
    * @param value The object required as attended neighbor of the collected results. 
    * 
    * @return Set of Lecture objects referring to value via attended
    */
   public StudentSet filterAttended(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getAttended()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Student object passed as parameter to the Attended attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Attended attributes.
    */
   public StudentSet withAttended(Lecture value)
   {
      for (Student obj : this)
      {
         obj.withAttended(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Student object passed as parameter from the Attended attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public StudentSet withoutAttended(Lecture value)
   {
      for (Student obj : this)
      {
         obj.withoutAttended(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Student objects and collect a set of the University objects reached via studs. 
    * 
    * @return Set of University objects reachable via studs
    */
   public UniversitySet getStuds()
   {
      UniversitySet result = new UniversitySet();
      
      for (Student obj : this)
      {
         result.with(obj.getStuds());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Student objects and collect all contained objects with reference studs pointing to the object passed as parameter. 
    * 
    * @param value The object required as studs neighbor of the collected results. 
    * 
    * @return Set of University objects referring to value via studs
    */
   public StudentSet filterStuds(Object value)
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
         if (neighbors.contains(obj.getStuds()) || (neighbors.isEmpty() && obj.getStuds() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Student object passed as parameter to the Studs attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Studs attributes.
    */
   public StudentSet withStuds(University value)
   {
      for (Student obj : this)
      {
         obj.withStuds(value);
      }
      
      return this;
   }

}
