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
   
package org.sdmlib.replication.util;

import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.ReplicationServer;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;

public class ReplicationServerCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ReplicationNode.PROPERTY_SPACEID,
      ReplicationNode.PROPERTY_HISTORY,
      ReplicationNode.PROPERTY_LASTCHANGEID,
      ReplicationNode.PROPERTY_NODEID,
      ReplicationNode.PROPERTY_JAVAFXAPPLICATION,
      ReplicationNode.PROPERTY_SHAREDSPACES,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ReplicationServer();
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

      if (ReplicationNode.PROPERTY_SPACEID.equalsIgnoreCase(attribute))
      {
         return ((ReplicationNode) target).getSpaceId();
      }

      if (ReplicationNode.PROPERTY_HISTORY.equalsIgnoreCase(attribute))
      {
         return ((ReplicationNode) target).getHistory();
      }

      if (ReplicationNode.PROPERTY_LASTCHANGEID.equalsIgnoreCase(attribute))
      {
         return ((ReplicationNode) target).getLastChangeId();
      }

      if (ReplicationNode.PROPERTY_NODEID.equalsIgnoreCase(attribute))
      {
         return ((ReplicationNode) target).getNodeId();
      }

      if (ReplicationNode.PROPERTY_JAVAFXAPPLICATION.equalsIgnoreCase(attribute))
      {
         return ((ReplicationNode) target).isJavaFXApplication();
      }

      if (ReplicationServer.PROPERTY_SHAREDSPACES.equalsIgnoreCase(attribute))
      {
         return ((ReplicationServer) target).getSharedSpaces();
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

      if (ReplicationNode.PROPERTY_SPACEID.equalsIgnoreCase(attrName))
      {
         ((ReplicationNode) target).withSpaceId((String) value);
         return true;
      }

      if (ReplicationNode.PROPERTY_HISTORY.equalsIgnoreCase(attrName))
      {
         ((ReplicationNode) target).withHistory((ChangeHistory) value);
         return true;
      }

      if (ReplicationNode.PROPERTY_LASTCHANGEID.equalsIgnoreCase(attrName))
      {
         ((ReplicationNode) target).withLastChangeId(Long.parseLong(value.toString()));
         return true;
      }

      if (ReplicationNode.PROPERTY_NODEID.equalsIgnoreCase(attrName))
      {
         ((ReplicationNode) target).withNodeId((String) value);
         return true;
      }

      if (ReplicationNode.PROPERTY_JAVAFXAPPLICATION.equalsIgnoreCase(attrName))
      {
         ((ReplicationNode) target).withJavaFXApplication((Boolean) value);
         return true;
      }

      if (ReplicationServer.PROPERTY_SHAREDSPACES.equalsIgnoreCase(attrName))
      {
         ((ReplicationServer) target).withSharedSpaces((SharedSpace) value);
         return true;
      }
      
      if ((ReplicationServer.PROPERTY_SHAREDSPACES + REMOVE).equalsIgnoreCase(attrName))
      {
         ((ReplicationServer) target).withoutSharedSpaces((SharedSpace) value);
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
      ((ReplicationServer) entity).removeYou();
   }
}
