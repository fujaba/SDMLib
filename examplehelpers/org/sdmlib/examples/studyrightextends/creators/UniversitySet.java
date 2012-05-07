package org.sdmlib.examples.studyrightextends.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.studyrightextends.University;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.studyrightextends.Room;

public class UniversitySet extends LinkedHashSet<University>
{
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (University obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public RoomSet getRooms()
   {
      RoomSet result = new RoomSet();
      
      for (University obj : this)
      {
         result.addAll(obj.getRooms());
      }
      
      return result;
   }
}

