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

package org.sdmlib.model.classes.creators;

import java.util.LinkedHashSet;

import org.sdmlib.model.classes.ReverseClassModelTest;
import org.sdmlib.models.modelsets.StringList;
import java.util.Collection;

public class ReverseClassModelTestSet extends
      LinkedHashSet<ReverseClassModelTest>
{

   public String toString()
   {
      StringList stringList = new StringList();

      for (ReverseClassModelTest elem : this)
      {
         stringList.add(elem.toString());
      }

      return "(" + stringList.concat(", ") + ")";
   }

   public String getEntryType()
   {
      return "org.sdmlib.model.classes.ReverseClassModelTest";
   }

   public ReverseClassModelTestPO hasReverseClassModelTestPO()
   {
      org.sdmlib.model.classes.creators.ModelPattern pattern = new org.sdmlib.model.classes.creators.ModelPattern();

      ReverseClassModelTestPO patternObject = pattern
         .hasElementReverseClassModelTestPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }

   public ReverseClassModelTestSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ReverseClassModelTest>) value);
      }
      else if (value != null)
      {
         this.add((ReverseClassModelTest) value);
      }

      return this;
   }

   public ReverseClassModelTestSet without(ReverseClassModelTest value)
   {
      this.remove(value);
      return this;
   }

}
