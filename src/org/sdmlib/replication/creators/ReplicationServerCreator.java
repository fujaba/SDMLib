package org.sdmlib.replication.creators;

import org.sdmlib.replication.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.replication.ReplicationServer;
import org.sdmlib.replication.ReplicationNode;

public class ReplicationServerCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ReplicationNode.PROPERTY_SHAREDSPACES,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new ReplicationServer();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ReplicationServer) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((ReplicationServer) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ReplicationServer) entity).removeYou();
   }
}

