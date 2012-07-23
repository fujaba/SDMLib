package org.sdmlib.examples.studyright.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.studyright.University;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.studyright.Student;
import org.sdmlib.examples.studyright.Room;

public class UniversitySet extends LinkedHashSet<University>
{
	private static final long serialVersionUID = 1L;
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
   public UniversitySet withName(String value)
   {
      for (University obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public UniversitySet withStudents(Student value)
   {
      for (University obj : this)
      {
         obj.withStudents(value);
      }
      
      return this;
   }

   public UniversitySet withoutStudents(Student value)
   {
      for (University obj : this)
      {
         obj.withoutStudents(value);
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

}


