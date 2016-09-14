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

import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.DrawingStack;
import org.sdmlib.test.examples.maumau.model.MauMau;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.maumau.model.util.MauMauSet;
import org.sdmlib.test.examples.maumau.model.util.CardSet;

public class DrawingStackSet extends SimpleSet<DrawingStack>
{

   public static final DrawingStackSet EMPTY_SET = new DrawingStackSet().withFlag(DrawingStackSet.READONLY);


   public DrawingStackPO hasDrawingStackPO()
   {
      return new DrawingStackPO(this.toArray(new DrawingStack[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.maumau.model.DrawingStack";
   }


   @SuppressWarnings("unchecked")
   public DrawingStackSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<DrawingStack>)value);
      }
      else if (value != null)
      {
         this.add((DrawingStack) value);
      }
      
      return this;
   }
   
   public DrawingStackSet without(DrawingStack value)
   {
      this.remove(value);
      return this;
   }

   public CardSet getCards()
   {
      CardSet result = new CardSet();
      
      for (DrawingStack obj : this)
      {
         result.addAll(obj.getCards());
      }
      
      return result;
   }

   public DrawingStackSet hasCards(Object value)
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
      
      DrawingStackSet answer = new DrawingStackSet();
      
      for (DrawingStack obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getCards()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public DrawingStackSet withCards(Card value)
   {
      for (DrawingStack obj : this)
      {
         obj.withCards(value);
      }
      
      return this;
   }

   public DrawingStackSet withoutCards(Card value)
   {
      for (DrawingStack obj : this)
      {
         obj.withoutCards(value);
      }
      
      return this;
   }

   public MauMauSet getDeckOwner()
   {
      MauMauSet result = new MauMauSet();
      
      for (DrawingStack obj : this)
      {
         result.add(obj.getDeckOwner());
      }
      
      return result;
   }

   public DrawingStackSet hasDeckOwner(Object value)
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
      
      DrawingStackSet answer = new DrawingStackSet();
      
      for (DrawingStack obj : this)
      {
         if (neighbors.contains(obj.getDeckOwner()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public DrawingStackSet withDeckOwner(MauMau value)
   {
      for (DrawingStack obj : this)
      {
         obj.withDeckOwner(value);
      }
      
      return this;
   }

   public MauMauSet getStackOwner()
   {
      MauMauSet result = new MauMauSet();
      
      for (DrawingStack obj : this)
      {
         result.add(obj.getStackOwner());
      }
      
      return result;
   }

   public DrawingStackSet hasStackOwner(Object value)
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
      
      DrawingStackSet answer = new DrawingStackSet();
      
      for (DrawingStack obj : this)
      {
         if (neighbors.contains(obj.getStackOwner()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public DrawingStackSet withStackOwner(MauMau value)
   {
      for (DrawingStack obj : this)
      {
         obj.withStackOwner(value);
      }
      
      return this;
   }

   public MauMauSet getGame()
   {
      MauMauSet result = new MauMauSet();
      
      for (DrawingStack obj : this)
      {
         result.add(obj.getGame());
      }
      
      return result;
   }

   public DrawingStackSet hasGame(Object value)
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
      
      DrawingStackSet answer = new DrawingStackSet();
      
      for (DrawingStack obj : this)
      {
         if (neighbors.contains(obj.getGame()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public DrawingStackSet withGame(MauMau value)
   {
      for (DrawingStack obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }



   public DrawingStackPO filterDrawingStackPO()
   {
      return new DrawingStackPO(this.toArray(new DrawingStack[this.size()]));
   }

   public DrawingStackSet()
   {
      // empty
   }

   public DrawingStackSet(DrawingStack... objects)
   {
      for (DrawingStack obj : objects)
      {
         this.add(obj);
      }
   }

   public DrawingStackSet(Collection<DrawingStack> objects)
   {
      this.addAll(objects);
   }
}
