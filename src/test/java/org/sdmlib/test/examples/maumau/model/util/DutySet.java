/*
   Copyright (c) 2015 Stefan
   
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
   
package org.sdmlib.test.examples.maumau.model.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.intList;
import org.sdmlib.test.examples.maumau.model.Duty;
import org.sdmlib.test.examples.maumau.model.DutyType;
import org.sdmlib.test.examples.maumau.model.Player;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.NumberList;
import org.sdmlib.test.examples.maumau.model.util.PlayerSet;

public class DutySet extends SimpleSet<Duty>
{

   public static final DutySet EMPTY_SET = new DutySet().withFlag(DutySet.READONLY);


   public DutyPO hasDutyPO()
   {
      return new DutyPO(this.toArray(new Duty[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.maumau.model.Duty";
   }


   @SuppressWarnings("unchecked")
   public DutySet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Duty>)value);
      }
      else if (value != null)
      {
         this.add((Duty) value);
      }
      
      return this;
   }
   
   public DutySet without(Duty value)
   {
      this.remove(value);
      return this;
   }

   public DutyTypeSet getType()
   {
      DutyTypeSet result = new DutyTypeSet();
      
      for (Duty obj : this)
      {
         result.add(obj.getType());
      }
      
      return result;
   }

   public DutySet hasType(DutyType value)
   {
      DutySet result = new DutySet();
      
      for (Duty obj : this)
      {
         if (value == obj.getType())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public DutySet withType(DutyType value)
   {
      for (Duty obj : this)
      {
         obj.setType(value);
      }
      
      return this;
   }

   public intList getNumber()
   {
      intList result = new intList();
      
      for (Duty obj : this)
      {
         result.add(obj.getNumber());
      }
      
      return result;
   }

   public DutySet hasNumber(int value)
   {
      DutySet result = new DutySet();
      
      for (Duty obj : this)
      {
         if (value == obj.getNumber())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public DutySet hasNumber(int lower, int upper)
   {
      DutySet result = new DutySet();
      
      for (Duty obj : this)
      {
         if (lower <= obj.getNumber() && obj.getNumber() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public DutySet withNumber(int value)
   {
      for (Duty obj : this)
      {
         obj.setNumber(value);
      }
      
      return this;
   }

   public PlayerSet getPlayer()
   {
      PlayerSet result = new PlayerSet();
      
      for (Duty obj : this)
      {
         result.add(obj.getPlayer());
      }
      
      return result;
   }

   public DutySet hasPlayer(Object value)
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
      
      DutySet answer = new DutySet();
      
      for (Duty obj : this)
      {
         if (neighbors.contains(obj.getPlayer()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public DutySet withPlayer(Player value)
   {
      for (Duty obj : this)
      {
         obj.withPlayer(value);
      }
      
      return this;
   }



   public DutyPO filterDutyPO()
   {
      return new DutyPO(this.toArray(new Duty[this.size()]));
   }

   /**
    * Loop through the current set of Duty objects and collect those Duty objects where the type attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Duty objects that match the parameter
    */
   public DutySet filterType(DutyType value)
   {
      DutySet result = new DutySet();
      
      for (Duty obj : this)
      {
         if (value == obj.getType())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Duty objects and collect those Duty objects where the number attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Duty objects that match the parameter
    */
   public DutySet filterNumber(int value)
   {
      DutySet result = new DutySet();
      
      for (Duty obj : this)
      {
         if (value == obj.getNumber())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Duty objects and collect those Duty objects where the number attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Duty objects that match the parameter
    */
   public DutySet filterNumber(int lower, int upper)
   {
      DutySet result = new DutySet();
      
      for (Duty obj : this)
      {
         if (lower <= obj.getNumber() && obj.getNumber() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public DutySet()
   {
      // empty
   }

   public DutySet(Duty... objects)
   {
      for (Duty obj : objects)
      {
         this.add(obj);
      }
   }

   public DutySet(Collection<Duty> objects)
   {
      this.addAll(objects);
   }


   public DutyPO createDutyPO()
   {
      return new DutyPO(this.toArray(new Duty[this.size()]));
   }


   @Override
   public DutySet getNewList(boolean keyValue)
   {
      return new DutySet();
   }


   public DutySet filter(Condition<Duty> condition) {
      DutySet filterList = new DutySet();
      filterItems(filterList, condition);
      return filterList;
   }
   /**
    * Loop through the current set of Duty objects and collect those Duty objects where the number attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Duty objects that match the parameter
    */
   public DutySet createNumberCondition(int value)
   {
      DutySet result = new DutySet();
      
      for (Duty obj : this)
      {
         if (value == obj.getNumber())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Duty objects and collect those Duty objects where the number attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Duty objects that match the parameter
    */
   public DutySet createNumberCondition(int lower, int upper)
   {
      DutySet result = new DutySet();
      
      for (Duty obj : this)
      {
         if (lower <= obj.getNumber() && obj.getNumber() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Duty objects and collect those Duty objects where the type attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Duty objects that match the parameter
    */
   public DutySet createTypeCondition(DutyType value)
   {
      DutySet result = new DutySet();
      
      for (Duty obj : this)
      {
         if (value == obj.getType())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
