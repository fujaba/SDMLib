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
   
package org.sdmlib.test.examples.groupaccount.model.util;

import org.sdmlib.test.examples.groupaccount.model.Item;
import org.sdmlib.test.examples.groupaccount.model.Party;
import org.sdmlib.test.examples.groupaccount.model.Person;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.AggregatedEntityCreator;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class PersonCreator implements AggregatedEntityCreator
{
   public static final PersonCreator it = new PersonCreator();
   
   private final String[] properties = new String[]
   {
      Person.PROPERTY_NAME,
      Person.PROPERTY_SALDO,
      Person.PROPERTY_TOTAL,
      Person.PROPERTY_PARTY,
      Person.PROPERTY_ITEMS,
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
      return new Person();
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

      if (Person.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Person) target).getName();
      }

      if (Person.PROPERTY_SALDO.equalsIgnoreCase(attribute))
      {
         return ((Person) target).getSaldo();
      }

      if (Person.PROPERTY_TOTAL.equalsIgnoreCase(attribute))
      {
         return ((Person) target).getTotal();
      }

      if (Person.PROPERTY_PARTY.equalsIgnoreCase(attribute))
      {
         return ((Person) target).getParty();
      }

      if (Person.PROPERTY_ITEMS.equalsIgnoreCase(attribute))
      {
         return ((Person) target).getItems();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Person.PROPERTY_TOTAL.equalsIgnoreCase(attrName))
      {
         ((Person) target).setTotal(Double.parseDouble(value.toString()));
         return true;
      }

      if (Person.PROPERTY_SALDO.equalsIgnoreCase(attrName))
      {
         ((Person) target).setSaldo(Double.parseDouble(value.toString()));
         return true;
      }

      if (Person.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Person) target).setName((String) value);
         return true;
      }

      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {
           ((Person)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Person.PROPERTY_PARTY.equalsIgnoreCase(attrName))
      {
         ((Person) target).setParty((Party) value);
         return true;
      }

      if (Person.PROPERTY_ITEMS.equalsIgnoreCase(attrName))
      {
         ((Person) target).withItems((Item) value);
         return true;
      }
      
      if ((Person.PROPERTY_ITEMS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Person) target).withoutItems((Item) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.groupaccount.model.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Person) entity).removeYou();
   }
}
