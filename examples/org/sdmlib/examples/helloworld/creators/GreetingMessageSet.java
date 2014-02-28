/*
   Copyright (c) 2013 zuendorf 
   
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
   
package org.sdmlib.examples.helloworld.creators;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.examples.helloworld.GreetingMessage;
import org.sdmlib.models.modelsets.StringList;

public class GreetingMessageSet extends LinkedHashSet<GreetingMessage> implements org.sdmlib.models.modelsets.ModelSet
{
   public StringList getText()
   {
      StringList result = new StringList();
      
      for (GreetingMessage obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }

   public GreetingMessageSet withText(String value)
   {
      for (GreetingMessage obj : this)
      {
         obj.withText(value);
      }
      
      return this;
   }

   public GreetingSet getGreeting()
   {
      GreetingSet result = new GreetingSet();
      
      for (GreetingMessage obj : this)
      {
         result.add(obj.getGreeting());
      }
      
      return result;
   }
   public GreetingMessageSet withGreeting(Greeting value)
   {
      for (GreetingMessage obj : this)
      {
         obj.withGreeting(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (GreetingMessage elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.helloworld.GreetingMessage";
   }


   public GreetingMessagePO startModelPattern()
   {
      org.sdmlib.examples.helloworld.creators.ModelPattern pattern = new org.sdmlib.examples.helloworld.creators.ModelPattern();
      
      GreetingMessagePO patternObject = pattern.hasElementGreetingMessagePO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


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



   public GreetingMessagePO hasGreetingMessagePO()
   {
      org.sdmlib.examples.helloworld.creators.ModelPattern pattern = new org.sdmlib.examples.helloworld.creators.ModelPattern();
      
      GreetingMessagePO patternObject = pattern.hasElementGreetingMessagePO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}




