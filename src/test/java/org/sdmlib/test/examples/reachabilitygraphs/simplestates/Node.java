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
   
package org.sdmlib.test.examples.reachabilitygraphs.simplestates;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.NodeSet;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.SimpleStateSet;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.SimpleState;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphExampleModels.java'>ReachabilityGraphExampleModels.java</a>
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#SimpleReachabilityGraphModel
 */
   public  class Node implements SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = null;
   
   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(listener);
   	return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(propertyName, listener);
   	return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners.removePropertyChangeListener(listener);
   	}
   	listeners.removePropertyChangeListener(listener);
   	return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(propertyName, listener);
   	}
   	return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      withoutPrev(this.getPrev().toArray(new Node[this.getPrev().size()]));
      withoutNext(this.getNext().toArray(new Node[this.getNext().size()]));
      withoutGraph(this.getGraph().toArray(new SimpleState[this.getGraph().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NUM = "num";
   
   private int num;

   public int getNum()
   {
      return this.num;
   }
   
   public void setNum(int value)
   {
      if (this.num != value) {
      
         int oldValue = this.num;
         this.num = value;
         this.firePropertyChange(PROPERTY_NUM, oldValue, value);
      }
   }
   
     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachbilityGraphSimpleExamples.java'>ReachbilityGraphSimpleExamples.java</a>
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachbilityGraphSimpleExamples#ReachabilityGraphSimpleIsomorphismTest
 */
   public Node withNum(int value)
   {
      setNum(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getNum());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              many                       many
    * Node ----------------------------------- Node
    *              next                   prev
    * </pre>
    */
   
   public static final String PROPERTY_PREV = "prev";

   private NodeSet prev = null;
   
   public NodeSet getPrev()
   {
      if (this.prev == null)
      {
         return NodeSet.EMPTY_SET;
      }
   
      return this.prev;
   }
   public NodeSet getPrevTransitive()
   {
      NodeSet result = new NodeSet().with(this);
      return result.getPrevTransitive();
   }


   public Node withPrev(Node... value)
   {
      if(value==null){
         return this;
      }
      for (Node item : value)
      {
         if (item != null)
         {
            if (this.prev == null)
            {
               this.prev = new NodeSet();
            }
            
            boolean changed = this.prev.add (item);

            if (changed)
            {
               item.withNext(this);
               firePropertyChange(PROPERTY_PREV, null, item);
            }
         }
      }
      return this;
   } 

   public Node withoutPrev(Node... value)
   {
      for (Node item : value)
      {
         if ((this.prev != null) && (item != null))
         {
            if (this.prev.remove(item))
            {
               item.withoutNext(this);
               firePropertyChange(PROPERTY_PREV, item, null);
            }
         }
      }
      return this;
   }

   public Node createPrev()
   {
      Node value = new Node();
      withPrev(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Node ----------------------------------- Node
    *              prev                   next
    * </pre>
    */
   
   public static final String PROPERTY_NEXT = "next";

   private NodeSet next = null;
   
   public NodeSet getNext()
   {
      if (this.next == null)
      {
         return NodeSet.EMPTY_SET;
      }
   
      return this.next;
   }
   public NodeSet getNextTransitive()
   {
      NodeSet result = new NodeSet().with(this);
      return result.getNextTransitive();
   }


     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachbilityGraphSimpleExamples.java'>ReachbilityGraphSimpleExamples.java</a>
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachbilityGraphSimpleExamples#LazyReachabilityGraphAttrsAndNodes
 */
   public Node withNext(Node... value)
   {
      if(value==null){
         return this;
      }
      for (Node item : value)
      {
         if (item != null)
         {
            if (this.next == null)
            {
               this.next = new NodeSet();
            }
            
            boolean changed = this.next.add (item);

            if (changed)
            {
               item.withPrev(this);
               firePropertyChange(PROPERTY_NEXT, null, item);
            }
         }
      }
      return this;
   } 

   public Node withoutNext(Node... value)
   {
      for (Node item : value)
      {
         if ((this.next != null) && (item != null))
         {
            if (this.next.remove(item))
            {
               item.withoutPrev(this);
               firePropertyChange(PROPERTY_NEXT, item, null);
            }
         }
      }
      return this;
   }

   public Node createNext()
   {
      Node value = new Node();
      withNext(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Node ----------------------------------- SimpleState
    *              nodes                   graph
    * </pre>
    */
   
   public static final String PROPERTY_GRAPH = "graph";

   private SimpleStateSet graph = null;
   
   public SimpleStateSet getGraph()
   {
      if (this.graph == null)
      {
         return SimpleStateSet.EMPTY_SET;
      }
   
      return this.graph;
   }

   public Node withGraph(SimpleState... value)
   {
      if(value==null){
         return this;
      }
      for (SimpleState item : value)
      {
         if (item != null)
         {
            if (this.graph == null)
            {
               this.graph = new SimpleStateSet();
            }
            
            boolean changed = this.graph.add (item);

            if (changed)
            {
               item.withNodes(this);
               firePropertyChange(PROPERTY_GRAPH, null, item);
            }
         }
      }
      return this;
   } 

   public Node withoutGraph(SimpleState... value)
   {
      for (SimpleState item : value)
      {
         if ((this.graph != null) && (item != null))
         {
            if (this.graph.remove(item))
            {
               item.withoutNodes(this);
               firePropertyChange(PROPERTY_GRAPH, item, null);
            }
         }
      }
      return this;
   }

   public SimpleState createGraph()
   {
      SimpleState value = new SimpleState();
      withGraph(value);
      return value;
   } 
}
