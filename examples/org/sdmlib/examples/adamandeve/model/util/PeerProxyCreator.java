package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.logger.PeerProxy;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class PeerProxyCreator extends EntityFactory
{
   public static final String PROPERTY_IP = "ip";
   public static final String PROPERTY_PORT = "port";
   public static final String PROPERTY_IDMAP = "idMap";
   private final String[] properties = new String[]
   {
      PROPERTY_IP,
      PROPERTY_PORT,
      PROPERTY_IDMAP,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return null;
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (PeerProxyCreator.PROPERTY_IP.equalsIgnoreCase(attrName))
      {
         return ((PeerProxy) target).getIp();
      }

      if (PeerProxyCreator.PROPERTY_PORT.equalsIgnoreCase(attrName))
      {
         return ((PeerProxy) target).getPort();
      }

      if (PeerProxyCreator.PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         return ((PeerProxy) target).getIdMap();
      }

      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (PeerProxyCreator.PROPERTY_IP.equalsIgnoreCase(attrName))
      {
         ((PeerProxy) target).setIp((String) value);
         return true;
      }

      if (PeerProxyCreator.PROPERTY_PORT.equalsIgnoreCase(attrName))
      {
         ((PeerProxy) target).setPort(Integer.parseInt(value.toString()));
         return true;
      }

      if (PeerProxyCreator.PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         ((PeerProxy) target).setIdMap((org.sdmlib.serialization.SDMLibJsonIdMap) value);
         return true;
      }
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      // wrapped object has no removeYou method
   }
}

