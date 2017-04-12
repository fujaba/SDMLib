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
   
package org.sdmlib.simple.model.attribute_e.util;

import java.util.Collection;

import de.uniks.networkparser.list.SimpleSet;

public class SimpleSetSet extends SimpleSet<SimpleSet>
{
	public Class<?> getTypClass() {
		return SimpleSet.class;
	}

   public SimpleSetSet()
   {
      // empty
   }

   public SimpleSetSet(SimpleSet... objects)
   {
      for (SimpleSet obj : objects)
      {
         this.add(obj);
      }
   }

   public SimpleSetSet(Collection<SimpleSet> objects)
   {
      this.addAll(objects);
   }

   public static final SimpleSetSet EMPTY_SET = new SimpleSetSet().withFlag(SimpleSetSet.READONLY);


   public SimpleSetPO createSimpleSetPO()
   {
      return new SimpleSetPO(this.toArray(new SimpleSet[this.size()]));
   }


   public String getEntryType()
   {
      return "de.uniks.networkparser.list.SimpleSet";
   }


   @SuppressWarnings("unchecked")
   public SimpleSetSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<SimpleSet>)value);
      }
      else if (value != null)
      {
         this.add((SimpleSet) value);
      }
      
      return this;
   }
   
   public SimpleSetSet without(SimpleSet value)
   {
      this.remove(value);
      return this;
   }

}
