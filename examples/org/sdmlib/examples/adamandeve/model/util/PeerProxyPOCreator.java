package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.logger.PeerProxy;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class PeerProxyPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new PeerProxyPO(new PeerProxy[]{});
      } else {
          return new PeerProxyPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
