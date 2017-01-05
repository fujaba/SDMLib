/*
   Copyright (c) 2016 Stefan
   
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
   
package org.sdmlib.simple.model.association_c.util;

import java.util.Collection;

import org.sdmlib.simple.model.association_c.Person;
import org.sdmlib.simple.model.association_c.Room;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class PersonSet extends SimpleSet<Person>
{
	protected Class<?> getTypClass() {
		return Person.class;
	}

   public PersonSet()
   {
      // empty
   }

   public PersonSet(Person... objects)
   {
      for (Person obj : objects)
      {
         this.add(obj);
      }
   }

   public PersonSet(Collection<Person> objects)
   {
      this.addAll(objects);
   }

   public static final PersonSet EMPTY_SET = new PersonSet().withFlag(PersonSet.READONLY);


   public PersonPO createPersonPO()
   {
      return new PersonPO(this.toArray(new Person[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.association_c.Person";
   }


   @SuppressWarnings("unchecked")
   public PersonSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Person>)value);
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

   /**
    * Loop through the current set of Person objects and collect a set of the Room objects reached via room. 
    * 
    * @return Set of Room objects reachable via room
    */
   public RoomSet getRoom()
   {
      RoomSet result = new RoomSet();
      
      for (Person obj : this)
      {
         result.with(obj.getRoom());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Person objects and collect all contained objects with reference room pointing to the object passed as parameter. 
    * 
    * @param value The object required as room neighbor of the collected results. 
    * 
    * @return Set of Room objects referring to value via room
    */
   public PersonSet filterRoom(Object value)
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
      
      PersonSet answer = new PersonSet();
      
      for (Person obj : this)
      {
         if (neighbors.contains(obj.getRoom()) || (neighbors.isEmpty() && obj.getRoom() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Person object passed as parameter to the Room attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Room attributes.
    */
   public PersonSet withRoom(Room value)
   {
      for (Person obj : this)
      {
         obj.withRoom(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Person objects and collect a set of the Person objects reached via prevPerson. 
    * 
    * @return Set of Person objects reachable via prevPerson
    */
   public PersonSet getPrevPerson()
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         result.with(obj.getPrevPerson());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Person objects and collect all contained objects with reference prevPerson pointing to the object passed as parameter. 
    * 
    * @param value The object required as prevPerson neighbor of the collected results. 
    * 
    * @return Set of Person objects referring to value via prevPerson
    */
   public PersonSet filterPrevPerson(Object value)
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
      
      PersonSet answer = new PersonSet();
      
      for (Person obj : this)
      {
         if (neighbors.contains(obj.getPrevPerson()) || (neighbors.isEmpty() && obj.getPrevPerson() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow prevPerson reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Person objects reachable via prevPerson transitively (including the start set)
    */
   public PersonSet getPrevPersonTransitive()
   {
      PersonSet todo = new PersonSet().with(this);
      
      PersonSet result = new PersonSet();
      
      while ( ! todo.isEmpty())
      {
         Person current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getPrevPerson()))
            {
               todo.with(current.getPrevPerson());
            }
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Person object passed as parameter to the PrevPerson attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their PrevPerson attributes.
    */
   public PersonSet withPrevPerson(Person value)
   {
      for (Person obj : this)
      {
         obj.withPrevPerson(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Person objects and collect a set of the Person objects reached via nextPerson. 
    * 
    * @return Set of Person objects reachable via nextPerson
    */
   public PersonSet getNextPerson()
   {
      PersonSet result = new PersonSet();
      
      for (Person obj : this)
      {
         result.with(obj.getNextPerson());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Person objects and collect all contained objects with reference nextPerson pointing to the object passed as parameter. 
    * 
    * @param value The object required as nextPerson neighbor of the collected results. 
    * 
    * @return Set of Person objects referring to value via nextPerson
    */
   public PersonSet filterNextPerson(Object value)
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
      
      PersonSet answer = new PersonSet();
      
      for (Person obj : this)
      {
         if (neighbors.contains(obj.getNextPerson()) || (neighbors.isEmpty() && obj.getNextPerson() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow nextPerson reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Person objects reachable via nextPerson transitively (including the start set)
    */
   public PersonSet getNextPersonTransitive()
   {
      PersonSet todo = new PersonSet().with(this);
      
      PersonSet result = new PersonSet();
      
      while ( ! todo.isEmpty())
      {
         Person current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getNextPerson()))
            {
               todo.with(current.getNextPerson());
            }
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Person object passed as parameter to the NextPerson attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their NextPerson attributes.
    */
   public PersonSet withNextPerson(Person value)
   {
      for (Person obj : this)
      {
         obj.withNextPerson(value);
      }
      
      return this;
   }

}
