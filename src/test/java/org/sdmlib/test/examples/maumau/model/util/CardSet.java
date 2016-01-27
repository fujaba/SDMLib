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

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.Holder;
import org.sdmlib.test.examples.maumau.model.MauMau;
import org.sdmlib.test.examples.maumau.model.Suit;
import org.sdmlib.test.examples.maumau.model.Value;

import de.uniks.networkparser.list.SimpleSet;

public class CardSet extends SimpleSet<Card>
{

   public static final CardSet EMPTY_SET = new CardSet().withFlag(CardSet.READONLY);


   public CardPO hasCardPO()
   {
      return new CardPO(this.toArray(new Card[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.maumau.model.Card";
   }


   @SuppressWarnings("unchecked")
   public CardSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Card>)value);
      }
      else if (value != null)
      {
         this.add((Card) value);
      }
      
      return this;
   }
   
   public CardSet without(Card value)
   {
      this.remove(value);
      return this;
   }

   public SuitSet getSuit()
   {
      SuitSet result = new SuitSet();
      
      for (Card obj : this)
      {
         result.add(obj.getSuit());
      }
      
      return result;
   }

   public CardSet hasSuit(Suit value)
   {
      CardSet result = new CardSet();
      
      for (Card obj : this)
      {
         if (value == obj.getSuit())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public CardSet withSuit(Suit value)
   {
      for (Card obj : this)
      {
         obj.setSuit(value);
      }
      
      return this;
   }

   public ValueSet getValue()
   {
      ValueSet result = new ValueSet();
      
      for (Card obj : this)
      {
         result.add(obj.getValue());
      }
      
      return result;
   }

   public CardSet hasValue(Value value)
   {
      CardSet result = new CardSet();
      
      for (Card obj : this)
      {
         if (value == obj.getValue())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public CardSet withValue(Value value)
   {
      for (Card obj : this)
      {
         obj.setValue(value);
      }
      
      return this;
   }

   public MauMauSet getGame()
   {
      MauMauSet result = new MauMauSet();
      
      for (Card obj : this)
      {
         result.add(obj.getGame());
      }
      
      return result;
   }

   public CardSet hasGame(Object value)
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
      
      CardSet answer = new CardSet();
      
      for (Card obj : this)
      {
         if (neighbors.contains(obj.getGame()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public CardSet withGame(MauMau value)
   {
      for (Card obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

   public HolderSet getHolder()
   {
      HolderSet result = new HolderSet();
      
      for (Card obj : this)
      {
         result.add(obj.getHolder());
      }
      
      return result;
   }

   public CardSet hasHolder(Object value)
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
      
      CardSet answer = new CardSet();
      
      for (Card obj : this)
      {
         if (neighbors.contains(obj.getHolder()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public CardSet withHolder(Holder value)
   {
      for (Card obj : this)
      {
         obj.withHolder(value);
      }
      
      return this;
   }

}
