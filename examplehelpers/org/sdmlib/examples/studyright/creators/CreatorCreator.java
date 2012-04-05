package org.sdmlib.examples.studyright.creators;

import org.sdmlib.serialization.json.JsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.UniversityCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.StudentCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.RoomCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.UniversityCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.StudentCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.RoomCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.UniversityCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.StudentCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.RoomCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.UniversityCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.StudentCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.RoomCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.UniversityCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.StudentCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyright.creators.RoomCreator());
      return jsonIdMap;
   }
}





