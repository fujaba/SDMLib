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
   
package org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBoat;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBankSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBank;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphExampleModels.java'>ReachabilityGraphExampleModels.java</a>
 */
   public  class LRiver implements SendableEntity
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
      if (getBoat() != null) { getBoat().removeYou(); }
      for (LBank obj : new LBankSet(this.getBanks())) { obj.removeYou(); }
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              many                       one
    * LRiver ----------------------------------- LBoat
    *              river                   boat
    * </pre>
    */
   
   public static final String PROPERTY_BOAT = "boat";

   private LBoat boat = null;

   public LBoat getBoat()
   {
      return this.boat;
   }

   public boolean setBoat(LBoat value)
   {
      boolean changed = false;
      
      if (this.boat != value)
      {
         LBoat oldValue = this.boat;
         
         if (this.boat != null)
         {
            this.boat = null;
            oldValue.withoutRiver(this);
         }
         
         this.boat = value;
         
         if (value != null)
         {
            value.withRiver(this);
         }
         
         firePropertyChange(PROPERTY_BOAT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public LRiver withBoat(LBoat value)
   {
      setBoat(value);
      return this;
   } 

   public LBoat createBoat()
   {
      LBoat value = new LBoat();
      withBoat(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * LRiver ----------------------------------- LBank
    *              river                   banks
    * </pre>
    */
   
   public static final String PROPERTY_BANKS = "banks";

   private LBankSet banks = null;
   
   public LBankSet getBanks()
   {
      if (this.banks == null)
      {
         return LBankSet.EMPTY_SET;
      }
   
      return this.banks;
   }

   public LRiver withBanks(LBank... value)
   {
      if(value==null){
         return this;
      }
      for (LBank item : value)
      {
         if (item != null)
         {
            if (this.banks == null)
            {
               this.banks = new LBankSet();
            }
            
            boolean changed = this.banks.add (item);

            if (changed)
            {
               item.withRiver(this);
               firePropertyChange(PROPERTY_BANKS, null, item);
            }
         }
      }
      return this;
   } 

   public LRiver withoutBanks(LBank... value)
   {
      for (LBank item : value)
      {
         if ((this.banks != null) && (item != null))
         {
            if (this.banks.remove(item))
            {
               item.withoutRiver(this);
               firePropertyChange(PROPERTY_BANKS, item, null);
            }
         }
      }
      return this;
   }

   public LBank createBanks()
   {
      LBank value = new LBank();
      withBanks(value);
      return value;
   } 
}
