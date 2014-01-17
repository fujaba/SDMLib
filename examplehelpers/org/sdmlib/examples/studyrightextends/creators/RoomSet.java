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
   
package org.sdmlib.examples.studyrightextends.creators;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.examples.studyrightextends.Lecture;
import org.sdmlib.examples.studyrightextends.Room;
import org.sdmlib.examples.studyrightextends.University;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;

public class RoomSet extends LinkedHashSet<Room> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Room elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.studyrightextends.Room";
   }


   
   //==========================================================================
   
   public intList studentCount()
   {
      intList result = new intList();
      for (Room obj : this)
      {
         result.add(obj.studentCount());
      }
      return result;
   }

   public StringList getRoomNo()
   {
      StringList result = new StringList();
      
      for (Room obj : this)
      {
         result.add(obj.getRoomNo());
      }
      
      return result;
   }

   public RoomSet withRoomNo(String value)
   {
      for (Room obj : this)
      {
         obj.setRoomNo(value);
      }
      
      return this;
   }

   public intList getCredits()
   {
      intList result = new intList();
      
      for (Room obj : this)
      {
         result.add(obj.getCredits());
      }
      
      return result;
   }

   public RoomSet withCredits(int value)
   {
      for (Room obj : this)
      {
         obj.setCredits(value);
      }
      
      return this;
   }

   public RoomSet getNeighbors()
   {
      RoomSet result = new RoomSet();
      
      for (Room obj : this)
      {
         result.addAll(obj.getNeighbors());
      }
      
      return result;
   }

   public RoomSet withNeighbors(Room value)
   {
      for (Room obj : this)
      {
         obj.withNeighbors(value);
      }
      
      return this;
   }

   public RoomSet withoutNeighbors(Room value)
   {
      for (Room obj : this)
      {
         obj.withoutNeighbors(value);
      }
      
      return this;
   }

   public LectureSet getLecture()
   {
      LectureSet result = new LectureSet();
      
      for (Room obj : this)
      {
         result.addAll(obj.getLecture());
      }
      
      return result;
   }

   public RoomSet withLecture(Lecture value)
   {
      for (Room obj : this)
      {
         obj.withLecture(value);
      }
      
      return this;
   }

   public RoomSet withoutLecture(Lecture value)
   {
      for (Room obj : this)
      {
         obj.withoutLecture(value);
      }
      
      return this;
   }

   public UniversitySet getUni()
   {
      UniversitySet result = new UniversitySet();
      
      for (Room obj : this)
      {
         result.add(obj.getUni());
      }
      
      return result;
   }

   public RoomSet withUni(University value)
   {
      for (Room obj : this)
      {
         obj.withUni(value);
      }
      
      return this;
   }



   public RoomPO startModelPattern()
   {
      org.sdmlib.examples.studyrightextends.creators.ModelPattern pattern = new org.sdmlib.examples.studyrightextends.creators.ModelPattern();
      
      RoomPO patternObject = pattern.hasElementRoomPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public RoomSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Room>)value);
      }
      else if (value != null)
      {
         this.add((Room) value);
      }
      
      return this;
   }
   
   public RoomSet without(Room value)
   {
      this.remove(value);
      return this;
   }

}


