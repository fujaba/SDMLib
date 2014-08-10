/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.examples.reachabilitygraphs.simplestates.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.reachabilitygraphs.simplestates.SimpleState;
import java.util.Collection;
import org.sdmlib.models.modelsets.ObjectSet;
import java.util.Collections;
import org.sdmlib.examples.reachabilitygraphs.simplestates.util.NodeSet;
import org.sdmlib.examples.reachabilitygraphs.simplestates.Node;

public class SimpleStateSet extends SDMSet<SimpleState>
{


   public SimpleStatePO hasSimpleStatePO()
   {
      return new SimpleStatePO(this.toArray(new SimpleState[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.reachabilitygraphs.simplestates.SimpleState";
   }


   @SuppressWarnings("unchecked")
   public SimpleStateSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<SimpleState>)value);
      }
      else if (value != null)
      {
         this.add((SimpleState) value);
      }
      
      return this;
   }
   
   public SimpleStateSet without(SimpleState value)
   {
      this.remove(value);
      return this;
   }

   public NodeSet getNodes()
   {
      NodeSet result = new NodeSet();
      
      for (SimpleState obj : this)
      {
         result.addAll(obj.getNodes());
      }
      
      return result;
   }

   public SimpleStateSet hasNodes(Object value)
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
      
      SimpleStateSet answer = new SimpleStateSet();
      
      for (SimpleState obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getNodes()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public SimpleStateSet withNodes(Node value)
   {
      for (SimpleState obj : this)
      {
         obj.withNodes(value);
      }
      
      return this;
   }

   public SimpleStateSet withoutNodes(Node value)
   {
      for (SimpleState obj : this)
      {
         obj.withoutNodes(value);
      }
      
      return this;
   }

}
