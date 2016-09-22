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
   
package org.sdmlib.simple.model.issue31.util;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.simple.model.issue31.B;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.simple.model.issue31.A;
import org.sdmlib.simple.model.issue31.util.ASet;
import java.util.Collection;

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
      return "org.sdmlib.simple.model.issue31.B";
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

   public ASet instanceOfA()
   {
      ASet result = new ASet();
      
      for(Object obj : this)
      {
         if (obj instanceof A)
         {
            result.with(obj);
         }
      }
      
      return result;
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

}
