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

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import de.kassel.roombook.creators.RoomSet;

public class Room implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_FLOOR.equalsIgnoreCase(attrName))
      {
         return getFloor();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_FLOOR.equalsIgnoreCase(attrName))
      {
         setFloor((Floor) value);
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
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setFloor(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   public static final RoomSet EMPTY_SET = new RoomSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Room ----------------------------------- Floor
    *              rooms                   floor
    * </pre>
    */
   
   public static final String PROPERTY_FLOOR = "floor";

   private Floor floor = null;

   public Floor getFloor()
   {
      return this.floor;
   }

   public boolean setFloor(Floor value)
   {
      boolean changed = false;
      
      if (this.floor != value)
      {
         Floor oldValue = this.floor;
         
         if (this.floor != null)
         {
            this.floor = null;
            oldValue.withoutRooms(this);
         }
         
         this.floor = value;
         
         if (value != null)
         {
            value.withRooms(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_FLOOR, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Room withFloor(Floor value)
   {
      setFloor(value);
      return this;
   } 

   public Floor createFloor()
   {
      Floor value = new Floor();
      withFloor(value);
      return value;
   } 
}

