package org.sdmlib.test.examples.studyrightWithAssignments.model.util;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.StringList;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Assignment;
import org.sdmlib.test.examples.studyrightWithAssignments.model.TeachingAssistant;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;

public class RoomSet extends SimpleSet<Room>
{
	public static final RoomSet EMPTY_SET = new RoomSet().withFlag(RoomSet.READONLY);

	public Class<?> getTypClass() {
		return Room.class;
	}

	@Override
	public RoomSet getNewList(boolean keyValue) {
		return new RoomSet();
	}


   public NumberList getCredits()
   {
      NumberList result = new NumberList();
      for (Room obj : this)
      {
         result.add(obj.getCredits());
      }
      return result;
   }

   public RoomSet filterCredits(int value)
   {
      RoomSet result = new RoomSet();
      for(Room obj : this)
      {
         if (value == obj.getCredits())
         {
            result.add(obj);
         }
      }
      return result;
   }
   public RoomSet filterCredits(int minValue, int maxValue)
   {
      RoomSet result = new RoomSet();
      for(Room obj : this)
      {
         if (minValue <= obj.getCredits() && maxValue >= obj.getCredits())
         {
            result.add(obj);
         }
      }
      return result;
   }

   public RoomSet withCredits(int value) {
      for (Room obj : this)
      {
         obj.setCredits(value);
      }
      return this;
   }
   public StringList getName()
   {
      StringList result = new StringList();
      for (Room obj : this)
      {
         result.add(obj.getName());
      }
      return result;
   }

   public RoomSet filterName(String value)
   {
      RoomSet result = new RoomSet();
      for(Room obj : this)
      {
         if (value == obj.getName())
         {
            result.add(obj);
         }
      }
      return result;
   }
   public RoomSet filterName(String minValue, String maxValue)
   {
      RoomSet result = new RoomSet();
      for(Room obj : this)
      {
         if (minValue.compareTo(obj.getName()) <= 0 && maxValue.compareTo(obj.getName()) >= 0)
         {
            result.add(obj);
         }
      }
      return result;
   }

   public RoomSet withName(String value) {
      for (Room obj : this)
      {
         obj.setName(value);
      }
      return this;
   }
   public StringList getTopic()
   {
      StringList result = new StringList();
      for (Room obj : this)
      {
         result.add(obj.getTopic());
      }
      return result;
   }

   public RoomSet filterTopic(String value)
   {
      RoomSet result = new RoomSet();
      for(Room obj : this)
      {
         if (value == obj.getTopic())
         {
            result.add(obj);
         }
      }
      return result;
   }
   public RoomSet filterTopic(String minValue, String maxValue)
   {
      RoomSet result = new RoomSet();
      for(Room obj : this)
      {
         if (minValue.compareTo(obj.getTopic()) <= 0 && maxValue.compareTo(obj.getTopic()) >= 0)
         {
            result.add(obj);
         }
      }
      return result;
   }

   public RoomSet withTopic(String value) {
      for (Room obj : this)
      {
         obj.setTopic(value);
      }
      return this;
   }
   public RoomSet getDoors()
   {
      RoomSet result = new RoomSet();
      for (Room obj : this)
      {
         result.addAll(obj.getDoors());
      }
      return result;
   }

   public RoomSet filterDoors(Object value)
   {
      ObjectSet neighbors = new ObjectSet().init(value);
      RoomSet answer = new RoomSet();
      for (Room obj : this)
      {
         if (! neighbors.containsAny(obj.getDoors()))
         {
            answer.add(obj);
         }
      }
      return answer;
   }

   public RoomSet withDoors(Room value)
   {
      for (Room obj : this)
      {
         obj.withDoors(value);
      }
      return this;
   }
   public StudentSet getStudents()
   {
      StudentSet result = new StudentSet();
      for (Room obj : this)
      {
         result.addAll(obj.getStudents());
      }
      return result;
   }

   public RoomSet filterStudents(Object value)
   {
      ObjectSet neighbors = new ObjectSet().init(value);
      RoomSet answer = new RoomSet();
      for (Room obj : this)
      {
         if (! neighbors.containsAny(obj.getStudents()))
         {
            answer.add(obj);
         }
      }
      return answer;
   }

   public RoomSet withStudents(Student value)
   {
      for (Room obj : this)
      {
         obj.withStudents(value);
      }
      return this;
   }
   public AssignmentSet getAssignments()
   {
      AssignmentSet result = new AssignmentSet();
      for (Room obj : this)
      {
         result.addAll(obj.getAssignments());
      }
      return result;
   }

   public RoomSet filterAssignments(Object value)
   {
      ObjectSet neighbors = new ObjectSet().init(value);
      RoomSet answer = new RoomSet();
      for (Room obj : this)
      {
         if (! neighbors.containsAny(obj.getAssignments()))
         {
            answer.add(obj);
         }
      }
      return answer;
   }

   public RoomSet withAssignments(Assignment value)
   {
      for (Room obj : this)
      {
         obj.withAssignments(value);
      }
      return this;
   }
   public TeachingAssistantSet getTas()
   {
      TeachingAssistantSet result = new TeachingAssistantSet();
      for (Room obj : this)
      {
         result.addAll(obj.getTas());
      }
      return result;
   }

   public RoomSet filterTas(Object value)
   {
      ObjectSet neighbors = new ObjectSet().init(value);
      RoomSet answer = new RoomSet();
      for (Room obj : this)
      {
         if (! neighbors.containsAny(obj.getTas()))
         {
            answer.add(obj);
         }
      }
      return answer;
   }

   public RoomSet withTas(TeachingAssistant value)
   {
      for (Room obj : this)
      {
         obj.withTas(value);
      }
      return this;
   }
   public UniversitySet getUniversity()
   {
      UniversitySet result = new UniversitySet();
      for (Room obj : this)
      {
         result.add(obj.getUniversity());
      }
      return result;
   }

   public RoomSet filterUniversity(Object value)
   {
      ObjectSet neighbors = new ObjectSet().init(value);
      RoomSet answer = new RoomSet();
      for (Room obj : this)
      {
         if (neighbors.contains(obj.getUniversity()) || (neighbors.isEmpty() && obj.getUniversity() == null))
         {
            answer.add(obj);
         }
      }
      return answer;
   }

   public RoomSet withUniversity(University value)
   {
      for (Room obj : this)
      {
         obj.withUniversity(value);
      }
      return this;
   }
   public RoomSet findPath(int motivation)
   {
      return RoomSet.EMPTY_SET;
   }

}