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
import java.util.Collections;

import org.sdmlib.test.examples.patternrewriteops.model.SignalFlag;
import org.sdmlib.test.examples.patternrewriteops.model.Station;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class SignalFlagSet extends SimpleSet<SignalFlag>
{
	public Class<?> getTypClass() {
		return SignalFlag.class;
	}

   public SignalFlagSet()
   {
      // empty
   }

   public SignalFlagSet(SignalFlag... objects)
   {
      for (SignalFlag obj : objects)
      {
         this.add(obj);
      }
   }

   public SignalFlagSet(Collection<SignalFlag> objects)
   {
      this.addAll(objects);
   }

   public static final SignalFlagSet EMPTY_SET = new SignalFlagSet().withFlag(SignalFlagSet.READONLY);


   public SignalFlagPO createSignalFlagPO()
   {
      return new SignalFlagPO(this.toArray(new SignalFlag[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.patternrewriteops.model.SignalFlag";
   }


   @Override
   public SignalFlagSet getNewList(boolean keyValue)
   {
      return new SignalFlagSet();
   }

   @SuppressWarnings("unchecked")
   public SignalFlagSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<SignalFlag>)value);
      }
      else if (value != null)
      {
         this.add((SignalFlag) value);
      }
      
      return this;
   }
   
   public SignalFlagSet without(SignalFlag value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of SignalFlag objects and collect a set of the Station objects reached via station. 
    * 
    * @return Set of Station objects reachable via station
    */
   public StationSet getStation()
   {
      StationSet result = new StationSet();
      
      for (SignalFlag obj : this)
      {
         result.with(obj.getStation());
      }
      
      return result;
   }

   /**
    * Loop through the current set of SignalFlag objects and collect all contained objects with reference station pointing to the object passed as parameter. 
    * 
    * @param value The object required as station neighbor of the collected results. 
    * 
    * @return Set of Station objects referring to value via station
    */
   public SignalFlagSet filterStation(Object value)
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
      
      SignalFlagSet answer = new SignalFlagSet();
      
      for (SignalFlag obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getStation()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the SignalFlag object passed as parameter to the Station attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Station attributes.
    */
   public SignalFlagSet withStation(Station value)
   {
      for (SignalFlag obj : this)
      {
         obj.withStation(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the SignalFlag object passed as parameter from the Station attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public SignalFlagSet withoutStation(Station value)
   {
      for (SignalFlag obj : this)
      {
         obj.withoutStation(value);
      }
      
      return this;
   }
}
