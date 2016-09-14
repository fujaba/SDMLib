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
import org.sdmlib.models.tables.Column;
import de.uniks.networkparser.IdMap;
import org.sdmlib.models.tables.Table;
import org.sdmlib.models.tables.Cell;

public class ColumnCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Column.PROPERTY_NAME,
      Column.PROPERTY_TDCSSCLASS,
      Column.PROPERTY_THCSSCLASS,
      Column.PROPERTY_TABLE,
      Column.PROPERTY_CELLS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Column();
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

      if (Column.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Column) target).getName();
      }

      if (Column.PROPERTY_TDCSSCLASS.equalsIgnoreCase(attribute))
      {
         return ((Column) target).getTdCssClass();
      }

      if (Column.PROPERTY_THCSSCLASS.equalsIgnoreCase(attribute))
      {
         return ((Column) target).getThCssClass();
      }

      if (Column.PROPERTY_TABLE.equalsIgnoreCase(attribute))
      {
         return ((Column) target).getTable();
      }

      if (Column.PROPERTY_CELLS.equalsIgnoreCase(attribute))
      {
         return ((Column) target).getCells();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Column.PROPERTY_THCSSCLASS.equalsIgnoreCase(attrName))
      {
         ((Column) target).setThCssClass((String) value);
         return true;
      }

      if (Column.PROPERTY_TDCSSCLASS.equalsIgnoreCase(attrName))
      {
         ((Column) target).setTdCssClass((String) value);
         return true;
      }

      if (Column.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Column) target).setName((String) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Column.PROPERTY_TABLE.equalsIgnoreCase(attrName))
      {
         ((Column) target).setTable((Table) value);
         return true;
      }

      if (Column.PROPERTY_CELLS.equalsIgnoreCase(attrName))
      {
         ((Column) target).withCells((Cell) value);
         return true;
      }
      
      if ((Column.PROPERTY_CELLS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Column) target).withoutCells((Cell) value);
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
      ((Column) entity).removeYou();
   }
}
