package org.sdmlib.test.examples.studyright.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

class CreatorCreator{

   public static SDMLibJsonIdMap createIdMap(String sessionID)
   {
	   SDMLibJsonIdMap map = new SDMLibJsonIdMap();
      map.withSessionId(sessionID);
      
      map.with(new LectureCreator());
      map.with(new LecturePOCreator());
      map.with(new RoomCreator());
      map.with(new RoomPOCreator());
      map.with(new UniversityCreator());
      map.with(new UniversityPOCreator());
      map.with(new FemaleCreator());
      map.with(new FemalePOCreator());
      map.with(new ProfessorCreator());
      map.with(new ProfessorPOCreator());
      map.with(new StudentCreator());
      map.with(new StudentPOCreator());
      map.with(new ProfessorCreator());
      map.with(new ProfessorPOCreator());
      map.with(new TopicCreator());
      map.with(new TopicPOCreator());
      map.with(new AssignmentCreator());
      map.with(new AssignmentPOCreator());
      
      map.with(new PersonCreator());
      map.with(new PersonPOCreator());
      map.with(new MaleCreator());
      map.with(new MalePOCreator());
      return map;
   }
}
