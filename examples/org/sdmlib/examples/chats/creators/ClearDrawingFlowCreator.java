package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.ClearDrawingFlow;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class ClearDrawingFlowCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ClearDrawingFlow.PROPERTY_GUI,
      TaskFlow.PROPERTY_TASKNO,
      TaskFlow.PROPERTY_IDMAP,
      TaskFlow.PROPERTY_SUBFLOW,
      TaskFlow.PROPERTY_PARENT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new ClearDrawingFlow();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ClearDrawingFlow) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ClearDrawingFlow) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ClearDrawingFlow) entity).removeYou();
   }
}



