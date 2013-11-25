package org.sdmlib.examples.ludoreverse.model.creators;

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
         creatorSet.add(new org.sdmlib.examples.ludoreverse.model.creators.LudoCreator());
         creatorSet.add(new org.sdmlib.examples.ludoreverse.model.creators.LudoPOCreator());
         creatorSet.add(new org.sdmlib.examples.ludoreverse.model.creators.PlayerCreator());
         creatorSet.add(new org.sdmlib.examples.ludoreverse.model.creators.PlayerPOCreator());
         creatorSet.add(new org.sdmlib.examples.ludoreverse.model.creators.PointCreator());
         creatorSet.add(new org.sdmlib.examples.ludoreverse.model.creators.PointPOCreator());
         creatorSet.addAll(org.sdmlib.models.pattern.creators.CreatorCreator.getCreatorSet());
      }
      
      return creatorSet;
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(getCreatorSet());

      return jsonIdMap;
   }
}



