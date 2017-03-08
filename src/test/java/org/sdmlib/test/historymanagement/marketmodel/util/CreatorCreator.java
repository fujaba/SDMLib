package org.sdmlib.test.historymanagement.marketmodel.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new ActorCreator());
      jsonIdMap.with(new ActorPOCreator());
      jsonIdMap.with(new BidCreator());
      jsonIdMap.with(new BidPOCreator());
      jsonIdMap.with(new MarketCreator());
      jsonIdMap.with(new MarketPOCreator());
      jsonIdMap.with(new OfferCreator());
      jsonIdMap.with(new OfferPOCreator());
      return jsonIdMap;
   }
}
