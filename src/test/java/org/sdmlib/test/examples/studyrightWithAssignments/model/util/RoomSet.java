package org.sdmlib.test.examples.studyrightWithAssignments.model.util;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import de.uniks.networkparser.list.SimpleSet;
import java.util.Collection;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.StringList;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Assignment;
import org.sdmlib.test.examples.studyrightWithAssignments.model.TeachingAssistant;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;

public class RoomSet extends SimpleSet<Room>
{

   public Class<?> getTypClass()
   {
      return Room.class;
   }

   public RoomSet()
   {
      // empty
   }

   public RoomSet(Room... objects)
   {
      for (Room obj : objects)
      {
         this.add(obj);
      }
   }

   public RoomSet(Collection<Room> objects)
   {
      this.addAll(objects);
   }
		public static final RoomSet EMPTY_SET = new RoomSet().withFlag(RoomSet.READONLY);

   public String getEntryType()
   {
      return "org.sdmlib.test.examples.studyrightWithAssignments.model.Room";
   }
   @Override   public RoomSet getNewList(boolean keyValue)
   {
      return new RoomSet();
   }

   @SuppressWarnings("unchecked")
   public RoomSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Room>)value);
      }
      else if (value != null)
      {
         this.add((Room) value);
      }
      return this;
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
         result.with(obj.getDoors());
      }
      return result;
   }

   public RoomSet filterDoors(Object value)
   {
      ObjectSet neighbors = new ObjectSet();
      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      RoomSet answer = new RoomSet();
      for (Room obj : this)
      {
         if (! Collections.disjoint(neighbors, obj.getDoors()))
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
   public RoomSet getStudents()
   {
      RoomSet result = new RoomSet();
      for (Room obj : this)
      {
         result.with(obj.getStudents());
      }
      return result;
   }

   public RoomSet filterStudents(Object value)
   {
      ObjectSet neighbors = new ObjectSet();
      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      RoomSet answer = new RoomSet();
      for (Room obj : this)
      {
         if (! Collections.disjoint(neighbors, obj.getStudents()))
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
   public RoomSet getAssignments()
   {
      RoomSet result = new RoomSet();
      for (Room obj : this)
      {
         result.with(obj.getAssignments());
      }
      return result;
   }

   public RoomSet filterAssignments(Object value)
   {
      ObjectSet neighbors = new ObjectSet();
      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      RoomSet answer = new RoomSet();
      for (Room obj : this)
      {
         if (! Collections.disjoint(neighbors, obj.getAssignments()))
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
   public RoomSet getTas()
   {
      RoomSet result = new RoomSet();
      for (Room obj : this)
      {
         result.with(obj.getTas());
      }
      return result;
   }

   public RoomSet filterTas(Object value)
   {
      ObjectSet neighbors = new ObjectSet();
      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      RoomSet answer = new RoomSet();
      for (Room obj : this)
      {
         if (! Collections.disjoint(neighbors, obj.getTas()))
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
   public RoomSet getUniversity()
   {
      RoomSet result = new RoomSet();
      for (Room obj : this)
      {
         result.with(obj.getUniversity());
      }
      return result;
   }

   public RoomSet filterUniversity(Object value)
   {
      ObjectSet neighbors = new ObjectSet();
      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
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