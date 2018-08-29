package org.sdmlib.test.examples.studyrightWithAssignments.model;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.StudentSet;

   /**
    * 
    * see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testStudyRightObjectModelNavigationAndQueries
 * see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testStudyRightWithAssignmentsStoryboard
 * see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testJsonPersistency
 */
   public class Assignment
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
   public static final String PROPERTY_CONTENT = "content";

   private String content;

   public String getContent()
   {
      return this.content;
   }

   public void setContent(String value)
   {
      if (this.content != value)
      {         String oldValue = this.content;
         this.content = value;
         firePropertyChange(PROPERTY_CONTENT, oldValue, value);
      }
   }

   public Assignment withContent(String value)
   {
      setContent(value);
      return this;
   }

   public static final String PROPERTY_POINTS = "points";

   private int points;

   public int getPoints()
   {
      return this.points;
   }

   public void setPoints(int value)
   {
      if (this.points != value)
      {         int oldValue = this.points;
         this.points = value;
         firePropertyChange(PROPERTY_POINTS, oldValue, value);
      }
   }

   public Assignment withPoints(int value)
   {
      setPoints(value);
      return this;
   }


   public static final String PROPERTY_ROOM = "room";

   private Room room = null;

   public Room getRoom()
   {
      return this.room;
   }

   public boolean setRoom(Room value)
   {
      boolean changed = false;
      if (this.room != value) {
         Room oldValue = this.room;
         if (this.room != null) {
            this.room = null;
            oldValue.withoutAssignments(this);
         }
         this.room = value;
         if (value != null) {
            value.withAssignments(this);
         }
         firePropertyChange(PROPERTY_ROOM, oldValue, value);
         changed = true;
      }
      return changed;
   }

   public Assignment withRoom(Room value)
   {
      this.setRoom(value);
      return this;
   }

   public Room createRoom()
   {
      Room value = new Room();
      withRoom(value);
      return value;
   }

   public static final String PROPERTY_STUDENTS = "students";

   private StudentSet students = null;

   public StudentSet getStudents()
   {
      return this.students;
   }

   public Assignment withStudents(Student... value)
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
               item.withDone(this);
               firePropertyChange(PROPERTY_STUDENTS, null, item);
            }
         }
      }
      return this;
   }

   public Assignment withoutStudents(Student... value)
   {
      for (Student item : value) {
         if (this.students != null && item != null) {
            if (this.students.remove(item)) {
               item.withoutDone(this);
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
}
