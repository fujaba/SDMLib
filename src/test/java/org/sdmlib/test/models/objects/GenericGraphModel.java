package org.sdmlib.test.models.objects;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

/**
 * 
 * @see <a href='../../../../../../../../src/test/java/org/sdmlib/test/models/objects/GenericObjectsTest.java'>GenericObjectsTest.java</a>
 * @see org.sdmlib.test.models.objects.GenericObjectsTest#testGenericObjectDiagram
 */
public class GenericGraphModel
{
   public static ClassModel genericModel = null;


   /**
    * 
    * <p>Storyboard <a href='.././src/test/java/org/sdmlib/test/models/objects/GenericGraphModel.java' type='text/x-java'>GenericGraphModel</a></p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"GenericAttribute",
    *          "attributes":[
    *             "name : String",
    *             "value : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"GenericGraph"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"GenericLink",
    *          "attributes":[
    *             "srcLabel : String",
    *             "tgtLabel : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"GenericObject",
    *          "attributes":[
    *             "icon : String",
    *             "name : String",
    *             "type : String"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericAttribute",
    *             "cardinality":"many",
    *             "property":"attrs"
    *          },
    *          "target":{
    *             "id":"GenericObject",
    *             "cardinality":"one",
    *             "property":"owner"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericGraph",
    *             "cardinality":"one",
    *             "property":"graph"
    *          },
    *          "target":{
    *             "id":"GenericObject",
    *             "cardinality":"many",
    *             "property":"objects"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericGraph",
    *             "cardinality":"one",
    *             "property":"graph"
    *          },
    *          "target":{
    *             "id":"GenericLink",
    *             "cardinality":"many",
    *             "property":"links"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericLink",
    *             "cardinality":"many",
    *             "property":"incommingLinks"
    *          },
    *          "target":{
    *             "id":"GenericObject",
    *             "cardinality":"one",
    *             "property":"tgt"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericLink",
    *             "cardinality":"many",
    *             "property":"links"
    *          },
    *          "target":{
    *             "id":"GenericGraph",
    *             "cardinality":"one",
    *             "property":"graph"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericLink",
    *             "cardinality":"many",
    *             "property":"outgoingLinks"
    *          },
    *          "target":{
    *             "id":"GenericObject",
    *             "cardinality":"one",
    *             "property":"src"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericObject",
    *             "cardinality":"many",
    *             "property":"objects"
    *          },
    *          "target":{
    *             "id":"GenericGraph",
    *             "cardinality":"one",
    *             "property":"graph"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericObject",
    *             "cardinality":"one",
    *             "property":"owner"
    *          },
    *          "target":{
    *             "id":"GenericAttribute",
    *             "cardinality":"many",
    *             "property":"attrs"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericObject",
    *             "cardinality":"one",
    *             "property":"src"
    *          },
    *          "target":{
    *             "id":"GenericLink",
    *             "cardinality":"many",
    *             "property":"outgoingLinks"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericObject",
    *             "cardinality":"one",
    *             "property":"tgt"
    *          },
    *          "target":{
    *             "id":"GenericLink",
    *             "cardinality":"many",
    *             "property":"incommingLinks"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasGenericGraphModelClassDiagram0", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * @see <a href='../../../../../../../../doc/GenericGraphModel.html'>GenericGraphModel.html</a>
    * @see <a href='../../../../../../../../genericgraphs/GenericGraphModel.html'>GenericGraphModel.html</a>
 * @see <a href='../../../../../../../../doc/genericgraphs/GenericGraphModel.html'>GenericGraphModel.html</a>
 */
   @Test
   public void testGenericGraphModel()
   {
      Storyboard storyboard = new Storyboard().withDocDirName("doc/genericgraphs");

      genericModel = new ClassModel("org.sdmlib.models.objects");

      Clazz genericGraph = genericModel.createClazz("GenericGraph");

      Clazz genericObjectClazz = genericModel.createClazz("GenericObject")
         .withAttribute("name", DataType.STRING)
         .withAttribute("type", DataType.STRING)
         .withAttribute("icon", DataType.STRING);

      genericGraph.withBidirectional(genericObjectClazz, "objects", Cardinality.MANY, "graph", Cardinality.ONE);

      Clazz genericAttributeClazz = genericModel.createClazz("GenericAttribute")
         .withAttribute("name", DataType.STRING)
         .withAttribute("value", DataType.STRING);

      genericObjectClazz.withBidirectional(genericAttributeClazz, "attrs", Cardinality.MANY, "owner", Cardinality.ONE);

      Clazz genericLinkClazz = genericModel.createClazz("GenericLink")
         .withAttribute("tgtLabel", DataType.STRING)
         .withAttribute("srcLabel", DataType.STRING);

      genericObjectClazz.withBidirectional(genericLinkClazz, "outgoingLinks", Cardinality.MANY, "src", Cardinality.ONE);
      genericObjectClazz.withBidirectional(genericLinkClazz, "incommingLinks", Cardinality.MANY, "tgt", Cardinality.ONE);

      genericGraph.withBidirectional(genericLinkClazz, "links", Cardinality.MANY, "graph", Cardinality.ONE);

      storyboard.addClassDiagram(genericModel);

      // genericModel.removeAllGeneratedCode("test", "src", "srchelpers");

      genericModel.generate("src/main/java");

      storyboard.dumpHTML();

   }
}
