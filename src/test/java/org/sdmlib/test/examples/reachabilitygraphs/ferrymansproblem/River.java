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
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BankSet;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
* @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphExampleModels.java'>ReachabilityGraphExampleModels.java</a>
*/
   public class River implements PropertyChangeInterface
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      setBoat(null);
      withoutBanks(this.getBanks().toArray(new Bank[this.getBanks().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
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
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BOAT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public River withBoat(Boat value)
   {
      setBoat(value);
      return this;
   } 

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
*/
   public Boat createBoat()
   {
      Boat value = new Boat();
      withBoat(value);
      return value;
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

   public River withBanks(Bank... value)
   {
      if(value==null){
         return this;
      }
      for (Bank item : value)
      {
         if (item != null)
         {
            if (this.banks == null)
            {
               this.banks = new BankSet();
            }
            
            boolean changed = this.banks.add (item);

            if (changed)
            {
               item.withRiver(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_BANKS, null, item);
            }
         }
      }
      return this;
   } 

   public River withoutBanks(Bank... value)
   {
      for (Bank item : value)
      {
         if ((this.banks != null) && (item != null))
         {
            if (this.banks.remove(item))
            {
               item.setRiver(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_BANKS, item, null);
            }
         }
      }
      return this;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
*/
   public Bank createBanks()
   {
      Bank value = new Bank();
      withBanks(value);
      return value;
   } 
}
