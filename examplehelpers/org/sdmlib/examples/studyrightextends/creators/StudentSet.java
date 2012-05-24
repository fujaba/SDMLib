package org.sdmlib.examples.studyrightextends.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.studyrightextends.Student;
import org.sdmlib.models.modelsets.intList;

public class StudentSet extends LinkedHashSet<Student>
{
   private static final long serialVersionUID = 1L;
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

