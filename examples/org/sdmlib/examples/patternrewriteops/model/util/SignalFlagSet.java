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

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.patternrewriteops.model.SignalFlag;
import org.sdmlib.models.modelsets.StringList;
import java.util.Collection;
import org.sdmlib.examples.patternrewriteops.model.util.StationSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.examples.patternrewriteops.model.Station;

public class SignalFlagSet extends SDMSet<SignalFlag>
{


   public SignalFlagPO hasSignalFlagPO()
   {
      org.sdmlib.examples.patternrewriteops.model.util.ModelPattern pattern = new org.sdmlib.examples.patternrewriteops.model.util.ModelPattern();
      
      SignalFlagPO patternObject = pattern.hasElementSignalFlagPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.patternrewriteops.model.SignalFlag";
   }


   public SignalFlagSet with(Object value)
   {
      if (value instanceof java.util.Collection)
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

   public StationSet getStation()
   {
      StationSet result = new StationSet();
      
      for (SignalFlag obj : this)
      {
         result.with(obj.getStation());
      }
      
      return result;
   }

   public SignalFlagSet hasStation(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection) value);
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

   public SignalFlagSet withStation(Station value)
   {
      for (SignalFlag obj : this)
      {
         obj.withStation(value);
      }
      
      return this;
   }

   public SignalFlagSet withoutStation(Station value)
   {
      for (SignalFlag obj : this)
      {
         obj.withoutStation(value);
      }
      
      return this;
   }

}

