/*
   Copyright (c) 2016 zuendorf
   
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
   
package org.sdmlib.models.tables;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.models.tables.Row;
import org.sdmlib.models.tables.Column;
   /**
    * 
    * @see <a href='../../../../../../../src/main/java/org/sdmlib/models/tables/TableModel.java'>TableModel.java</a>
 */
   public  class Cell implements SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = null;
   
   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(listener);
   	return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(propertyName, listener);
   	return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.removePropertyChangeListener(listener);
   	return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      setRow(null);
      setColumn(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_VALUE = "value";
   
   private Object value;

   public Object getValue()
   {
      return this.value;
   }
   
   public void setValue(Object value)
   {
      if (this.value != value) {
      
         Object oldValue = this.value;
         this.value = value;
         this.firePropertyChange(PROPERTY_VALUE, oldValue, value);
      }
   }
   
   public Cell withValue(Object value)
   {
      setValue(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Cell ----------------------------------- Row
    *              cells                   row
    * </pre>
    */
   
   public static final String PROPERTY_ROW = "row";

   private Row row = null;

   public Row getRow()
   {
      return this.row;
   }

   public boolean setRow(Row value)
   {
      boolean changed = false;
      
      if (this.row != value)
      {
         Row oldValue = this.row;
         
         if (this.row != null)
         {
            this.row = null;
            oldValue.withoutCells(this);
         }
         
         this.row = value;
         
         if (value != null)
         {
            value.withCells(this);
         }
         
         firePropertyChange(PROPERTY_ROW, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Cell withRow(Row value)
   {
      setRow(value);
      return this;
   } 

   public Row createRow()
   {
      Row value = new Row();
      withRow(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Cell ----------------------------------- Column
    *              cells                   column
    * </pre>
    */
   
   public static final String PROPERTY_COLUMN = "column";

   private Column column = null;

   public Column getColumn()
   {
      return this.column;
   }

   public boolean setColumn(Column value)
   {
      boolean changed = false;
      
      if (this.column != value)
      {
         Column oldValue = this.column;
         
         if (this.column != null)
         {
            this.column = null;
            oldValue.withoutCells(this);
         }
         
         this.column = value;
         
         if (value != null)
         {
            value.withCells(this);
         }
         else
         {
            this.removeYou();
         }
         
         firePropertyChange(PROPERTY_COLUMN, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Cell withColumn(Column value)
   {
      setColumn(value);
      return this;
   } 

   public Column createColumn()
   {
      Column value = new Column();
      withColumn(value);
      return value;
   } 
}
