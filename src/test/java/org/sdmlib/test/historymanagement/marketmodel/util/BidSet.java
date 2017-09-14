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

import org.sdmlib.test.historymanagement.marketmodel.Actor;
import org.sdmlib.test.historymanagement.marketmodel.Bid;
import org.sdmlib.test.historymanagement.marketmodel.Offer;

import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class BidSet extends SimpleSet<Bid>
{
	public Class<?> getTypClass() {
		return Bid.class;
	}

   public BidSet()
   {
      // empty
   }

   public BidSet(Bid... objects)
   {
      for (Bid obj : objects)
      {
         this.add(obj);
      }
   }

   public BidSet(Collection<Bid> objects)
   {
      this.addAll(objects);
   }

   public static final BidSet EMPTY_SET = new BidSet().withFlag(BidSet.READONLY);


   public BidPO createBidPO()
   {
      return new BidPO(this.toArray(new Bid[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.historymanagement.marketmodel.Bid";
   }


   @Override
   public BidSet getNewList(boolean keyValue)
   {
      return new BidSet();
   }


   public BidSet filter(Condition<Bid> condition) {
      BidSet filterList = new BidSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public BidSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Bid>)value);
      }
      else if (value != null)
      {
         this.add((Bid) value);
      }
      
      return this;
   }
   
   public BidSet without(Bid value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Bid objects and collect a list of the amount attribute values. 
    * 
    * @return List of double objects reachable via amount attribute
    */
   public NumberList getAmount()
   {
      NumberList result = new NumberList();
      
      for (Bid obj : this)
      {
         result.add(obj.getAmount());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Bid objects and collect those Bid objects where the amount attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Bid objects that match the parameter
    */
   public BidSet createAmountCondition(double value)
   {
      BidSet result = new BidSet();
      
      for (Bid obj : this)
      {
         if (value == obj.getAmount())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Bid objects and collect those Bid objects where the amount attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Bid objects that match the parameter
    */
   public BidSet createAmountCondition(double lower, double upper)
   {
      BidSet result = new BidSet();
      
      for (Bid obj : this)
      {
         if (lower <= obj.getAmount() && obj.getAmount() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Bid objects and assign value to the amount attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Bid objects now with new attribute values.
    */
   public BidSet withAmount(double value)
   {
      for (Bid obj : this)
      {
         obj.setAmount(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Bid objects and collect a set of the Actor objects reached via bidder. 
    * 
    * @return Set of Actor objects reachable via bidder
    */
   public ActorSet getBidder()
   {
      ActorSet result = new ActorSet();
      
      for (Bid obj : this)
      {
         result.with(obj.getBidder());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Bid objects and collect all contained objects with reference bidder pointing to the object passed as parameter. 
    * 
    * @param value The object required as bidder neighbor of the collected results. 
    * 
    * @return Set of Actor objects referring to value via bidder
    */
   public BidSet filterBidder(Object value)
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
      
      BidSet answer = new BidSet();
      
      for (Bid obj : this)
      {
         if (neighbors.contains(obj.getBidder()) || (neighbors.isEmpty() && obj.getBidder() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Bid object passed as parameter to the Bidder attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Bidder attributes.
    */
   public BidSet withBidder(Actor value)
   {
      for (Bid obj : this)
      {
         obj.withBidder(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Bid objects and collect a set of the Offer objects reached via offer. 
    * 
    * @return Set of Offer objects reachable via offer
    */
   public OfferSet getOffer()
   {
      OfferSet result = new OfferSet();
      
      for (Bid obj : this)
      {
         result.with(obj.getOffer());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Bid objects and collect all contained objects with reference offer pointing to the object passed as parameter. 
    * 
    * @param value The object required as offer neighbor of the collected results. 
    * 
    * @return Set of Offer objects referring to value via offer
    */
   public BidSet filterOffer(Object value)
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
      
      BidSet answer = new BidSet();
      
      for (Bid obj : this)
      {
         if (neighbors.contains(obj.getOffer()) || (neighbors.isEmpty() && obj.getOffer() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Bid object passed as parameter to the Offer attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Offer attributes.
    */
   public BidSet withOffer(Offer value)
   {
      for (Bid obj : this)
      {
         obj.withOffer(value);
      }
      
      return this;
   }

}
