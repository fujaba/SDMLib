package org.sdmlib.examples.groupAccount.model.util;

import org.sdmlib.examples.groupAccount.model.GroupAccount;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class GroupAccountPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new GroupAccountPO(new GroupAccount[]{});
      } else {
          return new GroupAccountPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.groupAccount.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
