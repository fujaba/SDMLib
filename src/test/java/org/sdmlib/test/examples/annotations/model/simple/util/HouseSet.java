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

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.annotations.model.simple.House;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.sdmlib.test.examples.annotations.model.simple.util.DoorSet;
import org.sdmlib.test.examples.annotations.model.simple.Door;
import org.sdmlib.test.examples.annotations.model.simple.util.WindowSet;
import org.sdmlib.test.examples.annotations.model.simple.Window;

public class HouseSet extends SimpleSet<House>
{
	public Class<?> getTypClass() {
		return House.class;
	}

   public HouseSet()
   {
      // empty
   }

   public HouseSet(House... objects)
   {
      for (House obj : objects)
      {
         this.add(obj);
      }
   }

   public HouseSet(Collection<House> objects)
   {
      this.addAll(objects);
   }

   public static final HouseSet EMPTY_SET = new HouseSet().withFlag(HouseSet.READONLY);


   public HousePO createHousePO()
   {
      return new HousePO(this.toArray(new House[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.annotations.model.simple.House";
   }


   @Override
   public HouseSet getNewList(boolean keyValue)
   {
      return new HouseSet();
   }


   @SuppressWarnings("unchecked")
   public HouseSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<House>)value);
      }
      else if (value != null)
      {
         this.add((House) value);
      }
      
      return this;
   }
   
   public HouseSet without(House value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public HouseSet init()
   {
      for (House obj : this)
      {
         obj.init();
      }
      return this;
   }

   /**
    * Loop through the current set of House objects and collect a set of the Door objects reached via doors. 
    * 
    * @return Set of Door objects reachable via doors
    */
   public DoorSet getDoors()
   {
      DoorSet result = new DoorSet();
      
      for (House obj : this)
      {
         result.with(obj.getDoors());
      }
      
      return result;
   }

   /**
    * Loop through the current set of House objects and collect all contained objects with reference doors pointing to the object passed as parameter. 
    * 
    * @param value The object required as doors neighbor of the collected results. 
    * 
    * @return Set of Door objects referring to value via doors
    */
   public HouseSet filterDoors(Object value)
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
      
      HouseSet answer = new HouseSet();
      
      for (House obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getDoors()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the House object passed as parameter to the Doors attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Doors attributes.
    */
   public HouseSet withDoors(Door value)
   {
      for (House obj : this)
      {
         obj.withDoors(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the House object passed as parameter from the Doors attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public HouseSet withoutDoors(Door value)
   {
      for (House obj : this)
      {
         obj.withoutDoors(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of House objects and collect a set of the Window objects reached via windows. 
    * 
    * @return Set of Window objects reachable via windows
    */
   public WindowSet getWindows()
   {
      WindowSet result = new WindowSet();
      
      for (House obj : this)
      {
         result.with(obj.getWindows());
      }
      
      return result;
   }

   /**
    * Loop through the current set of House objects and collect all contained objects with reference windows pointing to the object passed as parameter. 
    * 
    * @param value The object required as windows neighbor of the collected results. 
    * 
    * @return Set of Window objects referring to value via windows
    */
   public HouseSet filterWindows(Object value)
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
      
      HouseSet answer = new HouseSet();
      
      for (House obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getWindows()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the House object passed as parameter to the Windows attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Windows attributes.
    */
   public HouseSet withWindows(Window value)
   {
      for (House obj : this)
      {
         obj.withWindows(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the House object passed as parameter from the Windows attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public HouseSet withoutWindows(Window value)
   {
      for (House obj : this)
      {
         obj.withoutWindows(value);
      }
      
      return this;
   }

}
