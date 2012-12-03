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

import java.util.LinkedHashSet;
import org.sdmlib.models.modelsets.ModelSet;
import org.sdmlib.model.classes.test.Parent;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.model.classes.test.Uncle;

public class ParentSet extends LinkedHashSet<Parent> implements ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Parent elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.model.classes.test.Parent";
   }


   public ParentSet with(Parent value)
   {
      this.add(value);
      return this;
   }
   
   public ParentSet without(Parent value)
   {
      this.remove(value);
      return this;
   }
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Parent obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public ParentSet withName(String value)
   {
      for (Parent obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public UncleSet getUncle()
   {
      UncleSet result = new UncleSet();
      
      for (Parent obj : this)
      {
         result.add(obj.getUncle());
      }
      
      return result;
   }
   public ParentSet withUncle(Uncle value)
   {
      for (Parent obj : this)
      {
         obj.withUncle(value);
      }
      
      return this;
   }

}

