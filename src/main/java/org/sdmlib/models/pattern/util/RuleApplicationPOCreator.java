package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.RuleApplication;

import de.uniks.networkparser.json.JsonIdMap;

public class RuleApplicationPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new RuleApplicationPO(new RuleApplication[]{});
      } else {
         return new RuleApplicationPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}
