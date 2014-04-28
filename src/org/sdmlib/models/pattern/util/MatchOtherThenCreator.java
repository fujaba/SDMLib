package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.MatchOtherThen;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class MatchOtherThenCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      MatchOtherThen.PROPERTY_HOSTGRAPHSRCOBJECT,
      PatternElement.PROPERTY_MODIFIER,
      PatternElement.PROPERTY_HASMATCH,
      PatternElement.PROPERTY_PATTERNOBJECTNAME,
      PatternElement.PROPERTY_DOALLMATCHES,
      PatternElement.PROPERTY_PATTERN,
      MatchOtherThen.PROPERTY_SRC,
      MatchOtherThen.PROPERTY_FORBIDDEN,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new MatchOtherThen();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((MatchOtherThen) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((MatchOtherThen) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((MatchOtherThen) entity).removeYou();
   }
}

