/*
   Copyright (c) 2017 Stefan
   
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
   
package org.sdmlib.test.examples.patternrewriteops.model.util;

import java.util.Collection;

import org.sdmlib.test.examples.patternrewriteops.model.Person;
import org.sdmlib.test.examples.patternrewriteops.model.Station;
import org.sdmlib.test.examples.patternrewriteops.model.Train;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.patternrewriteops.model.util.TrainSet;
import org.sdmlib.test.examples.patternrewriteops.model.util.StationSet;

public class PersonSet extends SimpleSet<Person>
{
	public Class<?> getTypClass() {
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
      return "org.sdmlib.test.examples.patternrewriteops.model.Person";
   }


   @Override
   public PersonSet getNewList(boolean keyValue)
   {
      return new PersonSet();
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
    * Loop through the current set of Person objects and collect a set of the Train objects reached via train. 
    * 
    * @return Set of Train objects reachable via train
    */
   public TrainSet getTrain()
   {
      TrainSet result = new TrainSet();
      
      for (Person obj : this)
      {
         result.with(obj.getTrain());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Person objects and collect all contained objects with reference train pointing to the object passed as parameter. 
    * 
    * @param value The object required as train neighbor of the collected results. 
    * 
    * @return Set of Train objects referring to value via train
    */
   public PersonSet filterTrain(Object value)
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
         if (neighbors.contains(obj.getTrain()) || (neighbors.isEmpty() && obj.getTrain() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Person object passed as parameter to the Train attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Train attributes.
    */
   public PersonSet withTrain(Train value)
   {
      for (Person obj : this)
      {
         obj.withTrain(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Person objects and collect a set of the Station objects reached via station. 
    * 
    * @return Set of Station objects reachable via station
    */
   public StationSet getStation()
   {
      StationSet result = new StationSet();
      
      for (Person obj : this)
      {
         result.with(obj.getStation());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Person objects and collect all contained objects with reference station pointing to the object passed as parameter. 
    * 
    * @param value The object required as station neighbor of the collected results. 
    * 
    * @return Set of Station objects referring to value via station
    */
   public PersonSet filterStation(Object value)
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
         if (neighbors.contains(obj.getStation()) || (neighbors.isEmpty() && obj.getStation() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Person object passed as parameter to the Station attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Station attributes.
    */
   public PersonSet withStation(Station value)
   {
      for (Person obj : this)
      {
         obj.withStation(value);
      }
      
      return this;
   }
}
