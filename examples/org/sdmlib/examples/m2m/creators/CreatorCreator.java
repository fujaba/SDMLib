package org.sdmlib.examples.m2m.creators;

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
         creatorSet.add(new org.sdmlib.examples.m2m.creators.GraphCreator());
         creatorSet.add(new org.sdmlib.examples.m2m.creators.GraphPOCreator());
         creatorSet.add(new org.sdmlib.examples.m2m.creators.PersonCreator());
         creatorSet.add(new org.sdmlib.examples.m2m.creators.PersonPOCreator());
         creatorSet.add(new org.sdmlib.examples.m2m.creators.RelationCreator());
         creatorSet.add(new org.sdmlib.examples.m2m.creators.RelationPOCreator());
         creatorSet.add(new org.sdmlib.examples.m2m.creators.GraphComponentCreator());
         creatorSet.add(new org.sdmlib.examples.m2m.creators.GraphComponentPOCreator());
         creatorSet.add(new org.sdmlib.examples.m2m.creators.ModelPatternCreator());
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


