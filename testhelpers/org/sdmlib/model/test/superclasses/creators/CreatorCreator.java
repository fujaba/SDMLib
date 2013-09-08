package org.sdmlib.model.test.superclasses.creators;

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
         creatorSet.add(new org.sdmlib.model.test.superclasses.creators.ContinentCreator());
         creatorSet.add(new org.sdmlib.model.test.superclasses.creators.ContinentPOCreator());
         creatorSet.add(new org.sdmlib.model.test.superclasses.creators.StateCreator());
         creatorSet.add(new org.sdmlib.model.test.superclasses.creators.StatePOCreator());
         creatorSet.add(new org.sdmlib.model.test.superclasses.creators.TownCreator());
         creatorSet.add(new org.sdmlib.model.test.superclasses.creators.TownPOCreator());
         creatorSet.add(new org.sdmlib.model.classes.creators.ReverseClassModelTestCreator());
         creatorSet.add(new org.sdmlib.model.classes.creators.ReverseClassModelTestPOCreator());
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

