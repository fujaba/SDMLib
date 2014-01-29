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
   
package org.sdmlib.examples.studyrightWithAssignments;

import org.sdmlib.examples.studyrightWithAssignments.Student;
import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.studyrightWithAssignments.creators.TeachingAssistantSet;

public class TeachingAssistant extends Student implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_CERTIFIED.equalsIgnoreCase(attrName))
      {
         return getCertified();
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_ID.equalsIgnoreCase(attrName))
      {
         return getId();
      }

      if (PROPERTY_ASSIGNMENTPOINTS.equalsIgnoreCase(attrName))
      {
         return getAssignmentPoints();
      }

      if (PROPERTY_MOTIVATION.equalsIgnoreCase(attrName))
      {
         return getMotivation();
      }

      if (PROPERTY_CREDITS.equalsIgnoreCase(attrName))
      {
         return getCredits();
      }

      if (PROPERTY_UNIVERSITY.equalsIgnoreCase(attrName))
      {
         return getUniversity();
      }

      if (PROPERTY_IN.equalsIgnoreCase(attrName))
      {
         return getIn();
      }

      if (PROPERTY_DONE.equalsIgnoreCase(attrName))
      {
         return getDone();
      }

      if (PROPERTY_FRIENDS.equalsIgnoreCase(attrName))
      {
         return getFriends();
      }

      if (PROPERTY_ROOM.equalsIgnoreCase(attrName))
      {
         return getRoom();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_CERTIFIED.equalsIgnoreCase(attrName))
      {
         setCertified((Boolean) value);
         return true;
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_ID.equalsIgnoreCase(attrName))
      {
         setId((String) value);
         return true;
      }

      if (PROPERTY_ASSIGNMENTPOINTS.equalsIgnoreCase(attrName))
      {
         setAssignmentPoints(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_MOTIVATION.equalsIgnoreCase(attrName))
      {
         setMotivation(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_CREDITS.equalsIgnoreCase(attrName))
      {
         setCredits(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_UNIVERSITY.equalsIgnoreCase(attrName))
      {
         setUniversity((University) value);
         return true;
      }

      if (PROPERTY_IN.equalsIgnoreCase(attrName))
      {
         setIn((Room) value);
         return true;
      }

      if (PROPERTY_DONE.equalsIgnoreCase(attrName))
      {
         addToDone((Assignment) value);
         return true;
      }
      
      if ((PROPERTY_DONE + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromDone((Assignment) value);
         return true;
      }

      if (PROPERTY_FRIENDS.equalsIgnoreCase(attrName))
      {
         addToFriends((Student) value);
         return true;
      }
      
      if ((PROPERTY_FRIENDS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromFriends((Student) value);
         return true;
      }

      if (PROPERTY_ROOM.equalsIgnoreCase(attrName))
      {
         setRoom((Room) value);
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
      setUniversity(null);
      setIn(null);
      removeAllFromDone();
      removeAllFromFriends();
      setRoom(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   
   //==========================================================================
   
   public static final String PROPERTY_CERTIFIED = "certified";
   
   private boolean certified;

   public boolean getCertified()
   {
      return this.certified;
   }
   
   public void setCertified(boolean value)
   {
      if (this.certified != value)
      {
         boolean oldValue = this.certified;
         this.certified = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CERTIFIED, oldValue, value);
      }
   }
   
   public TeachingAssistant withCertified(boolean value)
   {
      setCertified(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getName());
      _.append(" ").append(this.getId());
      _.append(" ").append(this.getAssignmentPoints());
      _.append(" ").append(this.getMotivation());
      _.append(" ").append(this.getCredits());
      return _.substring(1);
   }


   
   public static final TeachingAssistantSet EMPTY_SET = new TeachingAssistantSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * TeachingAssistant ----------------------------------- Room
    *              tas                   room
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
            oldValue.withoutTas(this);
         }
         
         this.room = value;
         
         if (value != null)
         {
            value.withTas(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ROOM, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public TeachingAssistant withRoom(Room value)
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

