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

import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class NodeSet extends SimpleSet<Node>
{
	public Class<?> getTypClass() {
		return Node.class;
	}

   public NodeSet()
   {
      // empty
   }

   public NodeSet(Node... objects)
   {
      for (Node obj : objects)
      {
         this.add(obj);
      }
   }

   public NodeSet(Collection<Node> objects)
   {
      this.addAll(objects);
   }

   public static final NodeSet EMPTY_SET = new NodeSet().withFlag(NodeSet.READONLY);


   public NodePO createNodePO()
   {
      return new NodePO(this.toArray(new Node[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.reachabilitygraphs.simplestates.Node";
   }


   @Override
   public NodeSet getNewList(boolean keyValue)
   {
      return new NodeSet();
   }

   @SuppressWarnings("unchecked")
   public NodeSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Node>)value);
      }
      else if (value != null)
      {
         this.add((Node) value);
      }
      
      return this;
   }
   
   public NodeSet without(Node value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Node objects and collect a list of the num attribute values. 
    * 
    * @return List of int objects reachable via num attribute
    */
   public NumberList getNum()
   {
      NumberList result = new NumberList();
      
      for (Node obj : this)
      {
         result.add(obj.getNum());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Node objects and collect those Node objects where the num attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Node objects that match the parameter
    */
   public NodeSet createNumCondition(int value)
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         if (value == obj.getNum())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Node objects and collect those Node objects where the num attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Node objects that match the parameter
    */
   public NodeSet createNumCondition(int lower, int upper)
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         if (lower <= obj.getNum() && obj.getNum() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Node objects and assign value to the num attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Node objects now with new attribute values.
    */
   public NodeSet withNum(int value)
   {
      for (Node obj : this)
      {
         obj.setNum(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Node objects and collect a set of the Node objects reached via prev. 
    * 
    * @return Set of Node objects reachable via prev
    */
   public NodeSet getPrev()
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         result.with(obj.getPrev());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Node objects and collect all contained objects with reference prev pointing to the object passed as parameter. 
    * 
    * @param value The object required as prev neighbor of the collected results. 
    * 
    * @return Set of Node objects referring to value via prev
    */
   public NodeSet filterPrev(Object value)
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
      
      NodeSet answer = new NodeSet();
      
      for (Node obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPrev()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow prev reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Node objects reachable via prev transitively (including the start set)
    */
   public NodeSet getPrevTransitive()
   {
      NodeSet todo = new NodeSet().with(this);
      
      NodeSet result = new NodeSet();
      
      while ( ! todo.isEmpty())
      {
         Node current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getPrev()).minus(result);
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Node object passed as parameter to the Prev attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Prev attributes.
    */
   public NodeSet withPrev(Node value)
   {
      for (Node obj : this)
      {
         obj.withPrev(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Node object passed as parameter from the Prev attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public NodeSet withoutPrev(Node value)
   {
      for (Node obj : this)
      {
         obj.withoutPrev(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Node objects and collect a set of the Node objects reached via next. 
    * 
    * @return Set of Node objects reachable via next
    */
   public NodeSet getNext()
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         result.with(obj.getNext());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Node objects and collect all contained objects with reference next pointing to the object passed as parameter. 
    * 
    * @param value The object required as next neighbor of the collected results. 
    * 
    * @return Set of Node objects referring to value via next
    */
   public NodeSet filterNext(Object value)
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
      
      NodeSet answer = new NodeSet();
      
      for (Node obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getNext()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow next reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Node objects reachable via next transitively (including the start set)
    */
   public NodeSet getNextTransitive()
   {
      NodeSet todo = new NodeSet().with(this);
      
      NodeSet result = new NodeSet();
      
      while ( ! todo.isEmpty())
      {
         Node current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getNext()).minus(result);
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Node object passed as parameter to the Next attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Next attributes.
    */
   public NodeSet withNext(Node value)
   {
      for (Node obj : this)
      {
         obj.withNext(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Node object passed as parameter from the Next attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public NodeSet withoutNext(Node value)
   {
      for (Node obj : this)
      {
         obj.withoutNext(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Node objects and collect a set of the SimpleState objects reached via graph. 
    * 
    * @return Set of SimpleState objects reachable via graph
    */
   public SimpleStateSet getGraph()
   {
      SimpleStateSet result = new SimpleStateSet();
      
      for (Node obj : this)
      {
         result.with(obj.getGraph());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Node objects and collect all contained objects with reference graph pointing to the object passed as parameter. 
    * 
    * @param value The object required as graph neighbor of the collected results. 
    * 
    * @return Set of SimpleState objects referring to value via graph
    */
   public NodeSet filterGraph(Object value)
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
      
      NodeSet answer = new NodeSet();
      
      for (Node obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getGraph()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Node object passed as parameter to the Graph attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Graph attributes.
    */
   public NodeSet withGraph(SimpleState value)
   {
      for (Node obj : this)
      {
         obj.withGraph(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Node object passed as parameter from the Graph attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public NodeSet withoutGraph(SimpleState value)
   {
      for (Node obj : this)
      {
         obj.withoutGraph(value);
      }
      
      return this;
   }

}
