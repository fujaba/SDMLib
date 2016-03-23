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
   
package de.kassel.test.roombook.util;

import org.sdmlib.serialization.EntityFactory;

import de.kassel.test.roombook.Building;
import de.kassel.test.roombook.Floor;
import de.uniks.networkparser.IdMap;

public class FloorCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Floor.PROPERTY_LEVEL,
      Floor.PROPERTY_NAME,
      Floor.PROPERTY_GUEST,
      Floor.PROPERTY_BUILDINGS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Floor();
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

      if (Floor.PROPERTY_LEVEL.equalsIgnoreCase(attribute))
      {
         return ((Floor) target).getLevel();
      }

      if (Floor.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Floor) target).getName();
      }

      if (Floor.PROPERTY_GUEST.equalsIgnoreCase(attribute))
      {
         return ((Floor) target).getGuest();
      }

      if (Floor.PROPERTY_BUILDINGS.equalsIgnoreCase(attribute))
      {
         return ((Floor) target).getBuildings();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (IdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Floor.PROPERTY_LEVEL.equalsIgnoreCase(attrName))
      {
         ((Floor) target).setLevel(Integer.parseInt(value.toString()));
         return true;
      }

      if (Floor.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Floor) target).setName((String) value);
         return true;
      }

      if (Floor.PROPERTY_GUEST.equalsIgnoreCase(attrName))
      {
         ((Floor) target).setGuest((String) value);
         return true;
      }

      if (Floor.PROPERTY_BUILDINGS.equalsIgnoreCase(attrName))
      {
         ((Floor) target).setBuildings((Building) value);
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
      ((Floor) entity).removeYou();
   }
}
