/*
   Copyright (c) 2012 zuendorf 

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

import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.SDMLibClass;
import org.sdmlib.models.classes.Value;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class AttributeCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Attribute.PROPERTY_INITIALIZATION,
      Attribute.PROPERTY_CLAZZ,
      Attribute.PROPERTY_TYPE,
      Attribute.PROPERTY_NAME,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }

   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Attribute(null, null);
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

      if (Value.PROPERTY_INITIALIZATION.equalsIgnoreCase(attribute))
      {
         return ((Value) target).getInitialization();
      }

      if (Value.PROPERTY_TYPE.equalsIgnoreCase(attribute))
      {
         return ((Value) target).getType();
      }

      if (SDMLibClass.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((SDMLibClass) target).getName();
      }

      if (Attribute.PROPERTY_CLAZZ.equalsIgnoreCase(attribute))
      {
         return ((Attribute) target).getClazz();
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

      if (Value.PROPERTY_INITIALIZATION.equalsIgnoreCase(attrName))
      {
         ((Value) target).setInitialization((String) value);
         return true;
      }

      if (Value.PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         ((Value) target).setType((org.sdmlib.models.classes.DataType) value);
         return true;
      }

      if (SDMLibClass.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((SDMLibClass) target).withName((String) value);
         return true;
      }

      if (Attribute.PROPERTY_CLAZZ.equalsIgnoreCase(attrName))
      {
         ((Attribute) target).setClazz((Clazz) value);
         return true;
      }
      
      return false;
   }
     
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Attribute) entity).removeYou();
   }
}
