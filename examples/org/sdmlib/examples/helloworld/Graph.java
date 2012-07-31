/*
   Copyright (c) 2012 zuendorf 
   
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
   
package org.sdmlib.examples.helloworld;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.examples.helloworld.creators.NodeSet;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.helloworld.creators.EdgeSet;

public class Graph implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_NODES.equalsIgnoreCase(attrName))
      {
         return getNodes();
      }

      if (PROPERTY_EDGES.equalsIgnoreCase(attrName))
      {
         return getEdges();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_NODES.equalsIgnoreCase(attrName))
      {
         addToNodes((Node) value);
         return true;
      }
      
      if ((PROPERTY_NODES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromNodes((Node) value);
         return true;
      }

      if (PROPERTY_EDGES.equalsIgnoreCase(attrName))
      {
         addToEdges((Edge) value);
         return true;
      }
      
      if ((PROPERTY_EDGES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromEdges((Edge) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      removeAllFromNodes();
      removeAllFromEdges();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Graph ----------------------------------- Node
    *              graph                   nodes
    * </pre>
    */
   
   public static final String PROPERTY_NODES = "nodes";
   
   private NodeSet nodes = null;
   
   public NodeSet getNodes()
   {
      if (this.nodes == null)
      {
         return Node.EMPTY_SET;
      }
   
      return this.nodes;
   }
   
   public boolean addToNodes(Node value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.nodes == null)
         {
            this.nodes = new NodeSet();
         }
         
         changed = this.nodes.add (value);
         
         if (changed)
         {
            value.withGraph(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_NODES, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromNodes(Node value)
   {
      boolean changed = false;
      
      if ((this.nodes != null) && (value != null))
      {
         changed = this.nodes.remove (value);
         
         if (changed)
         {
            value.setGraph(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_NODES, value, null);
         }
      }
         
      return changed;   
   }
   
   public Graph withNodes(Node value)
   {
      addToNodes(value);
      return this;
   } 
   
   public Graph withoutNodes(Node value)
   {
      removeFromNodes(value);
      return this;
   } 
   
   public void removeAllFromNodes()
   {
      LinkedHashSet<Node> tmpSet = new LinkedHashSet<Node>(this.getNodes());
   
      for (Node value : tmpSet)
      {
         this.removeFromNodes(value);
      }
   }
   
   public Node createNodes()
   {
      Node value = new Node();
      withNodes(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Graph ----------------------------------- Edge
    *              graph                   edges
    * </pre>
    */
   
   public static final String PROPERTY_EDGES = "edges";
   
   private EdgeSet edges = null;
   
   public EdgeSet getEdges()
   {
      if (this.edges == null)
      {
         return Edge.EMPTY_SET;
      }
   
      return this.edges;
   }
   
   public boolean addToEdges(Edge value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.edges == null)
         {
            this.edges = new EdgeSet();
         }
         
         changed = this.edges.add (value);
         
         if (changed)
         {
            value.withGraph(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_EDGES, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromEdges(Edge value)
   {
      boolean changed = false;
      
      if ((this.edges != null) && (value != null))
      {
         changed = this.edges.remove (value);
         
         if (changed)
         {
            value.setGraph(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_EDGES, value, null);
         }
      }
         
      return changed;   
   }
   
   public Graph withEdges(Edge value)
   {
      addToEdges(value);
      return this;
   } 
   
   public Graph withoutEdges(Edge value)
   {
      removeFromEdges(value);
      return this;
   } 
   
   public void removeAllFromEdges()
   {
      LinkedHashSet<Edge> tmpSet = new LinkedHashSet<Edge>(this.getEdges());
   
      for (Edge value : tmpSet)
      {
         this.removeFromEdges(value);
      }
   }
   
   public Edge createEdges()
   {
      Edge value = new Edge();
      withEdges(value);
      return value;
   } 
}

