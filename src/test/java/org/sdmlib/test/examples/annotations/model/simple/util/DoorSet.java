/*
   Copyright (c) 2018 zuendorf
   
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

import java.util.Collection;

import org.sdmlib.test.examples.annotations.model.simple.Door;
import org.sdmlib.test.examples.annotations.model.simple.House;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class DoorSet extends SimpleSet<Door>
{
	public Class<?> getTypClass() {
		return Door.class;
	}

   public DoorSet()
   {
      // empty
   }

   public DoorSet(Door... objects)
   {
      for (Door obj : objects)
      {
         this.add(obj);
      }
   }

   public DoorSet(Collection<Door> objects)
   {
      this.addAll(objects);
   }

   public static final DoorSet EMPTY_SET = new DoorSet().withFlag(DoorSet.READONLY);


   public DoorPO createDoorPO()
   {
      return new DoorPO(this.toArray(new Door[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.annotations.model.simple.Door";
   }


   @Override
   public DoorSet getNewList(boolean keyValue)
   {
      return new DoorSet();
   }


   @SuppressWarnings("unchecked")
   public DoorSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Door>)value);
      }
      else if (value != null)
      {
         this.add((Door) value);
      }
      
      return this;
   }
   
   public DoorSet without(Door value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of Door objects and collect a set of the House objects reached via house. 
    * 
    * @return Set of House objects reachable via house
    */
   public HouseSet getHouse()
   {
      HouseSet result = new HouseSet();
      
      for (Door obj : this)
      {
         result.with(obj.getHouse());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Door objects and collect all contained objects with reference house pointing to the object passed as parameter. 
    * 
    * @param value The object required as house neighbor of the collected results. 
    * 
    * @return Set of House objects referring to value via house
    */
   public DoorSet filterHouse(Object value)
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
      
      DoorSet answer = new DoorSet();
      
      for (Door obj : this)
      {
         if (neighbors.contains(obj.getHouse()) || (neighbors.isEmpty() && obj.getHouse() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Door object passed as parameter to the House attribute of each of it. 
    * 
    * @param value value    * @return The original set of ModelType objects now with the new neighbor attached to their House attributes.
    */
   public DoorSet withHouse(House value)
   {
      for (Door obj : this)
      {
         obj.withHouse(value);
      }
      
      return this;
   }

}
