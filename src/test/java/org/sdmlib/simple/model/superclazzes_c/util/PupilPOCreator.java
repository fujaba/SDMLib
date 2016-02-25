package org.sdmlib.simple.model.superclazzes_c.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.simple.model.superclazzes_c.Pupil;

public class PupilPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new PupilPO(new Pupil[]{});
      } else {
          return new PupilPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.superclazzes_c.util.CreatorCreator.createIdMap(sessionID);
   }
}
