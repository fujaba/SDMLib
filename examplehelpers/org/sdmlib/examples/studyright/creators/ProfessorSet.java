package org.sdmlib.examples.studyright.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.studyright.Professor;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.studyright.Topic;

public class ProfessorSet extends LinkedHashSet<Professor>
{
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Professor obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public TopicSet getTopic()
   {
      TopicSet result = new TopicSet();
      
      for (Professor obj : this)
      {
         result.add(obj.getTopic());
      }
      
      return result;
   }
}

