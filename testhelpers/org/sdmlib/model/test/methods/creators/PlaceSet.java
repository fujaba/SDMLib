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
   
package org.sdmlib.model.test.methods.creators;

import java.util.LinkedHashSet;

import org.sdmlib.model.test.methods.Place;
import org.sdmlib.models.modelsets.StringList;
import java.util.Collection;

public class PlaceSet extends LinkedHashSet<Place>
{
   
   //==========================================================================
   
   public PlaceSet findMyPosition()
   {
      for (Place obj : this)
      {
         obj.findMyPosition();
      }
      return this;
   }

   
   //==========================================================================
   
   public PlaceSet findMyPosition(String p0)
   {
      for (Place obj : this)
      {
         obj.findMyPosition( p0);
      }
      return this;
   }

   
   //==========================================================================
   
   public PlaceSet findMyPosition(String p0, int p1)
   {
      for (Place obj : this)
      {
         obj.findMyPosition( p0,  p1);
      }
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Place elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.model.test.methods.Place";
   }


   public PlacePO hasPlacePO()
   {
      org.sdmlib.model.test.methods.creators.ModelPattern pattern = new org.sdmlib.model.test.methods.creators.ModelPattern();
      
      PlacePO patternObject = pattern.hasElementPlacePO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public PlaceSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Place>)value);
      }
      else if (value != null)
      {
         this.add((Place) value);
      }
      
      return this;
   }
   
   public PlaceSet without(Place value)
   {
      this.remove(value);
      return this;
   }

}




