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

import java.util.Collection;

import org.sdmlib.test.examples.features.model.albertsets.House;
import org.sdmlib.test.examples.features.model.albertsets.Window;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.features.model.albertsets.util.HouseSet;

public class WindowSet extends SimpleSet<Window>
{

   public static final WindowSet EMPTY_SET = new WindowSet().withFlag(WindowSet.READONLY);


   public WindowPO hasWindowPO()
   {
      return new WindowPO(this.toArray(new Window[this.size()]));
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



   public WindowPO filterWindowPO()
   {
      return new WindowPO(this.toArray(new Window[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.features.model.albertsets.Window";
   }

   public WindowSet()
   {
      // empty
   }

   public WindowSet(Window... objects)
   {
      for (Window obj : objects)
      {
         this.add(obj);
      }
   }

   public WindowSet(Collection<Window> objects)
   {
      this.addAll(objects);
   }


   public WindowPO createWindowPO()
   {
      return new WindowPO(this.toArray(new Window[this.size()]));
   }


   @Override
   public WindowSet getNewList(boolean keyValue)
   {
      return new WindowSet();
   }
}
