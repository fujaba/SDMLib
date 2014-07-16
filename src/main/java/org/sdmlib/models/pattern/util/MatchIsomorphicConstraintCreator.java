package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.MatchIsomorphicConstraint;
import org.sdmlib.models.pattern.PatternElement;

import de.uniks.networkparser.json.JsonIdMap;

public class MatchIsomorphicConstraintCreator extends PatternElementCreator
{
   private final String[] properties = new String[]
   {
      MatchIsomorphicConstraint.PROPERTY_MODIFIER,
      MatchIsomorphicConstraint.PROPERTY_HASMATCH,
      MatchIsomorphicConstraint.PROPERTY_PATTERNOBJECTNAME,
      PatternElement.PROPERTY_DOALLMATCHES,
      PatternElement.PROPERTY_PATTERN,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new MatchIsomorphicConstraint();
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((MatchIsomorphicConstraint) entity).removeYou();
   }
}
