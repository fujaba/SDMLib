package org.sdmlib.models.transformations.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.with(new org.sdmlib.models.transformations.util.TemplateCreator());
      jsonIdMap.with(new org.sdmlib.models.transformations.util.TemplatePOCreator());
      jsonIdMap.with(new org.sdmlib.models.transformations.util.PlaceHolderDescriptionCreator());
      jsonIdMap.with(new org.sdmlib.models.transformations.util.PlaceHolderDescriptionPOCreator());
      jsonIdMap.with(new org.sdmlib.models.transformations.util.ChoiceTemplateCreator());
      jsonIdMap.with(new org.sdmlib.models.transformations.util.ChoiceTemplatePOCreator());
      jsonIdMap.with(new org.sdmlib.models.transformations.util.MatchCreator());
      jsonIdMap.with(new org.sdmlib.models.transformations.util.MatchPOCreator());

      jsonIdMap.with(new TemplateCreator());
      jsonIdMap.with(new TemplatePOCreator());
      jsonIdMap.with(new PlaceHolderDescriptionCreator());
      jsonIdMap.with(new PlaceHolderDescriptionPOCreator());
      jsonIdMap.with(new ChoiceTemplateCreator());
      jsonIdMap.with(new ChoiceTemplatePOCreator());
      jsonIdMap.with(new MatchCreator());
      jsonIdMap.with(new MatchPOCreator());
      jsonIdMap.with(new TemplateCreator());
      jsonIdMap.with(new TemplatePOCreator());
      jsonIdMap.with(new PlaceHolderDescriptionCreator());
      jsonIdMap.with(new PlaceHolderDescriptionPOCreator());
      jsonIdMap.with(new ChoiceTemplateCreator());
      jsonIdMap.with(new ChoiceTemplatePOCreator());
      jsonIdMap.with(new MatchCreator());
      jsonIdMap.with(new MatchPOCreator());
      jsonIdMap.with(new TemplateCreator());
      jsonIdMap.with(new TemplatePOCreator());
      jsonIdMap.with(new PlaceHolderDescriptionCreator());
      jsonIdMap.with(new PlaceHolderDescriptionPOCreator());
      jsonIdMap.with(new ChoiceTemplateCreator());
      jsonIdMap.with(new ChoiceTemplatePOCreator());
      jsonIdMap.with(new MatchCreator());
      jsonIdMap.with(new MatchPOCreator());
      jsonIdMap.with(new ObjectCreator());
      jsonIdMap.with(new ObjectPOCreator());
      jsonIdMap.with(new TemplateCreator());
      jsonIdMap.with(new TemplatePOCreator());
      jsonIdMap.with(new PlaceHolderDescriptionCreator());
      jsonIdMap.with(new PlaceHolderDescriptionPOCreator());
      jsonIdMap.with(new ChoiceTemplateCreator());
      jsonIdMap.with(new ChoiceTemplatePOCreator());
      jsonIdMap.with(new MatchCreator());
      jsonIdMap.with(new MatchPOCreator());
      jsonIdMap.with(new ObjectCreator());
      jsonIdMap.with(new ObjectPOCreator());
      jsonIdMap.with(new TemplateCreator());
      jsonIdMap.with(new TemplatePOCreator());
      jsonIdMap.with(new PlaceHolderDescriptionCreator());
      jsonIdMap.with(new PlaceHolderDescriptionPOCreator());
      jsonIdMap.with(new ChoiceTemplateCreator());
      jsonIdMap.with(new ChoiceTemplatePOCreator());
      jsonIdMap.with(new MatchCreator());
      jsonIdMap.with(new MatchPOCreator());
      jsonIdMap.with(new ObjectCreator());
      jsonIdMap.with(new ObjectPOCreator());
      jsonIdMap.with(new TemplateCreator());
      jsonIdMap.with(new TemplatePOCreator());
      jsonIdMap.with(new PlaceHolderDescriptionCreator());
      jsonIdMap.with(new PlaceHolderDescriptionPOCreator());
      jsonIdMap.with(new ChoiceTemplateCreator());
      jsonIdMap.with(new ChoiceTemplatePOCreator());
      jsonIdMap.with(new MatchCreator());
      jsonIdMap.with(new MatchPOCreator());
      jsonIdMap.with(new ObjectCreator());
      jsonIdMap.with(new ObjectPOCreator());
      jsonIdMap.with(new TemplateCreator());
      jsonIdMap.with(new TemplatePOCreator());
      jsonIdMap.with(new PlaceHolderDescriptionCreator());
      jsonIdMap.with(new PlaceHolderDescriptionPOCreator());
      jsonIdMap.with(new ChoiceTemplateCreator());
      jsonIdMap.with(new ChoiceTemplatePOCreator());
      jsonIdMap.with(new MatchCreator());
      jsonIdMap.with(new MatchPOCreator());
      jsonIdMap.with(new ObjectCreator());
      jsonIdMap.with(new ObjectPOCreator());
      jsonIdMap.with(new TemplateCreator());
      jsonIdMap.with(new TemplatePOCreator());
      jsonIdMap.with(new PlaceHolderDescriptionCreator());
      jsonIdMap.with(new PlaceHolderDescriptionPOCreator());
      jsonIdMap.with(new ChoiceTemplateCreator());
      jsonIdMap.with(new ChoiceTemplatePOCreator());
      jsonIdMap.with(new MatchCreator());
      jsonIdMap.with(new MatchPOCreator());
      jsonIdMap.with(new ObjectCreator());
      jsonIdMap.with(new ObjectPOCreator());
      return jsonIdMap;
   }
}
