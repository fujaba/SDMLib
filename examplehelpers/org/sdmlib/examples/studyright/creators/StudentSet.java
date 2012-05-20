package org.sdmlib.examples.studyright.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.studyright.Student;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.examples.studyright.University;
import org.sdmlib.examples.studyright.Room;

public class StudentSet extends LinkedHashSet<Student>
{
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
}

