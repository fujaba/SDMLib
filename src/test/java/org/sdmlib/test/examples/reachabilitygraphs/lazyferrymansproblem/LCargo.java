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
import de.uniks.networkparser.EntityUtil;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBoatSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBoat;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LBankSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LBank;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphExampleModels.java'>ReachabilityGraphExampleModels.java</a>
 */
   public  class LCargo implements SendableEntity
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
      withoutBoat(this.getBoat().toArray(new LBoat[this.getBoat().size()]));
      withoutBank(this.getBank().toArray(new LBank[this.getBank().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;

   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      if ( ! EntityUtil.stringEquals(this.name, value)) {
      
         String oldValue = this.name;
         this.name = value;
         this.firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public LCargo withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       many
    * LCargo ----------------------------------- LBoat
    *              cargo                   boat
    * </pre>
    */
   
   public static final String PROPERTY_BOAT = "boat";

   private LBoatSet boat = null;
   
   public LBoatSet getBoat()
   {
      if (this.boat == null)
      {
         return LBoatSet.EMPTY_SET;
      }
   
      return this.boat;
   }

   public LCargo withBoat(LBoat... value)
   {
      if(value==null){
         return this;
      }
      for (LBoat item : value)
      {
         if (item != null)
         {
            if (this.boat == null)
            {
               this.boat = new LBoatSet();
            }
            
            boolean changed = this.boat.add (item);

            if (changed)
            {
               item.withCargo(this);
               firePropertyChange(PROPERTY_BOAT, null, item);
            }
         }
      }
      return this;
   } 

   public LCargo withoutBoat(LBoat... value)
   {
      for (LBoat item : value)
      {
         if ((this.boat != null) && (item != null))
         {
            if (this.boat.remove(item))
            {
               item.setCargo(null);
               firePropertyChange(PROPERTY_BOAT, item, null);
            }
         }
      }
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
    * LCargo ----------------------------------- LBank
    *              cargos                   bank
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

   public LCargo withBank(LBank... value)
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
               item.withCargos(this);
               firePropertyChange(PROPERTY_BANK, null, item);
            }
         }
      }
      return this;
   } 

   public LCargo withoutBank(LBank... value)
   {
      for (LBank item : value)
      {
         if ((this.bank != null) && (item != null))
         {
            if (this.bank.remove(item))
            {
               item.withoutCargos(this);
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
}
