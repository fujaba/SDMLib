package org.sdmlib.examples.reachabilitygraphs.simplestates.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.reachabilitygraphs.simplestates.SimpleState;

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
      return org.sdmlib.examples.reachabilitygraphs.simplestates.util.CreatorCreator.createIdMap(sessionID);
   }
}
