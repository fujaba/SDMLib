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

import org.sdmlib.StrUtil;
import org.sdmlib.models.tables.util.CellSet;

import de.uniks.networkparser.interfaces.SendableEntity;

/**
 * 
 * @see <a href='../../../../../../../src/main/java/org/sdmlib/models/tables/TableModel.java'>TableModel.java</a>
 */
public class Column implements SendableEntity
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


	public boolean removePropertyChangeListener(PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(listener);
		}
		return true;
	}

	public boolean removePropertyChangeListener(String property,
			PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
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

   public static final String PROPERTY_NAME = "name";

   private String name;


   public String getName()
   {
      return this.name;
   }


   public void setName(String value)
   {
      if (!StrUtil.stringEquals(this.name, value))
      {

         String oldValue = this.name;
         this.name = value;
         this.firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }


   public Column withName(String value)
   {
      setName(value);
      return this;
   }


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();

      result.append(" ").append(this.getName());
      result.append(" ").append(this.getTdCssClass());
      result.append(" ").append(this.getThCssClass());
      return result.substring(1);
   }

   /********************************************************************
    * <pre>
    *              many                       one
    * Column ----------------------------------- Table
    *              columns                   table
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
            oldValue.withoutColumns(this);
         }

         this.table = value;

         if (value != null)
         {
            value.withColumns(this);

            if (this.getName() == null)
            {
               String name = "";

               int size = value.getColumns().size();

               int numOfChars = ('Z' - 'A' + 1);

               while (size > 0)
               {
                  char c = (char) ('A' + (size % numOfChars) - 1);

                  name = "" + c + name;

                  size = size / numOfChars;
               }
               
               this.setName(name);
            }
         }

         firePropertyChange(PROPERTY_TABLE, oldValue, value);
         changed = true;
      }

      return changed;
   }


   public Column withTable(Table value)
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
    * Column ----------------------------------- Cell
    *              column                   cells
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


   public Column withCells(Cell... value)
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
               item.withColumn(this);
               firePropertyChange(PROPERTY_CELLS, null, item);
            }
         }
      }
      return this;
   }


   public Column withoutCells(Cell... value)
   {
      for (Cell item : value)
      {
         if ((this.cells != null) && (item != null))
         {
            if (this.cells.remove(item))
            {
               item.setColumn(null);
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

   // ==========================================================================

   public static final String PROPERTY_TDCSSCLASS = "tdCssClass";

   private String tdCssClass;


   public String getTdCssClass()
   {
      return this.tdCssClass;
   }


   public void setTdCssClass(String value)
   {
      if (!StrUtil.stringEquals(this.tdCssClass, value))
      {

         String oldValue = this.tdCssClass;
         this.tdCssClass = value;
         this.firePropertyChange(PROPERTY_TDCSSCLASS, oldValue, value);
      }
   }


   public Column withTdCssClass(String value)
   {
      setTdCssClass(value);
      return this;
   }

   // ==========================================================================

   public static final String PROPERTY_THCSSCLASS = "thCssClass";

   private String thCssClass = "text-center";


   public String getThCssClass()
   {
      return this.thCssClass;
   }


   public void setThCssClass(String value)
   {
      if (!StrUtil.stringEquals(this.thCssClass, value))
      {

         String oldValue = this.thCssClass;
         this.thCssClass = value;
         this.firePropertyChange(PROPERTY_THCSSCLASS, oldValue, value);
      }
   }


   public Column withThCssClass(String value)
   {
      setThCssClass(value);
      return this;
   }
}
