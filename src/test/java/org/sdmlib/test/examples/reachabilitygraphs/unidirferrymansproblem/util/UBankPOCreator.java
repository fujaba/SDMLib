package org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UBank;

public class UBankPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new UBankPO(new UBank[]{});
      } else {
          return new UBankPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.CreatorCreator.createIdMap(sessionID);
   }
}
