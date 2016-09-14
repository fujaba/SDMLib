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

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.StringList;
import org.sdmlib.test.examples.studyright.model.Professor;
import org.sdmlib.test.examples.studyright.model.Topic;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.studyright.model.util.ProfessorSet;

public class TopicSet extends SimpleSet<Topic>
{


   public TopicPO hasTopicPO()
   {
      return new TopicPO(this.toArray(new Topic[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public TopicSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Topic>)value);
      }
      else if (value != null)
      {
         this.add((Topic) value);
      }
      
      return this;
   }
   
   public TopicSet without(Topic value)
   {
      this.remove(value);
      return this;
   }

   public StringList getTitle()
   {
      StringList result = new StringList();
      
      for (Topic obj : this)
      {
         result.add(obj.getTitle());
      }
      
      return result;
   }

   public TopicSet hasTitle(String value)
   {
      TopicSet result = new TopicSet();
      
      for (Topic obj : this)
      {
         if (value.equals(obj.getTitle()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TopicSet withTitle(String value)
   {
      for (Topic obj : this)
      {
         obj.setTitle(value);
      }
      
      return this;
   }

   public ProfessorSet getProf()
   {
      ProfessorSet result = new ProfessorSet();
      
      for (Topic obj : this)
      {
         result.add(obj.getProf());
      }
      
      return result;
   }

   public TopicSet hasProf(Object value)
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
      
      TopicSet answer = new TopicSet();
      
      for (Topic obj : this)
      {
         if (neighbors.contains(obj.getProf()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public TopicSet withProf(Professor value)
   {
      for (Topic obj : this)
      {
         obj.withProf(value);
      }
      
      return this;
   }


   public static final TopicSet EMPTY_SET = new TopicSet().withFlag(TopicSet.READONLY);
   public TopicSet hasTitle(String lower, String upper)
   {
      TopicSet result = new TopicSet();
      
      for (Topic obj : this)
      {
         if (lower.compareTo(obj.getTitle()) <= 0 && obj.getTitle().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public TopicPO filterTopicPO()
   {
      return new TopicPO(this.toArray(new Topic[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.studyright.model.Topic";
   }

   /**
    * Loop through the current set of Topic objects and collect those Topic objects where the title attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Topic objects that match the parameter
    */
   public TopicSet filterTitle(String value)
   {
      TopicSet result = new TopicSet();
      
      for (Topic obj : this)
      {
         if (value.equals(obj.getTitle()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Topic objects and collect those Topic objects where the title attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Topic objects that match the parameter
    */
   public TopicSet filterTitle(String lower, String upper)
   {
      TopicSet result = new TopicSet();
      
      for (Topic obj : this)
      {
         if (lower.compareTo(obj.getTitle()) <= 0 && obj.getTitle().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public TopicSet()
   {
      // empty
   }

   public TopicSet(Topic... objects)
   {
      for (Topic obj : objects)
      {
         this.add(obj);
      }
   }

   public TopicSet(Collection<Topic> objects)
   {
      this.addAll(objects);
   }
}
