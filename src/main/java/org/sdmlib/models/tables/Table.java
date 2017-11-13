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
import java.util.Iterator;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.models.tables.util.CellSet;
import org.sdmlib.models.tables.util.ColumnSet;
import org.sdmlib.models.tables.util.RowSet;

import de.uniks.networkparser.interfaces.SendableEntity;
import org.sdmlib.models.tables.Column;
import org.sdmlib.models.tables.Row;
/**
 * 
 * @see <a href='../../../../../../../src/main/java/org/sdmlib/models/tables/TableModel.java'>TableModel.java</a>
 * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/TableModel.java'>TableModel.java</a>
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

   public boolean removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      if (listeners == null) {
         listeners = new PropertyChangeSupport(this);
      }
      listeners.removePropertyChangeListener(propertyName, listener);
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
   
   public Column getColumn(String colName)
   {
      for (Column result : this.getColumns())
      {
         if (result.getName().equals(colName))
         {
            return result;
         }
      }
      return null;
   }

   public ColumnSet getColumns()
   {
      if (this.columns == null)
      {
         return ColumnSet.EMPTY_SET;
      }

      return this.columns;
   }
   
   public Table withColumns(String... columnNames)
   {
      for (String newName : columnNames)
      {
         this.createColumns().withName(newName);
      }
      return this;
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

               item.removeYou();

               firePropertyChange(PROPERTY_COLUMNS, item, null);
            }
         }
      }
      return this;
   }

   @FunctionalInterface
   public interface RowConsumer 
   {
      public Object exec (Row r);
   }

   public Column createColumns()
   {
      Column value = new Column();
      withColumns(value);
      return value;
   } 

   public Column createColumns(String columnName, RowConsumer cons)
   {
      Column newColumn = this.createColumns()
            .withName(columnName);

      for (Row row : this.getRows())
      {
         Object value = cons.exec(row);
         Cell newCell = row.createCells().withColumn(newColumn);
         newCell.setValue(value);
      }

      return newColumn;
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

   public Row createRows(Object... values)
   {
      Row newRow = new Row();
      withRows(newRow);

      // add cells for values
      int i = 0;
      Iterator<Column> iter = this.getColumns().iterator();
      while (i < values.length && iter.hasNext())
      {
         Column col = iter.next();

         newRow.createCells().withColumn(col).withValue(values[i]);

         i++;
      }

      return newRow;      
   } 

   public void withoutColumns(String... colNames)
   {
      for (String name : colNames)
      {
         for (Column col : this.getColumns())
         {
            if (col.getName().equals(name))
            {
               this.withoutColumns(col);
               break;
            }
         }
      }
   }

   public String getHtmlTable()
   {
      StringBuilder columnHeaders = new StringBuilder();

      for (Column col : this.getColumns())
      {
         String cssClass = "";
         String thCssText = col.getThCssClass();

         if (thCssText != null)
         {
            cssClass = "class=\"" + thCssText + "\"";
         }

         String colHeader = ""
               + "<th cssClass>colName</th>\n";

         colHeader = CGUtil.replaceAll(colHeader, 
            "cssClass", cssClass,
            "colName", col.getName()
               );

         columnHeaders.append(colHeader);
      }

      String tableHeaderText = ""
            + "<thead>\n"
            + "<tr>\n"
            + "columnHeaders"
            + "</tr>\n"
            + "</thead>\n";

      tableHeaderText = CGUtil.replaceAll(tableHeaderText,
         "columnHeaders", columnHeaders);


      StringBuilder allRowsBuf = new StringBuilder();

      for (Row row : this.getRows())
      {
         StringBuilder rowBuf = new StringBuilder();

         for (Cell cell : row.getCells())
         {

            String cssClass = ""; 
            String tdClass = cell.getColumn().getTdCssClass();

            if (tdClass != null) 
            {
               cssClass = "class=\""+ tdClass + "\"";
            }

            String cellText = "<td cssClass>cellValue</td>\n";

            Object value = cell.getValue();
            
            String cellValue = value.toString();

            if (value instanceof Table)
            {
               cellValue = ((Table) value).getHtmlTable();
            }

            cellText = CGUtil.replaceAll(cellText, 
               "cssClass", cssClass,
               "cellValue", cellValue
                  );

            rowBuf.append(cellText);
         }

         String rowLine = ""
               + "<tr>\n"
               + rowBuf
               + "</tr>\n";

         allRowsBuf.append(rowLine);
      }

      String tableRowsText = ""
            + "<tbody>\n"
            + allRowsBuf
            + "</tbody>\n";

      String tableText = ""
            + "<table style=\"width: auto;\" class=\"table table-bordered table-condensed\">\n"
            + "tableHeader\n"
            + "tableRows\n"
            + "</table>\n";

      tableText = CGUtil.replaceAll(tableText,
         "tableHeader", tableHeaderText,
         "tableRows", tableRowsText);

      return tableText;
   }

   public String getHtmlLineChart(String chartName)
   {
      
      
      StringBuilder labelsText = new StringBuilder("labels: [\"")
            .append(this.getColumns().first().getCells().toString("\", \""))
            .append("\"]");

      
      CellSet cells = this.getColumns().last().getCells();
      StringBuilder dataText = new StringBuilder("data: [")
            .append(cells.toString(", "))
            .append("]");
      
      

      StringBuilder chartText = new StringBuilder("" + 
            "    <div style=\"width:75%;\">\n" + 
            "        <canvas id=\"canvasid\"></canvas>\n" + 
            "    </div>\n" +
            "    <script>\n" + 
            "        \n" + 
            "        var config = {\n" + 
            "            type: 'line',\n" + 
            "            data: {\n" + 
            "                labels: [],\n" + 
            "                datasets: [{\n" + 
            "                    label: \"column2Name\",\n" + 
            "                    data: []\n" + 
            "                }]\n" + 
            "            },\n" + 
            "            options: {\n" + 
            "                // responsive: true,\n" + 
            "                hover: {\n" + 
            "                    mode: 'dataset'\n" + 
            "                },\n" + 
            "                scales: {\n" + 
            "                    xAxes: [{\n" + 
            "                        display: true,\n" + 
            "                        scaleLabel: {\n" + 
            "                            display: true,\n" + 
            "                            labelString: 'column1Name'\n" + 
            "                        }\n" + 
            "                    }],\n" + 
            "                    yAxes: [{\n" + 
            "                        display: true,\n" + 
            "                        scaleLabel: {\n" + 
            "                            display: true,\n" + 
            "                            labelString: 'column2Name'\n" + 
            "                        }\n" + 
            "                    }]\n" + 
            "                }" + 
            "            }\n" + 
            "        };\n" + 
            "\n" + 
            "       \n" + 
            "\n" + 
            "        var ctx = document.getElementById(\"canvasid\").getContext(\"2d\");\n" + 
            "        window.myLine = new Chart(ctx, config);\n" + 
            "\n" + 
            "\n" + 
            "    </script>\n"
            );


      CGUtil.replaceAll(chartText, 
         "canvasid", chartName,
         "labels: []", labelsText, 
         "data: []", dataText, 
         "column1Name", getColumns().first().getName(), 
         "column2Name", getColumns().last().getName());



      return chartText.toString();
   }

   public String getHtmlBarChart(String chartName)
   {
      
      
      StringBuilder labelsText = new StringBuilder("labels: [\"")
            .append(this.getColumns().first().getCells().toString("\", \""))
            .append("\"]");

      
      CellSet cells = this.getColumns().last().getCells();
      StringBuilder dataText = new StringBuilder("data: [")
            .append(cells.toString(", "))
            .append("]");
      
      

      StringBuilder chartText = new StringBuilder("" + 
            "    <div style=\"width:75%;\">\n" + 
            "        <canvas id=\"canvasid\"></canvas>\n" + 
            "    </div>\n" +
            "    <script>\n" + 
            "        \n" + 
            "        var config = {\n" + 
            "            type: 'bar',\n" + 
            "            data: {\n" + 
            "                labels: [],\n" + 
            "                datasets: [{\n" + 
            "                    label: \"column2Name\",\n" + 
            "                    data: []\n" + 
            "                }]\n" + 
            "            },\n" + 
            "            options: {\n" + 
            "                // responsive: true,\n" + 
            "                hover: {\n" + 
            "                    mode: 'dataset'\n" + 
            "                },\n" + 
            "                scales: {\n" + 
            "                    xAxes: [{\n" + 
            "                        display: true,\n" + 
            "                        scaleLabel: {\n" + 
            "                            display: true,\n" + 
            "                            labelString: 'column1Name'\n" + 
            "                        }\n" + 
            "                    }],\n" + 
            "                    yAxes: [{\n" + 
            "                        display: true,\n" + 
            "                        ticks: {\r\n" + 
            "                            beginAtZero:true\r\n" + 
            "                        },\n" + 
            "                        scaleLabel: {\n" + 
            "                            display: true,\n" + 
            "                            labelString: 'column2Name'\n" + 
            "                        }\n" + 
            "                    }]\n" + 
            "                }" + 
            "            }\n" + 
            "        };\n" + 
            "\n" + 
            "       \n" + 
            "\n" + 
            "        var ctx = document.getElementById(\"canvasid\").getContext(\"2d\");\n" + 
            "        window.myLine = new Chart(ctx, config);\n" + 
            "\n" + 
            "\n" + 
            "    </script>\n"
            );


      CGUtil.replaceAll(chartText, 
         "canvasid", chartName,
         "labels: []", labelsText, 
         "data: []", dataText, 
         "column1Name", getColumns().first().getName(), 
         "column2Name", getColumns().last().getName());



      return chartText.toString();
   }

   public String getCSV()
   {
      StringBuffer buf = new StringBuffer();
      
      String string = this.getColumns().getName().concat(";");
      buf.append(string).append("\n");
      
      for (Row r : getRows())
      {
         string = r.getCells().toString(";");
         buf.append(string).append("\n");
      }
      
      
      return buf.toString();
   }
}
