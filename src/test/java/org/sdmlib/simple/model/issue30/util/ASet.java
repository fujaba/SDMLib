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
   
package org.sdmlib.simple.model.issue30.util;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.simple.model.issue30.A;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;

public class ASet extends SimpleSet<A>
{
	protected Class<?> getTypClass() {
		return A.class;
	}

   public ASet()
   {
      // empty
   }

   public ASet(A... objects)
   {
      for (A obj : objects)
      {
         this.add(obj);
      }
   }

   public ASet(Collection<A> objects)
   {
      this.addAll(objects);
   }

   public static final ASet EMPTY_SET = new ASet().withFlag(ASet.READONLY);


   public APO createAPO()
   {
      return new APO(this.toArray(new A[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.issue30.A";
   }


   @Override
   public ASet getNewList(boolean keyValue)
   {
      return new ASet();
   }


   public ASet filter(Condition<A> condition) {
      ASet filterList = new ASet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public ASet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<A>)value);
      }
      else if (value != null)
      {
         this.add((A) value);
      }
      
      return this;
   }
   
   public ASet without(A value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public de.uniks.networkparser.list.BooleanList checkEnd()
   {
      
      de.uniks.networkparser.list.BooleanList result = new de.uniks.networkparser.list.BooleanList();
      
      for (A obj : this)
      {
         result.add( obj.checkEnd() );
      }
      return result;
   }

}
