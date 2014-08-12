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
   
package org.sdmlib.examples.mancala.model.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.mancala.model.Player;
import java.util.Collection;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.examples.mancala.model.util.MancalaSet;
import org.sdmlib.examples.mancala.model.Mancala;
import java.util.Collections;
import org.sdmlib.examples.mancala.model.util.PitSet;
import org.sdmlib.examples.mancala.model.Pit;
import org.sdmlib.examples.mancala.model.util.KalahSet;
import org.sdmlib.examples.mancala.model.Kalah;
import org.sdmlib.examples.mancala.model.util.StoneSet;
import org.sdmlib.examples.mancala.model.Stone;

public class PlayerSet extends SDMSet<Player>
{


   public PlayerPO hasPlayerPO()
   {
      return new PlayerPO(this.toArray(new Player[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.mancala.model.Player";
   }


   @SuppressWarnings("unchecked")
   public PlayerSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Player>)value);
      }
      else if (value != null)
      {
         this.add((Player) value);
      }
      
      return this;
   }
   
   public PlayerSet without(Player value)
   {
      this.remove(value);
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Player obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public PlayerSet hasName(String value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PlayerSet hasName(String lower, String upper)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PlayerSet withName(String value)
   {
      for (Player obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public MancalaSet getGame()
   {
      MancalaSet result = new MancalaSet();
      
      for (Player obj : this)
      {
         result.add(obj.getGame());
      }
      
      return result;
   }

   public PlayerSet hasGame(Object value)
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
      
      PlayerSet answer = new PlayerSet();
      
      for (Player obj : this)
      {
         if (neighbors.contains(obj.getGame()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PlayerSet withGame(Mancala value)
   {
      for (Player obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

   public PitSet getPits()
   {
      PitSet result = new PitSet();
      
      for (Player obj : this)
      {
         result.addAll(obj.getPits());
      }
      
      return result;
   }

   public PlayerSet hasPits(Object value)
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
      
      PlayerSet answer = new PlayerSet();
      
      for (Player obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPits()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PlayerSet withPits(Pit value)
   {
      for (Player obj : this)
      {
         obj.withPits(value);
      }
      
      return this;
   }

   public PlayerSet withoutPits(Pit value)
   {
      for (Player obj : this)
      {
         obj.withoutPits(value);
      }
      
      return this;
   }

   public KalahSet getKalah()
   {
      KalahSet result = new KalahSet();
      
      for (Player obj : this)
      {
         result.add(obj.getKalah());
      }
      
      return result;
   }

   public PlayerSet hasKalah(Object value)
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
      
      PlayerSet answer = new PlayerSet();
      
      for (Player obj : this)
      {
         if (neighbors.contains(obj.getKalah()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PlayerSet withKalah(Kalah value)
   {
      for (Player obj : this)
      {
         obj.withKalah(value);
      }
      
      return this;
   }

   public StoneSet getStone()
   {
      StoneSet result = new StoneSet();
      
      for (Player obj : this)
      {
         result.add(obj.getStone());
      }
      
      return result;
   }

   public PlayerSet hasStone(Object value)
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
      
      PlayerSet answer = new PlayerSet();
      
      for (Player obj : this)
      {
         if (neighbors.contains(obj.getStone()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PlayerSet withStone(Stone value)
   {
      for (Player obj : this)
      {
         obj.withStone(value);
      }
      
      return this;
   }

   public MancalaSet getActiveGame()
   {
      MancalaSet result = new MancalaSet();
      
      for (Player obj : this)
      {
         result.add(obj.getActiveGame());
      }
      
      return result;
   }

   public PlayerSet hasActiveGame(Object value)
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
      
      PlayerSet answer = new PlayerSet();
      
      for (Player obj : this)
      {
         if (neighbors.contains(obj.getActiveGame()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PlayerSet withActiveGame(Mancala value)
   {
      for (Player obj : this)
      {
         obj.withActiveGame(value);
      }
      
      return this;
   }

}
