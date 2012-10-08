package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.chats.CSDrawPointFlow;
import org.sdmlib.model.taskflows.TaskFlow;

public class CSDrawPointFlowCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      CSDrawPointFlow.PROPERTY_X,
      CSDrawPointFlow.PROPERTY_Y,
      CSDrawPointFlow.PROPERTY_R,
      CSDrawPointFlow.PROPERTY_G,
      CSDrawPointFlow.PROPERTY_B,
      CSDrawPointFlow.PROPERTY_IDMAP,
      TaskFlow.PROPERTY_TASKNO,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new CSDrawPointFlow();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((CSDrawPointFlow) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((CSDrawPointFlow) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((CSDrawPointFlow) entity).removeYou();
   }
}

