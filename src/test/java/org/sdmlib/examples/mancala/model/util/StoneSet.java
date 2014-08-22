/*
   Copyright (c) 2014 NeTH 
   
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
   
package org.sdmlib.examples.mancala.model.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.mancala.model.Stone;
import java.util.Collection;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.examples.mancala.model.util.PlayerSet;
import org.sdmlib.examples.mancala.model.Player;

public class StoneSet extends SDMSet<Stone>
{


   public StonePO hasStonePO()
   {
      return new StonePO(this.toArray(new Stone[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.mancala.model.Stone";
   }


   @SuppressWarnings("unchecked")
   public StoneSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Stone>)value);
      }
      else if (value != null)
      {
         this.add((Stone) value);
      }
      
      return this;
   }
   
   public StoneSet without(Stone value)
   {
      this.remove(value);
      return this;
   }

   public PlayerSet getPlayer()
   {
      PlayerSet result = new PlayerSet();
      
      for (Stone obj : this)
      {
         result.add(obj.getPlayer());
      }
      
      return result;
   }

   public StoneSet hasPlayer(Object value)
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
      
      StoneSet answer = new StoneSet();
      
      for (Stone obj : this)
      {
         if (neighbors.contains(obj.getPlayer()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public StoneSet withPlayer(Player value)
   {
      for (Stone obj : this)
      {
         obj.withPlayer(value);
      }
      
      return this;
   }

}
