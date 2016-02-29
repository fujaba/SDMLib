package org.sdmlib.test.examples.ludo.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new LudoCreator());
      jsonIdMap.with(new LudoPOCreator());
      jsonIdMap.with(new PointCreator());
      jsonIdMap.with(new PointPOCreator());
      jsonIdMap.with(new PlayerCreator());
      jsonIdMap.with(new PlayerPOCreator());
      jsonIdMap.with(new DiceCreator());
      jsonIdMap.with(new DicePOCreator());
      jsonIdMap.with(new FieldCreator());
      jsonIdMap.with(new FieldPOCreator());
      jsonIdMap.with(new PawnCreator());
      jsonIdMap.with(new PawnPOCreator());
      jsonIdMap.with(new LudoColorCreator());
      jsonIdMap.with(new LudoColorPOCreator());
      return jsonIdMap;
   }
}
