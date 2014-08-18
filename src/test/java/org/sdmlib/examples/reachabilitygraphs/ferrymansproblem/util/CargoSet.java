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
   
package org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.Cargo;
import java.util.Collection;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.util.BankSet;
import org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.Bank;
import org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.util.BoatSet;
import org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.Boat;

public class CargoSet extends SDMSet<Cargo>
{


   public CargoPO hasCargoPO()
   {
      return new CargoPO(this.toArray(new Cargo[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.Cargo";
   }


   @SuppressWarnings("unchecked")
   public CargoSet with(Object value)
   {
      if (value instanceof java.util.Collection)
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

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Cargo obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public CargoSet hasName(String value)
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

   public CargoSet hasName(String lower, String upper)
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

   public CargoSet withName(String value)
   {
      for (Cargo obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public BankSet getBank()
   {
      BankSet result = new BankSet();
      
      for (Cargo obj : this)
      {
         result.add(obj.getBank());
      }
      
      return result;
   }

   public CargoSet hasBank(Object value)
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
         if (neighbors.contains(obj.getBank()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public CargoSet withBank(Bank value)
   {
      for (Cargo obj : this)
      {
         obj.withBank(value);
      }
      
      return this;
   }

   public BoatSet getBoat()
   {
      BoatSet result = new BoatSet();
      
      for (Cargo obj : this)
      {
         result.add(obj.getBoat());
      }
      
      return result;
   }

   public CargoSet hasBoat(Object value)
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
         if (neighbors.contains(obj.getBoat()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public CargoSet withBoat(Boat value)
   {
      for (Cargo obj : this)
      {
         obj.withBoat(value);
      }
      
      return this;
   }

}
