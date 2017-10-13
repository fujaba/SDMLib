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
   
package org.sdmlib.simple.model.association_l.util;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.simple.model.association_l.Lecture;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.sdmlib.simple.model.association_l.util.StudentSet;
import org.sdmlib.simple.model.association_l.Student;

public class LectureSet extends SimpleSet<Lecture>
{
	public Class<?> getTypClass() {
		return Lecture.class;
	}

   public LectureSet()
   {
      // empty
   }

   public LectureSet(Lecture... objects)
   {
      for (Lecture obj : objects)
      {
         this.add(obj);
      }
   }

   public LectureSet(Collection<Lecture> objects)
   {
      this.addAll(objects);
   }

   public static final LectureSet EMPTY_SET = new LectureSet().withFlag(LectureSet.READONLY);


   public LecturePO createLecturePO()
   {
      return new LecturePO(this.toArray(new Lecture[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.association_l.Lecture";
   }


   @Override
   public LectureSet getNewList(boolean keyValue)
   {
      return new LectureSet();
   }


   public LectureSet filter(Condition<Lecture> condition) {
      LectureSet filterList = new LectureSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public LectureSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Lecture>)value);
      }
      else if (value != null)
      {
         this.add((Lecture) value);
      }
      
      return this;
   }
   
   public LectureSet without(Lecture value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of Lecture objects and collect a set of the Student objects reached via has. 
    * 
    * @return Set of Student objects reachable via has
    */
   public StudentSet getHas()
   {
      StudentSet result = new StudentSet();
      
      for (Lecture obj : this)
      {
         result.with(obj.getHas());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Lecture objects and collect all contained objects with reference has pointing to the object passed as parameter. 
    * 
    * @param value The object required as has neighbor of the collected results. 
    * 
    * @return Set of Student objects referring to value via has
    */
   public LectureSet filterHas(Object value)
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
      
      LectureSet answer = new LectureSet();
      
      for (Lecture obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getHas()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Lecture object passed as parameter to the Has attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Has attributes.
    */
   public LectureSet withHas(Student value)
   {
      for (Lecture obj : this)
      {
         obj.withHas(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Lecture object passed as parameter from the Has attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public LectureSet withoutHas(Student value)
   {
      for (Lecture obj : this)
      {
         obj.withoutHas(value);
      }
      
      return this;
   }

}
