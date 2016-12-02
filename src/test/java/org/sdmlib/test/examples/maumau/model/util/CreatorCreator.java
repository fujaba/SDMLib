package org.sdmlib.test.examples.maumau.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new MauMauCreator());
      jsonIdMap.with(new MauMauPOCreator());
      jsonIdMap.with(new CardCreator());
      jsonIdMap.with(new CardPOCreator());
      jsonIdMap.with(new HolderCreator());
      jsonIdMap.with(new HolderPOCreator());
      jsonIdMap.with(new PlayerCreator());
      jsonIdMap.with(new PlayerPOCreator());
      jsonIdMap.with(new DutyCreator());
      jsonIdMap.with(new DutyPOCreator());
      jsonIdMap.with(new OpenStackCreator());
      jsonIdMap.with(new OpenStackPOCreator());
      jsonIdMap.with(new DrawingStackCreator());
      jsonIdMap.with(new DrawingStackPOCreator());
      jsonIdMap.with(new LaneCreator());
      jsonIdMap.with(new LanePOCreator());
      return jsonIdMap;
   }
}
