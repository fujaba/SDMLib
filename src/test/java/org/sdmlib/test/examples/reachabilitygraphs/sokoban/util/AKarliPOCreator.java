package org.sdmlib.test.examples.reachabilitygraphs.sokoban.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.AKarli;

public class AKarliPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new AKarliPO(new AKarli[]{});
      } else {
          return new AKarliPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.CreatorCreator.createIdMap(sessionID);
   }
}
