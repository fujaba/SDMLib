package org.sdmlib.test.examples.reachabilitygraphs.simplestates.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.Node;

import de.uniks.networkparser.json.JsonIdMap;

public class NodePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new NodePO(new Node[]{});
      } else {
          return new NodePO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.CreatorCreator.createIdMap(sessionID);
   }
}
