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
   
package de.kassel.roombook;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.StrUtil;
import de.kassel.roombook.util.FloorSet;

public class Floor implements PropertyChangeInterface
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
      setBuildings(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_LEVEL = "level";
   
   private int level;


   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getLevel());
      _.append(" ").append(this.getName());
      _.append(" ").append(this.getGuest());
      return _.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;

   
   //==========================================================================
   
   public static final String PROPERTY_GUEST = "guest";
   
   private String guest;

   
   public static final FloorSet EMPTY_SET = new FloorSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Floor ----------------------------------- Building
    *              has                   buildings
    * </pre>
    */
   
   public static final String PROPERTY_BUILDINGS = "buildings";

   private Building buildings = null;

   public Building getBuildings()
   {
      return this.buildings;
   }

   public boolean setBuildings(Building value)
   {
      boolean changed = false;
      
      if (this.buildings != value)
      {
         Building oldValue = this.buildings;
         
         if (this.buildings != null)
         {
            this.buildings = null;
            oldValue.withoutHas(this);
         }
         
         this.buildings = value;
         
         if (value != null)
         {
            value.withHas(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BUILDINGS, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Floor withBuildings(Building value)
   {
      setBuildings(value);
      return this;
   } 

   public Building createBuildings()
   {
      Building value = new Building();
      withBuildings(value);
      return value;
   } 

   
   //==========================================================================
   
   public int getLevel()
   {
      return this.level;
   }
   
   public void setLevel(int value)
   {
      if (this.level != value)
      {
         int oldValue = this.level;
         this.level = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LEVEL, oldValue, value);
      }
   }
   
   public Floor withLevel(int value)
   {
      setLevel(value);
      return this;
   } 

   
   //==========================================================================
   
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
   
   public Floor withName(String value)
   {
      setName(value);
      return this;
   } 

   
   //==========================================================================
   
   public String getGuest()
   {
      return this.guest;
   }
   
   public void setGuest(String value)
   {
      if ( ! StrUtil.stringEquals(this.guest, value))
      {
         String oldValue = this.guest;
         this.guest = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GUEST, oldValue, value);
      }
   }
   
   public Floor withGuest(String value)
   {
      setGuest(value);
      return this;
   } 
}
