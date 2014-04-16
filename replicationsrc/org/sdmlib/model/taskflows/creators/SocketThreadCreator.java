package org.sdmlib.model.taskflows.creators;

import org.sdmlib.model.taskflows.SocketThread;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class SocketThreadCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      SocketThread.PROPERTY_IP,
      SocketThread.PROPERTY_PORT,
      SocketThread.PROPERTY_IDMAP,
      SocketThread.PROPERTY_DEFAULTTARGETTHREAD,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new SocketThread();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((SocketThread) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((SocketThread) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((SocketThread) entity).removeYou();
   }
}


