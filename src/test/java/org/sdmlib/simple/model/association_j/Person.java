/*
   Copyright (c) 2016 zuendorf
   
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
   
package org.sdmlib.simple.model.association_j;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.simple.model.association_j.util.RoomSet;
import org.sdmlib.simple.model.association_j.Room;
   /**
    * 
    * @see <a href='../../../../../../../../src/test/java/org/sdmlib/simple/TestAssociation.java'>TestAssociation.java</a>
 */
   public  class Person implements SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
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
      getPropertyChangeSupport().removePropertyChangeListener(listener);
      return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
   
      withoutRooms(this.getRooms().toArray(new Room[this.getRooms().size()]));
      setRoom(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Person ----------------------------------- Room
    *              person                   rooms
    * </pre>
    */
   
   public static final String PROPERTY_ROOMS = "rooms";

   private RoomSet rooms = null;
   
   public RoomSet getRooms()
   {
      if (this.rooms == null)
      {
         return RoomSet.EMPTY_SET;
      }
   
      return this.rooms;
   }

   public Person withRooms(Room... value)
   {
      if(value==null){
         return this;
      }
      for (Room item : value)
      {
         if (item != null)
         {
            if (this.rooms == null)
            {
               this.rooms = new RoomSet();
            }
            
            boolean changed = this.rooms.add (item);

            if (changed)
            {
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ROOMS, null, item);
            }
         }
      }
      return this;
   } 

   public Person withoutRooms(Room... value)
   {
      for (Room item : value)
      {
         if ((this.rooms != null) && (item != null))
         {
            if (this.rooms.remove(item))
            {
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ROOMS, item, null);
            }
         }
      }
      return this;
   }

   public Room createRooms()
   {
      Room value = new Room();
      withRooms(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Person ----------------------------------- Room
    *              persons                   room
    * </pre>
    */
   
   public static final String PROPERTY_ROOM = "room";

   private Room room = null;

   public Room getRoom()
   {
      return this.room;
   }

   public boolean setRoom(Room value)
   {
      boolean changed = false;
      
      if (this.room != value)
      {
         Room oldValue = this.room;
         
         if (this.room != null)
         {
            this.room = null;
            oldValue.withoutPersons(this);
         }
         
         this.room = value;
         
         if (value != null)
         {
            value.withPersons(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ROOM, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Person withRoom(Room value)
   {
      setRoom(value);
      return this;
   } 

   public Room createRoom()
   {
      Room value = new Room();
      withRoom(value);
      return value;
   } 
}
