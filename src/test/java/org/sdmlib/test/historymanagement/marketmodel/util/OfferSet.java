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

import org.sdmlib.test.historymanagement.marketmodel.Actor;
import org.sdmlib.test.historymanagement.marketmodel.Bid;
import org.sdmlib.test.historymanagement.marketmodel.Market;
import org.sdmlib.test.historymanagement.marketmodel.Offer;

import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.historymanagement.marketmodel.util.BidSet;
import org.sdmlib.test.historymanagement.marketmodel.util.MarketSet;
import org.sdmlib.test.historymanagement.marketmodel.util.ActorSet;

public class OfferSet extends SimpleSet<Offer>
{
	public Class<?> getTypClass() {
		return Offer.class;
	}

   public OfferSet()
   {
      // empty
   }

   public OfferSet(Offer... objects)
   {
      for (Offer obj : objects)
      {
         this.add(obj);
      }
   }

   public OfferSet(Collection<Offer> objects)
   {
      this.addAll(objects);
   }

   public static final OfferSet EMPTY_SET = new OfferSet().withFlag(OfferSet.READONLY);


   public OfferPO createOfferPO()
   {
      return new OfferPO(this.toArray(new Offer[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.historymanagement.marketmodel.Offer";
   }


   @Override
   public OfferSet getNewList(boolean keyValue)
   {
      return new OfferSet();
   }


   public OfferSet filter(Condition<Offer> condition) {
      OfferSet filterList = new OfferSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public OfferSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Offer>)value);
      }
      else if (value != null)
      {
         this.add((Offer) value);
      }
      
      return this;
   }
   
   public OfferSet without(Offer value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Offer objects and collect a list of the description attribute values. 
    * 
    * @return List of String objects reachable via description attribute
    */
   public ObjectSet getDescription()
   {
      ObjectSet result = new ObjectSet();
      
      for (Offer obj : this)
      {
         result.add(obj.getDescription());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Offer objects and collect those Offer objects where the description attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Offer objects that match the parameter
    */
   public OfferSet createDescriptionCondition(String value)
   {
      OfferSet result = new OfferSet();
      
      for (Offer obj : this)
      {
         if (value.equals(obj.getDescription()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Offer objects and collect those Offer objects where the description attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Offer objects that match the parameter
    */
   public OfferSet createDescriptionCondition(String lower, String upper)
   {
      OfferSet result = new OfferSet();
      
      for (Offer obj : this)
      {
         if (lower.compareTo(obj.getDescription()) <= 0 && obj.getDescription().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Offer objects and assign value to the description attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Offer objects now with new attribute values.
    */
   public OfferSet withDescription(String value)
   {
      for (Offer obj : this)
      {
         obj.setDescription(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Offer objects and collect a list of the timeLimit attribute values. 
    * 
    * @return List of String objects reachable via timeLimit attribute
    */
   public ObjectSet getTimeLimit()
   {
      ObjectSet result = new ObjectSet();
      
      for (Offer obj : this)
      {
         result.add(obj.getTimeLimit());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Offer objects and collect those Offer objects where the timeLimit attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Offer objects that match the parameter
    */
   public OfferSet createTimeLimitCondition(String value)
   {
      OfferSet result = new OfferSet();
      
      for (Offer obj : this)
      {
         if (value.equals(obj.getTimeLimit()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Offer objects and collect those Offer objects where the timeLimit attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Offer objects that match the parameter
    */
   public OfferSet createTimeLimitCondition(String lower, String upper)
   {
      OfferSet result = new OfferSet();
      
      for (Offer obj : this)
      {
         if (lower.compareTo(obj.getTimeLimit()) <= 0 && obj.getTimeLimit().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Offer objects and assign value to the timeLimit attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Offer objects now with new attribute values.
    */
   public OfferSet withTimeLimit(String value)
   {
      for (Offer obj : this)
      {
         obj.setTimeLimit(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Offer objects and collect a set of the Bid objects reached via bids. 
    * 
    * @return Set of Bid objects reachable via bids
    */
   public BidSet getBids()
   {
      BidSet result = new BidSet();
      
      for (Offer obj : this)
      {
         result.with(obj.getBids());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Offer objects and collect all contained objects with reference bids pointing to the object passed as parameter. 
    * 
    * @param value The object required as bids neighbor of the collected results. 
    * 
    * @return Set of Bid objects referring to value via bids
    */
   public OfferSet filterBids(Object value)
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
      
      OfferSet answer = new OfferSet();
      
      for (Offer obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getBids()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Offer object passed as parameter to the Bids attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Bids attributes.
    */
   public OfferSet withBids(Bid value)
   {
      for (Offer obj : this)
      {
         obj.withBids(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Offer object passed as parameter from the Bids attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public OfferSet withoutBids(Bid value)
   {
      for (Offer obj : this)
      {
         obj.withoutBids(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Offer objects and collect a set of the Actor objects reached via owner. 
    * 
    * @return Set of Actor objects reachable via owner
    */
   public ActorSet getOwner()
   {
      ActorSet result = new ActorSet();
      
      for (Offer obj : this)
      {
         result.with(obj.getOwner());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Offer objects and collect all contained objects with reference owner pointing to the object passed as parameter. 
    * 
    * @param value The object required as owner neighbor of the collected results. 
    * 
    * @return Set of Actor objects referring to value via owner
    */
   public OfferSet filterOwner(Object value)
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
      
      OfferSet answer = new OfferSet();
      
      for (Offer obj : this)
      {
         if (neighbors.contains(obj.getOwner()) || (neighbors.isEmpty() && obj.getOwner() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Offer object passed as parameter to the Owner attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Owner attributes.
    */
   public OfferSet withOwner(Actor value)
   {
      for (Offer obj : this)
      {
         obj.withOwner(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Offer objects and collect a set of the Market objects reached via market. 
    * 
    * @return Set of Market objects reachable via market
    */
   public MarketSet getMarket()
   {
      MarketSet result = new MarketSet();
      
      for (Offer obj : this)
      {
         result.with(obj.getMarket());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Offer objects and collect all contained objects with reference market pointing to the object passed as parameter. 
    * 
    * @param value The object required as market neighbor of the collected results. 
    * 
    * @return Set of Market objects referring to value via market
    */
   public OfferSet filterMarket(Object value)
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
      
      OfferSet answer = new OfferSet();
      
      for (Offer obj : this)
      {
         if (neighbors.contains(obj.getMarket()) || (neighbors.isEmpty() && obj.getMarket() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Offer object passed as parameter to the Market attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Market attributes.
    */
   public OfferSet withMarket(Market value)
   {
      for (Offer obj : this)
      {
         obj.withMarket(value);
      }
      
      return this;
   }

}
