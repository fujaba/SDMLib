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

import org.sdmlib.test.examples.gofpattern.strategy.Blast;
import org.sdmlib.test.examples.gofpattern.strategy.BombermanStrategy;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class BlastSet extends SimpleSet<Blast>
{

   public static final BlastSet EMPTY_SET = new BlastSet().withFlag(BlastSet.READONLY);


   public BlastPO hasBlastPO()
   {
      return new BlastPO(this.toArray(new Blast[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.gofpattern.strategy.Blast";
   }


   @SuppressWarnings("unchecked")
   public BlastSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Blast>)value);
      }
      else if (value != null)
      {
         this.add((Blast) value);
      }
      
      return this;
   }
   
   public BlastSet without(Blast value)
   {
      this.remove(value);
      return this;
   }

   public BombermanStrategySet getSuccessor()
   {
      BombermanStrategySet result = new BombermanStrategySet();
      
      for (Blast obj : this)
      {
         result.add(obj.getSuccessor());
      }
      
      return result;
   }

   public BlastSet hasSuccessor(Object value)
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
      
      BlastSet answer = new BlastSet();
      
      for (Blast obj : this)
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

   public BlastSet withSuccessor(BombermanStrategy value)
   {
      for (Blast obj : this)
      {
         obj.withSuccessor(value);
      }
      
      return this;
   }



   public BlastPO filterBlastPO()
   {
      return new BlastPO(this.toArray(new Blast[this.size()]));
   }

   public BlastSet()
   {
      // empty
   }

   public BlastSet(Blast... objects)
   {
      for (Blast obj : objects)
      {
         this.add(obj);
      }
   }

   public BlastSet(Collection<Blast> objects)
   {
      this.addAll(objects);
   }


   public BlastPO createBlastPO()
   {
      return new BlastPO(this.toArray(new Blast[this.size()]));
   }


   @Override
   public BlastSet getNewList(boolean keyValue)
   {
      return new BlastSet();
   }
}