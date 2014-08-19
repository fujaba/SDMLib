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
   
package org.sdmlib.examples.mancala.model.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.mancala.model.Kalah;
import java.util.Collection;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.examples.mancala.model.util.MancalaSet;
import org.sdmlib.examples.mancala.model.Mancala;
import org.sdmlib.examples.mancala.model.util.PlayerSet;
import org.sdmlib.examples.mancala.model.Player;
import org.sdmlib.examples.mancala.model.util.PitSet;
import org.sdmlib.examples.mancala.model.Pit;

public class KalahSet extends SDMSet<Kalah>
{


   public KalahPO hasKalahPO()
   {
      return new KalahPO(this.toArray(new Kalah[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.mancala.model.Kalah";
   }


   @SuppressWarnings("unchecked")
   public KalahSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Kalah>)value);
      }
      else if (value != null)
      {
         this.add((Kalah) value);
      }
      
      return this;
   }
   
   public KalahSet without(Kalah value)
   {
      this.remove(value);
      return this;
   }

   public intList getNr()
   {
      intList result = new intList();
      
      for (Kalah obj : this)
      {
         result.add(obj.getNr());
      }
      
      return result;
   }

   public KalahSet hasNr(int value)
   {
      KalahSet result = new KalahSet();
      
      for (Kalah obj : this)
      {
         if (value == obj.getNr())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public KalahSet hasNr(int lower, int upper)
   {
      KalahSet result = new KalahSet();
      
      for (Kalah obj : this)
      {
         if (lower <= obj.getNr() && obj.getNr() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public KalahSet withNr(int value)
   {
      for (Kalah obj : this)
      {
         obj.setNr(value);
      }
      
      return this;
   }

   public MancalaSet getGame()
   {
      MancalaSet result = new MancalaSet();
      
      for (Kalah obj : this)
      {
         result.add(obj.getGame());
      }
      
      return result;
   }

   public KalahSet hasGame(Object value)
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
      
      KalahSet answer = new KalahSet();
      
      for (Kalah obj : this)
      {
         if (neighbors.contains(obj.getGame()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public KalahSet withGame(Mancala value)
   {
      for (Kalah obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

   public PlayerSet getPlayer()
   {
      PlayerSet result = new PlayerSet();
      
      for (Kalah obj : this)
      {
         result.add(obj.getPlayer());
      }
      
      return result;
   }

   public KalahSet hasPlayer(Object value)
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
      
      KalahSet answer = new KalahSet();
      
      for (Kalah obj : this)
      {
         if (neighbors.contains(obj.getPlayer()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public KalahSet withPlayer(Player value)
   {
      for (Kalah obj : this)
      {
         obj.withPlayer(value);
      }
      
      return this;
   }

   public PitSet getNext()
   {
      PitSet result = new PitSet();
      
      for (Kalah obj : this)
      {
         result.add(obj.getNext());
      }
      
      return result;
   }

   public KalahSet hasNext(Object value)
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
      
      KalahSet answer = new KalahSet();
      
      for (Kalah obj : this)
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

   public KalahSet withNext(Pit value)
   {
      for (Kalah obj : this)
      {
         obj.withNext(value);
      }
      
      return this;
   }

   public PitSet getPrevious()
   {
      PitSet result = new PitSet();
      
      for (Kalah obj : this)
      {
         result.add(obj.getPrevious());
      }
      
      return result;
   }

   public KalahSet hasPrevious(Object value)
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
      
      KalahSet answer = new KalahSet();
      
      for (Kalah obj : this)
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

   public KalahSet withPrevious(Pit value)
   {
      for (Kalah obj : this)
      {
         obj.withPrevious(value);
      }
      
      return this;
   }

   public PitSet getCounterpart()
   {
      PitSet result = new PitSet();
      
      for (Kalah obj : this)
      {
         result.add(obj.getCounterpart());
      }
      
      return result;
   }

   public KalahSet hasCounterpart(Object value)
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
      
      KalahSet answer = new KalahSet();
      
      for (Kalah obj : this)
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

   public KalahSet withCounterpart(Pit value)
   {
      for (Kalah obj : this)
      {
         obj.withCounterpart(value);
      }
      
      return this;
   }

   public PlayerSet getKalahPlayer()
   {
      PlayerSet result = new PlayerSet();
      
      for (Kalah obj : this)
      {
         result.add(obj.getKalahPlayer());
      }
      
      return result;
   }

   public KalahSet hasKalahPlayer(Object value)
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
      
      KalahSet answer = new KalahSet();
      
      for (Kalah obj : this)
      {
         if (neighbors.contains(obj.getKalahPlayer()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public KalahSet withKalahPlayer(Player value)
   {
      for (Kalah obj : this)
      {
         obj.withKalahPlayer(value);
      }
      
      return this;
   }

}
