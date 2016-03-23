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
import org.sdmlib.replication.ReplicationChannel;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;

public class SharedSpaceCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      SharedSpace.PROPERTY_SOCKET,
      SharedSpace.PROPERTY_TARGETNODEID,
      SharedSpace.PROPERTY_NODE,
      SharedSpace.PROPERTY_CHANNELS,
      SharedSpace.PROPERTY_SPACEID,
      SharedSpace.PROPERTY_HISTORY,
      SharedSpace.PROPERTY_LASTCHANGEID,
      SharedSpace.PROPERTY_NODEID,
      SharedSpace.PROPERTY_JAVAFXAPPLICATION,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new SharedSpace();
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

      if (SharedSpace.PROPERTY_SOCKET.equalsIgnoreCase(attribute))
      {
         return ((SharedSpace) target).getSocket();
      }

      if (SharedSpace.PROPERTY_TARGETNODEID.equalsIgnoreCase(attribute))
      {
         return ((SharedSpace) target).getTargetNodeId();
      }

      if (SharedSpace.PROPERTY_NODE.equalsIgnoreCase(attribute))
      {
         return ((SharedSpace) target).getNode();
      }

      if (SharedSpace.PROPERTY_CHANNELS.equalsIgnoreCase(attribute))
      {
         return ((SharedSpace) target).getChannels();
      }

      if (SharedSpace.PROPERTY_SPACEID.equalsIgnoreCase(attribute))
      {
         return ((SharedSpace) target).getSpaceId();
      }

      if (SharedSpace.PROPERTY_HISTORY.equalsIgnoreCase(attribute))
      {
         return ((SharedSpace) target).getHistory();
      }

      if (SharedSpace.PROPERTY_LASTCHANGEID.equalsIgnoreCase(attribute))
      {
         return ((SharedSpace) target).getLastChangeId();
      }

      if (SharedSpace.PROPERTY_NODEID.equalsIgnoreCase(attribute))
      {
         return ((SharedSpace) target).getNodeId();
      }

      if (SharedSpace.PROPERTY_JAVAFXAPPLICATION.equalsIgnoreCase(attribute))
      {
         return ((SharedSpace) target).isJavaFXApplication();
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

      if (SharedSpace.PROPERTY_SOCKET.equalsIgnoreCase(attrName))
      {
         ((SharedSpace) target).withSocket((java.net.Socket) value);
         return true;
      }

      if (SharedSpace.PROPERTY_TARGETNODEID.equalsIgnoreCase(attrName))
      {
         ((SharedSpace) target).withTargetNodeId((String) value);
         return true;
      }

      if (SharedSpace.PROPERTY_NODE.equalsIgnoreCase(attrName))
      {
         ((SharedSpace) target).setNode((ReplicationNode) value);
         return true;
      }

      if (SharedSpace.PROPERTY_CHANNELS.equalsIgnoreCase(attrName))
      {
         ((SharedSpace) target).withChannels((ReplicationChannel) value);
         return true;
      }
      
      if ((SharedSpace.PROPERTY_CHANNELS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((SharedSpace) target).withoutChannels((ReplicationChannel) value);
         return true;
      }

      if (SharedSpace.PROPERTY_SPACEID.equalsIgnoreCase(attrName))
      {
         ((SharedSpace) target).withSpaceId((String) value);
         return true;
      }

      if (SharedSpace.PROPERTY_HISTORY.equalsIgnoreCase(attrName))
      {
         ((SharedSpace) target).withHistory((ChangeHistory) value);
         return true;
      }

      if (SharedSpace.PROPERTY_LASTCHANGEID.equalsIgnoreCase(attrName))
      {
         ((SharedSpace) target).withLastChangeId(Long.parseLong(value.toString()));
         return true;
      }

      if (SharedSpace.PROPERTY_NODEID.equalsIgnoreCase(attrName))
      {
         ((SharedSpace) target).withNodeId((String) value);
         return true;
      }

      if (SharedSpace.PROPERTY_JAVAFXAPPLICATION.equalsIgnoreCase(attrName))
      {
         ((SharedSpace) target).withJavaFXApplication((Boolean) value);
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
      ((SharedSpace) entity).removeYou();
   }
}
