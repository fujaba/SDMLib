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
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.Cargo;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBoatSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBoat;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBankSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBank;

public class CargoSet extends SimpleSet<Cargo>
{
	public Class<?> getTypClass() {
		return Cargo.class;
	}

   public CargoSet()
   {
      // empty
   }

   public CargoSet(Cargo... objects)
   {
      for (Cargo obj : objects)
      {
         this.add(obj);
      }
   }

   public CargoSet(Collection<Cargo> objects)
   {
      this.addAll(objects);
   }

   public static final CargoSet EMPTY_SET = new CargoSet().withFlag(CargoSet.READONLY);


   public CargoPO createCargoPO()
   {
      return new CargoPO(this.toArray(new Cargo[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.Cargo";
   }


   @Override
   public CargoSet getNewList(boolean keyValue)
   {
      return new CargoSet();
   }


   public CargoSet filter(Condition<Cargo> condition) {
      CargoSet filterList = new CargoSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public CargoSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Cargo>)value);
      }
      else if (value != null)
      {
         this.add((Cargo) value);
      }
      
      return this;
   }
   
   public CargoSet without(Cargo value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Cargo objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public ObjectSet getName()
   {
      ObjectSet result = new ObjectSet();
      
      for (Cargo obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Cargo objects and collect those Cargo objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Cargo objects that match the parameter
    */
   public CargoSet createNameCondition(String value)
   {
      CargoSet result = new CargoSet();
      
      for (Cargo obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Cargo objects and collect those Cargo objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Cargo objects that match the parameter
    */
   public CargoSet createNameCondition(String lower, String upper)
   {
      CargoSet result = new CargoSet();
      
      for (Cargo obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Cargo objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Cargo objects now with new attribute values.
    */
   public CargoSet withName(String value)
   {
      for (Cargo obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Cargo objects and collect a set of the LBoat objects reached via boat. 
    * 
    * @return Set of LBoat objects reachable via boat
    */
   public LBoatSet getBoat()
   {
      LBoatSet result = new LBoatSet();
      
      for (Cargo obj : this)
      {
         result.with(obj.getBoat());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Cargo objects and collect all contained objects with reference boat pointing to the object passed as parameter. 
    * 
    * @param value The object required as boat neighbor of the collected results. 
    * 
    * @return Set of LBoat objects referring to value via boat
    */
   public CargoSet filterBoat(Object value)
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
      
      CargoSet answer = new CargoSet();
      
      for (Cargo obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getBoat()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Cargo object passed as parameter to the Boat attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Boat attributes.
    */
   public CargoSet withBoat(LBoat value)
   {
      for (Cargo obj : this)
      {
         obj.withBoat(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Cargo object passed as parameter from the Boat attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public CargoSet withoutBoat(LBoat value)
   {
      for (Cargo obj : this)
      {
         obj.withoutBoat(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Cargo objects and collect a set of the LBank objects reached via bank. 
    * 
    * @return Set of LBank objects reachable via bank
    */
   public LBankSet getBank()
   {
      LBankSet result = new LBankSet();
      
      for (Cargo obj : this)
      {
         result.with(obj.getBank());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Cargo objects and collect all contained objects with reference bank pointing to the object passed as parameter. 
    * 
    * @param value The object required as bank neighbor of the collected results. 
    * 
    * @return Set of LBank objects referring to value via bank
    */
   public CargoSet filterBank(Object value)
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
      
      CargoSet answer = new CargoSet();
      
      for (Cargo obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getBank()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Cargo object passed as parameter to the Bank attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Bank attributes.
    */
   public CargoSet withBank(LBank value)
   {
      for (Cargo obj : this)
      {
         obj.withBank(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Cargo object passed as parameter from the Bank attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public CargoSet withoutBank(LBank value)
   {
      for (Cargo obj : this)
      {
         obj.withoutBank(value);
      }
      
      return this;
   }

}
