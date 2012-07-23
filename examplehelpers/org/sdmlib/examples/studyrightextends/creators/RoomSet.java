package org.sdmlib.examples.studyrightextends.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.studyrightextends.Room;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.examples.studyrightextends.Lecture;
import org.sdmlib.examples.studyrightextends.University;

public class RoomSet extends LinkedHashSet<Room>
{
   public StringList getRoomNo()
   {
      StringList result = new StringList();
      
      for (Room obj : this)
      {
         result.add(obj.getRoomNo());
      }
      
      return result;
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

   public LectureSet getLecture()
   {
      LectureSet result = new LectureSet();
      
      for (Room obj : this)
      {
         result.addAll(obj.getLecture());
      }
      
      return result;
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

   public RoomSet withRoomNo(String value)
   {
      for (Room obj : this)
      {
         obj.withRoomNo(value);
      }
      
      return this;
   }

   public RoomSet withCredits(int value)
   {
      for (Room obj : this)
      {
         obj.withCredits(value);
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

   public RoomSet withUni(University value)
   {
      for (Room obj : this)
      {
         obj.withUni(value);
      }
      
      return this;
   }

}


