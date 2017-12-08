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
import java.util.Collections;

import org.sdmlib.replication.Lane;
import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.Duty;
import org.sdmlib.test.examples.maumau.model.MauMau;
import org.sdmlib.test.examples.maumau.model.Player;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;

public class PlayerSet extends SimpleSet<Player>
{

   public static final PlayerSet EMPTY_SET = new PlayerSet().withFlag(PlayerSet.READONLY);


   public PlayerPO hasPlayerPO()
   {
      return new PlayerPO(this.toArray(new Player[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.maumau.model.Player";
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

   public LaneSet getLane()
   {
      LaneSet result = new LaneSet();
      
      for (Player obj : this)
      {
         result.add(obj.getLane());
      }
      
      return result;
   }

   public PlayerSet hasLane(Lane value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value == obj.getLane())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PlayerSet withLane(Lane value)
   {
      for (Player obj : this)
      {
         obj.setLane(value);
      }
      
      return this;
   }

   public CardSet getCards()
   {
      CardSet result = new CardSet();
      
      for (Player obj : this)
      {
         result.addAll(obj.getCards());
      }
      
      return result;
   }

   public PlayerSet hasCards(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getCards()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PlayerSet withCards(Card value)
   {
      for (Player obj : this)
      {
         obj.withCards(value);
      }
      
      return this;
   }

   public PlayerSet withoutCards(Card value)
   {
      for (Player obj : this)
      {
         obj.withoutCards(value);
      }
      
      return this;
   }

   public MauMauSet getDeckOwner()
   {
      MauMauSet result = new MauMauSet();
      
      for (Player obj : this)
      {
         result.add(obj.getDeckOwner());
      }
      
      return result;
   }

   public PlayerSet hasDeckOwner(Object value)
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
         if (neighbors.contains(obj.getDeckOwner()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PlayerSet withDeckOwner(MauMau value)
   {
      for (Player obj : this)
      {
         obj.withDeckOwner(value);
      }
      
      return this;
   }

   public MauMauSet getStackOwner()
   {
      MauMauSet result = new MauMauSet();
      
      for (Player obj : this)
      {
         result.add(obj.getStackOwner());
      }
      
      return result;
   }

   public PlayerSet hasStackOwner(Object value)
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
         if (neighbors.contains(obj.getStackOwner()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PlayerSet withStackOwner(MauMau value)
   {
      for (Player obj : this)
      {
         obj.withStackOwner(value);
      }
      
      return this;
   }

   public MauMauSet getGame()
   {
      MauMauSet result = new MauMauSet();
      
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

   public PlayerSet withGame(MauMau value)
   {
      for (Player obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

   public MauMauSet getWonGame()
   {
      MauMauSet result = new MauMauSet();
      
      for (Player obj : this)
      {
         result.add(obj.getWonGame());
      }
      
      return result;
   }

   public PlayerSet hasWonGame(Object value)
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
         if (neighbors.contains(obj.getWonGame()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PlayerSet withWonGame(MauMau value)
   {
      for (Player obj : this)
      {
         obj.withWonGame(value);
      }
      
      return this;
   }

   public MauMauSet getLostGame()
   {
      MauMauSet result = new MauMauSet();
      
      for (Player obj : this)
      {
         result.add(obj.getLostGame());
      }
      
      return result;
   }

   public PlayerSet hasLostGame(Object value)
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
         if (neighbors.contains(obj.getLostGame()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PlayerSet withLostGame(MauMau value)
   {
      for (Player obj : this)
      {
         obj.withLostGame(value);
      }
      
      return this;
   }

   public PlayerSet getNext()
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         result.add(obj.getNext());
      }
      
      return result;
   }

   public PlayerSet hasNext(Object value)
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
         if (neighbors.contains(obj.getNext()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public PlayerSet getNextTransitive()
   {
      PlayerSet todo = new PlayerSet().with(this);
      
      PlayerSet result = new PlayerSet();
      
      while ( ! todo.isEmpty())
      {
         Player current = todo.first();
         
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

   public PlayerSet withNext(Player value)
   {
      for (Player obj : this)
      {
         obj.withNext(value);
      }
      
      return this;
   }

   public PlayerSet getPrev()
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         result.add(obj.getPrev());
      }
      
      return result;
   }

   public PlayerSet hasPrev(Object value)
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
         if (neighbors.contains(obj.getPrev()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public PlayerSet getPrevTransitive()
   {
      PlayerSet todo = new PlayerSet().with(this);
      
      PlayerSet result = new PlayerSet();
      
      while ( ! todo.isEmpty())
      {
         Player current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getPrev()))
            {
               todo.with(current.getPrev());
            }
         }
      }
      
      return result;
   }

   public PlayerSet withPrev(Player value)
   {
      for (Player obj : this)
      {
         obj.withPrev(value);
      }
      
      return this;
   }

   public DutySet getDuty()
   {
      DutySet result = new DutySet();
      
      for (Player obj : this)
      {
         result.addAll(obj.getDuty());
      }
      
      return result;
   }

   public PlayerSet hasDuty(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getDuty()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public PlayerSet withDuty(Duty value)
   {
      for (Player obj : this)
      {
         obj.withDuty(value);
      }
      
      return this;
   }

   public PlayerSet withoutDuty(Duty value)
   {
      for (Player obj : this)
      {
         obj.withoutDuty(value);
      }
      
      return this;
   }



   public PlayerPO filterPlayerPO()
   {
      return new PlayerPO(this.toArray(new Player[this.size()]));
   }

   /**
    * Loop through the current set of Player objects and collect those Player objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Player objects that match the parameter
    */
   public PlayerSet filterName(String value)
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


   /**
    * Loop through the current set of Player objects and collect those Player objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Player objects that match the parameter
    */
   public PlayerSet filterName(String lower, String upper)
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


   /**
    * Loop through the current set of Player objects and collect those Player objects where the lane attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Player objects that match the parameter
    */
   public PlayerSet filterLane(Lane value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value == obj.getLane())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public PlayerSet()
   {
      // empty
   }

   public PlayerSet(Player... objects)
   {
      for (Player obj : objects)
      {
         this.add(obj);
      }
   }

   public PlayerSet(Collection<Player> objects)
   {
      this.addAll(objects);
   }


   public PlayerPO createPlayerPO()
   {
      return new PlayerPO(this.toArray(new Player[this.size()]));
   }


   @Override
   public PlayerSet getNewList(boolean keyValue)
   {
      return new PlayerSet();
   }

   /**
    * Loop through the current set of Player objects and collect those Player objects where the lane attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Player objects that match the parameter
    */
   public PlayerSet createLaneCondition(Lane value)
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         if (value == obj.getLane())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Player objects and collect those Player objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Player objects that match the parameter
    */
   public PlayerSet createNameCondition(String value)
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


   /**
    * Loop through the current set of Player objects and collect those Player objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Player objects that match the parameter
    */
   public PlayerSet createNameCondition(String lower, String upper)
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

}
