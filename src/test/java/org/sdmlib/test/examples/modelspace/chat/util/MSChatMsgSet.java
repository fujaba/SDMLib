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

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.longList;
import org.sdmlib.test.examples.modelspace.chat.MSChatChannel;
import org.sdmlib.test.examples.modelspace.chat.MSChatMsg;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatChannelSet;

public class MSChatMsgSet extends SimpleSet<MSChatMsg>
{

   public static final MSChatMsgSet EMPTY_SET = new MSChatMsgSet().withFlag(MSChatMsgSet.READONLY);


   public MSChatMsgPO hasMSChatMsgPO()
   {
      return new MSChatMsgPO(this.toArray(new MSChatMsg[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.modelspace.chat.MSChatMsg";
   }


   @SuppressWarnings("unchecked")
   public MSChatMsgSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<MSChatMsg>)value);
      }
      else if (value != null)
      {
         this.add((MSChatMsg) value);
      }
      
      return this;
   }
   
   public MSChatMsgSet without(MSChatMsg value)
   {
      this.remove(value);
      return this;
   }

   public StringList getText()
   {
      StringList result = new StringList();
      
      for (MSChatMsg obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }

   public MSChatMsgSet hasText(String value)
   {
      MSChatMsgSet result = new MSChatMsgSet();
      
      for (MSChatMsg obj : this)
      {
         if (value.equals(obj.getText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MSChatMsgSet hasText(String lower, String upper)
   {
      MSChatMsgSet result = new MSChatMsgSet();
      
      for (MSChatMsg obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MSChatMsgSet withText(String value)
   {
      for (MSChatMsg obj : this)
      {
         obj.setText(value);
      }
      
      return this;
   }

   public longList getTime()
   {
      longList result = new longList();
      
      for (MSChatMsg obj : this)
      {
         result.add(obj.getTime());
      }
      
      return result;
   }

   public MSChatMsgSet hasTime(long value)
   {
      MSChatMsgSet result = new MSChatMsgSet();
      
      for (MSChatMsg obj : this)
      {
         if (value == obj.getTime())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MSChatMsgSet hasTime(long lower, long upper)
   {
      MSChatMsgSet result = new MSChatMsgSet();
      
      for (MSChatMsg obj : this)
      {
         if (lower <= obj.getTime() && obj.getTime() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MSChatMsgSet withTime(long value)
   {
      for (MSChatMsg obj : this)
      {
         obj.setTime(value);
      }
      
      return this;
   }

   public StringList getSender()
   {
      StringList result = new StringList();
      
      for (MSChatMsg obj : this)
      {
         result.add(obj.getSender());
      }
      
      return result;
   }

   public MSChatMsgSet hasSender(String value)
   {
      MSChatMsgSet result = new MSChatMsgSet();
      
      for (MSChatMsg obj : this)
      {
         if (value.equals(obj.getSender()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MSChatMsgSet hasSender(String lower, String upper)
   {
      MSChatMsgSet result = new MSChatMsgSet();
      
      for (MSChatMsg obj : this)
      {
         if (lower.compareTo(obj.getSender()) <= 0 && obj.getSender().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MSChatMsgSet withSender(String value)
   {
      for (MSChatMsg obj : this)
      {
         obj.setSender(value);
      }
      
      return this;
   }

   public MSChatChannelSet getChannel()
   {
      MSChatChannelSet result = new MSChatChannelSet();
      
      for (MSChatMsg obj : this)
      {
         result.add(obj.getChannel());
      }
      
      return result;
   }

   public MSChatMsgSet hasChannel(Object value)
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
      
      MSChatMsgSet answer = new MSChatMsgSet();
      
      for (MSChatMsg obj : this)
      {
         if (neighbors.contains(obj.getChannel()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MSChatMsgSet withChannel(MSChatChannel value)
   {
      for (MSChatMsg obj : this)
      {
         obj.withChannel(value);
      }
      
      return this;
   }



   public MSChatMsgPO filterMSChatMsgPO()
   {
      return new MSChatMsgPO(this.toArray(new MSChatMsg[this.size()]));
   }

   /**
    * Loop through the current set of MSChatMsg objects and collect those MSChatMsg objects where the text attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MSChatMsg objects that match the parameter
    */
   public MSChatMsgSet filterText(String value)
   {
      MSChatMsgSet result = new MSChatMsgSet();
      
      for (MSChatMsg obj : this)
      {
         if (value.equals(obj.getText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MSChatMsg objects and collect those MSChatMsg objects where the text attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of MSChatMsg objects that match the parameter
    */
   public MSChatMsgSet filterText(String lower, String upper)
   {
      MSChatMsgSet result = new MSChatMsgSet();
      
      for (MSChatMsg obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MSChatMsg objects and collect those MSChatMsg objects where the time attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MSChatMsg objects that match the parameter
    */
   public MSChatMsgSet filterTime(long value)
   {
      MSChatMsgSet result = new MSChatMsgSet();
      
      for (MSChatMsg obj : this)
      {
         if (value == obj.getTime())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MSChatMsg objects and collect those MSChatMsg objects where the time attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of MSChatMsg objects that match the parameter
    */
   public MSChatMsgSet filterTime(long lower, long upper)
   {
      MSChatMsgSet result = new MSChatMsgSet();
      
      for (MSChatMsg obj : this)
      {
         if (lower <= obj.getTime() && obj.getTime() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MSChatMsg objects and collect those MSChatMsg objects where the sender attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MSChatMsg objects that match the parameter
    */
   public MSChatMsgSet filterSender(String value)
   {
      MSChatMsgSet result = new MSChatMsgSet();
      
      for (MSChatMsg obj : this)
      {
         if (value.equals(obj.getSender()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MSChatMsg objects and collect those MSChatMsg objects where the sender attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of MSChatMsg objects that match the parameter
    */
   public MSChatMsgSet filterSender(String lower, String upper)
   {
      MSChatMsgSet result = new MSChatMsgSet();
      
      for (MSChatMsg obj : this)
      {
         if (lower.compareTo(obj.getSender()) <= 0 && obj.getSender().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
