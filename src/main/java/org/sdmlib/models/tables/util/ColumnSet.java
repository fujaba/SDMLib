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
import org.sdmlib.models.tables.Column;
import java.util.Collection;
import de.uniks.networkparser.list.StringList;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.models.tables.util.TableSet;
import org.sdmlib.models.tables.Table;
import java.util.Collections;
import org.sdmlib.models.tables.util.CellSet;
import org.sdmlib.models.tables.Cell;
import de.uniks.networkparser.interfaces.Condition;

public class ColumnSet extends SDMSet<Column>
{

   public ColumnSet()
   {
      // empty
   }

   public ColumnSet(Column... objects)
   {
      for (Column obj : objects)
      {
         this.add(obj);
      }
   }

   public ColumnSet(Collection<Column> objects)
   {
      this.addAll(objects);
   }

   public static final ColumnSet EMPTY_SET = new ColumnSet();


   public ColumnPO createColumnPO()
   {
      return new ColumnPO(this.toArray(new Column[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.tables.Column";
   }


   @SuppressWarnings("unchecked")
   public ColumnSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Column>)value);
      }
      else if (value != null)
      {
         this.add((Column) value);
      }
      
      return this;
   }
   
   public ColumnSet without(Column value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Column objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Column obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Column objects and collect those Column objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Column objects that match the parameter
    */
   public ColumnSet createNameCondition(String value)
   {
      ColumnSet result = new ColumnSet();
      
      for (Column obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Column objects and collect those Column objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Column objects that match the parameter
    */
   public ColumnSet createNameCondition(String lower, String upper)
   {
      ColumnSet result = new ColumnSet();
      
      for (Column obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Column objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Column objects now with new attribute values.
    */
   public ColumnSet withName(String value)
   {
      for (Column obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Column objects and collect a list of the tdCssClass attribute values. 
    * 
    * @return List of String objects reachable via tdCssClass attribute
    */
   public StringList getTdCssClass()
   {
      StringList result = new StringList();
      
      for (Column obj : this)
      {
         result.add(obj.getTdCssClass());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Column objects and collect those Column objects where the tdCssClass attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Column objects that match the parameter
    */
   public ColumnSet createTdCssClassCondition(String value)
   {
      ColumnSet result = new ColumnSet();
      
      for (Column obj : this)
      {
         if (value.equals(obj.getTdCssClass()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Column objects and collect those Column objects where the tdCssClass attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Column objects that match the parameter
    */
   public ColumnSet createTdCssClassCondition(String lower, String upper)
   {
      ColumnSet result = new ColumnSet();
      
      for (Column obj : this)
      {
         if (lower.compareTo(obj.getTdCssClass()) <= 0 && obj.getTdCssClass().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Column objects and assign value to the tdCssClass attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Column objects now with new attribute values.
    */
   public ColumnSet withTdCssClass(String value)
   {
      for (Column obj : this)
      {
         obj.setTdCssClass(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Column objects and collect a list of the thCssClass attribute values. 
    * 
    * @return List of String objects reachable via thCssClass attribute
    */
   public StringList getThCssClass()
   {
      StringList result = new StringList();
      
      for (Column obj : this)
      {
         result.add(obj.getThCssClass());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Column objects and collect those Column objects where the thCssClass attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Column objects that match the parameter
    */
   public ColumnSet createThCssClassCondition(String value)
   {
      ColumnSet result = new ColumnSet();
      
      for (Column obj : this)
      {
         if (value.equals(obj.getThCssClass()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Column objects and collect those Column objects where the thCssClass attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Column objects that match the parameter
    */
   public ColumnSet createThCssClassCondition(String lower, String upper)
   {
      ColumnSet result = new ColumnSet();
      
      for (Column obj : this)
      {
         if (lower.compareTo(obj.getThCssClass()) <= 0 && obj.getThCssClass().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Column objects and assign value to the thCssClass attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Column objects now with new attribute values.
    */
   public ColumnSet withThCssClass(String value)
   {
      for (Column obj : this)
      {
         obj.setThCssClass(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Column objects and collect a set of the Table objects reached via table. 
    * 
    * @return Set of Table objects reachable via table
    */
   public TableSet getTable()
   {
      TableSet result = new TableSet();
      
      for (Column obj : this)
      {
         result.with(obj.getTable());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Column objects and collect all contained objects with reference table pointing to the object passed as parameter. 
    * 
    * @param value The object required as table neighbor of the collected results. 
    * 
    * @return Set of Table objects referring to value via table
    */
   public ColumnSet filterTable(Object value)
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
      
      ColumnSet answer = new ColumnSet();
      
      for (Column obj : this)
      {
         if (neighbors.contains(obj.getTable()) || (neighbors.isEmpty() && obj.getTable() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Column object passed as parameter to the Table attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Table attributes.
    */
   public ColumnSet withTable(Table value)
   {
      for (Column obj : this)
      {
         obj.withTable(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Column objects and collect a set of the Cell objects reached via cells. 
    * 
    * @return Set of Cell objects reachable via cells
    */
   public CellSet getCells()
   {
      CellSet result = new CellSet();
      
      for (Column obj : this)
      {
         result.with(obj.getCells());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Column objects and collect all contained objects with reference cells pointing to the object passed as parameter. 
    * 
    * @param value The object required as cells neighbor of the collected results. 
    * 
    * @return Set of Cell objects referring to value via cells
    */
   public ColumnSet filterCells(Object value)
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
      
      ColumnSet answer = new ColumnSet();
      
      for (Column obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getCells()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Column object passed as parameter to the Cells attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Cells attributes.
    */
   public ColumnSet withCells(Cell value)
   {
      for (Column obj : this)
      {
         obj.withCells(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Column object passed as parameter from the Cells attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public ColumnSet withoutCells(Cell value)
   {
      for (Column obj : this)
      {
         obj.withoutCells(value);
      }
      
      return this;
   }



   @Override
   public ColumnSet getNewList(boolean keyValue)
   {
      return new ColumnSet();
   }


   public ColumnSet filter(Condition<Column> condition) {
      ColumnSet filterList = new ColumnSet();
      filterItems(filterList, condition);
      return filterList;
   }
   /**
    * Loop through the current set of Column objects and collect those Column objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Column objects that match the parameter
    */
   public ColumnSet filterName(String value)
   {
      ColumnSet result = new ColumnSet();
      
      for (Column obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Column objects and collect those Column objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Column objects that match the parameter
    */
   public ColumnSet filterName(String lower, String upper)
   {
      ColumnSet result = new ColumnSet();
      
      for (Column obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Column objects and collect those Column objects where the tdCssClass attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Column objects that match the parameter
    */
   public ColumnSet filterTdCssClass(String value)
   {
      ColumnSet result = new ColumnSet();
      
      for (Column obj : this)
      {
         if (value.equals(obj.getTdCssClass()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Column objects and collect those Column objects where the tdCssClass attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Column objects that match the parameter
    */
   public ColumnSet filterTdCssClass(String lower, String upper)
   {
      ColumnSet result = new ColumnSet();
      
      for (Column obj : this)
      {
         if (lower.compareTo(obj.getTdCssClass()) <= 0 && obj.getTdCssClass().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Column objects and collect those Column objects where the thCssClass attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Column objects that match the parameter
    */
   public ColumnSet filterThCssClass(String value)
   {
      ColumnSet result = new ColumnSet();
      
      for (Column obj : this)
      {
         if (value.equals(obj.getThCssClass()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Column objects and collect those Column objects where the thCssClass attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Column objects that match the parameter
    */
   public ColumnSet filterThCssClass(String lower, String upper)
   {
      ColumnSet result = new ColumnSet();
      
      for (Column obj : this)
      {
         if (lower.compareTo(obj.getThCssClass()) <= 0 && obj.getThCssClass().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
