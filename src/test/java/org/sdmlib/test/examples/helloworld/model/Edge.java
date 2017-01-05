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
   
package org.sdmlib.test.examples.helloworld.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.helloworld.model.util.EdgeSet;

import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/helloworld/HelloWorldTTC2011.java'>HelloWorldTTC2011.java</a>
*/
   public class Edge extends GraphComponent implements PropertyChangeInterface, SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
      return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
      getPropertyChangeSupport().removePropertyChangeListener(listener);
      return true;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setGraph(null);
      setSrc(null);
      setTgt(null);
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
      if (this.name != value)
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


   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getName());
      s.append(" ").append(this.getText());
      return s.substring(1);
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
   
   public GraphComponent withText(String value)
   {
      setText(value);
      return this;
   } 

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   }

