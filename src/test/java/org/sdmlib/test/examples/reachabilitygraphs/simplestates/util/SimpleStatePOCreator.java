package org.sdmlib.test.examples.reachabilitygraphs.simplestates.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.SimpleState;

import de.uniks.networkparser.json.JsonIdMap;

public class SimpleStatePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new SimpleStatePO(new SimpleState[]{});
      } else {
          return new SimpleStatePO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.CreatorCreator.createIdMap(sessionID);
   }
}
