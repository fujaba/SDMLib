package org.sdmlib.examples.studyright.creators;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.creators.UniversityCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.creators.StudentCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.creators.RoomCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.creators.ProfessorCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.creators.TopicCreator());
      return jsonIdMap;
   }
}




































