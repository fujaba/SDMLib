package org.sdmlib.examples.ludo.creators;

import java.util.LinkedHashSet;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static LinkedHashSet<SendableEntityCreator> creatorSet = null;

   public static LinkedHashSet<SendableEntityCreator> getCreatorSet()
   {
      if (creatorSet == null)
      {
         creatorSet = new LinkedHashSet<SendableEntityCreator>();
         creatorSet.add(new org.sdmlib.examples.ludo.creators.LudoCreator());
         creatorSet.add(new org.sdmlib.examples.ludo.creators.LudoPOCreator());
         creatorSet.add(new org.sdmlib.examples.ludo.creators.PointCreator());
         creatorSet.add(new org.sdmlib.examples.ludo.creators.PointPOCreator());
         creatorSet.add(new org.sdmlib.examples.ludo.creators.PlayerCreator());
         creatorSet
            .add(new org.sdmlib.examples.ludo.creators.PlayerPOCreator());
         creatorSet.add(new org.sdmlib.examples.ludo.creators.DiceCreator());
         creatorSet.add(new org.sdmlib.examples.ludo.creators.DicePOCreator());
         creatorSet.add(new org.sdmlib.examples.ludo.creators.FieldCreator());
         creatorSet.add(new org.sdmlib.examples.ludo.creators.FieldPOCreator());
         creatorSet.add(new org.sdmlib.examples.ludo.creators.PawnCreator());
         creatorSet.add(new org.sdmlib.examples.ludo.creators.PawnPOCreator());
         creatorSet.add(new org.sdmlib.examples.ludo.creators.DateCreator());
         creatorSet.add(new org.sdmlib.examples.ludo.creators.DatePOCreator());
         creatorSet
            .add(new org.sdmlib.examples.ludo.creators.LudoColorCreator());
         creatorSet
            .add(new org.sdmlib.examples.ludo.creators.LudoColorPOCreator());
         creatorSet.addAll(org.sdmlib.models.pattern.creators.CreatorCreator
            .getCreatorSet());
      }

      return creatorSet;
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap()
         .withSessionId(sessionID);

      jsonIdMap.withCreator(getCreatorSet());

      return jsonIdMap;
   }
}
