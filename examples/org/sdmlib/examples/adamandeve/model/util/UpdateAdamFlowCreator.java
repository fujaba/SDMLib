/*
   Copyright (c) 2014 zuendorf 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.examples.adamandeve.model.UpdateAdamFlow;
import org.sdmlib.logger.TaskFlow;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

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
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (UpdateAdamFlow.PROPERTY_ADAM.equalsIgnoreCase(attribute))
      {
         return ((UpdateAdamFlow) target).getAdam();
      }

      if (UpdateAdamFlow.PROPERTY_EVE.equalsIgnoreCase(attribute))
      {
         return ((UpdateAdamFlow) target).getEve();
      }

      if (UpdateAdamFlow.PROPERTY_IDMAP.equalsIgnoreCase(attribute))
      {
         return ((UpdateAdamFlow) target).getIdMap();
      }

      if (UpdateAdamFlow.PROPERTY_ADAMJARATEVESITELASTMODIFIED.equalsIgnoreCase(attribute))
      {
         return ((UpdateAdamFlow) target).getAdamJarAtEveSiteLastModified();
      }

      if (TaskFlowCreator.PROPERTY_TASKNO.equalsIgnoreCase(attribute))
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
         ((UpdateAdamFlow) target).setIdMap((org.sdmlib.serialization.SDMLibJsonIdMap) value);
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
