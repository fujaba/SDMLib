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

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BankSet;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CargoSet;

import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphExampleModels.java'>ReachabilityGraphExampleModels.java</a>
* @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#FerryMansProblemModel
 */
   public class Bank implements PropertyChangeInterface, SendableEntity
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
      setBoat(null);
      setRiver(null);
      withoutCargos(this.getCargos().toArray(new Cargo[this.getCargos().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
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
      if ( ! StrUtil.stringEquals(this.name, value))
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public Bank withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      // result.append(" ").append(this.getAge());
      
      result.append(" ").append(this.getCargos().toString(" "));
      
      if (this.getBoat() != null)
      {
         result.append(" ").append(getBoat().toString());
      }
      
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_AGE = "age";
   
   private int age;

   public int getAge()
   {
      return this.age;
   }
   
   public void setAge(int value)
   {
      if (this.age != value)
      {
         int oldValue = this.age;
         this.age = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_AGE, oldValue, value);
      }
   }
   
   public Bank withAge(int value)
   {
      setAge(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Bank ----------------------------------- Boat
    *              bank                   boat
    * </pre>
    */
   
   public static final String PROPERTY_BOAT = "boat";

   private Boat boat = null;

   public Boat getBoat()
   {
      return this.boat;
   }

   public boolean setBoat(Boat value)
   {
      boolean changed = false;
      
      if (this.boat != value)
      {
         Boat oldValue = this.boat;
         
         if (this.boat != null)
         {
            this.boat = null;
            oldValue.setBank(null);
         }
         
         this.boat = value;
         
         if (value != null)
         {
            value.withBank(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BOAT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Bank withBoat(Boat value)
   {
      setBoat(value);
      return this;
   } 

   public Boat createBoat()
   {
      Boat value = new Boat();
      withBoat(value);
      return value;
   } 

   
   public static final BankSet EMPTY_SET = new BankSet().withFlag(BankSet.READONLY);

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Bank ----------------------------------- River
    *              banks                   river
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
            oldValue.withoutBanks(this);
         }
         
         this.river = value;
         
         if (value != null)
         {
            value.withBanks(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_RIVER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Bank withRiver(River value)
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
    *              one                       many
    * Bank ----------------------------------- Cargo
    *              bank                   cargos
    * </pre>
    */
   
   public static final String PROPERTY_CARGOS = "cargos";

   private CargoSet cargos = null;
   
   public CargoSet getCargos()
   {
      if (this.cargos == null)
      {
         return Cargo.EMPTY_SET;
      }
   
      return this.cargos;
   }

   public Bank withCargos(Cargo... value)
   {
      if(value==null){
         return this;
      }
      for (Cargo item : value)
      {
         if (item != null)
         {
            if (this.cargos == null)
            {
               this.cargos = new CargoSet();
            }
            
            boolean changed = this.cargos.add (item);

            if (changed)
            {
               item.withBank(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CARGOS, null, item);
            }
         }
      }
      return this;
   } 

   public Bank withoutCargos(Cargo... value)
   {
      for (Cargo item : value)
      {
         if ((this.cargos != null) && (item != null))
         {
            if (this.cargos.remove(item))
            {
               item.setBank(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_CARGOS, item, null);
            }
         }
      }
      return this;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
* @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
* @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphFerrymansProblemExample#FerrymansProblemExample
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphFerrymansProblemExample#FerrymansProblemManuel
 */
   public Cargo createCargos()
   {
      Cargo value = new Cargo();
      withCargos(value);
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
