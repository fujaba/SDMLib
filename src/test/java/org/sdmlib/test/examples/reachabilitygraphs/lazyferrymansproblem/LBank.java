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
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.CargoSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.Cargo;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.LRiverSet;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LRiver;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphExampleModels.java'>ReachabilityGraphExampleModels.java</a>
 */
   public  class LBank implements SendableEntity
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
      for (Cargo obj : new CargoSet(this.getCargos())) { obj.removeYou(); }
      withoutRiver(this.getRiver().toArray(new LRiver[this.getRiver().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
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
      if (this.age != value) {
      
         int oldValue = this.age;
         this.age = value;
         this.firePropertyChange(PROPERTY_AGE, oldValue, value);
      }
   }
   
   public LBank withAge(int value)
   {
      setAge(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getAge());
      result.append(" ").append(this.getName());
      return result.substring(1);
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
   
   public LBank withName(String value)
   {
      setName(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * LBank ----------------------------------- LBoat
    *              bank                   boat
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

   public LBank withBoat(LBoat... value)
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
               item.withBank(this);
               firePropertyChange(PROPERTY_BOAT, null, item);
            }
         }
      }
      return this;
   } 

   public LBank withoutBoat(LBoat... value)
   {
      for (LBoat item : value)
      {
         if ((this.boat != null) && (item != null))
         {
            if (this.boat.remove(item))
            {
               item.setBank(null);
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
    * LBank ----------------------------------- Cargo
    *              bank                   cargos
    * </pre>
    */
   
   public static final String PROPERTY_CARGOS = "cargos";

   private CargoSet cargos = null;
   
   public CargoSet getCargos()
   {
      if (this.cargos == null)
      {
         return CargoSet.EMPTY_SET;
      }
   
      return this.cargos;
   }

   public LBank withCargos(Cargo... value)
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
               firePropertyChange(PROPERTY_CARGOS, null, item);
            }
         }
      }
      return this;
   } 

   public LBank withoutCargos(Cargo... value)
   {
      for (Cargo item : value)
      {
         if ((this.cargos != null) && (item != null))
         {
            if (this.cargos.remove(item))
            {
               item.withoutBank(this);
               firePropertyChange(PROPERTY_CARGOS, item, null);
            }
         }
      }
      return this;
   }

   public Cargo createCargos()
   {
      Cargo value = new Cargo();
      withCargos(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * LBank ----------------------------------- LRiver
    *              banks                   river
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

   public LBank withRiver(LRiver... value)
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
               item.withBanks(this);
               firePropertyChange(PROPERTY_RIVER, null, item);
            }
         }
      }
      return this;
   } 

   public LBank withoutRiver(LRiver... value)
   {
      for (LRiver item : value)
      {
         if ((this.river != null) && (item != null))
         {
            if (this.river.remove(item))
            {
               item.withoutBanks(this);
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
}
