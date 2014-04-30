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
   
package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.adamandeve.model.Adam;
import org.sdmlib.models.modelsets.StringList;
import java.util.Collection;

public class AdamSet extends SDMSet<Adam>
{


   public AdamPO hasAdamPO()
   {
      org.sdmlib.examples.adamandeve.model.util.ModelPattern pattern = new org.sdmlib.examples.adamandeve.model.util.ModelPattern();
      
      AdamPO patternObject = pattern.hasElementAdamPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.adamandeve.model.Adam";
   }


   public AdamSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Adam>)value);
      }
      else if (value != null)
      {
         this.add((Adam) value);
      }
      
      return this;
   }
   
   public AdamSet without(Adam value)
   {
      this.remove(value);
      return this;
   }

}

