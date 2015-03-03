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
   
package org.sdmlib.examples.ludo.model.util;

import java.util.Collection;

import org.sdmlib.examples.ludo.model.Dice;
import org.sdmlib.examples.ludo.model.Ludo;
import org.sdmlib.examples.ludo.model.Player;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.intList;

public class DiceSet extends SDMSet<Dice>
{


   public DicePO hasDicePO()
   {
      return new DicePO(this.toArray(new Dice[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.ludo.model.Dice";
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


   public static final DiceSet EMPTY_SET = new DiceSet().withReadOnly(true);
}
