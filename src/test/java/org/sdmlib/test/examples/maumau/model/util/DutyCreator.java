/*
   Copyright (c) 2015 Stefan
   
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
   
package org.sdmlib.test.examples.maumau.model.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.maumau.model.Duty;
import org.sdmlib.test.examples.maumau.model.DutyType;
import org.sdmlib.test.examples.maumau.model.Player;

public class DutyCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Duty.PROPERTY_TYPE,
      Duty.PROPERTY_NUMBER,
      Duty.PROPERTY_PLAYER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Duty();
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

      if (Duty.PROPERTY_TYPE.equalsIgnoreCase(attribute))
      {
         return ((Duty) target).getType();
      }

      if (Duty.PROPERTY_NUMBER.equalsIgnoreCase(attribute))
      {
         return ((Duty) target).getNumber();
      }

      if (Duty.PROPERTY_PLAYER.equalsIgnoreCase(attribute))
      {
         return ((Duty) target).getPlayer();
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

      if (Duty.PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         ((Duty) target).withType(DutyType.valueOf((String) value));
         return true;
      }

      if (Duty.PROPERTY_NUMBER.equalsIgnoreCase(attrName))
      {
         ((Duty) target).withNumber(Integer.parseInt(value.toString()));
         return true;
      }

      if (Duty.PROPERTY_PLAYER.equalsIgnoreCase(attrName))
      {
         ((Duty) target).setPlayer((Player) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Duty) entity).removeYou();
   }
}
