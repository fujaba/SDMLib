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

public class Node implements PropertyChangeInterface
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
}

