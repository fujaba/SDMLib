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
   
package org.sdmlib.test.examples.ludo.model.util;

import java.util.Collection;

import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.test.examples.ludo.model.Dice;
import org.sdmlib.test.examples.ludo.model.Ludo;
import org.sdmlib.test.examples.ludo.model.Player;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.ludo.model.util.LudoSet;
import org.sdmlib.test.examples.ludo.model.util.PlayerSet;
import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.NumberList;

public class DiceSet extends SimpleSet<Dice>
{


   public DicePO hasDicePO()
   {
      return new DicePO(this.toArray(new Dice[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public DiceSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Dice>)value);
      }
      else if (value != null)
      {
         this.add((Dice) value);
      }
      
      return this;
   }
   
   public DiceSet without(Dice value)
   {
      this.remove(value);
      return this;
   }

   public intList getValue()
   {
      intList result = new intList();
      
      for (Dice obj : this)
      {
         result.add(obj.getValue());
      }
      
      return result;
   }

   public DiceSet hasValue(int value)
   {
      DiceSet result = new DiceSet();
      
      for (Dice obj : this)
      {
         if (value == obj.getValue())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public DiceSet withValue(int value)
   {
      for (Dice obj : this)
      {
         obj.setValue(value);
      }
      
      return this;
   }

   public LudoSet getGame()
   {
      LudoSet result = new LudoSet();
      
      for (Dice obj : this)
      {
         result.add(obj.getGame());
      }
      
      return result;
   }

   public DiceSet hasGame(Object value)
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
      
      DiceSet answer = new DiceSet();
      
      for (Dice obj : this)
      {
         if (neighbors.contains(obj.getGame()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public DiceSet withGame(Ludo value)
   {
      for (Dice obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

   public PlayerSet getPlayer()
   {
      PlayerSet result = new PlayerSet();
      
      for (Dice obj : this)
      {
         result.add(obj.getPlayer());
      }
      
      return result;
   }

   public DiceSet hasPlayer(Object value)
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
      
      DiceSet answer = new DiceSet();
      
      for (Dice obj : this)
      {
         if (neighbors.contains(obj.getPlayer()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public DiceSet withPlayer(Player value)
   {
      for (Dice obj : this)
      {
         obj.withPlayer(value);
      }
      
      return this;
   }


   public static final DiceSet EMPTY_SET = new DiceSet().withFlag(DiceSet.READONLY);
   public DiceSet hasValue(int lower, int upper)
   {
      DiceSet result = new DiceSet();
      
      for (Dice obj : this)
      {
         if (lower <= obj.getValue() && obj.getValue() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public DicePO filterDicePO()
   {
      return new DicePO(this.toArray(new Dice[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.ludo.model.Dice";
   }

   /**
    * Loop through the current set of Dice objects and collect those Dice objects where the value attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Dice objects that match the parameter
    */
   public DiceSet filterValue(int value)
   {
      DiceSet result = new DiceSet();
      
      for (Dice obj : this)
      {
         if (value == obj.getValue())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Dice objects and collect those Dice objects where the value attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Dice objects that match the parameter
    */
   public DiceSet filterValue(int lower, int upper)
   {
      DiceSet result = new DiceSet();
      
      for (Dice obj : this)
      {
         if (lower <= obj.getValue() && obj.getValue() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public DiceSet()
   {
      // empty
   }

   public DiceSet(Dice... objects)
   {
      for (Dice obj : objects)
      {
         this.add(obj);
      }
   }

   public DiceSet(Collection<Dice> objects)
   {
      this.addAll(objects);
   }


   public DicePO createDicePO()
   {
      return new DicePO(this.toArray(new Dice[this.size()]));
   }


   @Override
   public DiceSet getNewList(boolean keyValue)
   {
      return new DiceSet();
   }


   public DiceSet filter(Condition<Dice> condition) {
      DiceSet filterList = new DiceSet();
      filterItems(filterList, condition);
      return filterList;
   }}
