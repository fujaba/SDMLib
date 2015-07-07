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
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.test.examples.helloworld.Greeting;
import org.sdmlib.test.examples.helloworld.GreetingMessage;
import org.sdmlib.test.examples.helloworld.Person;
import org.sdmlib.test.examples.helloworld.util.GreetingMessageSet;
import org.sdmlib.test.examples.helloworld.util.GreetingSet;
import org.sdmlib.test.examples.helloworld.util.PersonSet;

public class GreetingSet extends SDMSet<Greeting>
{
   public GreetingPO hasGreetingPO()
   {
      return new GreetingPO(this.toArray(new Greeting[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.test.examples.helloworld.Greeting";
   }


   public GreetingSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         Collection<?> collection = (Collection<?>) value;
         for(Object item : collection){
             this.add((Greeting) item);
         }
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

   public StringList getText()
   {
      StringList result = new StringList();
      
      for (Greeting obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }

   public GreetingSet hasText(String value)
   {
      GreetingSet result = new GreetingSet();
      
      for (Greeting obj : this)
      {
         if (value.equals(obj.getText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GreetingSet withText(String value)
   {
      for (Greeting obj : this)
      {
         obj.setText(value);
      }
      
      return this;
   }

   public GreetingMessageSet getGreetingMessage()
   {
      GreetingMessageSet result = new GreetingMessageSet();
      
      for (Greeting obj : this)
      {
         result.with(obj.getGreetingMessage());
      }
      
      return result;
   }

   public GreetingSet hasGreetingMessage(Object value)
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
      
      GreetingSet answer = new GreetingSet();
      
      for (Greeting obj : this)
      {
         if (neighbors.contains(obj.getGreetingMessage()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
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
         result.with(obj.getPerson());
      }
      
      return result;
   }

   public GreetingSet hasPerson(Object value)
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
      
      GreetingSet answer = new GreetingSet();
      
      for (Greeting obj : this)
      {
         if (neighbors.contains(obj.getPerson()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public GreetingSet withPerson(Person value)
   {
      for (Greeting obj : this)
      {
         obj.withPerson(value);
      }
      
      return this;
   }


   public static final GreetingSet EMPTY_SET = new GreetingSet().withReadOnly(true);
   public GreetingSet getTgt()
   {
      GreetingSet result = new GreetingSet();
      
      for (Greeting obj : this)
      {
         result.add(obj.getTgt());
      }
      
      return result;
   }

   public GreetingSet hasTgt(Object value)
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
      
      GreetingSet answer = new GreetingSet();
      
      for (Greeting obj : this)
      {
         if (neighbors.contains(obj.getTgt()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public GreetingSet getTgtTransitive()
   {
      GreetingSet todo = new GreetingSet().with(this);
      
      GreetingSet result = new GreetingSet();
      
      while ( ! todo.isEmpty())
      {
         Greeting current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getTgt()))
            {
               todo.with(current.getTgt());
            }
         }
      }
      
      return result;
   }

   public GreetingSet withTgt(Greeting value)
   {
      for (Greeting obj : this)
      {
         obj.withTgt(value);
      }
      
      return this;
   }

}




























