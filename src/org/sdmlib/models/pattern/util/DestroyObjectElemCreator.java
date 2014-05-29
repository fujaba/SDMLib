package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;

import de.uniks.networkparser.json.JsonIdMap;

public class DestroyObjectElemCreator extends PatternElementCreator
{
   private final String[] properties = new String[]
   {
      DestroyObjectElem.PROPERTY_MODIFIER,
      DestroyObjectElem.PROPERTY_HASMATCH,
      DestroyObjectElem.PROPERTY_PATTERNOBJECTNAME,
      DestroyObjectElem.PROPERTY_PATTERNOBJECT,
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
      return new DestroyObjectElem();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (DestroyObjectElem.PROPERTY_PATTERNOBJECT.equalsIgnoreCase(attrName))
      {
         return ((DestroyObjectElem)target).getPatternObject();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (DestroyObjectElem.PROPERTY_PATTERNOBJECT.equalsIgnoreCase(attrName))
      {
         ((DestroyObjectElem)target).setPatternObject((PatternObject<?,?>) value);
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
      ((DestroyObjectElem) entity).removeYou();
   }
}




