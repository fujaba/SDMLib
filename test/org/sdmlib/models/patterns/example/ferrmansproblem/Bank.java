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
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.BankSet;
import org.sdmlib.models.patterns.example.ferrmansproblem.creators.CargoSet;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;

public class Bank implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_AGE.equalsIgnoreCase(attrName))
      {
         return getAge();
      }

      if (PROPERTY_BOAT.equalsIgnoreCase(attrName))
      {
         return getBoat();
      }

      if (PROPERTY_RIVER.equalsIgnoreCase(attrName))
      {
         return getRiver();
      }

      if (PROPERTY_CARGOS.equalsIgnoreCase(attrName))
      {
         return getCargos();
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

      if (PROPERTY_AGE.equalsIgnoreCase(attrName))
      {
         setAge(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_BOAT.equalsIgnoreCase(attrName))
      {
         setBoat((Boat) value);
         return true;
      }

      if (PROPERTY_RIVER.equalsIgnoreCase(attrName))
      {
         setRiver((River) value);
         return true;
      }

      if (PROPERTY_CARGOS.equalsIgnoreCase(attrName))
      {
         addToCargos((Cargo) value);
         return true;
      }
      
      if ((PROPERTY_CARGOS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromCargos((Cargo) value);
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
      setBoat(null);
      setRiver(null);
      removeAllFromCargos();
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

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      _.append(" ").append(this.getAge());
      return _.substring(1);
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

   
   public static final BankSet EMPTY_SET = new BankSet();

   
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
   
   public boolean addToCargos(Cargo value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.cargos == null)
         {
            this.cargos = new CargoSet();
         }
         
         changed = this.cargos.add (value);
         
         if (changed)
         {
            value.withBank(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_CARGOS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromCargos(Cargo value)
   {
      boolean changed = false;
      
      if ((this.cargos != null) && (value != null))
      {
         changed = this.cargos.remove (value);
         
         if (changed)
         {
            value.setBank(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_CARGOS, value, null);
         }
      }
         
      return changed;   
   }
   
   public Bank withCargos(Cargo value)
   {
      addToCargos(value);
      return this;
   } 
   
   public Bank withoutCargos(Cargo value)
   {
      removeFromCargos(value);
      return this;
   } 
   
   public void removeAllFromCargos()
   {
      LinkedHashSet<Cargo> tmpSet = new LinkedHashSet<Cargo>(this.getCargos());
   
      for (Cargo value : tmpSet)
      {
         this.removeFromCargos(value);
      }
   }
   
   public Cargo createCargos()
   {
      Cargo value = new Cargo();
      withCargos(value);
      return value;
   } 
}

