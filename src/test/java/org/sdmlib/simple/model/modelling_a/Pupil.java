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
import org.sdmlib.simple.model.modelling_a.Room;
import org.sdmlib.simple.model.modelling_a.Teacher;
   /**
    * 
    * @see <a href='../../../../../../../../src/test/java/org/sdmlib/simple/TestModelCreation.java'>TestModelCreation.java</a>
 */
   public  class Pupil extends Person
{

   
   //==========================================================================
   public String read(  )
   {
      return null;
   }

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      setRoom(null);
      setCurrentRoom(null);
      setTeacher(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_CREDITS = "credits";
   
   private int credits;

   public int getCredits()
   {
      return this.credits;
   }
   
   public void setCredits(int value)
   {
      if (this.credits != value) {
      
         int oldValue = this.credits;
         this.credits = value;
         this.firePropertyChange(PROPERTY_CREDITS, oldValue, value);
      }
   }
   
   public Pupil withCredits(int value)
   {
      setCredits(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getCredits());
      result.append(" ").append(this.getName());
      result.append(" ").append(this.getAge());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              many                       one
    * Pupil ----------------------------------- Room
    *              currentPupils                   currentRoom
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
            oldValue.withoutCurrentPupils(this);
         }
         
         this.currentRoom = value;
         
         if (value != null)
         {
            value.withCurrentPupils(this);
         }
         
         firePropertyChange(PROPERTY_CURRENTROOM, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Pupil withCurrentRoom(Room value)
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

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Pupil ----------------------------------- Teacher
    *              pupils                   teacher
    * </pre>
    */
   
   public static final String PROPERTY_TEACHER = "teacher";

   private Teacher teacher = null;

   public Teacher getTeacher()
   {
      return this.teacher;
   }

   public boolean setTeacher(Teacher value)
   {
      boolean changed = false;
      
      if (this.teacher != value)
      {
         Teacher oldValue = this.teacher;
         
         if (this.teacher != null)
         {
            this.teacher = null;
            oldValue.withoutPupils(this);
         }
         
         this.teacher = value;
         
         if (value != null)
         {
            value.withPupils(this);
         }
         
         firePropertyChange(PROPERTY_TEACHER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Pupil withTeacher(Teacher value)
   {
      setTeacher(value);
      return this;
   } 

   public Teacher createTeacher()
   {
      Teacher value = new Teacher();
      withTeacher(value);
      return value;
   } 
}
