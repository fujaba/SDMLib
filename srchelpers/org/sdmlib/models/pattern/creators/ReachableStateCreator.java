package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.pattern.ReachableState;

public class ReachableStateCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ReachableState.PROPERTY_PARENT,
      ReachableState.PROPERTY_MASTER,
      ReachableState.PROPERTY_GRAPHROOT,
      ReachableState.PROPERTY_NUMBER,
      ReachableState.PROPERTY_RULEAPPLICATIONS,
      ReachableState.PROPERTY_RESULTOF,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new ReachableState();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ReachableState) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((ReachableState) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ReachableState) entity).removeYou();
   }
}





