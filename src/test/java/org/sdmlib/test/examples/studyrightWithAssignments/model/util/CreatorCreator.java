package org.sdmlib.test.examples.studyrightWithAssignments.model.util;
import de.uniks.networkparser.IdMap;

class CreatorCreator {

   public static final IdMap createIdMap(String session) {
        IdMap map = new IdMap().withSession(session);
        map.withCreator(new AssignmentCreator());
        map.withCreator(new PresidentCreator());
        map.withCreator(new RoomCreator());
        map.withCreator(new StudentCreator());
        map.withCreator(new TeachingAssistantCreator());
        map.withCreator(new UniversityCreator());

        return map;
   }
}