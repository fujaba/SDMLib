package org.sdmlib.simple.model.modelling_a.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.simple.model.modelling_a.roomInterface;

public class roomInterfacePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new roomInterfacePO(new roomInterface[]{});
      } else {
          return new roomInterfacePO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.modelling_a.util.CreatorCreator.createIdMap(sessionID);
   }
}
