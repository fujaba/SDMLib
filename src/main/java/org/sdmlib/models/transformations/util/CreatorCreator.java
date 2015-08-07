package org.sdmlib.models.transformations.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.util.TemplateCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.util.TemplatePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.util.PlaceHolderDescriptionCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.util.PlaceHolderDescriptionPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.util.ChoiceTemplateCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.util.ChoiceTemplatePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.util.MatchCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.util.MatchPOCreator());

      jsonIdMap.withCreator(new TemplateCreator());
      jsonIdMap.withCreator(new TemplatePOCreator());
      jsonIdMap.withCreator(new PlaceHolderDescriptionCreator());
      jsonIdMap.withCreator(new PlaceHolderDescriptionPOCreator());
      jsonIdMap.withCreator(new ChoiceTemplateCreator());
      jsonIdMap.withCreator(new ChoiceTemplatePOCreator());
      jsonIdMap.withCreator(new MatchCreator());
      jsonIdMap.withCreator(new MatchPOCreator());
      return jsonIdMap;
   }
}
