package org.sdmlib.test.examples.mancala.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;
import org.sdmlib.test.examples.mancala.referencemodel.util.ColorCreator;
import org.sdmlib.test.examples.mancala.referencemodel.util.ColorPOCreator;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.with(new MancalaCreator());
      jsonIdMap.with(new MancalaPOCreator());
      jsonIdMap.with(new PlayerCreator());
      jsonIdMap.with(new PlayerPOCreator());
      jsonIdMap.with(new PointCreator());
      jsonIdMap.with(new PointPOCreator());
      jsonIdMap.with(new PitCreator());
      jsonIdMap.with(new PitPOCreator());
      jsonIdMap.with(new KalahCreator());
      jsonIdMap.with(new KalahPOCreator());
      jsonIdMap.with(new ColorCreator());
      jsonIdMap.with(new ColorPOCreator());
      jsonIdMap.with(new StoneCreator());
      jsonIdMap.with(new StonePOCreator());
      return jsonIdMap;
   }
}
