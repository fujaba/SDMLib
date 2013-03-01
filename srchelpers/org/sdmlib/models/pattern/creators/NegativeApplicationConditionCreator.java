package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class NegativeApplicationConditionCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      NegativeApplicationCondition.PROPERTY_HASMATCH,
      Pattern.PROPERTY_ELEMENTS, 
      Pattern.PROPERTY_CURRENTSUBPATTERN,
      NegativeApplicationCondition.PROPERTY_MODIFIER,
      NegativeApplicationCondition.PROPERTY_PATTERNOBJECTNAME,
      PatternElement.PROPERTY_DOALLMATCHES,
      Pattern.PROPERTY_DEBUGMODE,
      PatternElement.PROPERTY_PATTERN,
      Pattern.PROPERTY_TRACE,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new NegativeApplicationCondition();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((NegativeApplicationCondition) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((NegativeApplicationCondition) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((NegativeApplicationCondition) entity).removeYou();
   }
}





