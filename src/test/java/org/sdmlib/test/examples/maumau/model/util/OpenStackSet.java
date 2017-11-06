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
import org.sdmlib.test.examples.maumau.model.MauMau;
import org.sdmlib.test.examples.maumau.model.OpenStack;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.test.examples.maumau.model.util.CardSet;
import org.sdmlib.test.examples.maumau.model.util.MauMauSet;

public class OpenStackSet extends SimpleSet<OpenStack>
{

   public static final OpenStackSet EMPTY_SET = new OpenStackSet().withFlag(OpenStackSet.READONLY);


   public OpenStackPO hasOpenStackPO()
   {
      return new OpenStackPO(this.toArray(new OpenStack[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.maumau.model.OpenStack";
   }


   @SuppressWarnings("unchecked")
   public OpenStackSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<OpenStack>)value);
      }
      else if (value != null)
      {
         this.add((OpenStack) value);
      }
      
      return this;
   }
   
   public OpenStackSet without(OpenStack value)
   {
      this.remove(value);
      return this;
   }

   public CardSet getCards()
   {
      CardSet result = new CardSet();
      
      for (OpenStack obj : this)
      {
         result.addAll(obj.getCards());
      }
      
      return result;
   }

   public OpenStackSet hasCards(Object value)
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
      
      OpenStackSet answer = new OpenStackSet();
      
      for (OpenStack obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getCards()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public OpenStackSet withCards(Card value)
   {
      for (OpenStack obj : this)
      {
         obj.withCards(value);
      }
      
      return this;
   }

   public OpenStackSet withoutCards(Card value)
   {
      for (OpenStack obj : this)
      {
         obj.withoutCards(value);
      }
      
      return this;
   }

   public MauMauSet getDeckOwner()
   {
      MauMauSet result = new MauMauSet();
      
      for (OpenStack obj : this)
      {
         result.add(obj.getDeckOwner());
      }
      
      return result;
   }

   public OpenStackSet hasDeckOwner(Object value)
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
      
      OpenStackSet answer = new OpenStackSet();
      
      for (OpenStack obj : this)
      {
         if (neighbors.contains(obj.getDeckOwner()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public OpenStackSet withDeckOwner(MauMau value)
   {
      for (OpenStack obj : this)
      {
         obj.withDeckOwner(value);
      }
      
      return this;
   }

   public MauMauSet getStackOwner()
   {
      MauMauSet result = new MauMauSet();
      
      for (OpenStack obj : this)
      {
         result.add(obj.getStackOwner());
      }
      
      return result;
   }

   public OpenStackSet hasStackOwner(Object value)
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
      
      OpenStackSet answer = new OpenStackSet();
      
      for (OpenStack obj : this)
      {
         if (neighbors.contains(obj.getStackOwner()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public OpenStackSet withStackOwner(MauMau value)
   {
      for (OpenStack obj : this)
      {
         obj.withStackOwner(value);
      }
      
      return this;
   }

   public MauMauSet getGame()
   {
      MauMauSet result = new MauMauSet();
      
      for (OpenStack obj : this)
      {
         result.add(obj.getGame());
      }
      
      return result;
   }

   public OpenStackSet hasGame(Object value)
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
      
      OpenStackSet answer = new OpenStackSet();
      
      for (OpenStack obj : this)
      {
         if (neighbors.contains(obj.getGame()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public OpenStackSet withGame(MauMau value)
   {
      for (OpenStack obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }



   public OpenStackPO filterOpenStackPO()
   {
      return new OpenStackPO(this.toArray(new OpenStack[this.size()]));
   }

   public OpenStackSet()
   {
      // empty
   }

   public OpenStackSet(OpenStack... objects)
   {
      for (OpenStack obj : objects)
      {
         this.add(obj);
      }
   }

   public OpenStackSet(Collection<OpenStack> objects)
   {
      this.addAll(objects);
   }


   public OpenStackPO createOpenStackPO()
   {
      return new OpenStackPO(this.toArray(new OpenStack[this.size()]));
   }


   @Override
   public OpenStackSet getNewList(boolean keyValue)
   {
      return new OpenStackSet();
   }


   public OpenStackSet filter(Condition<OpenStack> condition) {
      OpenStackSet filterList = new OpenStackSet();
      filterItems(filterList, condition);
      return filterList;
   }}
