package org.sdmlib.examples.gofpattern.strategy.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

public class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.gofpattern.strategy.util.BombermanPlayerCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.gofpattern.strategy.util.BombermanPlayerPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.gofpattern.strategy.util.BombermanStrategyCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.gofpattern.strategy.util.BombermanStrategyPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.gofpattern.strategy.util.MoveUpCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.gofpattern.strategy.util.MoveUpPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.gofpattern.strategy.util.MoveDownCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.gofpattern.strategy.util.MoveDownPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.gofpattern.strategy.util.MoveLeftCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.gofpattern.strategy.util.MoveLeftPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.gofpattern.strategy.util.MoveRightCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.gofpattern.strategy.util.MoveRightPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.gofpattern.strategy.util.BlastCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.gofpattern.strategy.util.BlastPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.gofpattern.strategy.util.StayCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.gofpattern.strategy.util.StayPOCreator());

      return jsonIdMap;
   }
}
