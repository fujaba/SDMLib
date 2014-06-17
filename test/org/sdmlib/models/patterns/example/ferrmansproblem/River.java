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
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BankSet;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;
import java.beans.PropertyChangeListener;

public class River implements PropertyChangeInterface
{

   // ==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_BANKS.equalsIgnoreCase(attrName))
      {
         return getBanks();
      }

      if (PROPERTY_BOAT.equalsIgnoreCase(attrName))
      {
         return getBoat();
      }

      return null;
   }

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_BANKS.equalsIgnoreCase(attrName))
      {
         addToBanks((Bank) value);
         return true;
      }

      if ((PROPERTY_BANKS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromBanks((Bank) value);
         return true;
      }

      if (PROPERTY_BOAT.equalsIgnoreCase(attrName))
      {
         setBoat((Boat) value);
         return true;
      }

      return false;
   }

   // ==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   // ==========================================================================

   public void removeYou()
   {
      removeAllFromBanks();
      setBoat(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * River ----------------------------------- Bank
    *              river                   banks
    * </pre>
    */

   public static final String PROPERTY_BANKS = "banks";

   private BankSet banks = null;

   public BankSet getBanks()
   {
      if (this.banks == null)
      {
         return Bank.EMPTY_SET;
      }

      return this.banks;
   }

   public boolean addToBanks(Bank value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.banks == null)
         {
            this.banks = new BankSet();
         }

         changed = this.banks.add(value);

         if (changed)
         {
            value.withRiver(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_BANKS, null,
               value);
         }
      }

      return changed;
   }

   public boolean removeFromBanks(Bank value)
   {
      boolean changed = false;

      if ((this.banks != null) && (value != null))
      {
         changed = this.banks.remove(value);

         if (changed)
         {
            value.setRiver(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_BANKS,
               value, null);
         }
      }

      return changed;
   }

   public River withBanks(Bank value)
   {
      addToBanks(value);
      return this;
   }

   public River withoutBanks(Bank value)
   {
      removeFromBanks(value);
      return this;
   }

   public void removeAllFromBanks()
   {
      LinkedHashSet<Bank> tmpSet = new LinkedHashSet<Bank>(this.getBanks());

      for (Bank value : tmpSet)
      {
         this.removeFromBanks(value);
      }
   }

   public Bank createBanks()
   {
      Bank value = new Bank();
      withBanks(value);
      return value;
   }

   /********************************************************************
    * <pre>
    *              one                       one
    * River ----------------------------------- Boat
    *              river                   boat
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
            oldValue.setRiver(null);
         }

         this.boat = value;

         if (value != null)
         {
            value.withRiver(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_BOAT, oldValue,
            value);
         changed = true;
      }

      return changed;
   }

   public River withBoat(Boat value)
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

   public River withBanks(Bank... value)
   {
      for (Bank item : value)
      {
         addToBanks(item);
      }
      return this;
   }

   public River withoutBanks(Bank... value)
   {
      for (Bank item : value)
      {
         removeFromBanks(item);
      }
      return this;
   }
}
