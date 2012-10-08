package org.sdmlib.examples.studyright.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.studyright.Student;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.examples.studyright.University;
import org.sdmlib.examples.studyright.Room;

public class StudentSet extends LinkedHashSet<Student>
{
	private static final long serialVersionUID = 1L;
public StringList getName()
   {
      StringList result = new StringList();
      
      for (Student obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public intList getMatrNo()
   {
      intList result = new intList();
      
      for (Student obj : this)
      {
         result.add(obj.getMatrNo());
      }
      
      return result;
   }

   public UniversitySet getUni()
   {
      UniversitySet result = new UniversitySet();
      
      for (Student obj : this)
      {
         result.add(obj.getUni());
      }
      
      return result;
   }
   public RoomSet getIn()
   {
      RoomSet result = new RoomSet();
      
      for (Student obj : this)
      {
         result.add(obj.getIn());
      }
      
      return result;
   }
   public StudentSet withName(String value)
   {
      for (Student obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public StudentSet withMatrNo(int value)
   {
      for (Student obj : this)
      {
         obj.withMatrNo(value);
      }
      
      return this;
   }

   public StudentSet withUni(University value)
   {
      for (Student obj : this)
      {
         obj.withUni(value);
      }
      
      return this;
   }

   public StudentSet withIn(Room value)
   {
      for (Student obj : this)
      {
         obj.withIn(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Student elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public StudentSet with(Student value)
   {
      this.add(value);
      return this;
   }
   
   public StudentSet without(Student value)
   {
      this.remove(value);
      return this;
   }
}



