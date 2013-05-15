package org.sdmlib.examples.groupAccount.creators;

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
         creatorSet.add(new org.sdmlib.examples.groupAccount.creators.GroupAccountCreator());
         creatorSet.add(new org.sdmlib.examples.groupAccount.creators.GroupAccountPOCreator());
         creatorSet.add(new org.sdmlib.examples.groupAccount.creators.PersonCreator());
         creatorSet.add(new org.sdmlib.examples.groupAccount.creators.PersonPOCreator());
         creatorSet.add(new org.sdmlib.examples.groupAccount.creators.ItemCreator());
         creatorSet.add(new org.sdmlib.examples.groupAccount.creators.ItemPOCreator());
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

