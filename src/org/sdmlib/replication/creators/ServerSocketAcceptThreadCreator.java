package org.sdmlib.replication.creators;

import org.sdmlib.replication.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.replication.ServerSocketAcceptThread;

public class ServerSocketAcceptThreadCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ServerSocketAcceptThread.PROPERTY_PORT,
      ServerSocketAcceptThread.PROPERTY_REPLICATIONNODE,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new ServerSocketAcceptThread();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ServerSocketAcceptThread) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((ServerSocketAcceptThread) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ServerSocketAcceptThread) entity).removeYou();
   }
}

