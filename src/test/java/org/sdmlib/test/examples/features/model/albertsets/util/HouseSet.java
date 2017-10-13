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
import java.util.Collections;

import org.sdmlib.test.examples.features.model.albertsets.Door;
import org.sdmlib.test.examples.features.model.albertsets.House;
import org.sdmlib.test.examples.features.model.albertsets.Window;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.test.examples.features.model.albertsets.util.DoorSet;
import org.sdmlib.test.examples.features.model.albertsets.util.WindowSet;

public class HouseSet extends SimpleSet<House>
{

   public static final HouseSet EMPTY_SET = new HouseSet().withFlag(HouseSet.READONLY);


   public HousePO hasHousePO()
   {
      return new HousePO(this.toArray(new House[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public HouseSet with(Object value)
   {
      if (value instanceof java.util.Collection)
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

   public DoorSet getDoors()
   {
      DoorSet result = new DoorSet();
      
      for (House obj : this)
      {
         result.addAll(obj.getDoors());
      }
      
      return result;
   }

   public HouseSet hasDoors(Object value)
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

   public HouseSet withDoors(Door value)
   {
      for (House obj : this)
      {
         obj.withDoors(value);
      }
      
      return this;
   }

   public HouseSet withoutDoors(Door value)
   {
      for (House obj : this)
      {
         obj.withoutDoors(value);
      }
      
      return this;
   }

   public WindowSet getWindows()
   {
      WindowSet result = new WindowSet();
      
      for (House obj : this)
      {
         result.addAll(obj.getWindows());
      }
      
      return result;
   }

   public HouseSet hasWindows(Object value)
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

   public HouseSet withWindows(Window value)
   {
      for (House obj : this)
      {
         obj.withWindows(value);
      }
      
      return this;
   }

   public HouseSet withoutWindows(Window value)
   {
      for (House obj : this)
      {
         obj.withoutWindows(value);
      }
      
      return this;
   }



   public HousePO filterHousePO()
   {
      return new HousePO(this.toArray(new House[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.features.model.albertsets.House";
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


   public HousePO createHousePO()
   {
      return new HousePO(this.toArray(new House[this.size()]));
   }


   @Override
   public HouseSet getNewList(boolean keyValue)
   {
      return new HouseSet();
   }


   public HouseSet filter(Condition<House> condition) {
      HouseSet filterList = new HouseSet();
      filterItems(filterList, condition);
      return filterList;
   }}
