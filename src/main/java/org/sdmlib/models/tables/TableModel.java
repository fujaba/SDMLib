package org.sdmlib.models.tables;

import static org.junit.Assert.*;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.StoryboardImpl;

import static de.uniks.networkparser.graph.Cardinality.*;
import de.uniks.networkparser.graph.Clazz;

import static de.uniks.networkparser.graph.DataType.*;

public class TableModel
{
   /**
    * 
    * @see <a href='../../../../../../../doc/TableModel.html'>TableModel.html</a>
    */
   @Test
   public void testTableModel() throws Exception
   {
      StoryboardImpl story = new StoryboardImpl();

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
         .withAttribute("value", OBJECT);

      tableClass.withBidirectional(columnClass, "columns", MANY, "table", ONE);
      tableClass.withBidirectional(rowClass, "rows", MANY, "table", ONE);
      columnClass.withBidirectional(cellClass, "cells", MANY, "column", ONE);
      rowClass.withBidirectional(cellClass, "cells", MANY, "row", ONE);

      story.addClassDiagram(model);

      model.generate();

      story.dumpHTML();
   }
}
