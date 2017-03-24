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

import org.sdmlib.replication.SeppelSpace;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;

public class SeppelSpaceCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      SeppelSpace.PROPERTY_SPACEID,
      SeppelSpace.PROPERTY_HISTORY,
      SeppelSpace.PROPERTY_LASTCHANGEID,
      SeppelSpace.PROPERTY_JAVAFXAPPLICATION,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new SeppelSpace();
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

      if (SeppelSpace.PROPERTY_SPACEID.equalsIgnoreCase(attribute))
      {
         return ((SeppelSpace) target).getSpaceId();
      }

      if (SeppelSpace.PROPERTY_HISTORY.equalsIgnoreCase(attribute))
      {
         return ((SeppelSpace) target).getHistory();
      }

      if (SeppelSpace.PROPERTY_LASTCHANGEID.equalsIgnoreCase(attribute))
      {
         return ((SeppelSpace) target).getLastChangeId();
      }

      if (SeppelSpace.PROPERTY_JAVAFXAPPLICATION.equalsIgnoreCase(attribute))
      {
         return ((SeppelSpace) target).isJavaFXApplication();
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

      if (SeppelSpace.PROPERTY_SPACEID.equalsIgnoreCase(attrName))
      {
         ((SeppelSpace) target).withSpaceId((String) value);
         return true;
      }

      if (SeppelSpace.PROPERTY_LASTCHANGEID.equalsIgnoreCase(attrName))
      {
         ((SeppelSpace) target).withLastChangeId(Long.parseLong(value.toString()));
         return true;
      }

      if (SeppelSpace.PROPERTY_JAVAFXAPPLICATION.equalsIgnoreCase(attrName))
      {
         ((SeppelSpace) target).withJavaFXApplication((Boolean) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.replication.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((SeppelSpace) entity).removeYou();
   }
}
