package org.sdmlib.test.codegen.studyright.model.util;
import org.sdmlib.test.codegen.studyright.model.Student;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.StringList;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.test.codegen.studyright.model.University;

public class StudentSet extends SimpleSet<Student>
{
	public static final StudentSet EMPTY_SET = new StudentSet().withFlag(StudentSet.READONLY);

	public Class<?> getTypClass() {
		return Student.class;
	}

	@Override
	public StudentSet getNewList(boolean keyValue) {
		return new StudentSet();
	}


   public NumberList getAssignmentPoints()
   {
      NumberList result = new NumberList();
      for (Student obj : this)
      {
         result.add(obj.getAssignmentPoints());
      }
      return result;
   }

   public StudentSet filterAssignmentPoints(int value)
   {
      StudentSet result = new StudentSet();
      for(Student obj : this)
      {
         if (value == obj.getAssignmentPoints())
         {
            result.add(obj);
         }
      }
      return result;
   }
   public StudentSet filterAssignmentPoints(int minValue, int maxValue)
   {
      StudentSet result = new StudentSet();
      for(Student obj : this)
      {
         if (minValue <= obj.getAssignmentPoints() && maxValue >= obj.getAssignmentPoints())
         {
            result.add(obj);
         }
      }
      return result;
   }

   public StudentSet withAssignmentPoints(int value) {
      for (Student obj : this)
      {
         obj.setAssignmentPoints(value);
      }
      return this;
   }
   public NumberList getCredits()
   {
      NumberList result = new NumberList();
      for (Student obj : this)
      {
         result.add(obj.getCredits());
      }
      return result;
   }

   public StudentSet filterCredits(int value)
   {
      StudentSet result = new StudentSet();
      for(Student obj : this)
      {
         if (value == obj.getCredits())
         {
            result.add(obj);
         }
      }
      return result;
   }
   public StudentSet filterCredits(int minValue, int maxValue)
   {
      StudentSet result = new StudentSet();
      for(Student obj : this)
      {
         if (minValue <= obj.getCredits() && maxValue >= obj.getCredits())
         {
            result.add(obj);
         }
      }
      return result;
   }

   public StudentSet withCredits(int value) {
      for (Student obj : this)
      {
         obj.setCredits(value);
      }
      return this;
   }
   public StringList getId()
   {
      StringList result = new StringList();
      for (Student obj : this)
      {
         result.add(obj.getId());
      }
      return result;
   }

   public StudentSet filterId(String value)
   {
      StudentSet result = new StudentSet();
      for(Student obj : this)
      {
         if (value == obj.getId())
         {
            result.add(obj);
         }
      }
      return result;
   }
   public StudentSet filterId(String minValue, String maxValue)
   {
      StudentSet result = new StudentSet();
      for(Student obj : this)
      {
         if (minValue.compareTo(obj.getId()) <= 0 && maxValue.compareTo(obj.getId()) >= 0)
         {
            result.add(obj);
         }
      }
      return result;
   }

   public StudentSet withId(String value) {
      for (Student obj : this)
      {
         obj.setId(value);
      }
      return this;
   }
   public NumberList getMotivation()
   {
      NumberList result = new NumberList();
      for (Student obj : this)
      {
         result.add(obj.getMotivation());
      }
      return result;
   }

   public StudentSet filterMotivation(int value)
   {
      StudentSet result = new StudentSet();
      for(Student obj : this)
      {
         if (value == obj.getMotivation())
         {
            result.add(obj);
         }
      }
      return result;
   }
   public StudentSet filterMotivation(int minValue, int maxValue)
   {
      StudentSet result = new StudentSet();
      for(Student obj : this)
      {
         if (minValue <= obj.getMotivation() && maxValue >= obj.getMotivation())
         {
            result.add(obj);
         }
      }
      return result;
   }

   public StudentSet withMotivation(int value) {
      for (Student obj : this)
      {
         obj.setMotivation(value);
      }
      return this;
   }
   public StringList getName()
   {
      StringList result = new StringList();
      for (Student obj : this)
      {
         result.add(obj.getName());
      }
      return result;
   }

   public StudentSet filterName(String value)
   {
      StudentSet result = new StudentSet();
      for(Student obj : this)
      {
         if (value == obj.getName())
         {
            result.add(obj);
         }
      }
      return result;
   }
   public StudentSet filterName(String minValue, String maxValue)
   {
      StudentSet result = new StudentSet();
      for(Student obj : this)
      {
         if (minValue.compareTo(obj.getName()) <= 0 && maxValue.compareTo(obj.getName()) >= 0)
         {
            result.add(obj);
         }
      }
      return result;
   }

   public StudentSet withName(String value) {
      for (Student obj : this)
      {
         obj.setName(value);
      }
      return this;
   }
   public UniversitySet getUniversity()
   {
      UniversitySet result = new UniversitySet();
      for (Student obj : this)
      {
         result.add(obj.getUniversity());
      }
      return result;
   }

   public StudentSet filterUniversity(Object value)
   {
      ObjectSet neighbors = new ObjectSet().init(value);
      StudentSet answer = new StudentSet();
      for (Student obj : this)
      {
         if (neighbors.contains(obj.getUniversity()) || (neighbors.isEmpty() && obj.getUniversity() == null))
         {
            answer.add(obj);
         }
      }
      return answer;
   }

   public StudentSet withUniversity(University value)
   {
      for (Student obj : this)
      {
         obj.withUniversity(value);
      }
      return this;
   }
}