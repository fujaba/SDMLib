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

import java.util.LinkedHashSet;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.PropertyChangeSupport;
import org.sdmlib.utils.StrUtil;

public class Statement implements PropertyChangeInterface
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

      if (PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         return getNext();
      }

      if (PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         return getPrev();
      }

      if (PROPERTY_OPERATIONOBJECTS.equalsIgnoreCase(attrName))
      {
         return getOperationObjects();
      }

      if (PROPERTY_TRANSFORMOP.equalsIgnoreCase(attrName))
      {
         return getTransformOp();
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

      if (PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         setNext((Statement) value);
         return true;
      }

      if (PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         setPrev((Statement) value);
         return true;
      }

      if (PROPERTY_OPERATIONOBJECTS.equalsIgnoreCase(attrName))
      {
         addToOperationObjects((OperationObject) value);
         return true;
      }
      
      if ((PROPERTY_OPERATIONOBJECTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromOperationObjects((OperationObject) value);
         return true;
      }

      if (PROPERTY_TRANSFORMOP.equalsIgnoreCase(attrName))
      {
         setTransformOp((TransformOp) value);
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
      setNext(null);
      setPrev(null);
      removeAllFromOperationObjects();
      setTransformOp(null);
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
   
   public Statement withText(String value)
   {
      setText(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Statement ----------------------------------- Statement
    *              prev                   next
    * </pre>
    */
   
   public static final String PROPERTY_NEXT = "next";
   
   private Statement next = null;
   
   public Statement getNext()
   {
      return this.next;
   }
   
   public boolean setNext(Statement value)
   {
      boolean changed = false;
      
      if (this.next != value)
      {
         Statement oldValue = this.next;
         
         if (this.next != null)
         {
            this.next = null;
            oldValue.withPrev(null);
         }
         
         this.next = value;
         
         if (value != null)
         {
            value.withPrev(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NEXT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Statement withNext(Statement value)
   {
      setNext(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Statement ----------------------------------- Statement
    *              next                   prev
    * </pre>
    */
   
   public static final String PROPERTY_PREV = "prev";
   
   private Statement prev = null;
   
   public Statement getPrev()
   {
      return this.prev;
   }
   
   public boolean setPrev(Statement value)
   {
      boolean changed = false;
      
      if (this.prev != value)
      {
         Statement oldValue = this.prev;
         
         if (this.prev != null)
         {
            this.prev = null;
            oldValue.withNext(null);
         }
         
         this.prev = value;
         
         if (value != null)
         {
            value.withNext(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PREV, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Statement withPrev(Statement value)
   {
      setPrev(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Statement ----------------------------------- OperationObject
    *              statements                   operationObjects
    * </pre>
    */
   
   public static final String PROPERTY_OPERATIONOBJECTS = "operationObjects";
   
   private LinkedHashSet<OperationObject> operationObjects = null;
   
   public LinkedHashSet<OperationObject> getOperationObjects()
   {
      if (this.operationObjects == null)
      {
         return OperationObject.EMPTY_SET;
      }
   
      return this.operationObjects;
   }
   
   public boolean addToOperationObjects(OperationObject value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.operationObjects == null)
         {
            this.operationObjects = new LinkedHashSet<OperationObject>();
         }
         
         changed = this.operationObjects.add (value);
         
         if (changed)
         {
            value.withStatements(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OPERATIONOBJECTS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromOperationObjects(OperationObject value)
   {
      boolean changed = false;
      
      if ((this.operationObjects != null) && (value != null))
      {
         changed = this.operationObjects.remove (value);
         
         if (changed)
         {
            value.withoutStatements(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OPERATIONOBJECTS, value, null);
         }
      }
         
      return changed;   
   }
   
   public Statement withOperationObjects(OperationObject value)
   {
      addToOperationObjects(value);
      return this;
   } 
   
   public Statement withoutOperationObjects(OperationObject value)
   {
      removeFromOperationObjects(value);
      return this;
   } 
   
   public void removeAllFromOperationObjects()
   {
      LinkedHashSet<OperationObject> tmpSet = new LinkedHashSet<OperationObject>(this.getOperationObjects());
   
      for (OperationObject value : tmpSet)
      {
         this.removeFromOperationObjects(value);
      }
   }

   
   public static final LinkedHashSet<Statement> EMPTY_SET = new LinkedHashSet<Statement>();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Statement ----------------------------------- TransformOp
    *              statements                   transformOp
    * </pre>
    */
   
   public static final String PROPERTY_TRANSFORMOP = "transformOp";
   
   private TransformOp transformOp = null;
   
   public TransformOp getTransformOp()
   {
      return this.transformOp;
   }
   
   public boolean setTransformOp(TransformOp value)
   {
      boolean changed = false;
      
      if (this.transformOp != value)
      {
         TransformOp oldValue = this.transformOp;
         
         if (this.transformOp != null)
         {
            this.transformOp = null;
            oldValue.withoutStatements(this);
         }
         
         this.transformOp = value;
         
         if (value != null)
         {
            value.withStatements(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TRANSFORMOP, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Statement withTransformOp(TransformOp value)
   {
      setTransformOp(value);
      return this;
   } 
}

