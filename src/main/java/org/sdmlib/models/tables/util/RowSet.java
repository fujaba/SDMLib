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

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.tables.Cell;
import org.sdmlib.models.tables.Row;
import org.sdmlib.models.tables.Table;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.NumberList;
import org.sdmlib.models.tables.util.CellSet;
import org.sdmlib.models.tables.util.TableSet;

public class RowSet extends SDMSet<Row>
{

   public RowSet()
   {
      // empty
   }

   public RowSet(Row... objects)
   {
      for (Row obj : objects)
      {
         this.add(obj);
      }
   }

   public RowSet(Collection<Row> objects)
   {
      this.addAll(objects);
   }

   public static final RowSet EMPTY_SET = new RowSet();


   public RowPO createRowPO()
   {
      return new RowPO(this.toArray(new Row[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.tables.Row";
   }


   @SuppressWarnings("unchecked")
   public RowSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Row>)value);
      }
      else if (value != null)
      {
         this.add((Row) value);
      }
      
      return this;
   }
   
   public RowSet without(Row value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Row objects and collect a list of the number attribute values. 
    * 
    * @return List of int objects reachable via number attribute
    */
   public intList getNumber()
   {
      intList result = new intList();
      
      for (Row obj : this)
      {
         result.add(obj.getNumber());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Row objects and collect those Row objects where the number attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Row objects that match the parameter
    */
   public RowSet createNumberCondition(int value)
   {
      RowSet result = new RowSet();
      
      for (Row obj : this)
      {
         if (value == obj.getNumber())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Row objects and collect those Row objects where the number attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Row objects that match the parameter
    */
   public RowSet createNumberCondition(int lower, int upper)
   {
      RowSet result = new RowSet();
      
      for (Row obj : this)
      {
         if (lower <= obj.getNumber() && obj.getNumber() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Row objects and assign value to the number attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Row objects now with new attribute values.
    */
   public RowSet withNumber(int value)
   {
      for (Row obj : this)
      {
         obj.setNumber(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Row objects and collect a set of the Table objects reached via table. 
    * 
    * @return Set of Table objects reachable via table
    */
   public TableSet getTable()
   {
      TableSet result = new TableSet();
      
      for (Row obj : this)
      {
         result.with(obj.getTable());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Row objects and collect all contained objects with reference table pointing to the object passed as parameter. 
    * 
    * @param value The object required as table neighbor of the collected results. 
    * 
    * @return Set of Table objects referring to value via table
    */
   public RowSet filterTable(Object value)
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
      
      RowSet answer = new RowSet();
      
      for (Row obj : this)
      {
         if (neighbors.contains(obj.getTable()) || (neighbors.isEmpty() && obj.getTable() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Row object passed as parameter to the Table attribute of each of it. 
    * @param value add Table
    * @return The original set of ModelType objects now with the new neighbor attached to their Table attributes.
    */
   public RowSet withTable(Table value)
   {
      for (Row obj : this)
      {
         obj.withTable(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Row objects and collect a set of the Cell objects reached via cells. 
    * 
    * @return Set of Cell objects reachable via cells
    */
   public CellSet getCells()
   {
      CellSet result = new CellSet();
      
      for (Row obj : this)
      {
         result.with(obj.getCells());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Row objects and collect all contained objects with reference cells pointing to the object passed as parameter. 
    * 
    * @param value The object required as cells neighbor of the collected results. 
    * 
    * @return Set of Cell objects referring to value via cells
    */
   public RowSet filterCells(Object value)
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
      
      RowSet answer = new RowSet();
      
      for (Row obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getCells()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Row object passed as parameter to the Cells attribute of each of it. 
    * @param value add Row
    * @return The original set of ModelType objects now with the new neighbor attached to their Cells attributes.
    */
   public RowSet withCells(Cell value)
   {
      for (Row obj : this)
      {
         obj.withCells(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Row object passed as parameter from the Cells attribute of each of it. 
    * @param value remove Cell
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public RowSet withoutCells(Cell value)
   {
      for (Row obj : this)
      {
         obj.withoutCells(value);
      }
      
      return this;
   }



   @Override
   public RowSet getNewList(boolean keyValue)
   {
      return new RowSet();
   }

   /**
    * Loop through the current set of Row objects and collect those Row objects where the number attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Row objects that match the parameter
    */
   public RowSet filterNumber(int value)
   {
      RowSet result = new RowSet();
      
      for (Row obj : this)
      {
         if (value == obj.getNumber())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Row objects and collect those Row objects where the number attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Row objects that match the parameter
    */
   public RowSet filterNumber(int lower, int upper)
   {
      RowSet result = new RowSet();
      
      for (Row obj : this)
      {
         if (lower <= obj.getNumber() && obj.getNumber() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
