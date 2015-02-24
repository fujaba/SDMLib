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
   
package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.studyright.model.Professor;
import java.util.Collection;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.ObjectSet;
import java.util.Collections;
import org.sdmlib.examples.studyright.model.Lecture;
import org.sdmlib.examples.studyright.model.Topic;
import org.sdmlib.examples.studyright.model.util.LectureSet;
import org.sdmlib.examples.studyright.model.util.TopicSet;

public class ProfessorSet extends SDMSet<Professor>
{


   public ProfessorPO hasProfessorPO()
   {
      return new ProfessorPO(this.toArray(new Professor[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.studyright.model.Professor";
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


   public static final ProfessorSet EMPTY_SET = new ProfessorSet().withReadOnly(true);
}
