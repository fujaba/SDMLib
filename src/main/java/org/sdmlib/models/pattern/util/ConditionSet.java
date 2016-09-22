/*
   Copyright (c) 2016 christoph
   
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
   
package org.sdmlib.models.pattern.util;

import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;

public class ConditionSet extends SimpleSet<Condition>
{
	protected Class<?> getTypClass() {
		return Condition.class;
	}

   public ConditionSet()
   {
      // empty
   }

   public ConditionSet(Condition... objects)
   {
      for (Condition obj : objects)
      {
         this.add(obj);
      }
   }

   public ConditionSet(Collection<Condition> objects)
   {
      this.addAll(objects);
   }

   public static final ConditionSet EMPTY_SET = new ConditionSet().withFlag(ConditionSet.READONLY);


   public ConditionPO createConditionPO()
   {
      return new ConditionPO(this.toArray(new Condition[this.size()]));
   }


   public String getEntryType()
   {
      return "de.uniks.networkparser.interfaces.Condition";
   }


   @SuppressWarnings("unchecked")
   public ConditionSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Condition>)value);
      }
      else if (value != null)
      {
         this.add((Condition) value);
      }
      
      return this;
   }
   
   public ConditionSet without(Condition value)
   {
      this.remove(value);
      return this;
   }

}
