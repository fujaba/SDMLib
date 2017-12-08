/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.test.examples.simpleModel.model.util;

import java.util.ArrayList;
import java.util.Collection;

import org.sdmlib.test.examples.simpleModel.model.MacList;

import de.uniks.networkparser.list.SimpleSet;

public class ArrayListSet extends SimpleSet<ArrayList>
{

   public static final ArrayListSet EMPTY_SET = new ArrayListSet().withFlag(ArrayListSet.READONLY);


   public ArrayListPO hasArrayListPO()
   {
      return new ArrayListPO(this.toArray(new ArrayList[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public ArrayListSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ArrayList<?>>)value);
      }
      else if (value != null)
      {
         this.add((ArrayList<?>) value);
      }
      
      return this;
   }
   
   public ArrayListSet without(ArrayList<?> value)
   {
      this.remove(value);
      return this;
   }



   public ArrayListPO filterArrayListPO()
   {
      return new ArrayListPO(this.toArray(new ArrayList[this.size()]));
   }


   public String getEntryType()
   {
      return "java.util.ArrayList";
   }

   public ArrayListSet()
   {
      // empty
   }

   public ArrayListSet(ArrayList... objects)
   {
      for (ArrayList obj : objects)
      {
         this.add(obj);
      }
   }

   public ArrayListSet(Collection<ArrayList> objects)
   {
      for (ArrayList obj : objects)
      {
         this.add(obj);
      }
   }


   public ArrayListPO createArrayListPO()
   {
      return new ArrayListPO(this.toArray(new ArrayList[this.size()]));
   }


   @Override
   public ArrayListSet getNewList(boolean keyValue)
   {
      return new ArrayListSet();
   }

   public MacListSet instanceOfMacList()
   {
      MacListSet result = new MacListSet();
      
      for(Object obj : this)
      {
         if (obj instanceof MacList)
         {
            result.with(obj);
         }
      }
      
      return result;
   }}
