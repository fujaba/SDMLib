package org.sdmlib.test.examples.couchspace.tasks.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.couchspace.tasks.User;

import de.uniks.networkparser.IdMap;

public class UserPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new UserPO(new User[]{});
      } else {
          return new UserPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.couchspace.tasks.util.CreatorCreator.createIdMap(sessionID);
   }
}
