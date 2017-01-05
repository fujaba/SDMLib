/*
   Copyright (c) 2016 zuendorf
   
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
   
package org.sdmlib.models.tables.util;

import java.util.Collection;

import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.SimpleSet;

public class ObjectSet extends SimpleSet<Object>
{
	protected Class<?> getTypClass() {
		return Object.class;
	}

   public ObjectSet()
   {
      // empty
   }

   public ObjectSet(Object... objects)
   {
      for (Object obj : objects)
      {
         this.add(obj);
      }
   }

   public ObjectSet(Collection<Object> objects)
   {
      this.addAll(objects);
   }

   public static final ObjectSet EMPTY_SET = new ObjectSet().withFlag(ObjectSet.READONLY);


   public ObjectPO createObjectPO()
   {
      return new ObjectPO(this.toArray(new Object[this.size()]));
   }


   public String getEntryType()
   {
      return "java.lang.Object";
   }


   @SuppressWarnings("unchecked")
   public ObjectSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Object>)value);
      }
      else if (value != null)
      {
         this.add((Object) value);
      }
      
      return this;
   }
   
   public ObjectSet without(Object value)
   {
      this.remove(value);
      return this;
   }



   @Override
   public ObjectSet getNewList(boolean keyValue)
   {
      return new ObjectSet();
   }


   public ObjectSet filter(Condition<Object> condition) {
      ObjectSet filterList = new ObjectSet();
      filterItems(filterList, condition);
      return filterList;
   }}
