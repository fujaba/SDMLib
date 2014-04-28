package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class DestroyObjectElemCreator extends EntityFactory
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
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new DestroyObjectElem();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((DestroyObjectElem) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((DestroyObjectElem) target).set(attrName, value);
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




