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
   
package org.sdmlib.examples.ludo.model.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.ludo.model.Ludo;
import org.sdmlib.models.modelsets.StringList;
import java.util.Collection;
import org.sdmlib.models.modelsets.DateList;
import java.util.List;
import java.util.Date;

public class LudoSet extends SDMSet<Ludo>
{


   public LudoPO hasLudoPO()
   {
      org.sdmlib.examples.ludo.model.util.ModelPattern pattern = new org.sdmlib.examples.ludo.model.util.ModelPattern();
      
      LudoPO patternObject = pattern.hasElementLudoPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.ludo.model.Ludo";
   }


   public LudoSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Ludo>)value);
      }
      else if (value != null)
      {
         this.add((Ludo) value);
      }
      
      return this;
   }
   
   public LudoSet without(Ludo value)
   {
      this.remove(value);
      return this;
   }

   public DateList getDate()
   {
      DateList result = new DateList();
      
      for (Ludo obj : this)
      {
         result.add(obj.getDate());
      }
      
      return result;
   }

   public LudoSet hasDate(java.util.Date value)
   {
      LudoSet result = new LudoSet();
      
      for (Ludo obj : this)
      {
         if (value == obj.getDate())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LudoSet withDate(Date value)
   {
      for (Ludo obj : this)
      {
         obj.setDate(value);
      }
      
      return this;
   }

}

