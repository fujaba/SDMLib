/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util;

import java.util.Collection;

import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Bank;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Boat;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Cargo;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.River;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class BoatSet extends SimpleSet<Boat>
{


   public BoatPO hasBoatPO()
   {
      return new BoatPO(this.toArray(new Boat[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public BoatSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Boat>)value);
      }
      else if (value != null)
      {
         this.add((Boat) value);
      }
      
      return this;
   }
   
   public BoatSet without(Boat value)
   {
      this.remove(value);
      return this;
   }

   public RiverSet getRiver()
   {
      RiverSet result = new RiverSet();
      
      for (Boat obj : this)
      {
         result.add(obj.getRiver());
      }
      
      return result;
   }

   public BoatSet hasRiver(Object value)
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
      
      BoatSet answer = new BoatSet();
      
      for (Boat obj : this)
      {
         if (neighbors.contains(obj.getRiver()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public BoatSet withRiver(River value)
   {
      for (Boat obj : this)
      {
         obj.withRiver(value);
      }
      
      return this;
   }

   public BankSet getBank()
   {
      BankSet result = new BankSet();
      
      for (Boat obj : this)
      {
         result.add(obj.getBank());
      }
      
      return result;
   }

   public BoatSet hasBank(Object value)
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
      
      BoatSet answer = new BoatSet();
      
      for (Boat obj : this)
      {
         if (neighbors.contains(obj.getBank()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public BoatSet withBank(Bank value)
   {
      for (Boat obj : this)
      {
         obj.withBank(value);
      }
      
      return this;
   }

   public CargoSet getCargo()
   {
      CargoSet result = new CargoSet();
      
      for (Boat obj : this)
      {
         result.add(obj.getCargo());
      }
      
      return result;
   }

   public BoatSet hasCargo(Object value)
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
      
      BoatSet answer = new BoatSet();
      
      for (Boat obj : this)
      {
         if (neighbors.contains(obj.getCargo()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public BoatSet withCargo(Cargo value)
   {
      for (Boat obj : this)
      {
         obj.withCargo(value);
      }
      
      return this;
   }


   public static final BoatSet EMPTY_SET = new BoatSet().withFlag(BoatSet.READONLY);


   public BoatPO filterBoatPO()
   {
      return new BoatPO(this.toArray(new Boat[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Boat";
   }

   public BoatSet()
   {
      // empty
   }

   public BoatSet(Boat... objects)
   {
      for (Boat obj : objects)
      {
         this.add(obj);
      }
   }

   public BoatSet(Collection<Boat> objects)
   {
      this.addAll(objects);
   }


   public BoatPO createBoatPO()
   {
      return new BoatPO(this.toArray(new Boat[this.size()]));
   }


   @Override
   public BoatSet getNewList(boolean keyValue)
   {
      return new BoatSet();
   }
}
