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
import org.sdmlib.test.examples.patternrewriteops.model.Station;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.test.examples.patternrewriteops.model.util.StationSet;
import java.util.Collections;
import org.sdmlib.test.examples.patternrewriteops.model.util.PersonSet;
import org.sdmlib.test.examples.patternrewriteops.model.Person;
import org.sdmlib.test.examples.patternrewriteops.model.util.SignalFlagSet;
import org.sdmlib.test.examples.patternrewriteops.model.SignalFlag;
import org.sdmlib.test.examples.patternrewriteops.model.util.TrainSet;
import org.sdmlib.test.examples.patternrewriteops.model.Train;

public class StationSet extends SimpleSet<Station>
{
	public Class<?> getTypClass() {
		return Station.class;
	}

   public StationSet()
   {
      // empty
   }

   public StationSet(Station... objects)
   {
      for (Station obj : objects)
      {
         this.add(obj);
      }
   }

   public StationSet(Collection<Station> objects)
   {
      this.addAll(objects);
   }

   public static final StationSet EMPTY_SET = new StationSet().withFlag(StationSet.READONLY);


   public StationPO createStationPO()
   {
      return new StationPO(this.toArray(new Station[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.patternrewriteops.model.Station";
   }


   @Override
   public StationSet getNewList(boolean keyValue)
   {
      return new StationSet();
   }


   public StationSet filter(Condition<Station> condition) {
      StationSet filterList = new StationSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public StationSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Station>)value);
      }
      else if (value != null)
      {
         this.add((Station) value);
      }
      
      return this;
   }
   
   public StationSet without(Station value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of Station objects and collect a set of the Station objects reached via prev. 
    * 
    * @return Set of Station objects reachable via prev
    */
   public StationSet getPrev()
   {
      StationSet result = new StationSet();
      
      for (Station obj : this)
      {
         result.with(obj.getPrev());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Station objects and collect all contained objects with reference prev pointing to the object passed as parameter. 
    * 
    * @param value The object required as prev neighbor of the collected results. 
    * 
    * @return Set of Station objects referring to value via prev
    */
   public StationSet filterPrev(Object value)
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
      
      StationSet answer = new StationSet();
      
      for (Station obj : this)
      {
         if (neighbors.contains(obj.getPrev()) || (neighbors.isEmpty() && obj.getPrev() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow prev reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Station objects reachable via prev transitively (including the start set)
    */
   public StationSet getPrevTransitive()
   {
      StationSet todo = new StationSet().with(this);
      
      StationSet result = new StationSet();
      
      while ( ! todo.isEmpty())
      {
         Station current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getPrev()))
            {
               todo.with(current.getPrev());
            }
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Station object passed as parameter to the Prev attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Prev attributes.
    */
   public StationSet withPrev(Station value)
   {
      for (Station obj : this)
      {
         obj.withPrev(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Station objects and collect a set of the Station objects reached via next. 
    * 
    * @return Set of Station objects reachable via next
    */
   public StationSet getNext()
   {
      StationSet result = new StationSet();
      
      for (Station obj : this)
      {
         result.with(obj.getNext());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Station objects and collect all contained objects with reference next pointing to the object passed as parameter. 
    * 
    * @param value The object required as next neighbor of the collected results. 
    * 
    * @return Set of Station objects referring to value via next
    */
   public StationSet filterNext(Object value)
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
      
      StationSet answer = new StationSet();
      
      for (Station obj : this)
      {
         if (neighbors.contains(obj.getNext()) || (neighbors.isEmpty() && obj.getNext() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow next reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Station objects reachable via next transitively (including the start set)
    */
   public StationSet getNextTransitive()
   {
      StationSet todo = new StationSet().with(this);
      
      StationSet result = new StationSet();
      
      while ( ! todo.isEmpty())
      {
         Station current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getNext()))
            {
               todo.with(current.getNext());
            }
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Station object passed as parameter to the Next attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Next attributes.
    */
   public StationSet withNext(Station value)
   {
      for (Station obj : this)
      {
         obj.withNext(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Station objects and collect a set of the Person objects reached via people. 
    * 
    * @return Set of Person objects reachable via people
    */
   public PersonSet getPeople()
   {
      PersonSet result = new PersonSet();
      
      for (Station obj : this)
      {
         result.with(obj.getPeople());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Station objects and collect all contained objects with reference people pointing to the object passed as parameter. 
    * 
    * @param value The object required as people neighbor of the collected results. 
    * 
    * @return Set of Person objects referring to value via people
    */
   public StationSet filterPeople(Object value)
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
      
      StationSet answer = new StationSet();
      
      for (Station obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPeople()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Station object passed as parameter to the People attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their People attributes.
    */
   public StationSet withPeople(Person value)
   {
      for (Station obj : this)
      {
         obj.withPeople(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Station object passed as parameter from the People attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public StationSet withoutPeople(Person value)
   {
      for (Station obj : this)
      {
         obj.withoutPeople(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Station objects and collect a set of the SignalFlag objects reached via flag. 
    * 
    * @return Set of SignalFlag objects reachable via flag
    */
   public SignalFlagSet getFlag()
   {
      SignalFlagSet result = new SignalFlagSet();
      
      for (Station obj : this)
      {
         result.with(obj.getFlag());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Station objects and collect all contained objects with reference flag pointing to the object passed as parameter. 
    * 
    * @param value The object required as flag neighbor of the collected results. 
    * 
    * @return Set of SignalFlag objects referring to value via flag
    */
   public StationSet filterFlag(Object value)
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
      
      StationSet answer = new StationSet();
      
      for (Station obj : this)
      {
         if (neighbors.contains(obj.getFlag()) || (neighbors.isEmpty() && obj.getFlag() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Station object passed as parameter to the Flag attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Flag attributes.
    */
   public StationSet withFlag(SignalFlag value)
   {
      for (Station obj : this)
      {
         obj.withFlag(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Station objects and collect a set of the Train objects reached via trains. 
    * 
    * @return Set of Train objects reachable via trains
    */
   public TrainSet getTrains()
   {
      TrainSet result = new TrainSet();
      
      for (Station obj : this)
      {
         result.with(obj.getTrains());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Station objects and collect all contained objects with reference trains pointing to the object passed as parameter. 
    * 
    * @param value The object required as trains neighbor of the collected results. 
    * 
    * @return Set of Train objects referring to value via trains
    */
   public StationSet filterTrains(Object value)
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
      
      StationSet answer = new StationSet();
      
      for (Station obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getTrains()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Station object passed as parameter to the Trains attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Trains attributes.
    */
   public StationSet withTrains(Train value)
   {
      for (Station obj : this)
      {
         obj.withTrains(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Station object passed as parameter from the Trains attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public StationSet withoutTrains(Train value)
   {
      for (Station obj : this)
      {
         obj.withoutTrains(value);
      }
      
      return this;
   }

}
