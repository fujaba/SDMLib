package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.CTDrawPoint;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class CTDrawPointCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      TaskFlow.PROPERTY_TASKNO,
      CTDrawPoint.PROPERTY_X,
      CTDrawPoint.PROPERTY_Y,
      CTDrawPoint.PROPERTY_R,
      CTDrawPoint.PROPERTY_G,
      CTDrawPoint.PROPERTY_B,
      CTDrawPoint.PROPERTY_IDMAP,
      TaskFlow.PROPERTY_SUBFLOW,
      TaskFlow.PROPERTY_PARENT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new CTDrawPoint();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((CTDrawPoint) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((CTDrawPoint) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((CTDrawPoint) entity).removeYou();
   }
}



