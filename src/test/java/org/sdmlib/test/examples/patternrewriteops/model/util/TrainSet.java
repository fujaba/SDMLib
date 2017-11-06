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

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.patternrewriteops.model.Train;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.sdmlib.test.examples.patternrewriteops.model.util.PersonSet;
import org.sdmlib.test.examples.patternrewriteops.model.Person;
import org.sdmlib.test.examples.patternrewriteops.model.util.StationSet;
import org.sdmlib.test.examples.patternrewriteops.model.Station;

public class TrainSet extends SimpleSet<Train>
{
	public Class<?> getTypClass() {
		return Train.class;
	}

   public TrainSet()
   {
      // empty
   }

   public TrainSet(Train... objects)
   {
      for (Train obj : objects)
      {
         this.add(obj);
      }
   }

   public TrainSet(Collection<Train> objects)
   {
      this.addAll(objects);
   }

   public static final TrainSet EMPTY_SET = new TrainSet().withFlag(TrainSet.READONLY);


   public TrainPO createTrainPO()
   {
      return new TrainPO(this.toArray(new Train[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.patternrewriteops.model.Train";
   }


   @Override
   public TrainSet getNewList(boolean keyValue)
   {
      return new TrainSet();
   }


   public TrainSet filter(Condition<Train> condition) {
      TrainSet filterList = new TrainSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public TrainSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Train>)value);
      }
      else if (value != null)
      {
         this.add((Train) value);
      }
      
      return this;
   }
   
   public TrainSet without(Train value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of Train objects and collect a set of the Person objects reached via passengers. 
    * 
    * @return Set of Person objects reachable via passengers
    */
   public PersonSet getPassengers()
   {
      PersonSet result = new PersonSet();
      
      for (Train obj : this)
      {
         result.with(obj.getPassengers());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Train objects and collect all contained objects with reference passengers pointing to the object passed as parameter. 
    * 
    * @param value The object required as passengers neighbor of the collected results. 
    * 
    * @return Set of Person objects referring to value via passengers
    */
   public TrainSet filterPassengers(Object value)
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
      
      TrainSet answer = new TrainSet();
      
      for (Train obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPassengers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Train object passed as parameter to the Passengers attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Passengers attributes.
    */
   public TrainSet withPassengers(Person value)
   {
      for (Train obj : this)
      {
         obj.withPassengers(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Train object passed as parameter from the Passengers attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TrainSet withoutPassengers(Person value)
   {
      for (Train obj : this)
      {
         obj.withoutPassengers(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Train objects and collect a set of the Station objects reached via station. 
    * 
    * @return Set of Station objects reachable via station
    */
   public StationSet getStation()
   {
      StationSet result = new StationSet();
      
      for (Train obj : this)
      {
         result.with(obj.getStation());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Train objects and collect all contained objects with reference station pointing to the object passed as parameter. 
    * 
    * @param value The object required as station neighbor of the collected results. 
    * 
    * @return Set of Station objects referring to value via station
    */
   public TrainSet filterStation(Object value)
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
      
      TrainSet answer = new TrainSet();
      
      for (Train obj : this)
      {
         if (neighbors.contains(obj.getStation()) || (neighbors.isEmpty() && obj.getStation() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Train object passed as parameter to the Station attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Station attributes.
    */
   public TrainSet withStation(Station value)
   {
      for (Train obj : this)
      {
         obj.withStation(value);
      }
      
      return this;
   }
}
