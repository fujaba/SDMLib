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

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.replication.ChangeEvent;

public class ChangeEventCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ChangeEvent.PROPERTY_OBJECTID,
      ChangeEvent.PROPERTY_OBJECTTYPE,
      ChangeEvent.PROPERTY_PROPERTY,
      ChangeEvent.PROPERTY_NEWVALUE,
      ChangeEvent.PROPERTY_OLDVALUE,
      ChangeEvent.PROPERTY_VALUETYPE,
      ChangeEvent.PROPERTY_CHANGENO,
      ChangeEvent.PROPERTY_SESSIONID,
      ChangeEvent.PROPERTY_PROPERTYKIND,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ChangeEvent();
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

      if (ChangeEvent.PROPERTY_OBJECTID.equalsIgnoreCase(attribute))
      {
         return ((ChangeEvent) target).getObjectId();
      }

      if (ChangeEvent.PROPERTY_OBJECTTYPE.equalsIgnoreCase(attribute))
      {
         return ((ChangeEvent) target).getObjectType();
      }

      if (ChangeEvent.PROPERTY_PROPERTY.equalsIgnoreCase(attribute))
      {
         return ((ChangeEvent) target).getProperty();
      }

      if (ChangeEvent.PROPERTY_NEWVALUE.equalsIgnoreCase(attribute))
      {
         return ((ChangeEvent) target).getNewValue();
      }

      if (ChangeEvent.PROPERTY_OLDVALUE.equalsIgnoreCase(attribute))
      {
         return ((ChangeEvent) target).getOldValue();
      }

      if (ChangeEvent.PROPERTY_VALUETYPE.equalsIgnoreCase(attribute))
      {
         return ((ChangeEvent) target).getValueType();
      }

      if (ChangeEvent.PROPERTY_CHANGENO.equalsIgnoreCase(attribute))
      {
         return ((ChangeEvent) target).getChangeNo();
      }

      if (ChangeEvent.PROPERTY_SESSIONID.equalsIgnoreCase(attribute))
      {
         return ((ChangeEvent) target).getSessionId();
      }

      if (ChangeEvent.PROPERTY_PROPERTYKIND.equalsIgnoreCase(attribute))
      {
         return ((ChangeEvent) target).getPropertyKind();
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

      if (ChangeEvent.PROPERTY_OBJECTID.equalsIgnoreCase(attrName))
      {
         ((ChangeEvent) target).withObjectId((String) value);
         return true;
      }

      if (ChangeEvent.PROPERTY_OBJECTTYPE.equalsIgnoreCase(attrName))
      {
         ((ChangeEvent) target).withObjectType((String) value);
         return true;
      }

      if (ChangeEvent.PROPERTY_PROPERTY.equalsIgnoreCase(attrName))
      {
         ((ChangeEvent) target).withProperty((String) value);
         return true;
      }

      if (ChangeEvent.PROPERTY_NEWVALUE.equalsIgnoreCase(attrName))
      {
         ((ChangeEvent) target).withNewValue((String) value);
         return true;
      }

      if (ChangeEvent.PROPERTY_OLDVALUE.equalsIgnoreCase(attrName))
      {
         ((ChangeEvent) target).withOldValue((String) value);
         return true;
      }

      if (ChangeEvent.PROPERTY_VALUETYPE.equalsIgnoreCase(attrName))
      {
         ((ChangeEvent) target).withValueType((String) value);
         return true;
      }

      if (ChangeEvent.PROPERTY_CHANGENO.equalsIgnoreCase(attrName))
      {
         ((ChangeEvent) target).withChangeNo((String) value);
         return true;
      }

      if (ChangeEvent.PROPERTY_SESSIONID.equalsIgnoreCase(attrName))
      {
         ((ChangeEvent) target).withSessionId((String) value);
         return true;
      }

      if (ChangeEvent.PROPERTY_PROPERTYKIND.equalsIgnoreCase(attrName))
      {
         ((ChangeEvent) target).withPropertyKind((String) value);
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
      ((ChangeEvent) entity).removeYou();
   }
}
