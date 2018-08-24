package org.sdmlib.test.examples.studyrightWithAssignments.model.util;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Assignment;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;

public class AssignmentSet extends SimpleSet<Assignment>
{
	public static final AssignmentSet EMPTY_SET = new AssignmentSet().withFlag(AssignmentSet.READONLY);

	public Class<?> getTypClass() {
		return Assignment.class;
	}

	@Override
	public AssignmentSet getNewList(boolean keyValue) {
		return new AssignmentSet();
	}


   public StringList getContent()
   {
      StringList result = new StringList();
      for (Assignment obj : this)
      {
         result.add(obj.getContent());
      }
      return result;
   }

   public AssignmentSet filterContent(String value)
   {
      AssignmentSet result = new AssignmentSet();
      for(Assignment obj : this)
      {
         if (value == obj.getContent())
         {
            result.add(obj);
         }
      }
      return result;
   }

   public AssignmentSet withContent(String value) {
      for (Assignment obj : this)
      {
         obj.setContent(value);
      }
      return this;
   }
   public NumberList getPoints()
   {
      NumberList result = new NumberList();
      for (Assignment obj : this)
      {
         result.add(obj.getPoints());
      }
      return result;
   }

   public AssignmentSet filterPoints(int value)
   {
      AssignmentSet result = new AssignmentSet();
      for(Assignment obj : this)
      {
         if (value == obj.getPoints())
         {
            result.add(obj);
         }
      }
      return result;
   }

   public AssignmentSet withPoints(int value) {
      for (Assignment obj : this)
      {
         obj.setPoints(value);
      }
      return this;
   }
   public AssignmentSet getRoom()
   {
      AssignmentSet result = new AssignmentSet();
      for (Assignment obj : this)
      {
         result.with(obj.getRoom());
      }
      return result;
   }

   public AssignmentSet filterRoom(Object value)
   {
      ObjectSet neighbors = new ObjectSet().init(value);
      AssignmentSet answer = new AssignmentSet();
      for (Assignment obj : this)
      {
         if (neighbors.contains(obj.getRoom()) || (neighbors.isEmpty() && obj.getRoom() == null))
         {
            answer.add(obj);
         }
      }
      return answer;
   }

   public AssignmentSet withRoom(Room value)
   {
      for (Assignment obj : this)
      {
         obj.withRoom(value);
      }
      return this;
   }
   public AssignmentSet getStudents()
   {
      AssignmentSet result = new AssignmentSet();
      for (Assignment obj : this)
      {
         result.with(obj.getStudents());
      }
      return result;
   }

   public AssignmentSet filterStudents(Object value)
   {
      ObjectSet neighbors = new ObjectSet().init(value);
      AssignmentSet answer = new AssignmentSet();
      for (Assignment obj : this)
      {
         if (! neighbors.containsAny(obj.getStudents()))
         {
            answer.add(obj);
         }
      }
      return answer;
   }

   public AssignmentSet withStudents(Student value)
   {
      for (Assignment obj : this)
      {
         obj.withStudents(value);
      }
      return this;
   }
}