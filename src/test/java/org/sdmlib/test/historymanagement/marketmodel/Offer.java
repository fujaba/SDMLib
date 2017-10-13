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
   
package org.sdmlib.test.historymanagement.marketmodel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.test.historymanagement.marketmodel.util.BidSet;

import de.uniks.networkparser.EntityUtil;
import de.uniks.networkparser.interfaces.SendableEntity;
import org.sdmlib.test.historymanagement.marketmodel.Bid;
import org.sdmlib.test.historymanagement.marketmodel.Market;
import org.sdmlib.test.historymanagement.marketmodel.Actor;
   /**
    * 
    * @see <a href='../../../../../../../../src/test/java/org/sdmlib/test/historymanagement/HistoryMarketModel.java'>HistoryMarketModel.java</a>
 */
   public  class Offer implements SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = null;
   
   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(listener);
   	return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(propertyName, listener);
   	return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners.removePropertyChangeListener(listener);
   	}
   	listeners.removePropertyChangeListener(listener);
   	return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(propertyName, listener);
   	}
   	return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      withoutBids(this.getBids().toArray(new Bid[this.getBids().size()]));
      setOwner(null);
      setMarket(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_DESCRIPTION = "description";
   
   private String description;

   public String getDescription()
   {
      return this.description;
   }
   
   public void setDescription(String value)
   {
      if ( ! EntityUtil.stringEquals(this.description, value)) {
      
         String oldValue = this.description;
         this.description = value;
         this.firePropertyChange(PROPERTY_DESCRIPTION, oldValue, value);
      }
   }
   
   public Offer withDescription(String value)
   {
      setDescription(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getDescription());
      result.append(" ").append(this.getTimeLimit());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_TIMELIMIT = "timeLimit";
   
   private String timeLimit;

   public String getTimeLimit()
   {
      return this.timeLimit;
   }
   
   public void setTimeLimit(String value)
   {
      if ( ! EntityUtil.stringEquals(this.timeLimit, value)) {
      
         String oldValue = this.timeLimit;
         this.timeLimit = value;
         this.firePropertyChange(PROPERTY_TIMELIMIT, oldValue, value);
      }
   }
   
   public Offer withTimeLimit(String value)
   {
      setTimeLimit(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Offer ----------------------------------- Bid
    *              offer                   bids
    * </pre>
    */
   
   public static final String PROPERTY_BIDS = "bids";

   private BidSet bids = null;
   
   public BidSet getBids()
   {
      if (this.bids == null)
      {
         return BidSet.EMPTY_SET;
      }
   
      return this.bids;
   }

   public Offer withBids(Bid... value)
   {
      if(value==null){
         return this;
      }
      for (Bid item : value)
      {
         if (item != null)
         {
            if (this.bids == null)
            {
               this.bids = new BidSet();
            }
            
            boolean changed = this.bids.add (item);

            if (changed)
            {
               item.withOffer(this);
               firePropertyChange(PROPERTY_BIDS, null, item);
            }
         }
      }
      return this;
   } 

   public Offer withoutBids(Bid... value)
   {
      for (Bid item : value)
      {
         if ((this.bids != null) && (item != null))
         {
            if (this.bids.remove(item))
            {
               item.setOffer(null);
               firePropertyChange(PROPERTY_BIDS, item, null);
            }
         }
      }
      return this;
   }

   public Bid createBids()
   {
      Bid value = new Bid();
      withBids(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Offer ----------------------------------- Actor
    *              offers                   owner
    * </pre>
    */
   
   public static final String PROPERTY_OWNER = "owner";

   private Actor owner = null;

   public Actor getOwner()
   {
      return this.owner;
   }

   public boolean setOwner(Actor value)
   {
      boolean changed = false;
      
      if (this.owner != value)
      {
         Actor oldValue = this.owner;
         
         if (this.owner != null)
         {
            this.owner = null;
            oldValue.withoutOffers(this);
         }
         
         this.owner = value;
         
         if (value != null)
         {
            value.withOffers(this);
         }
         
         firePropertyChange(PROPERTY_OWNER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Offer withOwner(Actor value)
   {
      setOwner(value);
      return this;
   } 

   public Actor createOwner()
   {
      Actor value = new Actor();
      withOwner(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Offer ----------------------------------- Market
    *              offers                   market
    * </pre>
    */
   
   public static final String PROPERTY_MARKET = "market";

   private Market market = null;

   public Market getMarket()
   {
      return this.market;
   }

   public boolean setMarket(Market value)
   {
      boolean changed = false;
      
      if (this.market != value)
      {
         Market oldValue = this.market;
         
         if (this.market != null)
         {
            this.market = null;
            oldValue.withoutOffers(this);
         }
         
         this.market = value;
         
         if (value != null)
         {
            value.withOffers(this);
         }
         
         firePropertyChange(PROPERTY_MARKET, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Offer withMarket(Market value)
   {
      setMarket(value);
      return this;
   } 

   public Market createMarket()
   {
      Market value = new Market();
      withMarket(value);
      return value;
   } 
}
