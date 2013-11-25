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
import java.util.LinkedHashSet;

import org.sdmlib.models.transformations.creators.AttributeOpSet;
import org.sdmlib.models.transformations.creators.LinkOpSet;
import org.sdmlib.models.transformations.creators.OperationObjectSet;
import org.sdmlib.models.transformations.creators.StatementSet;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;
import java.beans.PropertyChangeListener;

public class OperationObject implements PropertyChangeInterface
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

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         return getType();
      }

      if (PROPERTY_TRANSFORMOP.equalsIgnoreCase(attrName))
      {
         return getTransformOp();
      }

      if (PROPERTY_STATEMENTS.equalsIgnoreCase(attrName))
      {
         return getStatements();
      }

      if (PROPERTY_SET.equalsIgnoreCase(attrName))
      {
         return getSet();
      }

      if (PROPERTY_ATTRIBUTEOPS.equalsIgnoreCase(attrName))
      {
         return getAttributeOps();
      }

      if (PROPERTY_OUTGOINGS.equalsIgnoreCase(attrName))
      {
         return getOutgoings();
      }

      if (PROPERTY_INCOMINGS.equalsIgnoreCase(attrName))
      {
         return getIncomings();
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

      if (PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         setType((String) value);
         return true;
      }

      if (PROPERTY_TRANSFORMOP.equalsIgnoreCase(attrName))
      {
         setTransformOp((TransformOp) value);
         return true;
      }

      if (PROPERTY_STATEMENTS.equalsIgnoreCase(attrName))
      {
         addToStatements((Statement) value);
         return true;
      }
      
      if ((PROPERTY_STATEMENTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromStatements((Statement) value);
         return true;
      }

      if (PROPERTY_SET.equalsIgnoreCase(attrName))
      {
         setSet((Boolean) value);
         return true;
      }

      if (PROPERTY_ATTRIBUTEOPS.equalsIgnoreCase(attrName))
      {
         addToAttributeOps((AttributeOp) value);
         return true;
      }
      
      if ((PROPERTY_ATTRIBUTEOPS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromAttributeOps((AttributeOp) value);
         return true;
      }

      if (PROPERTY_OUTGOINGS.equalsIgnoreCase(attrName))
      {
         addToOutgoings((LinkOp) value);
         return true;
      }
      
      if ((PROPERTY_OUTGOINGS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromOutgoings((LinkOp) value);
         return true;
      }

      if (PROPERTY_INCOMINGS.equalsIgnoreCase(attrName))
      {
         addToIncomings((LinkOp) value);
         return true;
      }
      
      if ((PROPERTY_INCOMINGS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromIncomings((LinkOp) value);
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
      setTransformOp(null);
      removeAllFromStatements();
      removeAllFromAttributeOps();
      removeAllFromOutgoings();
      removeAllFromIncomings();
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
   
   public OperationObject withName(String value)
   {
      setName(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_TYPE = "type";
   
   private String type;
   
   public String getType()
   {
      return this.type;
   }
   
   public void setType(String value)
   {
      if ( ! StrUtil.stringEquals(this.type, value))
      {
         String oldValue = this.type;
         this.type = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TYPE, oldValue, value);
      }
   }
   
   public OperationObject withType(String value)
   {
      setType(value);
      return this;
   } 

   
   public static final OperationObjectSet EMPTY_SET = new OperationObjectSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * OperationObject ----------------------------------- TransformOp
    *              opObjects                   transformOp
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
            oldValue.withoutOpObjects(this);
         }
         
         this.transformOp = value;
         
         if (value != null)
         {
            value.withOpObjects(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TRANSFORMOP, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public OperationObject withTransformOp(TransformOp value)
   {
      setTransformOp(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * OperationObject ----------------------------------- Statement
    *              operationObjects                   statements
    * </pre>
    */
   
   public static final String PROPERTY_STATEMENTS = "statements";
   
   private StatementSet statements = null;
   
   public StatementSet getStatements()
   {
      if (this.statements == null)
      {
         return Statement.EMPTY_SET;
      }
   
      return this.statements;
   }
   
   public boolean addToStatements(Statement value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.statements == null)
         {
            this.statements = new StatementSet();
         }
         
         changed = this.statements.add (value);
         
         if (changed)
         {
            value.withOperationObjects(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_STATEMENTS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromStatements(Statement value)
   {
      boolean changed = false;
      
      if ((this.statements != null) && (value != null))
      {
         changed = this.statements.remove (value);
         
         if (changed)
         {
            value.withoutOperationObjects(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_STATEMENTS, value, null);
         }
      }
         
      return changed;   
   }
   
   public OperationObject withStatements(Statement value)
   {
      addToStatements(value);
      return this;
   } 
   
   public OperationObject withoutStatements(Statement value)
   {
      removeFromStatements(value);
      return this;
   } 
   
   public void removeAllFromStatements()
   {
      LinkedHashSet<Statement> tmpSet = new LinkedHashSet<Statement>(this.getStatements());
   
      for (Statement value : tmpSet)
      {
         this.removeFromStatements(value);
      }
   }

   
   //==========================================================================
   
   public static final String PROPERTY_SET = "set";
   
   private boolean set;
   
   public boolean getSet()
   {
      return this.set;
   }
   
   public void setSet(boolean value)
   {
      if (this.set != value)
      {
         boolean oldValue = this.set;
         this.set = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SET, oldValue, value);
      }
   }
   
   public OperationObject withSet(boolean value)
   {
      setSet(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * OperationObject ----------------------------------- AttributeOp
    *              operationObject                   attributeOps
    * </pre>
    */
   
   public static final String PROPERTY_ATTRIBUTEOPS = "attributeOps";
   
   private AttributeOpSet attributeOps = null;
   
   public AttributeOpSet getAttributeOps()
   {
      if (this.attributeOps == null)
      {
         return AttributeOp.EMPTY_SET;
      }
   
      return this.attributeOps;
   }
   
   public boolean addToAttributeOps(AttributeOp value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.attributeOps == null)
         {
            this.attributeOps = new AttributeOpSet();
         }
         
         changed = this.attributeOps.add (value);
         
         if (changed)
         {
            value.withOperationObject(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ATTRIBUTEOPS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromAttributeOps(AttributeOp value)
   {
      boolean changed = false;
      
      if ((this.attributeOps != null) && (value != null))
      {
         changed = this.attributeOps.remove (value);
         
         if (changed)
         {
            value.setOperationObject(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ATTRIBUTEOPS, value, null);
         }
      }
         
      return changed;   
   }
   
   public OperationObject withAttributeOps(AttributeOp value)
   {
      addToAttributeOps(value);
      return this;
   } 
   
   public OperationObject withoutAttributeOps(AttributeOp value)
   {
      removeFromAttributeOps(value);
      return this;
   } 
   
   public void removeAllFromAttributeOps()
   {
      LinkedHashSet<AttributeOp> tmpSet = new LinkedHashSet<AttributeOp>(this.getAttributeOps());
   
      for (AttributeOp value : tmpSet)
      {
         this.removeFromAttributeOps(value);
      }
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * OperationObject ----------------------------------- LinkOp
    *              src                   outgoings
    * </pre>
    */
   
   public static final String PROPERTY_OUTGOINGS = "outgoings";
   
   private LinkOpSet outgoings = null;
   
   public LinkOpSet getOutgoings()
   {
      if (this.outgoings == null)
      {
         return LinkOp.EMPTY_SET;
      }
   
      return this.outgoings;
   }
   
   public boolean addToOutgoings(LinkOp value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.outgoings == null)
         {
            this.outgoings = new LinkOpSet();
         }
         
         changed = this.outgoings.add (value);
         
         if (changed)
         {
            value.withSrc(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OUTGOINGS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromOutgoings(LinkOp value)
   {
      boolean changed = false;
      
      if ((this.outgoings != null) && (value != null))
      {
         changed = this.outgoings.remove (value);
         
         if (changed)
         {
            value.setSrc(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OUTGOINGS, value, null);
         }
      }
         
      return changed;   
   }
   
   public OperationObject withOutgoings(LinkOp value)
   {
      addToOutgoings(value);
      return this;
   } 
   
   public OperationObject withoutOutgoings(LinkOp value)
   {
      removeFromOutgoings(value);
      return this;
   } 
   
   public void removeAllFromOutgoings()
   {
      LinkedHashSet<LinkOp> tmpSet = new LinkedHashSet<LinkOp>(this.getOutgoings());
   
      for (LinkOp value : tmpSet)
      {
         this.removeFromOutgoings(value);
      }
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * OperationObject ----------------------------------- LinkOp
    *              tgt                   incomings
    * </pre>
    */
   
   public static final String PROPERTY_INCOMINGS = "incomings";
   
   private LinkOpSet incomings = null;
   
   public LinkOpSet getIncomings()
   {
      if (this.incomings == null)
      {
         return LinkOp.EMPTY_SET;
      }
   
      return this.incomings;
   }
   
   public boolean addToIncomings(LinkOp value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.incomings == null)
         {
            this.incomings = new LinkOpSet();
         }
         
         changed = this.incomings.add (value);
         
         if (changed)
         {
            value.withTgt(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_INCOMINGS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromIncomings(LinkOp value)
   {
      boolean changed = false;
      
      if ((this.incomings != null) && (value != null))
      {
         changed = this.incomings.remove (value);
         
         if (changed)
         {
            value.setTgt(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_INCOMINGS, value, null);
         }
      }
         
      return changed;   
   }
   
   public OperationObject withIncomings(LinkOp value)
   {
      addToIncomings(value);
      return this;
   } 
   
   public OperationObject withoutIncomings(LinkOp value)
   {
      removeFromIncomings(value);
      return this;
   } 
   
   public void removeAllFromIncomings()
   {
      LinkedHashSet<LinkOp> tmpSet = new LinkedHashSet<LinkOp>(this.getIncomings());
   
      for (LinkOp value : tmpSet)
      {
         this.removeFromIncomings(value);
      }
   }

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      _.append(" ").append(this.getType());
      return _.substring(1);
   }

}

