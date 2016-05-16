package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.MatchOtherThan;

import de.uniks.networkparser.IdMap;


public class MatchOtherThanPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new MatchOtherThanPO(new MatchOtherThan[]{});
      } else {
         return new MatchOtherThanPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}
