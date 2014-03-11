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
import org.sdmlib.examples.patternrewriteops.Station;
import org.sdmlib.examples.patternrewriteops.Train;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.patternrewriteops.creators.StationSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.examples.patternrewriteops.creators.TrainSet;

public class PersonSet extends LinkedHashSet<Person> implements org.sdmlib.models.modelsets.ModelSet
{


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
      return "org.sdmlib.examples.patternrewriteops.Person";
   }


   public StationSet getStation()
   {
      StationSet result = new StationSet();
      
      for (Person obj : this)
      {
         result.add(obj.getStation());
      }
      
      return result;
   }

   public PersonSet withStation(Station value)
   {
      for (Person obj : this)
      {
         obj.withStation(value);
      }
      
      return this;
   }

   public TrainSet getTrain()
   {
      TrainSet result = new TrainSet();
      
      for (Person obj : this)
      {
         result.add(obj.getTrain());
      }
      
      return result;
   }

   public PersonSet withTrain(Train value)
   {
      for (Person obj : this)
      {
         obj.withTrain(value);
      }
      
      return this;
   }



   public PersonPO startModelPattern()
   {
      org.sdmlib.examples.patternrewriteops.creators.ModelPattern pattern = new org.sdmlib.examples.patternrewriteops.creators.ModelPattern();
      
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



   public PersonPO hasPersonPO()
   {
      org.sdmlib.examples.patternrewriteops.creators.ModelPattern pattern = new org.sdmlib.examples.patternrewriteops.creators.ModelPattern();
      
      PersonPO patternObject = pattern.hasElementPersonPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}




