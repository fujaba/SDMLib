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

import org.sdmlib.replication.ReplicationRoot;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;

public class ReplicationRootCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ReplicationRoot.PROPERTY_NAME,
      ReplicationRoot.PROPERTY_APPLICATIONOBJECT,
      ReplicationRoot.PROPERTY_KIDS,
      ReplicationRoot.PROPERTY_PARENT,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ReplicationRoot();
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

      if (ReplicationRoot.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((ReplicationRoot) target).getName();
      }

      if (ReplicationRoot.PROPERTY_APPLICATIONOBJECT.equalsIgnoreCase(attribute))
      {
         return ((ReplicationRoot) target).getApplicationObject();
      }

      if (ReplicationRoot.PROPERTY_KIDS.equalsIgnoreCase(attribute))
      {
         return ((ReplicationRoot) target).getKids();
      }

      if (ReplicationRoot.PROPERTY_PARENT.equalsIgnoreCase(attribute))
      {
         return ((ReplicationRoot) target).getParent();
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

      if (ReplicationRoot.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((ReplicationRoot) target).withName((String) value);
         return true;
      }

      if (ReplicationRoot.PROPERTY_APPLICATIONOBJECT.equalsIgnoreCase(attrName))
      {
         ((ReplicationRoot) target).withApplicationObject((Object) value);
         return true;
      }

      if (ReplicationRoot.PROPERTY_KIDS.equalsIgnoreCase(attrName))
      {
         ((ReplicationRoot) target).withKids((ReplicationRoot) value);
         return true;
      }
      
      if ((ReplicationRoot.PROPERTY_KIDS + REMOVE).equalsIgnoreCase(attrName))
      {
         ((ReplicationRoot) target).withoutKids((ReplicationRoot) value);
         return true;
      }

      if (ReplicationRoot.PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         ((ReplicationRoot) target).setParent((ReplicationRoot) value);
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
      ((ReplicationRoot) entity).removeYou();
   }
}
