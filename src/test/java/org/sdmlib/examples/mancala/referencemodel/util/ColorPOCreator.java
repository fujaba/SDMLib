package org.sdmlib.examples.mancala.referencemodel.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.mancala.referencemodel.Color;

public class ColorPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ColorPO(new Color[]{});
      } else {
          return new ColorPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.mancala.referencemodel.util.CreatorCreator.createIdMap(sessionID);
   }
}
