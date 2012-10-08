package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.chats.CSClearDrawingFlow;
import org.sdmlib.model.taskflows.TaskFlow;

public class CSClearDrawingFlowCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      CSClearDrawingFlow.PROPERTY_IDMAP,
      TaskFlow.PROPERTY_TASKNO,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new CSClearDrawingFlow();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((CSClearDrawingFlow) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((CSClearDrawingFlow) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((CSClearDrawingFlow) entity).removeYou();
   }
}

