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
import org.sdmlib.StrUtil;
import org.sdmlib.models.tables.util.ColumnSet;
import org.sdmlib.models.tables.Column;
import org.sdmlib.models.tables.util.RowSet;
import org.sdmlib.models.tables.Row;
   /**
    * 
    * @see <a href='../../../../../../../src/main/java/org/sdmlib/models/tables/TableModel.java'>TableModel.java</a>
 */
   public  class Table implements SendableEntity
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
      withoutColumns(this.getColumns().toArray(new Column[this.getColumns().size()]));
      withoutRows(this.getRows().toArray(new Row[this.getRows().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
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
      if ( ! StrUtil.stringEquals(this.name, value)) {
      
         String oldValue = this.name;
         this.name = value;
         this.firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public Table withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       many
    * Table ----------------------------------- Column
    *              table                   columns
    * </pre>
    */
   
   public static final String PROPERTY_COLUMNS = "columns";

   private ColumnSet columns = null;
   
   public ColumnSet getColumns()
   {
      if (this.columns == null)
      {
         return ColumnSet.EMPTY_SET;
      }
   
      return this.columns;
   }

   public Table withColumns(Column... value)
   {
      if(value==null){
         return this;
      }
      for (Column item : value)
      {
         if (item != null)
         {
            if (this.columns == null)
            {
               this.columns = new ColumnSet();
            }
            
            boolean changed = this.columns.add (item);

            if (changed)
            {
               item.withTable(this);
               firePropertyChange(PROPERTY_COLUMNS, null, item);
            }
         }
      }
      return this;
   } 

   public Table withoutColumns(Column... value)
   {
      for (Column item : value)
      {
         if ((this.columns != null) && (item != null))
         {
            if (this.columns.remove(item))
            {
               item.setTable(null);
               firePropertyChange(PROPERTY_COLUMNS, item, null);
            }
         }
      }
      return this;
   }

   public Column createColumns()
   {
      Column value = new Column();
      withColumns(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Table ----------------------------------- Row
    *              table                   rows
    * </pre>
    */
   
   public static final String PROPERTY_ROWS = "rows";

   private RowSet rows = null;
   
   public RowSet getRows()
   {
      if (this.rows == null)
      {
         return RowSet.EMPTY_SET;
      }
   
      return this.rows;
   }

   public Table withRows(Row... value)
   {
      if(value==null){
         return this;
      }
      for (Row item : value)
      {
         if (item != null)
         {
            if (this.rows == null)
            {
               this.rows = new RowSet();
            }
            
            boolean changed = this.rows.add (item);

            if (changed)
            {
               item.withTable(this);
               firePropertyChange(PROPERTY_ROWS, null, item);
            }
         }
      }
      return this;
   } 

   public Table withoutRows(Row... value)
   {
      for (Row item : value)
      {
         if ((this.rows != null) && (item != null))
         {
            if (this.rows.remove(item))
            {
               item.setTable(null);
               firePropertyChange(PROPERTY_ROWS, item, null);
            }
         }
      }
      return this;
   }

   public Row createRows()
   {
      Row value = new Row();
      withRows(value);
      return value;
   } 
}
