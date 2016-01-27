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
   
package org.sdmlib.test.examples.reachabilitygraphs.simplestates;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.NodeSet;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphExampleModels.java'>ReachabilityGraphExampleModels.java</a>
*/
   public class Node implements PropertyChangeInterface
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      setGraph(null);
      withoutNext(this.getNext().toArray(new Node[this.getNext().size()]));
      withoutPrev(this.getPrev().toArray(new Node[this.getPrev().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
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
      if (this.num != value)
      {
         int oldValue = this.num;
         this.num = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NUM, oldValue, value);
      }
   }
   
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


   
   public static final NodeSet EMPTY_SET = new NodeSet().withFlag(NodeSet.READONLY);

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Node ----------------------------------- SimpleState
    *              nodes                   graph
    * </pre>
    */
   
   public static final String PROPERTY_GRAPH = "graph";

   private SimpleState graph = null;

   public SimpleState getGraph()
   {
      return this.graph;
   }

   public boolean setGraph(SimpleState value)
   {
      boolean changed = false;
      
      if (this.graph != value)
      {
         SimpleState oldValue = this.graph;
         
         if (this.graph != null)
         {
            this.graph = null;
            oldValue.withoutNodes(this);
         }
         
         this.graph = value;
         
         if (value != null)
         {
            value.withNodes(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GRAPH, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Node withGraph(SimpleState value)
   {
      setGraph(value);
      return this;
   } 

   public SimpleState createGraph()
   {
      SimpleState value = new SimpleState();
      withGraph(value);
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
         return Node.EMPTY_SET;
      }
   
      return this.next;
   }
   public NodeSet getNextTransitive()
   {
      NodeSet result = new NodeSet().with(this);
      return result.getNextTransitive();
   }


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
               getPropertyChangeSupport().firePropertyChange(PROPERTY_NEXT, null, item);
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
               getPropertyChangeSupport().firePropertyChange(PROPERTY_NEXT, item, null);
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
         return Node.EMPTY_SET;
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
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PREV, null, item);
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
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PREV, item, null);
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
}
