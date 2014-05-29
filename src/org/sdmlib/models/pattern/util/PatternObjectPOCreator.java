 package org.sdmlib.models.pattern.util;

import de.uniks.networkparser.json.JsonIdMap;


public class PatternObjectPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new PatternObjectPO();
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

