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
import org.sdmlib.examples.helloworld.Person;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.helloworld.creators.GreetingSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;

public class PersonSet extends LinkedHashSet<Person> implements
      org.sdmlib.models.modelsets.ModelSet
{
   public StringList getName()
   {
      StringList result = new StringList();

      for (Person obj : this)
      {
         result.add(obj.getName());
      }

      return result;
   }

   public PersonSet withName(String value)
   {
      for (Person obj : this)
      {
         obj.withName(value);
      }

      return this;
   }

   public GreetingSet getGreeting()
   {
      GreetingSet result = new GreetingSet();

      for (Person obj : this)
      {
         result.add(obj.getGreeting());
      }

      return result;
   }

   public PersonSet withGreeting(Greeting value)
   {
      for (Person obj : this)
      {
         obj.withGreeting(value);
      }

      return this;
   }

   public String toString()
   {
      StringList stringList = new StringList();

      for (Person elem : this)
      {
         stringList.add(elem.toString());
      }

      return "(" + stringList.concat(", ") + ")";
   }

   public String getEntryType()
   {
      return "org.sdmlib.examples.helloworld.Person";
   }

   public PersonPO startModelPattern()
   {
      org.sdmlib.examples.helloworld.creators.ModelPattern pattern = new org.sdmlib.examples.helloworld.creators.ModelPattern();

      PersonPO patternObject = pattern.hasElementPersonPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }

   public PersonSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Person>) value);
      }
      else if (value != null)
      {
         this.add((Person) value);
      }

      return this;
   }

   public PersonSet without(Person value)
   {
      this.remove(value);
      return this;
   }

   public PersonPO hasPersonPO()
   {
      org.sdmlib.examples.helloworld.creators.ModelPattern pattern = new org.sdmlib.examples.helloworld.creators.ModelPattern();

      PersonPO patternObject = pattern.hasElementPersonPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }
}



