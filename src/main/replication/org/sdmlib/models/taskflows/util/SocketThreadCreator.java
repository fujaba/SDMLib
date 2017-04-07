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

import org.sdmlib.models.taskflows.SocketThread;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;

public class SocketThreadCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      SocketThread.PROPERTY_IP,
      SocketThread.PROPERTY_PORT,
      SocketThread.PROPERTY_IDMAP,
      SocketThread.PROPERTY_DEFAULTTARGETTHREAD,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new SocketThread();
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

      if (SocketThread.PROPERTY_IP.equalsIgnoreCase(attribute))
      {
         return ((SocketThread) target).getIp();
      }

      if (SocketThread.PROPERTY_PORT.equalsIgnoreCase(attribute))
      {
         return ((SocketThread) target).getPort();
      }

      if (SocketThread.PROPERTY_IDMAP.equalsIgnoreCase(attribute))
      {
         return ((SocketThread) target).getIdMap();
      }

      if (SocketThread.PROPERTY_DEFAULTTARGETTHREAD.equalsIgnoreCase(attribute))
      {
         return ((SocketThread) target).getDefaultTargetThread();
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

      if (SocketThread.PROPERTY_IP.equalsIgnoreCase(attrName))
      {
         ((SocketThread) target).withIp((String) value);
         return true;
      }

      if (SocketThread.PROPERTY_PORT.equalsIgnoreCase(attrName))
      {
         ((SocketThread) target).withPort(Integer.parseInt(value.toString()));
         return true;
      }

      if (SocketThread.PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         ((SocketThread) target).withIdMap((org.sdmlib.serialization.SDMLibJsonIdMap) value);
         return true;
      }

      if (SocketThread.PROPERTY_DEFAULTTARGETTHREAD.equalsIgnoreCase(attrName))
      {
         ((SocketThread) target).withDefaultTargetThread((Object) value);
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
      ((SocketThread) entity).removeYou();
   }
}
