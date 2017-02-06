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

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.sdmlib.test.historymanagement.marketmodel.Market;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.historymanagement.marketmodel.Offer;

public class MarketCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Market.PROPERTY_MARKETTIME,
      Market.PROPERTY_OFFERS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Market();
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

      if (Market.PROPERTY_MARKETTIME.equalsIgnoreCase(attribute))
      {
         return ((Market) target).getMarketTime();
      }

      if (Market.PROPERTY_OFFERS.equalsIgnoreCase(attribute))
      {
         return ((Market) target).getOffers();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Market.PROPERTY_MARKETTIME.equalsIgnoreCase(attrName))
      {
         ((Market) target).setMarketTime((String) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Market.PROPERTY_OFFERS.equalsIgnoreCase(attrName))
      {
         ((Market) target).withOffers((Offer) value);
         return true;
      }
      
      if ((Market.PROPERTY_OFFERS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Market) target).withoutOffers((Offer) value);
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
      ((Market) entity).removeYou();
   }
}
