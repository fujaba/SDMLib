package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.pattern.RuleApplication;

public class RuleApplicationCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      RuleApplication.PROPERTY_DESCRIPTION,
      RuleApplication.PROPERTY_SRC,
      RuleApplication.PROPERTY_TGT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new RuleApplication();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((RuleApplication) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((RuleApplication) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((RuleApplication) entity).removeYou();
   }
}

