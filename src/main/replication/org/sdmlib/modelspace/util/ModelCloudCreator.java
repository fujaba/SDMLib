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
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.modelspace.ModelCloud;
import org.sdmlib.modelspace.ModelCloudProxy;
import org.sdmlib.modelspace.ModelSpaceProxy;

public class ModelCloudCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ModelCloud.PROPERTY_ACCEPTPORT,
      ModelCloud.PROPERTY_SERVERS,
      // ModelCloud.PROPERTY_MODELSPACES,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ModelCloud();
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

      if (ModelCloud.PROPERTY_ACCEPTPORT.equalsIgnoreCase(attribute))
      {
         return ((ModelCloud) target).getAcceptPort();
      }

      if (ModelCloud.PROPERTY_SERVERS.equalsIgnoreCase(attribute))
      {
         return ((ModelCloud) target).getServers();
      }

      if (ModelCloud.PROPERTY_MODELSPACES.equalsIgnoreCase(attribute))
      {
         return ((ModelCloud) target).getModelSpaces();
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

      if (ModelCloud.PROPERTY_ACCEPTPORT.equalsIgnoreCase(attrName))
      {
         ((ModelCloud) target).withAcceptPort(Integer.parseInt(value.toString()));
         return true;
      }

      if (ModelCloud.PROPERTY_SERVERS.equalsIgnoreCase(attrName))
      {
         ((ModelCloud) target).withServers((ModelCloudProxy) value);
         return true;
      }
      
      if ((ModelCloud.PROPERTY_SERVERS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ModelCloud) target).withoutServers((ModelCloudProxy) value);
         return true;
      }

      if (ModelCloud.PROPERTY_MODELSPACES.equalsIgnoreCase(attrName))
      {
         ((ModelCloud) target).withModelSpaces((ModelSpaceProxy) value);
         return true;
      }
      
      if ((ModelCloud.PROPERTY_MODELSPACES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ModelCloud) target).withoutModelSpaces((ModelSpaceProxy) value);
         return true;
      }
      
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return org.sdmlib.modelspace.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ModelCloud) entity).removeYou();
   }
}