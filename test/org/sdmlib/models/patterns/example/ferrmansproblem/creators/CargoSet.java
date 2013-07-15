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

import java.util.LinkedHashSet;
import org.sdmlib.models.patterns.example.ferrmansproblem.Cargo;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BankSet;
import org.sdmlib.models.patterns.example.ferrmansproblem.Bank;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BoatSet;
import org.sdmlib.models.patterns.example.ferrmansproblem.Boat;

public class CargoSet extends LinkedHashSet<Cargo> implements org.sdmlib.models.modelsets.ModelSet
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


   public CargoSet with(Cargo value)
   {
      this.add(value);
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

}

