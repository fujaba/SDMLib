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

import org.sdmlib.test.examples.groupaccount.model.Party;
import org.sdmlib.test.examples.groupaccount.model.Person;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.AggregatedEntityCreator;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class PartyCreator implements AggregatedEntityCreator
{
   public static final PartyCreator it = new PartyCreator();
   
   private final String[] properties = new String[]
   {
      Party.PROPERTY_PARTYNAME,
      Party.PROPERTY_SHARE,
      Party.PROPERTY_TOTAL,
      Party.PROPERTY_GUESTS,
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
      return new Party();
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

      if (Party.PROPERTY_PARTYNAME.equalsIgnoreCase(attribute))
      {
         return ((Party) target).getPartyName();
      }

      if (Party.PROPERTY_SHARE.equalsIgnoreCase(attribute))
      {
         return ((Party) target).getShare();
      }

      if (Party.PROPERTY_TOTAL.equalsIgnoreCase(attribute))
      {
         return ((Party) target).getTotal();
      }

      if (Party.PROPERTY_GUESTS.equalsIgnoreCase(attribute))
      {
         return ((Party) target).getGuests();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Party.PROPERTY_TOTAL.equalsIgnoreCase(attrName))
      {
         ((Party) target).setTotal(Double.parseDouble(value.toString()));
         return true;
      }

      if (Party.PROPERTY_SHARE.equalsIgnoreCase(attrName))
      {
         ((Party) target).setShare(Double.parseDouble(value.toString()));
         return true;
      }

      if (Party.PROPERTY_PARTYNAME.equalsIgnoreCase(attrName))
      {
         ((Party) target).setPartyName((String) value);
         return true;
      }

      if(SendableEntityCreator.REMOVE_YOU.equals(type)) {
           ((Party)target).removeYou();
           return true;
      }
      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Party.PROPERTY_GUESTS.equalsIgnoreCase(attrName))
      {
         ((Party) target).withGuests((Person) value);
         return true;
      }
      
      if ((Party.PROPERTY_GUESTS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Party) target).withoutGuests((Person) value);
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
      ((Party) entity).removeYou();
   }
}
