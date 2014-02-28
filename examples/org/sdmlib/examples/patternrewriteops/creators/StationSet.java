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
   
package org.sdmlib.examples.patternrewriteops.creators;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.examples.patternrewriteops.Person;
import org.sdmlib.examples.patternrewriteops.SignalFlag;
import org.sdmlib.examples.patternrewriteops.Station;
import org.sdmlib.examples.patternrewriteops.Train;
import org.sdmlib.models.modelsets.StringList;

public class StationSet extends LinkedHashSet<Station> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Station elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.patternrewriteops.Station";
   }


   public StationSet with(Station value)
   {
      this.add(value);
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
         result.addAll(obj.getTrains());
      }
      
      return result;
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
         result.add(obj.getNext());
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
         result.add(obj.getPrev());
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
         result.addAll(obj.getPeople());
      }
      
      return result;
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
         result.add(obj.getFlag());
      }
      
      return result;
   }

   public StationSet withFlag(SignalFlag value)
   {
      for (Station obj : this)
      {
         obj.withFlag(value);
      }
      
      return this;
   }



   public StationPO startModelPattern()
   {
      org.sdmlib.examples.patternrewriteops.creators.ModelPattern pattern = new org.sdmlib.examples.patternrewriteops.creators.ModelPattern();
      
      StationPO patternObject = pattern.hasElementStationPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public StationSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Station>)value);
      }
      else if (value != null)
      {
         this.add((Station) value);
      }
      
      return this;
   }
   


   public StationPO hasStationPO()
   {
      org.sdmlib.examples.patternrewriteops.creators.ModelPattern pattern = new org.sdmlib.examples.patternrewriteops.creators.ModelPattern();
      
      StationPO patternObject = pattern.hasElementStationPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}




