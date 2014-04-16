package org.sdmlib.models.transformations.creators;

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
         creatorSet.add(new org.sdmlib.models.transformations.creators.TemplateCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.TemplatePOCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.PlaceHolderDescriptionCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.PlaceHolderDescriptionPOCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.ObjectCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.ObjectPOCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.ChoiceTemplateCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.ChoiceTemplatePOCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.MatchCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.MatchPOCreator());
         creatorSet.addAll(org.sdmlib.models.pattern.creators.CreatorCreator.getCreatorSet());
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




