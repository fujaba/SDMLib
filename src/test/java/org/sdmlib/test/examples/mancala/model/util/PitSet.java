/*
   Copyright (c) 2014 NeTH 
   
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
   
package org.sdmlib.test.examples.mancala.model.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.test.examples.mancala.model.Mancala;
import org.sdmlib.test.examples.mancala.model.Pit;
import org.sdmlib.test.examples.mancala.model.Player;

import de.uniks.networkparser.list.SimpleSet;

public class PitSet extends SimpleSet<Pit>
{


   public PitPO hasPitPO()
   {
      return new PitPO(this.toArray(new Pit[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public PitSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Pit>)value);
      }
      else if (value != null)
      {
         this.add((Pit) value);
      }
      
      return this;
   }
   
   public PitSet without(Pit value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public PitSet moveStones()
   {
      for (Pit obj : this)
      {
         obj.moveStones();
      }
      return this;
   }

   public intList getNr()
   {
      intList result = new intList();
      
      for (Pit obj : this)
      {
         result.add(obj.getNr());
      }
      
      return result;
   }

   public PitSet hasNr(int value)
   {
      PitSet result = new PitSet();
      
      for (Pit obj : this)
      {
         if (value == obj.getNr())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PitSet hasNr(int lower, int upper)
   {
      PitSet result = new PitSet();
      
      for (Pit obj : this)
      {
         if (lower <= obj.getNr() && obj.getNr() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PitSet withNr(int value)
   {
      for (Pit obj : this)
      {
         obj.setNr(value);
      }
      
      return this;
   }

   public MancalaSet getGame()
   {
      MancalaSet result = new MancalaSet();
      
      for (Pit obj : this)
      {
         result.add(obj.getGame());
      }
      
      return result;
   }

   public PitSet hasGame(Object value)
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
      
      PitSet answer = new PitSet();
      
      for (Pit obj : this)
      {
         if (neighbors.contains(obj.getGame()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PitSet withGame(Mancala value)
   {
      for (Pit obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

   public PlayerSet getPlayer()
   {
      PlayerSet result = new PlayerSet();
      
      for (Pit obj : this)
      {
         result.add(obj.getPlayer());
      }
      
      return result;
   }

   public PitSet hasPlayer(Object value)
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
      
      PitSet answer = new PitSet();
      
      for (Pit obj : this)
      {
         if (neighbors.contains(obj.getPlayer()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PitSet withPlayer(Player value)
   {
      for (Pit obj : this)
      {
         obj.withPlayer(value);
      }
      
      return this;
   }

   public PitSet getNext()
   {
      PitSet result = new PitSet();
      
      for (Pit obj : this)
      {
         result.add(obj.getNext());
      }
      
      return result;
   }

   public PitSet hasNext(Object value)
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
      
      PitSet answer = new PitSet();
      
      for (Pit obj : this)
      {
         if (neighbors.contains(obj.getNext()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public PitSet getNextTransitive()
   {
      PitSet todo = new PitSet().with(this);
      
      PitSet result = new PitSet();
      
      while ( ! todo.isEmpty())
      {
         Pit current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getNext()))
            {
               todo.with(current.getNext());
            }
         }
      }
      
      return result;
   }

   public PitSet withNext(Pit value)
   {
      for (Pit obj : this)
      {
         obj.withNext(value);
      }
      
      return this;
   }

   public PitSet getPrevious()
   {
      PitSet result = new PitSet();
      
      for (Pit obj : this)
      {
         result.add(obj.getPrevious());
      }
      
      return result;
   }

   public PitSet hasPrevious(Object value)
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
      
      PitSet answer = new PitSet();
      
      for (Pit obj : this)
      {
         if (neighbors.contains(obj.getPrevious()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public PitSet getPreviousTransitive()
   {
      PitSet todo = new PitSet().with(this);
      
      PitSet result = new PitSet();
      
      while ( ! todo.isEmpty())
      {
         Pit current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getPrevious()))
            {
               todo.with(current.getPrevious());
            }
         }
      }
      
      return result;
   }

   public PitSet withPrevious(Pit value)
   {
      for (Pit obj : this)
      {
         obj.withPrevious(value);
      }
      
      return this;
   }

   public PitSet getCounterpart()
   {
      PitSet result = new PitSet();
      
      for (Pit obj : this)
      {
         result.add(obj.getCounterpart());
      }
      
      return result;
   }

   public PitSet hasCounterpart(Object value)
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
      
      PitSet answer = new PitSet();
      
      for (Pit obj : this)
      {
         if (neighbors.contains(obj.getCounterpart()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public PitSet getCounterpartTransitive()
   {
      PitSet todo = new PitSet().with(this);
      
      PitSet result = new PitSet();
      
      while ( ! todo.isEmpty())
      {
         Pit current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getCounterpart()))
            {
               todo.with(current.getCounterpart());
            }
         }
      }
      
      return result;
   }

   public PitSet withCounterpart(Pit value)
   {
      for (Pit obj : this)
      {
         obj.withCounterpart(value);
      }
      
      return this;
   }


   public static final PitSet EMPTY_SET = new PitSet().withFlag(PitSet.READONLY);


   public PitPO filterPitPO()
   {
      return new PitPO(this.toArray(new Pit[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.mancala.model.Pit";
   }

   /**
    * Loop through the current set of Pit objects and collect those Pit objects where the nr attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Pit objects that match the parameter
    */
   public PitSet filterNr(int value)
   {
      PitSet result = new PitSet();
      
      for (Pit obj : this)
      {
         if (value == obj.getNr())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Pit objects and collect those Pit objects where the nr attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Pit objects that match the parameter
    */
   public PitSet filterNr(int lower, int upper)
   {
      PitSet result = new PitSet();
      
      for (Pit obj : this)
      {
         if (lower <= obj.getNr() && obj.getNr() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
