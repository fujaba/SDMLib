/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.serialization.PropertyChangeInterface;
import de.uniks.networkparser.interfaces.SendableEntity;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.River;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Bank;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Cargo;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphExampleModels.java'>ReachabilityGraphExampleModels.java</a>
*/
   public class Boat implements PropertyChangeInterface, SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
      return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }
   
	public boolean removePropertyChangeListener(PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(listener);
		}
		return true;
	}

	public boolean removePropertyChangeListener(String property, PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
		return true;
	}


   
   //==========================================================================
   
   
   public void removeYou()
   {
      setRiver(null);
      setBank(null);
      setCargo(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Boat ----------------------------------- River
    *              boat                   river
    * </pre>
    */
   
   public static final String PROPERTY_RIVER = "river";

   private River river = null;

   public River getRiver()
   {
      return this.river;
   }

   public boolean setRiver(River value)
   {
      boolean changed = false;
      
      if (this.river != value)
      {
         River oldValue = this.river;
         
         if (this.river != null)
         {
            this.river = null;
            oldValue.setBoat(null);
         }
         
         this.river = value;
         
         if (value != null)
         {
            value.withBoat(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_RIVER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Boat withRiver(River value)
   {
      setRiver(value);
      return this;
   } 

   public River createRiver()
   {
      River value = new River();
      withRiver(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Boat ----------------------------------- Bank
    *              boat                   bank
    * </pre>
    */
   
   public static final String PROPERTY_BANK = "bank";

   private Bank bank = null;

   public Bank getBank()
   {
      return this.bank;
   }

   public boolean setBank(Bank value)
   {
      boolean changed = false;
      
      if (this.bank != value)
      {
         Bank oldValue = this.bank;
         
         if (this.bank != null)
         {
            this.bank = null;
            oldValue.setBoat(null);
         }
         
         this.bank = value;
         
         if (value != null)
         {
            value.withBoat(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BANK, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Boat withBank(Bank value)
   {
      setBank(value);
      return this;
   } 

   public Bank createBank()
   {
      Bank value = new Bank();
      withBank(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Boat ----------------------------------- Cargo
    *              boat                   cargo
    * </pre>
    */
   
   public static final String PROPERTY_CARGO = "cargo";

   private Cargo cargo = null;

   public Cargo getCargo()
   {
      return this.cargo;
   }

   public boolean setCargo(Cargo value)
   {
      boolean changed = false;
      
      if (this.cargo != value)
      {
         Cargo oldValue = this.cargo;
         
         if (this.cargo != null)
         {
            this.cargo = null;
            oldValue.setBoat(null);
         }
         
         this.cargo = value;
         
         if (value != null)
         {
            value.withBoat(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CARGO, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Boat withCargo(Cargo value)
   {
      setCargo(value);
      return this;
   } 

   public Cargo createCargo()
   {
      Cargo value = new Cargo();
      withCargo(value);
      return value;
   } 

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   }
