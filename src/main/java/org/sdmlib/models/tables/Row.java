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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.models.tables.util.CellSet;

import de.uniks.networkparser.interfaces.SendableEntity;

/**
 * 
 * @see <a href='../../../../../../../src/main/java/org/sdmlib/models/tables/TableModel.java'>TableModel.java</a>
 * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/TableModel.java'>TableModel.java</a>
 */
public class Row implements SendableEntity
{

   // ==========================================================================

   protected PropertyChangeSupport listeners = null;


   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null)
      {
         listeners.firePropertyChange(propertyName, oldValue, newValue);
         return true;
      }
      return false;
   }


   public boolean addPropertyChangeListener(PropertyChangeListener listener)
   {
      if (listeners == null)
      {
         listeners = new PropertyChangeSupport(this);
      }
      listeners.addPropertyChangeListener(listener);
      return true;
   }


   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
   {
      if (listeners == null)
      {
         listeners = new PropertyChangeSupport(this);
      }
      listeners.addPropertyChangeListener(propertyName, listener);
      return true;
   }


   public boolean removePropertyChangeListener(PropertyChangeListener listener)
   {
      if (listeners == null)
      {
         listeners = new PropertyChangeSupport(this);
      }
      listeners.removePropertyChangeListener(listener);
      return true;
   }


   // ==========================================================================

   public void removeYou()
   {
      setTable(null);
      withoutCells(this.getCells().toArray(new Cell[this.getCells().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   // ==========================================================================

   public static final String PROPERTY_NUMBER = "number";

   private int number;


   public int getNumber()
   {
      return this.number;
   }


   public void setNumber(int value)
   {
      if (this.number != value)
      {

         int oldValue = this.number;
         this.number = value;
         this.firePropertyChange(PROPERTY_NUMBER, oldValue, value);
      }
   }


   public Row withNumber(int value)
   {
      setNumber(value);
      return this;
   }


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();

      result.append(" ").append(this.getNumber());
      return result.substring(1);
   }

   /********************************************************************
    * <pre>
    *              many                       one
    * Row ----------------------------------- Table
    *              rows                   table
    * </pre>
    */

   public static final String PROPERTY_TABLE = "table";

   private Table table = null;


   public Table getTable()
   {
      return this.table;
   }


   public boolean setTable(Table value)
   {
      boolean changed = false;

      if (this.table != value)
      {
         Table oldValue = this.table;

         if (this.table != null)
         {
            this.table = null;
            oldValue.withoutRows(this);
         }

         this.table = value;

         if (value != null)
         {
            value.withRows(this);
            
            if (this.getNumber() == 0)
            {
               this.withNumber(value.getRows().size());
            }
         }

         firePropertyChange(PROPERTY_TABLE, oldValue, value);
         changed = true;
      }

      return changed;
   }


   public Row withTable(Table value)
   {
      setTable(value);
      return this;
   }


   public Table createTable()
   {
      Table value = new Table();
      withTable(value);
      return value;
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * Row ----------------------------------- Cell
    *              row                   cells
    * </pre>
    */

   public static final String PROPERTY_CELLS = "cells";

   private CellSet cells = null;


   public CellSet getCells()
   {
      if (this.cells == null)
      {
         return CellSet.EMPTY_SET;
      }

      return this.cells;
   }


   public Row withCells(Cell... value)
   {
      if (value == null)
      {
         return this;
      }
      for (Cell item : value)
      {
         if (item != null)
         {
            if (this.cells == null)
            {
               this.cells = new CellSet();
            }

            boolean changed = this.cells.add(item);

            if (changed)
            {
               item.withRow(this);
               firePropertyChange(PROPERTY_CELLS, null, item);
            }
         }
      }
      return this;
   }


   public Row withoutCells(Cell... value)
   {
      for (Cell item : value)
      {
         if ((this.cells != null) && (item != null))
         {
            if (this.cells.remove(item))
            {
               item.setRow(null);
               firePropertyChange(PROPERTY_CELLS, item, null);
            }
         }
      }
      return this;
   }


   public Cell createCells()
   {
      Cell value = new Cell();
      withCells(value);
      return value;
   }


   @SuppressWarnings("unchecked")
   public <T> T getCellValue(String columnName)
   {
      for (Cell c : this.getCells())
      {
         if (c.getColumn().getName().equals(columnName))
         {
            return (T) c.getValue();
         }
      }
      return null;
   }

   public boolean removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    if (listeners == null) {
       listeners = new PropertyChangeSupport(this);
    }
    listeners.removePropertyChangeListener(propertyName, listener);
    return true;
   }
}
