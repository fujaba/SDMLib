package org.sdmlib.examples.ludo.model.creators;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.LudoCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.LudoPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.PlayerCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.PlayerPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.DiceCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.DicePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.FieldCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.FieldPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.PawnCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.ludo.model.creators.PawnPOCreator());

      return jsonIdMap;
   }
}

