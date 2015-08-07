package org.sdmlib.test.examples.gofpattern.strategy.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.withCreator(new BombermanPlayerCreator());
      jsonIdMap.withCreator(new BombermanPlayerPOCreator());
      jsonIdMap.withCreator(new BombermanStrategyCreator());
      jsonIdMap.withCreator(new BombermanStrategyPOCreator());
      jsonIdMap.withCreator(new MoveUpCreator());
      jsonIdMap.withCreator(new MoveUpPOCreator());
      jsonIdMap.withCreator(new MoveDownCreator());
      jsonIdMap.withCreator(new MoveDownPOCreator());
      jsonIdMap.withCreator(new MoveLeftCreator());
      jsonIdMap.withCreator(new MoveLeftPOCreator());
      jsonIdMap.withCreator(new MoveRightCreator());
      jsonIdMap.withCreator(new MoveRightPOCreator());
      jsonIdMap.withCreator(new BlastCreator());
      jsonIdMap.withCreator(new BlastPOCreator());
      jsonIdMap.withCreator(new StayCreator());
      jsonIdMap.withCreator(new StayPOCreator());
      return jsonIdMap;
   }
}
