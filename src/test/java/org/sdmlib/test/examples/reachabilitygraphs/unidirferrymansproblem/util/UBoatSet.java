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
   
package org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UBoat;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UBankSet;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UBank;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UCargoSet;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UCargo;

public class UBoatSet extends SimpleSet<UBoat>
{
	public Class<?> getTypClass() {
		return UBoat.class;
	}

   public UBoatSet()
   {
      // empty
   }

   public UBoatSet(UBoat... objects)
   {
      for (UBoat obj : objects)
      {
         this.add(obj);
      }
   }

   public UBoatSet(Collection<UBoat> objects)
   {
      this.addAll(objects);
   }

   public static final UBoatSet EMPTY_SET = new UBoatSet().withFlag(UBoatSet.READONLY);


   public UBoatPO createUBoatPO()
   {
      return new UBoatPO(this.toArray(new UBoat[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UBoat";
   }


   @Override
   public UBoatSet getNewList(boolean keyValue)
   {
      return new UBoatSet();
   }


   @SuppressWarnings("unchecked")
   public UBoatSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<UBoat>)value);
      }
      else if (value != null)
      {
         this.add((UBoat) value);
      }
      
      return this;
   }
   
   public UBoatSet without(UBoat value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of UBoat objects and collect a set of the UBank objects reached via bank. 
    * 
    * @return Set of UBank objects reachable via bank
    */
   public UBankSet getBank()
   {
      UBankSet result = new UBankSet();
      
      for (UBoat obj : this)
      {
         result.with(obj.getBank());
      }
      
      return result;
   }

   /**
    * Loop through the current set of UBoat objects and collect all contained objects with reference bank pointing to the object passed as parameter. 
    * 
    * @param value The object required as bank neighbor of the collected results. 
    * 
    * @return Set of UBank objects referring to value via bank
    */
   public UBoatSet filterBank(Object value)
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
      
      UBoatSet answer = new UBoatSet();
      
      for (UBoat obj : this)
      {
         if (neighbors.contains(obj.getBank()) || (neighbors.isEmpty() && obj.getBank() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the UBoat object passed as parameter to the Bank attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Bank attributes.
    */
   public UBoatSet withBank(UBank value)
   {
      for (UBoat obj : this)
      {
         obj.withBank(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of UBoat objects and collect a set of the UCargo objects reached via cargo. 
    * 
    * @return Set of UCargo objects reachable via cargo
    */
   public UCargoSet getCargo()
   {
      UCargoSet result = new UCargoSet();
      
      for (UBoat obj : this)
      {
         result.with(obj.getCargo());
      }
      
      return result;
   }

   /**
    * Loop through the current set of UBoat objects and collect all contained objects with reference cargo pointing to the object passed as parameter. 
    * 
    * @param value The object required as cargo neighbor of the collected results. 
    * 
    * @return Set of UCargo objects referring to value via cargo
    */
   public UBoatSet filterCargo(Object value)
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
      
      UBoatSet answer = new UBoatSet();
      
      for (UBoat obj : this)
      {
         if (neighbors.contains(obj.getCargo()) || (neighbors.isEmpty() && obj.getCargo() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the UBoat object passed as parameter to the Cargo attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Cargo attributes.
    */
   public UBoatSet withCargo(UCargo value)
   {
      for (UBoat obj : this)
      {
         obj.withCargo(value);
      }
      
      return this;
   }

}
