package org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UCargo;

public class UCargoPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new UCargoPO(new UCargo[]{});
      } else {
          return new UCargoPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.CreatorCreator.createIdMap(sessionID);
   }
}
