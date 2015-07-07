package org.sdmlib.test.examples.studyright.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.studyright.model.Professor;

import de.uniks.networkparser.json.JsonIdMap;

public class ProfessorPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ProfessorPO(new Professor[]{});
      } else {
          return new ProfessorPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
