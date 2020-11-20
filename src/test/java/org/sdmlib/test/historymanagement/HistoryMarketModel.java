package org.sdmlib.test.historymanagement;

import static de.uniks.networkparser.graph.Association.MANY;
import static de.uniks.networkparser.graph.Association.ONE;
import static de.uniks.networkparser.graph.DataType.DOUBLE;
import static de.uniks.networkparser.graph.DataType.STRING;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;
import de.uniks.networkparser.graph.Clazz;

public class HistoryMarketModel {
  /**
   * 
   * <p>
   * Storyboard
   * <a href='./src/test/java/org/sdmlib/test/historymanagement/HistoryMarketModel.java' type=
   * 'text/x-java'>HistoryMarketModel</a>
   * </p>
   * <script> var json = { "typ":"class", "nodes":[ { "typ":"node", "id":"Actor", "attributes":[
   * "actorName : String" ] }, { "typ":"node", "id":"Bid", "attributes":[ "amount : double" ] }, {
   * "typ":"node", "id":"Market", "attributes":[ "marketTime : String" ] }, { "typ":"node",
   * "id":"Offer", "attributes":[ "description : String", "timeLimit : String" ] } ], "edges":[ {
   * "typ":"assoc", "source":{ "id":"Actor", "cardinality":"one", "property":"bidder" }, "target":{
   * "id":"Bid", "cardinality":"many", "property":"bids" } }, { "typ":"assoc", "source":{
   * "id":"Actor", "cardinality":"one", "property":"owner" }, "target":{ "id":"Offer",
   * "cardinality":"many", "property":"offers" } }, { "typ":"assoc", "source":{ "id":"Bid",
   * "cardinality":"many", "property":"bids" }, "target":{ "id":"Actor", "cardinality":"one",
   * "property":"bidder" } }, { "typ":"assoc", "source":{ "id":"Bid", "cardinality":"many",
   * "property":"bids" }, "target":{ "id":"Offer", "cardinality":"one", "property":"offer" } }, {
   * "typ":"assoc", "source":{ "id":"Market", "cardinality":"one", "property":"market" }, "target":{
   * "id":"Offer", "cardinality":"many", "property":"offers" } }, { "typ":"assoc", "source":{
   * "id":"Offer", "cardinality":"one", "property":"offer" }, "target":{ "id":"Bid",
   * "cardinality":"many", "property":"bids" } }, { "typ":"assoc", "source":{ "id":"Offer",
   * "cardinality":"many", "property":"offers" }, "target":{ "id":"Market", "cardinality":"one",
   * "property":"market" } }, { "typ":"assoc", "source":{ "id":"Offer", "cardinality":"many",
   * "property":"offers" }, "target":{ "id":"Actor", "cardinality":"one", "property":"owner" } } ] } ;
   * new Graph(json, {"canvasid":"canvasHistoryMarketModelClassDiagram0", "display":"html",
   * fontsize:10, bar:false, propertyinfo:false}).layout(100,100); </script>
   * 
   * @see <a href='../../../../../../../doc/Name.html'>Name.html</a>
   * @see <a href='../../../../../../../doc/HistoryMarketModel.html'>HistoryMarketModel.html</a>
   */
  // @Test
  public void HistoryMarketModel() throws Exception {
    Storyboard story = new Storyboard();

    ClassModel model = new ClassModel("org.sdmlib.test.historymanagement.marketmodel");

    Clazz actor = model.createClazz("Actor")
        .withAttribute("actorName", STRING);

    Clazz bid = model.createClazz("Bid")
        .withAttribute("amount", DOUBLE);
    actor.withBidirectional(bid, "bids", MANY, "bidder", ONE);

    Clazz market = model.createClazz("Market")
        .withAttribute("marketTime", STRING);

    Clazz offer = model.createClazz("Offer")
        .withAttribute("description", STRING)
        .withAttribute("timeLimit", STRING);
    market.withBidirectional(offer, "offers", MANY, "market", ONE);
    actor.withBidirectional(offer, "offers", MANY, "owner", ONE);
    bid.withBidirectional(offer, "offer", ONE, "bids", MANY);

    model.generate("src/test/java");

    story.addClassDiagram(model);

    story.dumpHTML();
  }
}
