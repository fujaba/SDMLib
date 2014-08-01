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
import org.sdmlib.models.classes.Enumeration;
import org.sdmlib.models.classes.SDMLibClass;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.EnumerationValue;
import org.sdmlib.models.classes.ClassModel;

public class EnumerationCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      SDMLibClass.PROPERTY_NAME,
      Enumeration.PROPERTY_METHODS,
      Enumeration.PROPERTY_VALUES,
      Enumeration.PROPERTY_CLASSMODEL,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Enumeration();
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

      if (SDMLibClass.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((SDMLibClass) target).getName();
      }

      if (Enumeration.PROPERTY_METHODS.equalsIgnoreCase(attribute))
      {
         return ((Enumeration) target).getMethods();
      }

      if (Enumeration.PROPERTY_VALUES.equalsIgnoreCase(attribute))
      {
         return ((Enumeration) target).getValues();
      }

      if (Enumeration.PROPERTY_CLASSMODEL.equalsIgnoreCase(attribute))
      {
         return ((Enumeration) target).getClassModel();
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

      if (SDMLibClass.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((SDMLibClass) target).withName((String) value);
         return true;
      }

      if (Enumeration.PROPERTY_METHODS.equalsIgnoreCase(attrName))
      {
         ((Enumeration) target).withMethods((Method) value);
         return true;
      }
      
      if ((Enumeration.PROPERTY_METHODS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Enumeration) target).withoutMethods((Method) value);
         return true;
      }

      if (Enumeration.PROPERTY_VALUES.equalsIgnoreCase(attrName))
      {
         ((Enumeration) target).withValues((EnumerationValue) value);
         return true;
      }
      
      if ((Enumeration.PROPERTY_VALUES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Enumeration) target).withoutValues((EnumerationValue) value);
         return true;
      }

      if (Enumeration.PROPERTY_CLASSMODEL.equalsIgnoreCase(attrName))
      {
         ((Enumeration) target).setClassModel((ClassModel) value);
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
      ((Enumeration) entity).removeYou();
   }
}
