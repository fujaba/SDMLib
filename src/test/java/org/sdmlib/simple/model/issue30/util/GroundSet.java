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
import org.sdmlib.simple.model.issue30.Ground;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.simple.model.issue30.A;
import org.sdmlib.simple.model.issue30.util.ASet;
import java.util.Collection;

public class GroundSet extends SimpleSet<Ground>
{
	protected Class<?> getTypClass() {
		return Ground.class;
	}

   public GroundSet()
   {
      // empty
   }

   public GroundSet(Ground... objects)
   {
      for (Ground obj : objects)
      {
         this.add(obj);
      }
   }

   public GroundSet(Collection<Ground> objects)
   {
      this.addAll(objects);
   }

   public static final GroundSet EMPTY_SET = new GroundSet().withFlag(GroundSet.READONLY);


   public GroundPO createGroundPO()
   {
      return new GroundPO(this.toArray(new Ground[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.issue30.Ground";
   }


   @Override
   public GroundSet getNewList(boolean keyValue)
   {
      return new GroundSet();
   }


   public GroundSet filter(Condition<Ground> condition) {
      GroundSet filterList = new GroundSet();
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
   public GroundSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Ground>)value);
      }
      else if (value != null)
      {
         this.add((Ground) value);
      }
      
      return this;
   }
   
   public GroundSet without(Ground value)
   {
      this.remove(value);
      return this;
   }

}
