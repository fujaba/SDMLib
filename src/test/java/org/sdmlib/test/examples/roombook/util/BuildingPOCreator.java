package org.sdmlib.test.examples.roombook.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.roombook.Building;

import de.uniks.networkparser.IdMap;

public class BuildingPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new BuildingPO(new Building[]{});
      } else {
          return new BuildingPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.roombook.util.CreatorCreator.createIdMap(sessionID);
   }
}
