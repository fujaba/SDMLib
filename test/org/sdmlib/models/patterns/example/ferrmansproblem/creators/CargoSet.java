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
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BankSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BoatSet;

public class CargoSet extends LinkedHashSet<Cargo> implements
      org.sdmlib.models.modelsets.ModelSet
{

   public String toString()
   {
      StringList stringList = new StringList();

      for (Cargo elem : this)
      {
         stringList.add(elem.toString());
      }

      return "(" + stringList.concat(", ") + ")";
   }

   public String getEntryType()
   {
      return "org.sdmlib.models.patterns.example.ferrmansproblem.Cargo";
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

   public CargoSet withBoat(Boat value)
   {
      for (Cargo obj : this)
      {
         obj.withBoat(value);
      }

      return this;
   }

   public CargoPO startModelPattern()
   {
      org.sdmlib.models.patterns.example.ferrmansproblem.creators.ModelPattern pattern = new org.sdmlib.models.patterns.example.ferrmansproblem.creators.ModelPattern();

      CargoPO patternObject = pattern.hasElementCargoPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }

   public CargoSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Cargo>) value);
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

   public CargoPO hasCargoPO()
   {
      org.sdmlib.models.patterns.example.ferrmansproblem.creators.ModelPattern pattern = new org.sdmlib.models.patterns.example.ferrmansproblem.creators.ModelPattern();

      CargoPO patternObject = pattern.hasElementCargoPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }
}




