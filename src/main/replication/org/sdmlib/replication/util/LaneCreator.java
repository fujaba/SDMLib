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
   
package org.sdmlib.replication.util;

import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.RemoteTaskBoard;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;

public class LaneCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Lane.PROPERTY_NAME,
      Lane.PROPERTY_BOARD,
      Lane.PROPERTY_TASKS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Lane();
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

      if (Lane.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Lane) target).getName();
      }

      if (Lane.PROPERTY_BOARD.equalsIgnoreCase(attribute))
      {
         return ((Lane) target).getBoard();
      }

      if (Lane.PROPERTY_TASKS.equalsIgnoreCase(attribute))
      {
         return ((Lane) target).getTasks();
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

      if (Lane.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Lane) target).withName((String) value);
         return true;
      }

      if (Lane.PROPERTY_BOARD.equalsIgnoreCase(attrName))
      {
         ((Lane) target).setBoard((RemoteTaskBoard) value);
         return true;
      }

      if (Lane.PROPERTY_TASKS.equalsIgnoreCase(attrName))
      {
         ((Lane) target).withTasks((BoardTask) value);
         return true;
      }
      
      if ((Lane.PROPERTY_TASKS + REMOVE).equalsIgnoreCase(attrName))
      {
         ((Lane) target).withoutTasks((BoardTask) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Lane) entity).removeYou();
   }
}
