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
   
package org.sdmlib.examples.helloworld.util;

import java.util.Collection;

import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.examples.helloworld.Person;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.helloworld.util.GreetingSet;

public class PersonSet extends SDMSet<Person>
{
   public PersonPO hasPersonPO()
   {
      return new PersonPO(this.toArray(new Person[this.size()]));
   }

   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.helloworld.Person";
   }


   public PersonSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         Collection<?> collection = (Collection<?>) value;
         for(Object item : collection){
             this.add((Person) item);
         }
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

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Person obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public PersonSet hasName(String value)
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PersonSet withName(String value)
   {
      for (Person obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public GreetingSet getGreeting()
   {
      GreetingSet result = new GreetingSet();
      
      for (Person obj : this)
      {
         result.with(obj.getGreeting());
      }
      
      return result;
   }

   public PersonSet hasGreeting(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         Collection<?> collection = (Collection<?>) value;
         for(Object item : collection){
            neighbors.add(item);
         }
      }
      else
      {
         neighbors.add(value);
      }
      
      PersonSet answer = new PersonSet();
      
      for (Person obj : this)
      {
         if (neighbors.contains(obj.getGreeting()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PersonSet withGreeting(Greeting value)
   {
      for (Person obj : this)
      {
         obj.withGreeting(value);
      }
      
      return this;
   }


   public static final PersonSet EMPTY_SET = new PersonSet().withReadOnly(true);
}




























