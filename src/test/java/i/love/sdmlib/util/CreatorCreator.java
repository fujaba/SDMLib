package i.love.sdmlib.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

public class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      

      jsonIdMap.withCreator(new org.sdmlib.examples.helloworld.util.GreetingCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.helloworld.util.GreetingPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.helloworld.util.GreetingMessageCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.helloworld.util.GreetingMessagePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.helloworld.util.PersonCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.helloworld.util.PersonPOCreator());
      return jsonIdMap;
   }
}
