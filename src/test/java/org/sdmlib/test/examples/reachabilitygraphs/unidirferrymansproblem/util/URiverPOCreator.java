package org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.URiver;

public class URiverPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new URiverPO(new URiver[]{});
      } else {
          return new URiverPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.CreatorCreator.createIdMap(sessionID);
   }
}
