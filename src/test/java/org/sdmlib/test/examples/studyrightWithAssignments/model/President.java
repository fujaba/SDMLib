package org.sdmlib.test.examples.studyrightWithAssignments.model;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;


public class President
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
            oldValue.setPresident(null);
         }
         this.university = value;
         if (value != null) {
            value.withPresident(this);
         }
         firePropertyChange(PROPERTY_UNIVERSITY, oldValue, value);
         changed = true;
      }
      return changed;
   }

   public President withUniversity(University value)
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