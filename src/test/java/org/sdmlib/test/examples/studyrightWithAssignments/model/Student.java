package org.sdmlib.test.examples.studyrightWithAssignments.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.UniversitySet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;

public class Student
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
   public static final String PROPERTY_ASSIGNMENTPOINTS = "assignmentPoints";

   private int assignmentPoints;

   public int getAssignmentPoints()
   {
      return this.assignmentPoints;
   }

   public void setAssignmentPoints(int value)
   {
      if (this.assignmentPoints != value)
      {         int oldValue = this.assignmentPoints;
         this.assignmentPoints = value;
         firePropertyChange(PROPERTY_ASSIGNMENTPOINTS, oldValue, value);
      }
   }

   public Student withAssignmentPoints(int value)
   {
      setAssignmentPoints(value);
      return this;
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

   public Student withCredits(int value)
   {
      setCredits(value);
      return this;
   }

   public static final String PROPERTY_ID = "id";

   private String id;

   public String getId()
   {
      return this.id;
   }

   public void setId(String value)
   {
      if (this.id != value)
      {         String oldValue = this.id;
         this.id = value;
         firePropertyChange(PROPERTY_ID, oldValue, value);
      }
   }

   public Student withId(String value)
   {
      setId(value);
      return this;
   }

   public static final String PROPERTY_MOTIVATION = "motivation";

   private int motivation;

   public int getMotivation()
   {
      return this.motivation;
   }

   public void setMotivation(int value)
   {
      if (this.motivation != value)
      {         int oldValue = this.motivation;
         this.motivation = value;
         firePropertyChange(PROPERTY_MOTIVATION, oldValue, value);
      }
   }

   public Student withMotivation(int value)
   {
      setMotivation(value);
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

   public Student withName(String value)
   {
      setName(value);
      return this;
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
            oldValue.withoutStudents(this);
         }
         this.university = value;
         if (value != null) {
            value.withStudents(this);
         }
         firePropertyChange(PROPERTY_UNIVERSITY, oldValue, value);
         changed = true;
      }
      return changed;
   }

   public Student withUniversity(University value)
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
}