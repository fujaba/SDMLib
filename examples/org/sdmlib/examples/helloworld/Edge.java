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

import org.sdmlib.examples.helloworld.creators.EdgeSet;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

public class Edge extends GraphComponent implements PropertyChangeInterface
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

      if (PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         return getGraph();
      }

      if (PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         return getSrc();
      }

      if (PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         return getTgt();
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return getName();
      }

      if (PROPERTY_TEXT.equalsIgnoreCase(attribute))
      {
         return getText();
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return getParent();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         setGraph((Graph) value);
         return true;
      }

      if (PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         setSrc((Node) value);
         return true;
      }

      if (PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         setTgt((Node) value);
         return true;
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
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
      setSrc(null);
      setTgt(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   public static final EdgeSet EMPTY_SET = new EdgeSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Edge ----------------------------------- Graph
    *              edges                   graph
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
            oldValue.withoutEdges(this);
         }
         
         this.graph = value;
         
         if (value != null)
         {
            value.withEdges(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GRAPH, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Edge withGraph(Graph value)
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
    *              many                       one
    * Edge ----------------------------------- Node
    *              outEdges                   src
    * </pre>
    */
   
   public static final String PROPERTY_SRC = "src";
   
   private Node src = null;
   
   public Node getSrc()
   {
      return this.src;
   }
   
   public boolean setSrc(Node value)
   {
      boolean changed = false;
      
      if (this.src != value)
      {
         Node oldValue = this.src;
         
         if (this.src != null)
         {
            this.src = null;
            oldValue.withoutOutEdges(this);
         }
         
         this.src = value;
         
         if (value != null)
         {
            value.withOutEdges(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SRC, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Edge withSrc(Node value)
   {
      setSrc(value);
      return this;
   } 
   
   public Node createSrc()
   {
      Node value = new Node();
      withSrc(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Edge ----------------------------------- Node
    *              inEdges                   tgt
    * </pre>
    */
   
   public static final String PROPERTY_TGT = "tgt";
   
   private Node tgt = null;
   
   public Node getTgt()
   {
      return this.tgt;
   }
   
   public boolean setTgt(Node value)
   {
      boolean changed = false;
      
      if (this.tgt != value)
      {
         Node oldValue = this.tgt;
         
         if (this.tgt != null)
         {
            this.tgt = null;
            oldValue.withoutInEdges(this);
         }
         
         this.tgt = value;
         
         if (value != null)
         {
            value.withInEdges(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TGT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Edge withTgt(Node value)
   {
      setTgt(value);
      return this;
   } 
   
   public Node createTgt()
   {
      Node value = new Node();
      withTgt(value);
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
      if ( ! StrUtil.stringEquals(this.name, value))
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public Edge withName(String value)
   {
      setName(value);
      return this;
   } 
}

