/*
   Copyright (c) 2016 Stefan
   
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
   
package org.sdmlib.simple.model.modelling_a;

import org.sdmlib.simple.model.modelling_a.Person;
import de.uniks.networkparser.EntityUtil;
import org.sdmlib.simple.model.modelling_a.Room;
import org.sdmlib.simple.model.modelling_a.util.PupilSet;
import org.sdmlib.simple.model.modelling_a.Pupil;
   /**
    * 
    * @see <a href='../../../../../../../../src/test/java/org/sdmlib/simple/TestModelCreation.java'>TestModelCreation.java</a>
 */
   public  class Teacher extends Person
{

   
   //==========================================================================
   public String teach(  )
   {
		String teachResult = "greatResult";
		return teachResult;
   }

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      setRoom(null);
      withoutPupils(this.getPupils().toArray(new Pupil[this.getPupils().size()]));
      setCurrentRoom(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_RANK = "rank";
   
   private String rank;

   public String getRank()
   {
      return this.rank;
   }
   
   public void setRank(String value)
   {
      if ( ! EntityUtil.stringEquals(this.rank, value)) {
      
         String oldValue = this.rank;
         this.rank = value;
         this.firePropertyChange(PROPERTY_RANK, oldValue, value);
      }
   }
   
   public Teacher withRank(String value)
   {
      setRank(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getRank());
      result.append(" ").append(this.getName());
      result.append(" ").append(this.getAge());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       many
    * Teacher ----------------------------------- Pupil
    *              teacher                   pupils
    * </pre>
    */
   
   public static final String PROPERTY_PUPILS = "pupils";

   private PupilSet pupils = null;
   
   public PupilSet getPupils()
   {
      if (this.pupils == null)
      {
         return PupilSet.EMPTY_SET;
      }
   
      return this.pupils;
   }

   public Teacher withPupils(Pupil... value)
   {
      if(value==null){
         return this;
      }
      for (Pupil item : value)
      {
         if (item != null)
         {
            if (this.pupils == null)
            {
               this.pupils = new PupilSet();
            }
            
            boolean changed = this.pupils.add (item);

            if (changed)
            {
               item.withTeacher(this);
               firePropertyChange(PROPERTY_PUPILS, null, item);
            }
         }
      }
      return this;
   } 

   public Teacher withoutPupils(Pupil... value)
   {
      for (Pupil item : value)
      {
         if ((this.pupils != null) && (item != null))
         {
            if (this.pupils.remove(item))
            {
               item.setTeacher(null);
               firePropertyChange(PROPERTY_PUPILS, item, null);
            }
         }
      }
      return this;
   }

   public Pupil createPupils()
   {
      Pupil value = new Pupil();
      withPupils(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Teacher ----------------------------------- Room
    *              currentTeacher                   currentRoom
    * </pre>
    */
   
   public static final String PROPERTY_CURRENTROOM = "currentRoom";

   private Room currentRoom = null;

   public Room getCurrentRoom()
   {
      return this.currentRoom;
   }

   public boolean setCurrentRoom(Room value)
   {
      boolean changed = false;
      
      if (this.currentRoom != value)
      {
         Room oldValue = this.currentRoom;
         
         if (this.currentRoom != null)
         {
            this.currentRoom = null;
            oldValue.setCurrentTeacher(null);
         }
         
         this.currentRoom = value;
         
         if (value != null)
         {
            value.withCurrentTeacher(this);
         }
         
         firePropertyChange(PROPERTY_CURRENTROOM, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Teacher withCurrentRoom(Room value)
   {
      setCurrentRoom(value);
      return this;
   } 

   public Room createCurrentRoom()
   {
      Room value = new Room();
      withCurrentRoom(value);
      return value;
   } 
}
