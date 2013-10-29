package org.sdmlib.models.objects.creators;

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
         creatorSet.add(new org.sdmlib.models.objects.creators.GenericGraphCreator());
         creatorSet.add(new org.sdmlib.models.objects.creators.GenericGraphPOCreator());
         creatorSet.add(new org.sdmlib.models.objects.creators.GenericObjectCreator());
         creatorSet.add(new org.sdmlib.models.objects.creators.GenericObjectPOCreator());
         creatorSet.add(new org.sdmlib.models.objects.creators.GenericAttributeCreator());
         creatorSet.add(new org.sdmlib.models.objects.creators.GenericAttributePOCreator());
         creatorSet.add(new org.sdmlib.models.objects.creators.GenericLinkCreator());
         creatorSet.add(new org.sdmlib.models.objects.creators.GenericLinkPOCreator());
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

