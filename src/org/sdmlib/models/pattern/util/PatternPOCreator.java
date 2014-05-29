package org.sdmlib.models.pattern.util;

import de.uniks.networkparser.json.JsonIdMap;

public class PatternPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new PatternPO();
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

