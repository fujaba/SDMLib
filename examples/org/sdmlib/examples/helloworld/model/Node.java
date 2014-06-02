/*
   Copyright (c) 2014 Stefan 
   
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
   
package org.sdmlib.examples.helloworld.model;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.examples.helloworld.model.util.NodeSet;
import org.sdmlib.examples.helloworld.model.util.EdgeSet;
import java.util.LinkedHashSet;
import org.sdmlib.examples.helloworld.model.GraphComponent;

public class Node extends GraphComponent implements PropertyChangeInterface
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
      setCopy(null);
      setOrig(null);
      setGraph(null);
      removeAllFromOutEdges();
      removeAllFromInEdges();
      setParent(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Node ----------------------------------- Node
    *              orig                   copy
    * </pre>
    */
   
   public static final String PROPERTY_COPY = "copy";

   private Node copy = null;

   public Node getCopy()
   {
      return this.copy;
   }
   public NodeSet getCopyTransitive()
   {
      NodeSet result = new NodeSet().with(this);
      return result.getCopyTransitive();
   }


   public boolean setCopy(Node value)
   {
      boolean changed = false;
      
      if (this.copy != value)
      {
         Node oldValue = this.copy;
         
         if (this.copy != null)
         {
            this.copy = null;
            oldValue.setOrig(null);
         }
         
         this.copy = value;
         
         if (value != null)
         {
            value.withOrig(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_COPY, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Node withCopy(Node value)
   {
      setCopy(value);
      return this;
   } 

   public Node createCopy()
   {
      Node value = new Node();
      withCopy(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Node ----------------------------------- Node
    *              copy                   orig
    * </pre>
    */
   
   public static final String PROPERTY_ORIG = "orig";

   private Node orig = null;

   public Node getOrig()
   {
      return this.orig;
   }
   public NodeSet getOrigTransitive()
   {
      NodeSet result = new NodeSet().with(this);
      return result.getOrigTransitive();
   }


   public boolean setOrig(Node value)
   {
      boolean changed = false;
      
      if (this.orig != value)
      {
         Node oldValue = this.orig;
         
         if (this.orig != null)
         {
            this.orig = null;
            oldValue.setCopy(null);
         }
         
         this.orig = value;
         
         if (value != null)
         {
            value.withCopy(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ORIG, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Node withOrig(Node value)
   {
      setOrig(value);
      return this;
   } 

   public Node createOrig()
   {
      Node value = new Node();
      withOrig(value);
      return value;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;

   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      if (this.name != value)
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public Node withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      _.append(" ").append(this.getText());
      return _.substring(1);
   }


   
   public static final NodeSet EMPTY_SET = new NodeSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Node ----------------------------------- Graph
    *              nodes                   graph
    * </pre>
    */
   
   public static final String PROPERTY_GRAPH = "graph";

   private Graph graph = null;

   public Graph getGraph()
   {
      return this.graph;
   }

   public boolean setGraph(Graph value)
   {
      boolean changed = false;
      
      if (this.graph != value)
      {
         Graph oldValue = this.graph;
         
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

   public Node withGraph(Graph value)
   {
      setGraph(value);
      return this;
   } 

   public Graph createGraph()
   {
      Graph value = new Graph();
      withGraph(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Node ----------------------------------- Edge
    *              src                   outEdges
    * </pre>
    */
   
   public static final String PROPERTY_OUTEDGES = "outEdges";

   private EdgeSet outEdges = null;
   
   public EdgeSet getOutEdges()
   {
      if (this.outEdges == null)
      {
         return Edge.EMPTY_SET;
      }
   
      return this.outEdges;
   }

   public boolean addToOutEdges(Edge value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.outEdges == null)
         {
            this.outEdges = new EdgeSet();
         }
         
         changed = this.outEdges.add (value);
         
         if (changed)
         {
            value.withSrc(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OUTEDGES, null, value);
         }
      }
         
      return changed;   
   }

   public boolean removeFromOutEdges(Edge value)
   {
      boolean changed = false;
      
      if ((this.outEdges != null) && (value != null))
      {
         changed = this.outEdges.remove (value);
         
         if (changed)
         {
            value.setSrc(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OUTEDGES, value, null);
         }
      }
         
      return changed;   
   }

   public Node withOutEdges(Edge... value)
   {
      for (Edge item : value)
      {
         addToOutEdges(item);
      }
      return this;
   } 

   public Node withoutOutEdges(Edge... value)
   {
      for (Edge item : value)
      {
         removeFromOutEdges(item);
      }
      return this;
   }

   public void removeAllFromOutEdges()
   {
      LinkedHashSet<Edge> tmpSet = new LinkedHashSet<Edge>(this.getOutEdges());
   
      for (Edge value : tmpSet)
      {
         this.removeFromOutEdges(value);
      }
   }

   public Edge createOutEdges()
   {
      Edge value = new Edge();
      withOutEdges(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Node ----------------------------------- Edge
    *              tgt                   inEdges
    * </pre>
    */
   
   public static final String PROPERTY_INEDGES = "inEdges";

   private EdgeSet inEdges = null;
   
   public EdgeSet getInEdges()
   {
      if (this.inEdges == null)
      {
         return Edge.EMPTY_SET;
      }
   
      return this.inEdges;
   }

   public boolean addToInEdges(Edge value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.inEdges == null)
         {
            this.inEdges = new EdgeSet();
         }
         
         changed = this.inEdges.add (value);
         
         if (changed)
         {
            value.withTgt(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_INEDGES, null, value);
         }
      }
         
      return changed;   
   }

   public boolean removeFromInEdges(Edge value)
   {
      boolean changed = false;
      
      if ((this.inEdges != null) && (value != null))
      {
         changed = this.inEdges.remove (value);
         
         if (changed)
         {
            value.setTgt(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_INEDGES, value, null);
         }
      }
         
      return changed;   
   }

   public Node withInEdges(Edge... value)
   {
      for (Edge item : value)
      {
         addToInEdges(item);
      }
      return this;
   } 

   public Node withoutInEdges(Edge... value)
   {
      for (Edge item : value)
      {
         removeFromInEdges(item);
      }
      return this;
   }

   public void removeAllFromInEdges()
   {
      LinkedHashSet<Edge> tmpSet = new LinkedHashSet<Edge>(this.getInEdges());
   
      for (Edge value : tmpSet)
      {
         this.removeFromInEdges(value);
      }
   }

   public Edge createInEdges()
   {
      Edge value = new Edge();
      withInEdges(value);
      return value;
   } 
}

