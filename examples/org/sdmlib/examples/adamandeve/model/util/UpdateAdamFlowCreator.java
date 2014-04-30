package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.examples.adamandeve.model.UpdateAdamFlow;
import org.sdmlib.logger.TaskFlow;

public class UpdateAdamFlowCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      UpdateAdamFlow.PROPERTY_ADAM,
      UpdateAdamFlow.PROPERTY_EVE,
      UpdateAdamFlow.PROPERTY_IDMAP,
      UpdateAdamFlow.PROPERTY_ADAMJARATEVESITELASTMODIFIED,
      TaskFlow.PROPERTY_TASKNO,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new UpdateAdamFlow();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (UpdateAdamFlow.PROPERTY_ADAM.equalsIgnoreCase(attrName))
      {
         return ((UpdateAdamFlow) target).getAdam();
      }

      if (UpdateAdamFlow.PROPERTY_EVE.equalsIgnoreCase(attrName))
      {
         return ((UpdateAdamFlow) target).getEve();
      }

      if (UpdateAdamFlow.PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         return ((UpdateAdamFlow) target).getIdMap();
      }

      if (UpdateAdamFlow.PROPERTY_ADAMJARATEVESITELASTMODIFIED.equalsIgnoreCase(attrName))
      {
         return ((UpdateAdamFlow) target).getAdamJarAtEveSiteLastModified();
      }

      if (TaskFlowCreator.PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         return ((TaskFlow) target).getTaskNo();
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

      if (UpdateAdamFlow.PROPERTY_ADAM.equalsIgnoreCase(attrName))
      {
         ((UpdateAdamFlow) target).setAdam((org.sdmlib.logger.PeerProxy) value);
         return true;
      }

      if (UpdateAdamFlow.PROPERTY_EVE.equalsIgnoreCase(attrName))
      {
         ((UpdateAdamFlow) target).setEve((org.sdmlib.logger.PeerProxy) value);
         return true;
      }

      if (UpdateAdamFlow.PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         ((UpdateAdamFlow) target).setIdMap((org.sdmlib.serialization.json.SDMLibJsonIdMap) value);
         return true;
      }

      if (UpdateAdamFlow.PROPERTY_ADAMJARATEVESITELASTMODIFIED.equalsIgnoreCase(attrName))
      {
         ((UpdateAdamFlow) target).setAdamJarAtEveSiteLastModified(Long.parseLong(value.toString()));
         return true;
      }

      if (TaskFlowCreator.PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         ((TaskFlow) target).setTaskNo(Integer.parseInt(value.toString()));
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
      ((UpdateAdamFlow) entity).removeYou();
   }
}

