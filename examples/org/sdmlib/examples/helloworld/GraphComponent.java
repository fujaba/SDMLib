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

import org.sdmlib.examples.helloworld.creators.GraphComponentSet;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;
import java.beans.PropertyChangeListener;

public class GraphComponent implements PropertyChangeInterface
{

   // ==========================================================================

   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;

      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
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

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
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

   // ==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   // ==========================================================================

   public void removeYou()
   {
      setParent(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   // ==========================================================================

   public static final String PROPERTY_TEXT = "text";

   private String text;

   public String getText()
   {
      return this.text;
   }

   public void setText(String value)
   {
      if (!StrUtil.stringEquals(this.text, value))
      {
         String oldValue = this.text;
         this.text = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TEXT, oldValue,
            value);
      }
   }

   public GraphComponent withText(String value)
   {
      setText(value);
      return this;
   }

   public static final GraphComponentSet EMPTY_SET = new GraphComponentSet();

   /********************************************************************
    * <pre>
    *              many                       one
    * GraphComponent ----------------------------------- Graph
    *              gcs                   parent
    * </pre>
    */

   public static final String PROPERTY_PARENT = "parent";

   private Graph parent = null;

   public Graph getParent()
   {
      return this.parent;
   }

   public boolean setParent(Graph value)
   {
      boolean changed = false;

      if (this.parent != value)
      {
         Graph oldValue = this.parent;

         if (this.parent != null)
         {
            this.parent = null;
            oldValue.withoutGcs(this);
         }

         this.parent = value;

         if (value != null)
         {
            value.withGcs(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_PARENT,
            oldValue, value);
         changed = true;
      }

      return changed;
   }

   public GraphComponent withParent(Graph value)
   {
      setParent(value);
      return this;
   }

   public Graph createParent()
   {
      Graph value = new Graph();
      withParent(value);
      return value;
   }

   public Graph getGraph()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public boolean setGraph(Graph graph)
   {
      return false;

   }

   public String getName()
   {
      // TODO Auto-generated method stub
      return null;
   }

   public void setName(String object)
   {
      // TODO Auto-generated method stub

   }

   public String toString()
   {
      StringBuilder _ = new StringBuilder();

      _.append(" ").append(this.getText());
      return _.substring(1);
   }

}

