package org.sdmlib.examples.adamandeve.creators;

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
         creatorSet.add(new org.sdmlib.examples.adamandeve.creators.EveCreator());
         creatorSet.add(new org.sdmlib.examples.adamandeve.creators.EvePOCreator());
         creatorSet.add(new org.sdmlib.examples.adamandeve.creators.AdamCreator());
         creatorSet.add(new org.sdmlib.examples.adamandeve.creators.AdamPOCreator());
         // creatorSet.add(new org.sdmlib.model.taskflows.creators.TaskFlowCreator());
         // creatorSet.add(new org.sdmlib.model.taskflows.creators.TaskFlowPOCreator());
         creatorSet.add(new org.sdmlib.serialization.json.creators.JsonIdMapCreator());
         creatorSet.add(new org.sdmlib.serialization.json.creators.JsonIdMapPOCreator());
         creatorSet.add(new org.sdmlib.model.taskflows.creators.PeerProxyCreator());
         creatorSet.add(new org.sdmlib.model.taskflows.creators.PeerProxyPOCreator());
         creatorSet.add(new org.sdmlib.examples.adamandeve.creators.UpdateAdamFlowCreator());
         creatorSet.add(new org.sdmlib.examples.adamandeve.creators.UpdateAdamFlowPOCreator());
         creatorSet.add(new org.sdmlib.model.taskflows.creators.TaskFlowCreator());
         creatorSet.add(new org.sdmlib.model.taskflows.creators.TaskFlowPOCreator());
         creatorSet.add(new org.sdmlib.serialization.json.creators.SDMLibJsonIdMapCreator());
         creatorSet.add(new org.sdmlib.serialization.json.creators.SDMLibJsonIdMapPOCreator());
         creatorSet.addAll(new org.sdmlib.model.taskflows.creators.CreatorCreator().getCreatorSet());
         // creatorSet.addAll(org.sdmlib.models.pattern.creators.CreatorCreator.getCreatorSet());
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



