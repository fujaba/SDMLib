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
import org.sdmlib.simple.model.issue30.ZoombieOwner;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;

public class ZoombieOwnerSet extends SimpleSet<ZoombieOwner>
{
	protected Class<?> getTypClass() {
		return ZoombieOwner.class;
	}

   public ZoombieOwnerSet()
   {
      // empty
   }

   public ZoombieOwnerSet(ZoombieOwner... objects)
   {
      for (ZoombieOwner obj : objects)
      {
         this.add(obj);
      }
   }

   public ZoombieOwnerSet(Collection<ZoombieOwner> objects)
   {
      this.addAll(objects);
   }

   public static final ZoombieOwnerSet EMPTY_SET = new ZoombieOwnerSet().withFlag(ZoombieOwnerSet.READONLY);


   public ZoombieOwnerPO createZoombieOwnerPO()
   {
      return new ZoombieOwnerPO(this.toArray(new ZoombieOwner[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.issue30.ZoombieOwner";
   }


   @Override
   public ZoombieOwnerSet getNewList(boolean keyValue)
   {
      return new ZoombieOwnerSet();
   }


   public ZoombieOwnerSet filter(Condition<ZoombieOwner> condition) {
      ZoombieOwnerSet filterList = new ZoombieOwnerSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public ZoombieOwnerSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ZoombieOwner>)value);
      }
      else if (value != null)
      {
         this.add((ZoombieOwner) value);
      }
      
      return this;
   }
   
   public ZoombieOwnerSet without(ZoombieOwner value)
   {
      this.remove(value);
      return this;
   }

}
