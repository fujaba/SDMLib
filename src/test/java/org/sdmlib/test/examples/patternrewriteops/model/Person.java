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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainModel.java'>TrainModel.java</a>
 * @see org.sdmlib.test.examples.patternrewriteops.TrainModel#TrainModel
 */
   public  class Person implements SendableEntity
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
      setTrain(null);
      setStation(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Person ----------------------------------- Train
    *              passengers                   train
    * </pre>
    */
   
   public static final String PROPERTY_TRAIN = "train";

   private Train train = null;

   public Train getTrain()
   {
      return this.train;
   }

   public boolean setTrain(Train value)
   {
      boolean changed = false;
      
      if (this.train != value)
      {
         Train oldValue = this.train;
         
         if (this.train != null)
         {
            this.train = null;
            oldValue.withoutPassengers(this);
         }
         
         this.train = value;
         
         if (value != null)
         {
            value.withPassengers(this);
         }
         
         firePropertyChange(PROPERTY_TRAIN, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Person withTrain(Train value)
   {
      setTrain(value);
      return this;
   } 

   public Train createTrain()
   {
      Train value = new Train();
      withTrain(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Person ----------------------------------- Station
    *              people                   station
    * </pre>
    */
   
   public static final String PROPERTY_STATION = "station";

   private Station station = null;

   public Station getStation()
   {
      return this.station;
   }

   public boolean setStation(Station value)
   {
      boolean changed = false;
      
      if (this.station != value)
      {
         Station oldValue = this.station;
         
         if (this.station != null)
         {
            this.station = null;
            oldValue.withoutPeople(this);
         }
         
         this.station = value;
         
         if (value != null)
         {
            value.withPeople(this);
         }
         
         firePropertyChange(PROPERTY_STATION, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Person withStation(Station value)
   {
      setStation(value);
      return this;
   } 

   public Station createStation()
   {
      Station value = new Station();
      withStation(value);
      return value;
   } 
}
