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
   
package org.sdmlib.models.taskflows.util;

import org.sdmlib.models.taskflows.FetchFileFlow;
import org.sdmlib.models.taskflows.TaskFlow;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;

public class FetchFileFlowCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      FetchFileFlow.PROPERTY_FILESERVER,
      FetchFileFlow.PROPERTY_IDMAP,
      FetchFileFlow.PROPERTY_FILENAME,
      TaskFlow.PROPERTY_TASKNO,
      TaskFlow.PROPERTY_SUBFLOW,
      TaskFlow.PROPERTY_PARENT,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new FetchFileFlow();
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

      if (FetchFileFlow.PROPERTY_FILESERVER.equalsIgnoreCase(attribute))
      {
         return ((FetchFileFlow) target).getFileServer();
      }

      if (FetchFileFlow.PROPERTY_IDMAP.equalsIgnoreCase(attribute))
      {
         return ((FetchFileFlow) target).getIdMap();
      }

      if (FetchFileFlow.PROPERTY_FILENAME.equalsIgnoreCase(attribute))
      {
         return ((FetchFileFlow) target).getFileName();
      }

      if (TaskFlow.PROPERTY_TASKNO.equalsIgnoreCase(attribute))
      {
         return ((TaskFlow) target).getTaskNo();
      }

      if (FetchFileFlow.PROPERTY_SUBFLOW.equalsIgnoreCase(attribute))
      {
         return ((FetchFileFlow) target).getSubFlow();
      }

      if (FetchFileFlow.PROPERTY_PARENT.equalsIgnoreCase(attribute))
      {
         return ((FetchFileFlow) target).getParent();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (FetchFileFlow.PROPERTY_FILESERVER.equalsIgnoreCase(attrName))
      {
         ((FetchFileFlow) target).withFileServer((org.sdmlib.models.taskflows.PeerProxy) value);
         return true;
      }

      if (FetchFileFlow.PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         ((FetchFileFlow) target).withIdMap((org.sdmlib.serialization.SDMLibJsonIdMap) value);
         return true;
      }

      if (FetchFileFlow.PROPERTY_FILENAME.equalsIgnoreCase(attrName))
      {
         ((FetchFileFlow) target).withFileName((String) value);
         return true;
      }

      if (TaskFlow.PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         ((TaskFlow) target).withTaskNo(Integer.parseInt(value.toString()));
         return true;
      }

      if (FetchFileFlow.PROPERTY_SUBFLOW.equalsIgnoreCase(attrName))
      {
         ((TaskFlow) target).setSubFlow((TaskFlow) value);
         return true;
      }

      if (FetchFileFlow.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         ((TaskFlow) target).setParent((TaskFlow) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((FetchFileFlow) entity).removeYou();
   }
}
