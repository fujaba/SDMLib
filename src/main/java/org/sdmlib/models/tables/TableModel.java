package org.sdmlib.models.tables;

import static de.uniks.networkparser.graph.Association.MANY;
import static de.uniks.networkparser.graph.Association.ONE;
import static de.uniks.networkparser.graph.DataType.INT;
import static de.uniks.networkparser.graph.DataType.STRING;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class TableModel
{
   /**
    * 
    * <h3>Storyboard TableModel</h3>
    * <img src="doc-files/TableModelStep0.png" alt="TableModelStep0.png">
    */
   @Test
   public void testTableModel()
   {
      Storyboard story = new Storyboard();

      ClassModel model = new ClassModel("org.sdmlib.models.tables");

      Clazz tableClass = model.createClazz("Table")
         .withAttribute("name", STRING);

      Clazz rowClass = model.createClazz("Row")
         .withAttribute("number", INT);

      Clazz columnClass = model.createClazz("Column")
         .withAttribute("name", STRING)
         .withAttribute("tdCssClass", STRING)
         .withAttribute("thCssClass", STRING);

      Clazz cellClass = model.createClazz("Cell")
         .withAttribute("value", DataType.OBJECT);

      tableClass.withBidirectional(columnClass, "columns", MANY, "table", ONE);
      tableClass.withBidirectional(rowClass, "rows", MANY, "table", ONE);
      columnClass.withBidirectional(cellClass, "cells", MANY, "column", ONE);
      rowClass.withBidirectional(cellClass, "cells", MANY, "row", ONE);

      story.addClassDiagram(model);

      model.generate();

      story.dumpHTML();
   }
}
