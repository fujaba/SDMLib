package org.sdmlib.test.historymanagement;


import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.historymanagement.marketmodel.Market;
import org.sdmlib.test.historymanagement.marketmodel.util.MarketCreator;

import de.uniks.networkparser.HistoryIdMap;
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.SimpleEvent;
import de.uniks.networkparser.interfaces.UpdateListener;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonObject;

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

   @Test
   public void updateHistoryOnNewObjectAttributes() throws Exception
   {
      Storyboard story = new Storyboard().withDocDirName("doc/rigorous");
      
      buf = new StringBuffer();
      IdMap oldIdMap = MarketCreator.createIdMap("ebay");
      HistoryIdMap idMap = new HistoryIdMap("ebay");
      idMap.withCreator(oldIdMap.getCreators().values());
      
      idMap.withListener((UpdateListener) value -> handleUpdate(value));
      
      Market market = new Market();
      market.withMarketTime("00:00");
      String id = idMap.getId(market);
      
      story.assertTrue("change message should contain 00:00", buf.indexOf("00:00") >= 0);

      story.dumpHTML();
   }

   
}
