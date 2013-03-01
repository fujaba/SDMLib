package org.sdmlib.examples.patternrewriteops.creators;

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
         creatorSet.add(new org.sdmlib.examples.patternrewriteops.creators.TrainCreator());
         creatorSet.add(new org.sdmlib.examples.patternrewriteops.creators.TrainPOCreator());
         creatorSet.add(new org.sdmlib.examples.patternrewriteops.creators.StationCreator());
         creatorSet.add(new org.sdmlib.examples.patternrewriteops.creators.StationPOCreator());
         creatorSet.add(new org.sdmlib.examples.patternrewriteops.creators.PersonCreator());
         creatorSet.add(new org.sdmlib.examples.patternrewriteops.creators.PersonPOCreator());
         creatorSet.add(new org.sdmlib.examples.patternrewriteops.creators.SignalFlagCreator());
         creatorSet.add(new org.sdmlib.examples.patternrewriteops.creators.SignalFlagPOCreator());
         creatorSet.addAll(org.sdmlib.models.pattern.creators.CreatorCreator.getCreatorSet());
      }
      
      return creatorSet;
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.addCreator(getCreatorSet());

      return jsonIdMap;
   }
}


