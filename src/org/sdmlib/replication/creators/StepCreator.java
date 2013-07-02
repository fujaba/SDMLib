package org.sdmlib.replication.creators;

import org.sdmlib.replication.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.replication.Step;

public class StepCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Step.PROPERTY_NAME,
      Step.PROPERTY_BOARD,
      Step.PROPERTY_EXECUTOR,
      Step.PROPERTY_TASKS,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Step();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Step) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((Step) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Step) entity).removeYou();
   }
}

