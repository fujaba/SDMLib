package org.sdmlib.simple.model.association_g.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.simple.model.association_g.Teacher;

import de.uniks.networkparser.IdMap;

public class TeacherPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new TeacherPO(new Teacher[]{});
      } else {
          return new TeacherPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.association_g.util.CreatorCreator.createIdMap(sessionID);
   }
}
