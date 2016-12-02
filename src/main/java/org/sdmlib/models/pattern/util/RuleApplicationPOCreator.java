package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.RuleApplication;

import de.uniks.networkparser.IdMap;

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
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.models.pattern.util.CreatorCreator.createIdMap(sessionID);
   }
}
