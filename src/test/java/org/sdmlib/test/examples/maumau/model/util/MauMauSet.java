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

import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.DrawingStack;
import org.sdmlib.test.examples.maumau.model.Holder;
import org.sdmlib.test.examples.maumau.model.MauMau;
import org.sdmlib.test.examples.maumau.model.OpenStack;
import org.sdmlib.test.examples.maumau.model.Player;
import org.sdmlib.test.examples.maumau.model.Suit;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class MauMauSet extends SimpleSet<MauMau>
{

   public static final MauMauSet EMPTY_SET = new MauMauSet().withFlag(MauMauSet.READONLY);


   public MauMauPO hasMauMauPO()
   {
      return new MauMauPO(this.toArray(new MauMau[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.maumau.model.MauMau";
   }


   @SuppressWarnings("unchecked")
   public MauMauSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<MauMau>)value);
      }
      else if (value != null)
      {
         this.add((MauMau) value);
      }
      
      return this;
   }
   
   public MauMauSet without(MauMau value)
   {
      this.remove(value);
      return this;
   }

   public PlayerSet getCurrentPlayer()
   {
      PlayerSet result = new PlayerSet();
      
      for (MauMau obj : this)
      {
         result.add(obj.getCurrentPlayer());
      }
      
      return result;
   }

   public MauMauSet hasCurrentPlayer(Player value)
   {
      MauMauSet result = new MauMauSet();
      
      for (MauMau obj : this)
      {
         if (value == obj.getCurrentPlayer())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MauMauSet withCurrentPlayer(Player value)
   {
      for (MauMau obj : this)
      {
         obj.setCurrentPlayer(value);
      }
      
      return this;
   }

   public SuitSet getCurrentSuit()
   {
      SuitSet result = new SuitSet();
      
      for (MauMau obj : this)
      {
         result.add(obj.getCurrentSuit());
      }
      
      return result;
   }

   public MauMauSet hasCurrentSuit(Suit value)
   {
      MauMauSet result = new MauMauSet();
      
      for (MauMau obj : this)
      {
         if (value == obj.getCurrentSuit())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MauMauSet withCurrentSuit(Suit value)
   {
      for (MauMau obj : this)
      {
         obj.setCurrentSuit(value);
      }
      
      return this;
   }

   public CardSet getCards()
   {
      CardSet result = new CardSet();
      
      for (MauMau obj : this)
      {
         result.addAll(obj.getCards());
      }
      
      return result;
   }

   public MauMauSet hasCards(Object value)
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
      
      MauMauSet answer = new MauMauSet();
      
      for (MauMau obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getCards()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MauMauSet withCards(Card value)
   {
      for (MauMau obj : this)
      {
         obj.withCards(value);
      }
      
      return this;
   }

   public MauMauSet withoutCards(Card value)
   {
      for (MauMau obj : this)
      {
         obj.withoutCards(value);
      }
      
      return this;
   }

   public HolderSet getDeck()
   {
      HolderSet result = new HolderSet();
      
      for (MauMau obj : this)
      {
         result.add(obj.getDeck());
      }
      
      return result;
   }

   public MauMauSet hasDeck(Object value)
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
      
      MauMauSet answer = new MauMauSet();
      
      for (MauMau obj : this)
      {
         if (neighbors.contains(obj.getDeck()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MauMauSet withDeck(Holder value)
   {
      for (MauMau obj : this)
      {
         obj.withDeck(value);
      }
      
      return this;
   }

   public HolderSet getStack()
   {
      HolderSet result = new HolderSet();
      
      for (MauMau obj : this)
      {
         result.add(obj.getStack());
      }
      
      return result;
   }

   public MauMauSet hasStack(Object value)
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
      
      MauMauSet answer = new MauMauSet();
      
      for (MauMau obj : this)
      {
         if (neighbors.contains(obj.getStack()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MauMauSet withStack(Holder value)
   {
      for (MauMau obj : this)
      {
         obj.withStack(value);
      }
      
      return this;
   }

   public PlayerSet getPlayers()
   {
      PlayerSet result = new PlayerSet();
      
      for (MauMau obj : this)
      {
         result.addAll(obj.getPlayers());
      }
      
      return result;
   }

   public MauMauSet hasPlayers(Object value)
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
      
      MauMauSet answer = new MauMauSet();
      
      for (MauMau obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPlayers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MauMauSet withPlayers(Player value)
   {
      for (MauMau obj : this)
      {
         obj.withPlayers(value);
      }
      
      return this;
   }

   public MauMauSet withoutPlayers(Player value)
   {
      for (MauMau obj : this)
      {
         obj.withoutPlayers(value);
      }
      
      return this;
   }

   public PlayerSet getWinner()
   {
      PlayerSet result = new PlayerSet();
      
      for (MauMau obj : this)
      {
         result.add(obj.getWinner());
      }
      
      return result;
   }

   public MauMauSet hasWinner(Object value)
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
      
      MauMauSet answer = new MauMauSet();
      
      for (MauMau obj : this)
      {
         if (neighbors.contains(obj.getWinner()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MauMauSet withWinner(Player value)
   {
      for (MauMau obj : this)
      {
         obj.withWinner(value);
      }
      
      return this;
   }

   public PlayerSet getLosers()
   {
      PlayerSet result = new PlayerSet();
      
      for (MauMau obj : this)
      {
         result.addAll(obj.getLosers());
      }
      
      return result;
   }

   public MauMauSet hasLosers(Object value)
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
      
      MauMauSet answer = new MauMauSet();
      
      for (MauMau obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getLosers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MauMauSet withLosers(Player value)
   {
      for (MauMau obj : this)
      {
         obj.withLosers(value);
      }
      
      return this;
   }

   public MauMauSet withoutLosers(Player value)
   {
      for (MauMau obj : this)
      {
         obj.withoutLosers(value);
      }
      
      return this;
   }

   public DrawingStackSet getDrawingStack()
   {
      DrawingStackSet result = new DrawingStackSet();
      
      for (MauMau obj : this)
      {
         result.add(obj.getDrawingStack());
      }
      
      return result;
   }

   public MauMauSet hasDrawingStack(Object value)
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
      
      MauMauSet answer = new MauMauSet();
      
      for (MauMau obj : this)
      {
         if (neighbors.contains(obj.getDrawingStack()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MauMauSet withDrawingStack(DrawingStack value)
   {
      for (MauMau obj : this)
      {
         obj.withDrawingStack(value);
      }
      
      return this;
   }

   public OpenStackSet getOpenStack()
   {
      OpenStackSet result = new OpenStackSet();
      
      for (MauMau obj : this)
      {
         result.add(obj.getOpenStack());
      }
      
      return result;
   }

   public MauMauSet hasOpenStack(Object value)
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
      
      MauMauSet answer = new MauMauSet();
      
      for (MauMau obj : this)
      {
         if (neighbors.contains(obj.getOpenStack()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MauMauSet withOpenStack(OpenStack value)
   {
      for (MauMau obj : this)
      {
         obj.withOpenStack(value);
      }
      
      return this;
   }



   public MauMauPO filterMauMauPO()
   {
      return new MauMauPO(this.toArray(new MauMau[this.size()]));
   }

   /**
    * Loop through the current set of MauMau objects and collect those MauMau objects where the currentPlayer attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MauMau objects that match the parameter
    */
   public MauMauSet filterCurrentPlayer(Player value)
   {
      MauMauSet result = new MauMauSet();
      
      for (MauMau obj : this)
      {
         if (value == obj.getCurrentPlayer())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MauMau objects and collect those MauMau objects where the currentSuit attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MauMau objects that match the parameter
    */
   public MauMauSet filterCurrentSuit(Suit value)
   {
      MauMauSet result = new MauMauSet();
      
      for (MauMau obj : this)
      {
         if (value == obj.getCurrentSuit())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public MauMauSet()
   {
      // empty
   }

   public MauMauSet(MauMau... objects)
   {
      for (MauMau obj : objects)
      {
         this.add(obj);
      }
   }

   public MauMauSet(Collection<MauMau> objects)
   {
      this.addAll(objects);
   }


   public MauMauPO createMauMauPO()
   {
      return new MauMauPO(this.toArray(new MauMau[this.size()]));
   }


   @Override
   public MauMauSet getNewList(boolean keyValue)
   {
      return new MauMauSet();
   }

   /**
    * Loop through the current set of MauMau objects and collect those MauMau objects where the currentPlayer attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MauMau objects that match the parameter
    */
   public MauMauSet createCurrentPlayerCondition(Player value)
   {
      MauMauSet result = new MauMauSet();
      
      for (MauMau obj : this)
      {
         if (value == obj.getCurrentPlayer())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MauMau objects and collect those MauMau objects where the currentSuit attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MauMau objects that match the parameter
    */
   public MauMauSet createCurrentSuitCondition(Suit value)
   {
      MauMauSet result = new MauMauSet();
      
      for (MauMau obj : this)
      {
         if (value == obj.getCurrentSuit())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
