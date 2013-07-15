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
import org.sdmlib.models.patterns.example.ferrmansproblem.Bank;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BoatSet;
import org.sdmlib.models.patterns.example.ferrmansproblem.Boat;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.RiverSet;
import org.sdmlib.models.patterns.example.ferrmansproblem.River;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.CargoSet;
import org.sdmlib.models.patterns.example.ferrmansproblem.Cargo;

public class BankSet extends LinkedHashSet<Bank> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Bank elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.patterns.example.ferrmansproblem.Bank";
   }


   public BankSet with(Bank value)
   {
      this.add(value);
      return this;
   }
   
   public BankSet without(Bank value)
   {
      this.remove(value);
      return this;
   }
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Bank obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public BankSet withName(String value)
   {
      for (Bank obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public intList getAge()
   {
      intList result = new intList();
      
      for (Bank obj : this)
      {
         result.add(obj.getAge());
      }
      
      return result;
   }

   public BankSet withAge(int value)
   {
      for (Bank obj : this)
      {
         obj.setAge(value);
      }
      
      return this;
   }

   public BoatSet getBoat()
   {
      BoatSet result = new BoatSet();
      
      for (Bank obj : this)
      {
         result.add(obj.getBoat());
      }
      
      return result;
   }

   public BankSet withBoat(Boat value)
   {
      for (Bank obj : this)
      {
         obj.withBoat(value);
      }
      
      return this;
   }

   public RiverSet getRiver()
   {
      RiverSet result = new RiverSet();
      
      for (Bank obj : this)
      {
         result.add(obj.getRiver());
      }
      
      return result;
   }

   public BankSet withRiver(River value)
   {
      for (Bank obj : this)
      {
         obj.withRiver(value);
      }
      
      return this;
   }

   public CargoSet getCargos()
   {
      CargoSet result = new CargoSet();
      
      for (Bank obj : this)
      {
         result.addAll(obj.getCargos());
      }
      
      return result;
   }

   public BankSet withCargos(Cargo value)
   {
      for (Bank obj : this)
      {
         obj.withCargos(value);
      }
      
      return this;
   }

   public BankSet withoutCargos(Cargo value)
   {
      for (Bank obj : this)
      {
         obj.withoutCargos(value);
      }
      
      return this;
   }

}

