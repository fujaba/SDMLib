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

import org.sdmlib.test.examples.helloworld.Greeting;
import org.sdmlib.test.examples.helloworld.GreetingMessage;
import org.sdmlib.test.examples.helloworld.Person;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;
import org.sdmlib.test.examples.helloworld.util.PersonSet;
import org.sdmlib.test.examples.helloworld.util.GreetingMessageSet;

public class GreetingSet extends SimpleSet<Greeting>
{
   public GreetingPO hasGreetingPO()
   {
      return new GreetingPO(this.toArray(new Greeting[this.size()]));
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


   public static final GreetingSet EMPTY_SET = new GreetingSet().withFlag(GreetingSet.READONLY);
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

   public GreetingSet hasText(String lower, String upper)
   {
      GreetingSet result = new GreetingSet();
      
      for (Greeting obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public GreetingPO filterGreetingPO()
   {
      return new GreetingPO(this.toArray(new Greeting[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.helloworld.Greeting";
   }

   /**
    * Loop through the current set of Greeting objects and collect those Greeting objects where the text attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Greeting objects that match the parameter
    */
   public GreetingSet filterText(String value)
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


   /**
    * Loop through the current set of Greeting objects and collect those Greeting objects where the text attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Greeting objects that match the parameter
    */
   public GreetingSet filterText(String lower, String upper)
   {
      GreetingSet result = new GreetingSet();
      
      for (Greeting obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   /**
    * Loop through the current set of Greeting objects and collect a set of the Greeting objects reached via greeting. 
    * 
    * @return Set of Greeting objects reachable via greeting
    */
   public GreetingSet getGreeting()
   {
      GreetingSet result = new GreetingSet();
      
      for (Greeting obj : this)
      {
         result.with(obj.getGreeting());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Greeting objects and collect all contained objects with reference greeting pointing to the object passed as parameter. 
    * 
    * @param value The object required as greeting neighbor of the collected results. 
    * 
    * @return Set of Greeting objects referring to value via greeting
    */
   public GreetingSet filterGreeting(Object value)
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
         if (neighbors.contains(obj.getGreeting()) || (neighbors.isEmpty() && obj.getGreeting() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow greeting reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Greeting objects reachable via greeting transitively (including the start set)
    */
   public GreetingSet getGreetingTransitive()
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
            
            if ( ! result.contains(current.getGreeting()))
            {
               todo.with(current.getGreeting());
            }
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Greeting object passed as parameter to the Greeting attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Greeting attributes.
    */
   public GreetingSet withGreeting(Greeting value)
   {
      for (Greeting obj : this)
      {
         obj.withGreeting(value);
      }
      
      return this;
   }


   public GreetingSet()
   {
      // empty
   }

   public GreetingSet(Greeting... objects)
   {
      for (Greeting obj : objects)
      {
         this.add(obj);
      }
   }

   public GreetingSet(Collection<Greeting> objects)
   {
      this.addAll(objects);
   }


   public GreetingPO createGreetingPO()
   {
      return new GreetingPO(this.toArray(new Greeting[this.size()]));
   }


   @Override
   public GreetingSet getNewList(boolean keyValue)
   {
      return new GreetingSet();
   }

   /**
    * Loop through the current set of Greeting objects and collect those Greeting objects where the text attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Greeting objects that match the parameter
    */
   public GreetingSet createTextCondition(String value)
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


   /**
    * Loop through the current set of Greeting objects and collect those Greeting objects where the text attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Greeting objects that match the parameter
    */
   public GreetingSet createTextCondition(String lower, String upper)
   {
      GreetingSet result = new GreetingSet();
      
      for (Greeting obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}




























