package org.sdmlib.examples.helloworld.creators;

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
         creatorSet.add(new org.sdmlib.examples.helloworld.creators.GraphCreator());
         creatorSet.add(new org.sdmlib.examples.helloworld.creators.GraphPOCreator());
         creatorSet.add(new org.sdmlib.examples.helloworld.creators.EdgeCreator());
         creatorSet.add(new org.sdmlib.examples.helloworld.creators.EdgePOCreator());
         creatorSet.add(new org.sdmlib.examples.helloworld.creators.NodeCreator());
         creatorSet.add(new org.sdmlib.examples.helloworld.creators.NodePOCreator());
         creatorSet.add(new org.sdmlib.examples.helloworld.creators.GreetingCreator());
         creatorSet.add(new org.sdmlib.examples.helloworld.creators.GreetingPOCreator());
         creatorSet.add(new org.sdmlib.examples.helloworld.creators.GreetingMessageCreator());
         creatorSet.add(new org.sdmlib.examples.helloworld.creators.GreetingMessagePOCreator());
         creatorSet.add(new org.sdmlib.examples.helloworld.creators.PersonCreator());
         creatorSet.add(new org.sdmlib.examples.helloworld.creators.PersonPOCreator());
         creatorSet.add(new org.sdmlib.examples.helloworld.creators.GraphComponentCreator());
         creatorSet.add(new org.sdmlib.examples.helloworld.creators.GraphComponentPOCreator());
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




