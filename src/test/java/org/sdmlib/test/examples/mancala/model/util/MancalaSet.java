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
import java.util.Collections;

import org.sdmlib.test.examples.mancala.model.Mancala;
import org.sdmlib.test.examples.mancala.model.Pit;
import org.sdmlib.test.examples.mancala.model.Player;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class MancalaSet extends SimpleSet<Mancala>
{


   public MancalaPO hasMancalaPO()
   {
      return new MancalaPO(this.toArray(new Mancala[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public MancalaSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Mancala>)value);
      }
      else if (value != null)
      {
         this.add((Mancala) value);
      }
      
      return this;
   }
   
   public MancalaSet without(Mancala value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public MancalaSet checkEnd()
   {
      for (Mancala obj : this)
      {
         obj.checkEnd();
      }
      return this;
   }

   

   public PlayerSet getActivePlayer()
   {
      PlayerSet result = new PlayerSet();
      
      for (Mancala obj : this)
      {
         result.add(obj.getActivePlayer());
      }
      
      return result;
   }

   public MancalaSet hasActivePlayer(Object value)
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
      
      MancalaSet answer = new MancalaSet();
      
      for (Mancala obj : this)
      {
         if (neighbors.contains(obj.getActivePlayer()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MancalaSet withActivePlayer(Player value)
   {
      for (Mancala obj : this)
      {
         obj.withActivePlayer(value);
      }
      
      return this;
   }

   public PlayerSet getPlayers()
   {
      PlayerSet result = new PlayerSet();
      
      for (Mancala obj : this)
      {
         result.addAll(obj.getPlayers());
      }
      
      return result;
   }

   public MancalaSet hasPlayers(Object value)
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
      
      MancalaSet answer = new MancalaSet();
      
      for (Mancala obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPlayers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MancalaSet withPlayers(Player value)
   {
      for (Mancala obj : this)
      {
         obj.withPlayers(value);
      }
      
      return this;
   }

   public MancalaSet withoutPlayers(Player value)
   {
      for (Mancala obj : this)
      {
         obj.withoutPlayers(value);
      }
      
      return this;
   }

   public PitSet getPits()
   {
      PitSet result = new PitSet();
      
      for (Mancala obj : this)
      {
         result.addAll(obj.getPits());
      }
      
      return result;
   }

   public MancalaSet hasPits(Object value)
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
      
      MancalaSet answer = new MancalaSet();
      
      for (Mancala obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPits()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MancalaSet withPits(Pit value)
   {
      for (Mancala obj : this)
      {
         obj.withPits(value);
      }
      
      return this;
   }

   public MancalaSet withoutPits(Pit value)
   {
      for (Mancala obj : this)
      {
         obj.withoutPits(value);
      }
      
      return this;
   }


   public static final MancalaSet EMPTY_SET = new MancalaSet().withFlag(MancalaSet.READONLY);


   public MancalaPO filterMancalaPO()
   {
      return new MancalaPO(this.toArray(new Mancala[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.mancala.model.Mancala";
   }

   public MancalaSet()
   {
      // empty
   }

   public MancalaSet(Mancala... objects)
   {
      for (Mancala obj : objects)
      {
         this.add(obj);
      }
   }

   public MancalaSet(Collection<Mancala> objects)
   {
      this.addAll(objects);
   }


   public MancalaPO createMancalaPO()
   {
      return new MancalaPO(this.toArray(new Mancala[this.size()]));
   }


   @Override
   public MancalaSet getNewList(boolean keyValue)
   {
      return new MancalaSet();
   }

   //==========================================================================
   
   public MancalaSet initGame(String firstPlayerName, String secondPlayerName)
   {
      for (Mancala obj : this)
      {
         obj.initGame(firstPlayerName, secondPlayerName);
      }
      return this;
   }

}
