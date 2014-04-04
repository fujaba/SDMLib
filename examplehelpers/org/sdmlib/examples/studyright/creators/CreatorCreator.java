package org.sdmlib.examples.studyright.creators;

import java.util.LinkedHashSet;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static LinkedHashSet<SendableEntityCreator> creatorSet = null;

   public static LinkedHashSet<SendableEntityCreator> getCreatorSet()
   {
      if (creatorSet == null)
      {
         creatorSet = new LinkedHashSet<SendableEntityCreator>();
         creatorSet
            .add(new org.sdmlib.examples.studyright.creators.ProfessorCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyright.creators.ProfessorPOCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyright.creators.TopicCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyright.creators.TopicPOCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyright.creators.UniversityCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyright.creators.UniversityPOCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyright.creators.StudentCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyright.creators.StudentPOCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyright.creators.RoomCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyright.creators.RoomPOCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyright.creators.AssignmentCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyright.creators.AssignmentPOCreator());
         creatorSet.addAll(org.sdmlib.models.pattern.creators.CreatorCreator
            .getCreatorSet());
      }

      return creatorSet;
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap()
         .withSessionId(sessionID);

      jsonIdMap.withCreator(getCreatorSet());

      return jsonIdMap;
   }
}
