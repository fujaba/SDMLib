package org.sdmlib.test.examples.groupaccount.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.groupaccount.model.Party;

public class PartyPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new PartyPO(new Party[]{});
      } else {
          return new PartyPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.groupaccount.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
