package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.Pattern;

public class NegativeApplicationConditionCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      NegativeApplicationCondition.PROPERTY_HASMATCH,
      Pattern.PROPERTY_ELEMENTS, 
      NegativeApplicationCondition.PROPERTY_CURRENTNAC,
      NegativeApplicationCondition.PROPERTY_MODIFIER,
      NegativeApplicationCondition.PROPERTY_PATTERNOBJECTNAME,
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


