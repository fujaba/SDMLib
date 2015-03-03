package org.sdmlib.examples.mancala.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

public class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.mancala.model.util.MancalaCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.mancala.model.util.MancalaPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.mancala.model.util.PlayerCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.mancala.model.util.PlayerPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.mancala.model.util.PointCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.mancala.model.util.PointPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.mancala.model.util.PitCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.mancala.model.util.PitPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.mancala.model.util.KalahCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.mancala.model.util.KalahPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.mancala.referencemodel.util.ColorCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.mancala.referencemodel.util.ColorPOCreator());

      jsonIdMap.withCreator(new org.sdmlib.examples.mancala.model.util.StoneCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.mancala.model.util.StonePOCreator());
      return jsonIdMap;
   }
}
