package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class PatternCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Pattern.PROPERTY_ELEMENTS,
      Pattern.PROPERTY_HASMATCH,
      Pattern.PROPERTY_MODIFIER,
      Pattern.PROPERTY_PATTERNOBJECTNAME,
      Pattern.PROPERTY_DOALLMATCHES,
      Pattern.PROPERTY_CURRENTSUBPATTERN,
      Pattern.PROPERTY_DEBUGMODE,
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
      return new Pattern<Object>();
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

      if (Pattern.PROPERTY_ELEMENTS.equalsIgnoreCase(attribute))
      {
         return ((Pattern<?>)target).getElements();
      }

      if (Pattern.PROPERTY_CURRENTSUBPATTERN.equalsIgnoreCase(attribute))
      {
         return ((Pattern<?>)target).getCurrentSubPattern();
      }

      if (Pattern.PROPERTY_DEBUGMODE.equalsIgnoreCase(attribute))
      {
         return ((Pattern<?>)target).getDebugMode();
      }

      if (Pattern.PROPERTY_TRACE.equalsIgnoreCase(attribute))
      {
         return ((Pattern<?>)target).getTrace();
      }

      if (Pattern.PROPERTY_RGRAPH.equalsIgnoreCase(attribute))
      {
         return ((Pattern<?>)target).getRgraph();
      }

      if (Pattern.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Pattern<?>)target).getName();
      }

      if (Pattern.PROPERTY_PATTERN.equalsIgnoreCase(attribute))
      {
         return ((Pattern) target).getPattern();
      }
      return super.getValue(target, attribute);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Pattern.PROPERTY_ELEMENTS.equalsIgnoreCase(attrName))
      {
         ((Pattern<?>)target).addToElements((PatternElement<?>) value);
         return true;
      }
      
      if ((Pattern.PROPERTY_ELEMENTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Pattern<?>)target).removeFromElements((PatternElement<?>) value);
         return true;
      }

      if (Pattern.PROPERTY_CURRENTSUBPATTERN.equalsIgnoreCase(attrName))
      {
         ((Pattern<?>)target).setCurrentSubPattern((Pattern<?>) value);
         return true;
      }

      if (Pattern.PROPERTY_DEBUGMODE.equalsIgnoreCase(attrName))
      {
         ((Pattern<?>)target).setDebugMode(Integer.parseInt(value.toString()));
         return true;
      }

      if (Pattern.PROPERTY_TRACE.equalsIgnoreCase(attrName))
      {
         ((Pattern<?>)target).setTrace((StringBuilder) value);
         return true;
      }

      if (Pattern.PROPERTY_RGRAPH.equalsIgnoreCase(attrName))
      {
         ((Pattern<?>)target).setRgraph((ReachabilityGraph) value);
         return true;
      }

      if (Pattern.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Pattern<?>)target).setName((String) value);
         return true;
      }

      if (Pattern.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         ((Pattern) target).setPattern((Pattern) value);
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
      ((Pattern<?>) entity).removeYou();
   }
}
