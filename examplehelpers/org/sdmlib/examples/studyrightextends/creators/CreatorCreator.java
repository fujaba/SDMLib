package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.serialization.json.JsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.addCreator(new org.sdmlib.examples.studyrightextends.creators.UniversityCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyrightextends.creators.FemaleCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyrightextends.creators.StudentCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyrightextends.creators.ProfessorCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyrightextends.creators.LectureCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyrightextends.creators.RoomCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyrightextends.creators.UniversityCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyrightextends.creators.FemaleCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyrightextends.creators.StudentCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyrightextends.creators.ProfessorCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyrightextends.creators.LectureCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyrightextends.creators.RoomCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyrightextends.creators.UniversityCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyrightextends.creators.FemaleCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyrightextends.creators.StudentCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyrightextends.creators.ProfessorCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyrightextends.creators.LectureCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.studyrightextends.creators.RoomCreator());
      return jsonIdMap;
   }
}



