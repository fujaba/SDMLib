package org.sdmlib.test.examples.gofpattern.strategy.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new BombermanPlayerCreator());
      jsonIdMap.with(new BombermanPlayerPOCreator());
      jsonIdMap.with(new BombermanStrategyCreator());
      jsonIdMap.with(new BombermanStrategyPOCreator());
      jsonIdMap.with(new MoveUpCreator());
      jsonIdMap.with(new MoveUpPOCreator());
      jsonIdMap.with(new MoveDownCreator());
      jsonIdMap.with(new MoveDownPOCreator());
      jsonIdMap.with(new MoveLeftCreator());
      jsonIdMap.with(new MoveLeftPOCreator());
      jsonIdMap.with(new MoveRightCreator());
      jsonIdMap.with(new MoveRightPOCreator());
      jsonIdMap.with(new BlastCreator());
      jsonIdMap.with(new BlastPOCreator());
      jsonIdMap.with(new StayCreator());
      jsonIdMap.with(new StayPOCreator());
      return jsonIdMap;
   }
}
