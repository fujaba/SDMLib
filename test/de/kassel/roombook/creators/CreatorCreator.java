package de.kassel.roombook.creators;

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
         creatorSet.add(new de.kassel.roombook.creators.BuildingCreator());
         creatorSet.add(new de.kassel.roombook.creators.BuildingPOCreator());
         creatorSet.add(new de.kassel.roombook.creators.FloorCreator());
         creatorSet.add(new de.kassel.roombook.creators.FloorPOCreator());
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
