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
   
package org.sdmlib.model.taskflows.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.model.taskflows.PeerProxy;

public class PeerProxyCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      PeerProxy.PROPERTY_IP,
      PeerProxy.PROPERTY_PORT,
      PeerProxy.PROPERTY_IDMAP,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new PeerProxy();
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

      if (PeerProxy.PROPERTY_IP.equalsIgnoreCase(attribute))
      {
         return ((PeerProxy) target).getIp();
      }

      if (PeerProxy.PROPERTY_PORT.equalsIgnoreCase(attribute))
      {
         return ((PeerProxy) target).getPort();
      }

      if (PeerProxy.PROPERTY_IDMAP.equalsIgnoreCase(attribute))
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

      if (PeerProxy.PROPERTY_IP.equalsIgnoreCase(attrName))
      {
         ((PeerProxy) target).withIp((String) value);
         return true;
      }

      if (PeerProxy.PROPERTY_PORT.equalsIgnoreCase(attrName))
      {
         ((PeerProxy) target).withPort(Integer.parseInt(value.toString()));
         return true;
      }

      if (PeerProxy.PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         ((PeerProxy) target).withIdMap((org.sdmlib.serialization.SDMLibJsonIdMap) value);
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
      ((PeerProxy) entity).removeYou();
   }
}
