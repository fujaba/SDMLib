package org.sdmlib.examples.studyrightWithAssignments.creators;

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
            .add(new org.sdmlib.examples.studyrightWithAssignments.creators.UniversityCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightWithAssignments.creators.UniversityPOCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightWithAssignments.creators.StudentCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightWithAssignments.creators.StudentPOCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightWithAssignments.creators.RoomCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightWithAssignments.creators.RoomPOCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightWithAssignments.creators.AssignmentCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightWithAssignments.creators.AssignmentPOCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightWithAssignments.creators.TeachingAssistantCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightWithAssignments.creators.TeachingAssistantPOCreator());
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
