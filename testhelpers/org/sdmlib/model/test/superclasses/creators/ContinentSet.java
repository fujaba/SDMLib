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

package org.sdmlib.model.test.superclasses.creators;

import java.util.LinkedHashSet;

import org.sdmlib.model.test.superclasses.Continent;
import org.sdmlib.models.modelsets.StringList;
import java.util.Collection;

public class ContinentSet extends LinkedHashSet<Continent>
{
   public StringList getTest()
   {
      StringList result = new StringList();

      for (Continent obj : this)
      {
         result.add(obj.getTest());
      }

      return result;
   }

   public ContinentSet withTest(String value)
   {
      for (Continent obj : this)
      {
         obj.withTest(value);
      }

      return this;
   }

   public String toString()
   {
      StringList stringList = new StringList();

      for (Continent elem : this)
      {
         stringList.add(elem.toString());
      }

      return "(" + stringList.concat(", ") + ")";
   }

   public String getEntryType()
   {
      return "org.sdmlib.model.test.superclasses.Continent";
   }

   public ContinentPO hasContinentPO()
   {
      org.sdmlib.model.test.superclasses.creators.ModelPattern pattern = new org.sdmlib.model.test.superclasses.creators.ModelPattern();

      ContinentPO patternObject = pattern.hasElementContinentPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }

   public ContinentSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Continent>) value);
      }
      else if (value != null)
      {
         this.add((Continent) value);
      }

      return this;
   }

   public ContinentSet without(Continent value)
   {
      this.remove(value);
      return this;
   }

}
