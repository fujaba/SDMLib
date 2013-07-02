package org.sdmlib.examples.replication.maumau.creators;

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
         creatorSet.add(new org.sdmlib.examples.replication.maumau.creators.MauMauCreator());
         creatorSet.add(new org.sdmlib.examples.replication.maumau.creators.MauMauPOCreator());
         creatorSet.add(new org.sdmlib.examples.replication.maumau.creators.CardCreator());
         creatorSet.add(new org.sdmlib.examples.replication.maumau.creators.CardPOCreator());
         creatorSet.add(new org.sdmlib.examples.replication.maumau.creators.HolderCreator());
         creatorSet.add(new org.sdmlib.examples.replication.maumau.creators.HolderPOCreator());
         creatorSet.add(new org.sdmlib.examples.replication.maumau.creators.PlayerCreator());
         creatorSet.add(new org.sdmlib.examples.replication.maumau.creators.PlayerPOCreator());
         creatorSet.addAll(org.sdmlib.models.pattern.creators.CreatorCreator.getCreatorSet());
      }
      
      return creatorSet;
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(getCreatorSet());

      return jsonIdMap;
   }
}

