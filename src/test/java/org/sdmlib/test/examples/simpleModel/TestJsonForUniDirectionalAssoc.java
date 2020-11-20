package org.sdmlib.test.examples.simpleModel;

import org.sdmlib.models.debug.FlipBook;
import org.sdmlib.serialization.SDMLibJsonIdMap;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.simpleModel.model.BigBrother;
import org.sdmlib.test.examples.simpleModel.model.Person;
import org.sdmlib.test.examples.simpleModel.model.util.BigBrotherCreator;

public class TestJsonForUniDirectionalAssoc {
  /**
   * 
   * <p>
   * Storyboard <a href=
   * './src/test/java/org/sdmlib/test/examples/simpleModel/TestJsonForUniDirectionalAssoc.java' type=
   * 'text/x-java'>UniDirectionalAssocJson</a>
   * </p>
   * <p>
   * adding flipbook to protocol changes.
   * </p>
   * 
   * <pre>
   * SDMLibJsonIdMap idMap = (SDMLibJsonIdMap) BigBrotherCreator.createIdMap(&quot;#&quot;);
   * FlipBook flipBook = idMap.createFlipBook();
   * 
   * BigBrother bigBrother = new BigBrother();
   * idMap.put(&quot;#bb&quot;, bigBrother);
   * </pre>
   * <p>
   * extend uni directional assco with objects and check flipbook for change protocol
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B1 : BigBrother"
   * }, { "type":"clazz", "id":"P2 : Person", "attributes":[ "name=Tom" ] }, { "type":"clazz",
   * "id":"P3 : Person", "attributes":[ "name=Jessica" ] }, { "type":"clazz", "id":"P4 : Person",
   * "attributes":[ "name=Albert" ] }, { "type":"clazz", "id":"P5 : Person", "attributes":[
   * "name=Sabine" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"many",
   * "property":"kids", "id":"P2 : Person" }, "target":{ "cardinality":"one", "property":"bigbrother",
   * "id":"B1 : BigBrother" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"kids",
   * "id":"P3 : Person" }, "target":{ "cardinality":"one", "property":"bigbrother", "id":"B1 :
   * BigBrother" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"noOne", "id":"P4 :
   * Person" }, "target":{ "cardinality":"one", "property":"bigbrother", "id":"B1 : BigBrother" } }, {
   * "type":"edge", "source":{ "cardinality":"many", "property":"suspects", "id":"P5 : Person" },
   * "target":{ "cardinality":"one", "property":"bigbrother", "id":"B1 : BigBrother" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirectionalAssocJson4", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * @see <a href=
   *      '../../../../../../../../doc/UniDirectionalAssocJson.html'>UniDirectionalAssocJson.html</a>
   */
  // @Test
  public void testUniDirectionalAssocJson() {
    Storyboard story = new Storyboard();

    // =============================================================
    story.add("adding flipbook to protocol changes.");

    story.markCodeStart();
    SDMLibJsonIdMap idMap = (SDMLibJsonIdMap) BigBrotherCreator.createIdMap("#");
    FlipBook flipBook = idMap.createFlipBook();

    BigBrother bigBrother = new BigBrother();
    idMap.put("#bb", bigBrother);
    story.addCode();

    // story.withMap(idMap);

    // =============================================================
    story.add("extend uni directional assco with objects and check flipbook for change protocol");

    Person albert = bigBrother.createNoOne().withName("Albert");
    Person sabine = bigBrother.createSuspects().withName("Sabine");

    Person tom = new Person().withName("Tom");
    Person jessi = new Person().withName("Jessi");
    bigBrother.withKids(tom, jessi);

    jessi.withName("Jessica");

    story.addObjectDiagram(bigBrother);


    story.dumpHTML();
  }
}
