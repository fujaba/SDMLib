/*
   Copyright (c) 2017 zuendorf
   
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
import org.sdmlib.simple.model.issue29.C;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.simple.model.issue29.util.BSet;
import org.sdmlib.simple.model.issue29.B;

public class CSet extends SimpleSet<C>
{
	public Class<?> getTypClass() {
		return C.class;
	}

   public CSet()
   {
      // empty
   }

   public CSet(C... objects)
   {
      for (C obj : objects)
      {
         this.add(obj);
      }
   }

   public CSet(Collection<C> objects)
   {
      this.addAll(objects);
   }

   public static final CSet EMPTY_SET = new CSet().withFlag(CSet.READONLY);


   public CPO createCPO()
   {
      return new CPO(this.toArray(new C[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.issue29.C";
   }


   @Override
   public CSet getNewList(boolean keyValue)
   {
      return new CSet();
   }


   public CSet filter(Condition<C> condition) {
      CSet filterList = new CSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public CSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<C>)value);
      }
      else if (value != null)
      {
         this.add((C) value);
      }
      
      return this;
   }
   
   public CSet without(C value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of C objects and collect a set of the B objects reached via b. 
    * 
    * @return Set of B objects reachable via b
    */
   public BSet getB()
   {
      BSet result = new BSet();
      
      for (C obj : this)
      {
         result.with(obj.getB());
      }
      
      return result;
   }

   /**
    * Loop through the current set of C objects and collect all contained objects with reference b pointing to the object passed as parameter. 
    * 
    * @param value The object required as b neighbor of the collected results. 
    * 
    * @return Set of B objects referring to value via b
    */
   public CSet filterB(Object value)
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
      
      CSet answer = new CSet();
      
      for (C obj : this)
      {
         if (neighbors.contains(obj.getB()) || (neighbors.isEmpty() && obj.getB() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the C object passed as parameter to the B attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their B attributes.
    */
   public CSet withB(B value)
   {
      for (C obj : this)
      {
         obj.withB(value);
      }
      
      return this;
   }

}
