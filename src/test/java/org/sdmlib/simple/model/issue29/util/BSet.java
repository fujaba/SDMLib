/*
   Copyright (c) 2016 Stefan
   
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
   
package org.sdmlib.simple.model.issue29.util;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.simple.model.issue29.B;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.simple.model.issue29.util.CSet;
import org.sdmlib.simple.model.issue29.C;

public class BSet extends SimpleSet<B>
{
	protected Class<?> getTypClass() {
		return B.class;
	}

   public BSet()
   {
      // empty
   }

   public BSet(B... objects)
   {
      for (B obj : objects)
      {
         this.add(obj);
      }
   }

   public BSet(Collection<B> objects)
   {
      this.addAll(objects);
   }

   public static final BSet EMPTY_SET = new BSet().withFlag(BSet.READONLY);


   public BPO createBPO()
   {
      return new BPO(this.toArray(new B[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.issue29.B";
   }


   @Override
   public BSet getNewList(boolean keyValue)
   {
      return new BSet();
   }


   public BSet filter(Condition<B> condition) {
      BSet filterList = new BSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public BSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<B>)value);
      }
      else if (value != null)
      {
         this.add((B) value);
      }
      
      return this;
   }
   
   public BSet without(B value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of B objects and collect a set of the C objects reached via c. 
    * 
    * @return Set of C objects reachable via c
    */
   public CSet getC()
   {
      CSet result = new CSet();
      
      for (B obj : this)
      {
         result.with(obj.getC());
      }
      
      return result;
   }

   /**
    * Loop through the current set of B objects and collect all contained objects with reference c pointing to the object passed as parameter. 
    * 
    * @param value The object required as c neighbor of the collected results. 
    * 
    * @return Set of C objects referring to value via c
    */
   public BSet filterC(Object value)
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
      
      BSet answer = new BSet();
      
      for (B obj : this)
      {
         if (neighbors.contains(obj.getC()) || (neighbors.isEmpty() && obj.getC() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the B object passed as parameter to the C attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their C attributes.
    */
   public BSet withC(C value)
   {
      for (B obj : this)
      {
         obj.withC(value);
      }
      
      return this;
   }

}
