/*
   Copyright (c) 2017 zuendorf
   
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
   
package org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBoat;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBankSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBank;
import java.util.Collections;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LRiverSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LRiver;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.CargoSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.Cargo;

public class LBoatSet extends SimpleSet<LBoat>
{
	public Class<?> getTypClass() {
		return LBoat.class;
	}

   public LBoatSet()
   {
      // empty
   }

   public LBoatSet(LBoat... objects)
   {
      for (LBoat obj : objects)
      {
         this.add(obj);
      }
   }

   public LBoatSet(Collection<LBoat> objects)
   {
      this.addAll(objects);
   }

   public static final LBoatSet EMPTY_SET = new LBoatSet().withFlag(LBoatSet.READONLY);


   public LBoatPO createLBoatPO()
   {
      return new LBoatPO(this.toArray(new LBoat[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBoat";
   }


   @Override
   public LBoatSet getNewList(boolean keyValue)
   {
      return new LBoatSet();
   }


   public LBoatSet filter(Condition<LBoat> condition) {
      LBoatSet filterList = new LBoatSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public LBoatSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<LBoat>)value);
      }
      else if (value != null)
      {
         this.add((LBoat) value);
      }
      
      return this;
   }
   
   public LBoatSet without(LBoat value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of LBoat objects and collect a set of the LBank objects reached via bank. 
    * 
    * @return Set of LBank objects reachable via bank
    */
   public LBankSet getBank()
   {
      LBankSet result = new LBankSet();
      
      for (LBoat obj : this)
      {
         result.with(obj.getBank());
      }
      
      return result;
   }

   /**
    * Loop through the current set of LBoat objects and collect all contained objects with reference bank pointing to the object passed as parameter. 
    * 
    * @param value The object required as bank neighbor of the collected results. 
    * 
    * @return Set of LBank objects referring to value via bank
    */
   public LBoatSet filterBank(Object value)
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
      
      LBoatSet answer = new LBoatSet();
      
      for (LBoat obj : this)
      {
         if (neighbors.contains(obj.getBank()) || (neighbors.isEmpty() && obj.getBank() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the LBoat object passed as parameter to the Bank attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Bank attributes.
    */
   public LBoatSet withBank(LBank value)
   {
      for (LBoat obj : this)
      {
         obj.withBank(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of LBoat objects and collect a set of the LRiver objects reached via river. 
    * 
    * @return Set of LRiver objects reachable via river
    */
   public LRiverSet getRiver()
   {
      LRiverSet result = new LRiverSet();
      
      for (LBoat obj : this)
      {
         result.with(obj.getRiver());
      }
      
      return result;
   }

   /**
    * Loop through the current set of LBoat objects and collect all contained objects with reference river pointing to the object passed as parameter. 
    * 
    * @param value The object required as river neighbor of the collected results. 
    * 
    * @return Set of LRiver objects referring to value via river
    */
   public LBoatSet filterRiver(Object value)
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
      
      LBoatSet answer = new LBoatSet();
      
      for (LBoat obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getRiver()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the LBoat object passed as parameter to the River attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their River attributes.
    */
   public LBoatSet withRiver(LRiver value)
   {
      for (LBoat obj : this)
      {
         obj.withRiver(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the LBoat object passed as parameter from the River attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public LBoatSet withoutRiver(LRiver value)
   {
      for (LBoat obj : this)
      {
         obj.withoutRiver(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of LBoat objects and collect a set of the Cargo objects reached via cargo. 
    * 
    * @return Set of Cargo objects reachable via cargo
    */
   public CargoSet getCargo()
   {
      CargoSet result = new CargoSet();
      
      for (LBoat obj : this)
      {
         result.with(obj.getCargo());
      }
      
      return result;
   }

   /**
    * Loop through the current set of LBoat objects and collect all contained objects with reference cargo pointing to the object passed as parameter. 
    * 
    * @param value The object required as cargo neighbor of the collected results. 
    * 
    * @return Set of Cargo objects referring to value via cargo
    */
   public LBoatSet filterCargo(Object value)
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
      
      LBoatSet answer = new LBoatSet();
      
      for (LBoat obj : this)
      {
         if (neighbors.contains(obj.getCargo()) || (neighbors.isEmpty() && obj.getCargo() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the LBoat object passed as parameter to the Cargo attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Cargo attributes.
    */
   public LBoatSet withCargo(Cargo value)
   {
      for (LBoat obj : this)
      {
         obj.withCargo(value);
      }
      
      return this;
   }

}
