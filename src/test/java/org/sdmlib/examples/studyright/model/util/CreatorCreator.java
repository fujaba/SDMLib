package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.LectureCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.LecturePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.RoomCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.RoomPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.UniversityCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.UniversityPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.FemaleCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.FemalePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.ProfessorCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.ProfessorPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.StudentCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.StudentPOCreator());

      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.TopicCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.TopicPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.AssignmentCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.AssignmentPOCreator());
      return jsonIdMap;
   }
}
