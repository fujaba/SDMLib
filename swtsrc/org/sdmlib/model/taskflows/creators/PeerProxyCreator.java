package org.sdmlib.model.taskflows.creators;

import org.sdmlib.model.taskflows.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.model.taskflows.PeerProxy;

public class PeerProxyCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      PeerProxy.PROPERTY_IP,
      PeerProxy.PROPERTY_PORT,
      PeerProxy.PROPERTY_IDMAP,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new PeerProxy();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((PeerProxy) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((PeerProxy) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((PeerProxy) entity).removeYou();
   }
}

