/*
   Copyright (c) 2017 zuendorf
   
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
   
package org.sdmlib.test.historymanagement.marketmodel.util;

import org.sdmlib.test.historymanagement.marketmodel.Actor;
import org.sdmlib.test.historymanagement.marketmodel.Bid;
import org.sdmlib.test.historymanagement.marketmodel.Offer;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class ActorCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Actor.PROPERTY_ACTORNAME,
      Actor.PROPERTY_BIDS,
      Actor.PROPERTY_OFFERS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Actor();
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

      if (Actor.PROPERTY_ACTORNAME.equalsIgnoreCase(attribute))
      {
         return ((Actor) target).getActorName();
      }

      if (Actor.PROPERTY_BIDS.equalsIgnoreCase(attribute))
      {
         return ((Actor) target).getBids();
      }

      if (Actor.PROPERTY_OFFERS.equalsIgnoreCase(attribute))
      {
         return ((Actor) target).getOffers();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Actor.PROPERTY_ACTORNAME.equalsIgnoreCase(attrName))
      {
         ((Actor) target).setActorName((String) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Actor.PROPERTY_BIDS.equalsIgnoreCase(attrName))
      {
         ((Actor) target).withBids((Bid) value);
         return true;
      }
      
      if ((Actor.PROPERTY_BIDS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Actor) target).withoutBids((Bid) value);
         return true;
      }

      if (Actor.PROPERTY_OFFERS.equalsIgnoreCase(attrName))
      {
         ((Actor) target).withOffers((Offer) value);
         return true;
      }
      
      if ((Actor.PROPERTY_OFFERS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Actor) target).withoutOffers((Offer) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.historymanagement.marketmodel.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Actor) entity).removeYou();
   }
}
