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
import org.sdmlib.models.tables.Row;
import de.uniks.networkparser.IdMap;
import org.sdmlib.models.tables.Table;
import org.sdmlib.models.tables.Cell;

public class RowCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Row.PROPERTY_NUMBER,
      Row.PROPERTY_TABLE,
      Row.PROPERTY_CELLS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Row();
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

      if (Row.PROPERTY_NUMBER.equalsIgnoreCase(attribute))
      {
         return ((Row) target).getNumber();
      }

      if (Row.PROPERTY_TABLE.equalsIgnoreCase(attribute))
      {
         return ((Row) target).getTable();
      }

      if (Row.PROPERTY_CELLS.equalsIgnoreCase(attribute))
      {
         return ((Row) target).getCells();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Row.PROPERTY_NUMBER.equalsIgnoreCase(attrName))
      {
         ((Row) target).setNumber(Integer.parseInt(value.toString()));
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Row.PROPERTY_TABLE.equalsIgnoreCase(attrName))
      {
         ((Row) target).setTable((Table) value);
         return true;
      }

      if (Row.PROPERTY_CELLS.equalsIgnoreCase(attrName))
      {
         ((Row) target).withCells((Cell) value);
         return true;
      }
      
      if ((Row.PROPERTY_CELLS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Row) target).withoutCells((Cell) value);
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
      ((Row) entity).removeYou();
   }
}
