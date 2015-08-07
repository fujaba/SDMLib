package org.sdmlib.test.examples.ludo.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludo.model.util.LudoCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludo.model.util.LudoPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludo.model.util.PointCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludo.model.util.PointPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludo.model.util.PlayerCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludo.model.util.PlayerPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludo.model.util.DiceCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludo.model.util.DicePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludo.model.util.FieldCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludo.model.util.FieldPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludo.model.util.PawnCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludo.model.util.PawnPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludo.model.util.LudoColorCreator());
      jsonIdMap.withCreator(new org.sdmlib.test.examples.ludo.model.util.LudoColorPOCreator());

      jsonIdMap.withCreator(new LudoCreator());
      jsonIdMap.withCreator(new LudoPOCreator());
      jsonIdMap.withCreator(new PointCreator());
      jsonIdMap.withCreator(new PointPOCreator());
      jsonIdMap.withCreator(new PlayerCreator());
      jsonIdMap.withCreator(new PlayerPOCreator());
      jsonIdMap.withCreator(new DiceCreator());
      jsonIdMap.withCreator(new DicePOCreator());
      jsonIdMap.withCreator(new FieldCreator());
      jsonIdMap.withCreator(new FieldPOCreator());
      jsonIdMap.withCreator(new PawnCreator());
      jsonIdMap.withCreator(new PawnPOCreator());
      jsonIdMap.withCreator(new LudoColorCreator());
      jsonIdMap.withCreator(new LudoColorPOCreator());
      jsonIdMap.withCreator(new LudoCreator());
      jsonIdMap.withCreator(new LudoPOCreator());
      jsonIdMap.withCreator(new PointCreator());
      jsonIdMap.withCreator(new PointPOCreator());
      jsonIdMap.withCreator(new PlayerCreator());
      jsonIdMap.withCreator(new PlayerPOCreator());
      jsonIdMap.withCreator(new DiceCreator());
      jsonIdMap.withCreator(new DicePOCreator());
      jsonIdMap.withCreator(new FieldCreator());
      jsonIdMap.withCreator(new FieldPOCreator());
      jsonIdMap.withCreator(new PawnCreator());
      jsonIdMap.withCreator(new PawnPOCreator());
      jsonIdMap.withCreator(new LudoColorCreator());
      jsonIdMap.withCreator(new LudoColorPOCreator());
      jsonIdMap.withCreator(new LudoCreator());
      jsonIdMap.withCreator(new LudoPOCreator());
      jsonIdMap.withCreator(new PointCreator());
      jsonIdMap.withCreator(new PointPOCreator());
      jsonIdMap.withCreator(new PlayerCreator());
      jsonIdMap.withCreator(new PlayerPOCreator());
      jsonIdMap.withCreator(new DiceCreator());
      jsonIdMap.withCreator(new DicePOCreator());
      jsonIdMap.withCreator(new FieldCreator());
      jsonIdMap.withCreator(new FieldPOCreator());
      jsonIdMap.withCreator(new PawnCreator());
      jsonIdMap.withCreator(new PawnPOCreator());
      jsonIdMap.withCreator(new LudoColorCreator());
      jsonIdMap.withCreator(new LudoColorPOCreator());
      return jsonIdMap;
   }
}
