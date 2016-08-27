package org.sdmlib.models.pattern.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new PatternElementCreator());
      jsonIdMap.with(new PatternElementPOCreator());
      jsonIdMap.with(new PatternCreator());
      jsonIdMap.with(new PatternPOCreator());
      jsonIdMap.with(new NegativeApplicationConditionCreator());
      jsonIdMap.with(new NegativeApplicationConditionPOCreator());
      jsonIdMap.with(new OptionalSubPatternCreator());
      jsonIdMap.with(new OptionalSubPatternPOCreator());
      jsonIdMap.with(new PatternObjectCreator());
      jsonIdMap.with(new PatternObjectPOCreator());
      jsonIdMap.with(new PatternLinkCreator());
      jsonIdMap.with(new PatternLinkPOCreator());
      jsonIdMap.with(new AttributeConstraintCreator());
      jsonIdMap.with(new AttributeConstraintPOCreator());
      jsonIdMap.with(new LinkConstraintCreator());
      jsonIdMap.with(new LinkConstraintPOCreator());
      jsonIdMap.with(new MatchIsomorphicConstraintCreator());
      jsonIdMap.with(new MatchIsomorphicConstraintPOCreator());
      jsonIdMap.with(new CloneOpCreator());
      jsonIdMap.with(new CloneOpPOCreator());
      jsonIdMap.with(new UnifyGraphsOpCreator());
      jsonIdMap.with(new UnifyGraphsOpPOCreator());
      jsonIdMap.with(new DestroyObjectElemCreator());
      jsonIdMap.with(new DestroyObjectElemPOCreator());
      jsonIdMap.with(new CardinalityConstraintCreator());
      jsonIdMap.with(new CardinalityConstraintPOCreator());
      jsonIdMap.with(new MatchOtherThenCreator());
      jsonIdMap.with(new MatchOtherThenPOCreator());
      jsonIdMap.with(new GenericConstraintCreator());
      jsonIdMap.with(new GenericConstraintPOCreator());
      jsonIdMap.with(new ReachabilityGraphCreator());
      jsonIdMap.with(new ReachabilityGraphPOCreator());
      jsonIdMap.with(new ReachableStateCreator());
      jsonIdMap.with(new ReachableStatePOCreator());
      jsonIdMap.with(new RuleApplicationCreator());
      jsonIdMap.with(new RuleApplicationPOCreator());
      jsonIdMap.with(new ObjectCreator());
      jsonIdMap.with(new ObjectPOCreator());
      return jsonIdMap;
   }
}
