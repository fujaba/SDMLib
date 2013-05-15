package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.studyrightextends.creators.LectureCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyrightextends.creators.RoomCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyrightextends.creators.UniversityCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyrightextends.creators.FemaleCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyrightextends.creators.ProfessorCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyrightextends.creators.StudentCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.creators.StudyRightClassesCodeGenCreator());
      return jsonIdMap;
   }
}


