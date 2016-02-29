/*
   Copyright (c) 2016 zuendorf
   
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

import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Parameter;
import org.sdmlib.models.classes.SDMLibClass;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class MethodCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Method.PROPERTY_RETURNTYPE,
      Method.PROPERTY_BODY,
      SDMLibClass.PROPERTY_NAME,
      Method.PROPERTY_PARAMETER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Method();
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

      if (Method.PROPERTY_RETURNTYPE.equalsIgnoreCase(attribute))
      {
         return ((Method) target).getReturnType();
      }

      if (Method.PROPERTY_BODY.equalsIgnoreCase(attribute))
      {
         return ((Method) target).getBody();
      }

      if (SDMLibClass.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((SDMLibClass) target).getName();
      }

      if (Method.PROPERTY_PARAMETER.equalsIgnoreCase(attribute))
      {
         return ((Method) target).getParameter();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (SDMLibClass.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((SDMLibClass) target).withName((String) value);
         return true;
      }

      if (Method.PROPERTY_BODY.equalsIgnoreCase(attrName))
      {
         ((Method) target).withBody((String) value);
         return true;
      }

      if (Method.PROPERTY_RETURNTYPE.equalsIgnoreCase(attrName))
      {
         ((Method) target).withReturnType((DataType) value);
         return true;
      }

      if (IdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Method.PROPERTY_PARAMETER.equalsIgnoreCase(attrName))
      {
         ((Method) target).withParameter((Parameter) value);
         return true;
      }
      
      if ((Method.PROPERTY_PARAMETER + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Method) target).withoutParameter((Parameter) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.models.classes.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Method) entity).removeYou();
   }
}
