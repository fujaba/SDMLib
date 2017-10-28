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
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBankSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBank;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LRiverSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LRiver;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LCargo;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphExampleModels.java'>ReachabilityGraphExampleModels.java</a>
 */
   public  class LBoat implements SendableEntity
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
      withoutBank(this.getBank().toArray(new LBank[this.getBank().size()]));
      withoutRiver(this.getRiver().toArray(new LRiver[this.getRiver().size()]));
      if (getCargo() != null) { getCargo().removeYou(); }
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              many                       many
    * LBoat ----------------------------------- LBank
    *              boat                   bank
    * </pre>
    */
   
   public static final String PROPERTY_BANK = "bank";

   private LBankSet bank = null;
   
   public LBankSet getBank()
   {
      if (this.bank == null)
      {
         return LBankSet.EMPTY_SET;
      }
   
      return this.bank;
   }

   public LBoat withBank(LBank... value)
   {
      if(value==null){
         return this;
      }
      for (LBank item : value)
      {
         if (item != null)
         {
            if (this.bank == null)
            {
               this.bank = new LBankSet();
            }
            
            boolean changed = this.bank.add (item);

            if (changed)
            {
               item.withBoat(this);
               firePropertyChange(PROPERTY_BANK, null, item);
            }
         }
      }
      return this;
   } 

   public LBoat withoutBank(LBank... value)
   {
      for (LBank item : value)
      {
         if ((this.bank != null) && (item != null))
         {
            if (this.bank.remove(item))
            {
               item.withoutBoat(this);
               firePropertyChange(PROPERTY_BANK, item, null);
            }
         }
      }
      return this;
   }

   public LBank createBank()
   {
      LBank value = new LBank();
      withBank(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * LBoat ----------------------------------- LRiver
    *              boat                   river
    * </pre>
    */
   
   public static final String PROPERTY_RIVER = "river";

   private LRiverSet river = null;
   
   public LRiverSet getRiver()
   {
      if (this.river == null)
      {
         return LRiverSet.EMPTY_SET;
      }
   
      return this.river;
   }

   public LBoat withRiver(LRiver... value)
   {
      if(value==null){
         return this;
      }
      for (LRiver item : value)
      {
         if (item != null)
         {
            if (this.river == null)
            {
               this.river = new LRiverSet();
            }
            
            boolean changed = this.river.add (item);

            if (changed)
            {
               item.withBoat(this);
               firePropertyChange(PROPERTY_RIVER, null, item);
            }
         }
      }
      return this;
   } 

   public LBoat withoutRiver(LRiver... value)
   {
      for (LRiver item : value)
      {
         if ((this.river != null) && (item != null))
         {
            if (this.river.remove(item))
            {
               item.setBoat(null);
               firePropertyChange(PROPERTY_RIVER, item, null);
            }
         }
      }
      return this;
   }

   public LRiver createRiver()
   {
      LRiver value = new LRiver();
      withRiver(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * LBoat ----------------------------------- LCargo
    *              boat                   cargo
    * </pre>
    */
   
   public static final String PROPERTY_CARGO = "cargo";

   private LCargo cargo = null;

   public LCargo getCargo()
   {
      return this.cargo;
   }

   public boolean setCargo(LCargo value)
   {
      boolean changed = false;
      
      if (this.cargo != value)
      {
         LCargo oldValue = this.cargo;
         
         if (this.cargo != null)
         {
            this.cargo = null;
            oldValue.withoutBoat(this);
         }
         
         this.cargo = value;
         
         if (value != null)
         {
            value.withBoat(this);
         }
         
         firePropertyChange(PROPERTY_CARGO, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public LBoat withCargo(LCargo value)
   {
      setCargo(value);
      return this;
   } 

   public LCargo createCargo()
   {
      LCargo value = new LCargo();
      withCargo(value);
      return value;
   } 
   
   public String toString()
   {
      if (cargo == null)
      {
         return "boat \\___/";
      }
      else
      {
         return "boat \\" + cargo.getName() + "/";
      }
   }
}
