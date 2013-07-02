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
import java.util.LinkedList;

import org.sdmlib.examples.replication.maumau.Card;
import org.sdmlib.examples.replication.maumau.Suit;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.replication.maumau.creators.ValueSet;
import org.sdmlib.examples.replication.maumau.Value;
import org.sdmlib.examples.replication.maumau.creators.MauMauSet;
import org.sdmlib.examples.replication.maumau.MauMau;
import org.sdmlib.examples.replication.maumau.creators.HolderSet;
import org.sdmlib.examples.replication.maumau.Holder;

public class CardSet extends LinkedList<Card> implements org.sdmlib.models.modelsets.ModelSet
{

   @Override
   public boolean add(Card e)
   {
      if ( ! this.contains(e))
      {
         return super.add(e);
      }
      
      return false;
   }

   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Card elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.replication.maumau.Card";
   }


   public CardSet with(Card value)
   {
      this.add(value);
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

   public CardSet withHolder(Holder value)
   {
      for (Card obj : this)
      {
         obj.withHolder(value);
      }
      
      return this;
   }

}

