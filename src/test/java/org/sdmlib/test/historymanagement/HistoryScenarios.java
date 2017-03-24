package org.sdmlib.test.historymanagement;


import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.historymanagement.marketmodel.Actor;
import org.sdmlib.test.historymanagement.marketmodel.Market;
import org.sdmlib.test.historymanagement.marketmodel.Offer;
import org.sdmlib.test.historymanagement.marketmodel.util.MarketCreator;

import de.uniks.networkparser.HistoryIdMap;
import de.uniks.networkparser.HistoryIdMap.AttrTimeStampMap;
import de.uniks.networkparser.HistoryIdMap.RefTime;
import de.uniks.networkparser.HistoryIdMap.RefTimeStampsMap;
import de.uniks.networkparser.HistoryIdMap.TimeStampMap;
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.SimpleEvent;
import de.uniks.networkparser.interfaces.ObjectCondition;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonObject;
import de.uniks.networkparser.json.JsonTokener;

public class HistoryScenarios
{
   private StringBuffer buf;

   private boolean handleUpdate(Object value)
   {
      JsonArray json = (JsonArray) value;
      
      if (json == null)
      {
         return true;
      }
      
      String text = json.toString(3) + "\n";
      
      buf.append(text);
      
      return true;
   }
   
     /**
    * 
    * @see <a href='../../../../../../../doc/HistoryIdMapMarketScenario.html'>HistoryIdMapMarketScenario.html</a>
 */
   @Test
   public void HistoryIdMapMarketScenario() throws Exception
   {
      Storyboard story = new Storyboard();
      
      new MarketManager().start();

      story.dumpHTML();
   }
   
   @Test
   public void updateHistoryOnNewComplexNeighbors() throws Exception
   {
      Storyboard story = new Storyboard().withDocDirName("doc/rigorous");
      
      buf = new StringBuffer();
      IdMap oldIdMap = MarketCreator.createIdMap("ebay");
      HistoryIdMap idMap = new HistoryIdMap("ebay");
      idMap.withCreator(oldIdMap.getCreators().values());
      
      idMap.withListener((ObjectCondition) value -> handleUpdate(value));
      
      long nanoTime = System.nanoTime();
      
      Market market = new Market()
            .withMarketTime("00:00");
      
      String marketId = idMap.getId(market);
      
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
      RefTimeStampsMap refTimeStampsMap = idMap.toManyChanges.get(marketId);
      TimeStampMap timeStampMap = refTimeStampsMap.get("offers");
      String coffeeOfferId = idMap.getId(coffeeOffer);
      RefTime insertionTime = timeStampMap.get(coffeeOfferId);
      story.assertTrue("there should be an insertion in our toManyChanges map", insertionTime.timeStamp > nanoTime);
      
      // add a second offer
      Offer cupOffer = new Offer()
            .withDescription("Big Mug")
            .withTimeLimit("12:32");

      market.withOffers(cupOffer);
      story.assertTrue("change message should contain Big Mug", buf.indexOf("Big Mug") >= 0);
      
      // now remove the first order from the market
      market.withoutOffers(coffeeOffer);
      
      int size = timeStampMap.getRemovedTimeStamps().size();
      story.assertTrue("history should record removal", size > 0);
      
      // add it again, should be second in list now
      market.withOffers(coffeeOffer);
      
      RefTime refTime = timeStampMap.getSortedTimeStamps().get(1);
      story.assertTrue("coffeeOffer has position 2 in history list now", refTime.objId.equals(coffeeOfferId));
      boolean empty = timeStampMap.getRemovedTimeStamps().isEmpty();
      story.assertTrue("removed from toMany should be empty again", empty);
      
      coffeeOffer.removeYou();

      String baseId = HistoryIdMap.baseId(coffeeOfferId);
      RefTime removedRefTime = idMap.getRemovedObjects().get("ebay").get(baseId);
      story.assertTrue("history should store removal " + removedRefTime, removedRefTime != null);
      
      // get current change set
      JsonArray currentChanges = idMap.getCurrentChanges();
      Logger.getGlobal().info(currentChanges.toString(3));
      story.assertEquals("Only non-overwritten changes", 12, currentChanges.size());
      // try to decode current changes
      HistoryIdMap loadedIdMap = new HistoryIdMap("reichelt");
      loadedIdMap.withCreator(oldIdMap.getCreators().values());
      loadedIdMap.decodeChanges(currentChanges.toString(3));
      Market loadedMarket = (Market) loadedIdMap.getObject(marketId);
      story.assertNotNull("We should have a clone of our market", loadedMarket);String otherBuf = revertChanges(buf);
      
      
      HistoryIdMap otherIdMap = new HistoryIdMap("conrad");
      otherIdMap.withCreator(oldIdMap.getCreators().values());
      otherIdMap.decodeChanges(otherBuf);
      Market otherMarket = (Market) otherIdMap.getObject(marketId);
      story.assertNotNull("We should have another market", otherMarket);

      // rebirth
      Offer newOffer = new Offer()
         .withDescription("Coffee Maker 2000")
         .withTimeLimit("12:05");
      
      market.withOffers(newOffer);
      
      long lifeCounter = idMap.getRebirthCounters().get(baseId);
      story.assertEquals("new offer should have lifecounter", 2, lifeCounter);
      // newOffer should reuse id of removed object
      String newOfferId = idMap.getId(newOffer);
      String newBaseId = HistoryIdMap.baseId(newOfferId);
      story.assertEquals("old and new offer should have same prefix", baseId, newBaseId);
      empty = idMap.getRemovedObjects().get("ebay").isEmpty();
      story.assertEquals("Set of removed object ids should be empty", true, empty);
      timeStampMap = idMap.toManyChanges.get(marketId).get("offers");
      empty = timeStampMap.getRemovedTimeStamps().isEmpty();
      story.assertEquals("Set of removed references on market offers should be empty", true, empty);
      
      // have a clone market with reverse change order
      String revBuf = revertChanges(buf);
      HistoryIdMap cloneIdMap = new HistoryIdMap("amazon");
      cloneIdMap.withCreator(oldIdMap.getCreators().values());
      cloneIdMap.decodeChanges(revBuf);

      Market cloneMarket = (Market) cloneIdMap.getObject(marketId);
      story.assertNotNull("We should have a clone of our market", cloneMarket);
      
      long noOfAcceptedChanges = idMap.decodeChanges(buf.toString());
      story.assertEquals("number of changes accepted", 0, noOfAcceptedChanges);

      // get current change set
      currentChanges = idMap.getCurrentChanges();
      Logger.getGlobal().info(currentChanges.toString(3));
      story.assertEquals("Only non-overwritten changes", 15, currentChanges.size());


      
      
      story.dumpHTML();
   }
   
   private String revertChanges(StringBuffer inBuf)
   {
      long noOfChangesApplied = 0;
      // read all changes into single json array
      JsonArray result = new JsonArray();
      result.clear();
      JsonTokener tokener = new JsonTokener();
      tokener.withBuffer(inBuf.toString());
      while (!tokener.isEnd())
      {
         tokener.parseToEntity(result);
      }
      
      JsonArray revBuf = new JsonArray();
      for (int i = result.size()-1; i >= 0; i--)
      {
         revBuf.add(result.get(i));
      }
      
      return revBuf.toString(3);
   }
   
   

   
   @Test
   public void updateHistoryOnObjectRemoval() throws Exception
   {
      Storyboard story = new Storyboard().withDocDirName("doc/rigorous");
      
      buf = new StringBuffer();
      IdMap oldIdMap = MarketCreator.createIdMap("ebay");
      HistoryIdMap idMap = new HistoryIdMap("ebay");
      idMap.withCreator(oldIdMap.getCreators().values());
      
      idMap.withListener((ObjectCondition) value -> handleUpdate(value));
      
      Market market = new Market();
      market.withMarketTime("00:00");
      String id = idMap.getId(market);
      
      market.removeYou();
      
      story.assertTrue("change message should contain REMOVE_YOU", buf.indexOf("REMOVE_YOU") >= 0);

      story.dumpHTML();
   }


   


   @Test
   public void updateHistoryOnNewObjectAttributes() throws Exception
   {
      Storyboard story = new Storyboard().withDocDirName("doc/rigorous");
      
      buf = new StringBuffer();
      IdMap oldIdMap = MarketCreator.createIdMap("ebay");
      HistoryIdMap idMap = new HistoryIdMap("ebay");
      idMap.withCreator(oldIdMap.getCreators().values());
      
      idMap.withListener((ObjectCondition) value -> handleUpdate(value));
      
      long nanoTime = System.nanoTime();
      
      Market market = new Market();
      market.withMarketTime("00:00");

      String id = idMap.getId(market);
      
      story.assertTrue("change message should contain 00:00", buf.indexOf("00:00") >= 0);
      
      AttrTimeStampMap attrTimeStampMap = idMap.simpleAttrChanges.get(id);
      Long changeTime = attrTimeStampMap.get("marketTime");
      story.assertTrue("history should store time stamp for market time", changeTime != null && changeTime > nanoTime);

      story.dumpHTML();
   }

   
}
