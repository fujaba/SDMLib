package org.sdmlib.test.codegen.studyright.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.codegen.studyright.model.University;

import de.uniks.networkparser.IdMap;

public class UniversityPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new UniversityPO(new University[]{});
      } else {
          return new UniversityPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.codegen.studyright.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
