package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.MatchOtherThen;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.models.pattern.Pattern;

public class MatchOtherThenCreator extends PatternElementCreator
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
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new MatchOtherThen();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (MatchOtherThen.PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attrName))
      {
         return ((MatchOtherThen)target).getHostGraphSrcObject();
      }

      if (MatchOtherThen.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         return ((MatchOtherThen)target).getSrc();
      }

      if (MatchOtherThen.PROPERTY_FORBIDDEN.equalsIgnoreCase(attrName))
      {
         return ((MatchOtherThen)target).getForbidden();
      }

      if (MatchOtherThen.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         return ((MatchOtherThen) target).getPattern();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (MatchOtherThen.PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attrName))
      {
         ((MatchOtherThen)target).setHostGraphSrcObject((Object) value);
         return true;
      }

      if (MatchOtherThen.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         ((MatchOtherThen)target).setSrc((PatternObject<?,?>) value);
         return true;
      }

      if (MatchOtherThen.PROPERTY_FORBIDDEN.equalsIgnoreCase(attrName))
      {
         ((MatchOtherThen)target).setForbidden((PatternObject<?,?>) value);
         return true;
      }

      if (MatchOtherThen.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         ((MatchOtherThen) target).setPattern((Pattern) value);
         return true;
      }

      return super.setValue(target, attrName, value, type);
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

