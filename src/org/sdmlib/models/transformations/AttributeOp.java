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
   
package org.sdmlib.models.transformations;

import java.beans.PropertyChangeSupport;

import org.sdmlib.models.transformations.creators.AttributeOpSet;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;
import java.beans.PropertyChangeListener;

public class AttributeOp implements PropertyChangeInterface
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

      if (PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         return getText();
      }

      if (PROPERTY_OPERATIONOBJECT.equalsIgnoreCase(attrName))
      {
         return getOperationObject();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         setText((String) value);
         return true;
      }

      if (PROPERTY_OPERATIONOBJECT.equalsIgnoreCase(attrName))
      {
         setOperationObject((OperationObject) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setOperationObject(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
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
   
   public AttributeOp withText(String value)
   {
      setText(value);
      return this;
   } 

   
   public static final AttributeOpSet EMPTY_SET = new AttributeOpSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * AttributeOp ----------------------------------- OperationObject
    *              attributeOps                   operationObject
    * </pre>
    */
   
   public static final String PROPERTY_OPERATIONOBJECT = "operationObject";
   
   private OperationObject operationObject = null;
   
   public OperationObject getOperationObject()
   {
      return this.operationObject;
   }
   
   public boolean setOperationObject(OperationObject value)
   {
      boolean changed = false;
      
      if (this.operationObject != value)
      {
         OperationObject oldValue = this.operationObject;
         
         if (this.operationObject != null)
         {
            this.operationObject = null;
            oldValue.withoutAttributeOps(this);
         }
         
         this.operationObject = value;
         
         if (value != null)
         {
            value.withAttributeOps(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_OPERATIONOBJECT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public AttributeOp withOperationObject(OperationObject value)
   {
      setOperationObject(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getText());
      return _.substring(1);
   }

}

