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
import org.sdmlib.utils.StrUtil;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.CargoSet;

public class Cargo implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_BANK.equalsIgnoreCase(attrName))
      {
         return getBank();
      }

      if (PROPERTY_BOAT.equalsIgnoreCase(attrName))
      {
         return getBoat();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_BANK.equalsIgnoreCase(attrName))
      {
         setBank((Bank) value);
         return true;
      }

      if (PROPERTY_BOAT.equalsIgnoreCase(attrName))
      {
         setBoat((Boat) value);
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
      setBoat(null);
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
   
   public Cargo withName(String value)
   {
      setName(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      return _.substring(1);
   }


   
   public static final CargoSet EMPTY_SET = new CargoSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Cargo ----------------------------------- Bank
    *              cargos                   bank
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
            oldValue.withoutCargos(this);
         }
         
         this.bank = value;
         
         if (value != null)
         {
            value.withCargos(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BANK, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Cargo withBank(Bank value)
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
    * Cargo ----------------------------------- Boat
    *              cargo                   boat
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
            oldValue.setCargo(null);
         }
         
         this.boat = value;
         
         if (value != null)
         {
            value.withCargo(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BOAT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Cargo withBoat(Boat value)
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
}

