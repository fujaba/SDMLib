package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.OptionalSubPattern;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.models.pattern.ReachabilityGraph;

public class OptionalSubPatternCreator extends PatternCreator
{
   private final String[] properties = new String[]
   {
      OptionalSubPattern.PROPERTY_MODIFIER,
      OptionalSubPattern.PROPERTY_HASMATCH,
      OptionalSubPattern.PROPERTY_PATTERNOBJECTNAME,
      OptionalSubPattern.PROPERTY_DOALLMATCHES,
      OptionalSubPattern.PROPERTY_MATCHFORWARD,
      OptionalSubPattern.PROPERTY_CURRENTSUBPATTERN,
      Pattern.PROPERTY_DEBUGMODE,
      Pattern.PROPERTY_ELEMENTS,
      PatternElement.PROPERTY_PATTERN,
      Pattern.PROPERTY_TRACE,
      Pattern.PROPERTY_RGRAPH,
      Pattern.PROPERTY_NAME,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new OptionalSubPattern();
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
      if (OptionalSubPattern.PROPERTY_MATCHFORWARD.equalsIgnoreCase(attribute))
      {
         return ((OptionalSubPattern)target).getMatchForward();
      }

      if (OptionalSubPattern.PROPERTY_ELEMENTS.equalsIgnoreCase(attribute))
      {
         return ((OptionalSubPattern) target).getElements();
      }

      if (OptionalSubPattern.PROPERTY_RGRAPH.equalsIgnoreCase(attribute))
      {
         return ((OptionalSubPattern) target).getRgraph();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (OptionalSubPattern.PROPERTY_MATCHFORWARD.equalsIgnoreCase(attrName)){
         ((OptionalSubPattern)target).setMatchForward((Boolean) value);
         return true;
      }

      if (OptionalSubPattern.PROPERTY_ELEMENTS.equalsIgnoreCase(attrName))
      {
         ((OptionalSubPattern) target).addToElements((PatternElement) value);
         return true;
      }
      
      if ((OptionalSubPattern.PROPERTY_ELEMENTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((OptionalSubPattern) target).removeFromElements((PatternElement) value);
         return true;
      }

      if (OptionalSubPattern.PROPERTY_RGRAPH.equalsIgnoreCase(attrName))
      {
         ((OptionalSubPattern) target).setRgraph((ReachabilityGraph) value);
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
      ((OptionalSubPattern) entity).removeYou();
   }
}
