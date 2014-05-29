package org.sdmlib.models.pattern.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.PatternElementCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.PatternElementPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.PatternCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.PatternPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.NegativeApplicationConditionCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.NegativeApplicationConditionPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.PatternObjectCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.PatternObjectPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.PatternLinkCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.PatternLinkPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.AttributeConstraintCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.AttributeConstraintPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.LinkConstraintCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.LinkConstraintPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.MatchIsomorphicConstraintCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.MatchIsomorphicConstraintPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.DestroyObjectElemCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.DestroyObjectElemPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.OptionalSubPatternCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.OptionalSubPatternPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.logger.util.JsonIdMapCreator());
      jsonIdMap.withCreator(new org.sdmlib.logger.util.JsonIdMapPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.logger.util.SDMLibJsonIdMapCreator());
      jsonIdMap.withCreator(new org.sdmlib.logger.util.SDMLibJsonIdMapPOCreator());
      jsonIdMap.withCreator(new StringBuilderPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.StringBuilderCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.StringBuilderPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.CardinalityConstraintCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.CardinalityConstraintPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.MatchOtherThenCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.MatchOtherThenPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.ReachabilityGraphCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.ReachabilityGraphPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.ReachableStateCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.ReachableStatePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.CloneOpCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.CloneOpPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.UnifyGraphsOpCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.UnifyGraphsOpPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.RuleApplicationCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.RuleApplicationPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.GenericConstraintCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.pattern.util.GenericConstraintPOCreator());
      
      return jsonIdMap;
   }
}
