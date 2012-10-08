package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.chats.CTClearDrawing;
import org.sdmlib.model.taskflows.TaskFlow;

public class CTClearDrawingCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      TaskFlow.PROPERTY_TASKNO,
      CTClearDrawing.PROPERTY_IDMAP,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new CTClearDrawing();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((CTClearDrawing) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((CTClearDrawing) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((CTClearDrawing) entity).removeYou();
   }
}


