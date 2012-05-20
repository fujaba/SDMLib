package org.sdmlib.examples.studyright.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.studyright.University;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.studyright.Student;
import org.sdmlib.examples.studyright.Room;

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

   public StudentSet getStudents()
   {
      StudentSet result = new StudentSet();
      
      for (University obj : this)
      {
         result.addAll(obj.getStudents());
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

