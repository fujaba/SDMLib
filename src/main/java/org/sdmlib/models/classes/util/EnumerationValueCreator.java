/*
   Copyright (c) 2014 christian 
   
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
   
package org.sdmlib.models.classes.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.models.classes.EnumerationValue;
import org.sdmlib.models.classes.SDMLibClass;
import org.sdmlib.models.classes.Enumeration;

public class EnumerationValueCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      EnumerationValue.PROPERTY_VALUENAME,
      SDMLibClass.PROPERTY_NAME,
      EnumerationValue.PROPERTY_ENUMERATION,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new EnumerationValue();
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

      if (EnumerationValue.PROPERTY_VALUENAME.equalsIgnoreCase(attribute))
      {
         return ((EnumerationValue) target).getValueName();
      }

      if (SDMLibClass.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((SDMLibClass) target).getName();
      }

      if (EnumerationValue.PROPERTY_ENUMERATION.equalsIgnoreCase(attribute))
      {
         return ((EnumerationValue) target).getEnumeration();
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

      if (EnumerationValue.PROPERTY_VALUENAME.equalsIgnoreCase(attrName))
      {
         ((EnumerationValue) target).withValueName((String) value);
         return true;
      }

      if (SDMLibClass.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((SDMLibClass) target).withName((String) value);
         return true;
      }

      if (EnumerationValue.PROPERTY_ENUMERATION.equalsIgnoreCase(attrName))
      {
         ((EnumerationValue) target).setEnumeration((Enumeration) value);
         return true;
      }
      
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return org.sdmlib.models.classes.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((EnumerationValue) entity).removeYou();
   }
}
