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
   
package org.sdmlib.models.tables.util;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.sdmlib.models.tables.Table;
import de.uniks.networkparser.IdMap;
import org.sdmlib.models.tables.Column;
import org.sdmlib.models.tables.Row;

public class TableCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Table.PROPERTY_NAME,
      Table.PROPERTY_COLUMNS,
      Table.PROPERTY_ROWS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Table();
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

      if (Table.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Table) target).getName();
      }

      if (Table.PROPERTY_COLUMNS.equalsIgnoreCase(attribute))
      {
         return ((Table) target).getColumns();
      }

      if (Table.PROPERTY_ROWS.equalsIgnoreCase(attribute))
      {
         return ((Table) target).getRows();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Table.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Table) target).setName((String) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Table.PROPERTY_COLUMNS.equalsIgnoreCase(attrName))
      {
         ((Table) target).withColumns((Column) value);
         return true;
      }
      
      if ((Table.PROPERTY_COLUMNS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Table) target).withoutColumns((Column) value);
         return true;
      }

      if (Table.PROPERTY_ROWS.equalsIgnoreCase(attrName))
      {
         ((Table) target).withRows((Row) value);
         return true;
      }
      
      if ((Table.PROPERTY_ROWS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Table) target).withoutRows((Row) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.models.tables.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Table) entity).removeYou();
   }
}
