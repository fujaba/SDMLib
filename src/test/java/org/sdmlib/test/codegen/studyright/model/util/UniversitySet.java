package org.sdmlib.test.codegen.studyright.model.util;
import org.sdmlib.test.codegen.studyright.model.University;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.test.codegen.studyright.model.Student;

public class UniversitySet extends SimpleSet<University>
{
	public static final UniversitySet EMPTY_SET = new UniversitySet().withFlag(UniversitySet.READONLY);

	public Class<?> getTypClass() {
		return University.class;
	}

	@Override
	public UniversitySet getNewList(boolean keyValue) {
		return new UniversitySet();
	}


   public StringList getName()
   {
      StringList result = new StringList();
      for (University obj : this)
      {
         result.add(obj.getName());
      }
      return result;
   }

   public UniversitySet filterName(String value)
   {
      UniversitySet result = new UniversitySet();
      for(University obj : this)
      {
         if (value == obj.getName())
         {
            result.add(obj);
         }
      }
      return result;
   }

   public UniversitySet withName(String value) {
      for (University obj : this)
      {
         obj.setName(value);
      }
      return this;
   }
   public UniversitySet getStudents()
   {
      UniversitySet result = new UniversitySet();
      for (University obj : this)
      {
         result.with(obj.getStudents());
      }
      return result;
   }

   public UniversitySet filterStudents(Object value)
   {
      ObjectSet neighbors = new ObjectSet().init(value);
      UniversitySet answer = new UniversitySet();
      for (University obj : this)
      {
         if (! neighbors.containsAny(obj.getStudents()))
         {
            answer.add(obj);
         }
      }
      return answer;
   }

   public UniversitySet withStudents(Student value)
   {
      for (University obj : this)
      {
         obj.withStudents(value);
      }
      return this;
   }
   public UniversitySet selfTest()
   {
      return UniversitySet.EMPTY_SET;
   }

}