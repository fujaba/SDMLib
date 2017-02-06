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

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.test.historymanagement.marketmodel.Actor;
import org.sdmlib.test.historymanagement.marketmodel.Offer;
   /**
    * 
    * @see <a href='../../../../../../../../src/test/java/org/sdmlib/test/historymanagement/HistoryMarketModel.java'>HistoryMarketModel.java</a>
 */
   public  class Bid implements SendableEntity
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
      setBidder(null);
      setOffer(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_AMOUNT = "amount";
   
   private double amount;

   public double getAmount()
   {
      return this.amount;
   }
   
   public void setAmount(double value)
   {
      if (this.amount != value) {
      
         double oldValue = this.amount;
         this.amount = value;
         this.firePropertyChange(PROPERTY_AMOUNT, oldValue, value);
      }
   }
   
   public Bid withAmount(double value)
   {
      setAmount(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getAmount());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              many                       one
    * Bid ----------------------------------- Actor
    *              bids                   bidder
    * </pre>
    */
   
   public static final String PROPERTY_BIDDER = "bidder";

   private Actor bidder = null;

   public Actor getBidder()
   {
      return this.bidder;
   }

   public boolean setBidder(Actor value)
   {
      boolean changed = false;
      
      if (this.bidder != value)
      {
         Actor oldValue = this.bidder;
         
         if (this.bidder != null)
         {
            this.bidder = null;
            oldValue.withoutBids(this);
         }
         
         this.bidder = value;
         
         if (value != null)
         {
            value.withBids(this);
         }
         
         firePropertyChange(PROPERTY_BIDDER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Bid withBidder(Actor value)
   {
      setBidder(value);
      return this;
   } 

   public Actor createBidder()
   {
      Actor value = new Actor();
      withBidder(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Bid ----------------------------------- Offer
    *              bids                   offer
    * </pre>
    */
   
   public static final String PROPERTY_OFFER = "offer";

   private Offer offer = null;

   public Offer getOffer()
   {
      return this.offer;
   }

   public boolean setOffer(Offer value)
   {
      boolean changed = false;
      
      if (this.offer != value)
      {
         Offer oldValue = this.offer;
         
         if (this.offer != null)
         {
            this.offer = null;
            oldValue.withoutBids(this);
         }
         
         this.offer = value;
         
         if (value != null)
         {
            value.withBids(this);
         }
         
         firePropertyChange(PROPERTY_OFFER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Bid withOffer(Offer value)
   {
      setOffer(value);
      return this;
   } 

   public Offer createOffer()
   {
      Offer value = new Offer();
      withOffer(value);
      return value;
   } 
}
