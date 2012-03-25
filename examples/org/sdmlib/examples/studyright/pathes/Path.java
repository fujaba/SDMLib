package org.sdmlib.examples.studyright.pathes;

import org.sdmlib.examples.studyright.Student;

public class Path
{

   public static StudentPath startWith(Student value)
   {
      return new StudentPath().with(value);
      
   }

}
