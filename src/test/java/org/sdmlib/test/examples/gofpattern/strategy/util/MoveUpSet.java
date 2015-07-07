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

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.test.examples.gofpattern.strategy.BombermanStrategy;
import org.sdmlib.test.examples.gofpattern.strategy.MoveUp;
import org.sdmlib.test.examples.gofpattern.strategy.util.BombermanStrategySet;

import java.util.Collection;
import org.sdmlib.models.modelsets.ObjectSet;

public class MoveUpSet extends SDMSet<MoveUp>
{

   public static final MoveUpSet EMPTY_SET = new MoveUpSet().withReadOnly(true);


   public MoveUpPO hasMoveUpPO()
   {
      return new MoveUpPO(this.toArray(new MoveUp[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.gofpattern.strategy.MoveUp";
   }


   @SuppressWarnings("unchecked")
   public MoveUpSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<MoveUp>)value);
      }
      else if (value != null)
      {
         this.add((MoveUp) value);
      }
      
      return this;
   }
   
   public MoveUpSet without(MoveUp value)
   {
      this.remove(value);
      return this;
   }

   public BombermanStrategySet getSuccessor()
   {
      BombermanStrategySet result = new BombermanStrategySet();
      
      for (MoveUp obj : this)
      {
         result.add(obj.getSuccessor());
      }
      
      return result;
   }

   public MoveUpSet hasSuccessor(Object value)
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
      
      MoveUpSet answer = new MoveUpSet();
      
      for (MoveUp obj : this)
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

   public MoveUpSet withSuccessor(BombermanStrategy value)
   {
      for (MoveUp obj : this)
      {
         obj.withSuccessor(value);
      }
      
      return this;
   }

}
