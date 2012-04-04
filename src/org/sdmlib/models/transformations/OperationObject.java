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

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.utils.StrUtil;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;

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
      
      if ((PROPERTY_STATEMENTS + JsonIdMap.REMOVE_SUFFIX).equalsIgnoreCase(attrName))
      {
         removeFromStatements((Statement) value);
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

   
   public static final LinkedHashSet<OperationObject> EMPTY_SET = new LinkedHashSet<OperationObject>();

   
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
   
   private LinkedHashSet<Statement> statements = null;
   
   public LinkedHashSet<Statement> getStatements()
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
            this.statements = new LinkedHashSet<Statement>();
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
}

