package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.UnifyGraphsOp;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class UnifyGraphsOpCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      PatternElement.PROPERTY_MODIFIER,
      PatternElement.PROPERTY_HASMATCH,
      PatternElement.PROPERTY_PATTERNOBJECTNAME,
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
      return new UnifyGraphsOp();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (PatternElement.PROPERTY_MODIFIER.equalsIgnoreCase(attrName))
      {
         return ((PatternElement<?>)target).getModifier();
      }

      if (PatternElement.PROPERTY_HASMATCH.equalsIgnoreCase(attrName))
      {
         return ((PatternElement<?>)target).getHasMatch();
      }

      if (PatternElement.PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attrName))
      {
         return ((PatternElement<?>)target).getPatternObjectName();
      }

      if (PatternElement.PROPERTY_DOALLMATCHES.equalsIgnoreCase(attrName))
      {
         return ((PatternElement<?>)target).getDoAllMatches();
      }

      if (PatternElement.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         return ((PatternElement<?>)target).getPattern();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
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

      if (PatternElement.PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attrName))
      {
         ((PatternElement<?>)target).setPatternObjectName((String) value);
         return true;
      }

      if (PatternElement.PROPERTY_DOALLMATCHES.equalsIgnoreCase(attrName))
      {
         ((PatternElement<?>)target).setDoAllMatches((Boolean) value);
         return true;
      }

      if (PatternElement.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         ((PatternElement<?>)target).setPattern((Pattern<Object>) value);
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
      ((UnifyGraphsOp) entity).removeYou();
   }
}

