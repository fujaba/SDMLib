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
   
package org.sdmlib.examples.gofpattern.strategy.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.gofpattern.strategy.MoveDown;
import java.util.Collection;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.examples.gofpattern.strategy.util.BombermanStrategySet;
import org.sdmlib.examples.gofpattern.strategy.BombermanStrategy;

public class MoveDownSet extends SDMSet<MoveDown>
{

   public static final MoveDownSet EMPTY_SET = new MoveDownSet().withReadOnly(true);


   public MoveDownPO hasMoveDownPO()
   {
      return new MoveDownPO(this.toArray(new MoveDown[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.gofpattern.strategy.MoveDown";
   }


   @SuppressWarnings("unchecked")
   public MoveDownSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<MoveDown>)value);
      }
      else if (value != null)
      {
         this.add((MoveDown) value);
      }
      
      return this;
   }
   
   public MoveDownSet without(MoveDown value)
   {
      this.remove(value);
      return this;
   }

   public BombermanStrategySet getSuccessor()
   {
      BombermanStrategySet result = new BombermanStrategySet();
      
      for (MoveDown obj : this)
      {
         result.add(obj.getSuccessor());
      }
      
      return result;
   }

   public MoveDownSet hasSuccessor(Object value)
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
      
      MoveDownSet answer = new MoveDownSet();
      
      for (MoveDown obj : this)
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

   public MoveDownSet withSuccessor(BombermanStrategy value)
   {
      for (MoveDown obj : this)
      {
         obj.withSuccessor(value);
      }
      
      return this;
   }

}
