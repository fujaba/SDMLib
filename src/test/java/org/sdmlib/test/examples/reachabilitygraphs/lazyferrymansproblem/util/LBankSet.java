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
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBank;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBoatSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBoat;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.CargoSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.Cargo;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LRiverSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LRiver;

public class LBankSet extends SimpleSet<LBank>
{
	public Class<?> getTypClass() {
		return LBank.class;
	}

   public LBankSet()
   {
      // empty
   }

   public LBankSet(LBank... objects)
   {
      for (LBank obj : objects)
      {
         this.add(obj);
      }
   }

   public LBankSet(Collection<LBank> objects)
   {
      this.addAll(objects);
   }

   public static final LBankSet EMPTY_SET = new LBankSet().withFlag(LBankSet.READONLY);


   public LBankPO createLBankPO()
   {
      return new LBankPO(this.toArray(new LBank[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBank";
   }


   @Override
   public LBankSet getNewList(boolean keyValue)
   {
      return new LBankSet();
   }


   public LBankSet filter(Condition<LBank> condition) {
      LBankSet filterList = new LBankSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public LBankSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<LBank>)value);
      }
      else if (value != null)
      {
         this.add((LBank) value);
      }
      
      return this;
   }
   
   public LBankSet without(LBank value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of LBank objects and collect a list of the age attribute values. 
    * 
    * @return List of int objects reachable via age attribute
    */
   public NumberList getAge()
   {
      NumberList result = new NumberList();
      
      for (LBank obj : this)
      {
         result.add(obj.getAge());
      }
      
      return result;
   }


   /**
    * Loop through the current set of LBank objects and collect those LBank objects where the age attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LBank objects that match the parameter
    */
   public LBankSet createAgeCondition(int value)
   {
      LBankSet result = new LBankSet();
      
      for (LBank obj : this)
      {
         if (value == obj.getAge())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LBank objects and collect those LBank objects where the age attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of LBank objects that match the parameter
    */
   public LBankSet createAgeCondition(int lower, int upper)
   {
      LBankSet result = new LBankSet();
      
      for (LBank obj : this)
      {
         if (lower <= obj.getAge() && obj.getAge() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LBank objects and assign value to the age attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of LBank objects now with new attribute values.
    */
   public LBankSet withAge(int value)
   {
      for (LBank obj : this)
      {
         obj.setAge(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of LBank objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public ObjectSet getName()
   {
      ObjectSet result = new ObjectSet();
      
      for (LBank obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of LBank objects and collect those LBank objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LBank objects that match the parameter
    */
   public LBankSet createNameCondition(String value)
   {
      LBankSet result = new LBankSet();
      
      for (LBank obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LBank objects and collect those LBank objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of LBank objects that match the parameter
    */
   public LBankSet createNameCondition(String lower, String upper)
   {
      LBankSet result = new LBankSet();
      
      for (LBank obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LBank objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of LBank objects now with new attribute values.
    */
   public LBankSet withName(String value)
   {
      for (LBank obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of LBank objects and collect a set of the LBoat objects reached via boat. 
    * 
    * @return Set of LBoat objects reachable via boat
    */
   public LBoatSet getBoat()
   {
      LBoatSet result = new LBoatSet();
      
      for (LBank obj : this)
      {
         result.with(obj.getBoat());
      }
      
      return result;
   }

   /**
    * Loop through the current set of LBank objects and collect all contained objects with reference boat pointing to the object passed as parameter. 
    * 
    * @param value The object required as boat neighbor of the collected results. 
    * 
    * @return Set of LBoat objects referring to value via boat
    */
   public LBankSet filterBoat(Object value)
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
      
      LBankSet answer = new LBankSet();
      
      for (LBank obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getBoat()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the LBank object passed as parameter to the Boat attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Boat attributes.
    */
   public LBankSet withBoat(LBoat value)
   {
      for (LBank obj : this)
      {
         obj.withBoat(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the LBank object passed as parameter from the Boat attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public LBankSet withoutBoat(LBoat value)
   {
      for (LBank obj : this)
      {
         obj.withoutBoat(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of LBank objects and collect a set of the Cargo objects reached via cargos. 
    * 
    * @return Set of Cargo objects reachable via cargos
    */
   public CargoSet getCargos()
   {
      CargoSet result = new CargoSet();
      
      for (LBank obj : this)
      {
         result.with(obj.getCargos());
      }
      
      return result;
   }

   /**
    * Loop through the current set of LBank objects and collect all contained objects with reference cargos pointing to the object passed as parameter. 
    * 
    * @param value The object required as cargos neighbor of the collected results. 
    * 
    * @return Set of Cargo objects referring to value via cargos
    */
   public LBankSet filterCargos(Object value)
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
      
      LBankSet answer = new LBankSet();
      
      for (LBank obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getCargos()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the LBank object passed as parameter to the Cargos attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Cargos attributes.
    */
   public LBankSet withCargos(Cargo value)
   {
      for (LBank obj : this)
      {
         obj.withCargos(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the LBank object passed as parameter from the Cargos attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public LBankSet withoutCargos(Cargo value)
   {
      for (LBank obj : this)
      {
         obj.withoutCargos(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of LBank objects and collect a set of the LRiver objects reached via river. 
    * 
    * @return Set of LRiver objects reachable via river
    */
   public LRiverSet getRiver()
   {
      LRiverSet result = new LRiverSet();
      
      for (LBank obj : this)
      {
         result.with(obj.getRiver());
      }
      
      return result;
   }

   /**
    * Loop through the current set of LBank objects and collect all contained objects with reference river pointing to the object passed as parameter. 
    * 
    * @param value The object required as river neighbor of the collected results. 
    * 
    * @return Set of LRiver objects referring to value via river
    */
   public LBankSet filterRiver(Object value)
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
      
      LBankSet answer = new LBankSet();
      
      for (LBank obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getRiver()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the LBank object passed as parameter to the River attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their River attributes.
    */
   public LBankSet withRiver(LRiver value)
   {
      for (LBank obj : this)
      {
         obj.withRiver(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the LBank object passed as parameter from the River attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public LBankSet withoutRiver(LRiver value)
   {
      for (LBank obj : this)
      {
         obj.withoutRiver(value);
      }
      
      return this;
   }

}
