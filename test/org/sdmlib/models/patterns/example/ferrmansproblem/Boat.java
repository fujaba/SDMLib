/*
   Copyright (c) 2013 zuendorf 
   
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
   
package org.sdmlib.models.patterns.example.ferrmansproblem;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;

public class Boat implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_BANK.equalsIgnoreCase(attrName))
      {
         return getBank();
      }

      if (PROPERTY_CARGO.equalsIgnoreCase(attrName))
      {
         return getCargo();
      }

      if (PROPERTY_RIVER.equalsIgnoreCase(attrName))
      {
         return getRiver();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_BANK.equalsIgnoreCase(attrName))
      {
         setBank((Bank) value);
         return true;
      }

      if (PROPERTY_CARGO.equalsIgnoreCase(attrName))
      {
         setCargo((Cargo) value);
         return true;
      }

      if (PROPERTY_RIVER.equalsIgnoreCase(attrName))
      {
         setRiver((River) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setBank(null);
      setCargo(null);
      setRiver(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
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
}

