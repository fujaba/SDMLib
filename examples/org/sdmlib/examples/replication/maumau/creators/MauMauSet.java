/*
   Copyright (c) 2013 zuendorf 
   
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
   
package org.sdmlib.examples.replication.maumau.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.replication.maumau.MauMau;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.replication.maumau.creators.CardSet;
import org.sdmlib.examples.replication.maumau.Card;
import org.sdmlib.examples.replication.maumau.creators.HolderSet;
import org.sdmlib.examples.replication.maumau.Holder;
import org.sdmlib.examples.replication.maumau.creators.PlayerSet;
import org.sdmlib.examples.replication.maumau.Player;

public class MauMauSet extends LinkedHashSet<MauMau> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (MauMau elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.replication.maumau.MauMau";
   }


   public MauMauSet with(MauMau value)
   {
      this.add(value);
      return this;
   }
   
   public MauMauSet without(MauMau value)
   {
      this.remove(value);
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

   public PlayerSet getCurrentMove()
   {
      PlayerSet result = new PlayerSet();
      
      for (MauMau obj : this)
      {
         result.add(obj.getCurrentMove());
      }
      
      return result;
   }

   public MauMauSet withCurrentMove(Player value)
   {
      for (MauMau obj : this)
      {
         obj.withCurrentMove(value);
      }
      
      return this;
   }

}

