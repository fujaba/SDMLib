/*
   Copyright (c) 2017 Stefan
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.test.examples.patternrewriteops.model;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.test.examples.patternrewriteops.model.util.PersonSet;
import org.sdmlib.test.examples.patternrewriteops.model.Person;
import org.sdmlib.test.examples.patternrewriteops.model.Station;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainModel.java'>TrainModel.java</a>
 * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
 */
   public  class Train implements SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = null;
   
   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
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
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(propertyName, listener);
   	return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners.removePropertyChangeListener(listener);
   	}
   	listeners.removePropertyChangeListener(listener);
   	return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(propertyName, listener);
   	}
   	return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      withoutPassengers(this.getPassengers().toArray(new Person[this.getPassengers().size()]));
      setStation(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Train ----------------------------------- Person
    *              train                   passengers
    * </pre>
    */
   
   public static final String PROPERTY_PASSENGERS = "passengers";

   private PersonSet passengers = null;
   
   public PersonSet getPassengers()
   {
      if (this.passengers == null)
      {
         return PersonSet.EMPTY_SET;
      }
   
      return this.passengers;
   }

   public Train withPassengers(Person... value)
   {
      if(value==null){
         return this;
      }
      for (Person item : value)
      {
         if (item != null)
         {
            if (this.passengers == null)
            {
               this.passengers = new PersonSet();
            }
            
            boolean changed = this.passengers.add (item);

            if (changed)
            {
               item.withTrain(this);
               firePropertyChange(PROPERTY_PASSENGERS, null, item);
            }
         }
      }
      return this;
   } 

   public Train withoutPassengers(Person... value)
   {
      for (Person item : value)
      {
         if ((this.passengers != null) && (item != null))
         {
            if (this.passengers.remove(item))
            {
               item.setTrain(null);
               firePropertyChange(PROPERTY_PASSENGERS, item, null);
            }
         }
      }
      return this;
   }

   public Person createPassengers()
   {
      Person value = new Person();
      withPassengers(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Train ----------------------------------- Station
    *              trains                   station
    * </pre>
    */
   
   public static final String PROPERTY_STATION = "station";

   private Station station = null;

   public Station getStation()
   {
      return this.station;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
 */
   public boolean setStation(Station value)
   {
      boolean changed = false;
      
      if (this.station != value)
      {
         Station oldValue = this.station;
         
         if (this.station != null)
         {
            this.station = null;
            oldValue.withoutTrains(this);
         }
         
         this.station = value;
         
         if (value != null)
         {
            value.withTrains(this);
         }
         
         firePropertyChange(PROPERTY_STATION, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Train withStation(Station value)
   {
      setStation(value);
      return this;
   } 

     /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java'>TrainStoryboards.java</a>
 */
   public Station createStation()
   {
      Station value = new Station();
      withStation(value);
      return value;
   } 
}
