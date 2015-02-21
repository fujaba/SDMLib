/*
   Copyright (c) 2014 Stefan 
   
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
   
package org.sdmlib.examples.patternrewriteops.model.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.examples.patternrewriteops.model.Person;
import org.sdmlib.examples.patternrewriteops.model.SignalFlag;
import org.sdmlib.examples.patternrewriteops.model.Station;
import org.sdmlib.examples.patternrewriteops.model.Train;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.patternrewriteops.model.util.TrainSet;
import org.sdmlib.examples.patternrewriteops.model.util.PersonSet;
import org.sdmlib.examples.patternrewriteops.model.util.SignalFlagSet;

public class StationSet extends SDMSet<Station>
{
        private static final long serialVersionUID = 1L;


   public StationPO hasStationPO()
   {
      return new StationPO (this.toArray(new Station[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.patternrewriteops.model.Station";
   }


   public StationSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
           Collection<?> collection = (Collection<?>) value;
           for(Object item : collection){
               this.add((Station) item);
           }
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

   public TrainSet getTrains()
   {
      TrainSet result = new TrainSet();
      
      for (Station obj : this)
      {
         result.with(obj.getTrains());
      }
      
      return result;
   }

   public StationSet hasTrains(Object value)
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

   public StationSet withTrains(Train value)
   {
      for (Station obj : this)
      {
         obj.withTrains(value);
      }
      
      return this;
   }

   public StationSet withoutTrains(Train value)
   {
      for (Station obj : this)
      {
         obj.withoutTrains(value);
      }
      
      return this;
   }

   public StationSet getNext()
   {
      StationSet result = new StationSet();
      
      for (Station obj : this)
      {
         result.with(obj.getNext());
      }
      
      return result;
   }

   public StationSet hasNext(Object value)
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
         if (neighbors.contains(obj.getNext()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


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

   public StationSet withNext(Station value)
   {
      for (Station obj : this)
      {
         obj.withNext(value);
      }
      
      return this;
   }

   public StationSet getPrev()
   {
      StationSet result = new StationSet();
      
      for (Station obj : this)
      {
         result.with(obj.getPrev());
      }
      
      return result;
   }

   public StationSet hasPrev(Object value)
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
         if (neighbors.contains(obj.getPrev()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


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

   public StationSet withPrev(Station value)
   {
      for (Station obj : this)
      {
         obj.withPrev(value);
      }
      
      return this;
   }

   public PersonSet getPeople()
   {
      PersonSet result = new PersonSet();
      
      for (Station obj : this)
      {
         result.with(obj.getPeople());
      }
      
      return result;
   }

   public StationSet hasPeople(Object value)
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

   public StationSet withPeople(Person value)
   {
      for (Station obj : this)
      {
         obj.withPeople(value);
      }
      
      return this;
   }

   public StationSet withoutPeople(Person value)
   {
      for (Station obj : this)
      {
         obj.withoutPeople(value);
      }
      
      return this;
   }

   public SignalFlagSet getFlag()
   {
      SignalFlagSet result = new SignalFlagSet();
      
      for (Station obj : this)
      {
         result.with(obj.getFlag());
      }
      
      return result;
   }

   public StationSet hasFlag(Object value)
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
         if (neighbors.contains(obj.getFlag()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public StationSet withFlag(SignalFlag value)
   {
      for (Station obj : this)
      {
         obj.withFlag(value);
      }
      
      return this;
   }


   public static final StationSet EMPTY_SET = new StationSet().withReadonly(true);
}

