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
   
package org.sdmlib.examples.m2m.model;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.StrUtil;
import org.sdmlib.examples.m2m.model.util.RelationSet;
import org.sdmlib.examples.m2m.model.GraphComponent;

public class Relation extends GraphComponent implements PropertyChangeInterface
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
      setSrc(null);
      setTgt(null);
      setParent(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_KIND = "kind";
   
   private String kind;

   public String getKind()
   {
      return this.kind;
   }
   
   public void setKind(String value)
   {
      if ( ! StrUtil.stringEquals(this.kind, value))
      {
         String oldValue = this.kind;
         this.kind = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_KIND, oldValue, value);
      }
   }
   
   public Relation withKind(String value)
   {
      setKind(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getKind());
      _.append(" ").append(this.getText());
      return _.substring(1);
   }


   
   public static final RelationSet EMPTY_SET = new RelationSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Relation ----------------------------------- Graph
    *              relations                   graph
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
            oldValue.withoutRelations(this);
         }
         
         this.graph = value;
         
         if (value != null)
         {
            value.withRelations(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GRAPH, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Relation withGraph(Graph value)
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
    * Relation ----------------------------------- Person
    *              outEdges                   src
    * </pre>
    */
   
   public static final String PROPERTY_SRC = "src";

   private Person src = null;

   public Person getSrc()
   {
      return this.src;
   }

   public boolean setSrc(Person value)
   {
      boolean changed = false;
      
      if (this.src != value)
      {
         Person oldValue = this.src;
         
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

   public Relation withSrc(Person value)
   {
      setSrc(value);
      return this;
   } 

   public Person createSrc()
   {
      Person value = new Person();
      withSrc(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Relation ----------------------------------- Person
    *              inEdges                   tgt
    * </pre>
    */
   
   public static final String PROPERTY_TGT = "tgt";

   private Person tgt = null;

   public Person getTgt()
   {
      return this.tgt;
   }

   public boolean setTgt(Person value)
   {
      boolean changed = false;
      
      if (this.tgt != value)
      {
         Person oldValue = this.tgt;
         
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

   public Relation withTgt(Person value)
   {
      setTgt(value);
      return this;
   } 

   public Person createTgt()
   {
      Person value = new Person();
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
}

