package org.sdmlib.test.model;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;
import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Parameter;

public class ModelRefactoring {

  /**
   * 
   * <p>
   * Storyboard <a href='./src/test/java/org/sdmlib/test/model/ModelRefactoring.java' type=
   * 'text/x-java'>RemoveAttribute</a>
   * </p>
   * <script> var json = { "typ":"class", "nodes":[ { "typ":"node", "id":"Ludo", "attributes":[
   * "location : String" ], "methods":[ "init(String p)" ] }, { "typ":"node", "id":"Player" } ],
   * "edges":[ { "typ":"assoc", "source":{ "id":"Ludo", "cardinality":"one", "property":"game" },
   * "target":{ "id":"Player", "cardinality":"many", "property":"players" } }, { "typ":"assoc",
   * "source":{ "id":"Player", "cardinality":"many", "property":"players" }, "target":{ "id":"Ludo",
   * "cardinality":"one", "property":"game" } } ] } ; new Graph(json,
   * {"canvasid":"canvasRemoveAttributeClassDiagram0", "display":"html", fontsize:10, bar:false,
   * propertyinfo:false}).layout(100,100); </script>
   * 
   * @see <a href='../../../../../../../doc/RemoveAttribute.html'>RemoveAttribute.html</a>/n
   */
  // @Test
  public void testRemoveAttribute() {

    Storyboard story = new Storyboard();

    ClassModel model = new ClassModel("org.sdmlib.test.model.refactoring");

    Clazz ludo = model.createClazz("Ludo");

    Clazz player = model.createClazz("Player");

    ludo.withAttribute("location", DataType.STRING);

    ludo.withMethod("init", DataType.VOID, new Parameter(DataType.STRING).with("p"));

    ludo.withBidirectional(player, "players", Association.MANY, "game", Association.ONE);

    ludo.getMethods().first().withBody("     System.out.println(\"Hallo\");\n");

    // model.removeAllGeneratedCode("src/test/java");

    // model.generate("src/test/java");

    // ludo.getAttributes().hasName("location").removeFromModelAndCode("src/test/java");

    // ludo.getMethods().hasName("init").removeFromModelAndCode("src/test/java");

    // ludo.getRoles().getAssoc().removeFromModelAndCode("src/test/java");

    // model.getClasses().hasName("Ludo").first().removeFromModelAndCode("src/test/java");

    model.generate("src/test/java");

    story.addClassDiagram(model);

    story.dumpHTML();

  }

}
