package org.sdmlib.replication.creators;

import org.sdmlib.replication.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.replication.ReplicationChannel;

public class ReplicationChannelCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ReplicationChannel.PROPERTY_SHAREDSPACE,
      ReplicationChannel.PROPERTY_SOCKET,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new ReplicationChannel();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ReplicationChannel) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ReplicationChannel) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ReplicationChannel) entity).removeYou();
   }
}


