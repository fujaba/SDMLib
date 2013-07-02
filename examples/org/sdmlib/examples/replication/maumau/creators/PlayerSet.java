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
import org.sdmlib.examples.replication.maumau.Player;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.examples.replication.maumau.creators.MauMauSet;
import org.sdmlib.examples.replication.maumau.MauMau;
import org.sdmlib.examples.replication.maumau.creators.CardSet;
import org.sdmlib.examples.replication.maumau.Card;
import org.sdmlib.examples.replication.maumau.creators.PlayerSet;

public class PlayerSet extends LinkedHashSet<Player> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Player elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.replication.maumau.Player";
   }


   public PlayerSet with(Player value)
   {
      this.add(value);
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

   public PlayerSet withName(String value)
   {
      for (Player obj : this)
      {
         obj.setName(value);
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

   public PlayerSet withDeckOwner(MauMau value)
   {
      for (Player obj : this)
      {
         obj.withDeckOwner(value);
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

   public MauMauSet getStackOwner()
   {
      MauMauSet result = new MauMauSet();
      
      for (Player obj : this)
      {
         result.addAll(obj.getStackOwner());
      }
      
      return result;
   }

   public PlayerSet withStackOwner(MauMau value)
   {
      for (Player obj : this)
      {
         obj.withStackOwner(value);
      }
      
      return this;
   }

   public PlayerSet withoutStackOwner(MauMau value)
   {
      for (Player obj : this)
      {
         obj.withoutStackOwner(value);
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

   public PlayerSet withGame(MauMau value)
   {
      for (Player obj : this)
      {
         obj.withGame(value);
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

   public PlayerSet withPrev(Player value)
   {
      for (Player obj : this)
      {
         obj.withPrev(value);
      }
      
      return this;
   }

   public MauMauSet getAssignment()
   {
      MauMauSet result = new MauMauSet();
      
      for (Player obj : this)
      {
         result.add(obj.getAssignment());
      }
      
      return result;
   }

   public PlayerSet withAssignment(MauMau value)
   {
      for (Player obj : this)
      {
         obj.withAssignment(value);
      }
      
      return this;
   }

}

