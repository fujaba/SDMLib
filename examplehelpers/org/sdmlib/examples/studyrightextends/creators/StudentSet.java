package org.sdmlib.examples.studyrightextends.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.studyrightextends.Student;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.examples.studyrightextends.Lecture;

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

   public LectureSet getLecture()
   {
      LectureSet result = new LectureSet();
      
      for (Student obj : this)
      {
         result.addAll(obj.getLecture());
      }
      
      return result;
   }
}

