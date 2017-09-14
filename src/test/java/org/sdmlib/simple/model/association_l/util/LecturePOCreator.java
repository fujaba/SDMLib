package org.sdmlib.simple.model.association_l.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.simple.model.association_l.Lecture;

import de.uniks.networkparser.IdMap;

public class LecturePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new LecturePO(new Lecture[]{});
      } else {
          return new LecturePO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.association_l.util.CreatorCreator.createIdMap(sessionID);
   }
}
