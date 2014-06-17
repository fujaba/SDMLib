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
   
package org.sdmlib.examples.groupAccount.model;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.StrUtil;
import org.sdmlib.examples.groupAccount.model.util.ItemSet;

public class Item implements PropertyChangeInterface
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
      setParent(null);
      setBuyer(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_DESCRIPTION = "description";
   
   private String description;

   public String getDescription()
   {
      return this.description;
   }
   
   public void setDescription(String value)
   {
      if ( ! StrUtil.stringEquals(this.description, value))
      {
         String oldValue = this.description;
         this.description = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_DESCRIPTION, oldValue, value);
      }
   }
   
   public Item withDescription(String value)
   {
      setDescription(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getDescription());
      _.append(" ").append(this.getValue());
      return _.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_VALUE = "value";
   
   private double value;

   public double getValue()
   {
      return this.value;
   }
   
   public void setValue(double value)
   {
      if (this.value != value)
      {
         double oldValue = this.value;
         this.value = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_VALUE, oldValue, value);
      }
   }
   
   public Item withValue(double value)
   {
      setValue(value);
      return this;
   } 

   
   public static final ItemSet EMPTY_SET = new ItemSet().withReadonly(true);

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Item ----------------------------------- GroupAccount
    *              item                   parent
    * </pre>
    */
   
   public static final String PROPERTY_PARENT = "parent";

   private GroupAccount parent = null;

   public GroupAccount getParent()
   {
      return this.parent;
   }

   public boolean setParent(GroupAccount value)
   {
      boolean changed = false;
      
      if (this.parent != value)
      {
         GroupAccount oldValue = this.parent;
         
         if (this.parent != null)
         {
            this.parent = null;
            oldValue.withoutItem(this);
         }
         
         this.parent = value;
         
         if (value != null)
         {
            value.withItem(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PARENT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Item withParent(GroupAccount value)
   {
      setParent(value);
      return this;
   } 

   public GroupAccount createParent()
   {
      GroupAccount value = new GroupAccount();
      withParent(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Item ----------------------------------- Person
    *              item                   buyer
    * </pre>
    */
   
   public static final String PROPERTY_BUYER = "buyer";

   private Person buyer = null;

   public Person getBuyer()
   {
      return this.buyer;
   }

   public boolean setBuyer(Person value)
   {
      boolean changed = false;
      
      if (this.buyer != value)
      {
         Person oldValue = this.buyer;
         
         if (this.buyer != null)
         {
            this.buyer = null;
            oldValue.withoutItem(this);
         }
         
         this.buyer = value;
         
         if (value != null)
         {
            value.withItem(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BUYER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Item withBuyer(Person value)
   {
      setBuyer(value);
      return this;
   } 

   public Person createBuyer()
   {
      Person value = new Person();
      withBuyer(value);
      return value;
   } 
}

