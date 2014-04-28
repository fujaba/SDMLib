package org.sdmlib.models.transformations.util;

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
         creatorSet.add(new org.sdmlib.models.transformations.util.TemplateCreator());
         creatorSet.add(new org.sdmlib.models.transformations.util.TemplatePOCreator());
         creatorSet.add(new org.sdmlib.models.transformations.util.PlaceHolderDescriptionCreator());
         creatorSet.add(new org.sdmlib.models.transformations.util.PlaceHolderDescriptionPOCreator());
         creatorSet.add(new org.sdmlib.models.transformations.util.ObjectCreator());
         creatorSet.add(new org.sdmlib.models.transformations.util.ObjectPOCreator());
         creatorSet.add(new org.sdmlib.models.transformations.util.ChoiceTemplateCreator());
         creatorSet.add(new org.sdmlib.models.transformations.util.ChoiceTemplatePOCreator());
         creatorSet.add(new org.sdmlib.models.transformations.util.MatchCreator());
         creatorSet.add(new org.sdmlib.models.transformations.util.MatchPOCreator());
         creatorSet.addAll(org.sdmlib.models.pattern.util.CreatorCreator.getCreatorSet());
      }
      
      return creatorSet;
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(getCreatorSet());

      return jsonIdMap;
   }
}




