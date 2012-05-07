package org.sdmlib.examples.studyrightextends.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.studyrightextends.Professor;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.examples.studyrightextends.Lecture;

public class ProfessorSet extends LinkedHashSet<Professor>
{
   public intList getPersNr()
   {
      intList result = new intList();
      
      for (Professor obj : this)
      {
         result.add(obj.getPersNr());
      }
      
      return result;
   }

   public LectureSet getLecture()
   {
      LectureSet result = new LectureSet();
      
      for (Professor obj : this)
      {
         result.addAll(obj.getLecture());
      }
      
      return result;
   }
}

