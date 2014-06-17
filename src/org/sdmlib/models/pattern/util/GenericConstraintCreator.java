package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.GenericConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;

import de.uniks.networkparser.json.JsonIdMap;

public class GenericConstraintCreator extends PatternElementCreator
{
   private final String[] properties = new String[]
   {
      PatternElement.PROPERTY_MODIFIER,
      PatternElement.PROPERTY_HASMATCH,
      PatternElement.PROPERTY_PATTERNOBJECTNAME,
      PatternElement.PROPERTY_DOALLMATCHES,
      PatternElement.PROPERTY_PATTERN,
      GenericConstraint.PROPERTY_TEXT,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new GenericConstraint();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (GenericConstraint.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         return ((GenericConstraint)target).getText();
      }

      if (GenericConstraint.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         return ((GenericConstraint) target).getPattern();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {      
      if (GenericConstraint.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         ((GenericConstraint)target).setText((String) value);
         return true;
      }

      if (GenericConstraint.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         ((GenericConstraint) target).setPattern((Pattern) value);
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
      ((GenericConstraint) entity).removeYou();
   }
}


