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
import java.util.Collections;

import org.sdmlib.models.modelsets.intList;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Bank;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Boat;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Cargo;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.River;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;

public class BankSet extends SimpleSet<Bank>
{


   public BankPO hasBankPO()
   {
      return new BankPO(this.toArray(new Bank[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public BankSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Bank>)value);
      }
      else if (value != null)
      {
         this.add((Bank) value);
      }
      
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

   public BankSet hasName(String value)
   {
      BankSet result = new BankSet();
      
      for (Bank obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public BankSet hasName(String lower, String upper)
   {
      BankSet result = new BankSet();
      
      for (Bank obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
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

   public BankSet hasAge(int value)
   {
      BankSet result = new BankSet();
      
      for (Bank obj : this)
      {
         if (value == obj.getAge())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public BankSet hasAge(int lower, int upper)
   {
      BankSet result = new BankSet();
      
      for (Bank obj : this)
      {
         if (lower <= obj.getAge() && obj.getAge() <= upper)
         {
            result.add(obj);
         }
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

   public BankSet hasBoat(Object value)
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
      
      BankSet answer = new BankSet();
      
      for (Bank obj : this)
      {
         if (neighbors.contains(obj.getBoat()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
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

   public BankSet hasRiver(Object value)
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
      
      BankSet answer = new BankSet();
      
      for (Bank obj : this)
      {
         if (neighbors.contains(obj.getRiver()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
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

   public BankSet hasCargos(Object value)
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
      
      BankSet answer = new BankSet();
      
      for (Bank obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getCargos()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
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


   public static final BankSet EMPTY_SET = new BankSet().withFlag(BankSet.READONLY);


   public BankPO filterBankPO()
   {
      return new BankPO(this.toArray(new Bank[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Bank";
   }

   /**
    * Loop through the current set of Bank objects and collect those Bank objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Bank objects that match the parameter
    */
   public BankSet filterName(String value)
   {
      BankSet result = new BankSet();
      
      for (Bank obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Bank objects and collect those Bank objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Bank objects that match the parameter
    */
   public BankSet filterName(String lower, String upper)
   {
      BankSet result = new BankSet();
      
      for (Bank obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Bank objects and collect those Bank objects where the age attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Bank objects that match the parameter
    */
   public BankSet filterAge(int value)
   {
      BankSet result = new BankSet();
      
      for (Bank obj : this)
      {
         if (value == obj.getAge())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Bank objects and collect those Bank objects where the age attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Bank objects that match the parameter
    */
   public BankSet filterAge(int lower, int upper)
   {
      BankSet result = new BankSet();
      
      for (Bank obj : this)
      {
         if (lower <= obj.getAge() && obj.getAge() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public BankSet()
   {
      // empty
   }

   public BankSet(Bank... objects)
   {
      for (Bank obj : objects)
      {
         this.add(obj);
      }
   }

   public BankSet(Collection<Bank> objects)
   {
      this.addAll(objects);
   }


   public BankPO createBankPO()
   {
      return new BankPO(this.toArray(new Bank[this.size()]));
   }


   @Override
   public BankSet getNewList(boolean keyValue)
   {
      return new BankSet();
   }

   /**
    * Loop through the current set of Bank objects and collect those Bank objects where the age attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Bank objects that match the parameter
    */
   public BankSet createAgeCondition(int value)
   {
      BankSet result = new BankSet();
      
      for (Bank obj : this)
      {
         if (value == obj.getAge())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Bank objects and collect those Bank objects where the age attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Bank objects that match the parameter
    */
   public BankSet createAgeCondition(int lower, int upper)
   {
      BankSet result = new BankSet();
      
      for (Bank obj : this)
      {
         if (lower <= obj.getAge() && obj.getAge() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Bank objects and collect those Bank objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Bank objects that match the parameter
    */
   public BankSet createNameCondition(String value)
   {
      BankSet result = new BankSet();
      
      for (Bank obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Bank objects and collect those Bank objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Bank objects that match the parameter
    */
   public BankSet createNameCondition(String lower, String upper)
   {
      BankSet result = new BankSet();
      
      for (Bank obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
