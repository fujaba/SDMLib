package org.sdmlib.replication.creators;

import org.sdmlib.replication.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.replication.ReplicationChange;
import org.sdmlib.replication.Task;

public class ReplicationChangeCreator extends EntityFactory
{
   private final String[] properties = new String[]
   { ReplicationChange.PROPERTY_HISTORYIDPREFIX,
         ReplicationChange.PROPERTY_HISTORYIDNUMBER,
         ReplicationChange.PROPERTY_TARGETOBJECTID,
         ReplicationChange.PROPERTY_TARGETPROPERTY,
         ReplicationChange.PROPERTY_ISTOMANYPROPERTY,
         ReplicationChange.PROPERTY_CHANGEMSG, Task.PROPERTY_LOGENTRIES,
   // ReplicationChange.PROPERTY_HISTORY,
   };

   public String[] getProperties()
   {
      return properties;
   }

   public Object getSendableInstance(boolean reference)
   {
      return new ReplicationChange();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((ReplicationChange) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      return ((ReplicationChange) target).set(attrName, value);
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   // ==========================================================================

   @Override
   public void removeObject(Object entity)
   {
      ((ReplicationChange) entity).removeYou();
   }
}
