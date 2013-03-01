package org.sdmlib.models.pattern.creators;

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
         creatorSet.add(new org.sdmlib.models.pattern.creators.PatternElementCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.PatternElementPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.PatternCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.PatternPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.NegativeApplicationConditionCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.NegativeApplicationConditionPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.PatternObjectCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.PatternObjectPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.PatternLinkCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.PatternLinkPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.AttributeConstraintCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.AttributeConstraintPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.LinkConstraintCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.LinkConstraintPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.MatchIsomorphicConstraintCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.MatchIsomorphicConstraintPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.DestroyObjectElemCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.DestroyObjectElemPOCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.OptionalSubPatternCreator());
         creatorSet.add(new org.sdmlib.models.pattern.creators.OptionalSubPatternPOCreator());
         creatorSet.add(new org.sdmlib.serialization.json.creators.JsonIdMapCreator());
         creatorSet.add(new org.sdmlib.serialization.json.creators.JsonIdMapPOCreator());
         creatorSet.add(new org.sdmlib.serialization.json.creators.SDMLibJsonIdMapCreator());
         creatorSet.add(new org.sdmlib.serialization.json.creators.SDMLibJsonIdMapPOCreator());
         // creatorSet.addAll(org.sdmlib.models.pattern.creators.CreatorCreator.getCreatorSet());
      }
      
      return creatorSet;
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.addCreator(getCreatorSet());

      return jsonIdMap;
   }
}





