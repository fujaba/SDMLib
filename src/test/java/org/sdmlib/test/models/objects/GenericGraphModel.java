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
 */
public class GenericGraphModel
{
   public static ClassModel genericModel = null;


   /**
    * 
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
