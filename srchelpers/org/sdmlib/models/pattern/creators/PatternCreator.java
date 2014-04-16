package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

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
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Pattern();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Pattern) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Pattern) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Pattern) entity).removeYou();
   }
}





















