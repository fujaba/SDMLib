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
import org.sdmlib.examples.replication.maumau.Holder;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.replication.maumau.creators.MauMauSet;
import org.sdmlib.examples.replication.maumau.MauMau;
import org.sdmlib.examples.replication.maumau.creators.CardSet;
import org.sdmlib.examples.replication.maumau.Card;

public class HolderSet extends LinkedHashSet<Holder> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Holder elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.replication.maumau.Holder";
   }


   public HolderSet with(Holder value)
   {
      this.add(value);
      return this;
   }
   
   public HolderSet without(Holder value)
   {
      this.remove(value);
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

   public HolderSet withDeckOwner(MauMau value)
   {
      for (Holder obj : this)
      {
         obj.withDeckOwner(value);
      }
      
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

   public MauMauSet getStackOwner()
   {
      MauMauSet result = new MauMauSet();
      
      for (Holder obj : this)
      {
         result.addAll(obj.getStackOwner());
      }
      
      return result;
   }

   public HolderSet withStackOwner(MauMau value)
   {
      for (Holder obj : this)
      {
         obj.withStackOwner(value);
      }
      
      return this;
   }

   public HolderSet withoutStackOwner(MauMau value)
   {
      for (Holder obj : this)
      {
         obj.withoutStackOwner(value);
      }
      
      return this;
   }

}

