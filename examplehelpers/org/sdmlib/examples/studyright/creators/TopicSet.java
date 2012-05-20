package org.sdmlib.examples.studyright.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.studyright.Topic;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.studyright.Professor;

public class TopicSet extends LinkedHashSet<Topic>
{
   public StringList getTitle()
   {
      StringList result = new StringList();
      
      for (Topic obj : this)
      {
         result.add(obj.getTitle());
      }
      
      return result;
   }

   public ProfessorSet getProf()
   {
      ProfessorSet result = new ProfessorSet();
      
      for (Topic obj : this)
      {
         result.add(obj.getProf());
      }
      
      return result;
   }
}

