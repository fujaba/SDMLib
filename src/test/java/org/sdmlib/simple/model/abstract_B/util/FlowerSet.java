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
   
package org.sdmlib.simple.model.abstract_B.util;

import java.util.Collection;

import org.sdmlib.simple.model.abstract_B.Flower;
import org.sdmlib.simple.model.abstract_B.Human;

import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class FlowerSet extends SimpleSet<Flower>
{
	protected Class<?> getTypClass() {
		return Flower.class;
	}

   public FlowerSet()
   {
      // empty
   }

   public FlowerSet(Flower... objects)
   {
      for (Flower obj : objects)
      {
         this.add(obj);
      }
   }

   public FlowerSet(Collection<Flower> objects)
   {
      this.addAll(objects);
   }

   public static final FlowerSet EMPTY_SET = new FlowerSet().withFlag(FlowerSet.READONLY);


   public FlowerPO createFlowerPO()
   {
      return new FlowerPO(this.toArray(new Flower[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.abstract_B.Flower";
   }


   @Override
   public FlowerSet getNewList(boolean keyValue)
   {
      return new FlowerSet();
   }


   public FlowerSet filter(Condition<Flower> condition) {
      FlowerSet filterList = new FlowerSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public FlowerSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Flower>)value);
      }
      else if (value != null)
      {
         this.add((Flower) value);
      }
      
      return this;
   }
   
   public FlowerSet without(Flower value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of Flower objects and collect a set of the Human objects reached via owner. 
    * 
    * @return Set of Human objects reachable via owner
    */
   public HumanSet getOwner()
   {
      HumanSet result = new HumanSet();
      
      for (Flower obj : this)
      {
         result.with(obj.getOwner());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Flower objects and collect all contained objects with reference owner pointing to the object passed as parameter. 
    * 
    * @param value The object required as owner neighbor of the collected results. 
    * 
    * @return Set of Human objects referring to value via owner
    */
   public FlowerSet filterOwner(Object value)
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
      
      FlowerSet answer = new FlowerSet();
      
      for (Flower obj : this)
      {
         if (neighbors.contains(obj.getOwner()) || (neighbors.isEmpty() && obj.getOwner() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Flower object passed as parameter to the Owner attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Owner attributes.
    */
   public FlowerSet withOwner(Human value)
   {
      for (Flower obj : this)
      {
         obj.withOwner(value);
      }
      
      return this;
   }

}
