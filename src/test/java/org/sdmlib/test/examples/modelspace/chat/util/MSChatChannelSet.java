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

import org.sdmlib.test.examples.modelspace.chat.MSChatChannel;
import org.sdmlib.test.examples.modelspace.chat.MSChatMsg;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;

public class MSChatChannelSet extends SimpleSet<MSChatChannel>
{

   public static final MSChatChannelSet EMPTY_SET = new MSChatChannelSet().withFlag(MSChatChannelSet.READONLY);


   public MSChatChannelPO hasMSChatChannelPO()
   {
      return new MSChatChannelPO(this.toArray(new MSChatChannel[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.modelspace.chat.MSChatChannel";
   }


   @SuppressWarnings("unchecked")
   public MSChatChannelSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<MSChatChannel>)value);
      }
      else if (value != null)
      {
         this.add((MSChatChannel) value);
      }
      
      return this;
   }
   
   public MSChatChannelSet without(MSChatChannel value)
   {
      this.remove(value);
      return this;
   }

   public MSChatMsgSet getMsgs()
   {
      MSChatMsgSet result = new MSChatMsgSet();
      
      for (MSChatChannel obj : this)
      {
         result.addAll(obj.getMsgs());
      }
      
      return result;
   }

   public MSChatChannelSet hasMsgs(Object value)
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
      
      MSChatChannelSet answer = new MSChatChannelSet();
      
      for (MSChatChannel obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getMsgs()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MSChatChannelSet withMsgs(MSChatMsg value)
   {
      for (MSChatChannel obj : this)
      {
         obj.withMsgs(value);
      }
      
      return this;
   }

   public MSChatChannelSet withoutMsgs(MSChatMsg value)
   {
      for (MSChatChannel obj : this)
      {
         obj.withoutMsgs(value);
      }
      
      return this;
   }

   public StringList getTask()
   {
      StringList result = new StringList();
      
      for (MSChatChannel obj : this)
      {
         result.add(obj.getTask());
      }
      
      return result;
   }

   public MSChatChannelSet hasTask(String value)
   {
      MSChatChannelSet result = new MSChatChannelSet();
      
      for (MSChatChannel obj : this)
      {
         if (value.equals(obj.getTask()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MSChatChannelSet hasTask(String lower, String upper)
   {
      MSChatChannelSet result = new MSChatChannelSet();
      
      for (MSChatChannel obj : this)
      {
         if (lower.compareTo(obj.getTask()) <= 0 && obj.getTask().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MSChatChannelSet withTask(String value)
   {
      for (MSChatChannel obj : this)
      {
         obj.setTask(value);
      }
      
      return this;
   }



   public MSChatChannelPO filterMSChatChannelPO()
   {
      return new MSChatChannelPO(this.toArray(new MSChatChannel[this.size()]));
   }

   /**
    * Loop through the current set of MSChatChannel objects and collect those MSChatChannel objects where the task attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MSChatChannel objects that match the parameter
    */
   public MSChatChannelSet filterTask(String value)
   {
      MSChatChannelSet result = new MSChatChannelSet();
      
      for (MSChatChannel obj : this)
      {
         if (value.equals(obj.getTask()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MSChatChannel objects and collect those MSChatChannel objects where the task attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of MSChatChannel objects that match the parameter
    */
   public MSChatChannelSet filterTask(String lower, String upper)
   {
      MSChatChannelSet result = new MSChatChannelSet();
      
      for (MSChatChannel obj : this)
      {
         if (lower.compareTo(obj.getTask()) <= 0 && obj.getTask().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public MSChatChannelSet()
   {
      // empty
   }

   public MSChatChannelSet(MSChatChannel... objects)
   {
      for (MSChatChannel obj : objects)
      {
         this.add(obj);
      }
   }

   public MSChatChannelSet(Collection<MSChatChannel> objects)
   {
      this.addAll(objects);
   }


   public MSChatChannelPO createMSChatChannelPO()
   {
      return new MSChatChannelPO(this.toArray(new MSChatChannel[this.size()]));
   }


   @Override
   public MSChatChannelSet getNewList(boolean keyValue)
   {
      return new MSChatChannelSet();
   }

   /**
    * Loop through the current set of MSChatChannel objects and collect those MSChatChannel objects where the task attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MSChatChannel objects that match the parameter
    */
   public MSChatChannelSet createTaskCondition(String value)
   {
      MSChatChannelSet result = new MSChatChannelSet();
      
      for (MSChatChannel obj : this)
      {
         if (value.equals(obj.getTask()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MSChatChannel objects and collect those MSChatChannel objects where the task attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of MSChatChannel objects that match the parameter
    */
   public MSChatChannelSet createTaskCondition(String lower, String upper)
   {
      MSChatChannelSet result = new MSChatChannelSet();
      
      for (MSChatChannel obj : this)
      {
         if (lower.compareTo(obj.getTask()) <= 0 && obj.getTask().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
