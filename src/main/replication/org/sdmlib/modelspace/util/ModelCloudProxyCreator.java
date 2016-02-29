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
   
package org.sdmlib.modelspace.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.IdMap;
import org.sdmlib.modelspace.ModelCloudProxy;
import org.sdmlib.modelspace.ModelCloud;
import org.sdmlib.modelspace.ModelSpaceProxy;

public class ModelCloudProxyCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ModelCloudProxy.PROPERTY_HOSTNAME,
      ModelCloudProxy.PROPERTY_PORTNO,
      ModelCloudProxy.PROPERTY_ROOT,
      // ModelCloudProxy.PROPERTY_PROVIDEDSPACES,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ModelCloudProxy();
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

      if (ModelCloudProxy.PROPERTY_HOSTNAME.equalsIgnoreCase(attribute))
      {
         return ((ModelCloudProxy) target).getHostName();
      }

      if (ModelCloudProxy.PROPERTY_PORTNO.equalsIgnoreCase(attribute))
      {
         return ((ModelCloudProxy) target).getPortNo();
      }

      if (ModelCloudProxy.PROPERTY_ROOT.equalsIgnoreCase(attribute))
      {
         return ((ModelCloudProxy) target).getRoot();
      }

      if (ModelCloudProxy.PROPERTY_PROVIDEDSPACES.equalsIgnoreCase(attribute))
      {
         return ((ModelCloudProxy) target).getProvidedSpaces();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (IdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (ModelCloudProxy.PROPERTY_HOSTNAME.equalsIgnoreCase(attrName))
      {
         ((ModelCloudProxy) target).withHostName((String) value);
         return true;
      }

      if (ModelCloudProxy.PROPERTY_PORTNO.equalsIgnoreCase(attrName))
      {
         ((ModelCloudProxy) target).withPortNo(Integer.parseInt(value.toString()));
         return true;
      }

      if (ModelCloudProxy.PROPERTY_ROOT.equalsIgnoreCase(attrName))
      {
         ((ModelCloudProxy) target).setRoot((ModelCloud) value);
         return true;
      }

      if (ModelCloudProxy.PROPERTY_PROVIDEDSPACES.equalsIgnoreCase(attrName))
      {
         ((ModelCloudProxy) target).withProvidedSpaces((ModelSpaceProxy) value);
         return true;
      }
      
      if ((ModelCloudProxy.PROPERTY_PROVIDEDSPACES + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ModelCloudProxy) target).withoutProvidedSpaces((ModelSpaceProxy) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.modelspace.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ModelCloudProxy) entity).removeYou();
   }
}
