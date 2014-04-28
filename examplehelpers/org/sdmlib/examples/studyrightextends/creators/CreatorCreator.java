package org.sdmlib.examples.studyrightextends.creators;

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
            .add(new org.sdmlib.examples.studyrightextends.creators.LectureCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightextends.creators.LecturePOCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightextends.creators.RoomCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightextends.creators.RoomPOCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightextends.creators.UniversityCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightextends.creators.UniversityPOCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightextends.creators.FemaleCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightextends.creators.FemalePOCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightextends.creators.ProfessorCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightextends.creators.ProfessorPOCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightextends.creators.StudentCreator());
         creatorSet
            .add(new org.sdmlib.examples.studyrightextends.creators.StudentPOCreator());
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
