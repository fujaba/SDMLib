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
   
package org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Bank;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Boat;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Cargo;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.River;

import de.uniks.networkparser.IdMap;

public class BankCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Bank.PROPERTY_NAME,
      Bank.PROPERTY_AGE,
      Bank.PROPERTY_BOAT,
      Bank.PROPERTY_RIVER,
      Bank.PROPERTY_CARGOS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Bank();
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

      if (Bank.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Bank) target).getName();
      }

      if (Bank.PROPERTY_AGE.equalsIgnoreCase(attribute))
      {
         return ((Bank) target).getAge();
      }

      if (Bank.PROPERTY_BOAT.equalsIgnoreCase(attribute))
      {
         return ((Bank) target).getBoat();
      }

      if (Bank.PROPERTY_RIVER.equalsIgnoreCase(attribute))
      {
         return ((Bank) target).getRiver();
      }

      if (Bank.PROPERTY_CARGOS.equalsIgnoreCase(attribute))
      {
         return ((Bank) target).getCargos();
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

      if (Bank.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Bank) target).withName((String) value);
         return true;
      }

      if (Bank.PROPERTY_AGE.equalsIgnoreCase(attrName))
      {
         ((Bank) target).withAge(Integer.parseInt(value.toString()));
         return true;
      }

      if (Bank.PROPERTY_BOAT.equalsIgnoreCase(attrName))
      {
         ((Bank) target).setBoat((Boat) value);
         return true;
      }

      if (Bank.PROPERTY_RIVER.equalsIgnoreCase(attrName))
      {
         ((Bank) target).setRiver((River) value);
         return true;
      }

      if (Bank.PROPERTY_CARGOS.equalsIgnoreCase(attrName))
      {
         ((Bank) target).withCargos((Cargo) value);
         return true;
      }
      
      if ((Bank.PROPERTY_CARGOS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Bank) target).withoutCargos((Cargo) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Bank) entity).removeYou();
   }
}
