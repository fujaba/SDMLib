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
   
package org.sdmlib.models.tables.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.tables.Cell;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.models.tables.util.RowSet;
import org.sdmlib.models.tables.Row;
import org.sdmlib.models.tables.util.ColumnSet;
import org.sdmlib.models.tables.Column;

public class CellSet extends SDMSet<Cell>
{

   public CellSet()
   {
      // empty
   }

   public CellSet(Cell... objects)
   {
      for (Cell obj : objects)
      {
         this.add(obj);
      }
   }

   public CellSet(Collection<Cell> objects)
   {
      this.addAll(objects);
   }

   public static final CellSet EMPTY_SET = new CellSet();


   public CellPO createCellPO()
   {
      return new CellPO(this.toArray(new Cell[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.tables.Cell";
   }


   @SuppressWarnings("unchecked")
   public CellSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Cell>)value);
      }
      else if (value != null)
      {
         this.add((Cell) value);
      }
      
      return this;
   }
   
   public CellSet without(Cell value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Cell objects and collect a list of the value attribute values. 
    * 
    * @return List of Object objects reachable via value attribute
    */
   public ObjectSet getValue()
   {
      ObjectSet result = new ObjectSet();
      
      for (Cell obj : this)
      {
         result.add(obj.getValue());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Cell objects and collect those Cell objects where the value attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Cell objects that match the parameter
    */
   public CellSet createValueCondition(Object value)
   {
      CellSet result = new CellSet();
      
      for (Cell obj : this)
      {
         if (value == obj.getValue())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Cell objects and assign value to the value attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Cell objects now with new attribute values.
    */
   public CellSet withValue(Object value)
   {
      for (Cell obj : this)
      {
         obj.setValue(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Cell objects and collect a set of the Row objects reached via row. 
    * 
    * @return Set of Row objects reachable via row
    */
   public RowSet getRow()
   {
      RowSet result = new RowSet();
      
      for (Cell obj : this)
      {
         result.with(obj.getRow());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Cell objects and collect all contained objects with reference row pointing to the object passed as parameter. 
    * 
    * @param value The object required as row neighbor of the collected results. 
    * 
    * @return Set of Row objects referring to value via row
    */
   public CellSet filterRow(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      CellSet answer = new CellSet();
      
      for (Cell obj : this)
      {
         if (neighbors.contains(obj.getRow()) || (neighbors.isEmpty() && obj.getRow() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Cell object passed as parameter to the Row attribute of each of it. 
    * @param value add Row
    * @return The original set of ModelType objects now with the new neighbor attached to their Row attributes.
    */
   public CellSet withRow(Row value)
   {
      for (Cell obj : this)
      {
         obj.withRow(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Cell objects and collect a set of the Column objects reached via column. 
    * 
    * @return Set of Column objects reachable via column
    */
   public ColumnSet getColumn()
   {
      ColumnSet result = new ColumnSet();
      
      for (Cell obj : this)
      {
         result.with(obj.getColumn());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Cell objects and collect all contained objects with reference column pointing to the object passed as parameter. 
    * 
    * @param value The object required as column neighbor of the collected results. 
    * 
    * @return Set of Column objects referring to value via column
    */
   public CellSet filterColumn(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      CellSet answer = new CellSet();
      
      for (Cell obj : this)
      {
         if (neighbors.contains(obj.getColumn()) || (neighbors.isEmpty() && obj.getColumn() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Cell object passed as parameter to the Column attribute of each of it. 
    * @param value add Column
    * @return The original set of ModelType objects now with the new neighbor attached to their Column attributes.
    */
   public CellSet withColumn(Column value)
   {
      for (Cell obj : this)
      {
         obj.withColumn(value);
      }
      
      return this;
   }

}
