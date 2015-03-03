package org.sdmlib.examples.studyrightWithAssignments.model.util;

import org.sdmlib.examples.studyrightWithAssignments.model.TeachingAssistant;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class TeachingAssistantPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new TeachingAssistantPO(new TeachingAssistant[]{});
      } else {
          return new TeachingAssistantPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.studyrightWithAssignments.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
