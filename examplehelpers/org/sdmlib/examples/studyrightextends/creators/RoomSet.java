package org.sdmlib.examples.studyrightextends.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.studyrightextends.Room;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;

public class RoomSet extends LinkedHashSet<Room>
{
   private static final long serialVersionUID = 1L;
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

   public UniversitySet getUni()
   {
      UniversitySet result = new UniversitySet();
      
      for (Room obj : this)
      {
         result.add(obj.getUni());
      }
      
      return result;
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
   public LectureSet getLecture()
   {
      LectureSet result = new LectureSet();
      
      for (Room obj : this)
      {
         result.addAll(obj.getLecture());
      }
      
      return result;
   }
}

