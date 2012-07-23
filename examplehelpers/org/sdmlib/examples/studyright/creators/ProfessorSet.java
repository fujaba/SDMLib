package org.sdmlib.examples.studyright.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.studyright.Professor;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.studyright.Topic;

public class ProfessorSet extends LinkedHashSet<Professor>
{
	private static final long serialVersionUID = 1L;

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
   public ProfessorSet withName(String value)
   {
      for (Professor obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public ProfessorSet withTopic(Topic value)
   {
      for (Professor obj : this)
      {
         obj.withTopic(value);
      }
      
      return this;
   }

}


