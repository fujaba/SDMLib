package org.sdmlib.simple.model.association_i.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.simple.model.association_i.Teacher;

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
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.association_i.util.CreatorCreator.createIdMap(sessionID);
   }
}
