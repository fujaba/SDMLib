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

import org.sdmlib.test.examples.patternrewriteops.model.util.StationSet;

import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainModel.java'>TrainModel.java</a>
 * @see org.sdmlib.test.examples.patternrewriteops.TrainModel#TrainModel
 */
   public  class SignalFlag implements SendableEntity
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
      withoutStation(this.getStation().toArray(new Station[this.getStation().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * SignalFlag ----------------------------------- Station
    *              flag                   station
    * </pre>
    */
   
   public static final String PROPERTY_STATION = "station";

   private StationSet station = null;
   
   public StationSet getStation()
   {
      if (this.station == null)
      {
         return StationSet.EMPTY_SET;
      }
   
      return this.station;
   }

   public SignalFlag withStation(Station... value)
   {
      if(value==null){
         return this;
      }
      for (Station item : value)
      {
         if (item != null)
         {
            if (this.station == null)
            {
               this.station = new StationSet();
            }
            
            boolean changed = this.station.add (item);

            if (changed)
            {
               item.withFlag(this);
               firePropertyChange(PROPERTY_STATION, null, item);
            }
         }
      }
      return this;
   } 

   public SignalFlag withoutStation(Station... value)
   {
      for (Station item : value)
      {
         if ((this.station != null) && (item != null))
         {
            if (this.station.remove(item))
            {
               item.setFlag(null);
               firePropertyChange(PROPERTY_STATION, item, null);
            }
         }
      }
      return this;
   }

   public Station createStation()
   {
      Station value = new Station();
      withStation(value);
      return value;
   } 
}
