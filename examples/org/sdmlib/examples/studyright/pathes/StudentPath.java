package org.sdmlib.examples.studyright.pathes;

import java.util.LinkedHashSet;

import org.sdmlib.examples.studyright.Student;
import org.sdmlib.examples.studyright.University;

public class StudentPath extends LinkedHashSet<Student>
{
	private static final long serialVersionUID = 4968869988238673731L;

public StudentPath with(Student value)
   {
      this.add(value);
      return this;
   }

   public UniversityPath getUni()
   {
      UniversityPath universityPath = new UniversityPath();
      
      for (Student student : this)
      {
         University university = student.getUni();
         
         if (university != null)
         {
            universityPath.add(university);
         }
      }
      
      return universityPath;
   }

}
