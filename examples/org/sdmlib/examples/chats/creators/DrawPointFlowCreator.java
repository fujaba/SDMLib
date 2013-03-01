package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.DrawPointFlow;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class DrawPointFlowCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      DrawPointFlow.PROPERTY_X,
      DrawPointFlow.PROPERTY_Y,
      DrawPointFlow.PROPERTY_GUI,
      TaskFlow.PROPERTY_TASKNO,
      DrawPointFlow.PROPERTY_R,
      DrawPointFlow.PROPERTY_B,
      DrawPointFlow.PROPERTY_IDMAP,
      TaskFlow.PROPERTY_SUBFLOW,
      TaskFlow.PROPERTY_PARENT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new DrawPointFlow();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((DrawPointFlow) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((DrawPointFlow) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((DrawPointFlow) entity).removeYou();
   }
}







