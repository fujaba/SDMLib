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
import org.sdmlib.test.examples.studyright.model.Lecture;
import org.sdmlib.test.examples.studyright.model.Professor;
import org.sdmlib.test.examples.studyright.model.Topic;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;

public class ProfessorSet extends SimpleSet<Professor>
{


   public ProfessorPO hasProfessorPO()
   {
      return new ProfessorPO(this.toArray(new Professor[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public ProfessorSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Professor>)value);
      }
      else if (value != null)
      {
         this.add((Professor) value);
      }
      
      return this;
   }
   
   public ProfessorSet without(Professor value)
   {
      this.remove(value);
      return this;
   }

   public intList getPersNr()
   {
      intList result = new intList();
      
      for (Professor obj : this)
      {
         result.add(obj.getPersNr());
      }
      
      return result;
   }

   public ProfessorSet hasPersNr(int value)
   {
      ProfessorSet result = new ProfessorSet();
      
      for (Professor obj : this)
      {
         if (value == obj.getPersNr())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ProfessorSet withPersNr(int value)
   {
      for (Professor obj : this)
      {
         obj.setPersNr(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Professor obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public ProfessorSet hasName(String value)
   {
      ProfessorSet result = new ProfessorSet();
      
      for (Professor obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ProfessorSet withName(String value)
   {
      for (Professor obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public LectureSet getLecture()
   {
      LectureSet result = new LectureSet();
      
      for (Professor obj : this)
      {
         result.addAll(obj.getLecture());
      }
      
      return result;
   }

   public ProfessorSet hasLecture(Object value)
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
      
      ProfessorSet answer = new ProfessorSet();
      
      for (Professor obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getLecture()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ProfessorSet withLecture(Lecture value)
   {
      for (Professor obj : this)
      {
         obj.withLecture(value);
      }
      
      return this;
   }

   public ProfessorSet withoutLecture(Lecture value)
   {
      for (Professor obj : this)
      {
         obj.withoutLecture(value);
      }
      
      return this;
   }

   public TopicSet getTopic()
   {
      TopicSet result = new TopicSet();
      
      for (Professor obj : this)
      {
         result.add(obj.getTopic());
      }
      
      return result;
   }

   public ProfessorSet hasTopic(Object value)
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
      
      ProfessorSet answer = new ProfessorSet();
      
      for (Professor obj : this)
      {
         if (neighbors.contains(obj.getTopic()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ProfessorSet withTopic(Topic value)
   {
      for (Professor obj : this)
      {
         obj.withTopic(value);
      }
      
      return this;
   }


   public static final ProfessorSet EMPTY_SET = new ProfessorSet().withFlag(ProfessorSet.READONLY);
   public ProfessorSet hasPersNr(int lower, int upper)
   {
      ProfessorSet result = new ProfessorSet();
      
      for (Professor obj : this)
      {
         if (lower <= obj.getPersNr() && obj.getPersNr() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ProfessorSet hasName(String lower, String upper)
   {
      ProfessorSet result = new ProfessorSet();
      
      for (Professor obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public ProfessorPO filterProfessorPO()
   {
      return new ProfessorPO(this.toArray(new Professor[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.studyright.model.Professor";
   }

   /**
    * Loop through the current set of Professor objects and collect those Professor objects where the PersNr attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Professor objects that match the parameter
    */
   public ProfessorSet filterPersNr(int value)
   {
      ProfessorSet result = new ProfessorSet();
      
      for (Professor obj : this)
      {
         if (value == obj.getPersNr())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Professor objects and collect those Professor objects where the PersNr attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Professor objects that match the parameter
    */
   public ProfessorSet filterPersNr(int lower, int upper)
   {
      ProfessorSet result = new ProfessorSet();
      
      for (Professor obj : this)
      {
         if (lower <= obj.getPersNr() && obj.getPersNr() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Professor objects and collect those Professor objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Professor objects that match the parameter
    */
   public ProfessorSet filterName(String value)
   {
      ProfessorSet result = new ProfessorSet();
      
      for (Professor obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Professor objects and collect those Professor objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Professor objects that match the parameter
    */
   public ProfessorSet filterName(String lower, String upper)
   {
      ProfessorSet result = new ProfessorSet();
      
      for (Professor obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public ProfessorSet()
   {
      // empty
   }

   public ProfessorSet(Professor... objects)
   {
      for (Professor obj : objects)
      {
         this.add(obj);
      }
   }

   public ProfessorSet(Collection<Professor> objects)
   {
      this.addAll(objects);
   }
}
