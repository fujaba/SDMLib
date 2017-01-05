package org.sdmlib.test.examples.studyrightWithAssignments.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;

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
      return org.sdmlib.test.examples.studyrightWithAssignments.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
