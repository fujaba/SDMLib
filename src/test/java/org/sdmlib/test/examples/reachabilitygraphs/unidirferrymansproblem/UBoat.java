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
   
package org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UBank;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UCargo;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphExampleModels.java'>ReachabilityGraphExampleModels.java</a>
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#UniDirectFerryMansProblemModel
 */
   public  class UBoat implements SendableEntity
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
      setBank(null);
      setCargo(null);
      if (getCargo() != null) { getCargo().removeYou(); }
      if (getBank() != null) { getBank().removeYou(); }
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * UBoat ----------------------------------- UBank
    *              uboat                   bank
    * </pre>
    */
   
   public static final String PROPERTY_BANK = "bank";

   private UBank bank = null;

   public UBank getBank()
   {
      return this.bank;
   }

   public boolean setBank(UBank value)
   {
      boolean changed = false;
      
      if (this.bank != value)
      {
         UBank oldValue = this.bank;
         
         this.bank = value;
         
         firePropertyChange(PROPERTY_BANK, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphFerrymansProblemExample#UniDirFerrymansProblemRules
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphFerrymansProblemExample#FerrymansProblemLazyBackup
 */
   public UBoat withBank(UBank value)
   {
      setBank(value);
      return this;
   } 

   public UBank createBank()
   {
      UBank value = new UBank();
      withBank(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * UBoat ----------------------------------- UCargo
    *              uboat                   cargo
    * </pre>
    */
   
   public static final String PROPERTY_CARGO = "cargo";

   private UCargo cargo = null;

   public UCargo getCargo()
   {
      return this.cargo;
   }

   public boolean setCargo(UCargo value)
   {
      boolean changed = false;
      
      if (this.cargo != value)
      {
         UCargo oldValue = this.cargo;
         
         this.cargo = value;
         
         firePropertyChange(PROPERTY_CARGO, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public UBoat withCargo(UCargo value)
   {
      setCargo(value);
      return this;
   } 

   public UCargo createCargo()
   {
      UCargo value = new UCargo();
      withCargo(value);
      return value;
   } 
}
