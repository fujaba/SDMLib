package org.sdmlib.test.examples.ludo.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.ludo.LudoModel.LudoColor;

import de.uniks.networkparser.json.JsonIdMap;

public class LudoColorPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new LudoColorPO(new LudoColor[]{});
      } else {
          return new LudoColorPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
