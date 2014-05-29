package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.RuleApplication;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class RuleApplicationCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      RuleApplication.PROPERTY_DESCRIPTION,
      RuleApplication.PROPERTY_SRC,
      RuleApplication.PROPERTY_TGT,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new RuleApplication();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (RuleApplication.PROPERTY_DESCRIPTION.equalsIgnoreCase(attrName))
      {
         return ((RuleApplication)target).getDescription();
      }

      if (RuleApplication.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         return ((RuleApplication)target).getSrc();
      }

      if (RuleApplication.PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         return ((RuleApplication)target).getTgt();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (RuleApplication.PROPERTY_DESCRIPTION.equalsIgnoreCase(attrName))
      {
         ((RuleApplication)target).setDescription((String) value);
         return true;
      }

      if (RuleApplication.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         ((RuleApplication)target).setSrc((ReachableState) value);
         return true;
      }

      if (RuleApplication.PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         ((RuleApplication)target).setTgt((ReachableState) value);
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
      ((RuleApplication) entity).removeYou();
   }
}

