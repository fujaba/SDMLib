package org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.util;

import org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.Bank;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class BankPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new BankPO(new Bank[]{});
      } else {
          return new BankPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.util.CreatorCreator.createIdMap(sessionID);
   }
}
