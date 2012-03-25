package org.sdmlib.examples.studyright.pathes;

import java.util.LinkedHashSet;

import org.sdmlib.examples.studyright.Room;
import org.sdmlib.examples.studyright.University;

public class UniversityPath extends LinkedHashSet<University>
{

   public RoomPath getRooms()
   {
      RoomPath roomPath = new RoomPath();
      
      for (University university : this)
      {
         LinkedHashSet<Room> rooms = university.getRooms();
         roomPath.addAll(rooms);
      }
      
      return roomPath;
   }
   
}
