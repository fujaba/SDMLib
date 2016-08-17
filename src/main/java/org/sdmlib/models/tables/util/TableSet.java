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
import org.sdmlib.models.tables.Table;
import java.util.Collection;
import de.uniks.networkparser.list.StringList;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.sdmlib.models.tables.util.ColumnSet;
import org.sdmlib.models.tables.Column;
import org.sdmlib.models.tables.util.RowSet;
import org.sdmlib.models.tables.Row;

public class TableSet extends SDMSet<Table>
{

   public TableSet()
   {
      // empty
   }

   public TableSet(Table... objects)
   {
      for (Table obj : objects)
      {
         this.add(obj);
      }
   }

   public TableSet(Collection<Table> objects)
   {
      this.addAll(objects);
   }

   public static final TableSet EMPTY_SET = new TableSet();


   public TablePO createTablePO()
   {
      return new TablePO(this.toArray(new Table[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.tables.Table";
   }


   @SuppressWarnings("unchecked")
   public TableSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Table>)value);
      }
      else if (value != null)
      {
         this.add((Table) value);
      }
      
      return this;
   }
   
   public TableSet without(Table value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Table objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Table obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Table objects and collect those Table objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Table objects that match the parameter
    */
   public TableSet createNameCondition(String value)
   {
      TableSet result = new TableSet();
      
      for (Table obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Table objects and collect those Table objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Table objects that match the parameter
    */
   public TableSet createNameCondition(String lower, String upper)
   {
      TableSet result = new TableSet();
      
      for (Table obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Table objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Table objects now with new attribute values.
    */
   public TableSet withName(String value)
   {
      for (Table obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Table objects and collect a set of the Column objects reached via columns. 
    * 
    * @return Set of Column objects reachable via columns
    */
   public ColumnSet getColumns()
   {
      ColumnSet result = new ColumnSet();
      
      for (Table obj : this)
      {
         result.with(obj.getColumns());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Table objects and collect all contained objects with reference columns pointing to the object passed as parameter. 
    * 
    * @param value The object required as columns neighbor of the collected results. 
    * 
    * @return Set of Column objects referring to value via columns
    */
   public TableSet filterColumns(Object value)
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
      
      TableSet answer = new TableSet();
      
      for (Table obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getColumns()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Table object passed as parameter to the Columns attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Columns attributes.
    */
   public TableSet withColumns(Column value)
   {
      for (Table obj : this)
      {
         obj.withColumns(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Table object passed as parameter from the Columns attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TableSet withoutColumns(Column value)
   {
      for (Table obj : this)
      {
         obj.withoutColumns(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Table objects and collect a set of the Row objects reached via rows. 
    * 
    * @return Set of Row objects reachable via rows
    */
   public RowSet getRows()
   {
      RowSet result = new RowSet();
      
      for (Table obj : this)
      {
         result.with(obj.getRows());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Table objects and collect all contained objects with reference rows pointing to the object passed as parameter. 
    * 
    * @param value The object required as rows neighbor of the collected results. 
    * 
    * @return Set of Row objects referring to value via rows
    */
   public TableSet filterRows(Object value)
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
      
      TableSet answer = new TableSet();
      
      for (Table obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getRows()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Table object passed as parameter to the Rows attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Rows attributes.
    */
   public TableSet withRows(Row value)
   {
      for (Table obj : this)
      {
         obj.withRows(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Table object passed as parameter from the Rows attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TableSet withoutRows(Row value)
   {
      for (Table obj : this)
      {
         obj.withoutRows(value);
      }
      
      return this;
   }

}
