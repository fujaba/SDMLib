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

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.Holder;
import org.sdmlib.test.examples.maumau.model.MauMau;

import de.uniks.networkparser.list.SimpleSet;

public class HolderSet extends SimpleSet<Holder>
{

   public static final HolderSet EMPTY_SET = new HolderSet().withFlag(HolderSet.READONLY);


   public HolderPO hasHolderPO()
   {
      return new HolderPO(this.toArray(new Holder[this.size()]));
   }


   @SuppressWarnings("unchecked")
   public HolderSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Holder>)value);
      }
      else if (value != null)
      {
         this.add((Holder) value);
      }
      
      return this;
   }
   
   public HolderSet without(Holder value)
   {
      this.remove(value);
      return this;
   }

   public CardSet getCards()
   {
      CardSet result = new CardSet();
      
      for (Holder obj : this)
      {
         result.addAll(obj.getCards());
      }
      
      return result;
   }

   public HolderSet hasCards(Object value)
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
      
      HolderSet answer = new HolderSet();
      
      for (Holder obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getCards()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public HolderSet withCards(Card value)
   {
      for (Holder obj : this)
      {
         obj.withCards(value);
      }
      
      return this;
   }

   public HolderSet withoutCards(Card value)
   {
      for (Holder obj : this)
      {
         obj.withoutCards(value);
      }
      
      return this;
   }

   public MauMauSet getDeckOwner()
   {
      MauMauSet result = new MauMauSet();
      
      for (Holder obj : this)
      {
         result.add(obj.getDeckOwner());
      }
      
      return result;
   }

   public HolderSet hasDeckOwner(Object value)
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
      
      HolderSet answer = new HolderSet();
      
      for (Holder obj : this)
      {
         if (neighbors.contains(obj.getDeckOwner()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public HolderSet withDeckOwner(MauMau value)
   {
      for (Holder obj : this)
      {
         obj.withDeckOwner(value);
      }
      
      return this;
   }

   public MauMauSet getStackOwner()
   {
      MauMauSet result = new MauMauSet();
      
      for (Holder obj : this)
      {
         result.add(obj.getStackOwner());
      }
      
      return result;
   }

   public HolderSet hasStackOwner(Object value)
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
      
      HolderSet answer = new HolderSet();
      
      for (Holder obj : this)
      {
         if (neighbors.contains(obj.getStackOwner()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public HolderSet withStackOwner(MauMau value)
   {
      for (Holder obj : this)
      {
         obj.withStackOwner(value);
      }
      
      return this;
   }

}
