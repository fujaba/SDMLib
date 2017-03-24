package org.sdmlib.test.historymanagement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

import org.sdmlib.test.historymanagement.marketmodel.Actor;
import org.sdmlib.test.historymanagement.marketmodel.Market;
import org.sdmlib.test.historymanagement.marketmodel.Offer;
import org.sdmlib.test.historymanagement.marketmodel.util.MarketCreator;

import de.uniks.networkparser.HistoryIdMap;
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.ObjectCondition;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonObject;
   /**
    * 
    * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/historymanagement/HistoryScenarios.java'>HistoryScenarios.java</a>
 */
   public class MarketManager extends Thread
   {
      /**
       * 
       * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/historymanagement/HistoryScenarios.java'>HistoryScenarios.java</a>
       */
      @Override
      public void run()
   {
      IdMap oldIdMap = MarketCreator.createIdMap("ebay");
      HistoryIdMap idMap = new HistoryIdMap("ebay");
      idMap.withCreator(oldIdMap.getCreators().values());
      idMap.withListener((ObjectCondition) update -> handleUpdate(update));
      // idMap.withListener((PropertyChangeListener) e -> handlePopertyChange(e));

      Market market = new Market();
      String id = idMap.getId(market);
      market.withMarketTime("00:00");
      
      int hour = 8;
      while (hour < 12)
      {
         String newTime = String.format("%02d:00", hour);
         market.withMarketTime(newTime);

         Offer coffeeOffer = new Offer()
               .withDescription("Coffee 1 pound")
               .withTimeLimit("12:30");
         
         Actor sturbucks = new Actor()
               .withActorName("Starbucks");

         coffeeOffer.withOwner(sturbucks);

         market.withOffers(coffeeOffer);
         
         Actor starCoffee = new Actor();
         starCoffee.withActorName("StarCoffee");
         coffeeOffer.withOwner(starCoffee);

         
         int minutes = 0;
         while (minutes <= 10)
         {
            try
            {
               Thread.sleep(100);
               minutes++;
               newTime = String.format("%02d:%02d", hour, minutes);
               market.setMarketTime(newTime);
            }
            catch (InterruptedException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }

         if (coffeeOffer.getBids().isEmpty())
         {
            coffeeOffer.removeYou();
         }
         
         hour++;
      }
   }
   
   
   private boolean handleUpdate(Object update)
   {
      // write change to log file
      try
      {
         JsonArray array = (JsonArray) update;
         
         StringBuffer buf = new StringBuffer();
         for (int i = 0; i < array.size(); i++)
         {
            JsonObject change = array.getJSONObject(i);
            String line = change.toString() + "\n";
            buf.append(line);
         }
         
         String text = buf.toString();
         Files.write(Paths.get("doc/historymarketplace/ebay.json"), text.getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
      }
      catch (IOException e)
      {
         Logger.getGlobal().info(""+e);
      }
      return true;
   }

}
