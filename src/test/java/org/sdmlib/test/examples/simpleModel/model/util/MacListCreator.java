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
   
package org.sdmlib.test.examples.simpleModel.model.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.test.examples.simpleModel.model.MacList;

import de.uniks.networkparser.json.JsonIdMap;

public class MacListCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      MacList.PROPERTY_NAME,
      MacList.PROPERTY_SERIALVERSIONUID,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new MacList();
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

      if (MacList.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((MacList) target).getName();
      }

      if (MacList.PROPERTY_SERIALVERSIONUID.equalsIgnoreCase(attribute))
      {
         return ((MacList) target).getSerialVersionUID();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (MacList.PROPERTY_SERIALVERSIONUID.equalsIgnoreCase(attrName))
      {
         ((MacList) target).withSerialVersionUID(Long.parseLong(value.toString()));
         return true;
      }

      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (MacList.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((MacList) target).withName((String) value);
         return true;
      }
      
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.simpleModel.model.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((MacList) entity).removeYou();
   }
}
