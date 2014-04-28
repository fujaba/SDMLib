package org.sdmlib.replication.creators;

import org.sdmlib.replication.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.replication.ReplicationRoot;

public class ReplicationRootCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ReplicationRoot.PROPERTY_NAME,
      ReplicationRoot.PROPERTY_APPLICATIONOBJECT,
      ReplicationRoot.PROPERTY_KIDS,
      ReplicationRoot.PROPERTY_PARENT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new ReplicationRoot();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ReplicationRoot) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }
      return ((ReplicationRoot) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ReplicationRoot) entity).removeYou();
   }
}

