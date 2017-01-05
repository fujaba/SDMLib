package org.sdmlib.simple.model.abstract_B.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.simple.model.abstract_B.Student;

import de.uniks.networkparser.IdMap;

public class StudentPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new StudentPO(new Student[]{});
      } else {
          return new StudentPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.abstract_B.util.CreatorCreator.createIdMap(sessionID);
   }
}
