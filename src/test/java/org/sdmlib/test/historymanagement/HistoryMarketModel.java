package org.sdmlib.test.historymanagement;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import static de.uniks.networkparser.graph.Cardinality.*;
import de.uniks.networkparser.graph.Clazz;

import static de.uniks.networkparser.graph.DataType.*;

public class HistoryMarketModel
{
   /**
    * 
    * @see <a href='../../../../../../../doc/Name.html'>Name.html</a>
    * @see <a href='../../../../../../../doc/HistoryMarketModel.html'>HistoryMarketModel.html</a>
 */
   @Test
   public void HistoryMarketModel() throws Exception
   {
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
