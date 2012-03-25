package org.sdmlib.examples.studyright.pathes;

import java.util.LinkedHashSet;
import java.util.Vector;

import org.sdmlib.examples.studyright.Room;

public class RoomPath extends LinkedHashSet<Room>
{
   public intPath getCredits()
   {
      intPath result = new intPath();
      
      for (Room room : this)
      {
         int credits = room.getCredits();
         result.add(credits);
      }
      
      return result;
   }

}
