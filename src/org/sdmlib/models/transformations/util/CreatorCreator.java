package org.sdmlib.models.transformations.util;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.util.TemplateCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.util.TemplatePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.util.PlaceHolderDescriptionCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.util.PlaceHolderDescriptionPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.util.ObjectCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.util.ObjectPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.util.ChoiceTemplateCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.util.ChoiceTemplatePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.util.MatchCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.util.MatchPOCreator());
      
      // Add All Creators from Pattern
      JsonIdMap patternMap = org.sdmlib.models.pattern.util.PatternCreator.createIdMap(sessionID);
      jsonIdMap.withCreator(patternMap.getCreators());
      
      return jsonIdMap;
   }
}
