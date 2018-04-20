package org.sdmlib.models.tables;

import static de.uniks.networkparser.graph.Cardinality.MANY;
import static de.uniks.networkparser.graph.Cardinality.ONE;
import static de.uniks.networkparser.graph.DataType.INT;
import static de.uniks.networkparser.graph.DataType.STRING;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardImpl;

import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class TableModel
{
   /**
    * <p>Storyboard <a href='./src/main/java/org/sdmlib/models/tables/TableModel.java' type='text/x-java'>TableModel</a></p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"Cell",
    *          "attributes":[
    *             "value : Object"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Column",
    *          "attributes":[
    *             "name : String",
    *             "tdCssClass : String",
    *             "thCssClass : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Row",
    *          "attributes":[
    *             "number : int"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Table",
    *          "attributes":[
    *             "name : String"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Cell",
    *             "cardinality":"many",
    *             "property":"cells"
    *          },
    *          "target":{
    *             "id":"Column",
    *             "cardinality":"one",
    *             "property":"column"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Cell",
    *             "cardinality":"many",
    *             "property":"cells"
    *          },
    *          "target":{
    *             "id":"Row",
    *             "cardinality":"one",
    *             "property":"row"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Column",
    *             "cardinality":"one",
    *             "property":"column"
    *          },
    *          "target":{
    *             "id":"Cell",
    *             "cardinality":"many",
    *             "property":"cells"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Column",
    *             "cardinality":"many",
    *             "property":"columns"
    *          },
    *          "target":{
    *             "id":"Table",
    *             "cardinality":"one",
    *             "property":"table"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Row",
    *             "cardinality":"one",
    *             "property":"row"
    *          },
    *          "target":{
    *             "id":"Cell",
    *             "cardinality":"many",
    *             "property":"cells"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Row",
    *             "cardinality":"many",
    *             "property":"rows"
    *          },
    *          "target":{
    *             "id":"Table",
    *             "cardinality":"one",
    *             "property":"table"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Table",
    *             "cardinality":"one",
    *             "property":"table"
    *          },
    *          "target":{
    *             "id":"Column",
    *             "cardinality":"many",
    *             "property":"columns"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Table",
    *             "cardinality":"one",
    *             "property":"table"
    *          },
    *          "target":{
    *             "id":"Row",
    *             "cardinality":"many",
    *             "property":"rows"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasTableModelClassDiagram0", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * @see <a href='../../../../../../../doc/TableModel.html'>TableModel.html</a>
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
