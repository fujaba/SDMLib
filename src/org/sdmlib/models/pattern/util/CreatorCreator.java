package org.sdmlib.models.pattern.util;

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
         creatorSet.add(new org.sdmlib.models.pattern.util.PatternElementCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.PatternElementPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.PatternCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.PatternPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.NegativeApplicationConditionCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.NegativeApplicationConditionPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.PatternObjectCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.PatternObjectPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.PatternLinkCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.PatternLinkPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.AttributeConstraintCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.AttributeConstraintPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.LinkConstraintCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.LinkConstraintPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.MatchIsomorphicConstraintCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.MatchIsomorphicConstraintPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.DestroyObjectElemCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.DestroyObjectElemPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.OptionalSubPatternCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.OptionalSubPatternPOCreator());
         creatorSet.add(new org.sdmlib.logger.util.JsonIdMapCreator());
         creatorSet.add(new org.sdmlib.logger.util.JsonIdMapPOCreator());
         creatorSet.add(new org.sdmlib.logger.util.SDMLibJsonIdMapCreator());
         creatorSet.add(new org.sdmlib.logger.util.SDMLibJsonIdMapPOCreator());
         // creatorSet.add(new null.creators.StringBuilderCreator());
         creatorSet.add(new StringBuilderPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.StringBuilderCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.StringBuilderPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.CardinalityConstraintCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.CardinalityConstraintPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.MatchOtherThenCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.MatchOtherThenPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.ReachabilityGraphCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.ReachabilityGraphPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.ReachableStateCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.ReachableStatePOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.CloneOpCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.CloneOpPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.UnifyGraphsOpCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.UnifyGraphsOpPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.RuleApplicationCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.RuleApplicationPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.GenericConstraintCreator());
         creatorSet.add(new org.sdmlib.models.pattern.util.GenericConstraintPOCreator());
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













