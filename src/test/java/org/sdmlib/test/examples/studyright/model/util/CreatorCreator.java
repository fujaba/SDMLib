package org.sdmlib.test.examples.studyright.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.with(new LectureCreator());
      jsonIdMap.with(new LecturePOCreator());
      jsonIdMap.with(new RoomCreator());
      jsonIdMap.with(new RoomPOCreator());
      jsonIdMap.with(new UniversityCreator());
      jsonIdMap.with(new UniversityPOCreator());
      jsonIdMap.with(new FemaleCreator());
      jsonIdMap.with(new FemalePOCreator());
      jsonIdMap.with(new ProfessorCreator());
      jsonIdMap.with(new ProfessorPOCreator());
      jsonIdMap.with(new StudentCreator());
      jsonIdMap.with(new StudentPOCreator());
      jsonIdMap.with(new ProfessorCreator());
      jsonIdMap.with(new ProfessorPOCreator());
      jsonIdMap.with(new TopicCreator());
      jsonIdMap.with(new TopicPOCreator());
      jsonIdMap.with(new UniversityCreator());
      jsonIdMap.with(new UniversityPOCreator());
      jsonIdMap.with(new StudentCreator());
      jsonIdMap.with(new StudentPOCreator());
      jsonIdMap.with(new RoomCreator());
      jsonIdMap.with(new RoomPOCreator());
      jsonIdMap.with(new AssignmentCreator());
      jsonIdMap.with(new AssignmentPOCreator());
      
      jsonIdMap.with(new PersonCreator());
      jsonIdMap.with(new PersonPOCreator());
      jsonIdMap.with(new MaleCreator());
      jsonIdMap.with(new MalePOCreator());
      return jsonIdMap;
   }
}
