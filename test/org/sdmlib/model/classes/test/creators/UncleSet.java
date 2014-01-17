/*
   Copyright (c) 2012 zuendorf 
   
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
   
package org.sdmlib.model.classes.test.creators;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.model.classes.test.Parent;
import org.sdmlib.model.classes.test.Uncle;
import org.sdmlib.models.modelsets.ModelSet;
import org.sdmlib.models.modelsets.StringList;

public class UncleSet extends LinkedHashSet<Uncle> implements ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Uncle elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.model.classes.test.Uncle";
   }


   public UncleSet with(Uncle value)
   {
      this.add(value);
      return this;
   }
   
   public UncleSet without(Uncle value)
   {
      this.remove(value);
      return this;
   }
   public ParentSet getBrother()
   {
      ParentSet result = new ParentSet();
      
      for (Uncle obj : this)
      {
         result.add(obj.getBrother());
      }
      
      return result;
   }
   public UncleSet withBrother(Parent value)
   {
      for (Uncle obj : this)
      {
         obj.withBrother(value);
      }
      
      return this;
   }



   public UnclePO startModelPattern()
   {
      org.sdmlib.model.classes.test.creators.ModelPattern pattern = new org.sdmlib.model.classes.test.creators.ModelPattern();
      
      UnclePO patternObject = pattern.hasElementUnclePO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public UncleSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Uncle>)value);
      }
      else if (value != null)
      {
         this.add((Uncle) value);
      }
      
      return this;
   }
   

}


