/*
   Copyright (c) 2015 zuendorf
   
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
   
package org.sdmlib.test.examples.modelspace.chat.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.test.examples.modelspace.chat.MSChatChannelDescription;
import org.sdmlib.test.examples.modelspace.chat.MSChatGroup;
import org.sdmlib.test.examples.modelspace.chat.MSChatMember;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatChannelDescriptionSet;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatMemberSet;

public class MSChatGroupSet extends SimpleSet<MSChatGroup>
{

   public static final MSChatGroupSet EMPTY_SET = new MSChatGroupSet().withFlag(MSChatGroupSet.READONLY);


   public MSChatGroupPO hasMSChatGroupPO()
   {
      return new MSChatGroupPO(this.toArray(new MSChatGroup[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.modelspace.chat.MSChatGroup";
   }


   @SuppressWarnings("unchecked")
   public MSChatGroupSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<MSChatGroup>)value);
      }
      else if (value != null)
      {
         this.add((MSChatGroup) value);
      }
      
      return this;
   }
   
   public MSChatGroupSet without(MSChatGroup value)
   {
      this.remove(value);
      return this;
   }

   public MSChatMemberSet getMembers()
   {
      MSChatMemberSet result = new MSChatMemberSet();
      
      for (MSChatGroup obj : this)
      {
         result.addAll(obj.getMembers());
      }
      
      return result;
   }

   public MSChatGroupSet hasMembers(Object value)
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
      
      MSChatGroupSet answer = new MSChatGroupSet();
      
      for (MSChatGroup obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getMembers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MSChatGroupSet withMembers(MSChatMember value)
   {
      for (MSChatGroup obj : this)
      {
         obj.withMembers(value);
      }
      
      return this;
   }

   public MSChatGroupSet withoutMembers(MSChatMember value)
   {
      for (MSChatGroup obj : this)
      {
         obj.withoutMembers(value);
      }
      
      return this;
   }

   public MSChatChannelDescriptionSet getChannels()
   {
      MSChatChannelDescriptionSet result = new MSChatChannelDescriptionSet();
      
      for (MSChatGroup obj : this)
      {
         result.addAll(obj.getChannels());
      }
      
      return result;
   }

   public MSChatGroupSet hasChannels(Object value)
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
      
      MSChatGroupSet answer = new MSChatGroupSet();
      
      for (MSChatGroup obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getChannels()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MSChatGroupSet withChannels(MSChatChannelDescription value)
   {
      for (MSChatGroup obj : this)
      {
         obj.withChannels(value);
      }
      
      return this;
   }

   public MSChatGroupSet withoutChannels(MSChatChannelDescription value)
   {
      for (MSChatGroup obj : this)
      {
         obj.withoutChannels(value);
      }
      
      return this;
   }

   public StringList getTask()
   {
      StringList result = new StringList();
      
      for (MSChatGroup obj : this)
      {
         result.add(obj.getTask());
      }
      
      return result;
   }

   public MSChatGroupSet hasTask(String value)
   {
      MSChatGroupSet result = new MSChatGroupSet();
      
      for (MSChatGroup obj : this)
      {
         if (value.equals(obj.getTask()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MSChatGroupSet hasTask(String lower, String upper)
   {
      MSChatGroupSet result = new MSChatGroupSet();
      
      for (MSChatGroup obj : this)
      {
         if (lower.compareTo(obj.getTask()) <= 0 && obj.getTask().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MSChatGroupSet withTask(String value)
   {
      for (MSChatGroup obj : this)
      {
         obj.setTask(value);
      }
      
      return this;
   }



   public MSChatGroupPO filterMSChatGroupPO()
   {
      return new MSChatGroupPO(this.toArray(new MSChatGroup[this.size()]));
   }

   /**
    * Loop through the current set of MSChatGroup objects and collect those MSChatGroup objects where the task attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MSChatGroup objects that match the parameter
    */
   public MSChatGroupSet filterTask(String value)
   {
      MSChatGroupSet result = new MSChatGroupSet();
      
      for (MSChatGroup obj : this)
      {
         if (value.equals(obj.getTask()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MSChatGroup objects and collect those MSChatGroup objects where the task attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of MSChatGroup objects that match the parameter
    */
   public MSChatGroupSet filterTask(String lower, String upper)
   {
      MSChatGroupSet result = new MSChatGroupSet();
      
      for (MSChatGroup obj : this)
      {
         if (lower.compareTo(obj.getTask()) <= 0 && obj.getTask().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public MSChatGroupSet()
   {
      // empty
   }

   public MSChatGroupSet(MSChatGroup... objects)
   {
      for (MSChatGroup obj : objects)
      {
         this.add(obj);
      }
   }

   public MSChatGroupSet(Collection<MSChatGroup> objects)
   {
      this.addAll(objects);
   }


   public MSChatGroupPO createMSChatGroupPO()
   {
      return new MSChatGroupPO(this.toArray(new MSChatGroup[this.size()]));
   }


   @Override
   public MSChatGroupSet getNewList(boolean keyValue)
   {
      return new MSChatGroupSet();
   }

   /**
    * Loop through the current set of MSChatGroup objects and collect those MSChatGroup objects where the task attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MSChatGroup objects that match the parameter
    */
   public MSChatGroupSet createTaskCondition(String value)
   {
      MSChatGroupSet result = new MSChatGroupSet();
      
      for (MSChatGroup obj : this)
      {
         if (value.equals(obj.getTask()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MSChatGroup objects and collect those MSChatGroup objects where the task attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of MSChatGroup objects that match the parameter
    */
   public MSChatGroupSet createTaskCondition(String lower, String upper)
   {
      MSChatGroupSet result = new MSChatGroupSet();
      
      for (MSChatGroup obj : this)
      {
         if (lower.compareTo(obj.getTask()) <= 0 && obj.getTask().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
