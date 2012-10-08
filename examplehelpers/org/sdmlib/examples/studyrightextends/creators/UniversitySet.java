package org.sdmlib.examples.studyrightextends.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.studyrightextends.University;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
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
   public UniversitySet withName(String value)
   {
      for (University obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public UniversitySet withRooms(Room value)
   {
      for (University obj : this)
      {
         obj.withRooms(value);
      }
      
      return this;
   }

   public UniversitySet withoutRooms(Room value)
   {
      for (University obj : this)
      {
         obj.withoutRooms(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (University elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public UniversitySet with(University value)
   {
      this.add(value);
      return this;
   }
   
   public UniversitySet without(University value)
   {
      this.remove(value);
      return this;
   }
}



