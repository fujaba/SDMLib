/*
   Copyright (c) 2018 zuendorf
   
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
   
package org.sdmlib.test.examples.annotations.model.simple.util;

import org.sdmlib.test.examples.annotations.model.simple.Door;
import org.sdmlib.test.examples.annotations.model.simple.House;
import org.sdmlib.test.examples.annotations.model.simple.Window;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.AggregatedEntityCreator;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class HouseCreator implements AggregatedEntityCreator
{
   public static final HouseCreator it = new HouseCreator();
   
   private final String[] properties = new String[]
   {
      House.PROPERTY_DOORS,
      House.PROPERTY_WINDOWS,
   };
   
   private final String[] upProperties = new String[]
   {
   };
   
   private final String[] downProperties = new String[]
   {
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public String[] getUpProperties()
   {
      return upProperties;
   }
   
   @Override
   public String[] getDownProperties()
   {
      return downProperties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new House();
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

      if (House.PROPERTY_DOORS.equalsIgnoreCase(attribute))
      {
         return ((House) target).getDoors();
      }

      if (House.PROPERTY_WINDOWS.equalsIgnoreCase(attribute))
      {
         return ((House) target).getWindows();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {
           ((House)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (House.PROPERTY_DOORS.equalsIgnoreCase(attrName))
      {
         ((House) target).withDoors((Door) value);
         return true;
      }
      
      if ((House.PROPERTY_DOORS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((House) target).withoutDoors((Door) value);
         return true;
      }

      if (House.PROPERTY_WINDOWS.equalsIgnoreCase(attrName))
      {
         ((House) target).withWindows((Window) value);
         return true;
      }
      
      if ((House.PROPERTY_WINDOWS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((House) target).withoutWindows((Window) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.annotations.model.simple.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((House) entity).removeYou();
   }
}
