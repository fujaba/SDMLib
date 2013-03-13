package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.pattern.PatternElement;

public class CardinalityConstraintCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      CardinalityConstraint.PROPERTY_TGTROLENAME,
      CardinalityConstraint.PROPERTY_HOSTGRAPHSRCOBJECT,
      CardinalityConstraint.PROPERTY_MINCARD,
      CardinalityConstraint.PROPERTY_MAXCARD,
      PatternElement.PROPERTY_MODIFIER,
      PatternElement.PROPERTY_HASMATCH,
      PatternElement.PROPERTY_PATTERNOBJECTNAME,
      PatternElement.PROPERTY_DOALLMATCHES,
      PatternElement.PROPERTY_PATTERN,
      CardinalityConstraint.PROPERTY_SRC,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new CardinalityConstraint();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((CardinalityConstraint) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((CardinalityConstraint) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((CardinalityConstraint) entity).removeYou();
   }
}

