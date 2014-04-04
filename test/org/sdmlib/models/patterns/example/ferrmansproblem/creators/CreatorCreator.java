package org.sdmlib.models.patterns.example.ferrmansproblem.creators;

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
         creatorSet
            .add(new org.sdmlib.models.patterns.example.ferrmansproblem.creators.RiverCreator());
         creatorSet
            .add(new org.sdmlib.models.patterns.example.ferrmansproblem.creators.RiverPOCreator());
         creatorSet
            .add(new org.sdmlib.models.patterns.example.ferrmansproblem.creators.BoatCreator());
         creatorSet
            .add(new org.sdmlib.models.patterns.example.ferrmansproblem.creators.BoatPOCreator());
         creatorSet
            .add(new org.sdmlib.models.patterns.example.ferrmansproblem.creators.BankCreator());
         creatorSet
            .add(new org.sdmlib.models.patterns.example.ferrmansproblem.creators.BankPOCreator());
         creatorSet
            .add(new org.sdmlib.models.patterns.example.ferrmansproblem.creators.CargoCreator());
         creatorSet
            .add(new org.sdmlib.models.patterns.example.ferrmansproblem.creators.CargoPOCreator());
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
