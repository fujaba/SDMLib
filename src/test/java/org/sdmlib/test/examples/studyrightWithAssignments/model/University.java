package org.sdmlib.test.examples.studyrightWithAssignments.model;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.StudentSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.RoomSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.President;

   /**
    * 
    * see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testStudyRightObjectModelNavigationAndQueries
 * see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testStudyRightWithAssignmentsStoryboard
 * see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testJsonPersistency
 */
   public class University
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

   public University withName(String value)
   {
      setName(value);
      return this;
   }


   public static final String PROPERTY_STUDENTS = "students";

   private StudentSet students = null;

     /**
    * 
    * see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testStudyRightObjectModelNavigationAndQueries
 */
   public StudentSet getStudents()
   {
      return this.students;
   }

     /**
    * 
    * see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testStudyRightObjectModelNavigationAndQueries
 */
   public University withStudents(Student... value)
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
               item.setUniversity(this);
               firePropertyChange(PROPERTY_STUDENTS, null, item);
            }
         }
      }
      return this;
   }

   public University withoutStudents(Student... value)
   {
      for (Student item : value) {
         if (this.students != null && item != null) {
            if (this.students.remove(item)) {
               item.withUniversity(null);
            }
         }
      }
      return this;
   }

     /**
    * 
    * see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testStudyRightObjectModelNavigationAndQueries
 * see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testStudyRightWithAssignmentsStoryboard
 * see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testJsonPersistency
 */
   public Student createStudents()
   {
      Student value = new Student();
      withStudents(value);
      return value;
   }

   public static final String PROPERTY_ROOMS = "rooms";

   private RoomSet rooms = null;

     /**
    * 
    * see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testStudyRightObjectModelNavigationAndQueries
 */
   public RoomSet getRooms()
   {
      return this.rooms;
   }

   public University withRooms(Room... value)
   {
      if (value == null) {
         return this;
      }
      for (Room item : value) {
         if (item != null) {
            if (this.rooms == null) {
               this.rooms = new RoomSet();
            }
            boolean changed = this.rooms.add(item);
            if (changed)
            {
               firePropertyChange(PROPERTY_ROOMS, null, item);
            }
         }
      }
      return this;
   }

   public University withoutRooms(Room... value)
   {
      for (Room item : value) {
         if (this.rooms != null && item != null) {
            this.rooms.remove(item);
         }
      }
      return this;
   }

     /**
    * 
    * see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testStudyRightObjectModelNavigationAndQueries
 * see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testStudyRightWithAssignmentsStoryboard
 */
   public Room createRooms()
   {
      Room value = new Room();
      withRooms(value);
      return value;
   }

   public static final String PROPERTY_PRESIDENT = "president";

   private President president = null;

   public President getPresident()
   {
      return this.president;
   }

   public boolean setPresident(President value)
   {
      boolean changed = false;
      if (this.president != value) {
         President oldValue = this.president;
         this.president = value;
         firePropertyChange(PROPERTY_PRESIDENT, oldValue, value);
         changed = true;
      }
      return changed;
   }

   public University withPresident(President value)
   {
      this.setPresident(value);
      return this;
   }

   public President createPresident()
   {
      President value = new President();
      withPresident(value);
      return value;
   }
}
