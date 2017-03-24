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
   
package org.sdmlib.modelspace.util;

import org.sdmlib.modelspace.Task;
import org.sdmlib.modelspace.TaskLane;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.IdMap;

public class TaskCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Task.PROPERTY_LANE,
      Task.PROPERTY_STATE,
      Task.PROPERTY_SPACENAME,
      Task.PROPERTY_FILENAME,
      Task.PROPERTY_LASTMODIFIED,
      Task.PROPERTY_FILETARGETCLOUD,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Task();
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

      if (Task.PROPERTY_LANE.equalsIgnoreCase(attribute))
      {
         return ((Task) target).getLane();
      }

      if (Task.PROPERTY_STATE.equalsIgnoreCase(attribute))
      {
         return ((Task) target).getState();
      }

      if (Task.PROPERTY_SPACENAME.equalsIgnoreCase(attribute))
      {
         return ((Task) target).getSpaceName();
      }

      if (Task.PROPERTY_FILENAME.equalsIgnoreCase(attribute))
      {
         return ((Task) target).getFileName();
      }

      if (Task.PROPERTY_LASTMODIFIED.equalsIgnoreCase(attribute))
      {
         return ((Task) target).getLastModified();
      }

      if (Task.PROPERTY_FILETARGETCLOUD.equalsIgnoreCase(attribute))
      {
         return ((Task) target).getFileTargetCloud();
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

      if (Task.PROPERTY_LANE.equalsIgnoreCase(attrName))
      {
         ((Task) target).setLane((TaskLane) value);
         return true;
      }

      if (Task.PROPERTY_STATE.equalsIgnoreCase(attrName))
      {
         ((Task) target).withState((String) value);
         return true;
      }

      if (Task.PROPERTY_SPACENAME.equalsIgnoreCase(attrName))
      {
         ((Task) target).withSpaceName((String) value);
         return true;
      }

      if (Task.PROPERTY_FILENAME.equalsIgnoreCase(attrName))
      {
         ((Task) target).withFileName((String) value);
         return true;
      }

      if (Task.PROPERTY_LASTMODIFIED.equalsIgnoreCase(attrName))
      {
         ((Task) target).withLastModified(Long.parseLong(value.toString()));
         return true;
      }

      if (Task.PROPERTY_FILETARGETCLOUD.equalsIgnoreCase(attrName))
      {
         ((Task) target).setFileTargetCloud((TaskLane) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.modelspace.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Task) entity).removeYou();
   }
}
