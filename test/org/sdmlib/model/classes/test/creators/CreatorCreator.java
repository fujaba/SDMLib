package org.sdmlib.model.classes.test.creators;

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
         creatorSet.add(new org.sdmlib.model.classes.test.creators.NoPropertiesCreator());
         creatorSet.add(new org.sdmlib.model.classes.test.creators.NoPropertiesPOCreator());
         creatorSet.add(new org.sdmlib.model.classes.test.creators.ParentCreator());
         creatorSet.add(new org.sdmlib.model.classes.test.creators.ParentPOCreator());
         creatorSet.add(new org.sdmlib.model.classes.test.creators.UncleCreator());
         creatorSet.add(new org.sdmlib.model.classes.test.creators.UnclePOCreator());
         creatorSet.add(new org.sdmlib.model.classes.test.creators.KidCreator());
         creatorSet.add(new org.sdmlib.model.classes.test.creators.KidPOCreator());
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

