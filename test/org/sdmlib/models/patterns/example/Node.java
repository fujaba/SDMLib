/*
   Copyright (c) 2013 zuendorf 
   
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

package org.sdmlib.models.patterns.example;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.models.patterns.example.creators.NodeSet;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;
import java.beans.PropertyChangeListener;

public class Node implements PropertyChangeInterface
{

   // ==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_NUM.equalsIgnoreCase(attrName))
      {
         return getNum();
      }

      if (PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         return getGraph();
      }

      if (PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         return getNext();
      }

      if (PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         return getPrev();
      }

      return null;
   }

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_NUM.equalsIgnoreCase(attrName))
      {
         setNum(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         setGraph((SimpleState) value);
         return true;
      }

      if (PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         addToNext((Node) value);
         return true;
      }

      if ((PROPERTY_NEXT + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromNext((Node) value);
         return true;
      }

      if (PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         addToPrev((Node) value);
         return true;
      }

      if ((PROPERTY_PREV + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromPrev((Node) value);
         return true;
      }

      return false;
   }

   // ==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   // ==========================================================================

   public void removeYou()
   {
      setGraph(null);
      removeAllFromNext();
      removeAllFromPrev();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   // ==========================================================================

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
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NUM, oldValue,
            value);
      }
   }

   public Node withNum(int value)
   {
      setNum(value);
      return this;
   }

   public String toString()
   {
      StringBuilder _ = new StringBuilder();

      _.append(" ").append(this.getNum());
      return _.substring(1);
   }

   public static final NodeSet EMPTY_SET = new NodeSet();

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

         getPropertyChangeSupport().firePropertyChange(PROPERTY_GRAPH,
            oldValue, value);
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

   public boolean addToNext(Node value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.next == null)
         {
            this.next = new NodeSet();
         }

         changed = this.next.add(value);

         if (changed)
         {
            value.withPrev(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_NEXT, null,
               value);
         }
      }

      return changed;
   }

   public boolean removeFromNext(Node value)
   {
      boolean changed = false;

      if ((this.next != null) && (value != null))
      {
         changed = this.next.remove(value);

         if (changed)
         {
            value.withoutPrev(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_NEXT, value,
               null);
         }
      }

      return changed;
   }

   public Node withNext(Node value)
   {
      addToNext(value);
      return this;
   }

   public Node withoutNext(Node value)
   {
      removeFromNext(value);
      return this;
   }

   public void removeAllFromNext()
   {
      LinkedHashSet<Node> tmpSet = new LinkedHashSet<Node>(this.getNext());

      for (Node value : tmpSet)
      {
         this.removeFromNext(value);
      }
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

   public boolean addToPrev(Node value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.prev == null)
         {
            this.prev = new NodeSet();
         }

         changed = this.prev.add(value);

         if (changed)
         {
            value.withNext(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PREV, null,
               value);
         }
      }

      return changed;
   }

   public boolean removeFromPrev(Node value)
   {
      boolean changed = false;

      if ((this.prev != null) && (value != null))
      {
         changed = this.prev.remove(value);

         if (changed)
         {
            value.withoutNext(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PREV, value,
               null);
         }
      }

      return changed;
   }

   public Node withPrev(Node value)
   {
      addToPrev(value);
      return this;
   }

   public Node withoutPrev(Node value)
   {
      removeFromPrev(value);
      return this;
   }

   public void removeAllFromPrev()
   {
      LinkedHashSet<Node> tmpSet = new LinkedHashSet<Node>(this.getPrev());

      for (Node value : tmpSet)
      {
         this.removeFromPrev(value);
      }
   }

   public Node createPrev()
   {
      Node value = new Node();
      withPrev(value);
      return value;
   }

   public Node withNext(Node... value)
   {
      for (Node item : value)
      {
         addToNext(item);
      }
      return this;
   }

   public Node withoutNext(Node... value)
   {
      for (Node item : value)
      {
         removeFromNext(item);
      }
      return this;
   }

   public Node withPrev(Node... value)
   {
      for (Node item : value)
      {
         addToPrev(item);
      }
      return this;
   }

   public Node withoutPrev(Node... value)
   {
      for (Node item : value)
      {
         removeFromPrev(item);
      }
      return this;
   }

   public NodeSet getNextTransitive()
   {
      NodeSet result = new NodeSet().with(this);
      return result.getNextTransitive();
   }

   public NodeSet getPrevTransitive()
   {
      NodeSet result = new NodeSet().with(this);
      return result.getPrevTransitive();
   }

}

