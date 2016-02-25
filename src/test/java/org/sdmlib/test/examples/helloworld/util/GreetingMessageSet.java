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
   
package org.sdmlib.test.examples.helloworld.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.test.examples.helloworld.Greeting;
import org.sdmlib.test.examples.helloworld.GreetingMessage;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.helloworld.util.GreetingSet;

public class GreetingMessageSet extends SimpleSet<GreetingMessage>
{


   public GreetingMessagePO hasGreetingMessagePO()
   {
      return new GreetingMessagePO(this.toArray(new GreetingMessage[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public GreetingMessageSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<GreetingMessage>)value);
      }
      else if (value != null)
      {
         this.add((GreetingMessage) value);
      }
      
      return this;
   }
   
   public GreetingMessageSet without(GreetingMessage value)
   {
      this.remove(value);
      return this;
   }

   public StringList getText()
   {
      StringList result = new StringList();
      
      for (GreetingMessage obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }

   public GreetingMessageSet hasText(String value)
   {
      GreetingMessageSet result = new GreetingMessageSet();
      
      for (GreetingMessage obj : this)
      {
         if (value.equals(obj.getText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GreetingMessageSet withText(String value)
   {
      for (GreetingMessage obj : this)
      {
         obj.setText(value);
      }
      
      return this;
   }

   public GreetingSet getGreeting()
   {
      GreetingSet result = new GreetingSet();
      
      for (GreetingMessage obj : this)
      {
         result.with(obj.getGreeting());
      }
      
      return result;
   }

   public GreetingMessageSet hasGreeting(Object value)
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
      
      GreetingMessageSet answer = new GreetingMessageSet();
      
      for (GreetingMessage obj : this)
      {
         if (neighbors.contains(obj.getGreeting()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public GreetingMessageSet withGreeting(Greeting value)
   {
      for (GreetingMessage obj : this)
      {
         obj.withGreeting(value);
      }
      
      return this;
   }


   public static final GreetingMessageSet EMPTY_SET = new GreetingMessageSet().withFlag(GreetingMessageSet.READONLY);
   public GreetingMessageSet hasText(String lower, String upper)
   {
      GreetingMessageSet result = new GreetingMessageSet();
      
      for (GreetingMessage obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public GreetingMessagePO filterGreetingMessagePO()
   {
      return new GreetingMessagePO(this.toArray(new GreetingMessage[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.helloworld.GreetingMessage";
   }

   /**
    * Loop through the current set of GreetingMessage objects and collect those GreetingMessage objects where the text attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of GreetingMessage objects that match the parameter
    */
   public GreetingMessageSet filterText(String value)
   {
      GreetingMessageSet result = new GreetingMessageSet();
      
      for (GreetingMessage obj : this)
      {
         if (value.equals(obj.getText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GreetingMessage objects and collect those GreetingMessage objects where the text attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of GreetingMessage objects that match the parameter
    */
   public GreetingMessageSet filterText(String lower, String upper)
   {
      GreetingMessageSet result = new GreetingMessageSet();
      
      for (GreetingMessage obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}




























