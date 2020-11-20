package org.sdmlib.test.examples.simpleEnumModel;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.GraphUtil;
import de.uniks.networkparser.graph.Literal;
import de.uniks.networkparser.graph.Parameter;

public class SimpleClassModelWithEnumeration {
  /**
   * <p>
   * Storyboard <a href=
   * './src/test/java/org/sdmlib/test/examples/simpleEnumModel/SimpleClassModelWithEnumeration.java'
   * type='text/x-java'>EnumerationInSimpleClassModel</a>
   * </p>
   * 
   * <pre>
   * ClassModel model = new ClassModel(&quot;org.sdmlib.test.examples.simpleEnumModel.model&quot;);
   * 
   * Clazz enumeration = model.createClazz(&quot;TEnum&quot;).enableEnumeration();
   * 
   * GraphUtil.setLiteral(enumeration, new Literal(&quot;T1&quot;),
   *     new Literal(&quot;T2&quot;),
   *     new Literal(&quot;12&quot;),
   *     new Literal(&quot;T1000&quot;));
   * enumeration.withMethod(&quot;toString&quot;, DataType.STRING);
   * 
   * Clazz alexClazz = model.createClazz(&quot;Alex&quot;);
   * alexClazz.withAttribute(&quot;Name&quot;, DataType.STRING);
   * 
   * Clazz macClazz = model.createClazz(&quot;Mac&quot;);
   * macClazz.withAttribute(&quot;Name&quot;, DataType.STRING)
   *     .withAttribute(&quot;type&quot;, DataType.create(enumeration))
   *     .withAttribute(&quot;owner&quot;, DataType.create(alexClazz));
   * 
   * macClazz.withMethod(&quot;concat&quot;, DataType.STRING, new Parameter(DataType.INT));
   * macClazz.withMethod(&quot;select&quot;, DataType.create(enumeration), new Parameter(DataType.INT));
   * 
   * model.generate(&quot;src&#x2F;test&#x2F;java&quot;);
   * </pre>
   * 
   * <script> var json = { "typ":"class", "nodes":[ { "typ":"node", "id":"Alex", "attributes":[ "Name
   * : String" ] }, { "typ":"node", "id":"Mac", "attributes":[ "Name : String", "owner : Alex", "type
   * : TEnum" ], "methods":[ "concat(int p1) String", "select(int p1)
   * org.sdmlib.test.examples.simpleEnumModel.model.TEnum" ] }, { "typ":"node", "id":"TEnum",
   * "methods":[ "TEnum() ", "toString() String" ] } ], "edges":[] } ; new Graph(json,
   * {"canvasid":"canvasEnumerationInSimpleClassModelClassDiagram1", "display":"html", fontsize:10,
   * bar:false, propertyinfo:false}).layout(100,100); </script>
   * <p>
   * Check: Number of Enumeration types in the model: 1 actual 1
   * </p>
   * 
   * @see <a href=
   *      '../../../../../../../../doc/EnumerationInSimpleClassModel.html'>EnumerationInSimpleClassModel.html</a>
   */
  // @Test
  public void testEnumerationInSimpleClassModel() {
    Storyboard story = new Storyboard();

    story.markCodeStart();
    ClassModel model = new ClassModel("org.sdmlib.test.examples.simpleEnumModel.model");

    Clazz enumeration = model.createClazz("TEnum").enableEnumeration();

    GraphUtil.setLiteral(enumeration, new Literal("T1"),
        new Literal("T2"),
        new Literal("12"),
        new Literal("T1000"));
    enumeration.withMethod("toString", DataType.STRING);

    Clazz alexClazz = model.createClazz("Alex");
    alexClazz.withAttribute("Name", DataType.STRING);

    Clazz macClazz = model.createClazz("Mac");
    macClazz.withAttribute("Name", DataType.STRING)
        .withAttribute("type", DataType.create(enumeration))
        .withAttribute("owner", DataType.create(alexClazz));

    macClazz.withMethod("concat", DataType.STRING, new Parameter(DataType.INT));
    macClazz.withMethod("select", DataType.create(enumeration), new Parameter(DataType.INT));

    model.generate("src/test/java");
    story.addCode();

    story.addClassDiagram(model);

    story.assertEquals("Number of Enumeration types in the model: ", 1, model.getEnumerations().size());

    story.dumpHTML();

  }
}
