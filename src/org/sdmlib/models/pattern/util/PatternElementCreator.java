package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class PatternElementCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      PatternElement.PROPERTY_PATTERN,
      PatternElement.PROPERTY_MODIFIER,
      PatternElement.PROPERTY_HASMATCH,
      PatternElement.PROPERTY_DOALLMATCHES,
      PatternElement.PROPERTY_PATTERNOBJECTNAME
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new PatternElement<Object>();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PatternElement.PROPERTY_PATTERN.equalsIgnoreCase(attribute))
      {
         return ((PatternElement<?>)target).getPattern();
      }

      if (PatternElement.PROPERTY_MODIFIER.equalsIgnoreCase(attribute))
      {
         return ((PatternElement<?>)target).getModifier();
      }

      if (PatternElement.PROPERTY_HASMATCH.equalsIgnoreCase(attribute))
      {
         return ((PatternElement<?>)target).getHasMatch();
      }

      if (PatternElement.PROPERTY_DOALLMATCHES.equalsIgnoreCase(attribute))
      {
         return ((PatternElement<?>)target).getDoAllMatches();
      }

      if (PatternElement.PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attribute))
      {
         return ((PatternElement<?>)target).getPatternObjectName();
      }
      return super.getValue(target, attribute);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (PatternElement.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         ((PatternElement<?>)target).setPattern((Pattern<Object>) value);
         return true;
      }

      if (PatternElement.PROPERTY_MODIFIER.equalsIgnoreCase(attrName))
      {
         ((PatternElement<?>)target).setModifier((String) value);
         return true;
      }

      if (PatternElement.PROPERTY_HASMATCH.equalsIgnoreCase(attrName))
      {
         ((PatternElement<?>)target).setHasMatch((Boolean) value);
         return true;
      }

      if (PatternElement.PROPERTY_DOALLMATCHES.equalsIgnoreCase(attrName))
      {
         ((PatternElement<?>)target).setDoAllMatches((Boolean) value);
         return true;
      }

      if (PatternElement.PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attrName))
      {
         ((PatternElement<?>)target).setPatternObjectName((String) value);
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
      ((PatternElement<?>) entity).removeYou();
   }
}
