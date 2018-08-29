package org.sdmlib.test.examples.studyrightWithAssignments.model;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.RoomSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.StudentSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.AssignmentSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.TeachingAssistantSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;


public class Room
{
   protected PropertyChangeSupport listeners = null;

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue) {
      if (listeners != null) {
         listeners.firePropertyChange(propertyName, oldValue, newValue);
         return true;
      }
      return false;
   }

   public boolean addPropertyChangeListener(PropertyChangeListener listener)
   {
      if (listeners == null) {
         listeners = new PropertyChangeSupport(this);
      }
      listeners.addPropertyChangeListener(listener);
      return true;
   }

   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
   {
      if (listeners == null) {
         listeners = new PropertyChangeSupport(this);
      }
      listeners.addPropertyChangeListener(propertyName, listener);
      return true;
   }

   public boolean removePropertyChangeListener(PropertyChangeListener listener)
   {
      if (listeners != null) {
         listeners.removePropertyChangeListener(listener);
      }
      listeners.removePropertyChangeListener(listener);
      return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener)
   {
      if (listeners != null) {
         listeners.removePropertyChangeListener(propertyName, listener);
      }
      return true;
   }
   public static final String PROPERTY_CREDITS = "credits";

   private int credits;

   public int getCredits()
   {
      return this.credits;
   }

   public void setCredits(int value)
   {
      if (this.credits != value)
      {         int oldValue = this.credits;
         this.credits = value;
         firePropertyChange(PROPERTY_CREDITS, oldValue, value);
      }
   }

   public Room withCredits(int value)
   {
      setCredits(value);
      return this;
   }

   public static final String PROPERTY_NAME = "name";

   private String name;

   public String getName()
   {
      return this.name;
   }

   public void setName(String value)
   {
      if (this.name != value)
      {         String oldValue = this.name;
         this.name = value;
         firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }

   public Room withName(String value)
   {
      setName(value);
      return this;
   }

   public static final String PROPERTY_TOPIC = "topic";

   private String topic;

   public String getTopic()
   {
      return this.topic;
   }

   public void setTopic(String value)
   {
      if (this.topic != value)
      {         String oldValue = this.topic;
         this.topic = value;
         firePropertyChange(PROPERTY_TOPIC, oldValue, value);
      }
   }

   public Room withTopic(String value)
   {
      setTopic(value);
      return this;
   }


   public static final String PROPERTY_DOORS = "doors";

   private RoomSet doors = null;

   public RoomSet getDoors()
   {
      return this.doors;
   }

   public Room withDoors(Room... value)
   {
      if (value == null) {
         return this;
      }
      for (Room item : value) {
         if (item != null) {
            if (this.doors == null) {
               this.doors = new RoomSet();
            }
            boolean changed = this.doors.add(item);
            if (changed)
            {
               item.withDoors(this);
               firePropertyChange(PROPERTY_DOORS, null, item);
            }
         }
      }
      return this;
   }

   public Room withoutDoors(Room... value)
   {
      for (Room item : value) {
         if (this.doors != null && item != null) {
            if (this.doors.remove(item)) {
               item.withoutDoors(this);
            }
         }
      }
      return this;
   }

   public Room createDoors()
   {
      Room value = new Room();
      withDoors(value);
      return value;
   }

   public static final String PROPERTY_STUDENTS = "students";

   private StudentSet students = null;

   public StudentSet getStudents()
   {
      return this.students;
   }

   public Room withStudents(Student... value)
   {
      if (value == null) {
         return this;
      }
      for (Student item : value) {
         if (item != null) {
            if (this.students == null) {
               this.students = new StudentSet();
            }
            boolean changed = this.students.add(item);
            if (changed)
            {
               item.setIn(this);
               firePropertyChange(PROPERTY_STUDENTS, null, item);
            }
         }
      }
      return this;
   }

   public Room withoutStudents(Student... value)
   {
      for (Student item : value) {
         if (this.students != null && item != null) {
            if (this.students.remove(item)) {
               item.withIn(null);
            }
         }
      }
      return this;
   }

   public Student createStudents()
   {
      Student value = new Student();
      withStudents(value);
      return value;
   }

   public static final String PROPERTY_ASSIGNMENTS = "assignments";

   private AssignmentSet assignments = null;

   public AssignmentSet getAssignments()
   {
      return this.assignments;
   }

   public Room withAssignments(Assignment... value)
   {
      if (value == null) {
         return this;
      }
      for (Assignment item : value) {
         if (item != null) {
            if (this.assignments == null) {
               this.assignments = new AssignmentSet();
            }
            boolean changed = this.assignments.add(item);
            if (changed)
            {
               item.setRoom(this);
               firePropertyChange(PROPERTY_ASSIGNMENTS, null, item);
            }
         }
      }
      return this;
   }

   public Room withoutAssignments(Assignment... value)
   {
      for (Assignment item : value) {
         if (this.assignments != null && item != null) {
            if (this.assignments.remove(item)) {
               item.withRoom(null);
            }
         }
      }
      return this;
   }

     /**
    * 
    * see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testStudyRightObjectModelNavigationAndQueries
 */
   public Assignment createAssignments()
   {
      Assignment value = new Assignment();
      withAssignments(value);
      return value;
   }

   public static final String PROPERTY_TAS = "tas";

   private TeachingAssistantSet tas = null;

   public TeachingAssistantSet getTas()
   {
      return this.tas;
   }

   public Room withTas(TeachingAssistant... value)
   {
      if (value == null) {
         return this;
      }
      for (TeachingAssistant item : value) {
         if (item != null) {
            if (this.tas == null) {
               this.tas = new TeachingAssistantSet();
            }
            boolean changed = this.tas.add(item);
            if (changed)
            {
               item.setRoom(this);
               firePropertyChange(PROPERTY_TAS, null, item);
            }
         }
      }
      return this;
   }

   public Room withoutTas(TeachingAssistant... value)
   {
      for (TeachingAssistant item : value) {
         if (this.tas != null && item != null) {
            if (this.tas.remove(item)) {
               item.withRoom(null);
            }
         }
      }
      return this;
   }

   public TeachingAssistant createTas()
   {
      TeachingAssistant value = new TeachingAssistant();
      withTas(value);
      return value;
   }

   public static final String PROPERTY_UNIVERSITY = "university";

   private University university = null;

   public University getUniversity()
   {
      return this.university;
   }

   public boolean setUniversity(University value)
   {
      boolean changed = false;
      if (this.university != value) {
         University oldValue = this.university;
         if (this.university != null) {
            this.university = null;
            oldValue.withoutRooms(this);
         }
         this.university = value;
         if (value != null) {
            value.withRooms(this);
         }
         firePropertyChange(PROPERTY_UNIVERSITY, oldValue, value);
         changed = true;
      }
      return changed;
   }

   public Room withUniversity(University value)
   {
      this.setUniversity(value);
      return this;
   }

   public University createUniversity()
   {
      University value = new University();
      withUniversity(value);
      return value;
   }
   public String findPath(int motivation)    {
      return null;
    }


}
