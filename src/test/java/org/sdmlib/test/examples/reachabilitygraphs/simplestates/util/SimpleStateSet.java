/*
   Copyright (c) 2017 zuendorf
   
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
   
package org.sdmlib.test.examples.reachabilitygraphs.simplestates.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.test.examples.reachabilitygraphs.simplestates.Node;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.SimpleState;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class SimpleStateSet extends SimpleSet<SimpleState>
{
	public Class<?> getTypClass() {
		return SimpleState.class;
	}

   public SimpleStateSet()
   {
      // empty
   }

   public SimpleStateSet(SimpleState... objects)
   {
      for (SimpleState obj : objects)
      {
         this.add(obj);
      }
   }

   public SimpleStateSet(Collection<SimpleState> objects)
   {
      this.addAll(objects);
   }

   public static final SimpleStateSet EMPTY_SET = new SimpleStateSet().withFlag(SimpleStateSet.READONLY);


   public SimpleStatePO createSimpleStatePO()
   {
      return new SimpleStatePO(this.toArray(new SimpleState[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.reachabilitygraphs.simplestates.SimpleState";
   }


   @Override
   public SimpleStateSet getNewList(boolean keyValue)
   {
      return new SimpleStateSet();
   }

   @SuppressWarnings("unchecked")
   public SimpleStateSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
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

   /**
    * Loop through the current set of SimpleState objects and collect a set of the Node objects reached via nodes. 
    * 
    * @return Set of Node objects reachable via nodes
    */
   public NodeSet getNodes()
   {
      NodeSet result = new NodeSet();
      
      for (SimpleState obj : this)
      {
         result.with(obj.getNodes());
      }
      
      return result;
   }

   /**
    * Loop through the current set of SimpleState objects and collect all contained objects with reference nodes pointing to the object passed as parameter. 
    * 
    * @param value The object required as nodes neighbor of the collected results. 
    * 
    * @return Set of Node objects referring to value via nodes
    */
   public SimpleStateSet filterNodes(Object value)
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

   /**
    * Loop through current set of ModelType objects and attach the SimpleState object passed as parameter to the Nodes attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Nodes attributes.
    */
   public SimpleStateSet withNodes(Node value)
   {
      for (SimpleState obj : this)
      {
         obj.withNodes(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the SimpleState object passed as parameter from the Nodes attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public SimpleStateSet withoutNodes(Node value)
   {
      for (SimpleState obj : this)
      {
         obj.withoutNodes(value);
      }
      
      return this;
   }

}
