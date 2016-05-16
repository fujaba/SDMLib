package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.MatchOtherThan;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;

import de.uniks.networkparser.IdMap;

public class MatchOtherThanCreator extends PatternElementCreator
{
   private final String[] properties = new String[]
   {
      MatchOtherThan.PROPERTY_HOSTGRAPHSRCOBJECT,
      PatternElement.PROPERTY_MODIFIER,
      PatternElement.PROPERTY_HASMATCH,
      PatternElement.PROPERTY_PATTERNOBJECTNAME,
      PatternElement.PROPERTY_DOALLMATCHES,
      PatternElement.PROPERTY_PATTERN,
      MatchOtherThan.PROPERTY_SRC,
      MatchOtherThan.PROPERTY_FORBIDDEN,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new MatchOtherThan();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (MatchOtherThan.PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attrName))
      {
         return ((MatchOtherThan)target).getHostGraphSrcObject();
      }

      if (MatchOtherThan.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         return ((MatchOtherThan)target).getSrc();
      }

      if (MatchOtherThan.PROPERTY_FORBIDDEN.equalsIgnoreCase(attrName))
      {
         return ((MatchOtherThan)target).getForbidden();
      }

      if (MatchOtherThan.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         return ((MatchOtherThan) target).getPattern();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (MatchOtherThan.PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attrName))
      {
         ((MatchOtherThan)target).setHostGraphSrcObject((Object) value);
         return true;
      }

      if (MatchOtherThan.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         ((MatchOtherThan)target).setSrc((PatternObject<?,?>) value);
         return true;
      }

      if (MatchOtherThan.PROPERTY_FORBIDDEN.equalsIgnoreCase(attrName))
      {
         ((MatchOtherThan)target).setForbidden((PatternObject<?,?>) value);
         return true;
      }

      if (MatchOtherThan.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         ((MatchOtherThan) target).setPattern((Pattern) value);
         return true;
      }

      return super.setValue(target, attrName, value, type);
   }
   
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((MatchOtherThan) entity).removeYou();
   }
}

