/*
   Copyright (c) 2015 zuendorf 
   
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
   
package org.sdmlib.replication.util;

import org.sdmlib.replication.SeppelChannel;
import org.sdmlib.replication.SeppelScope;
import org.sdmlib.replication.SeppelSpaceProxy;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class SeppelSpaceProxyCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      SeppelSpaceProxy.PROPERTY_SPACEID,
      SeppelSpaceProxy.PROPERTY_ACCEPTSCONNECTIONREQUESTS,
      SeppelSpaceProxy.PROPERTY_HOSTNAME,
      SeppelSpaceProxy.PROPERTY_PORTNO,
      SeppelSpaceProxy.PROPERTY_LOGINNAME,
      SeppelSpaceProxy.PROPERTY_PASSWORD,
      SeppelSpaceProxy.PROPERTY_PARTNERS,
      SeppelSpaceProxy.PROPERTY_SCOPES,
      SeppelSpaceProxy.PROPERTY_CHANNEL,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new SeppelSpaceProxy();
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

      if (SeppelSpaceProxy.PROPERTY_SPACEID.equalsIgnoreCase(attribute))
      {
         return ((SeppelSpaceProxy) target).getSpaceId();
      }

      if (SeppelSpaceProxy.PROPERTY_ACCEPTSCONNECTIONREQUESTS.equalsIgnoreCase(attribute))
      {
         return ((SeppelSpaceProxy) target).isAcceptsConnectionRequests();
      }

      if (SeppelSpaceProxy.PROPERTY_HOSTNAME.equalsIgnoreCase(attribute))
      {
         return ((SeppelSpaceProxy) target).getHostName();
      }

      if (SeppelSpaceProxy.PROPERTY_PORTNO.equalsIgnoreCase(attribute))
      {
         return ((SeppelSpaceProxy) target).getPortNo();
      }

      if (SeppelSpaceProxy.PROPERTY_LOGINNAME.equalsIgnoreCase(attribute))
      {
         return ((SeppelSpaceProxy) target).getLoginName();
      }

      if (SeppelSpaceProxy.PROPERTY_PASSWORD.equalsIgnoreCase(attribute))
      {
         return ((SeppelSpaceProxy) target).getPassword();
      }

      if (SeppelSpaceProxy.PROPERTY_PARTNERS.equalsIgnoreCase(attribute))
      {
         return ((SeppelSpaceProxy) target).getPartners();
      }

      if (SeppelSpaceProxy.PROPERTY_SCOPES.equalsIgnoreCase(attribute))
      {
         return ((SeppelSpaceProxy) target).getScopes();
      }

      if (SeppelSpaceProxy.PROPERTY_CHANNEL.equalsIgnoreCase(attribute))
      {
         return ((SeppelSpaceProxy) target).getChannel();
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

      if (SeppelSpaceProxy.PROPERTY_SPACEID.equalsIgnoreCase(attrName))
      {
         ((SeppelSpaceProxy) target).withSpaceId((String) value);
         return true;
      }

      if (SeppelSpaceProxy.PROPERTY_ACCEPTSCONNECTIONREQUESTS.equalsIgnoreCase(attrName))
      {
         ((SeppelSpaceProxy) target).withAcceptsConnectionRequests((Boolean) value);
         return true;
      }

      if (SeppelSpaceProxy.PROPERTY_HOSTNAME.equalsIgnoreCase(attrName))
      {
         ((SeppelSpaceProxy) target).withHostName((String) value);
         return true;
      }

      if (SeppelSpaceProxy.PROPERTY_PORTNO.equalsIgnoreCase(attrName))
      {
         ((SeppelSpaceProxy) target).withPortNo(Integer.parseInt(value.toString()));
         return true;
      }

      if (SeppelSpaceProxy.PROPERTY_LOGINNAME.equalsIgnoreCase(attrName))
      {
         ((SeppelSpaceProxy) target).withLoginName((String) value);
         return true;
      }

      if (SeppelSpaceProxy.PROPERTY_PASSWORD.equalsIgnoreCase(attrName))
      {
         ((SeppelSpaceProxy) target).withPassword((String) value);
         return true;
      }

      if (SeppelSpaceProxy.PROPERTY_PARTNERS.equalsIgnoreCase(attrName))
      {
         ((SeppelSpaceProxy) target).withPartners((SeppelSpaceProxy) value);
         return true;
      }
      
      if ((SeppelSpaceProxy.PROPERTY_PARTNERS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((SeppelSpaceProxy) target).withoutPartners((SeppelSpaceProxy) value);
         return true;
      }

      if (SeppelSpaceProxy.PROPERTY_SCOPES.equalsIgnoreCase(attrName))
      {
         ((SeppelSpaceProxy) target).withScopes((SeppelScope) value);
         return true;
      }
      
      if ((SeppelSpaceProxy.PROPERTY_SCOPES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((SeppelSpaceProxy) target).withoutScopes((SeppelScope) value);
         return true;
      }

      if (SeppelSpaceProxy.PROPERTY_CHANNEL.equalsIgnoreCase(attrName))
      {
         ((SeppelSpaceProxy) target).setChannel((SeppelChannel) value);
         return true;
      }
      
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return org.sdmlib.replication.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((SeppelSpaceProxy) entity).removeYou();
   }
}
