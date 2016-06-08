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
   
package org.sdmlib.test.examples.annotations.model.simple.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.test.examples.annotations.model.simple.Window;
import java.util.Collection;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.test.examples.annotations.model.simple.util.HouseSet;
import org.sdmlib.test.examples.annotations.model.simple.House;

public class WindowSet extends SDMSet<Window>
{

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

   public static final WindowSet EMPTY_SET = new WindowSet();


   public WindowPO filterWindowPO()
   {
      return new WindowPO(this.toArray(new Window[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.annotations.model.simple.Window";
   }


   @SuppressWarnings("unchecked")
   public WindowSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
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

   /**
    * Loop through the current set of Window objects and collect a set of the House objects reached via house. 
    * 
    * @return Set of House objects reachable via house
    */
   public HouseSet getHouse()
   {
      HouseSet result = new HouseSet();
      
      for (Window obj : this)
      {
         result.with(obj.getHouse());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Window objects and collect all contained objects with reference house pointing to the object passed as parameter. 
    * 
    * @param value The object required as house neighbor of the collected results. 
    * 
    * @return Set of House objects referring to value via house
    */
   public WindowSet filterHouse(Object value)
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
         if (neighbors.contains(obj.getHouse()) || (neighbors.isEmpty() && obj.getHouse() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Window object passed as parameter to the House attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their House attributes.
    */
   public WindowSet withHouse(House value)
   {
      for (Window obj : this)
      {
         obj.withHouse(value);
      }
      
      return this;
   }

}
