/*
   Copyright (c) 2013 zuendorf 
   
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
   
package org.sdmlib.models.patterns.example.ferrmansproblem.creators;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.patterns.example.ferrmansproblem.Bank;
import org.sdmlib.models.patterns.example.ferrmansproblem.Boat;
import org.sdmlib.models.patterns.example.ferrmansproblem.Cargo;
import org.sdmlib.models.patterns.example.ferrmansproblem.River;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.RiverSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BankSet;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.CargoSet;

public class BoatSet extends LinkedHashSet<Boat> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Boat elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.patterns.example.ferrmansproblem.Boat";
   }


   public BoatSet with(Boat value)
   {
      this.add(value);
      return this;
   }
   
   public BoatSet without(Boat value)
   {
      this.remove(value);
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

   public BoatSet withCargo(Cargo value)
   {
      for (Boat obj : this)
      {
         obj.withCargo(value);
      }
      
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

   public BoatSet withRiver(River value)
   {
      for (Boat obj : this)
      {
         obj.withRiver(value);
      }
      
      return this;
   }



   public BoatPO startModelPattern()
   {
      org.sdmlib.models.patterns.example.ferrmansproblem.creators.ModelPattern pattern = new org.sdmlib.models.patterns.example.ferrmansproblem.creators.ModelPattern();
      
      BoatPO patternObject = pattern.hasElementBoatPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


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



   public BoatPO hasBoatPO()
   {
      org.sdmlib.models.patterns.example.ferrmansproblem.creators.ModelPattern pattern = new org.sdmlib.models.patterns.example.ferrmansproblem.creators.ModelPattern();
      
      BoatPO patternObject = pattern.hasElementBoatPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}





