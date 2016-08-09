/*
   Copyright (c) 2015 zuendorf
   
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
   
package org.sdmlib.test.examples.gofpattern.strategy.util;

import java.util.Collection;

import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.test.examples.gofpattern.strategy.BombermanStrategy;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.gofpattern.strategy.util.BombermanStrategySet;

public class BombermanStrategySet extends SimpleSet<BombermanStrategy>
{

   public static final BombermanStrategySet EMPTY_SET = new BombermanStrategySet().withFlag(BombermanStrategySet.READONLY);


   public BombermanStrategyPO hasBombermanStrategyPO()
   {
      return new BombermanStrategyPO(this.toArray(new BombermanStrategy[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.gofpattern.strategy.BombermanStrategy";
   }


   @SuppressWarnings("unchecked")
   public BombermanStrategySet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<BombermanStrategy>)value);
      }
      else if (value != null)
      {
         this.add((BombermanStrategy) value);
      }
      
      return this;
   }
   
   public BombermanStrategySet without(BombermanStrategy value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public BombermanStrategySet handleMove()
   {
      for (BombermanStrategy obj : this)
      {
         obj.handleMove();
      }
      return this;
   }

   public BombermanStrategySet getSuccessor()
   {
      BombermanStrategySet result = new BombermanStrategySet();
      
      for (BombermanStrategy obj : this)
      {
         result.add(obj.getSuccessor());
      }
      
      return result;
   }

   public BombermanStrategySet hasSuccessor(Object value)
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
      
      BombermanStrategySet answer = new BombermanStrategySet();
      
      for (BombermanStrategy obj : this)
      {
         if (neighbors.contains(obj.getSuccessor()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public BombermanStrategySet getSuccessorTransitive()
   {
      BombermanStrategySet todo = new BombermanStrategySet().with(this);
      
      BombermanStrategySet result = new BombermanStrategySet();
      
      while ( ! todo.isEmpty())
      {
         BombermanStrategy current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getSuccessor()))
            {
               todo.with(current.getSuccessor());
            }
         }
      }
      
      return result;
   }

   public BombermanStrategySet withSuccessor(BombermanStrategy value)
   {
      for (BombermanStrategy obj : this)
      {
         obj.withSuccessor(value);
      }
      
      return this;
   }



   public BombermanStrategyPO filterBombermanStrategyPO()
   {
      return new BombermanStrategyPO(this.toArray(new BombermanStrategy[this.size()]));
   }
   /**
    * Loop through the current set of BombermanStrategy objects and collect a set of the BombermanStrategy objects reached via bombermanstrategy. 
    * 
    * @return Set of BombermanStrategy objects reachable via bombermanstrategy
    */
   public BombermanStrategySet getBombermanstrategy()
   {
      BombermanStrategySet result = new BombermanStrategySet();
      
      for (BombermanStrategy obj : this)
      {
         result.with(obj.getBombermanstrategy());
      }
      
      return result;
   }

   /**
    * Loop through the current set of BombermanStrategy objects and collect all contained objects with reference bombermanstrategy pointing to the object passed as parameter. 
    * 
    * @param value The object required as bombermanstrategy neighbor of the collected results. 
    * 
    * @return Set of BombermanStrategy objects referring to value via bombermanstrategy
    */
   public BombermanStrategySet filterBombermanstrategy(Object value)
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
      
      BombermanStrategySet answer = new BombermanStrategySet();
      
      for (BombermanStrategy obj : this)
      {
         if (neighbors.contains(obj.getBombermanstrategy()) || (neighbors.isEmpty() && obj.getBombermanstrategy() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow bombermanstrategy reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of BombermanStrategy objects reachable via bombermanstrategy transitively (including the start set)
    */
   public BombermanStrategySet getBombermanstrategyTransitive()
   {
      BombermanStrategySet todo = new BombermanStrategySet().with(this);
      
      BombermanStrategySet result = new BombermanStrategySet();
      
      while ( ! todo.isEmpty())
      {
         BombermanStrategy current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getBombermanstrategy()))
            {
               todo.with(current.getBombermanstrategy());
            }
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the BombermanStrategy object passed as parameter to the Bombermanstrategy attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Bombermanstrategy attributes.
    */
   public BombermanStrategySet withBombermanstrategy(BombermanStrategy value)
   {
      for (BombermanStrategy obj : this)
      {
         obj.withBombermanstrategy(value);
      }
      
      return this;
   }


   public BombermanStrategySet()
   {
      // empty
   }

   public BombermanStrategySet(BombermanStrategy... objects)
   {
      for (BombermanStrategy obj : objects)
      {
         this.add(obj);
      }
   }

   public BombermanStrategySet(Collection<BombermanStrategy> objects)
   {
      this.addAll(objects);
   }
}
