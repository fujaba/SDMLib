package org.sdmlib.test.historymanagement.marketmodel.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.historymanagement.marketmodel.Bid;

import de.uniks.networkparser.IdMap;

public class BidPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new BidPO(new Bid[]{});
      } else {
          return new BidPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.historymanagement.marketmodel.util.CreatorCreator.createIdMap(sessionID);
   }
}
