package org.sdmlib.test.examples.mancala.referencemodel.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.mancala.referencemodel.Color;

import de.uniks.networkparser.IdMap;

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
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.mancala.referencemodel.util.CreatorCreator.createIdMap(sessionID);
   }
}
