/*
   Copyright (c) 2015 Stefan
   
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
   
package org.sdmlib.test.examples.SimpleModelWithSet.model.util;

import de.uniks.networkparser.list.SDMSet;
import org.sdmlib.test.examples.SimpleModelWithSet.model.Child;
import java.util.Collection;
import org.sdmlib.test.examples.SimpleModelWithSet.model.Person;

public class ChildSet extends SDMSet<Child>
{

   public static final ChildSet EMPTY_SET = new ChildSet().withReadOnly();


   public ChildPO hasChildPO()
   {
      return new ChildPO(this.toArray(new Child[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.SimpleModelWithSet.model.Child";
   }


   @SuppressWarnings("unchecked")
   public ChildSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Child>)value);
      }
      else if (value != null)
      {
         this.add((Child) value);
      }
      
      return this;
   }
   
   public ChildSet without(Child value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public ChildSet setParent(Person parent)
   {
      for (Child obj : this)
      {
         obj.setParent(parent);
      }
      return this;
   }


   /**
    * Loop through the current set of Child objects and collect a list of the parent attribute values. 
    * 
    * @return List of Person objects reachable via parent attribute
    */
   public PersonSet getParent()
   {
      PersonSet result = new PersonSet();
      
      for (Child obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Child objects and collect those Child objects where the parent attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Child objects that match the parameter
    */
   public ChildSet hasParent(Person value)
   {
      ChildSet result = new ChildSet();
      
      for (Child obj : this)
      {
         if (value == obj.getParent())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Child objects and assign value to the parent attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Child objects now with new attribute values.
    */
   public ChildSet withParent(Person value)
   {
      for (Child obj : this)
      {
         obj.setParent(value);
      }
      
      return this;
   }

}
