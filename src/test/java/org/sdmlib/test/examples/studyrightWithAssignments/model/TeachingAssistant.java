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
   
package org.sdmlib.test.examples.studyrightWithAssignments.model;

/**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/studyrightWithAssignments/StudyRightWithAssignmentsModel.java'>StudyRightWithAssignmentsModel.java</a>
 */
   public  class TeachingAssistant extends Student
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      setUniversity(null);
      setIn(null);
      withoutFriends(this.getFriends().toArray(new Student[this.getFriends().size()]));
      withoutDone(this.getDone().toArray(new Assignment[this.getDone().size()]));
      setRoom(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_CERTIFIED = "certified";
   
   private boolean certified;

   public boolean isCertified()
   {
      return this.certified;
   }
   
   public void setCertified(boolean value)
   {
      if (this.certified != value) {
      
         boolean oldValue = this.certified;
         this.certified = value;
         this.firePropertyChange(PROPERTY_CERTIFIED, oldValue, value);
      }
   }
   
   public TeachingAssistant withCertified(boolean value)
   {
      setCertified(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      result.append(" ").append(this.getId());
      result.append(" ").append(this.getAssignmentPoints());
      result.append(" ").append(this.getMotivation());
      result.append(" ").append(this.getCredits());
      return result.substring(1);
   }


   
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
         
         firePropertyChange(PROPERTY_ROOM, oldValue, value);
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
