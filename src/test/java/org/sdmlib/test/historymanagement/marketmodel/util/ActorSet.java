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

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.historymanagement.marketmodel.Actor;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.sdmlib.test.historymanagement.marketmodel.util.BidSet;
import org.sdmlib.test.historymanagement.marketmodel.Bid;
import org.sdmlib.test.historymanagement.marketmodel.util.OfferSet;
import org.sdmlib.test.historymanagement.marketmodel.Offer;

public class ActorSet extends SimpleSet<Actor>
{
	public Class<?> getTypClass() {
		return Actor.class;
	}

   public ActorSet()
   {
      // empty
   }

   public ActorSet(Actor... objects)
   {
      for (Actor obj : objects)
      {
         this.add(obj);
      }
   }

   public ActorSet(Collection<Actor> objects)
   {
      this.addAll(objects);
   }

   public static final ActorSet EMPTY_SET = new ActorSet().withFlag(ActorSet.READONLY);


   public ActorPO createActorPO()
   {
      return new ActorPO(this.toArray(new Actor[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.historymanagement.marketmodel.Actor";
   }


   @Override
   public ActorSet getNewList(boolean keyValue)
   {
      return new ActorSet();
   }


   public ActorSet filter(Condition<Actor> condition) {
      ActorSet filterList = new ActorSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public ActorSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Actor>)value);
      }
      else if (value != null)
      {
         this.add((Actor) value);
      }
      
      return this;
   }
   
   public ActorSet without(Actor value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Actor objects and collect a list of the actorName attribute values. 
    * 
    * @return List of String objects reachable via actorName attribute
    */
   public ObjectSet getActorName()
   {
      ObjectSet result = new ObjectSet();
      
      for (Actor obj : this)
      {
         result.add(obj.getActorName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Actor objects and collect those Actor objects where the actorName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Actor objects that match the parameter
    */
   public ActorSet createActorNameCondition(String value)
   {
      ActorSet result = new ActorSet();
      
      for (Actor obj : this)
      {
         if (value.equals(obj.getActorName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Actor objects and collect those Actor objects where the actorName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Actor objects that match the parameter
    */
   public ActorSet createActorNameCondition(String lower, String upper)
   {
      ActorSet result = new ActorSet();
      
      for (Actor obj : this)
      {
         if (lower.compareTo(obj.getActorName()) <= 0 && obj.getActorName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Actor objects and assign value to the actorName attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Actor objects now with new attribute values.
    */
   public ActorSet withActorName(String value)
   {
      for (Actor obj : this)
      {
         obj.setActorName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Actor objects and collect a set of the Bid objects reached via bids. 
    * 
    * @return Set of Bid objects reachable via bids
    */
   public BidSet getBids()
   {
      BidSet result = new BidSet();
      
      for (Actor obj : this)
      {
         result.with(obj.getBids());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Actor objects and collect all contained objects with reference bids pointing to the object passed as parameter. 
    * 
    * @param value The object required as bids neighbor of the collected results. 
    * 
    * @return Set of Bid objects referring to value via bids
    */
   public ActorSet filterBids(Object value)
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
      
      ActorSet answer = new ActorSet();
      
      for (Actor obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getBids()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Actor object passed as parameter to the Bids attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Bids attributes.
    */
   public ActorSet withBids(Bid value)
   {
      for (Actor obj : this)
      {
         obj.withBids(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Actor object passed as parameter from the Bids attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public ActorSet withoutBids(Bid value)
   {
      for (Actor obj : this)
      {
         obj.withoutBids(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Actor objects and collect a set of the Offer objects reached via offers. 
    * 
    * @return Set of Offer objects reachable via offers
    */
   public OfferSet getOffers()
   {
      OfferSet result = new OfferSet();
      
      for (Actor obj : this)
      {
         result.with(obj.getOffers());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Actor objects and collect all contained objects with reference offers pointing to the object passed as parameter. 
    * 
    * @param value The object required as offers neighbor of the collected results. 
    * 
    * @return Set of Offer objects referring to value via offers
    */
   public ActorSet filterOffers(Object value)
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
      
      ActorSet answer = new ActorSet();
      
      for (Actor obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getOffers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Actor object passed as parameter to the Offers attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Offers attributes.
    */
   public ActorSet withOffers(Offer value)
   {
      for (Actor obj : this)
      {
         obj.withOffers(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Actor object passed as parameter from the Offers attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public ActorSet withoutOffers(Offer value)
   {
      for (Actor obj : this)
      {
         obj.withoutOffers(value);
      }
      
      return this;
   }

}
