package org.sdmlib.test.historymanagement;


import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.historymanagement.marketmodel.Actor;
import org.sdmlib.test.historymanagement.marketmodel.Market;
import org.sdmlib.test.historymanagement.marketmodel.Offer;
import org.sdmlib.test.historymanagement.marketmodel.util.MarketCreator;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.SimpleEvent;
import de.uniks.networkparser.interfaces.ObjectCondition;
import de.uniks.networkparser.json.JsonObject;

public class IdMapRigorousTests
{
   /**
    * 
    * <p>Storyboard <a href='.././src/test/java/org/sdmlib/test/historymanagement/IdMapRigorousTests.java' type='text/x-java'>updateOnNewObjectAttributes</a></p>
    * <p>Check: change message should contain 00:00 true</p>
    */
   @Test
   public void updateOnNewObjectAttributes() throws Exception
   {
      Storyboard story = new Storyboard().withDocDirName("doc/rigorous");
      
      buf = new StringBuffer();
      IdMap idMap = MarketCreator.createIdMap("ebay");
      
      idMap.withListener((ObjectCondition) value -> handleUpdate(value));
      
      Market market = new Market();
      market.withMarketTime("00:00");
      String id = idMap.getId(market);
      
      story.assertTrue("change message should contain 00:00", buf.indexOf("00:00") >= 0);

      story.dumpHTML();
   }

   
   /**
    * 
    * <p>Storyboard <a href='.././src/test/java/org/sdmlib/test/historymanagement/IdMapRigorousTests.java' type='text/x-java'>updateOnNewComplexNeighbors</a></p>
    * <p>Check: change message should contain 00:00 true</p>
    * <p>Check: change message should contain Bid true</p>
    * <p>Check: change message should contain 42.23 true</p>
    * <p>Check: change message should contain Starbucks true</p>
    */
   @Test
   public void updateOnNewComplexNeighbors() throws Exception
   {
      Storyboard story = new Storyboard().withDocDirName("doc/rigorous");
      
      buf = new StringBuffer();
      
      IdMap idMap = MarketCreator.createIdMap("ebay");
      
      idMap.withListener((ObjectCondition) value -> handleUpdate(value));
      
      Market market = new Market();
      String id = idMap.getId(market);
      market.withMarketTime("00:00");
      
      story.assertTrue("change message should contain 00:00", buf.indexOf("00:00") >= 0);

      Offer coffeeOffer = new Offer()
            .withDescription("Coffee 1 pound")
            .withTimeLimit("12:30");
      
      Actor starbucks = new Actor()
            .withActorName("Starbucks");
      
      starbucks.createBids().withAmount(42.23);

      coffeeOffer.withOwner(starbucks);

      market.withOffers(coffeeOffer);
      
      story.assertTrue("change message should contain Bid", buf.indexOf(".Bid") >= 0);
      story.assertTrue("change message should contain 42.23", buf.indexOf("42.23") >= 0);
      story.assertTrue("change message should contain Starbucks", buf.indexOf("Starbucks") >= 0);

      
      story.dumpHTML();
   }

   
   /**
    * 
    * <p>Storyboard <a href='.././src/test/java/org/sdmlib/test/historymanagement/IdMapRigorousTests.java' type='text/x-java'>updateOnObjectRemoval</a></p>
    * <p>Check: change message should contain "rem":{} true</p>
    */
   @Test
   public void updateOnObjectRemoval() throws Exception
   {
      Storyboard story = new Storyboard().withDocDirName("doc/rigorous");
      
      buf = new StringBuffer();
      IdMap idMap = MarketCreator.createIdMap("ebay");
      
      idMap.withListener((ObjectCondition) value -> handleUpdate(value));
      
      Market market = new Market();
      market.withMarketTime("00:00");
      String id = idMap.getId(market);
      
      market.removeYou();
      
      story.assertTrue("change message should contain \"rem\":{}", buf.indexOf("\"rem\":{}") >= 0);

      story.dumpHTML();
   }


   
   private StringBuffer buf = new StringBuffer();
   
   private boolean handleUpdate(Object value)
   {
      SimpleEvent evt = (SimpleEvent) value;
      
      JsonObject json = (JsonObject) evt.getEntity();
      
      if (json == null)
      {
         return true;
      }
      
      String text = json.toString(3) + "\n";
      
      buf.append(text);
      
      return true;
   }

}
