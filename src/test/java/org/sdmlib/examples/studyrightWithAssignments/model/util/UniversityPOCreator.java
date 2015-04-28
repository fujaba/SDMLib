package org.sdmlib.examples.studyrightWithAssignments.model.util;

import org.sdmlib.examples.studyrightWithAssignments.model.University;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

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
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.studyrightWithAssignments.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
