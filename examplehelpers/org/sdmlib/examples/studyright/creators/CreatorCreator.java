package org.sdmlib.examples.studyright.creators;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.UniversityCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.StudentCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.RoomCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.ProfessorCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.TopicCreator());
      return jsonIdMap;
   }
}




































