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

import org.sdmlib.test.historymanagement.marketmodel.util.OfferSet;

import de.uniks.networkparser.EntityUtil;
import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../../../src/test/java/org/sdmlib/test/historymanagement/HistoryMarketModel.java'>HistoryMarketModel.java</a>
 * @see org.sdmlib.test.historymanagement.HistoryMarketModel#HistoryMarketModel
 */
   public  class Market implements SendableEntity
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
      withoutOffers(this.getOffers().toArray(new Offer[this.getOffers().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_MARKETTIME = "marketTime";
   
   private String marketTime;

   public String getMarketTime()
   {
      return this.marketTime;
   }
   
   public void setMarketTime(String value)
   {
      if ( ! EntityUtil.stringEquals(this.marketTime, value)) {
      
         String oldValue = this.marketTime;
         this.marketTime = value;
         this.firePropertyChange(PROPERTY_MARKETTIME, oldValue, value);
      }
   }
   
   public Market withMarketTime(String value)
   {
      setMarketTime(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getMarketTime());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       many
    * Market ----------------------------------- Offer
    *              market                   offers
    * </pre>
    */
   
   public static final String PROPERTY_OFFERS = "offers";

   private OfferSet offers = null;
   
   public OfferSet getOffers()
   {
      if (this.offers == null)
      {
         return OfferSet.EMPTY_SET;
      }
   
      return this.offers;
   }

   public Market withOffers(Offer... value)
   {
      if(value==null){
         return this;
      }
      for (Offer item : value)
      {
         if (item != null)
         {
            if (this.offers == null)
            {
               this.offers = new OfferSet();
            }
            
            boolean changed = this.offers.add (item);

            if (changed)
            {
               item.withMarket(this);
               firePropertyChange(PROPERTY_OFFERS, null, item);
            }
         }
      }
      return this;
   } 

   public Market withoutOffers(Offer... value)
   {
      for (Offer item : value)
      {
         if ((this.offers != null) && (item != null))
         {
            if (this.offers.remove(item))
            {
               item.setMarket(null);
               firePropertyChange(PROPERTY_OFFERS, item, null);
            }
         }
      }
      return this;
   }

   public Offer createOffers()
   {
      Offer value = new Offer();
      withOffers(value);
      return value;
   } 
}
