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

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.test.historymanagement.marketmodel.Market;
import org.sdmlib.test.historymanagement.marketmodel.Offer;

import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class MarketSet extends SimpleSet<Market>
{
	public Class<?> getTypClass() {
		return Market.class;
	}

   public MarketSet()
   {
      // empty
   }

   public MarketSet(Market... objects)
   {
      for (Market obj : objects)
      {
         this.add(obj);
      }
   }

   public MarketSet(Collection<Market> objects)
   {
      this.addAll(objects);
   }

   public static final MarketSet EMPTY_SET = new MarketSet().withFlag(MarketSet.READONLY);


   public MarketPO createMarketPO()
   {
      return new MarketPO(this.toArray(new Market[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.historymanagement.marketmodel.Market";
   }


   @Override
   public MarketSet getNewList(boolean keyValue)
   {
      return new MarketSet();
   }


   public MarketSet filter(Condition<Market> condition) {
      MarketSet filterList = new MarketSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public MarketSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Market>)value);
      }
      else if (value != null)
      {
         this.add((Market) value);
      }
      
      return this;
   }
   
   public MarketSet without(Market value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Market objects and collect a list of the marketTime attribute values. 
    * 
    * @return List of String objects reachable via marketTime attribute
    */
   public ObjectSet getMarketTime()
   {
      ObjectSet result = new ObjectSet();
      
      for (Market obj : this)
      {
         result.add(obj.getMarketTime());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Market objects and collect those Market objects where the marketTime attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Market objects that match the parameter
    */
   public MarketSet createMarketTimeCondition(String value)
   {
      MarketSet result = new MarketSet();
      
      for (Market obj : this)
      {
         if (value.equals(obj.getMarketTime()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Market objects and collect those Market objects where the marketTime attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Market objects that match the parameter
    */
   public MarketSet createMarketTimeCondition(String lower, String upper)
   {
      MarketSet result = new MarketSet();
      
      for (Market obj : this)
      {
         if (lower.compareTo(obj.getMarketTime()) <= 0 && obj.getMarketTime().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Market objects and assign value to the marketTime attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Market objects now with new attribute values.
    */
   public MarketSet withMarketTime(String value)
   {
      for (Market obj : this)
      {
         obj.setMarketTime(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Market objects and collect a set of the Offer objects reached via offers. 
    * 
    * @return Set of Offer objects reachable via offers
    */
   public OfferSet getOffers()
   {
      OfferSet result = new OfferSet();
      
      for (Market obj : this)
      {
         result.with(obj.getOffers());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Market objects and collect all contained objects with reference offers pointing to the object passed as parameter. 
    * 
    * @param value The object required as offers neighbor of the collected results. 
    * 
    * @return Set of Offer objects referring to value via offers
    */
   public MarketSet filterOffers(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      MarketSet answer = new MarketSet();
      
      for (Market obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getOffers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Market object passed as parameter to the Offers attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Offers attributes.
    */
   public MarketSet withOffers(Offer value)
   {
      for (Market obj : this)
      {
         obj.withOffers(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Market object passed as parameter from the Offers attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public MarketSet withoutOffers(Offer value)
   {
      for (Market obj : this)
      {
         obj.withoutOffers(value);
      }
      
      return this;
   }

}
