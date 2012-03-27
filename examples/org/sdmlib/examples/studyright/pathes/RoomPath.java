package org.sdmlib.examples.studyright.pathes;

import java.util.LinkedHashSet;

import org.sdmlib.examples.studyright.Room;

public class RoomPath extends LinkedHashSet<Room>
{
	private static final long serialVersionUID = 2091079250205273044L;

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
