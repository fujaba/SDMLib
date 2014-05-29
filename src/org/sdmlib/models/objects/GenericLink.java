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
   
package org.sdmlib.models.objects;

import java.beans.PropertyChangeSupport;

import org.sdmlib.StrUtil;
import org.sdmlib.models.objects.util.GenericLinkSet;
import org.sdmlib.serialization.PropertyChangeInterface;

public class GenericLink implements PropertyChangeInterface
{
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setSrc(null);
      setTgt(null);
      setGraph(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_TGTLABEL = "tgtLabel";
   
   private String tgtLabel;

   public String getTgtLabel()
   {
      return this.tgtLabel;
   }
   
   public void setTgtLabel(String value)
   {
      if ( ! StrUtil.stringEquals(this.tgtLabel, value))
      {
         String oldValue = this.tgtLabel;
         this.tgtLabel = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TGTLABEL, oldValue, value);
      }
   }
   
   public GenericLink withTgtLabel(String value)
   {
      setTgtLabel(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_SRCLABEL = "srcLabel";
   
   private String srcLabel;

   public String getSrcLabel()
   {
      return this.srcLabel;
   }
   
   public void setSrcLabel(String value)
   {
      if ( ! StrUtil.stringEquals(this.srcLabel, value))
      {
         String oldValue = this.srcLabel;
         this.srcLabel = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SRCLABEL, oldValue, value);
      }
   }
   
   public GenericLink withSrcLabel(String value)
   {
      setSrcLabel(value);
      return this;
   } 

   
   public static final GenericLinkSet EMPTY_SET = new GenericLinkSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * GenericLink ----------------------------------- GenericObject
    *              outgoingLinks                   src
    * </pre>
    */
   
   public static final String PROPERTY_SRC = "src";
   
   private GenericObject src = null;
   
   public GenericObject getSrc()
   {
      return this.src;
   }
   
   public boolean setSrc(GenericObject value)
   {
      boolean changed = false;
      
      if (this.src != value)
      {
         GenericObject oldValue = this.src;
         
         if (this.src != null)
         {
            this.src = null;
            oldValue.withoutOutgoingLinks(this);
         }
         
         this.src = value;
         
         if (value != null)
         {
            value.withOutgoingLinks(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SRC, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public GenericLink withSrc(GenericObject value)
   {
      setSrc(value);
      return this;
   } 
   
   public GenericObject createSrc()
   {
      GenericObject value = new GenericObject()
      .withGraph(this.getGraph());
      
      this.withSrc(value);
      
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * GenericLink ----------------------------------- GenericObject
    *              incommingLinks                   tgt
    * </pre>
    */
   
   public static final String PROPERTY_TGT = "tgt";
   
   private GenericObject tgt = null;
   
   public GenericObject getTgt()
   {
      return this.tgt;
   }
   
   public boolean setTgt(GenericObject value)
   {
      boolean changed = false;
      
      if (this.tgt != value)
      {
         GenericObject oldValue = this.tgt;
         
         if (this.tgt != null)
         {
            this.tgt = null;
            oldValue.withoutIncommingLinks(this);
         }
         
         this.tgt = value;
         
         if (value != null)
         {
            value.withIncommingLinks(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TGT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public GenericLink withTgt(GenericObject value)
   {
      setTgt(value);
      return this;
   } 
   
   public GenericObject createTgt()
   {
      GenericObject value = new GenericObject()
      .withGraph(this.getGraph());
      
      this.withTgt(value);
      
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * GenericLink ----------------------------------- GenericGraph
    *              links                   graph
    * </pre>
    */
   
   public static final String PROPERTY_GRAPH = "graph";
   
   private GenericGraph graph = null;
   
   public GenericGraph getGraph()
   {
      return this.graph;
   }
   
   public boolean setGraph(GenericGraph value)
   {
      boolean changed = false;
      
      if (this.graph != value)
      {
         GenericGraph oldValue = this.graph;
         
         if (this.graph != null)
         {
            this.graph = null;
            oldValue.withoutLinks(this);
         }
         
         this.graph = value;
         
         if (value != null)
         {
            value.withLinks(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GRAPH, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public GenericLink withGraph(GenericGraph value)
   {
      setGraph(value);
      return this;
   } 
   
   public GenericGraph createGraph()
   {
      GenericGraph value = new GenericGraph();
      withGraph(value);
      return value;
   } 

   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getTgtLabel());
      _.append(" ").append(this.getSrcLabel());
      return _.substring(1);
   }
}
