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
import org.sdmlib.utils.StrUtil;
import org.sdmlib.examples.helloworld.creators.NodeSet;
import org.sdmlib.examples.helloworld.creators.EdgeSet;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;

public class Node implements PropertyChangeInterface
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

      if (PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return getName();
      }

      if (PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         return getGraph();
      }

      if (PROPERTY_OUTEDGES.equalsIgnoreCase(attrName))
      {
         return getOutEdges();
      }

      if (PROPERTY_INEDGES.equalsIgnoreCase(attrName))
      {
         return getInEdges();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         setGraph((Graph) value);
         return true;
      }

      if (PROPERTY_OUTEDGES.equalsIgnoreCase(attrName))
      {
         addToOutEdges((Edge) value);
         return true;
      }
      
      if ((PROPERTY_OUTEDGES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromOutEdges((Edge) value);
         return true;
      }

      if (PROPERTY_INEDGES.equalsIgnoreCase(attrName))
      {
         addToInEdges((Edge) value);
         return true;
      }
      
      if ((PROPERTY_INEDGES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromInEdges((Edge) value);
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
      setGraph(null);
      removeAllFromOutEdges();
      removeAllFromInEdges();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
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
      if ( ! StrUtil.stringEquals(this.name, value))
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
   
   public Node withOutEdges(Edge value)
   {
      addToOutEdges(value);
      return this;
   } 
   
   public Node withoutOutEdges(Edge value)
   {
      removeFromOutEdges(value);
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
   
   public Node withInEdges(Edge value)
   {
      addToInEdges(value);
      return this;
   } 
   
   public Node withoutInEdges(Edge value)
   {
      removeFromInEdges(value);
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

