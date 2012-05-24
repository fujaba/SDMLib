package org.sdmlib.examples.groupAccount.creators;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
     
      jsonIdMap.addCreator(new org.sdmlib.examples.groupAccount.creators.GroupAccountCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.groupAccount.creators.PersonCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.groupAccount.creators.ItemCreator());
      return jsonIdMap;
   }
}


















































