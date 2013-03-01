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

import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.examples.helloworld.creators.EdgeSet;
import org.sdmlib.examples.helloworld.creators.NodeSet;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

public class Node extends GraphComponent implements PropertyChangeInterface
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

      if (PROPERTY_TEXT.equalsIgnoreCase(attribute))
      {
         return getText();
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return getParent();
      }

      if (PROPERTY_ORIG.equalsIgnoreCase(attrName))
      {
         return getOrig();
      }

      if (PROPERTY_COPY.equalsIgnoreCase(attrName))
      {
         return getCopy();
      }

      if (PROPERTY_LINKSTO.equalsIgnoreCase(attrName))
      {
         return getLinksTo();
      }

      if (PROPERTY_LINKSFROM.equalsIgnoreCase(attrName))
      {
         return getLinksFrom();
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

      if (PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         setText((String) value);
         return true;
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         setParent((Graph) value);
         return true;
      }

      if (PROPERTY_ORIG.equalsIgnoreCase(attrName))
      {
         setOrig((Node) value);
         return true;
      }

      if (PROPERTY_COPY.equalsIgnoreCase(attrName))
      {
         setCopy((Node) value);
         return true;
      }

      if (PROPERTY_LINKSTO.equalsIgnoreCase(attrName))
      {
         addToLinksTo((Node) value);
         return true;
      }
      
      if ((PROPERTY_LINKSTO + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromLinksTo((Node) value);
         return true;
      }

      if (PROPERTY_LINKSFROM.equalsIgnoreCase(attrName))
      {
         addToLinksFrom((Node) value);
         return true;
      }
      
      if ((PROPERTY_LINKSFROM + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromLinksFrom((Node) value);
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
      setOrig(null);
      setCopy(null);
      removeAllFromLinksTo();
      removeAllFromLinksFrom();
      setParent(null);
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

   
   //==========================================================================
   
   public static final String PROPERTY_TEXT = "text";
   
   private String text;

   public String getText()
   {
      return this.text;
   }
   
   public void setText(String value)
   {
      if ( ! StrUtil.stringEquals(this.text, value))
      {
         String oldValue = this.text;
         this.text = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TEXT, oldValue, value);
      }
   }
   
   public Node withText(String value)
   {
      setText(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Node ----------------------------------- Node
    *              linksFrom                   linksTo
    * </pre>
    */
   
   public static final String PROPERTY_LINKSTO = "linksTo";
   
   private NodeSet linksTo = null;
   
   public NodeSet getLinksTo()
   {
      if (this.linksTo == null)
      {
         return Node.EMPTY_SET;
      }
   
      return this.linksTo;
   }
   
   public boolean addToLinksTo(Node value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.linksTo == null)
         {
            this.linksTo = new NodeSet();
         }
         
         changed = this.linksTo.add (value);
         
         if (changed)
         {
            value.withLinksFrom(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LINKSTO, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromLinksTo(Node value)
   {
      boolean changed = false;
      
      if ((this.linksTo != null) && (value != null))
      {
         changed = this.linksTo.remove (value);
         
         if (changed)
         {
            value.withoutLinksFrom(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LINKSTO, value, null);
         }
      }
         
      return changed;   
   }
   
   public Node withLinksTo(Node value)
   {
      addToLinksTo(value);
      return this;
   } 
   
   public Node withoutLinksTo(Node value)
   {
      removeFromLinksTo(value);
      return this;
   } 
   
   public void removeAllFromLinksTo()
   {
      LinkedHashSet<Node> tmpSet = new LinkedHashSet<Node>(this.getLinksTo());
   
      for (Node value : tmpSet)
      {
         this.removeFromLinksTo(value);
      }
   }
   
   public Node createLinksTo()
   {
      Node value = new Node();
      withLinksTo(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Node ----------------------------------- Node
    *              linksTo                   linksFrom
    * </pre>
    */
   
   public static final String PROPERTY_LINKSFROM = "linksFrom";
   
   private NodeSet linksFrom = null;
   
   public NodeSet getLinksFrom()
   {
      if (this.linksFrom == null)
      {
         return Node.EMPTY_SET;
      }
   
      return this.linksFrom;
   }
   
   public boolean addToLinksFrom(Node value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.linksFrom == null)
         {
            this.linksFrom = new NodeSet();
         }
         
         changed = this.linksFrom.add (value);
         
         if (changed)
         {
            value.withLinksTo(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LINKSFROM, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromLinksFrom(Node value)
   {
      boolean changed = false;
      
      if ((this.linksFrom != null) && (value != null))
      {
         changed = this.linksFrom.remove (value);
         
         if (changed)
         {
            value.withoutLinksTo(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LINKSFROM, value, null);
         }
      }
         
      return changed;   
   }
   
   public Node withLinksFrom(Node value)
   {
      addToLinksFrom(value);
      return this;
   } 
   
   public Node withoutLinksFrom(Node value)
   {
      removeFromLinksFrom(value);
      return this;
   } 
   
   public void removeAllFromLinksFrom()
   {
      LinkedHashSet<Node> tmpSet = new LinkedHashSet<Node>(this.getLinksFrom());
   
      for (Node value : tmpSet)
      {
         this.removeFromLinksFrom(value);
      }
   }
   
   public Node createLinksFrom()
   {
      Node value = new Node();
      withLinksFrom(value);
      return value;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      _.append(" ").append(this.getText());
      return _.substring(1);
   }

}

