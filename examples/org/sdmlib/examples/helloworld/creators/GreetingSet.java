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
import org.sdmlib.examples.helloworld.Person;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.helloworld.creators.GreetingMessageSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.examples.helloworld.creators.PersonSet;

public class GreetingSet extends LinkedHashSet<Greeting> implements
      org.sdmlib.models.modelsets.ModelSet
{
   public GreetingMessageSet getGreetingMessage()
   {
      GreetingMessageSet result = new GreetingMessageSet();

      for (Greeting obj : this)
      {
         result.add(obj.getGreetingMessage());
      }

      return result;
   }

   public GreetingSet withGreetingMessage(GreetingMessage value)
   {
      for (Greeting obj : this)
      {
         obj.withGreetingMessage(value);
      }

      return this;
   }

   public PersonSet getPerson()
   {
      PersonSet result = new PersonSet();

      for (Greeting obj : this)
      {
         result.add(obj.getPerson());
      }

      return result;
   }

   public GreetingSet withPerson(Person value)
   {
      for (Greeting obj : this)
      {
         obj.withPerson(value);
      }

      return this;
   }

   public StringList getText()
   {
      StringList result = new StringList();

      for (Greeting obj : this)
      {
         result.add(obj.getText());
      }

      return result;
   }

   public GreetingSet withText(String value)
   {
      for (Greeting obj : this)
      {
         obj.withText(value);
      }

      return this;
   }

   public String toString()
   {
      StringList stringList = new StringList();

      for (Greeting elem : this)
      {
         stringList.add(elem.toString());
      }

      return "(" + stringList.concat(", ") + ")";
   }

   public String getEntryType()
   {
      return "org.sdmlib.examples.helloworld.Greeting";
   }

   public GreetingPO startModelPattern()
   {
      org.sdmlib.examples.helloworld.creators.ModelPattern pattern = new org.sdmlib.examples.helloworld.creators.ModelPattern();

      GreetingPO patternObject = pattern.hasElementGreetingPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }

   public GreetingSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Greeting>) value);
      }
      else if (value != null)
      {
         this.add((Greeting) value);
      }

      return this;
   }

   public GreetingSet without(Greeting value)
   {
      this.remove(value);
      return this;
   }

   public GreetingPO hasGreetingPO()
   {
      org.sdmlib.examples.helloworld.creators.ModelPattern pattern = new org.sdmlib.examples.helloworld.creators.ModelPattern();

      GreetingPO patternObject = pattern.hasElementGreetingPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }
}



