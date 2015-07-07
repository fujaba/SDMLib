/*
   Copyright (c) 2015 alex 
   
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
   
package org.sdmlib.test.examples.features.model.albertsets.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.test.examples.features.model.albertsets.House;
import org.sdmlib.test.examples.features.model.albertsets.Window;
import org.sdmlib.test.examples.features.model.albertsets.util.HouseSet;

import java.util.Collection;
import org.sdmlib.models.modelsets.ObjectSet;

public class WindowSet extends SDMSet<Window>
{

   public static final WindowSet EMPTY_SET = new WindowSet().withReadOnly(true);


   public WindowPO hasWindowPO()
   {
      return new WindowPO(this.toArray(new Window[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.features.model.albertsets.Window";
   }


   @SuppressWarnings("unchecked")
   public WindowSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Window>)value);
      }
      else if (value != null)
      {
         this.add((Window) value);
      }
      
      return this;
   }
   
   public WindowSet without(Window value)
   {
      this.remove(value);
      return this;
   }

   public HouseSet getHouse()
   {
      HouseSet result = new HouseSet();
      
      for (Window obj : this)
      {
         result.add(obj.getHouse());
      }
      
      return result;
   }

   public WindowSet hasHouse(Object value)
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
      
      WindowSet answer = new WindowSet();
      
      for (Window obj : this)
      {
         if (neighbors.contains(obj.getHouse()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public WindowSet withHouse(House value)
   {
      for (Window obj : this)
      {
         obj.withHouse(value);
      }
      
      return this;
   }

}
